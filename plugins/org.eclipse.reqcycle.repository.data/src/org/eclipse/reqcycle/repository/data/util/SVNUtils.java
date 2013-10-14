/*****************************************************************************
 * Copyright (c) 2013 AtoS.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Anass RADOUANI (AtoS) anass.radouani@atos.net - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.reqcycle.repository.data.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.reqcycle.repository.data.IDataManager;
import org.eclipse.team.svn.core.connector.ISVNConnector;
import org.eclipse.team.svn.core.connector.ISVNConnector.Depth;
import org.eclipse.team.svn.core.connector.SVNChangeStatus;
import org.eclipse.team.svn.core.connector.SVNConnectorException;
import org.eclipse.team.svn.core.connector.SVNEntryReference;
import org.eclipse.team.svn.core.connector.SVNEntryRevisionReference;
import org.eclipse.team.svn.core.connector.SVNRevision;
import org.eclipse.team.svn.core.extension.CoreExtensionsManager;
import org.eclipse.team.svn.core.extension.factory.ISVNConnectorFactory;
import org.eclipse.team.svn.core.operation.SVNNullProgressMonitor;
import org.eclipse.team.svn.core.utility.SVNUtility;
import org.eclipse.ziggurat.inject.ZigguratInject;

import RequirementSourceConf.RequirementSource;
import RequirementSourceData.AbstractElement;
import RequirementSourceData.RequirementsContainer;
import RequirementSourceData.Section;

public class SVNUtils {

	/** The svn connector factory. */
	protected static ISVNConnectorFactory svnConnectorFactory = CoreExtensionsManager.instance().getSVNConnectorFactory();

	/** The data manager. */
	static IDataManager dataManager = ZigguratInject.make(IDataManager.class);

	/**
	 * Commit.
	 * 
	 * @param path
	 *        the element to commit path
	 * @param message
	 *        the commit message
	 * @param depth
	 *        the commit depth
	 * @param options
	 *        the commit options
	 * @return long[]
	 * @throws SVNConnectorException
	 *         the SVN connector exception
	 */
	public static long[] commit(String path, String message, int depth, long options) throws SVNConnectorException {
		final ISVNConnector connector = svnConnectorFactory.newInstance();
		long[] l = connector.commit(new String[]{ path }, message, null, depth, options, null, new SVNNullProgressMonitor());
		return l;
	}

	/**
	 * Gets the SVN info.
	 * 
	 * @param resource
	 *        the resource
	 * @return the SVN info
	 */
	public static SVNChangeStatus getSVNInfo(IResource resource) {
		return SVNUtility.getSVNInfoForNotConnected(resource);
	}

	/**
	 * Gets the input stream from svn file url.
	 * 
	 * @param SVNFileUrl
	 *        the SVN file url
	 * @param revision
	 *        the svn revision
	 * @return the input stream from svn file url
	 */
	public static InputStream getInputStreamFromSVNFileUrl(String SVNFileUrl, SVNRevision revision) {
		final SVNEntryRevisionReference reference = new SVNEntryRevisionReference(new SVNEntryReference(SVNFileUrl), revision);

		final ISVNConnector connector = svnConnectorFactory.newInstance();

		boolean referenceAvailable = true;
		try {
			SVNUtility.info(connector, reference, ISVNConnector.Depth.EMPTY, new SVNNullProgressMonitor());
		} catch (SVNConnectorException e) {
			referenceAvailable = false;
		}

		if(referenceAvailable) {
			PipedInputStream in = new PipedInputStream(10240);
			try {
				final PipedOutputStream out = new PipedOutputStream(in);
				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							connector.streamFileContent(reference, 10240, out, new SVNNullProgressMonitor());
						} catch (SVNConnectorException e) {
							e.printStackTrace();
						}
						try {
							out.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}).start();
				return in;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Synchronize svn requirement source.
	 * 
	 * @param source
	 *        the requirement source to synchronize
	 * @throws SVNConnectorException
	 * @throws IOException
	 * @throws UnconnectedException
	 *         the unconnected exception
	 * @throws Exception
	 *         the exception
	 */
	public static long[] synchronizeSVNSource(RequirementSource source) throws SVNConnectorException, IOException {
		// get requirements resource
		Resource mainResource = source.getContents().eResource();
		if(mainResource == null) {
			// FIXME : Exception
			return null;
		}
		ResourceSet rs = mainResource.getResourceSet();

		SVNChangeStatus svnInfo = SVNUtils.getSVNInfo(WorkspaceSynchronizer.getFile(mainResource));
		if(svnInfo == null) {
			//FIXME : Exception
			return null;
		}

		Resource wResource = getSVNRevisionResource(svnInfo, SVNRevision.WORKING, rs);
		Resource hResource = getSVNRevisionResource(svnInfo, SVNRevision.HEAD, rs);

		RequirementsContainer wRC = getFirstReqContainer(wResource);
		RequirementsContainer hRC = getFirstReqContainer(hResource);

		Resource resource = wRC.eResource();
		Collection<AbstractElement> difference = retrieveNewElement(resource, hRC.getRequirements());

		moveElementToSource(mainResource, difference);

		dataManager.save();
		IFile file = WorkspaceSynchronizer.getFile(mainResource);
		return commit(file.getLocationURI().getPath(), "ReqCycle Commit", Depth.infinityOrFiles(false), ISVNConnector.Options.FORCE);
	}

	/**
	 * Moves elements to the given resource if it doesn't already contain them.
	 * 
	 * @param mainResource
	 *        the main resource where elements are moved
	 * @param elements
	 *        the elements
	 */
	protected static void moveElementToSource(Resource mainResource, Collection<AbstractElement> elements) {
		for(AbstractElement abstractElement : elements) {
			EObject container = abstractElement.eContainer();
			Resource resource = abstractElement.eResource();
			if(container != null && resource != null) {
				String fragment = resource.getURIFragment(container);
				if(fragment != null && !fragment.isEmpty()) {
					EObject eobj = mainResource.getEObject(fragment);
					if(eobj instanceof Section) {
						((Section)eobj).getChildren().add(abstractElement);
					} else if(eobj instanceof RequirementsContainer) {
						((RequirementsContainer)eobj).getRequirements().add(abstractElement);
					}
				}
			}
		}
	}

	/**
	 * Gets the element not contained in the resource
	 * 
	 * @param resource
	 *        the resource
	 * @param elements
	 *        the elements
	 * @return elements not found in the resource
	 */
	protected static Collection<AbstractElement> retrieveNewElement(Resource resource, Collection<AbstractElement> elements) {
		Collection<AbstractElement> result = new ArrayList<AbstractElement>();
		for(AbstractElement abstractElement : elements) {
			Resource r = abstractElement.eResource();
			String fragment = r.getURIFragment(abstractElement);
			if(resource.getEObject(fragment) == null) {
				result.add(abstractElement);
			} else if(abstractElement instanceof Section) {
				result.addAll(retrieveNewElement(resource, ((Section)abstractElement).getChildren()));
			}
		}
		return result;
	}

	/**
	 * Gets the first requirements container.
	 * 
	 * @param resource
	 *        the resource
	 * @return the first requirement container
	 */
	protected static RequirementsContainer getFirstReqContainer(Resource resource) {
		EList<EObject> contents = resource.getContents();
		if(contents != null && contents.size() > 0) {
			for(EObject eObject : contents) {
				if(eObject instanceof RequirementsContainer) {
					return (RequirementsContainer)eObject;
				}
			}
		}
		return null;
	}

	/**
	 * Gets the SVN revision resource.
	 * 
	 * @param svnChangeStatus
	 *        the svn change status
	 * @param svnRevision
	 *        the svn revision to get
	 * @param rs
	 *        the resource set containing the newly created resource
	 * @return the SVN revision resource
	 * @throws IOException
	 *         Signals that an I/O exception has occurred.
	 */
	public static Resource getSVNRevisionResource(SVNChangeStatus svnChangeStatus, SVNRevision svnRevision, ResourceSet rs) throws IOException {
		InputStream inputStreamWorking = SVNUtils.getInputStreamFromSVNFileUrl(svnChangeStatus.path, svnRevision);
		if(inputStreamWorking == null) {
			return null;
		}
		Resource resource = rs.createResource(URI.createURI(svnChangeStatus.url + "." + svnRevision.toString()));
		resource.load(inputStreamWorking, Collections.emptyMap());
		return resource;
	}

}

/*******************************************************************************
 *  * Copyright (c) 2013-2014 AtoS.
 *  * All rights reserved. This program and the accompanying materials
 *  * are made available under the terms of the Eclipse Public License v1.0
 *  * which accompanies this distribution, and is available at
 *  * http://www.eclipse.org/legal/epl-v10.html
 *  *
 *  * Contributors:
 *  *    {INITIAL AUTHOR} - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.xcos.model;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.viewers.ILabelProvider;
import org.polarsys.reqcycle.uri.exceptions.VisitableException;
import org.polarsys.reqcycle.uri.model.Reachable;
import org.polarsys.reqcycle.uri.model.ReachableObject;
import org.polarsys.reqcycle.uri.visitors.EmptyVisitable;
import org.polarsys.reqcycle.uri.visitors.IVisitable;
import org.polarsys.reqcycle.xcos.utils.XcosUtils;


/**
 * @author R. faudou
 * this class does the main job to create ReachableObjects from reachable
 *
 */
public class XcosReachableObject implements ReachableObject {

	private Reachable reachable = null;
	IFile file = null;
	private XcosElement element = null;

	public XcosReachableObject(Reachable t) {
		
		this.reachable = t;
		String path = t.trimFragment().toString()
				.replaceFirst(XcosUtils.PLATFORM, "");
		file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(path));
		
		// if root, then we create or retrieve model (only one model for one file)
		if (t.getFragment() == null) {
			
			element = getModel(file);
		}
		else {
			element = null;
		}
	}
	
	/**
	 * @param res
	 * @return
	 */
	private XcosElement getModel (IResource res) {
		//TODO real impl
		return XcosModelFactory.getModel(res);
	}


	@Override
	@SuppressWarnings("rawtypes")
	public Object getAdapter( Class adapter) {
		if (ILabelProvider.class.equals(adapter)) {
			return new XcosLabelProvider();
		}
		if (XcosElement.class.equals(adapter)) {
			return element;
		}
		return null;
	}

	@Override
	public IVisitable getVisitable() throws VisitableException {
		if (element != null) {
			return new XcosVisitable(element);
		} else {
			return new EmptyVisitable();
		}
	}

	@Override
	public String getRevisionIdentification() {
		return String.valueOf(file.getLocalTimeStamp());
	}

	@Override
	public Reachable getReachable(Object o) {
		return reachable;
	}

}

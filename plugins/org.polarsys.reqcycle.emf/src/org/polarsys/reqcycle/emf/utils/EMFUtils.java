/*******************************************************************************
 * * Copyright (c) 2013,2014 AtoS and others
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 * * Raphael Faudou (Samares Engineering) allowed to reference a meta model not yet registered
 * in the EMF registry
 *******************************************************************************/
package org.polarsys.reqcycle.emf.utils;

import java.net.URISyntaxException;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.polarsys.reqcycle.uri.IReachableCreator;
import org.polarsys.reqcycle.uri.model.Reachable;
import org.eclipse.ziggurat.inject.ZigguratInject;

public class EMFUtils {
	private static final List<URIHandler> DEFAULT_HANDLERS = URIHandler.DEFAULT_HANDLERS;

	public static Reachable getReachable(URI uri) {
		try {
			IReachableCreator creator = ZigguratInject
					.make(IReachableCreator.class);
			return creator.getReachable(new java.net.URI(uri.toString()));
		} catch (URISyntaxException e) {
			return null;
		}
	}

	public static EObject getEObject(Reachable t, ResourceSet set) {
		EObject result = null;
		URI uri = getEMFURI(t);
		Resource r = set.getResource(uri.trimFragment(), true);
		if (r != null) {
			result = r.getEObject(uri.fragment());
		}
		return result;
	}

	public static URI getEMFURI(Reachable t) {
		return URI.createURI(t.toString());
	}

	public static Reachable getReachable(EObject eobject) {
		try {
			IReachableCreator creator = ZigguratInject
					.make(IReachableCreator.class);
			return creator.getReachable(
					new java.net.URI(EcoreUtil.getURI(eobject).toString()),
					eobject);
		} catch (URISyntaxException e) {
			return null;
		}
	}

	public static ResourceSet getFastAndUnresolvingResourceSet() {
		ResourceSet set = new ResourceSetImpl() {
			URI firstURI = null;

			@Override
			public Resource getResource(URI uri, boolean loadOnDemand) {
				if (firstURI == null) {
					firstURI = uri;
				}
				if (uri.equals(firstURI)) {
					return super.getResource(uri, loadOnDemand);
				}
				return null;
			}

		};
		set.eSetDeliver(false);
		set.getLoadOptions().put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION,
				true);
		set.getLoadOptions().put(XMLResource.OPTION_DEFER_ATTACHMENT, true);
		set.getLoadOptions().put(XMLResource.OPTION_DISABLE_NOTIFY, true);
		return set;
	}

	public static ResourceSet getFAURSWithPathMaps() {
		ResourceSet set = new ResourceSetImpl() {
			URI firstURI = null;

			@Override
			public Resource getResource(URI uri, boolean loadOnDemand) {
				if (firstURI == null) {
					firstURI = uri;
				}
				if (uri.equals(firstURI)) {
					return super.getResource(uri, loadOnDemand);
				}
				if (uri.isPlatformPlugin() || "pathmap".equals(uri.scheme())
						|| "http".equals(uri.scheme())) {
					return super.getResource(uri, loadOnDemand);
				}
				return null;
			}
		};
		set.eSetDeliver(false);
		set.getLoadOptions().put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION,
				true);
		set.getLoadOptions().put(XMLResource.OPTION_DEFER_ATTACHMENT, true);
		// set.getLoadOptions().put(XMLResource.OPTION_DISABLE_NOTIFY, true);
		return set;
	}

	public static java.net.URI getURI(EObject e) {
		return getURI(e.eResource());
	}

	public static java.net.URI getURI(Resource r) {
		try {
			return new java.net.URI(r.getURI().toString());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getExtension(String path) {
		if (path == null || path.length() == 0) {
			return "";
		}
		int lastIndexOf = path.lastIndexOf(".");
		if (lastIndexOf < 0) {
			return "";
		}
		return path.substring(lastIndexOf + 1, path.length());
	}

	public static boolean isEMF(java.net.URI uri) {
		return isEMF(URI.createURI(uri.toString()));
	}

	public static boolean isEMF(URI uri) {
		boolean handles = false;
		for (URIHandler u : DEFAULT_HANDLERS) {
			if (u.canHandle(uri)) {
				handles = true;
				break;
			}
		}
		return handles
				&& Resource.Factory.Registry.INSTANCE
						.getExtensionToFactoryMap().containsKey(
								getExtension(uri.path()));
	}
	
	/**
	 * Check whether uri can be managed by URI handler. if checkRegistrationExtension is set to true
	 * check also if extension is registered in EMF registry.
	 * @param uri
	 * @param checkRegisteredExtension
	 * @return
	 */
	public static boolean isEMF(URI uri, boolean checkRegisteredExtension) {
		
		// if we want to check that EMF extension is registered in EMF registry, use default method
		if (checkRegisteredExtension) {
			return isEMF(uri);
		}
		//else, just check handler can do that.
		boolean handles = false;
		for (URIHandler u : DEFAULT_HANDLERS) {
			if (u.canHandle(uri)) {
				handles = true;
				break;
			}
		}
		return handles;
				
	}
}

/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.emf.handlers;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.reqcycle.emf.utils.EMFUtils;
import org.polarsys.reqcycle.uri.model.IObjectHandler;
import org.polarsys.reqcycle.uri.model.IReachableHandler;
import org.polarsys.reqcycle.uri.model.Reachable;
import org.polarsys.reqcycle.uri.model.ReachableObject;
import org.eclipse.ziggurat.inject.ZigguratInject;

public class EMFURIHandler implements IReachableHandler, IObjectHandler {

	@Override
	public boolean handlesReachable(Reachable t) {
		URI uri = EMFUtils.getEMFURI(t);
		return handlesURI(uri);
	}

	public boolean handlesURI(URI uri) {
		return EMFUtils.isEMF(uri);
	}

	@Override
	public ReachableObject getFromReachable(final Reachable t) {
		EMFReachableObject emfReachableObject = doGetReachableObject(t);
		ZigguratInject.inject(emfReachableObject);
		return emfReachableObject;
	}

	protected EMFReachableObject doGetReachableObject(final Reachable t) {
		return new EMFReachableObject(t);
	}

	@Override
	public boolean handlesObject(Object t) {
		return handles(t);
	}

	public boolean handles(Object t) {
		boolean result = false;
		result |= t instanceof EObject;
		if (!result) {
			result |= t instanceof Resource;
		}
		if (!result) {
			if (t instanceof IResource) {
				result |= handlesURI(URI.createPlatformResourceURI(
						((IResource) t).getFullPath().toString(), true));
			}
		}
		if (!result) {
			if (t instanceof IAdaptable) {
				IAdaptable adaptable = (IAdaptable) t;
				result = (adaptable.getAdapter(EObject.class) != null);
			}
		}
		return result;
	}

	@Override
	public ReachableObject getFromObject(final Object o) {
		EMFReachableObject result = null;
		if (o instanceof IResource) {
			IResource res = (IResource) o;
			result = doGetReachableObject(EMFUtils.getReachable(URI
					.createPlatformResourceURI(res.getFullPath().toString(),
							true)));

		} else if (o instanceof EObject) {
			result = doGetReachableObject(EMFUtils.getReachable(((EObject) o)));
		} else if (o instanceof Resource) {
			result = doGetReachableObject(EMFUtils.getReachable(((Resource) o)
					.getURI()));
		} else if (o instanceof IAdaptable) {
			IAdaptable adaptable = (IAdaptable) o;
			result = doGetReachableObject(EMFUtils
					.getReachable((EObject) adaptable.getAdapter(EObject.class)));
		}
		if (result != null) {
			ZigguratInject.inject(result);
		}
		return result;
	}
}

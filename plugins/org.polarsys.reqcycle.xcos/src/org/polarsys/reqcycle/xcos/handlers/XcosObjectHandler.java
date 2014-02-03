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
/**
 * 
 */
package org.polarsys.reqcycle.xcos.handlers;

import static org.polarsys.reqcycle.xcos.utils.XcosUtils.XcosExtension;

import org.eclipse.core.internal.resources.File;
import org.eclipse.core.internal.resources.Resource;
import org.eclipse.core.resources.IFile;
import org.polarsys.reqcycle.uri.model.IObjectHandler;
import org.polarsys.reqcycle.uri.model.Reachable;
import org.polarsys.reqcycle.uri.model.ReachableObject;
import org.polarsys.reqcycle.xcos.model.XcosElement;
import org.polarsys.reqcycle.xcos.model.XcosModel;
import org.polarsys.reqcycle.xcos.utils.XcosUtils;


/**
 * @author R. Faudou
 * This class defines which types of objects are of interest and can be transformed in ReachableObjects
 * We are interested in IFile and XcosBlock and XcosModel elements. We filter
 * on XCosElement, a marker for all XCos elements.
 *
 */
public class XcosObjectHandler implements IObjectHandler {

	/**
	 * 
	 */
	public XcosObjectHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ReachableObject getFromObject(Object object) {
		if (object instanceof IFile) {
			IFile file = (IFile) object;
			if (XcosExtension.equalsIgnoreCase(file.getFileExtension())) {
				return XcosUtils.getReachableObject(file);
			}
		}
		if (object instanceof XcosElement) {
			XcosElement xce = (XcosElement) object;
			return XcosUtils.getReachable(xce);
		}
		
		return null;
	}

	@SuppressWarnings("restriction")
	@Override
	public boolean handlesObject(Object object) {
		
		// in case this is a file, we must ensure that it is xcos extension.
		// else we care only of XcosElements.
		
		if (object instanceof IFile) {
			IFile file = (IFile) object;
			if (XcosExtension.equalsIgnoreCase(file.getFileExtension())) {
				//TODO real handleObject for Xcos
				return true;
			}
		}
		if (object instanceof Resource) {
			return true;
			
		}
		return (object instanceof XcosElement);
	}



}

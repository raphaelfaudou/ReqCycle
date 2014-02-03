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


import org.eclipse.core.resources.IResource;


public class XcosElement {
	private String name = "NONAME";
	private IResource resource = null;
	
	public XcosElement(String aName, IResource res) {
		this.name = aName;
		this.resource = res;
	}

	public IResource getResource() {

		return resource;
	}

	public String getElementName() {
		return name;
	}

	public XcosElement getParent() {
		return null;
	}



}

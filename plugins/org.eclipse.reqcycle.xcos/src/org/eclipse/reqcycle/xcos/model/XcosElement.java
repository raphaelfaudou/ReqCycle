package org.eclipse.reqcycle.xcos.model;


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

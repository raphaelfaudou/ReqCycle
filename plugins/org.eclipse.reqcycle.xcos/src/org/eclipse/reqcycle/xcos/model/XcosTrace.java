package org.eclipse.reqcycle.xcos.model;

import org.eclipse.core.resources.IResource;

public class XcosTrace extends XcosElement{
	
	private String semantics = "TRACE";
	private String ref = "UNKNOWN-EXT-REFERENCE";
	private XcosElement source;

	public XcosTrace(String aSemantics, XcosElement aSource, String externalObjectReference, String aName, IResource res) {
		super(aName, res);
		if (aSemantics != null)  {
			if (aSemantics != "")
				this.semantics = aSemantics;
		}
		ref = externalObjectReference;
		source = aSource;
		
	}
	
	public String toString() {
		return "{semantics="+semantics+";source=" + source + ";ref="+ref+";name="+this.getElementName()+"}";
	}

	public String getSemantics() {
		return semantics;
	}



	public String getRef() {
		return ref;
	}

	public XcosElement getSource() {
		return source;
	}



	

	
}

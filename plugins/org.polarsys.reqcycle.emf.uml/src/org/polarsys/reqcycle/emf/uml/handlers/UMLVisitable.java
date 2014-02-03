package org.polarsys.reqcycle.emf.uml.handlers;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.polarsys.reqcycle.emf.utils.EMFUtils;
import org.polarsys.reqcycle.emf.visitors.EMFVisitable;

public class UMLVisitable extends EMFVisitable {

	public UMLVisitable(URI uri) {
		super(uri);
	}

	@Override
	protected ResourceSet getResourceSet() {
		return EMFUtils.getFAURSWithPathMaps();
	}

}

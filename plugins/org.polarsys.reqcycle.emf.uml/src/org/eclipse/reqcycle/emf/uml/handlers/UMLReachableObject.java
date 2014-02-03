package org.eclipse.reqcycle.emf.uml.handlers;

import org.eclipse.emf.common.util.URI;
import org.eclipse.reqcycle.emf.handlers.EMFReachableObject;
import org.eclipse.reqcycle.emf.visitors.EMFVisitable;
import org.eclipse.reqcycle.uri.model.Reachable;

public class UMLReachableObject extends EMFReachableObject {
	public UMLReachableObject(Reachable r) {
		super(r);
	}

	@Override
	protected EMFVisitable doGetVisitable(URI uri) {
		return new UMLVisitable(uri);
	}

}

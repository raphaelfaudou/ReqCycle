package org.polarsys.reqcycle.emf.uml.handlers;

import org.eclipse.emf.common.util.URI;
import org.polarsys.reqcycle.emf.handlers.EMFReachableObject;
import org.polarsys.reqcycle.emf.visitors.EMFVisitable;
import org.polarsys.reqcycle.uri.model.Reachable;

public class UMLReachableObject extends EMFReachableObject {
	public UMLReachableObject(Reachable r) {
		super(r);
	}

	@Override
	protected EMFVisitable doGetVisitable(URI uri) {
		return new UMLVisitable(uri);
	}

}

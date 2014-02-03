package org.polarsys.reqcycle.repository.data.reachables;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.polarsys.reqcycle.emf.handlers.EMFReachableObject;
import org.polarsys.reqcycle.emf.utils.EMFUtils;
import org.polarsys.reqcycle.emf.visitors.EMFVisitable;
import org.polarsys.reqcycle.uri.model.Reachable;


public class ConfReachableObject extends EMFReachableObject {

	public ConfReachableObject(Reachable t) {
		super(t);
	}

	@Override
	protected EMFVisitable doGetVisitable(URI uri) {
		return new EMFVisitable(uri) {

			@Override
			protected ResourceSet getResourceSet() {
				return EMFUtils.getFAURSWithPathMaps();
			}
		};
	}
}

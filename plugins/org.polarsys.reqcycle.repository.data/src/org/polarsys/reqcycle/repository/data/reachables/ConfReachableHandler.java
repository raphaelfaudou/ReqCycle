package org.polarsys.reqcycle.repository.data.reachables;

import org.eclipse.emf.common.util.URI;
import org.polarsys.reqcycle.emf.handlers.EMFReachableObject;
import org.polarsys.reqcycle.emf.handlers.EMFURIHandler;
import org.polarsys.reqcycle.uri.model.Reachable;


public class ConfReachableHandler extends EMFURIHandler {

	@Override
	public boolean handlesURI(URI uri) {
		return super.handlesURI(uri) && (uri.path().endsWith("emfconf") || uri.path().endsWith("reqcycle"));
	}

	@Override
	protected EMFReachableObject doGetReachableObject(Reachable t) {
		return new ConfReachableObject(t);
	}

}

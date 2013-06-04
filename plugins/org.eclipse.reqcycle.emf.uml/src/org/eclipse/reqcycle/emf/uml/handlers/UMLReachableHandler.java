package org.eclipse.reqcycle.emf.uml.handlers;

import org.eclipse.emf.common.util.URI;
import org.eclipse.reqcycle.emf.handlers.EMFReachableObject;
import org.eclipse.reqcycle.emf.handlers.EMFURIHandler;
import org.eclipse.reqcycle.uri.model.Reachable;

public class UMLReachableHandler extends EMFURIHandler {

    
    @Override
    public boolean handlesURI(URI uri)
    {
        return super.handlesURI(uri) && uri.path().endsWith("uml");
    }

    @Override
	protected EMFReachableObject doGetReachableObject(Reachable t) {
		return new UMLReachableObject(t);
	}

}

package org.polarsys.reqcycle.emf.uml.handlers;

import org.eclipse.emf.common.util.URI;
import org.polarsys.reqcycle.emf.handlers.EMFReachableObject;
import org.polarsys.reqcycle.emf.handlers.EMFURIHandler;
import org.polarsys.reqcycle.uri.model.Reachable;

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

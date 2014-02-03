package org.polarsys.reqcycle.emf.types;

import org.polarsys.reqcycle.emf.handlers.EMFURIHandler;
import org.polarsys.reqcycle.types.ITypeChecker;
import org.polarsys.reqcycle.uri.model.Reachable;

public class EMFTypeChecker implements ITypeChecker {

	@Override
	public boolean apply(Reachable reachable) {
		return new EMFURIHandler().handlesReachable(reachable);
	}

}

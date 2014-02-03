package org.eclipse.reqcycle.emf.types;

import org.eclipse.reqcycle.emf.handlers.EMFURIHandler;
import org.eclipse.reqcycle.types.ITypeChecker;
import org.eclipse.reqcycle.uri.model.Reachable;

public class EMFTypeChecker implements ITypeChecker {

	@Override
	public boolean apply(Reachable reachable) {
		return new EMFURIHandler().handlesReachable(reachable);
	}

}

package org.eclipse.reqcycle.types.impl;

import org.eclipse.reqcycle.types.ITypeChecker;
import org.eclipse.reqcycle.uri.model.Reachable;

public class AnyElementChecker implements ITypeChecker {

	@Override
	public boolean apply(Reachable reachable) {
		return true;
	}

}

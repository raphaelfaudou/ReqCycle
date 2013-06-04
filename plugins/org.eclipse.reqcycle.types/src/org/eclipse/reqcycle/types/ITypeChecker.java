package org.eclipse.reqcycle.types;

import org.eclipse.reqcycle.uri.model.Reachable;

public interface ITypeChecker {
	boolean apply(Reachable reachable);
}

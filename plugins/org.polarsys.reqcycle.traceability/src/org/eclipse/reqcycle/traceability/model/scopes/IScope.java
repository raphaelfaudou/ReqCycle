package org.eclipse.reqcycle.traceability.model.scopes;

import java.util.Iterator;

import org.eclipse.reqcycle.uri.model.Reachable;

public interface IScope {
	Iterator<Reachable> getReachables();
}

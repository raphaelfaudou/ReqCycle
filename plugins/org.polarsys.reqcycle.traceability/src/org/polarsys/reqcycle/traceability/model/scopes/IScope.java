package org.polarsys.reqcycle.traceability.model.scopes;

import java.util.Iterator;

import org.polarsys.reqcycle.uri.model.Reachable;

public interface IScope {
	Iterator<Reachable> getReachables();
}

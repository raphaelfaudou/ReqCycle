package org.polarsys.reqcycle.traceability.model;

import org.polarsys.reqcycle.traceability.model.scopes.IScope;
import org.polarsys.reqcycle.uri.model.Reachable;

public interface StopCondition {
	boolean apply(Pair<Link, Reachable> pair);

	public interface ScopedStopCondition extends StopCondition {
		IScope getScope();
	}
}

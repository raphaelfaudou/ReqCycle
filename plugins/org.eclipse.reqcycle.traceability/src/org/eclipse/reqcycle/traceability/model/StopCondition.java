package org.eclipse.reqcycle.traceability.model;

import org.eclipse.reqcycle.traceability.model.scopes.IScope;
import org.eclipse.reqcycle.uri.model.Reachable;

public interface StopCondition {
	boolean apply(Pair<Link, Reachable> pair);

	public interface ScopedStopCondition extends StopCondition {
		IScope getScope();
	}
}

package org.eclipse.reqcycle.traceability.predicates;

import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.traceability.model.Pair;
import org.eclipse.reqcycle.traceability.model.scopes.IScope;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.ziggurat.inject.ZigguratInject;

import com.google.common.base.Predicate;

public class TraceabilityPredicates {

	public static Predicate<Pair<Link, Reachable>> newIsInScopePredicate(
			IScope scope) {
		IsInScopePredicate p = new IsInScopePredicate(scope);
		ZigguratInject.inject(p);
		return p;
	}
}

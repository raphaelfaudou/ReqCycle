package org.eclipse.reqcycle.traceability.cache.hypergraph.predicates;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.traceability.model.Pair;
import org.eclipse.reqcycle.traceability.model.scopes.IScope;
import org.eclipse.reqcycle.uri.model.Reachable;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;

public class NotInScopePredicate implements Predicate<Pair<Link, Reachable>> {
	private IScope scope;
	private Set<Reachable> reachables = new HashSet<Reachable>();

	public NotInScopePredicate(IScope scope) {
		this.scope = scope;
		Iterators.addAll(reachables, scope.getReachables());
	}

	public boolean apply(Pair<Link, Reachable> pair) {
		return reachables.contains(pair.getSecond().trimFragment());
	}
}

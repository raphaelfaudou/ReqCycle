package org.polarsys.reqcycle.traceability.predicates;

import java.util.Iterator;

import org.polarsys.reqcycle.traceability.model.Link;
import org.polarsys.reqcycle.traceability.model.Pair;
import org.polarsys.reqcycle.traceability.model.scopes.IScope;
import org.polarsys.reqcycle.uri.functions.URIFunctions;
import org.polarsys.reqcycle.uri.model.Reachable;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;

public class IsInScopePredicate implements Predicate<Pair<Link, Reachable>> {

	private IScope scope;

	public IsInScopePredicate(IScope scope) {
		this.scope = scope;
	}

	@Override
	public boolean apply(Pair<Link, Reachable> arg0) {
		if (scope == null) {
			return true;
		}
		Function<Reachable, Reachable> function = URIFunctions
				.newTrimFragmentFunction();
		Iterator<Reachable> reachables = Iterators.concat(Iterators.transform(
				arg0.getFirst().getSources().iterator(), function), Iterators
				.transform(arg0.getFirst().getTargets().iterator(), function));
		return Sets.newHashSet(scope.getReachables()).containsAll(
				Sets.newHashSet(reachables));
	}
}

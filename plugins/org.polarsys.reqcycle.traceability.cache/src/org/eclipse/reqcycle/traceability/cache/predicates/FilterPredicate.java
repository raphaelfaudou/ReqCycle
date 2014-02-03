package org.eclipse.reqcycle.traceability.cache.predicates;

import org.eclipse.reqcycle.traceability.model.Filter;
import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.traceability.model.Pair;
import org.eclipse.reqcycle.uri.model.Reachable;

import com.google.common.base.Predicate;

public class FilterPredicate implements Predicate<Pair<Link, Reachable>> {
	private Filter filter;

	public FilterPredicate(Filter filter) {
		this.filter = filter;
	}

	public boolean apply(Pair<Link, Reachable> pair) {
		return filter.apply(pair);
	}
}

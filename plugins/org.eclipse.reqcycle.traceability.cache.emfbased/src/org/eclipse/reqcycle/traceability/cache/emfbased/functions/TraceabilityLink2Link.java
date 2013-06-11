package org.eclipse.reqcycle.traceability.cache.emfbased.functions;

import org.agesys.inject.AgesysInject;
import org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceabilityLink;
import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.uri.model.Reachable;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

public class TraceabilityLink2Link implements Function<TraceabilityLink, Link> {

	@Override
	public Link apply(TraceabilityLink arg0) {
		TraceableElement2Traceable traceableElement2Traceable = new TraceableElement2Traceable();
		AgesysInject.inject(traceableElement2Traceable);
		Iterable<Reachable> sources = Iterables.transform(arg0.getSources(),
				traceableElement2Traceable);
		Iterable<Reachable> targets = Iterables.transform(arg0.getTargets(),
				traceableElement2Traceable);
		Link link = new Link(arg0.getLabel(), sources, targets);
		return link;
	}

}

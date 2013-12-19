package org.eclipse.reqcycle.traceability.cache.emfbased.functions;

import org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceabilityLink;
import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.ziggurat.inject.ZigguratInject;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

public class TraceabilityLink2Link implements Function<TraceabilityLink, Link> {

	@Override
	public Link apply(TraceabilityLink arg0) {
		TraceableElement2Traceable traceableElement2Traceable = new TraceableElement2Traceable();
		ZigguratInject.inject(traceableElement2Traceable);
		Iterable<Reachable> sources = Iterables.transform(arg0.getSources(),
				traceableElement2Traceable);
		Iterable<Reachable> targets = Iterables.transform(arg0.getTargets(),
				traceableElement2Traceable);
		//-RFU- Link link = new Link(arg0.getLabel(), sources, targets);
		Link link = new Link(new Reachable ("RFU ID"), new TType("RFU",arg0.getLabel()), sources, targets);
		return link;
	}

}

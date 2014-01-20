package org.eclipse.reqcycle.traceability.cache.emfbased.functions;

import javax.inject.Inject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceabilityLink;
import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.uri.IReachableCreator;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.ziggurat.inject.ZigguratInject;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

public class TraceabilityLink2Link implements Function<TraceabilityLink, Link> {
	
	@Inject
	IReachableCreator creator;

	@Override
	public Link apply(TraceabilityLink aLink) {
		TraceableElement2Traceable traceableElement2Traceable = new TraceableElement2Traceable();
		ZigguratInject.inject(traceableElement2Traceable);
		Iterable<Reachable> sources = Iterables.transform(aLink.getSources(),
				traceableElement2Traceable);
		Iterable<Reachable> targets = Iterables.transform(aLink.getTargets(),
				traceableElement2Traceable);
		
		// RFa provide ID - to check
		//FIXME
		UUID uniqueID = UUID.randomUUID(); 
		URI uri = null;
		try {
			uri = new URI(aLink.getResource().getUri());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reachable r = creator.getReachable(uri);
		Link link = new Link(r,new TType(uniqueID.toString(),aLink.getLabel()), sources, targets);
		return link;
	}

}

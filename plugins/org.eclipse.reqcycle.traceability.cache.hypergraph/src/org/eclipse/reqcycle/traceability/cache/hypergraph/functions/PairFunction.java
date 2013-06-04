package org.eclipse.reqcycle.traceability.cache.hypergraph.functions;

import java.util.Collections;

import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGValueLink;
import org.hypergraphdb.HyperGraph;
import org.hypergraphdb.util.Pair;

import com.google.common.base.Function;

public class PairFunction
		implements
		Function<Pair<HGHandle, HGHandle>, org.eclipse.reqcycle.traceability.model.Pair<Link, Reachable>> {

	private HyperGraph graph;

	public PairFunction(HyperGraph graph) {
		this.graph = graph;
	}

	@Override
	public org.eclipse.reqcycle.traceability.model.Pair<Link, Reachable> apply(
			Pair<HGHandle, HGHandle> arg0) {
		HGValueLink l = (HGValueLink) graph.get(arg0.getFirst());
		Reachable source = graph.get(l.getTargetAt(0));
		Reachable target = graph.get(l.getTargetAt(1));
		Link link = new Link((String) l.getValue(),
				Collections.singleton(source), Collections.singleton(target));
		return new org.eclipse.reqcycle.traceability.model.Pair<Link, Reachable>(
				link, target);
	}

}

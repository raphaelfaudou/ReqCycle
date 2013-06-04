package org.eclipse.reqcycle.traceability.cache.hypergraph.functions;

import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine.DIRECTION;
import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.traceability.model.Pair;
import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.traceability.model.TraceabilityLink;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGValueLink;
import org.hypergraphdb.HyperGraph;

import com.google.common.base.Function;

public class Pair2Pair implements Function<Object, Pair<Link, Reachable>> {

	private HyperGraph graph;
	private DIRECTION direction;

	/**
	 * @param hgTraceabilityEngine
	 */
	public Pair2Pair(HyperGraph graph, DIRECTION direction) {
		this.graph = graph;
		this.direction = direction;
	}

	// TODO change
	public Pair<Link, Reachable> apply(Object o) {
		if (o instanceof org.hypergraphdb.util.Pair) {
			org.hypergraphdb.util.Pair pair = (org.hypergraphdb.util.Pair) o;
			HGValueLink link = (HGValueLink) graph.get((HGHandle) pair
					.getFirst());
			HGHandle handle = (HGHandle) pair.getSecond();
			Reachable linkTarget1 = (Reachable) graph.get(link.getTargetAt(1));
			Reachable linkTarget0 = graph.get(link.getTargetAt(0));
			Reachable second = graph.get(handle);
			if (direction == DIRECTION.DOWNWARD) {
				return new Pair<Link, Reachable>(new Link(TType.custom(
						TraceabilityLink.TRACE, (String) link.getValue()),
						linkTarget1, linkTarget0), linkTarget0);
			} else {
				return new Pair<Link, Reachable>(new Link(TType.custom(
						TraceabilityLink.TRACE, (String) link.getValue()),
						linkTarget0, linkTarget1), linkTarget1);
			}
		}
		return null;
	}
}
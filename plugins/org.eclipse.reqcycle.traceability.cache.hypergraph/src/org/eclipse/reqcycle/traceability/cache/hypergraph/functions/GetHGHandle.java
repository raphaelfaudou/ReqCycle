package org.eclipse.reqcycle.traceability.cache.hypergraph.functions;

import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGQuery.hg;
import org.hypergraphdb.HyperGraph;

import com.google.common.base.Function;

public class GetHGHandle implements Function<Object, HGHandle> {
	private HyperGraph graph;

	public GetHGHandle(HyperGraph graph) {
		this.graph = graph;
	}

	@Override
	public HGHandle apply(Object arg0) {
		return hg.assertAtom(graph, arg0);
	}
}

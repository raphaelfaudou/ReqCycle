package org.eclipse.reqcycle.traceability.cache.hypergraph.functions;

import org.eclipse.reqcycle.uri.model.Reachable;
import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGQuery.hg;
import org.hypergraphdb.HyperGraph;

import com.google.common.base.Function;

public class Object2HGHandle implements Function<Object, HGHandle> {
	private HyperGraph graph;

	public Object2HGHandle(HyperGraph graph) {
		this.graph = graph;
	}

	@Override
	public HGHandle apply(Object arg0) {
		HGHandle result = hg.assertAtom(graph, arg0);
		// the properties of the reachable are ignored for hashcode and
		// identification
		// if they are different it is important to update the element in db if
		// it is the case
		if (result != null && arg0 instanceof Reachable) {
			Reachable reachable = (Reachable) arg0;
			if (!((Reachable) graph.get(result)).getProperties().equals(
					reachable.getProperties())
					&& !reachable.getProperties().isEmpty()) {
				Reachable fromDB = graph.get(result);
				fromDB.getProperties().clear();
				fromDB.putAll(reachable.getProperties());
				graph.update(fromDB);
			}
		}
		return result;
	}
}

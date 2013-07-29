package org.eclipse.reqcycle.traceability.storage.blueprints.storage;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.traceability.storage.blueprints.graph.ISpecificGraphProvider;
import org.eclipse.reqcycle.uri.model.Reachable;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Vertex;

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.transform;

public class GraphUtils implements ISpecificGraphProvider.IBusinessOperations {
	private static final String KIND = "kind";
	// EDGES
	public static final String CHILDREN_EDGE = "children";
	public static final String TRACE_TARGET = "target";
	public static final String VERTEX_OUTGOING = "outgoing";

	public Vertex getVertex(Graph graph, Reachable reachable) {
		String id = reachable.toString();
		Vertex v = graph.getVertex(id);
		if (v == null) {
			v = graph.addVertex(id);
			for (String s : reachable.getProperties().keySet()) {
				String value = reachable.get(s);
				if (value != null) {
					v.setProperty(s, value);
				}
			}
		}
		return v;
	}

	public Vertex getVertex(Graph graph, String reachableUri) {
		Vertex v = graph.getVertex(reachableUri);
		return v;
	}

	public void addRelation(Graph graph, Vertex source, Vertex target,
			String relation) {
		graph.addEdge(null, source, target, relation);
	}

	public void addRelation(Graph graph, Reachable source, Reachable target,
			String relation) {
		addRelation(graph, getVertex(graph, source), getVertex(graph, target),
				relation);
	}

	public Vertex addTraceabilityRelation(Graph graph, Reachable source,
			Reachable target, TType relation) {
		Vertex traceability = graph.addVertex(null);
		traceability.setProperty(KIND, relation);
		graph.addEdge(null, getVertex(graph, source), traceability,
				VERTEX_OUTGOING);
		graph.addEdge(null, traceability, getVertex(graph, target),
				TRACE_TARGET);
		return traceability;
	}

	private String getLitteral(String relation) {
		return "\"" + relation + "\"";
	}

	public void addChildrenRelation(Graph graph, Vertex container,
			Vertex children) {
		addRelation(graph, container, children, CHILDREN_EDGE);
	}

	public Iterable<Vertex> getTraceability(Vertex v,
			final Direction graphDirection) {
		String label = graphDirection == Direction.IN ? TRACE_TARGET
				: VERTEX_OUTGOING;
		return Iterables.transform(v.getEdges(graphDirection, label),
				new Function<Edge, Vertex>() {
					public Vertex apply(Edge e) {
						return e.getVertex(invert(graphDirection));
					}

				});
	}

	private Direction invert(final Direction graphDirection) {
		return graphDirection == Direction.IN ? Direction.OUT : Direction.IN;
	}

	public Vertex getTraceabilityTarget(Vertex trac, Direction direction) {
		String label = direction == Direction.IN ? VERTEX_OUTGOING
				: TRACE_TARGET;
		return trac.getEdges(direction, label).iterator().next()
				.getVertex(invert(direction));
	}

	public TType getKind(Vertex trac) {
		return (TType) trac.getProperty(KIND);
	}

	public Iterable<Vertex> getTraceabilityIn(Graph graph, Reachable reachable) {
		List<Vertex> result = new LinkedList<Vertex>();
		Vertex vContainer = graph.getVertex(reachable.toString());
		if (vContainer != null) {
			for (Edge e : vContainer.getEdges(Direction.OUT, CHILDREN_EDGE)) {
				result.add(e.getVertex(Direction.IN));
			}
		}
		return result;
	}

	public void delete(Vertex v) {
		v.remove();
	}

	@Override
	public TType getTType(Vertex traceabilityvertex) {
		// TODO Auto-generated method stub
		return traceabilityvertex.getProperty(KIND);
	}

	@Override
	public Map<String, String> getProperties(Vertex v) {
		Map<String, String> map = new HashMap<String, String>();
		for (String s : v.getPropertyKeys()) {
			Object val = v.getProperty(s);
			if (val instanceof String) {
				String new_name = (String) val;
				map.put(s, new_name);
			}
		}
		return map;
	}

	@Override
	public Iterable<Vertex> getAllTraceabilityVertices(Graph graph) {
		Iterable<Edge> edges = graph.getEdges();
		Iterable<Edge> edgesFilterd = filter(edges, new Predicate<Edge>() {

			@Override
			public boolean apply(Edge arg0) {
				return TRACE_TARGET.equals(arg0.getLabel());
			}
		});
		return transform(edgesFilterd, new Function<Edge, Vertex>() {

			@Override
			public Vertex apply(Edge arg0) {
				return arg0.getVertex(Direction.IN);
			}
		});
	}

	@Override
	public Vertex getSourceFromTraceabilityVertex(Vertex arg0) {
		return arg0.getEdges(Direction.IN, VERTEX_OUTGOING).iterator().next()
				.getVertex(Direction.IN);
	}

	@Override
	public Vertex getTargetFromTraceabilityVertex(Vertex arg0) {
		return arg0.getEdges(Direction.OUT, TRACE_TARGET).iterator().next()
				.getVertex(Direction.OUT);
	}

	@Override
	public void removeUpwardRelationShip(Graph graph, TType kind,
			Reachable container, Reachable source, Reachable target) {
		Vertex vertex = getVertex(graph, source);
		if (vertex != null) {
			for (Iterator<Edge> i = vertex.getEdges(Direction.OUT,
					VERTEX_OUTGOING).iterator(); i.hasNext();) {
				Edge e = i.next();
				if (e.getVertex(Direction.OUT).equals(getVertex(graph, target))) {
					e.remove();
				}
			}
		}
	}

}

package org.eclipse.reqcycle.sesame.graph;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.traceability.storage.blueprints.graph.ISpecificGraphProvider;
import org.eclipse.reqcycle.traceability.utils.SerializationUtils;
import org.eclipse.reqcycle.uri.IReachableCreator;
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

public class SailBusinessOperations implements
		ISpecificGraphProvider.IBusinessOperations {
	private static final String PatternSplitter = "_=@=_";
	private static final String KIND = "http://kind";
	// EDGES
	public static final String CHILDREN_EDGE = "http://children";
	public static final String TRACE_TARGET = "http://target";
	public static final String TRACE_SOURCE = "http://source";
	public static final String VERTEX_OUTGOING = "http://outgoing";
	public static final String VERTEX_INCOMING = "http://incoming";
	public static final String EDGE_PROPERTIES = "http://properties";

	@Inject
	IReachableCreator creator;

	public Vertex getVertex(Graph graph, Reachable reachable) {
		String id = reachable.toString();
		Vertex v = graph.getVertex(id);
		removeProperties(graph, v);
		addProperties(graph, v, reachable);
		return v;
	}

	private void addProperties(Graph graph, Vertex v, Reachable reachable) {
		for (String key : reachable.getProperties().keySet()) {
			graph.addEdge(null, v, graph.addVertex(getVertexProperty(key,
					reachable.getProperties().get(key))), EDGE_PROPERTIES);
		}
	}

	private String getVertexProperty(String key, String value) {
		return getLitteral(key + PatternSplitter + value);
	}

	private void removeProperties(Graph graph, Vertex v) {
		Iterable<Edge> edges = Iterables.filter(graph.getEdges(),
				new Predicate<Edge>() {
					public boolean apply(Edge e) {
						return EDGE_PROPERTIES.equals(e.getId());
					}
				});
		for (Edge e : edges) {
			graph.removeVertex(e.getVertex(Direction.IN));
			graph.removeEdge(e);
		}
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
		// TODO change for sail
		// Vertex vKind = graph.addVertex(getLitteral(relation));
		// graph.addEdge(null, traceability, vKind, KIND);
		setKind(graph, relation, traceability);
		Vertex sourceVertex = getVertex(graph, source);
		Vertex targetVertex = getVertex(graph, target);

		setSource(graph, traceability, sourceVertex);
		setTarget(graph, traceability, targetVertex);
		return traceability;
	}

	/**
	 * Set link between source and traceability vertex
	 * 
	 * @param graph
	 * @param traceability
	 * @param sourceVertex
	 */
	private void setSource(Graph graph, Vertex traceability, Vertex sourceVertex) {
		doSet(graph, traceability, sourceVertex, TRACE_SOURCE, VERTEX_OUTGOING);
	}

	// add the correct edges between a vertex and its traceability vertex
	private void doSet(Graph graph, Vertex traceaVertex, Vertex node,
			String traceaVertex2Node, String node2TraceaVertex) {
		graph.addEdge(null, traceaVertex, node, traceaVertex2Node);
		graph.addEdge(null, node, traceaVertex, node2TraceaVertex);
	}

	private void setTarget(Graph graph, Vertex traceability, Vertex targetVertex) {
		doSet(graph, traceability, targetVertex, TRACE_TARGET, VERTEX_INCOMING);
	}

	@Override
	public void setKind(Graph graph, TType relation, Vertex traceability) {
		graph.addEdge(null, traceability,
				graph.addVertex(getLitteral(getRelationString(relation))), KIND);

	}

	private String getRelationString(TType relation) {
		return relation.getSemantic() == null ? "" : SerializationUtils
				.serialize(relation);
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
		Iterable<Edge> edges = trac.getEdges(Direction.OUT, KIND);
		if (edges.iterator().hasNext()) {
			Vertex theKind = edges.iterator().next().getVertex(Direction.IN);
			String id = (String) theKind.getId();
			id = id.replaceAll("\"", "");
			return SerializationUtils.deserialize(id);
		}
		return null;
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
	public Vertex getVertex(Graph graph, String reachableUri) {
		return graph.getVertex(reachableUri);
	}

	@Override
	public TType getTType(Vertex traceabilityvertex) {
		return getKind(traceabilityvertex);
	}

	@Override
	public Map<String, String> getProperties(Vertex v) {
		Map<String, String> map = new HashMap<String, String>();
		for (Edge e : v.getEdges(Direction.OUT, EDGE_PROPERTIES)) {
			Vertex p = e.getVertex(Direction.IN);
			String s = (String) p.getId();
			s = s.replaceAll("\"", "");
			String[] splitted = s.split(PatternSplitter);
			if (splitted.length >= 2) {
				StringBuffer buffer = new StringBuffer();
				for (int i = 1; i < splitted.length; i++) {
					buffer.append(splitted[i]);
					if (i != splitted.length - 1) {
						buffer.append(PatternSplitter);
					}
				}
				map.put(splitted[0], buffer.toString());
			}
		}
		return map;
	}

	public Vertex getContainerOfTraceability(Vertex v) {
		Iterable<Edge> edges = v.getEdges(Direction.IN, CHILDREN_EDGE);
		Iterator<Edge> iterator = edges.iterator();
		if (iterator.hasNext()) {
			Vertex parent = iterator.next().getVertex(Direction.OUT);
			return parent;
		}
		return null;
	}

	public Reachable getReachable(Vertex v) {
		return new Vertex2Reachable().apply(v);
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
				return arg0.getVertex(Direction.OUT);
			}
		});
	}

	private class Vertex2Reachable implements Function<Vertex, Reachable> {

		public Vertex2Reachable() {
		}

		@Override
		public Reachable apply(Vertex v) {
			if (v != null) {
				Reachable r;
				try {
					r = SailBusinessOperations.this.creator
							.getReachable(new URI((String) v.getId()));
					Map<String, String> properties = getProperties(v);
					for (String s : properties.keySet()) {
						r.put(s, (String) properties.get(s));
					}
					return r;
				} catch (URISyntaxException e) {
				}
			}
			return null;
		}
	}

	@Override
	public void removeUpwardRelationShip(Graph graph, TType kind,
			Reachable container, Reachable source, Reachable target) {
		Vertex sourceVertex = getVertex(graph, source);
		Vertex targetVertex = getVertex(graph, target);
		Set<Edge> toDelete = new HashSet<Edge>();
		delete(targetVertex, sourceVertex, VERTEX_OUTGOING, TRACE_TARGET,
				toDelete, true);
		delete(sourceVertex, targetVertex, VERTEX_INCOMING, TRACE_SOURCE,
				toDelete, true);
		for (Edge e : toDelete) {
			graph.removeEdge(e);
		}
	}

	private void delete(Vertex target, Vertex sourceVertex, String vertex2Trac,
			String trac2vertex, Set<Edge> toDelete, boolean flag) {
		Direction directionEdge = Direction.IN;
		Direction directionVertex = Direction.OUT;
		if (sourceVertex != null) {
			for (Edge e : sourceVertex.getEdges(directionVertex, vertex2Trac)) {
				Vertex tracVertex = e.getVertex(directionEdge);
				for (Edge e2 : tracVertex
						.getEdges(directionVertex, trac2vertex)) {
					Vertex targetVertex = e2.getVertex(directionEdge);
					if (targetVertex != null
							&& targetVertex.getId().equals(target.getId())) {
						toDelete.add(e);
						toDelete.add(e2);
					}
				}
			}

		}
	}

	@Override
	public Vertex getSourceFromTraceabilityVertex(Vertex arg0) {
		return arg0.getEdges(Direction.OUT, TRACE_SOURCE).iterator().next()
				.getVertex(Direction.IN);
	}

	@Override
	public Vertex getTargetFromTraceabilityVertex(Vertex arg0) {
		return getTargetEdgeFromTraceabilityVertex(arg0).iterator().next()
				.getVertex(Direction.IN);
	}

	private Iterable<Edge> getTargetEdgeFromTraceabilityVertex(Vertex arg0) {
		return arg0.getEdges(Direction.OUT, TRACE_TARGET);
	}

	@Override
	public void setTarget(Graph graph, Reachable newTarget,
			Vertex traceabilityVertex) {
		set(graph, TRACE_TARGET, VERTEX_INCOMING, traceabilityVertex,
				newTarget, Direction.OUT);
	}

	private void set(Graph graph, String stringForVertexTr,
			String stringForNode, Vertex traceabilityVertex,
			Reachable newTarget, Direction out) {
		Iterable<Edge> edges = traceabilityVertex.getEdges(Direction.OUT,
				stringForVertexTr);
		// only one by traceability
		Edge next = edges.iterator().next();
		Vertex otherVertex = next.getVertex(Direction.OUT);
		Set<Edge> toDelete = new HashSet<Edge>();
		toDelete.add(next);
		for (Edge e : otherVertex.getEdges(Direction.OUT, stringForNode)) {
			if (e.getVertex(Direction.IN).equals(traceabilityVertex)) {
				toDelete.add(e);
			}
		}
		// remove the edges
		for (Edge e : toDelete) {
			graph.removeEdge(e);
		}
		doSet(graph, traceabilityVertex, getVertex(graph, newTarget),
				stringForVertexTr, stringForNode);
	}

	@Override
	public void setSource(Graph graph, Reachable newSource, Vertex vTrac) {
		set(graph, TRACE_SOURCE, VERTEX_OUTGOING, vTrac, newSource,
				Direction.IN);
	}
}

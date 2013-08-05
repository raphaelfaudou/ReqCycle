package org.eclipse.reqcycle.traceability.storage.blueprints.storage;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine.DIRECTION;
import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.traceability.model.Pair;
import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.traceability.storage.ITraceabilityStorage;
import org.eclipse.reqcycle.traceability.storage.ITraceabilityStorageListener;
import org.eclipse.reqcycle.traceability.storage.blueprints.graph.ISpecificGraphProvider;
import org.eclipse.reqcycle.uri.IReachableCreator;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.ziggurat.inject.ZigguratInject;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.TransactionalGraph;
import com.tinkerpop.blueprints.Vertex;

import static com.google.common.collect.Iterables.transform;

public class GraphStorage implements ITraceabilityStorage {

	@Inject
	IReachableCreator creator;
	private Graph graph;
	private ISpecificGraphProvider.IBusinessOperations graphUtils;

	private Set<ITraceabilityStorageListener> listeners = new HashSet<ITraceabilityStorageListener>();

	public GraphStorage(Graph graph) {
		this(graph, null);
	}

	public GraphStorage(Graph graph,
			ISpecificGraphProvider.IBusinessOperations operations) {
		this.graph = graph;
		if (operations == null) {
			this.graphUtils = new GraphUtils();
			ZigguratInject.inject(this.graphUtils);
		} else {
			this.graphUtils = operations;
		}
	}

	@Override
	public void save() {
		if (graph instanceof TransactionalGraph) {
			for (ITraceabilityStorageListener l : listeners) {
				l.notifySave(this);
			}
			TransactionalGraph tGraph = (TransactionalGraph) graph;
			tGraph.commit();
		}
	}

	@Override
	public void newUpwardRelationShip(TType kind, Reachable tracea,
			Reachable container, Reachable source, Reachable... targets) {
		// FIXME improve the multi target
		// in general cases only one target is provided.
		// currently several traceability links are created and the first one is
		// returned
		// in the future, create one traceability link for several targets
		String uri = null;
		for (Reachable target : targets) {
			Vertex vertex = graphUtils.addTraceabilityRelation(graph, source,
					target, kind);
			if (uri == null) {
				uri = (String) vertex.getId();
			}
			graphUtils.addChildrenRelation(graph,
					graphUtils.getVertex(graph, container), vertex);
		}
		for (ITraceabilityStorageListener l : listeners) {
			l.notifyNewUpwardRelationShip(this, kind, tracea, container,
					source, targets);
		}
	}

	@Override
	public Reachable getReachable(String uri) {
		Vertex v = graphUtils.getVertex(graph, uri);
		if (v != null) {
			Reachable r;
			try {
				r = creator.getReachable(new URI(uri));
				Map<String, String> properties = graphUtils.getProperties(v);
				for (String s : properties.keySet()) {
					r.put(s, (String) properties.get(s));
				}
				return r;
			} catch (URISyntaxException e) {
			}
		}
		return null;
	}

	@Override
	public Iterable<Pair<Link, Reachable>> getTraceability(Reachable r,
			DIRECTION direction) {
		Direction graphDirection = null;
		if (direction == DIRECTION.DOWNWARD) {
			graphDirection = Direction.IN;
		} else {
			graphDirection = Direction.OUT;
		}
		Vertex v = graph.getVertex(r.toString());
		if (v == null) {
			return Lists.newArrayList();
		}
		Iterable<Vertex> tracVertexes = graphUtils.getTraceability(v,
				graphDirection);
		List<Pair<Link, Reachable>> result = new LinkedList<Pair<Link, Reachable>>();
		for (Vertex trac : tracVertexes) {
			Vertex target = graphUtils.getTraceabilityTarget(trac,
					graphDirection);
			Reachable rTarget = getReachable((String) target.getId());
			Link link = new Link((String) trac.getId(),
					graphUtils.getKind(trac), r, rTarget);
			result.add(new Pair<Link, Reachable>(link, rTarget));
		}
		return result;
	}

	@Override
	public void dispose() {
		for (ITraceabilityStorageListener l : listeners) {
			l.notifyDispose(this);
		}
		graph.shutdown();
		listeners.clear();
	}

	@Override
	public void startTransaction() {
	}

	@Override
	public void commit() {
		if (graph instanceof TransactionalGraph) {
			for (ITraceabilityStorageListener l : listeners) {
				l.notifyCommit(this);
			}
			TransactionalGraph tGraph = (TransactionalGraph) graph;
			tGraph.commit();
		}
	}

	@Override
	public void rollback() {
		if (graph instanceof TransactionalGraph) {
			TransactionalGraph tGraph = (TransactionalGraph) graph;
			tGraph.rollback();
		}

	}

	@Override
	public void removeTraceabilityLinksContainedIn(Reachable reachable) {
		Iterable<Vertex> traceabilities = graphUtils.getTraceabilityIn(graph,
				reachable);
		for (Vertex v : traceabilities) {
			graphUtils.delete(v);
			for (ITraceabilityStorageListener l : listeners) {
				Reachable source;
				try {
					source = creator.getReachable(new URI((String) graphUtils
							.getSourceFromTraceabilityVertex(v).getId()));
					Reachable target = creator
							.getReachable(new URI((String) graphUtils
									.getTargetFromTraceabilityVertex(v).getId()));
					l.notifyTraceabilityLinksRemoved(this, reachable, source,
							target, graphUtils.getKind(v));
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public Iterable<Pair<Link, Reachable>> getAllTraceability(
			final DIRECTION direction) {
		Iterable<Vertex> allVertices = graphUtils
				.getAllTraceabilityVertices(graph);
		return transform(allVertices,
				new Function<Vertex, Pair<Link, Reachable>>() {

					@Override
					public Pair<Link, Reachable> apply(Vertex arg0) {
						Vertex source = graphUtils
								.getSourceFromTraceabilityVertex(arg0);
						Vertex target = graphUtils
								.getTargetFromTraceabilityVertex(arg0);
						if (direction == DIRECTION.DOWNWARD) {
							Vertex sourceTmp = source;
							source = target;
							target = sourceTmp;
						}
						Link link = new Link((String) arg0.getId(), graphUtils
								.getKind(arg0), getReachable((String) source
								.getId()),
								getReachable((String) target.getId()));
						return new Pair<Link, Reachable>(link, link
								.getTargets().iterator().next());
					}
				});

	}

	@Override
	public void removeUpwardRelationShip(TType kind, Reachable container,
			Reachable source, Reachable... targets) {
		for (Reachable t : targets) {
			graphUtils.removeUpwardRelationShip(graph, kind, container, source,
					t);
		}
	}

	@Override
	public void updateRelationShip(Link oldLink, Link newLink,
			DIRECTION direction) {
		Reachable source = oldLink.getSources().iterator().next();
		Reachable target = oldLink.getTargets().iterator().next();
		Set<Vertex> toDelete = new HashSet<Vertex>();
		Vertex vSource = graphUtils.getVertex(graph, source);
		if (vSource == null) {
			return;
		}
		Iterable<Vertex> traceability = graphUtils.getTraceability(vSource,
				Direction.OUT);
		for (Vertex vTrac : traceability) {
			Vertex vTarget = graphUtils.getTraceabilityTarget(vTrac,
					Direction.OUT);
			if (vTarget != null) {
				if (getReachable((String) vTarget.getId()).equals(target)) {
					// target is the same check for kind
					TType aKind = graphUtils.getTType(vTrac);
					if (aKind != null && aKind.equals(oldLink.getKind())) {
						handleTraceability(oldLink, source, target, newLink,
								vSource, vTarget, vTrac, direction);
						for (ITraceabilityStorageListener l : listeners) {
							l.notifyUpdateRelationShip(this, oldLink, newLink,
									direction);
						}
						break;
					}
				}
			}
		}
	}

	protected void handleTraceability(Link oldLink, Reachable oldSource,
			Reachable oldTarget, Link newLink, Vertex vSource, Vertex vTarget,
			Vertex vTrac, DIRECTION direction) {
		Reachable newSource = newLink.getSources().iterator().next();
		Reachable newTarget = newLink.getTargets().iterator().next();
		if (!newSource.equals(oldSource)) {
			if (direction == DIRECTION.DOWNWARD) {
				handleTargetChanged(oldTarget, newTarget, vTrac);
			} else {
				handleSourceChanged(oldSource, newSource, vTrac);
			}
		}
		if (!newTarget.equals(oldTarget)) {
			if (direction == DIRECTION.DOWNWARD) {
				handleTargetChanged(oldSource, newSource, vTrac);
			} else {
				handleSourceChanged(oldTarget, newTarget, vTrac);
			}
		}
		TType newType = newLink.getKind();
		if (!newType.equals(oldLink.getKind())) {
			handleKindChanged(oldLink.getKind(), newType, vTrac);
		}

	}

	protected void handleKindChanged(TType kind, TType newType, Vertex vTrac) {
		graphUtils.setKind(graph, newType, vTrac);
	}

	protected void handleTargetChanged(Reachable oldTarget,
			Reachable newTarget, Vertex vTrac) {
		graphUtils.setTarget(graph, newTarget, vTrac);
	}

	private void handleSourceChanged(Reachable oldSource, Reachable newSource,
			Vertex vTrac) {
		graphUtils.setSource(graph, newSource, vTrac);
	}

	@Override
	public void addUpdateRemoveProperty(Reachable reachable,
			String propertyName, String propertyValue) {
		String id = reachable.toString();
		Vertex vertex = graphUtils.getVertex(graph, id);
		if (vertex != null) {
			graphUtils.setProperty(graph, vertex, propertyName, propertyValue);
		}
	}

	@Override
	public String getProperty(Reachable reachable, String propertyName) {
		String id = reachable.toString();
		Vertex vertex = graphUtils.getVertex(graph, id);
		if (vertex != null) {
			Map<String, String> p = graphUtils.getProperties(vertex);
			if (p != null) {
				return p.get(propertyName);
			}
		}
		return null;
	}

	@Override
	public void addTraceabilityStorageListener(
			ITraceabilityStorageListener listener) {

	}

	@Override
	public void removeTraceabilityStorageListener(
			ITraceabilityStorageListener listener) {

	}
}

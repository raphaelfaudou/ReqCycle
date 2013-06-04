package org.eclipse.reqcycle.traceability.cache.hypergraph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.reqcycle.core.ILogger;
import org.eclipse.reqcycle.traceability.cache.AbstractCachedTraceabilityEngine;
import org.eclipse.reqcycle.traceability.cache.Activator;
import org.eclipse.reqcycle.traceability.cache.hypergraph.functions.GetHGHandle;
import org.eclipse.reqcycle.traceability.cache.hypergraph.functions.Object2HGHandle;
import org.eclipse.reqcycle.traceability.cache.hypergraph.functions.Pair2Pair;
import org.eclipse.reqcycle.traceability.cache.hypergraph.pickers.PrecedentMapPicker;
import org.eclipse.reqcycle.traceability.cache.hypergraph.utils.GraphUtils;
import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.traceability.model.Pair;
import org.eclipse.reqcycle.traceability.model.StopCondition;
import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.traceability.model.scopes.ResourceScope;
import org.eclipse.reqcycle.uri.exceptions.IReachableHandlerException;
import org.eclipse.reqcycle.uri.model.IReachableHandler;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.reqcycle.uri.model.ReachableObject;
import org.hypergraphdb.HGConfiguration;
import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGQuery.hg;
import org.hypergraphdb.HGValueLink;
import org.hypergraphdb.HyperGraph;
import org.hypergraphdb.IncidenceSet;
import org.hypergraphdb.algorithms.DefaultALGenerator;
import org.hypergraphdb.algorithms.HGBreadthFirstTraversal;
import org.hypergraphdb.indexing.ByPartIndexer;
import org.hypergraphdb.query.AnyAtomCondition;
import org.hypergraphdb.storage.bje.BJEConfig;
import org.topcased.iterators.factories.IteratorFactory;
import org.topcased.iterators.pickers.IPicker;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

@Singleton
public class HGTraceabilityEngine extends AbstractCachedTraceabilityEngine {

	private static final String LINK_RESOURCE_TO_CHILD = "linkResourceToChild";
	private static final String REVISION = "HG_REVISION_INFO";
	HyperGraph graph;
	@Inject
	ILogger logger;

	public HGTraceabilityEngine() {
		super();
	}

	@PostConstruct
	public void postConstruct() {
		this.graph = getHGFromProject(getCachePath());
	}

	public HyperGraph getHGFromProject(String fullpath) {
		if (logger.isDebug(Activator.OPTIONS_DEBUG, Activator.getDefault())) {
			logger.trace("HyperGraph Initialization");
		}
		HyperGraph graph = new HyperGraph(fullpath);
		HGConfiguration conf = graph.getConfig();
		// to support model elements in this plugin
		conf.setClassLoader(org.eclipse.reqcycle.traceability.cache.hypergraph.Activator.class
				.getClassLoader());

		// Change the storage cache from the 20MB default to 80MB
		BJEConfig bdbConfig = (BJEConfig) conf.getStoreImplementation()
				.getConfiguration();

		// path and fragments are indexed
		HGHandle userType = graph.getTypeSystem()
				.getTypeHandle(Reachable.class);
		ByPartIndexer fragmentIndexer = new ByPartIndexer(userType,
				new String[] { "fragment" });
		if (!graph.getIndexManager().isRegistered(fragmentIndexer)) {
			graph.getIndexManager().register(fragmentIndexer);
		}
		ByPartIndexer pathIndexer = new ByPartIndexer(userType,
				new String[] { "path" });
		if (!graph.getIndexManager().isRegistered(pathIndexer)) {
			graph.getIndexManager().register(pathIndexer);
		}

		if (logger.isDebug(Activator.OPTIONS_DEBUG, Activator.getDefault())) {
			logger.trace(String.format("HyperGraph Initialized : %s", fullpath));
		}

		return graph;
	}

	@Override
	protected void environmentClosed() {
		if (graph != null) {
			boolean debug = logger.isDebug(Activator.OPTIONS_DEBUG,
					Activator.getDefault());
			if (debug) {
				logger.trace("Graph Closing");
			}
			graph.close();
			if (debug) {
				logger.trace("Graph Closed");
			}
		}
	}

	@Override
	protected boolean isCacheOk(Reachable traceable) {
		String uri = ResourceScope.getURIPath(traceable.trimFragment());
		if (uri == null) {
			// TODO error management
		}
		Reachable trimmedFragment = traceable.trimFragment();
		HGHandle handle = hg.assertAtom(graph, trimmedFragment);
		if (handle != null) {
			try {
				IReachableHandler handler = manager
						.getHandlerFromReachable(traceable);
				ReachableObject object = handler.getFromReachable(traceable);
				if (object != null) {
					// NULL means the object can not identify its revision
					// so the cache must be computed each time
					String revisionIdentification = object
							.getRevisionIdentification();
					if (revisionIdentification != null) {
						return revisionIdentification.equals(((Reachable) graph
								.get(handle)).get(REVISION));
					}
				}
			} catch (IReachableHandlerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	protected void build(Reachable traceable) {
		// force the save of the resource if no traceability found
		super.build(traceable);
	}

	@Override
	public void newUpwardRelation(Reachable container, Reachable aSource,
			List<Reachable> targets, TType kind) {
		Reachable containerOfTraceability = handleResource(container);
		List<Reachable> sources = new LinkedList<Reachable>();
		if (aSource != null) {
			// handle traceability link
			List<HGHandle> handles = new LinkedList<HGHandle>();
			Object2HGHandle function = new Object2HGHandle(graph);
			HGHandle source = function.apply(aSource);
			handles.add(source);
			handles.addAll(Lists.newArrayList(Iterables.filter(
					Iterables.transform(targets, function),
					Predicates.notNull())));
			HGValueLink link = new HGValueLink(kind.getSemantic(),
					handles.toArray(new HGHandle[handles.size()]));
			HGHandle linkHandle = graph.add(link);

			// a relation is created between the resource and the link to
			// make
			// possible the remove operation
			HGValueLink pl = new HGValueLink(LINK_RESOURCE_TO_CHILD,
					graph.getHandle(containerOfTraceability), linkHandle);
			graph.add(pl);

			// handle resource to traceable link
			addLinkToResourceLink(sources);
			addLinkToResourceLink(targets);
		}
	}

	public void addLinkToResourceLink(List<Reachable> reachables) {
		for (Reachable r : reachables) {
			Object2HGHandle func = new Object2HGHandle(graph);
			HGHandle handle = func.apply(r);
			HGHandle resourceHandle = func.apply(r.trimFragment());
			if (!isLinkExist(handle, LINK_RESOURCE_TO_CHILD)) {
				HGValueLink linkFromResource = new HGValueLink(
						LINK_RESOURCE_TO_CHILD, resourceHandle, handle);
				graph.add(linkFromResource);
			}
		}
	}

	public boolean isLinkExist(HGHandle source, Object linkType) {
		IncidenceSet set = graph.getIncidenceSet(source);
		for (Object o : set) {
			if (o instanceof HGHandle) {
				HGHandle handle = (HGHandle) o;
				if (graph.get(handle) instanceof HGValueLink) {
					HGValueLink link = (HGValueLink) graph.get(handle);
					if (Objects.equal(linkType, link.getValue())) {
						return true;
					}
				}
			}
		}
		return false;

	}

	public void assertAtoms(Iterable<Reachable> traceables) {
		for (Reachable t : traceables) {
			hg.assertAtom(graph, t);
		}
	}

	protected Reachable getResource(Reachable traceable) {
		HGHandle handle = getResourceHandle(traceable);
		if (handle != null) {
			return graph.get(handle);
		}
		return null;
	}

	public HGHandle getResourceHandle(Reachable traceable) {
		HGHandle handle = graph.findOne(hg.eq(traceable));
		return handle;
	}

	protected Reachable handleResource(Reachable container) {
		Reachable traceable = container.trimFragment();
		Reachable handle = getResource(traceable);
		if (handle == null) {
			handle = traceable;
			IReachableHandler uriHandler = null;
			try {
				uriHandler = manager.getHandlerFromReachable(traceable);
				ReachableObject object = uriHandler.getFromReachable(traceable);
				if (object != null) {
					handle.put(REVISION, object.getRevisionIdentification());
					graph.add(handle);
				}
			} catch (IReachableHandlerException e) {
				// TODO ERROR management ?
				e.printStackTrace();
			}
		}
		return handle;
	}

	@Override
	protected void tagDeletedRelationShips(Iterable<Link> linksToTag) {

	}

	@Override
	public Iterable<Link> getLinksForTraceable(Reachable traceable) {
		return Lists.newArrayList();
	}

	@Override
	protected Iterator<Pair<Link, Reachable>> doGetOneLevelTraceability(
			Reachable source, DIRECTION direction,
			Predicate<Pair<Link, Reachable>> scope) {
		GetHGHandle function = new GetHGHandle(graph);
		HGHandle sourceHandle = function.apply(source);
		if (sourceHandle != null) {
			DefaultALGenerator generator = getTraversal(direction);
			return Lists.newArrayList(
					Iterators.filter(Iterators.transform(generator
							.generate(sourceHandle), new Pair2Pair(graph,
							direction)), scope)).iterator();
		}
		return Iterators.emptyIterator();
	}

	@Override
	protected Iterator<Pair<Link, Reachable>> doGetTraceability(
			Reachable source, DIRECTION direction,
			Predicate<Pair<Link, Reachable>> scope) {
		GetHGHandle function = new GetHGHandle(graph);
		HGHandle sourceHandle = function.apply(source);
		if (sourceHandle != null) {
			HGBreadthFirstTraversal traversal = new HGBreadthFirstTraversal(
					sourceHandle, getTraversal(direction));
			return Lists.newArrayList(
					Iterators.filter(Iterators.transform(traversal,
							new Pair2Pair(graph, direction)), scope))
					.iterator();
		}
		return Iterators.emptyIterator();
	}

	@Override
	protected Iterator<Pair<Link, Reachable>> doGetTraceability(
			Reachable source, StopCondition target, DIRECTION direction,
			Predicate<Pair<Link, Reachable>> scope) {
		GetHGHandle function = new GetHGHandle(graph);
		HGHandle sourceHandle = function.apply(source);
		if (sourceHandle != null && target != null) {
			DefaultALGenerator generator = getTraversal(direction);
			Map<HGHandle, org.hypergraphdb.util.Pair<HGHandle, HGHandle>> map = new HashMap<HGHandle, org.hypergraphdb.util.Pair<HGHandle, HGHandle>>();
			HGHandle result = GraphUtils.dijkstra(sourceHandle,
					new StopPredicate(target), generator, map, null, null);
			if (result != null) {
				Iterable<IPicker> pickers = Arrays
						.asList(new IPicker[] { new PrecedentMapPicker(map) });
				IteratorFactory f = new IteratorFactory(pickers);
				f.activateWidthWisdom();
				Iterable<Object> iterable = f.createIterable(result);
				return Lists.reverse(
						Lists.newArrayList(Iterators.filter(Iterators
								.transform(iterable.iterator(), new Pair2Pair(
										graph, direction)), Predicates.and(
								Predicates.notNull(), scope)))).iterator();

			}
		}
		return Iterators.emptyIterator();
	}

	public DefaultALGenerator getTraversal(DIRECTION direction) {
		return new DefaultALGenerator(graph, hg.not(hg
				.eq(LINK_RESOURCE_TO_CHILD)), new AnyAtomCondition(),
				direction == DIRECTION.DOWNWARD, direction == DIRECTION.UPWARD,
				false);
	}

	@Override
	protected void removeEntriesFor(Reachable traceable) {
		// TODO don t delete but tag
		Reachable resource = getResource(traceable);
		if (resource != null) {
			HGBreadthFirstTraversal traversal = new HGBreadthFirstTraversal(
					graph.getHandle(resource), new DefaultALGenerator(graph,
							hg.eq(LINK_RESOURCE_TO_CHILD),
							new AnyAtomCondition(), true, true, true));
			while (traversal.hasNext()) {
				org.hypergraphdb.util.Pair<HGHandle, HGHandle> next = traversal
						.next();
				if (graph.get(next.getSecond()) instanceof HGValueLink) {
					graph.remove(next.getSecond(), false);
				}
			}
			graph.remove(getResourceHandle(traceable));
		}
	}

	@Override
	public void endBuild(Reachable reachable) {
		super.endBuild(reachable);
		Reachable resource = getResource(reachable);
		if (resource != null) {
			HGHandle handleResource = graph.getHandle(resource);
			HGBreadthFirstTraversal traversal = new HGBreadthFirstTraversal(
					handleResource, new DefaultALGenerator(graph,
							hg.eq(LINK_RESOURCE_TO_CHILD),
							new AnyAtomCondition(), true, true, true));
			while (traversal.hasNext()) {
				org.hypergraphdb.util.Pair<HGHandle, HGHandle> next = traversal
						.next();
				// one incidence set means there is only LINK_RESOURCE_TO_CHILD
				if (!(graph.get(next.getSecond()) instanceof HGValueLink)
						&& graph.getIncidenceSet(next.getSecond()).size() <= 1) {
					graph.remove(next.getSecond(), false);
				}
			}
			// the resource is kept to keep build revision information
		}
	}

	@Override
	public void startBuild(Reachable reachable) {
		super.startBuild(reachable);
		handleResource(reachable);
	}

	private class StopPredicate implements Predicate<HGHandle> {

		private StopCondition condition;

		public StopPredicate(StopCondition condition) {
			this.condition = condition;
		}

		@Override
		public boolean apply(HGHandle arg0) {
			Reachable r = graph.get(arg0);
			return condition.apply(r);
		}

	}

}

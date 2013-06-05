package org.eclipse.reqcycle.traceability.cache.storagebased.engine;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.reqcycle.core.ILogger;
import org.eclipse.reqcycle.traceability.cache.AbstractCachedTraceabilityEngine;
import org.eclipse.reqcycle.traceability.cache.Activator;
import org.eclipse.reqcycle.traceability.cache.storagebased.engine.pickers.GetTraceabilityPicker;
import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.traceability.model.Pair;
import org.eclipse.reqcycle.traceability.model.StopCondition;
import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.traceability.model.scopes.ResourceScope;
import org.eclipse.reqcycle.traceability.storage.IStorageProvider;
import org.eclipse.reqcycle.traceability.storage.ITraceabilityStorage;
import org.eclipse.reqcycle.uri.exceptions.IReachableHandlerException;
import org.eclipse.reqcycle.uri.model.IReachableHandler;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.reqcycle.uri.model.ReachableObject;
import org.eclipse.ziggurat.inject.ZigguratInject;
import org.topcased.iterators.exceptions.PickerExecutionException;
import org.topcased.iterators.factories.IteratorFactory;
import org.topcased.iterators.pickers.IPicker;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

@Singleton
public class StorageBasedTraceabilityEngine extends
		AbstractCachedTraceabilityEngine {

	public static final String REVISION = "revisionProperty";

	@Inject
	ILogger logger;
	/**
	 * Force the {@link IStorageProvider} to be the Jena one
	 */
	@Inject
	IStorageProvider storageEngine;

	ITraceabilityStorage storage;

	private boolean isDebugging;

	@Override
	protected void environmentClosed() {
		if (isDebugging) {
			logger.trace("Storage is closing");
		}
		storage.save();
		storage.dispose();
		if (isDebugging) {
			logger.trace("Storage closed, saved : " + getCachePath());
		}
	}

	@PostConstruct
	public void postConstruct() {
		isDebugging = logger.isDebug(Activator.OPTIONS_DEBUG,
				Activator.getDefault());
		if (isDebugging) {
			logger.trace("Storage Initialization");
		}
		storage = storageEngine.getStorage(getCachePath());
		if (isDebugging) {
			logger.trace("Storage is initialized");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Iterator<Pair<Link, Reachable>> doGetTraceability(
			Reachable source, DIRECTION direction,
			Predicate<Pair<Link, Reachable>> scope) {
		IPicker picker = new GetTraceabilityPicker(direction, storage, scope);
		ZigguratInject.inject(picker);
		IPicker[] pickers = new IPicker[] { picker };
		IteratorFactory factory = new IteratorFactory(Arrays.asList(pickers));
		factory.activateDepthWisdom();
		factory.activateRedundancyAwareness();
		Iterator<Object> iterator = factory.createIterable(source).iterator();
		iterator.next();
		List<Pair<Link, Reachable>> list = Lists.newArrayList(Iterators
				.transform(iterator,
						new Function<Object, Pair<Link, Reachable>>() {
							public Pair<Link, Reachable> apply(Object o) {
								return (Pair<Link, Reachable>) o;
							}
						}));
		return list.iterator();
	}

	@Override
	protected Iterator<Pair<Link, Reachable>> doGetOneLevelTraceability(
			Reachable source, DIRECTION direction,
			Predicate<Pair<Link, Reachable>> scope) {
		IPicker picker = new GetTraceabilityPicker(direction, storage, scope);
		ZigguratInject.inject(picker);
		Iterable<?> nexts;
		try {
			nexts = picker.getNexts(source);
			Iterator<Pair<Link, Reachable>> iterator = Iterators.transform(
					nexts.iterator(),
					new Function<Object, Pair<Link, Reachable>>() {
						public Pair<Link, Reachable> apply(Object o) {
							return (Pair<Link, Reachable>) o;
						}
					}), TraceabilityPredicates;
			return iterator;
		} catch (PickerExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Iterators.emptyIterator();
	}

	@Override
	protected Iterator<Pair<Link, Reachable>> doGetTraceability(
			Reachable source, StopCondition condition, DIRECTION direction,
			Predicate<Pair<Link, Reachable>> scope) {
		ArrayDeque<Pair<Link, Reachable>> result = new ArrayDeque<Pair<Link, Reachable>>();
		ArrayDeque<Pair<Link, Reachable>> current = new ArrayDeque<Pair<Link, Reachable>>();
		if (source != null && condition != null) {
			IPicker picker = new GetTraceabilityPicker(direction, storage,
					scope);
			ZigguratInject.inject(picker);
			Iterable<IPicker> pickers = Arrays.asList(new IPicker[] { picker });
			IteratorFactory f = new IteratorFactory(pickers);
			f.activateWidthWisdom();
			f.activateRedundancyAwareness();
			Iterable<Object> iterable = f.createIterable(source);
			Iterator<Object> i = iterable.iterator();
			if (checkPath((Reachable) i.next(), condition, i, result, current)) {
				result.addAll(current);
			}

		}
		return result.iterator();
	}

	private boolean checkPath(Reachable s, StopCondition cond,
			Iterator<Object> i, ArrayDeque<Pair<Link, Reachable>> result,
			ArrayDeque<Pair<Link, Reachable>> current) {
		boolean found = false;
		Reachable source = s;

		while (i.hasNext()) {
			Object o = i.next();
			if (o instanceof Pair) {
				Pair<Link, Reachable> pair = (Pair<Link, Reachable>) o;
				if (cond.apply(pair.getSecond())) {
					current.add(pair);
					found = true;
					break;
				} else if (!(pair.getFirst().getSources().contains(source))) {
					if (checkPath(pair.getFirst().getSources().iterator()
							.next(), cond, i, result, current)) {
						result.addAll(current);
					}
				} else {
					current.add(pair);
					if (cond.apply(pair.getSecond())) {
						found = true;
						break;
					}
				}
			}
		}
		return found;

	}

	@Override
	protected void removeEntriesFor(Reachable reachable) {
		storage.removeTraceabilityLinksContainedIn(reachable);
	}

	@Override
	protected void tagDeletedRelationShips(Iterable<Link> linksToTag) {

	}

	@Override
	public Iterable<Link> getLinksForTraceable(Reachable reachable) {
		return null;
	}

	@Override
	protected boolean isCacheOk(Reachable reachable) {
		String uri = ResourceScope.getURIPath(reachable.trimFragment());
		if (uri == null) {
			// TODO error management
		}
		Reachable trimmedFragment = reachable.trimFragment();
		Reachable fromStorage = storage
				.getReachable(trimmedFragment.toString());
		if (fromStorage != null) {
			try {
				IReachableHandler handler = manager
						.getHandlerFromReachable(reachable);
				ReachableObject object = handler.getFromReachable(reachable);
				if (object != null) {
					// NULL means the object can not identify its revision
					// so the cache must be computed each time
					String revisionIdentification = object
							.getRevisionIdentification();
					if (revisionIdentification != null) {
						return revisionIdentification.equals(fromStorage
								.get(REVISION));
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
	public void newUpwardRelation(Reachable container, Reachable source,
			List<Reachable> targets, TType tType) {
		handleRevision(container);
		storage.newUpwardRelationShip(tType, container, source,
				targets.toArray(new Reachable[targets.size()]));
	}

	private void handleRevision(Reachable container) {
		if (container.get(OPTION_CHECK_CACHE) == null) {
			IReachableHandler uriHandler = null;
			try {
				uriHandler = manager.getHandlerFromReachable(container);
				ReachableObject object = uriHandler.getFromReachable(container);
				if (object != null) {
					container.put(REVISION, object.getRevisionIdentification());
				}
			} catch (IReachableHandlerException e) {
				// TODO ERROR management ?
				e.printStackTrace();
			}
		}
	}

	@Override
	public void startBuild(Reachable reachable) {
		storage.startTransaction();
		super.startBuild(reachable);
	}

	@Override
	public void endBuild(Reachable reachable) {
		super.endBuild(reachable);
		storage.commit();
	}

	@Override
	public void errorOccurs(Reachable reachable, Throwable t) {
		super.errorOccurs(reachable, t);
		storage.rollback();
	}
}

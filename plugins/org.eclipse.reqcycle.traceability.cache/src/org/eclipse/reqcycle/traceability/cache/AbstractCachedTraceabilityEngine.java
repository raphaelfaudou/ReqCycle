package org.eclipse.reqcycle.traceability.cache;

import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.eclipse.reqcycle.core.ILogger;
import org.eclipse.reqcycle.traceability.builder.IBuildingTraceabilityEngine;
import org.eclipse.reqcycle.traceability.builder.ITraceabilityBuilder;
import org.eclipse.reqcycle.traceability.builder.exceptions.BuilderException;
import org.eclipse.reqcycle.traceability.cache.predicates.FilterPredicate;
import org.eclipse.reqcycle.traceability.engine.Request;
import org.eclipse.reqcycle.traceability.engine.Request.Couple;
import org.eclipse.reqcycle.traceability.engine.Request.DEPTH;
import org.eclipse.reqcycle.traceability.exceptions.EngineException;
import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.traceability.model.Pair;
import org.eclipse.reqcycle.traceability.model.StopCondition;
import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.traceability.model.scopes.CompositeScope;
import org.eclipse.reqcycle.traceability.model.scopes.IScope;
import org.eclipse.reqcycle.traceability.predicates.TraceabilityPredicates;
import org.eclipse.reqcycle.uri.IReachableManager;
import org.eclipse.reqcycle.uri.functions.URIFunctions;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchListener;
import org.eclipse.ui.PlatformUI;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public abstract class AbstractCachedTraceabilityEngine implements
		IBuildingTraceabilityEngine {
	private String cachePath = determineDataBasePath();
	public static String HIDDEN_PROJET_NAME = "reqCycleHiddenProject";
	@Inject
	ITraceabilityBuilder builder;
	@Inject
	protected IReachableManager manager;
	@Inject
	ILogger logger;

	public AbstractCachedTraceabilityEngine() {
		PlatformUI.getWorkbench().addWorkbenchListener(
				new IWorkbenchListener() {

					@Override
					public boolean preShutdown(IWorkbench workbench,
							boolean forced) {
						return true;
					}

					@Override
					public void postShutdown(IWorkbench workbench) {
						environmentClosed();
					}
				});
	}

	/**
	 * This method returns a database path, in case of non Eclipse usage a
	 * temporary folder is created
	 * 
	 * @nu
	 * @return
	 */
	private String determineDataBasePath() {
		// Activator activator = Activator.getDefault();
		// if (activator != null){
		// if (activator.getStateLocation() != null &&
		// activator.getStateLocation().toString().length() > 0){
		// return
		// activator.getStateLocation().toString()+"/"+HIDDEN_PROJET_NAME;
		// }
		// }
		File f = new File(Activator.getDefault().getStateLocation().toString());
		File f2 = new File(f.getAbsolutePath() + "/" + HIDDEN_PROJET_NAME + "/");
		f2.mkdirs();
		return f2.getAbsolutePath();
	}

	protected String getCachePath() {
		return cachePath;
	}

	protected abstract void environmentClosed();

	protected void cacheCheck(Reachable traceable) {
		boolean debug = logger.isDebug(Activator.OPTIONS_DEBUG,
				Activator.getDefault());
		if (!isCacheOk(traceable)) {
			if (debug) {
				logger.trace(String.format(
						"cache default for %s build starting", traceable
								.trimFragment().toString()));
			}
			build(traceable);
			if (debug) {
				logger.trace(String.format("build for %s ended", traceable
						.trimFragment().toString()));
			}
		} else {
			if (debug) {
				logger.trace(String.format("cache for %s ok", traceable
						.trimFragment().toString()));
			}
		}
	}

	@Override
	public Iterator<Pair<Link, Reachable>> getTraceability(Request... requests)
			throws EngineException {
		long timeInNanos = 0;
		boolean debug = logger.isDebug(Activator.OPTIONS_DEBUG,
				Activator.getDefault());

		if (debug) {
			timeInNanos = System.nanoTime();
		}
		if (requests == null) {
			throw new EngineException();
		}
		boolean checkCache = isCacheCheckNeeded(requests);
		if (checkCache) {
			checkScope(getScope(requests));
		}
		if (debug) {
			if (checkCache) {
				long timeInMsc = (System.nanoTime() - timeInNanos) / 1000000;
				logger.trace(String.format("Cache checked in %d ms", timeInMsc));
			} else {
				logger.trace(String
						.format("Cache checked disabled via request"));
			}
		}
		Iterator<Pair<Link, Reachable>> result = Iterators.emptyIterator();
		for (Request request : requests) {
			// Scope and Filter are used to validate or invalidate paths so
			// they can be combined
			Predicate<Pair<Link, Reachable>> requestPredicate = TraceabilityPredicates
					.newIsInScopePredicate(request.getScope());
			if (request.getFilter() != null) {
				requestPredicate = Predicates.and(
						new FilterPredicate(request.getFilter()),
						requestPredicate);
			}
			Iterable<Couple> couples = request.getCouples();
			// for each couple an traceability iterable is computed
			for (Couple c : couples) {

				// when the target is equals to null it is a prospective
				// analysis
				if (c.getStopCondition() == null) {
					// for a depth equals to infinite the traceability shall be
					// complete
					if (request.getDepth() == DEPTH.INFINITE) {
						result = Iterators.concat(
								result,
								doGetTraceability(c.getSource(),
										request.getDirection(),
										requestPredicate));
						// otherwise just the first level shall be complete
					} else if (request.getDepth() == DEPTH.ONE) {
						result = Iterators.concat(
								result,
								doGetOneLevelTraceability(c.getSource(),
										request.getDirection(),
										requestPredicate));
					}
					// when the target is different to null a search shall be
					// performed
				} else {
					if (request.getDepth() == DEPTH.INFINITE) {
						result = Iterators.concat(
								result,
								doGetTraceability(c.getSource(), c.getStopCondition(),
										request.getDirection(),
										requestPredicate));
					} else {
						// except when the depth is equals to one in this case
						// it can be computed using a filter
						result = Iterators.concat(
								result,
								doGetTraceability(c.getSource(), c.getStopCondition(),
										request.getDirection(), Predicates.and(
												requestPredicate,
												new TargetEqualsPredicate(c
														.getStopCondition()))));
					}
				}
			}
		}
		if (debug) {
			timeInNanos = System.nanoTime() - timeInNanos;
			long timeInMsc = timeInNanos / 1000000;
			logger.trace(String.format(
					"Traceability computed in %d ms (including cache check)",
					timeInMsc));
		}
		return result;
	}

	public boolean isCacheCheckNeeded(Request... requests) {
		// all the request shall say NO to not check the cache
		int nbFalse = 0;
		int i = 0;
		for (Request r : requests) {
			Object property = r.getProperty(OPTION_CHECK_CACHE);
			boolean checkCache = true;
			if (property instanceof Boolean) {
				checkCache = (Boolean) property;
			}
			if (!checkCache) {
				nbFalse++;
			}
			i++;
		}
		return !(nbFalse == i);
	}

	protected IScope getScope(Request[] requests) {
		CompositeScope compo = new CompositeScope(Iterables.transform(
				Arrays.asList(requests), new Function<Request, IScope>() {
					public IScope apply(Request r) {
						return r.getScope();
					}
				}));
		return compo;
	}

	protected void checkScope(IScope scope) {
		if (scope != null) {
			Iterator<Reachable> i = scope.getReachables();
			while (i.hasNext()) {
				Reachable next = i.next();
				cacheCheck(next);
			}
		}
	}

	protected abstract Iterator<Pair<Link, Reachable>> doGetTraceability(
			Reachable source, DIRECTION direction,
			Predicate<Pair<Link, Reachable>> predicate);

	protected abstract Iterator<Pair<Link, Reachable>> doGetOneLevelTraceability(
			Reachable source, DIRECTION direction,
			Predicate<Pair<Link, Reachable>> predicate);

	protected abstract Iterator<Pair<Link, Reachable>> doGetTraceability(
			Reachable source, StopCondition stopCondition, DIRECTION direction,
			Predicate<Pair<Link, Reachable>> predicate);

	protected void build(Reachable traceable) {
		// TODO manage clean of the cache and transaction
		try {
			Iterable<Link> links = getLinksForTraceable(traceable);
			builder.build(traceable, this, false);
			Iterable<Link> newLinks = getLinksForTraceable(traceable);
			Iterable<Link> linksToTag = getLinksToTag(links, newLinks);
			tagDeletedRelationShips(linksToTag);
		} catch (BuilderException e) {
			// TODO error management
		}

	}

	protected abstract void removeEntriesFor(Reachable reachable);

	protected Iterable<Link> getLinksToTag(Iterable<Link> oldLinks,
			Iterable<Link> newLinks) {
		if (newLinks == null) {
			newLinks = Sets.newHashSet();
		}
		if (oldLinks == null) {
			oldLinks = Sets.newHashSet();
		}
		final Set<Link> newLinkSet = Sets.newHashSet();
		return Iterables.filter(oldLinks, new Predicate<Link>() {
			@Override
			public boolean apply(Link l) {
				return !newLinkSet.contains(l);
			}
		});
	}

	protected abstract void tagDeletedRelationShips(Iterable<Link> linksToTag);

	public abstract Iterable<Link> getLinksForTraceable(Reachable reachable);

	protected abstract boolean isCacheOk(Reachable reachable);

	@Override
	public void newUpwardRelation(Object resource, Object source,
			List<? extends Object> targets, TType kind) {
		Function<Object, Reachable> obj2RO = URIFunctions
				.newObject2ReachableFunction();
		Reachable resourceReachable = obj2RO.apply(resource);
		Reachable sourceR = obj2RO.apply(source);
		List<Reachable> targetsR = Lists.newArrayList(Iterables.transform(
				targets, obj2RO));
		if (sourceR != null
				&& Iterables.filter(targetsR, Predicates.notNull()).iterator()
						.hasNext()) {
			newUpwardRelation(resourceReachable, sourceR, targetsR, kind);
		}

	}

	public abstract void newUpwardRelation(Reachable container,
			Reachable source, List<Reachable> targets, TType kind);

	@Override
	public void startBuild(Reachable reachable) {
		// the build is starting it is the moment to remove the corresponding
		// entries
		removeEntriesFor(reachable);
	}

	@Override
	public void endBuild(Reachable reachable) {
		// DO NOTHING
	}

	@Override
	public void errorOccurs(Reachable reachable, Throwable t) {
		logger.error(t.getMessage());
		t.printStackTrace();
		// DO NOTHING
	}

	@Override
	public boolean needsBuild(Reachable reachable) {
		return !isCacheOk(reachable);
	}

	private class TargetEqualsPredicate implements
			Predicate<Pair<Link, Reachable>> {
		private StopCondition condition;

		public TargetEqualsPredicate(StopCondition condition) {
			this.condition = condition;
		}

		public boolean apply(Pair<Link, Reachable> pair) {
			return condition.apply(pair.getSecond());
		}
	}

}

/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.cache.emfbased;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.reqcycle.core.ILogger;
import org.polarsys.reqcycle.traceability.cache.AbstractCachedTraceabilityEngine;
import org.polarsys.reqcycle.traceability.cache.Activator;
import org.polarsys.reqcycle.traceability.cache.emfbased.functions.TraceabilityLink2Link;
import org.polarsys.reqcycle.traceability.cache.emfbased.functions.Traceable2TraceableElement;
import org.polarsys.reqcycle.traceability.cache.emfbased.functions.TraceableElement2Traceable;
import org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.AnalyzedResource;
import org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.CacheTracabilityFactory;
import org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.Model;
import org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceabilityLink;
import org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceableElement;
import org.polarsys.reqcycle.traceability.cache.emfbased.pickers.TraceableElementPicker;
import org.polarsys.reqcycle.traceability.cache.emfbased.predicates.TraceabilityLinkPredicate;
import org.polarsys.reqcycle.traceability.model.Link;
import org.polarsys.reqcycle.traceability.model.Pair;
import org.polarsys.reqcycle.traceability.model.StopCondition;
import org.polarsys.reqcycle.traceability.model.TType;
import org.polarsys.reqcycle.traceability.model.scopes.ResourceScope;
import org.polarsys.reqcycle.uri.exceptions.IReachableHandlerException;
import org.polarsys.reqcycle.uri.model.IReachableHandler;
import org.polarsys.reqcycle.uri.model.Reachable;
import org.polarsys.reqcycle.uri.model.ReachableObject;
import org.polarsys.reqcycle.utils.inject.ZigguratInject;
import org.polarsys.reqcycle.utils.iterators.exceptions.PickerExecutionException;
import org.polarsys.reqcycle.utils.iterators.factories.IteratorFactory;
import org.polarsys.reqcycle.utils.iterators.pickers.IPicker;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

@Singleton
public class CacheTraceabilityEngine extends AbstractCachedTraceabilityEngine {

	ResourceSet set;
	private Resource resource;
	public static String CACHE_RESOURCE_NAME = "traceability.ctr";
	@Inject
	ILogger logger;
	private Model theModel;
	
	

	@Inject
	public CacheTraceabilityEngine() {
		super();
	}

	@PostConstruct
	public void postConstruct() {
		set = new ResourceSetImpl();
		String pathName = getCachePath() + "/" + CACHE_RESOURCE_NAME;
		if (logger.isDebug(Activator.OPTIONS_DEBUG, Activator.getDefault())) {
			logger.trace(String
					.format("cached database loading : %s", pathName));
		}
		URI uri = URI.createFileURI(pathName);
		if (!(new File(pathName).exists())) {
			if (logger.isDebug(Activator.OPTIONS_DEBUG, Activator.getDefault())) {
				logger.trace("Caching resource for traceability creation");
			}
			resource = set.createResource(uri);
			Model m = CacheTracabilityFactory.eINSTANCE.createModel();
			resource.getContents().add(m);
		} else {
			resource = set.getResource(uri, true);
		}
		if (resource == null || resource.getContents().isEmpty()) {
			// TODO log
		}
		theModel = (Model) resource.getContents().get(0);
		if (logger.isDebug(Activator.OPTIONS_DEBUG, Activator.getDefault())) {
			logger.trace("cached database loaded ");
		}
	}

	@Override
	protected void environmentClosed() {
		if (resource != null) {
			boolean debug = logger.isDebug(Activator.OPTIONS_DEBUG,
					Activator.getDefault());
			if (debug) {
				logger.trace("Resource cache Closing");
			}
			try {
				resource.save(Collections.emptyMap());
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (debug) {
				logger.trace("Resource cache Closed");
			}
		}
	}

	@Override
	protected boolean isCacheOk(Reachable traceable) {
		String uri = ResourceScope.getURIPath(traceable.trimFragment());
		if (uri == null) {
			// TODO error management
		}
		for (AnalyzedResource a : theModel.getResources()) {
			if (uri.equals(a.getUri())) {
				try {
					IReachableHandler handler = manager
							.getHandlerFromReachable(traceable);
					ReachableObject object = handler
							.getFromReachable(traceable);
					if (object != null) {
						// NULL means the object can not identify its revision
						// so the cache must be computed each time
						String revisionIdentification = object
								.getRevisionIdentification();
						if (revisionIdentification != null) {
							return Objects.equal(revisionIdentification,
									a.getModificationTime());
						}
					}
				} catch (IReachableHandlerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	@Override
	public void newUpwardRelation(Reachable traceable, Reachable container, Reachable source,
			List<Reachable> targets, TType kind) {
		Traceable2TraceableElement traceable2TraceableElement = new Traceable2TraceableElement(
				theModel);
		ZigguratInject.inject(traceable2TraceableElement);
		TraceableElement sourceElement = traceable2TraceableElement
				.apply(source);
		Iterable<TraceableElement> targetElements = Iterables.transform(
				targets, traceable2TraceableElement);
		TraceabilityLink link = getTraceabilityLink(container, sourceElement,
				targetElements, kind.getSemantic());
		if (link == null) {
			AnalyzedResource r = getOrCreateAnalyzedResource(container);
			link = CacheTracabilityFactory.eINSTANCE.createTraceabilityLink();
			link.setLabel(kind.getSemantic());
			ArrayList<TraceableElement> sourcesToAdd = Lists
					.newArrayList(sourceElement);
			link.getSources().addAll(sourcesToAdd);
			ArrayList<TraceableElement> targetsToAdd = Lists
					.newArrayList(targetElements);
			link.getTargets().addAll(targetsToAdd);
			r.getLinks().add(link);
		}
	}

	private TraceabilityLink getTraceabilityLink(Reachable container,
			TraceableElement sourceElements,
			Iterable<TraceableElement> targetElements, String label) {
		TraceabilityLink result = null;
		AnalyzedResource analy = getResource(container);
		if (analy != null) {
			TraceabilityLink t = Iterables.find(analy.getLinks(),
					new TraceabilityLinkPredicate(sourceElements,
							targetElements, label), null);
			result = t;
		}
		return result;
	}

	@Override
	public Iterable<Link> getLinksForTraceable(Reachable traceable) {
		AnalyzedResource r = getResource(traceable);
		if (r != null) {
			Iterable<TraceabilityLink> links = r.getLinks();
			Iterable<Link> result = Iterables.transform(links,
					new TraceabilityLink2Link());
			return result;
		}
		return Lists.newArrayList();
	}

	private AnalyzedResource getOrCreateAnalyzedResource(Reachable traceable) {
		AnalyzedResource r = getResource(traceable);
		if (r == null) {
			r = CacheTracabilityFactory.eINSTANCE.createAnalyzedResource();
			theModel.getResources().add(r);
			String traceablePath = traceable.trimFragment().toString();
			r.setUri(traceablePath);
			IReachableHandler uriHandler = null;
			try {
				uriHandler = manager.getHandlerFromReachable(traceable);
				ReachableObject object = uriHandler.getFromReachable(traceable);
				if (object != null) {
					r.setModificationTime(object.getRevisionIdentification());
				}
			} catch (IReachableHandlerException e) {
				// TODO ERROR management ?
				e.printStackTrace();
			}
		}
		return r;
	}

	private AnalyzedResource getResource(final Reachable traceable) {
		return Iterables.find(theModel.getResources(),
				new Predicate<AnalyzedResource>() {
					@Override
					public boolean apply(AnalyzedResource a) {
						return a.getUri().equals(
								traceable.trimFragment().toString());
					}
				}, null);
	}

	@Override
	protected void tagDeletedRelationShips(Iterable<Link> linksToTag) {

	}

	@Override
	protected Iterator<Pair<Link, Reachable>> doGetTraceability(
			Reachable source, DIRECTION direction,
			Predicate<Pair<Link, Reachable>> scope) {
		TraceableElement s = getTraceableElement(source);
		if (s != null) {
			IPicker picker = getPicker(direction, scope);
			Iterable<IPicker> pickers = Arrays.asList(new IPicker[] { picker });
			IteratorFactory f = new IteratorFactory(pickers);
			f.activateWidthWisdom();
			f.activateRedundancyAwareness();
			Iterable<Object> iterable = f.createIterable(s);
			Iterator<Object> i = iterable.iterator();
			i.next();
			// create a list to "copy" data to the caller
			return Lists.newArrayList(
					Iterators.transform(i,
							new Function<Object, Pair<Link, Reachable>>() {
								public Pair<Link, Reachable> apply(Object o) {
									return (Pair<Link, Reachable>) o;
								}
							})).iterator();
		}
		return Iterators.emptyIterator();
	}

	@Override
	protected Iterator<Pair<Link, Reachable>> doGetOneLevelTraceability(
			Reachable source, DIRECTION direction,
			Predicate<Pair<Link, Reachable>> scope) {
		TraceableElement s = getTraceableElement(source);
		if (s != null) {
			IPicker picker = getPicker(direction, scope);
			try {
				List<Pair<Link, Reachable>> result = new ArrayList<Pair<Link, Reachable>>();
				Iterable<?> elements = picker.getNexts(source);
				return Lists.newArrayList(
						Iterables.transform(elements,
								new Function<Object, Pair<Link, Reachable>>() {
									public Pair<Link, Reachable> apply(Object o) {
										return (Pair<Link, Reachable>) o;
									}
								})).iterator();
			} catch (PickerExecutionException e) {
				e.printStackTrace();
			}
		}
		return Iterators.emptyIterator();
	}

	@Override
	protected Iterator<Pair<Link, Reachable>> doGetTraceability(
			Reachable source, StopCondition t, DIRECTION direction,
			Predicate<Pair<Link, Reachable>> scope) {
		// TODO Auto-generated method stub
		ArrayDeque<Pair<Link, Reachable>> result = new ArrayDeque<Pair<Link, Reachable>>();
		ArrayDeque<Pair<Link, Reachable>> current = new ArrayDeque<Pair<Link, Reachable>>();
		TraceableElement s = getTraceableElement(source);
		if (s != null && t != null) {
			IPicker picker = getPicker(direction, scope);
			Iterable<IPicker> pickers = Arrays.asList(new IPicker[] { picker });
			IteratorFactory f = new IteratorFactory(pickers);
			f.activateWidthWisdom();
			f.activateRedundancyAwareness();
			Iterable<Object> iterable = f.createIterable(s);
			Iterator<Object> i = iterable.iterator();
			if (checkPath((TraceableElement) i.next(), t, i, result, current)) {
				result.addAll(current);
			}

		}
		return result.iterator();
	}

	private IPicker getPicker(DIRECTION direction,
			Predicate<Pair<Link, Reachable>> scope) {
		TraceableElementPicker traceableElementPicker = new TraceableElementPicker(
				direction, theModel, scope);
		ZigguratInject.inject(traceableElementPicker);
		return traceableElementPicker;
	}

	@SuppressWarnings("unchecked")
	private boolean checkPath(TraceableElement te, StopCondition condition,
			Iterator<Object> i, ArrayDeque<Pair<Link, Reachable>> result,
			ArrayDeque<Pair<Link, Reachable>> current) {
		boolean found = false;
		TraceableElement2Traceable traceableElement2Traceable = new TraceableElement2Traceable();
		ZigguratInject.inject(traceableElement2Traceable);
		Reachable source = traceableElement2Traceable.apply(te);

		while (i.hasNext()) {
			Object o = i.next();
			if (o instanceof Pair) {
				
				Pair<Link, Reachable> pair = (Pair<Link, Reachable>) o;
				Reachable secondReachable = pair.getSecond();
				Pair<Link, Reachable> pair2 = new Pair<Link, Reachable>(pair.getFirst(), secondReachable);
				if (condition.apply(pair2)) {
					current.add(pair);
					found = true;
					break;
				} else if (!(pair.getFirst().getSources().contains(source))) {
					if (checkPath(
							new Traceable2TraceableElement(theModel).apply(pair
									.getFirst().getSources().iterator().next()),
							condition, i, result, current)) {
						result.addAll(current);
					}
				} else {
					current.add(pair);
					if (condition.apply(pair2)) {
						found = true;
						break;
					}
				}
			}
		}
		return found;

	}

	private TraceableElement getTraceableElement(Reachable source) {
		return new Traceable2TraceableElement(theModel).apply(source);
	}

	@Override
	protected void removeTraceabilityLink(Reachable traceable) {
		// TODO don t delete but tag
		AnalyzedResource a = getResource(traceable);
		if (a != null) {
			for (TraceabilityLink l : a.getLinks()) {
				deleteNodesIfCorrespondingToTraceable(traceable, l.getSources());
				deleteNodesIfCorrespondingToTraceable(traceable, l.getTargets());
				EcoreUtil.delete(l);
			}
		}
	}

	private void deleteNodesIfCorrespondingToTraceable(Reachable traceable,
			Iterable<TraceableElement> collection) {
		TraceableElement2Traceable function = new TraceableElement2Traceable();
		ZigguratInject.inject(function);
		for (TraceableElement t : collection) {
			Reachable tr = function.apply(t);
			if (tr.trimFragment().equals(traceable)) {
				EcoreUtil.delete(t);
			}
		}
	}

	@Override
	public void startBuild(Reachable reachable) {
		getOrCreateAnalyzedResource(reachable);
	}

	@Override
	protected Iterator<Pair<Link, Reachable>> doGetAllTraceability(
			DIRECTION direction,
			Predicate<Pair<Link, Reachable>> requestPredicate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Iterable<Reachable> getEntriesFor(Reachable reachable) {
		// TODO Auto-generated method stub
		return null;
	}





}

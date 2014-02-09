/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.cache.emfbased.pickers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.eclipse.emf.common.util.EList;
import org.polarsys.reqcycle.traceability.cache.emfbased.functions.Traceable2TraceableElement;
import org.polarsys.reqcycle.traceability.cache.emfbased.functions.TraceableElement2Traceable;
import org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.Model;
import org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceabilityLink;
import org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceableElement;
import org.polarsys.reqcycle.traceability.engine.ITraceabilityEngine.DIRECTION;
import org.polarsys.reqcycle.traceability.model.Link;
import org.polarsys.reqcycle.traceability.model.Pair;
import org.polarsys.reqcycle.traceability.model.TType;
import org.polarsys.reqcycle.uri.IReachableCreator;
import org.polarsys.reqcycle.uri.IReachableManager;
import org.polarsys.reqcycle.uri.model.Reachable;
import org.eclipse.ziggurat.inject.ZigguratInject;
import org.topcased.iterators.exceptions.PickerExecutionException;
import org.topcased.iterators.pickers.IPicker;

import com.google.common.base.Predicate;

public class TraceableElementPicker implements IPicker {

	private DIRECTION d;
	private Model theModel;
	private Predicate<Pair<Link, Reachable>> scope;
	@Inject
	IReachableManager manager;
	
	@Inject
	IReachableCreator creator;

	public TraceableElementPicker(DIRECTION d, Model m,
			Predicate<Pair<Link, Reachable>> scope) {
		this.d = d;
		this.theModel = m;
		this.scope = scope;
	}

	@Override
	public Iterable<?> getNexts(Object element) throws PickerExecutionException {
		ArrayDeque<Object> result = new ArrayDeque<Object>();
		TraceableElement elem = null;
		if (element instanceof TraceableElement) {
			elem = (TraceableElement) element;
		}
		if (element instanceof Pair) {
			Pair<Link, Reachable> pair = (Pair<Link, Reachable>) element;
			elem = new Traceable2TraceableElement(theModel).apply(pair
					.getSecond());
		}
		TraceableElement2Traceable traceableElement2Traceable = new TraceableElement2Traceable();
		ZigguratInject.inject(traceableElement2Traceable);
		if (elem != null) {
			List<TraceabilityLink> list = null;
			if (d == DIRECTION.UPWARD) {
				list = elem.getOutgoings();
			} else {
				list = elem.getIncomings();
			}
			for (TraceabilityLink l : list) {
				EList<TraceableElement> list2 = null;
				if (d == DIRECTION.UPWARD) {
					list2 = l.getTargets();
				} else {
					list2 = l.getSources();
				}
				for (TraceableElement t : list2) {
					Reachable source = traceableElement2Traceable.apply(elem);
					Reachable target = traceableElement2Traceable.apply(t);
					
					// RFa - fix link creation
					URI uri = null;
					try {
						uri = new URI(l.getResource().getUri());
					} catch (URISyntaxException e) {
						
						e.printStackTrace();
					}
					Reachable r = creator.getReachable(uri);
					UUID uniqueID = UUID.randomUUID(); 
					
					Pair<Link, Reachable> pair = new Pair<Link, Reachable>(
							new Link(r,new TType(uniqueID.toString(),l.getLabel()),
									Collections.singleton(source),
									Collections.singleton(target)), target);
					if (scope.apply(pair)) {
						result.add(pair);
					}
				}
			}
		}
		return result;
	}
}

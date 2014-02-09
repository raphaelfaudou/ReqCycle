/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.polarsys.reqcycle.traceability.model.Link;
import org.polarsys.reqcycle.traceability.model.Pair;
import org.polarsys.reqcycle.uri.model.Reachable;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;

public class LazyMap implements Multimap<Reachable, Link> {

	Multimap<Reachable, Link> delegate = HashMultimap.create();
	private Iterator<Pair<Link, Reachable>> resultOfEngine;
	private Pair<Link, Reachable> next;

	public LazyMap(Iterator<Pair<Link, Reachable>> resultOfEngine) {
		this.resultOfEngine = resultOfEngine;
		if (resultOfEngine.hasNext()) {
			next = resultOfEngine.next();
			Reachable first = getSource(next);
			Predicate<Reachable> equalTo = Predicates.equalTo(first);
			addIf(equalTo, Predicates.not(equalTo));
		}
	}

	public void addUntil(Predicate<Reachable> predicateToReach,
			Predicate<Reachable> predicateToLeave) {
		boolean reached = false;

		while (resultOfEngine.hasNext()) {
			next = resultOfEngine.next();
			Reachable tmp = getSource(next);
			delegate.put(tmp, next.getFirst());
			if (!reached) {
				if (predicateToReach.apply(tmp)) {
					reached = true;
				}
			}
			if (reached) {
				if (predicateToLeave.apply(tmp)) {
					break;
				}
			}

		}
	}

	public void addIf(Predicate<Reachable> firstCondition,
			Predicate<Reachable> predicateToLeave) {
		boolean first = true;
		while (resultOfEngine.hasNext()) {
			next = resultOfEngine.next();
			Reachable tmp = getSource(next);
			delegate.put(tmp, next.getFirst());
			if (first) {
				if (!firstCondition.apply(tmp)) {
					break;
				}
			}

			first = false;
			if (!first && predicateToLeave.apply(tmp)) {
				break;
			}
		}
	}

	public Reachable getSource(Pair<Link, Reachable> pair) {
		return pair.getFirst().getSources().iterator().next();
	}

	@Override
	public Map<Reachable, Collection<Link>> asMap() {
		return delegate.asMap();
	}

	@Override
	public void clear() {
		delegate.clear();
	}

	@Override
	public boolean containsEntry(Object arg0, Object arg1) {
		return delegate.containsEntry(arg0, arg1);
	}

	@Override
	public boolean containsKey(Object arg0) {
		return delegate.containsKey(arg0);
	}

	@Override
	public boolean containsValue(Object arg0) {
		return delegate.containsValue(arg0);
	}

	@Override
	public Collection<Entry<Reachable, Link>> entries() {
		return delegate.entries();
	}

	@Override
	public Collection<Link> get(Reachable arg0) {
		Collection<Link> result = delegate.get(arg0);
		if (result.isEmpty()) {
			Predicate<Reachable> equalTo = Predicates.equalTo(arg0);
			addUntil(equalTo, Predicates.not(equalTo));
		}
		return result;
	}

	@Override
	public boolean isEmpty() {
		return delegate.isEmpty();
	}

	@Override
	public Set<Reachable> keySet() {
		return delegate.keySet();
	}

	@Override
	public Multiset<Reachable> keys() {
		return delegate.keys();
	}

	@Override
	public boolean put(Reachable arg0, Link arg1) {
		return delegate.put(arg0, arg1);
	}

	@Override
	public boolean putAll(Multimap<? extends Reachable, ? extends Link> arg0) {
		return delegate.putAll(arg0);
	}

	@Override
	public boolean putAll(Reachable arg0, Iterable<? extends Link> arg1) {
		return delegate.putAll(arg0, arg1);
	}

	@Override
	public boolean remove(Object arg0, Object arg1) {
		return delegate.remove(arg0, arg1);
	}

	@Override
	public Collection<Link> removeAll(Object arg0) {
		return delegate.removeAll(arg0);
	}

	@Override
	public Collection<Link> replaceValues(Reachable arg0,
			Iterable<? extends Link> arg1) {
		return delegate.replaceValues(arg0, arg1);
	}

	@Override
	public int size() {
		return delegate.size();
	}

	@Override
	public Collection<Link> values() {
		return delegate.values();
	}

}

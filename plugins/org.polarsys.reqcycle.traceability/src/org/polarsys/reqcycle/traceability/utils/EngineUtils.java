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

import java.util.Iterator;

import org.polarsys.reqcycle.traceability.model.Link;
import org.polarsys.reqcycle.traceability.model.Pair;
import org.polarsys.reqcycle.uri.model.Reachable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class EngineUtils {
	public static Multimap<Reachable, Link> toFollowingMap(
			Iterator<Pair<Link, Reachable>> resultOfEngine) {
		Multimap<Reachable, Link> result = HashMultimap.create();
		while (resultOfEngine.hasNext()) {
			Pair<Link, Reachable> next = resultOfEngine.next();
			Reachable source = next.getFirst().getSources().iterator().next();
			result.put(source, next.getFirst());
		}
		return result;
	}

	public static Multimap<Reachable, Link> toPrecedingMap(
			Iterator<Pair<Link, Reachable>> resultOfEngine) {
		Multimap<Reachable, Link> result = HashMultimap.create();
		while (resultOfEngine.hasNext()) {
			Pair<Link, Reachable> next = resultOfEngine.next();
			result.put(next.getFirst().getTargets().iterator().next(),
					next.getFirst());
		}
		return result;
	}

	public static Multimap<Reachable, Link> toFollowingLazyMap(
			Iterator<Pair<Link, Reachable>> resultOfEngine) {
		LazyMap map = new LazyMap(resultOfEngine);
		return map;
	}
}

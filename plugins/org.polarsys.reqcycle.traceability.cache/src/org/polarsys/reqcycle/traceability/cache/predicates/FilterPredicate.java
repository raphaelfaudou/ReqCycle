/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.cache.predicates;

import org.polarsys.reqcycle.traceability.model.Filter;
import org.polarsys.reqcycle.traceability.model.Link;
import org.polarsys.reqcycle.traceability.model.Pair;
import org.polarsys.reqcycle.uri.model.Reachable;

import com.google.common.base.Predicate;

public class FilterPredicate implements Predicate<Pair<Link, Reachable>> {
	private Filter filter;

	public FilterPredicate(Filter filter) {
		this.filter = filter;
	}

	public boolean apply(Pair<Link, Reachable> pair) {
		return filter.apply(pair);
	}
}

/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.cache.emfbased.predicates;

import org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceableElement;
import org.polarsys.reqcycle.uri.model.Reachable;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;

public class TraceableEqualsToTraceableElement implements Predicate<TraceableElement>{
	private Reachable t;

	public TraceableEqualsToTraceableElement  (Reachable  t){
		this.t = t;
	}
	
	@Override
	public boolean apply(TraceableElement eleme) {
		return Objects.equal(t.toString(),eleme.getUri());
	}
}

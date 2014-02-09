/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.model.scopes;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.polarsys.reqcycle.uri.model.Reachable;

import com.google.common.collect.Iterators;

public class ResourceScope implements IScope {
	public Set<Reachable> pathsInScope = new HashSet<Reachable>();

	public ResourceScope(Reachable... traceables) {
		for (Reachable t : traceables) {
			if (t != null) {
				pathsInScope.add(t.trimFragment());
			}
		}
	}

	public static String getURIPath(Reachable t) {
		return t.trimFragment().getURI().toString();
	}

	@Override
	public Iterator<Reachable> getReachables() {
		return Iterators.unmodifiableIterator(pathsInScope.iterator());
	}
}

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

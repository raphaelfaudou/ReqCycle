package org.eclipse.reqcycle.traceability.model.scopes;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.reqcycle.uri.model.Reachable;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class CompositeScope implements IScope {
	ArrayDeque<IScope> scopes = new ArrayDeque<IScope>();
	Set<Reachable> cache = new HashSet<Reachable>();
	boolean flag = false;

	public CompositeScope() {
	}

	public CompositeScope(Iterable<IScope> initialScopes) {
		scopes.addAll(Lists.newArrayList(initialScopes));
	}

	public void remove(IScope source) {
		scopes.remove(source);
		flag = false;
	}

	public void add(IScope scope) {
		scopes.add(scope);
		flag = false;
	}

	@Override
	public Iterator<Reachable> getReachables() {
		if (!flag) {
			flag = true;
			for (IScope s : scopes) {
				cache.addAll(Sets.newHashSet(s.getReachables()));
			}
		}
		return Iterators.unmodifiableIterator(cache.iterator());
	}
}

package org.eclipse.reqcycle.traceability.cache.emfbased.predicates;

import org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceableElement;
import org.eclipse.reqcycle.uri.model.Reachable;

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

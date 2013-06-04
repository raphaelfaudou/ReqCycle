package org.eclipse.reqcycle.traceability.cache.emfbased.predicates;

import java.util.NoSuchElementException;

import org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceabilityLink;
import org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceableElement;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

public class TraceabilityLinkPredicate implements Predicate<TraceabilityLink> {

	private TraceableElement source;
	private Iterable<TraceableElement> targets;
	private String label;

	public TraceabilityLinkPredicate(TraceableElement source,
			Iterable<TraceableElement> targets, String label) {
		this.source = source;
		this.targets = targets;
		this.label = label;
	}

	@Override
	public boolean apply(TraceabilityLink arg0) {
		try {
			if (Objects.equal(arg0.getLabel(), label)) {
				if (Iterables.find(arg0.getSources(),
						Predicates.equalTo(source)) != null) {
					if (Iterables.elementsEqual(arg0.getTargets(), targets)) {
						return true;
					}
				}
			}
		} catch (NoSuchElementException e) {
		}
		return false;
	}

}

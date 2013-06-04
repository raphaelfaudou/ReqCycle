package org.eclipse.reqcycle.traceability.cache.jena.pickers;

import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine.DIRECTION;
import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.traceability.model.Pair;
import org.eclipse.reqcycle.traceability.storage.ITraceabilityStorage;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.topcased.iterators.exceptions.PickerExecutionException;
import org.topcased.iterators.pickers.IPicker;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

public class GetTraceabilityPicker implements IPicker {

	private DIRECTION direction;
	private ITraceabilityStorage storage;
	private Predicate<Pair<Link, Reachable>> predicate;

	public GetTraceabilityPicker(DIRECTION d, ITraceabilityStorage storage,
			Predicate<Pair<Link, Reachable>> predicate) {
		this.direction = d;
		this.storage = storage;
		this.predicate = predicate;
	}

	@Override
	public Iterable<?> getNexts(Object element) throws PickerExecutionException {
		if (element instanceof Reachable) {
			Reachable r = (Reachable) element;
			return getTraceability(r, direction);
		} else if (element instanceof Pair) {
			return getNexts(((Pair) element).getSecond());
		}
		return null;
	}

	private Iterable<Pair<Link, Reachable>> getTraceability(Reachable source,
			DIRECTION direction) {
		Iterable<Pair<Link, Reachable>> result = storage.getTraceability(
				source, direction);
		return Iterables.filter(result, predicate);
	}
}

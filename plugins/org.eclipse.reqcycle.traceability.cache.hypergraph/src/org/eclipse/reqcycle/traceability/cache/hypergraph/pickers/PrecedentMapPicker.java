package org.eclipse.reqcycle.traceability.cache.hypergraph.pickers;

import java.util.Collections;
import java.util.Map;

import org.hypergraphdb.HGHandle;
import org.hypergraphdb.util.Pair;
import org.topcased.iterators.exceptions.PickerExecutionException;
import org.topcased.iterators.pickers.IPicker;

public class PrecedentMapPicker implements IPicker {

	private Map<HGHandle, Pair<HGHandle, HGHandle>> map;

	public PrecedentMapPicker(Map<HGHandle, Pair<HGHandle, HGHandle>> map) {
		this.map = map;
	}

	@Override
	public Iterable<?> getNexts(Object element) throws PickerExecutionException {
		if (element instanceof Pair) {
			Pair pair = (Pair) element;
			return Collections.singleton(map.get(pair.getSecond()));
		} else if (element instanceof HGHandle) {
			return Collections.singleton(map.get(element));
		}
		return null;
	}

}

/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.cache.storagebased.engine.pickers;

import org.polarsys.reqcycle.traceability.engine.ITraceabilityEngine.DIRECTION;
import org.polarsys.reqcycle.traceability.model.Link;
import org.polarsys.reqcycle.traceability.model.Pair;
import org.polarsys.reqcycle.traceability.storage.ITraceabilityStorage;
import org.polarsys.reqcycle.uri.model.Reachable;
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

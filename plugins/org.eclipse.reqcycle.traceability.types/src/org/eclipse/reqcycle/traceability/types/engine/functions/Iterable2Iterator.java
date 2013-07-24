package org.eclipse.reqcycle.traceability.types.engine.functions;

import java.util.Iterator;

import com.google.common.base.Function;

public class Iterable2Iterator<T> implements Function<Iterable<T>, Iterator<T>> {

	@Override
	public Iterator<T> apply(Iterable<T> arg0) {
		return arg0.iterator();
	}

}

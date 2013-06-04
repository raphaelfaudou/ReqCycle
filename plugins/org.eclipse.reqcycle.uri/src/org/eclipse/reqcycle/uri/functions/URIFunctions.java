package org.eclipse.reqcycle.uri.functions;

import org.agesys.inject.AgesysInject;
import org.eclipse.reqcycle.uri.model.Reachable;

import com.google.common.base.Function;

public class URIFunctions {
	public static Function<Reachable, Reachable> newTrimFragmentFunction() {
		Function<Reachable, Reachable> result = new TrimFragmentFunction();
		AgesysInject.inject(result);
		return result;
	}

	public static Function<Object, Reachable> newObject2ReachableFunction() {
		Function<Object, Reachable> result = new Object2Reachable();
		AgesysInject.inject(result);
		return result;
	}
}

package org.eclipse.reqcycle.uri.functions;

import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.ziggurat.inject.ZigguratInject;

import com.google.common.base.Function;

public class URIFunctions {
	public static Function<Reachable, Reachable> newTrimFragmentFunction() {
		Function<Reachable, Reachable> result = new TrimFragmentFunction();
		ZigguratInject.inject(result);
		return result;
	}

	public static Function<Object, Reachable> newObject2ReachableFunction() {
		Function<Object, Reachable> result = new Object2Reachable();
		ZigguratInject.inject(result);
		return result;
	}
}

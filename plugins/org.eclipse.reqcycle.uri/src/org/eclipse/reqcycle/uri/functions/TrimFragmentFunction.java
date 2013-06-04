package org.eclipse.reqcycle.uri.functions;

import org.eclipse.reqcycle.uri.model.Reachable;

import com.google.common.base.Function;

public class TrimFragmentFunction implements Function<Reachable, Reachable> {

	@Override
	public Reachable apply(Reachable arg0) {
		return arg0.trimFragment();
	}

}

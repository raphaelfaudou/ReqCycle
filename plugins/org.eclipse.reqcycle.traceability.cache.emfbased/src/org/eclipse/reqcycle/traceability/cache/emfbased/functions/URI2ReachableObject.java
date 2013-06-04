package org.eclipse.reqcycle.traceability.cache.emfbased.functions;

import java.net.URI;

import javax.inject.Inject;

import org.eclipse.reqcycle.uri.IReachableCreator;
import org.eclipse.reqcycle.uri.IReachableManager;
import org.eclipse.reqcycle.uri.exceptions.IReachableHandlerException;
import org.eclipse.reqcycle.uri.model.IReachableHandler;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.reqcycle.uri.model.ReachableObject;

import com.google.common.base.Function;

public class URI2ReachableObject implements Function<URI, ReachableObject> {
	@Inject
	IReachableManager manager;
	@Inject
	IReachableCreator creator;

	public ReachableObject apply(URI o) {
		try {
			Reachable r = creator.getReachable(o);
			IReachableHandler handler = manager.getHandlerFromReachable(r);
			return handler.getFromReachable(r);
		} catch (IReachableHandlerException e) {
			return null;
		}
	}
}

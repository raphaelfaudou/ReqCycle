package org.eclipse.reqcycle.traceability.builder.impl;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.agesys.inject.AgesysInject;
import org.eclipse.reqcycle.core.ILogger;
import org.eclipse.reqcycle.traceability.builder.Activator;
import org.eclipse.reqcycle.traceability.builder.ITraceabilityBuilder;
import org.eclipse.reqcycle.traceability.builder.exceptions.BuilderException;
import org.eclipse.reqcycle.uri.IReachableListenerManager;
import org.eclipse.reqcycle.uri.IReachableManager;
import org.eclipse.reqcycle.uri.exceptions.IReachableHandlerException;
import org.eclipse.reqcycle.uri.exceptions.VisitableException;
import org.eclipse.reqcycle.uri.model.IReachableHandler;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.reqcycle.uri.model.ReachableObject;
import org.eclipse.reqcycle.uri.visitors.IVisitable;

@Singleton
public class TraceabilityBuilder implements ITraceabilityBuilder {

	@Inject
	IReachableManager manager;
	@Inject
	ILogger logger;
	@Inject
	IReachableListenerManager listener;

	@Override
	public void build(Reachable t, IBuilderCallBack callBack, boolean forceBuild)
			throws BuilderException {
		callBack = new DelegatedAndDecoratedBuilderCallBack(callBack);
		IReachableHandler h;
		try {
			h = manager.getHandlerFromReachable(t);
			if (h != null) {
				ReachableObject object = h.getFromReachable(t);
				if (object != null) {
					Reachable reachable = object.getReachable(null);
					if (forceBuild || callBack.needsBuild(reachable)) {
						if (Activator.getDefault().isDebugging()) {
							logger.trace(String.format("build for %s starting",
									t.toString()));
						}
						callBack.startBuild(reachable);
						IVisitable visitable;
						try {
							visitable = object.getVisitable();
							TraceabilityVisitor visitor = new TraceabilityVisitor(
									callBack);
							AgesysInject.inject(visitor);
							visitable.accept(visitor);
							visitable.dispose();
							callBack.endBuild(reachable);
							listener.notifyChanged(reachable);
							if (Activator.getDefault().isDebugging()) {
								logger.trace(String.format(
										"build for %s ended", t.toString()));
							}
						} catch (VisitableException e) {
							callBack.errorOccurs(reachable, e);
							// TODO error management
							if (Activator.getDefault().isDebugging()) {
								logger.trace(String.format(
										"build for %s failed", t.toString()));
							}
						} catch (Throwable e2) {
							callBack.errorOccurs(reachable, e2);
						}
					} else {
						if (Activator.getDefault().isDebugging()) {
							logger.trace(String.format(
									"build for %s unnecessary", t.toString()));
						}
					}
				}
			}
		} catch (IReachableHandlerException e) {
			throw new BuilderException();
		}
	}
}

/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.builder.impl;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.polarsys.reqcycle.core.ILogger;
import org.polarsys.reqcycle.traceability.builder.Activator;
import org.polarsys.reqcycle.traceability.builder.ITraceabilityBuilder;
import org.polarsys.reqcycle.traceability.builder.exceptions.BuilderException;
import org.polarsys.reqcycle.uri.IReachableListenerManager;
import org.polarsys.reqcycle.uri.IReachableManager;
import org.polarsys.reqcycle.uri.exceptions.IReachableHandlerException;
import org.polarsys.reqcycle.uri.exceptions.VisitableException;
import org.polarsys.reqcycle.uri.model.IReachableHandler;
import org.polarsys.reqcycle.uri.model.Reachable;
import org.polarsys.reqcycle.uri.model.ReachableObject;
import org.polarsys.reqcycle.uri.visitors.IVisitable;
import org.polarsys.reqcycle.utils.inject.ZigguratInject;

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
							ZigguratInject.inject(visitor);
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

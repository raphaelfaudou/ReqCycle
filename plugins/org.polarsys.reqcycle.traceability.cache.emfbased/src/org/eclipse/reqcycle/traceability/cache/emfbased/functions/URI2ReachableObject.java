/*******************************************************************************
 *  * Copyright (c) 2013-2014 AtoS.
 *  * All rights reserved. This program and the accompanying materials
 *  * are made available under the terms of the Eclipse Public License v1.0
 *  * which accompanies this distribution, and is available at
 *  * http://www.eclipse.org/legal/epl-v10.html
 *  *
 *  * Contributors:
 *  *    {INITIAL AUTHOR} - initial API and implementation and/or initial documentation
 *******************************************************************************/
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

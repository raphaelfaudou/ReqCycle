/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.uri.functions;

import javax.inject.Inject;

import org.polarsys.reqcycle.uri.IReachableManager;
import org.polarsys.reqcycle.uri.exceptions.IReachableHandlerException;
import org.polarsys.reqcycle.uri.model.IObjectHandler;
import org.polarsys.reqcycle.uri.model.Reachable;
import org.polarsys.reqcycle.uri.model.ReachableObject;

import com.google.common.base.Function;

public class Object2Reachable implements Function<Object, Reachable> {

	@Inject
	IReachableManager manager;

	public Object2Reachable() {
	}

	@Override
	public Reachable apply(Object arg0) {
		if (arg0 instanceof Reachable) {
			return (Reachable) arg0;
		}
		try {
			IObjectHandler handler = manager.getHandlerFromObject(arg0);
			ReachableObject obj = handler.getFromObject(arg0);
			if (obj != null) {
				return obj.getReachable(arg0);
			}
		} catch (IReachableHandlerException e) {
			e.printStackTrace();
		}
		return null;
	}

}

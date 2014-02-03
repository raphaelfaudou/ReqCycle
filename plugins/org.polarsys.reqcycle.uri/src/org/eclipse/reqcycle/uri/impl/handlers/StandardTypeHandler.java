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
package org.eclipse.reqcycle.uri.impl.handlers;

import java.net.URI;

import org.eclipse.reqcycle.uri.model.IObjectHandler;
import org.eclipse.reqcycle.uri.model.IReachableHandler;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.reqcycle.uri.model.ReachableObject;

public class StandardTypeHandler implements IObjectHandler, IReachableHandler {

	@Override
	public ReachableObject getFromObject(Object object) {
		return new StandardReachableObject(object);
	}

	@Override
	public boolean handlesObject(Object object) {
		if (StandardUtils.arrayToURI(object) != null){
			return true;
		}
		return object instanceof String || object instanceof Integer || object instanceof URI;
	}

	@Override
	public ReachableObject getFromReachable(Reachable t) {
		return new StandardReachableObject(t);
	}

	@Override
	public boolean handlesReachable(Reachable t) {
		return StandardUtils.SCHEME.equals(t.getScheme());
	}

}

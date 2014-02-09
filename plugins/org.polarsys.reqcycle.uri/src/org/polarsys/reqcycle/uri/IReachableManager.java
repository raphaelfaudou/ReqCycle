/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.uri;

import org.polarsys.reqcycle.uri.exceptions.IReachableHandlerException;
import org.polarsys.reqcycle.uri.model.IObjectHandler;
import org.polarsys.reqcycle.uri.model.IReachableHandler;
import org.polarsys.reqcycle.uri.model.Reachable;

public interface IReachableManager {

	/**
	 * Returns a {@link IReachableHandler} from a {@link Reachable}
	 * 
	 * @param t
	 *            the reachable object
	 * @return the {@link IReachableManager}
	 * @throws IReachableHandlerException
	 *             if no {@link IReachableHandler} can handle t
	 */
	IReachableHandler getHandlerFromReachable(Reachable t)
			throws IReachableHandlerException;

	/**
	 * Returns a {@link IReachableHandler} from a {@link Reachable}
	 * 
	 * @param u
	 *            the url
	 * @return the {@link IReachableManager}
	 * @throws IReachableHandlerException
	 *             if no {@link IReachableHandler} can handle u
	 */
	IObjectHandler getHandlerFromObject(Object o)
			throws IReachableHandlerException;

}

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
package org.polarsys.reqcycle.uri;

import java.net.URI;

import org.polarsys.reqcycle.uri.model.Reachable;

public interface IReachableCreator {
	/**
	 * Create a {@link Reachable} from an {@link URI} and an {@link Object}
	 * 
	 * @param uri
	 * @param originalObject
	 * @return
	 */
	Reachable getReachable(URI uri, Object originalObject);

	/**
	 * Create a reachable from an URI
	 * 
	 * @param uri
	 * @return
	 */
	Reachable getReachable(URI uri);
}

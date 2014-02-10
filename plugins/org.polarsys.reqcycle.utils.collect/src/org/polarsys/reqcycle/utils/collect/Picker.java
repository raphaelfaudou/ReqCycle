/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Olivier Melois (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.utils.collect;

import org.polarsys.reqcycle.utils.collect.exceptions.CannotPickException;


/**
 * A picker defines a way to collect children from an element t.he type of children 
 * and the type of parent are not taken into consideration by the interface
 *  meaning that the implementation should make sure that the types are consistent.
 */
public interface Picker<T> {

	/**
	 * @param element
	 *        the element from which the children are to be retrieved
	 * @return
	 *         the children of the elements
	 * @throws Exception
	 */
	public Iterable<T> getNexts(T element) throws CannotPickException;

}

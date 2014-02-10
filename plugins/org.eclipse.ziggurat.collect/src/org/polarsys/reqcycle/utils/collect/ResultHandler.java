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

import org.polarsys.reqcycle.utils.collect.exceptions.CannotHandleException;
import org.polarsys.reqcycle.utils.collect.exceptions.CollectionAbortedException;

/**
 * Defines objects that handle results from a Collector<>, with a function called immediately as
 * each value is gathered.
 */
public interface ResultHandler<T> {

	/**
	 * This method is called by collectors whenever a result is collected.
	 * 
	 * @param value
	 *        The collected result
	 * @throws CollectionAbortedException
	 *         The client code requests that the collection is aborted
	 * @throws CannotHandleException
	 */
	void handleResult(T value) throws CollectionAbortedException, CannotHandleException;
}

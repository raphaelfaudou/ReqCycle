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
package org.polarsys.reqcycle.traceability.types;

import org.polarsys.reqcycle.traceability.model.TType;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.RegisteredAttribute;

/**
 * Manages the different TTypes registered
 * 
 * @author Tristan FAURE
 * 
 */
public interface ITraceTypesManager {
	/**
	 * Returns all the registered types
	 * 
	 * @return an {@link Iterable} of {@link TType}
	 */
	Iterable<TType> getAllTTypes();

	/**
	 * Returns the {@link TType} with the given id
	 * 
	 * @param id
	 * @return
	 */
	TType getTType(String id);

	/**
	 * @param id
	 * @return
	 */
	RegisteredAttribute getAttribute(String id);

	Iterable<RegisteredAttribute> getAllAttributes();

}

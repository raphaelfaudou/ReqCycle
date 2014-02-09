/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.uri.visitors;

import org.eclipse.core.runtime.IAdaptable;
import org.polarsys.reqcycle.uri.model.IReachableHandler;

/**
 * Interface for visiting result of {@link IReachableHandler}
 * 
 * @author tfaure
 * 
 */
public interface IVisitor {

	/**
	 * first method called during visiting
	 * 
	 * @param adapable
	 *            , object adaptable in several classes depending on the caller
	 */
	void start(IAdaptable adaptable);

	/**
	 * Visit for each new element
	 * 
	 * @param o
	 *            the current element
	 * @param adapable
	 *            object adaptable in several classes depending on the caller
	 * @return true if the visit shall continue false otherwise
	 */
	boolean visit(Object o, IAdaptable adaptable);

	/**
	 * last method called during visiting
	 * 
	 * @param adapable
	 *            , object adaptable in several classes depending on the caller
	 */
	void end(IAdaptable adaptable);

}

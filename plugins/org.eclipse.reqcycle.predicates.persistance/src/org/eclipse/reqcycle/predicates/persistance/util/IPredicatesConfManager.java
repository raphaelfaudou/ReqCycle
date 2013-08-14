/*****************************************************************************
 * Copyright (c) 2013 AtoS.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Anass RADOUANI (AtoS) anass.radouani@atos.net - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.reqcycle.predicates.persistance.util;

import java.util.Collection;

import org.eclipse.reqcycle.predicates.core.api.IPredicate;


public interface IPredicatesConfManager {

	/**
	 * Stores a new predicate into the workspace.
	 * 
	 * @param predicate
	 * 
	 * @return <code>true</code> if the persisting operation is done correctly, <code>false</code> otherwise.
	 * 
	 * @see #isPredicateNameAlreadyUsed(String)
	 */
	public boolean storePredicate(final IPredicate predicate);

	/**
	 * Gets the stored predicates
	 * 
	 * @return The stored of predicates
	 */
	public Collection<IPredicate> getPredicates(boolean reload);

	/**
	 * @param predicateName
	 * @return <code>true</code> if the name is already used by another persisted predicate, <code>false</code> otherwise.
	 */
	public boolean isPredicateNameAlreadyUsed(final String predicateName);

	/**
	 * @param predicateName
	 *        - The name of the predicate to retrieve.
	 * @return The stored predicate having the specified name or <code>null</code> if not found or if the specified
	 *         predicate's name is <code>null</code>.
	 */
	public IPredicate getPredicateByName(final String predicateName);

	/**
	 * Removes the first predicate having the specified name.
	 * 
	 * @return <code>true</code> if the removal operation is done correctly, <code>false</code> otherwise.
	 * 
	 * @param predicateName
	 *   
	 */
	public boolean removePredicate(String predicateName);
	
	/**
	 * Removes the predicate
	 * 
	 * @return <code>true</code> if the removal operation is done correctly, <code>false</code> otherwise.
	 * 
	 * @param predicate the predicate to remove
	 *   
	 */
	public boolean removePredicate(IPredicate predicate);
	


}

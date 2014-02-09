/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Papa Issa Diakhate (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
/**
 */
package org.polarsys.reqcycle.predicates.core.api;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>In Predicate</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.polarsys.reqcycle.predicates.core.api.InPredicate#getInput <em>Input</em>}</li>
 * <li>{@link org.polarsys.reqcycle.predicates.core.api.InPredicate#getObjectToSearch <em>Object To Search</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.polarsys.reqcycle.predicates.core.PredicatesPackage#getInPredicate()
 * @model abstract="true"
 * @generated
 */
public interface InPredicate<T> extends IEAttrPredicate {

	/**
	 * Returns the value of the '<em><b>Input</b></em>' attribute list.
	 * The list contents are of type {@link T}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Input</em>' attribute list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Input</em>' attribute list.
	 * @see org.polarsys.reqcycle.predicates.core.PredicatesPackage#getInPredicate_Input()
	 * @model
	 * @generated
	 */
	EList<T> getInput();

	/**
	 * Returns the value of the '<em><b>Object To Search</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Object To Search</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Object To Search</em>' attribute.
	 * @see #setObjectToSearch(Object)
	 * @see org.polarsys.reqcycle.predicates.core.PredicatesPackage#getInPredicate_ObjectToSearch()
	 * @model
	 * @generated
	 */
	Object getObjectToSearch();

	/**
	 * Sets the value of the '{@link org.polarsys.reqcycle.predicates.core.api.InPredicate#getObjectToSearch <em>Object To Search</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value
	 *        the new value of the '<em>Object To Search</em>' attribute.
	 * @see #getObjectToSearch()
	 * @generated
	 */
	void setObjectToSearch(Object value);

} // InPredicate

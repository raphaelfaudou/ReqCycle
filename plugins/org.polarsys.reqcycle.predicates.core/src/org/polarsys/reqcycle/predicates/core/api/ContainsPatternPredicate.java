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

import java.util.regex.Pattern;



/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Contains Pattern Predicate</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.polarsys.reqcycle.predicates.core.api.ContainsPatternPredicate#getInput <em>Input</em>}</li>
 * <li>{@link org.polarsys.reqcycle.predicates.core.api.ContainsPatternPredicate#getExpectedPattern <em>Expected Pattern</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.polarsys.reqcycle.predicates.core.PredicatesPackage#getContainsPatternPredicate()
 * @model
 * @generated
 */
public interface ContainsPatternPredicate extends IEAttrPredicate {

	/**
	 * Returns the value of the '<em><b>Input</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Input</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Input</em>' attribute.
	 * @see #setInput(CharSequence)
	 * @see org.polarsys.reqcycle.predicates.core.PredicatesPackage#getContainsPatternPredicate_Input()
	 * @model dataType="org.polarsys.reqcycle.predicates.core.api.CharSequence"
	 * @generated
	 */
	CharSequence getInput();

	/**
	 * Sets the value of the '{@link org.polarsys.reqcycle.predicates.core.api.ContainsPatternPredicate#getInput <em>Input</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value
	 *        the new value of the '<em>Input</em>' attribute.
	 * @see #getInput()
	 * @generated
	 */
	void setInput(CharSequence value);

	/**
	 * Returns the value of the '<em><b>Expected Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expected Pattern</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Expected Pattern</em>' attribute.
	 * @see #setExpectedPattern(Pattern)
	 * @see org.polarsys.reqcycle.predicates.core.PredicatesPackage#getContainsPatternPredicate_ExpectedPattern()
	 * @model dataType="org.polarsys.reqcycle.predicates.core.api.Pattern"
	 * @generated
	 */
	Pattern getExpectedPattern();

	/**
	 * Sets the value of the '{@link org.polarsys.reqcycle.predicates.core.api.ContainsPatternPredicate#getExpectedPattern <em>Expected Pattern</em>}'
	 * attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value
	 *        the new value of the '<em>Expected Pattern</em>' attribute.
	 * @see #getExpectedPattern()
	 * @generated
	 */
	void setExpectedPattern(Pattern value);
} // ContainsPatternPredicate

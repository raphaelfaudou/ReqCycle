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
package org.polarsys.reqcycle.predicates.core;

import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EFactory;
import org.polarsys.reqcycle.predicates.core.api.AndPredicate;
import org.polarsys.reqcycle.predicates.core.api.BooleanEqualPredicate;
import org.polarsys.reqcycle.predicates.core.api.CompareNumberPredicate;
import org.polarsys.reqcycle.predicates.core.api.ContainsPatternPredicate;
import org.polarsys.reqcycle.predicates.core.api.DateEqualPredicate;
import org.polarsys.reqcycle.predicates.core.api.EnumEqualPredicate;
import org.polarsys.reqcycle.predicates.core.api.EnumIntoPredicate;
import org.polarsys.reqcycle.predicates.core.api.NotPredicate;
import org.polarsys.reqcycle.predicates.core.api.OPERATOR;
import org.polarsys.reqcycle.predicates.core.api.OrPredicate;
import org.polarsys.reqcycle.predicates.core.api.StringEqualPredicate;
import org.polarsys.reqcycle.predicates.core.api.StringIntoPredicate;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * 
 * @see org.polarsys.reqcycle.predicates.core.PredicatesPackage
 * @generated
 */
public interface PredicatesFactory extends EFactory {

	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	PredicatesFactory eINSTANCE = org.polarsys.reqcycle.predicates.core.impl.PredicatesFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>String Equal Predicate</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>String Equal Predicate</em>'.
	 * @generated
	 */
	StringEqualPredicate createStringEqualPredicate();

	/**
	 * Returns a new object of class '<em>Date Equal Predicate</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Date Equal Predicate</em>'.
	 * @generated
	 */
	DateEqualPredicate createDateEqualPredicate();

	/**
	 * Returns a new object of class '<em>Enum Equal Predicate</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Enum Equal Predicate</em>'.
	 * @generated
	 */
	EnumEqualPredicate createEnumEqualPredicate();

	/**
	 * Returns a new object of class '<em>Boolean Equal Predicate</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Boolean Equal Predicate</em>'.
	 * @generated
	 */
	BooleanEqualPredicate createBooleanEqualPredicate();

	/**
	 * Returns a new object of class '<em>Contains Pattern Predicate</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Contains Pattern Predicate</em>'.
	 * @generated
	 */
	ContainsPatternPredicate createContainsPatternPredicate();

	/**
	 * Returns a new object of class '<em>String Into Predicate</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>String Into Predicate</em>'.
	 * @generated
	 */
	StringIntoPredicate createStringIntoPredicate();

	/**
	 * Returns a new object of class '<em>Enum Into Predicate</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Enum Into Predicate</em>'.
	 * @generated
	 */
	EnumIntoPredicate createEnumIntoPredicate();

	/**
	 * Returns a new object of class '<em>And Predicate</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>And Predicate</em>'.
	 * @generated
	 */
	AndPredicate createAndPredicate();

	/**
	 * Returns a new object of class '<em>Or Predicate</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Or Predicate</em>'.
	 * @generated
	 */
	OrPredicate createOrPredicate();

	/**
	 * Returns a new object of class '<em>Compare Number Predicate</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Compare Number Predicate</em>'.
	 * @generated
	 */
	CompareNumberPredicate createCompareNumberPredicate();

	/**
	 * Returns a new object of class '<em>Not Predicate</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Not Predicate</em>'.
	 * @generated
	 */
	NotPredicate createNotPredicate();

	/**
	 * Returns an instance of data type '<em>OPERATOR</em>' corresponding the given literal.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param literal
	 *        a literal of the data type.
	 * @return a new instance value of the data type.
	 * @generated
	 */
	OPERATOR createOPERATOR(String literal);

	/**
	 * Returns a literal representation of an instance of data type '<em>OPERATOR</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param instanceValue
	 *        an instance value of the data type.
	 * @return a literal representation of the instance value.
	 * @generated
	 */
	String convertOPERATOR(OPERATOR instanceValue);

	/**
	 * Returns an instance of data type '<em>Pattern</em>' corresponding the given literal.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param literal
	 *        a literal of the data type.
	 * @return a new instance value of the data type.
	 * @generated
	 */
	Pattern createPattern(String literal);

	/**
	 * Returns a literal representation of an instance of data type '<em>Pattern</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param instanceValue
	 *        an instance value of the data type.
	 * @return a literal representation of the instance value.
	 * @generated
	 */
	String convertPattern(Pattern instanceValue);

	/**
	 * Returns an instance of data type '<em>Char Sequence</em>' corresponding the given literal.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param literal
	 *        a literal of the data type.
	 * @return a new instance value of the data type.
	 * @generated
	 */
	CharSequence createCharSequence(String literal);

	/**
	 * Returns a literal representation of an instance of data type '<em>Char Sequence</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param instanceValue
	 *        an instance value of the data type.
	 * @return a literal representation of the instance value.
	 * @generated
	 */
	String convertCharSequence(CharSequence instanceValue);

	/**
	 * Returns an instance of data type '<em>Number</em>' corresponding the given literal.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param literal
	 *        a literal of the data type.
	 * @return a new instance value of the data type.
	 * @generated
	 */
	Number createNumber(String literal);

	/**
	 * Returns a literal representation of an instance of data type '<em>Number</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param instanceValue
	 *        an instance value of the data type.
	 * @return a literal representation of the instance value.
	 * @generated
	 */
	String convertNumber(Number instanceValue);

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the package supported by this factory.
	 * @generated
	 */
	PredicatesPackage getPredicatesPackage();

} //PredicatesFactory

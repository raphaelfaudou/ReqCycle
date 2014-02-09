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
package org.polarsys.reqcycle.predicates.core.impl;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.reqcycle.predicates.core.PredicatesPackage;
import org.polarsys.reqcycle.predicates.core.api.StringEqualPredicate;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>String Equal Predicate</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 * 
 * @generated
 */
public class StringEqualPredicateImpl extends EqualPredicateImpl<String> implements StringEqualPredicate {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected StringEqualPredicateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PredicatesPackage.Literals.STRING_EQUAL_PREDICATE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * This is specialized for the more specific type known in this context.
	 * 
	 * @generated
	 */
	@Override
	public void setInput(String newInput) {
		super.setInput(newInput);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * This is specialized for the more specific type known in this context.
	 * 
	 * @generated
	 */
	@Override
	public void setExpectedObject(String newExpectedObject) {
		super.setExpectedObject(newExpectedObject);
	}

	@Override
	public boolean match(Object input) {
		Object val = this.getInputValueFromEObject(input);
		if(!(val instanceof String) && val != null) {
			val = val.toString();
		}
		return expectedObject != null && expectedObject.equals(val);
	}

} //StringEqualPredicateImpl

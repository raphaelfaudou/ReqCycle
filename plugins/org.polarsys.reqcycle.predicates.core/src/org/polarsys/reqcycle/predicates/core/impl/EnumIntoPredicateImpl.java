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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.polarsys.reqcycle.predicates.core.PredicatesPackage;
import org.polarsys.reqcycle.predicates.core.api.EnumIntoPredicate;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Enum Into Predicate</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 * 
 * @generated
 */
public class EnumIntoPredicateImpl extends IntoPredicateImpl<String> implements EnumIntoPredicate {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EnumIntoPredicateImpl() {
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
		return PredicatesPackage.Literals.ENUM_INTO_PREDICATE;
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
	 * This is specialized for the more specific element type known in this context.
	 * 
	 * @generated
	 */
	@Override
	public EList<String> getAllowedEntries() {
		if(allowedEntries == null) {
			allowedEntries = new EDataTypeUniqueEList<String>(String.class, this, PredicatesPackage.ENUM_INTO_PREDICATE__ALLOWED_ENTRIES);
		}
		return allowedEntries;
	}

	@Override
	public boolean match(Object input) {
		Object inputValue = this.getInputValueFromEObject(input);
		if(inputValue instanceof EEnumLiteral) {
			return getAllowedEntries().contains(((EEnumLiteral)inputValue).getLiteral());
		}
		return false;
	}

} //EnumIntoPredicateImpl

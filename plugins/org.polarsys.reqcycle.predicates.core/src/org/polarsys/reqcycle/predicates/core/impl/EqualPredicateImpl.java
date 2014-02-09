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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.reqcycle.predicates.core.PredicatesPackage;
import org.polarsys.reqcycle.predicates.core.api.EqualPredicate;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Equal Predicate</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.polarsys.reqcycle.predicates.core.impl.EqualPredicateImpl#getTypedElement <em>Typed Element</em>}</li>
 * <li>{@link org.polarsys.reqcycle.predicates.core.impl.EqualPredicateImpl#getInput <em>Input</em>}</li>
 * <li>{@link org.polarsys.reqcycle.predicates.core.impl.EqualPredicateImpl#getExpectedObject <em>Expected Object</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class EqualPredicateImpl<T extends Object> extends IEAttrPredicateImpl implements EqualPredicate<T> {

	/**
	 * The cached value of the '{@link #getInput() <em>Input</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getInput()
	 * @generated
	 * @ordered
	 */
	protected T input;

	/**
	 * The cached value of the '{@link #getExpectedObject() <em>Expected Object</em>}' attribute.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getExpectedObject()
	 * @generated
	 * @ordered
	 */
	protected T expectedObject;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EqualPredicateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PredicatesPackage.Literals.EQUAL_PREDICATE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public T getExpectedObject() {
		return expectedObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setExpectedObject(T newExpectedObject) {
		T oldExpectedObject = expectedObject;
		expectedObject = newExpectedObject;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PredicatesPackage.EQUAL_PREDICATE__EXPECTED_OBJECT, oldExpectedObject, expectedObject));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean match(Object input) {
		try {
			@SuppressWarnings("unchecked")
			T inputValue = (T)this.getInputValueFromEObject(input);
			return inputValue.equals(getExpectedObject());
		} catch (ClassCastException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public T getInput() {
		return input;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setInput(T newInput) {
		T oldInput = input;
		input = newInput;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PredicatesPackage.EQUAL_PREDICATE__INPUT, oldInput, input));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch(featureID) {
		case PredicatesPackage.EQUAL_PREDICATE__INPUT:
			return getInput();
		case PredicatesPackage.EQUAL_PREDICATE__EXPECTED_OBJECT:
			return getExpectedObject();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch(featureID) {
		case PredicatesPackage.EQUAL_PREDICATE__INPUT:
			setInput((T)newValue);
			return;
		case PredicatesPackage.EQUAL_PREDICATE__EXPECTED_OBJECT:
			setExpectedObject((T)newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch(featureID) {
		case PredicatesPackage.EQUAL_PREDICATE__INPUT:
			setInput((T)null);
			return;
		case PredicatesPackage.EQUAL_PREDICATE__EXPECTED_OBJECT:
			setExpectedObject((T)null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch(featureID) {
		case PredicatesPackage.EQUAL_PREDICATE__INPUT:
			return input != null;
		case PredicatesPackage.EQUAL_PREDICATE__EXPECTED_OBJECT:
			return expectedObject != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if(eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (input: ");
		result.append(input);
		result.append(", expectedObject: ");
		result.append(expectedObject);
		result.append(')');
		return result.toString();
	}

} // EqualPredicateImpl

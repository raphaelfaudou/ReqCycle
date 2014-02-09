/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
/**
 */
package org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.AttributeType;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.StdAttribute;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Std Attribute</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.StdAttributeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.StdAttributeImpl#getPossibleValues <em>Possible Values</em>}</li>
 *   <li>{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.StdAttributeImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StdAttributeImpl extends AttributeImpl implements StdAttribute {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPossibleValues() <em>Possible Values</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPossibleValues()
	 * @generated
	 * @ordered
	 */
	protected EList<String> possibleValues;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final AttributeType TYPE_EDEFAULT = AttributeType.STRING;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected AttributeType type = TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StdAttributeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypeconfigurationPackage.Literals.STD_ATTRIBUTE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypeconfigurationPackage.STD_ATTRIBUTE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getPossibleValues() {
		if (possibleValues == null) {
			possibleValues = new EDataTypeUniqueEList<String>(String.class, this, TypeconfigurationPackage.STD_ATTRIBUTE__POSSIBLE_VALUES);
		}
		return possibleValues;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AttributeType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(AttributeType newType) {
		AttributeType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypeconfigurationPackage.STD_ATTRIBUTE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TypeconfigurationPackage.STD_ATTRIBUTE__NAME:
				return getName();
			case TypeconfigurationPackage.STD_ATTRIBUTE__POSSIBLE_VALUES:
				return getPossibleValues();
			case TypeconfigurationPackage.STD_ATTRIBUTE__TYPE:
				return getType();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TypeconfigurationPackage.STD_ATTRIBUTE__NAME:
				setName((String)newValue);
				return;
			case TypeconfigurationPackage.STD_ATTRIBUTE__POSSIBLE_VALUES:
				getPossibleValues().clear();
				getPossibleValues().addAll((Collection<? extends String>)newValue);
				return;
			case TypeconfigurationPackage.STD_ATTRIBUTE__TYPE:
				setType((AttributeType)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case TypeconfigurationPackage.STD_ATTRIBUTE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case TypeconfigurationPackage.STD_ATTRIBUTE__POSSIBLE_VALUES:
				getPossibleValues().clear();
				return;
			case TypeconfigurationPackage.STD_ATTRIBUTE__TYPE:
				setType(TYPE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TypeconfigurationPackage.STD_ATTRIBUTE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case TypeconfigurationPackage.STD_ATTRIBUTE__POSSIBLE_VALUES:
				return possibleValues != null && !possibleValues.isEmpty();
			case TypeconfigurationPackage.STD_ATTRIBUTE__TYPE:
				return type != TYPE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", possibleValues: ");
		result.append(possibleValues);
		result.append(", type: ");
		result.append(type);
		result.append(')');
		return result.toString();
	}

} //StdAttributeImpl

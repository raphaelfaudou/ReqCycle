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
/**
 */
package org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Std Attribute</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.StdAttribute#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.StdAttribute#getPossibleValues <em>Possible Values</em>}</li>
 *   <li>{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.StdAttribute#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationPackage#getStdAttribute()
 * @model
 * @generated
 */
public interface StdAttribute extends Attribute {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationPackage#getStdAttribute_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.StdAttribute#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Possible Values</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Possible Values</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Possible Values</em>' attribute list.
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationPackage#getStdAttribute_PossibleValues()
	 * @model
	 * @generated
	 */
	EList<String> getPossibleValues();

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.AttributeType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.AttributeType
	 * @see #setType(AttributeType)
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationPackage#getStdAttribute_Type()
	 * @model
	 * @generated
	 */
	AttributeType getType();

	/**
	 * Sets the value of the '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.StdAttribute#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.AttributeType
	 * @see #getType()
	 * @generated
	 */
	void setType(AttributeType value);

} // StdAttribute

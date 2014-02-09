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
package org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.reqcycle.types.IType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Type#getTypeId <em>Type Id</em>}</li>
 *   <li>{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Type#getOutgoings <em>Outgoings</em>}</li>
 *   <li>{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Type#getIncomings <em>Incomings</em>}</li>
 *   <li>{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Type#isIsExtensible <em>Is Extensible</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationPackage#getType()
 * @model
 * @generated
 */
public interface Type extends EObject {
	/**
	 * Returns the value of the '<em><b>Type Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Id</em>' attribute.
	 * @see #setTypeId(String)
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationPackage#getType_TypeId()
	 * @model
	 * @generated
	 */
	String getTypeId();

	/**
	 * Sets the value of the '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Type#getTypeId <em>Type Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Id</em>' attribute.
	 * @see #getTypeId()
	 * @generated
	 */
	void setTypeId(String value);

	/**
	 * Returns the value of the '<em><b>Outgoings</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation}.
	 * It is bidirectional and its opposite is '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getUpstreamType <em>Upstream Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outgoings</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outgoings</em>' reference list.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationPackage#getType_Outgoings()
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getUpstreamType
	 * @model opposite="upstreamType"
	 * @generated
	 */
	EList<Relation> getOutgoings();

	/**
	 * Returns the value of the '<em><b>Incomings</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation}.
	 * It is bidirectional and its opposite is '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getDownstreamType <em>Downstream Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Incomings</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Incomings</em>' reference list.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationPackage#getType_Incomings()
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getDownstreamType
	 * @model opposite="downstreamType"
	 * @generated
	 */
	EList<Relation> getIncomings();

	/**
	 * Returns the value of the '<em><b>Is Extensible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Extensible</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Extensible</em>' attribute.
	 * @see #setIsExtensible(boolean)
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationPackage#getType_IsExtensible()
	 * @model
	 * @generated
	 */
	boolean isIsExtensible();

	/**
	 * Sets the value of the '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Type#isIsExtensible <em>Is Extensible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Extensible</em>' attribute.
	 * @see #isIsExtensible()
	 * @generated
	 */
	void setIsExtensible(boolean value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" dataType="org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.IType"
	 * @generated
	 */
	IType getIType();

} // Type

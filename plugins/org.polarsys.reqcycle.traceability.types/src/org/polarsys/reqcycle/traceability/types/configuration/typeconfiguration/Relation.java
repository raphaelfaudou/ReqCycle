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
package org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.reqcycle.traceability.model.TType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Relation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getUpstreamType <em>Upstream Type</em>}</li>
 *   <li>{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getDownstreamType <em>Downstream Type</em>}</li>
 *   <li>{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getKind <em>Kind</em>}</li>
 *   <li>{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getAgregatedTypes <em>Agregated Types</em>}</li>
 *   <li>{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getAttributes <em>Attributes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationPackage#getRelation()
 * @model
 * @generated
 */
public interface Relation extends EObject {
	/**
	 * Returns the value of the '<em><b>Upstream Type</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Type#getOutgoings <em>Outgoings</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Upstream Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Upstream Type</em>' reference.
	 * @see #setUpstreamType(Type)
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationPackage#getRelation_UpstreamType()
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Type#getOutgoings
	 * @model opposite="outgoings"
	 * @generated
	 */
	Type getUpstreamType();

	/**
	 * Sets the value of the '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getUpstreamType <em>Upstream Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Upstream Type</em>' reference.
	 * @see #getUpstreamType()
	 * @generated
	 */
	void setUpstreamType(Type value);

	/**
	 * Returns the value of the '<em><b>Downstream Type</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Type#getIncomings <em>Incomings</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Downstream Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Downstream Type</em>' reference.
	 * @see #setDownstreamType(Type)
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationPackage#getRelation_DownstreamType()
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Type#getIncomings
	 * @model opposite="incomings"
	 * @generated
	 */
	Type getDownstreamType();

	/**
	 * Sets the value of the '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getDownstreamType <em>Downstream Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Downstream Type</em>' reference.
	 * @see #getDownstreamType()
	 * @generated
	 */
	void setDownstreamType(Type value);

	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see #setKind(String)
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationPackage#getRelation_Kind()
	 * @model
	 * @generated
	 */
	String getKind();

	/**
	 * Sets the value of the '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see #getKind()
	 * @generated
	 */
	void setKind(String value);

	/**
	 * Returns the value of the '<em><b>Agregated Types</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Agregated Types</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Agregated Types</em>' attribute list.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationPackage#getRelation_AgregatedTypes()
	 * @model
	 * @generated
	 */
	EList<String> getAgregatedTypes();

	/**
	 * Returns the value of the '<em><b>Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Icon</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Icon</em>' attribute.
	 * @see #setIcon(String)
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationPackage#getRelation_Icon()
	 * @model
	 * @generated
	 */
	String getIcon();

	/**
	 * Sets the value of the '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getIcon <em>Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Icon</em>' attribute.
	 * @see #getIcon()
	 * @generated
	 */
	void setIcon(String value);

	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Attribute}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' containment reference list.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationPackage#getRelation_Attributes()
	 * @model containment="true"
	 * @generated
	 */
	EList<Attribute> getAttributes();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" dataType="org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TType"
	 * @generated
	 */
	EList<TType> getAgregated();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" dataType="org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TType"
	 * @generated
	 */
	TType getTType();

} // Relation

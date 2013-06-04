/**
 */
package org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Relation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getUpstreamType <em>Upstream Type</em>}</li>
 *   <li>{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getDownstreamType <em>Downstream Type</em>}</li>
 *   <li>{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getKind <em>Kind</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationPackage#getRelation()
 * @model
 * @generated
 */
public interface Relation extends EObject {
	/**
	 * Returns the value of the '<em><b>Upstream Type</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Type#getOutgoings <em>Outgoings</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Upstream Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Upstream Type</em>' reference.
	 * @see #setUpstreamType(Type)
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationPackage#getRelation_UpstreamType()
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Type#getOutgoings
	 * @model opposite="outgoings"
	 * @generated
	 */
	Type getUpstreamType();

	/**
	 * Sets the value of the '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getUpstreamType <em>Upstream Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Upstream Type</em>' reference.
	 * @see #getUpstreamType()
	 * @generated
	 */
	void setUpstreamType(Type value);

	/**
	 * Returns the value of the '<em><b>Downstream Type</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Type#getIncomings <em>Incomings</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Downstream Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Downstream Type</em>' reference.
	 * @see #setDownstreamType(Type)
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationPackage#getRelation_DownstreamType()
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Type#getIncomings
	 * @model opposite="incomings"
	 * @generated
	 */
	Type getDownstreamType();

	/**
	 * Sets the value of the '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getDownstreamType <em>Downstream Type</em>}' reference.
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
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationPackage#getRelation_Kind()
	 * @model
	 * @generated
	 */
	String getKind();

	/**
	 * Sets the value of the '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see #getKind()
	 * @generated
	 */
	void setKind(String value);

} // Relation

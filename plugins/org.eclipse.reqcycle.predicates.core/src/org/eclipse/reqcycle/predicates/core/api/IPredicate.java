/**
 */
package org.eclipse.reqcycle.predicates.core.api;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IPredicate</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.reqcycle.predicates.core.api.IPredicate#getDisplayName <em>Display Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.reqcycle.predicates.core.PredicatesPackage#getIPredicate()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface IPredicate extends EObject {
    /**
     * Returns the value of the '<em><b>Display Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Display Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Display Name</em>' attribute.
     * @see #setDisplayName(String)
     * @see org.eclipse.reqcycle.predicates.core.PredicatesPackage#getIPredicate_DisplayName()
     * @model
     * @generated
     */
    String getDisplayName();

    /**
     * Sets the value of the '{@link org.eclipse.reqcycle.predicates.core.api.IPredicate#getDisplayName <em>Display Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Display Name</em>' attribute.
     * @see #getDisplayName()
     * @generated
     */
    void setDisplayName(String value);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model
     * @generated
     */
    boolean match(Object input);

} // IPredicate

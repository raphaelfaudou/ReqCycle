/**
 */
package org.eclipse.reqcycle.predicates.persistance.api;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.reqcycle.predicates.core.api.IPredicate;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Predicates Conf</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.reqcycle.predicates.persistance.api.PredicatesConf#getPredicates <em>Predicates</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.reqcycle.predicates.persistance.PredicatesConfPackage#getPredicatesConf()
 * @model
 * @generated
 */
public interface PredicatesConf extends EObject {
    /**
     * Returns the value of the '<em><b>Predicates</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.reqcycle.predicates.core.api.IPredicate}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Predicates</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Predicates</em>' containment reference list.
     * @see org.eclipse.reqcycle.predicates.persistance.PredicatesConfPackage#getPredicatesConf_Predicates()
     * @model containment="true"
     * @generated
     */
    EList<IPredicate> getPredicates();

} // PredicatesConf

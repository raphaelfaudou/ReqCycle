/**
 */
package org.eclipse.reqcycle.predicates.core.api;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Into Predicate</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.reqcycle.predicates.core.api.IntoPredicate#getInput <em>Input</em>}</li>
 *   <li>{@link org.eclipse.reqcycle.predicates.core.api.IntoPredicate#getAllowedEntries <em>Allowed Entries</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.reqcycle.predicates.core.PredicatesPackage#getIntoPredicate()
 * @model abstract="true"
 * @generated
 */
public interface IntoPredicate<T> extends IEAttrPredicate {
    /**
     * Returns the value of the '<em><b>Input</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Input</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Input</em>' attribute.
     * @see #setInput(Object)
     * @see org.eclipse.reqcycle.predicates.core.PredicatesPackage#getIntoPredicate_Input()
     * @model
     * @generated
     */
    T getInput();

    /**
     * Sets the value of the '{@link org.eclipse.reqcycle.predicates.core.api.IntoPredicate#getInput <em>Input</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Input</em>' attribute.
     * @see #getInput()
     * @generated
     */
    void setInput(T value);

    /**
     * Returns the value of the '<em><b>Allowed Entries</b></em>' attribute list.
     * The list contents are of type {@link T}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Allowed Entries</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Allowed Entries</em>' attribute list.
     * @see org.eclipse.reqcycle.predicates.core.PredicatesPackage#getIntoPredicate_AllowedEntries()
     * @model
     * @generated
     */
    EList<T> getAllowedEntries();

} // IntoPredicate

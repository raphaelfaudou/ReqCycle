/**
 */
package org.eclipse.reqcycle.predicates.core.api;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Compare Number Predicate</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.reqcycle.predicates.core.api.CompareNumberPredicate#getInput <em>Input</em>}</li>
 *   <li>{@link org.eclipse.reqcycle.predicates.core.api.CompareNumberPredicate#getExpectedValue <em>Expected Value</em>}</li>
 *   <li>{@link org.eclipse.reqcycle.predicates.core.api.CompareNumberPredicate#getOperator <em>Operator</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.reqcycle.predicates.core.PredicatesPackage#getCompareNumberPredicate()
 * @model
 * @generated
 */
public interface CompareNumberPredicate extends IEAttrPredicate {
    /**
     * Returns the value of the '<em><b>Input</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Input</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Input</em>' attribute.
     * @see #setInput(Number)
     * @see org.eclipse.reqcycle.predicates.core.PredicatesPackage#getCompareNumberPredicate_Input()
     * @model dataType="org.eclipse.reqcycle.predicates.core.api.Number"
     * @generated
     */
    Number getInput();

    /**
     * Sets the value of the '{@link org.eclipse.reqcycle.predicates.core.api.CompareNumberPredicate#getInput <em>Input</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Input</em>' attribute.
     * @see #getInput()
     * @generated
     */
    void setInput(Number value);

    /**
     * Returns the value of the '<em><b>Expected Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Expected Value</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Expected Value</em>' attribute.
     * @see #setExpectedValue(Number)
     * @see org.eclipse.reqcycle.predicates.core.PredicatesPackage#getCompareNumberPredicate_ExpectedValue()
     * @model dataType="org.eclipse.reqcycle.predicates.core.api.Number"
     * @generated
     */
    Number getExpectedValue();

    /**
     * Sets the value of the '{@link org.eclipse.reqcycle.predicates.core.api.CompareNumberPredicate#getExpectedValue <em>Expected Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Expected Value</em>' attribute.
     * @see #getExpectedValue()
     * @generated
     */
    void setExpectedValue(Number value);

    /**
     * Returns the value of the '<em><b>Operator</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.reqcycle.predicates.core.api.OPERATOR}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Operator</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Operator</em>' attribute.
     * @see org.eclipse.reqcycle.predicates.core.api.OPERATOR
     * @see #setOperator(OPERATOR)
     * @see org.eclipse.reqcycle.predicates.core.PredicatesPackage#getCompareNumberPredicate_Operator()
     * @model
     * @generated
     */
    OPERATOR getOperator();

    /**
     * Sets the value of the '{@link org.eclipse.reqcycle.predicates.core.api.CompareNumberPredicate#getOperator <em>Operator</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Operator</em>' attribute.
     * @see org.eclipse.reqcycle.predicates.core.api.OPERATOR
     * @see #getOperator()
     * @generated
     */
    void setOperator(OPERATOR value);

} // CompareNumberPredicate

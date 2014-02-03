/**
 */
package org.polarsys.reqcycle.predicates.core.api;



/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Equal Predicate</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.polarsys.reqcycle.predicates.core.api.EqualPredicate#getInput <em>Input</em>}</li>
 * <li>{@link org.polarsys.reqcycle.predicates.core.api.EqualPredicate#getExpectedObject <em>Expected Object</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.polarsys.reqcycle.predicates.core.PredicatesPackage#getEqualPredicate()
 * @model abstract="true" TBounds="org.eclipse.emf.ecore.EJavaObject"
 * @generated
 */
public interface EqualPredicate<T extends Object> extends IEAttrPredicate {

	/**
	 * Returns the value of the '<em><b>Input</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Input</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Input</em>' attribute.
	 * @see #setInput(Object)
	 * @see org.polarsys.reqcycle.predicates.core.PredicatesPackage#getEqualPredicate_Input()
	 * @model
	 * @generated
	 */
	T getInput();

	/**
	 * Sets the value of the '{@link org.polarsys.reqcycle.predicates.core.api.EqualPredicate#getInput <em>Input</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value
	 *        the new value of the '<em>Input</em>' attribute.
	 * @see #getInput()
	 * @generated
	 */
	void setInput(T value);

	/**
	 * Returns the value of the '<em><b>Expected Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expected Object</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Expected Object</em>' attribute.
	 * @see #setExpectedObject(Object)
	 * @see org.polarsys.reqcycle.predicates.core.PredicatesPackage#getEqualPredicate_ExpectedObject()
	 * @model
	 * @generated
	 */
	T getExpectedObject();

	/**
	 * Sets the value of the '{@link org.polarsys.reqcycle.predicates.core.api.EqualPredicate#getExpectedObject <em>Expected Object</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value
	 *        the new value of the '<em>Expected Object</em>' attribute.
	 * @see #getExpectedObject()
	 * @generated
	 */
	void setExpectedObject(T value);

} // EqualPredicate

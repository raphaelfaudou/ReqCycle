/**
 */
package org.eclipse.reqcycle.predicates.core.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.reqcycle.predicates.core.PredicatesPackage;
import org.eclipse.reqcycle.predicates.core.api.BooleanEqualPredicate;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Boolean Equal Predicate</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 * 
 * @generated
 */
public class BooleanEqualPredicateImpl extends EqualPredicateImpl<Boolean> implements BooleanEqualPredicate {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected BooleanEqualPredicateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PredicatesPackage.Literals.BOOLEAN_EQUAL_PREDICATE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * This is specialized for the more specific type known in this context.
	 * 
	 * @generated
	 */
	@Override
	public void setInput(Boolean newInput) {
		super.setInput(newInput);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * This is specialized for the more specific type known in this context.
	 * 
	 * @generated
	 */
	@Override
	public void setExpectedObject(Boolean newExpectedObject) {
		super.setExpectedObject(newExpectedObject);
	}

} //BooleanEqualPredicateImpl

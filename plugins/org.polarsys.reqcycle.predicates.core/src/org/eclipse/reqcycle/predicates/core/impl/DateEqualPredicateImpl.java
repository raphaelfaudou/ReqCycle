/**
 */
package org.eclipse.reqcycle.predicates.core.impl;

import java.util.Date;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.reqcycle.predicates.core.PredicatesPackage;
import org.eclipse.reqcycle.predicates.core.api.DateEqualPredicate;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Date Equal Predicate</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 * 
 * @generated
 */
public class DateEqualPredicateImpl extends EqualPredicateImpl<Date> implements DateEqualPredicate {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected DateEqualPredicateImpl() {
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
		return PredicatesPackage.Literals.DATE_EQUAL_PREDICATE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * This is specialized for the more specific type known in this context.
	 * 
	 * @generated
	 */
	@Override
	public void setInput(Date newInput) {
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
	public void setExpectedObject(Date newExpectedObject) {
		super.setExpectedObject(newExpectedObject);
	}

} //DateEqualPredicateImpl

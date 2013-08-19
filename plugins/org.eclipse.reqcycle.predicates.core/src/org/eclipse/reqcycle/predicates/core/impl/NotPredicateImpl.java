/**
 */
package org.eclipse.reqcycle.predicates.core.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.reqcycle.predicates.core.PredicatesPackage;
import org.eclipse.reqcycle.predicates.core.api.NotPredicate;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Not Predicate</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 * 
 * @generated
 */
public class NotPredicateImpl extends CompositePredicateImpl implements NotPredicate {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected NotPredicateImpl() {
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
		return PredicatesPackage.Literals.NOT_PREDICATE;
	}

	@Override
	public boolean match(Object input) {
		return !this.getPredicates().get(0).match(input);
	}

} //NotPredicateImpl

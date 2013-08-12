/**
 */
package org.eclipse.reqcycle.predicates.core.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.reqcycle.predicates.core.PredicatesPackage;
import org.eclipse.reqcycle.predicates.core.api.IPredicate;
import org.eclipse.reqcycle.predicates.core.api.OrPredicate;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Or Predicate</b></em>'. <!-- end-user-doc -->
 * <p>
 * </p>
 * 
 * @generated
 */
public class OrPredicateImpl extends CompositePredicateImpl implements OrPredicate {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected OrPredicateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PredicatesPackage.Literals.OR_PREDICATE;
	}

	@Override
	public boolean match(Object input) {
		for(IPredicate predicate : this.getPredicates()) {
			if(predicate.match(input))
				return true;
		}
		return false;
	}

} // OrPredicateImpl

/**
 */
package org.eclipse.reqcycle.predicates.core.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.reqcycle.predicates.core.PredicatesPackage;
import org.eclipse.reqcycle.predicates.core.api.AndPredicate;
import org.eclipse.reqcycle.predicates.core.api.IPredicate;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>And Predicate</b></em>'. <!-- end-user-doc -->
 * <p>
 * </p>
 * 
 * @generated
 */
public class AndPredicateImpl extends CompositePredicateImpl implements AndPredicate {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected AndPredicateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PredicatesPackage.Literals.AND_PREDICATE;
	}

	@Override
	public boolean match(Object input) {
		for(IPredicate predicate : this.getPredicates()) {
			if(!predicate.match(input))
				return false;
		}
		return true;
	}

} // AndPredicateImpl

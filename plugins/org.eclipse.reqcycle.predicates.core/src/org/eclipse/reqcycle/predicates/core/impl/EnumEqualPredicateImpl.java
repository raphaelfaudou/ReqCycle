/**
 */
package org.eclipse.reqcycle.predicates.core.impl;

import org.eclipse.emf.common.util.Enumerator;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.reqcycle.predicates.core.PredicatesPackage;

import org.eclipse.reqcycle.predicates.core.api.EnumEqualPredicate;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Enum Equal Predicate</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class EnumEqualPredicateImpl extends EqualPredicateImpl<Enumerator> implements EnumEqualPredicate {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EnumEqualPredicateImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PredicatesPackage.Literals.ENUM_EQUAL_PREDICATE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * This is specialized for the more specific type known in this context.
     * @generated
     */
    @Override
    public void setInput(Enumerator newInput) {
        super.setInput(newInput);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * This is specialized for the more specific type known in this context.
     * @generated
     */
    @Override
    public void setExpectedObject(Enumerator newExpectedObject) {
        super.setExpectedObject(newExpectedObject);
    }

} //EnumEqualPredicateImpl

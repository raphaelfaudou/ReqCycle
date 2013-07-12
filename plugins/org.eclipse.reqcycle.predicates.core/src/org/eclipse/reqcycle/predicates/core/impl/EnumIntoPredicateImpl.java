/**
 */
package org.eclipse.reqcycle.predicates.core.impl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.Enumerator;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

import org.eclipse.reqcycle.predicates.core.PredicatesPackage;

import org.eclipse.reqcycle.predicates.core.api.EnumIntoPredicate;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Enum Into Predicate</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class EnumIntoPredicateImpl extends IntoPredicateImpl<Enumerator> implements EnumIntoPredicate {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EnumIntoPredicateImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PredicatesPackage.Literals.ENUM_INTO_PREDICATE;
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
     * This is specialized for the more specific element type known in this context.
     * @generated
     */
    @Override
    public EList<Enumerator> getAllowedEntries() {
        if (allowedEntries == null) {
            allowedEntries = new EDataTypeUniqueEList<Enumerator>(Enumerator.class, this, PredicatesPackage.ENUM_INTO_PREDICATE__ALLOWED_ENTRIES);
        }
        return allowedEntries;
    }

} //EnumIntoPredicateImpl

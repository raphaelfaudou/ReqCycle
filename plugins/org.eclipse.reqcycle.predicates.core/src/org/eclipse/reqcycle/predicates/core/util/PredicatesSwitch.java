/**
 */
package org.eclipse.reqcycle.predicates.core.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.eclipse.reqcycle.predicates.core.PredicatesPackage;

import org.eclipse.reqcycle.predicates.core.api.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.reqcycle.predicates.core.PredicatesPackage
 * @generated
 */
public class PredicatesSwitch<T1> extends Switch<T1> {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static PredicatesPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PredicatesSwitch() {
        if (modelPackage == null) {
            modelPackage = PredicatesPackage.eINSTANCE;
        }
    }

    /**
     * Checks whether this is a switch for the given package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @parameter ePackage the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
    @Override
    protected boolean isSwitchFor(EPackage ePackage) {
        return ePackage == modelPackage;
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    @Override
    protected T1 doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case PredicatesPackage.IPREDICATE: {
                IPredicate iPredicate = (IPredicate)theEObject;
                T1 result = caseIPredicate(iPredicate);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PredicatesPackage.COMPOSITE_PREDICATE: {
                CompositePredicate compositePredicate = (CompositePredicate)theEObject;
                T1 result = caseCompositePredicate(compositePredicate);
                if (result == null) result = caseIPredicate(compositePredicate);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PredicatesPackage.CUSTOM_PREDICATE: {
                CustomPredicate customPredicate = (CustomPredicate)theEObject;
                T1 result = caseCustomPredicate(customPredicate);
                if (result == null) result = caseIEAttrPredicate(customPredicate);
                if (result == null) result = caseITypedPredicate(customPredicate);
                if (result == null) result = caseIPredicate(customPredicate);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PredicatesPackage.EQUAL_PREDICATE: {
                EqualPredicate<?> equalPredicate = (EqualPredicate<?>)theEObject;
                T1 result = caseEqualPredicate(equalPredicate);
                if (result == null) result = caseIEAttrPredicate(equalPredicate);
                if (result == null) result = caseITypedPredicate(equalPredicate);
                if (result == null) result = caseIPredicate(equalPredicate);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PredicatesPackage.STRING_EQUAL_PREDICATE: {
                StringEqualPredicate stringEqualPredicate = (StringEqualPredicate)theEObject;
                T1 result = caseStringEqualPredicate(stringEqualPredicate);
                if (result == null) result = caseEqualPredicate(stringEqualPredicate);
                if (result == null) result = caseIEAttrPredicate(stringEqualPredicate);
                if (result == null) result = caseITypedPredicate(stringEqualPredicate);
                if (result == null) result = caseIPredicate(stringEqualPredicate);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PredicatesPackage.DATE_EQUAL_PREDICATE: {
                DateEqualPredicate dateEqualPredicate = (DateEqualPredicate)theEObject;
                T1 result = caseDateEqualPredicate(dateEqualPredicate);
                if (result == null) result = caseEqualPredicate(dateEqualPredicate);
                if (result == null) result = caseIEAttrPredicate(dateEqualPredicate);
                if (result == null) result = caseITypedPredicate(dateEqualPredicate);
                if (result == null) result = caseIPredicate(dateEqualPredicate);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PredicatesPackage.ENUM_EQUAL_PREDICATE: {
                EnumEqualPredicate enumEqualPredicate = (EnumEqualPredicate)theEObject;
                T1 result = caseEnumEqualPredicate(enumEqualPredicate);
                if (result == null) result = caseEqualPredicate(enumEqualPredicate);
                if (result == null) result = caseIEAttrPredicate(enumEqualPredicate);
                if (result == null) result = caseITypedPredicate(enumEqualPredicate);
                if (result == null) result = caseIPredicate(enumEqualPredicate);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PredicatesPackage.BOOLEAN_EQUAL_PREDICATE: {
                BooleanEqualPredicate booleanEqualPredicate = (BooleanEqualPredicate)theEObject;
                T1 result = caseBooleanEqualPredicate(booleanEqualPredicate);
                if (result == null) result = caseEqualPredicate(booleanEqualPredicate);
                if (result == null) result = caseIEAttrPredicate(booleanEqualPredicate);
                if (result == null) result = caseITypedPredicate(booleanEqualPredicate);
                if (result == null) result = caseIPredicate(booleanEqualPredicate);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PredicatesPackage.CONTAINS_PATTERN_PREDICATE: {
                ContainsPatternPredicate containsPatternPredicate = (ContainsPatternPredicate)theEObject;
                T1 result = caseContainsPatternPredicate(containsPatternPredicate);
                if (result == null) result = caseIEAttrPredicate(containsPatternPredicate);
                if (result == null) result = caseITypedPredicate(containsPatternPredicate);
                if (result == null) result = caseIPredicate(containsPatternPredicate);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PredicatesPackage.INTO_PREDICATE: {
                IntoPredicate<?> intoPredicate = (IntoPredicate<?>)theEObject;
                T1 result = caseIntoPredicate(intoPredicate);
                if (result == null) result = caseIEAttrPredicate(intoPredicate);
                if (result == null) result = caseITypedPredicate(intoPredicate);
                if (result == null) result = caseIPredicate(intoPredicate);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PredicatesPackage.STRING_INTO_PREDICATE: {
                StringIntoPredicate stringIntoPredicate = (StringIntoPredicate)theEObject;
                T1 result = caseStringIntoPredicate(stringIntoPredicate);
                if (result == null) result = caseIntoPredicate(stringIntoPredicate);
                if (result == null) result = caseIEAttrPredicate(stringIntoPredicate);
                if (result == null) result = caseITypedPredicate(stringIntoPredicate);
                if (result == null) result = caseIPredicate(stringIntoPredicate);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PredicatesPackage.ENUM_INTO_PREDICATE: {
                EnumIntoPredicate enumIntoPredicate = (EnumIntoPredicate)theEObject;
                T1 result = caseEnumIntoPredicate(enumIntoPredicate);
                if (result == null) result = caseIntoPredicate(enumIntoPredicate);
                if (result == null) result = caseIEAttrPredicate(enumIntoPredicate);
                if (result == null) result = caseITypedPredicate(enumIntoPredicate);
                if (result == null) result = caseIPredicate(enumIntoPredicate);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PredicatesPackage.AND_PREDICATE: {
                AndPredicate andPredicate = (AndPredicate)theEObject;
                T1 result = caseAndPredicate(andPredicate);
                if (result == null) result = caseCompositePredicate(andPredicate);
                if (result == null) result = caseIPredicate(andPredicate);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PredicatesPackage.OR_PREDICATE: {
                OrPredicate orPredicate = (OrPredicate)theEObject;
                T1 result = caseOrPredicate(orPredicate);
                if (result == null) result = caseCompositePredicate(orPredicate);
                if (result == null) result = caseIPredicate(orPredicate);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PredicatesPackage.NOT_PREDICATE: {
                NotPredicate notPredicate = (NotPredicate)theEObject;
                T1 result = caseNotPredicate(notPredicate);
                if (result == null) result = caseCompositePredicate(notPredicate);
                if (result == null) result = caseIPredicate(notPredicate);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PredicatesPackage.COMPARE_NUMBER_PREDICATE: {
                CompareNumberPredicate compareNumberPredicate = (CompareNumberPredicate)theEObject;
                T1 result = caseCompareNumberPredicate(compareNumberPredicate);
                if (result == null) result = caseIEAttrPredicate(compareNumberPredicate);
                if (result == null) result = caseITypedPredicate(compareNumberPredicate);
                if (result == null) result = caseIPredicate(compareNumberPredicate);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PredicatesPackage.ITYPED_PREDICATE: {
                ITypedPredicate<?> iTypedPredicate = (ITypedPredicate<?>)theEObject;
                T1 result = caseITypedPredicate(iTypedPredicate);
                if (result == null) result = caseIPredicate(iTypedPredicate);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PredicatesPackage.IE_ATTR_PREDICATE: {
                IEAttrPredicate ieAttrPredicate = (IEAttrPredicate)theEObject;
                T1 result = caseIEAttrPredicate(ieAttrPredicate);
                if (result == null) result = caseITypedPredicate(ieAttrPredicate);
                if (result == null) result = caseIPredicate(ieAttrPredicate);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PredicatesPackage.IE_CLASSIFIER_PREDICATE: {
                IEClassifierPredicate ieClassifierPredicate = (IEClassifierPredicate)theEObject;
                T1 result = caseIEClassifierPredicate(ieClassifierPredicate);
                if (result == null) result = caseITypedPredicate(ieClassifierPredicate);
                if (result == null) result = caseIPredicate(ieClassifierPredicate);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>IPredicate</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>IPredicate</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseIPredicate(IPredicate object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Composite Predicate</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Composite Predicate</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseCompositePredicate(CompositePredicate object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Custom Predicate</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Custom Predicate</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseCustomPredicate(CustomPredicate object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Equal Predicate</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Equal Predicate</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public <T extends Object> T1 caseEqualPredicate(EqualPredicate<T> object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>String Equal Predicate</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>String Equal Predicate</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseStringEqualPredicate(StringEqualPredicate object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Date Equal Predicate</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Date Equal Predicate</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseDateEqualPredicate(DateEqualPredicate object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Enum Equal Predicate</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Enum Equal Predicate</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseEnumEqualPredicate(EnumEqualPredicate object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Boolean Equal Predicate</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Boolean Equal Predicate</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseBooleanEqualPredicate(BooleanEqualPredicate object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Contains Pattern Predicate</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Contains Pattern Predicate</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseContainsPatternPredicate(ContainsPatternPredicate object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Into Predicate</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Into Predicate</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public <T> T1 caseIntoPredicate(IntoPredicate<T> object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>String Into Predicate</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>String Into Predicate</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseStringIntoPredicate(StringIntoPredicate object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Enum Into Predicate</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Enum Into Predicate</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseEnumIntoPredicate(EnumIntoPredicate object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>And Predicate</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>And Predicate</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseAndPredicate(AndPredicate object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Or Predicate</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Or Predicate</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseOrPredicate(OrPredicate object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Compare Number Predicate</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Compare Number Predicate</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseCompareNumberPredicate(CompareNumberPredicate object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>ITyped Predicate</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>ITyped Predicate</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public <T> T1 caseITypedPredicate(ITypedPredicate<T> object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>IE Attr Predicate</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>IE Attr Predicate</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseIEAttrPredicate(IEAttrPredicate object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>IE Classifier Predicate</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>IE Classifier Predicate</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseIEClassifierPredicate(IEClassifierPredicate object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Not Predicate</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Not Predicate</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseNotPredicate(NotPredicate object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    @Override
    public T1 defaultCase(EObject object) {
        return null;
    }

} //PredicatesSwitch

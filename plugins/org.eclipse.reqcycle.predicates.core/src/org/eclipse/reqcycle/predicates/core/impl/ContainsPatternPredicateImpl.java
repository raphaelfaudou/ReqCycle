/**
 */
package org.eclipse.reqcycle.predicates.core.impl;

import java.lang.CharSequence;
import java.util.regex.Pattern;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.reqcycle.predicates.core.PredicatesPackage;
import org.eclipse.reqcycle.predicates.core.api.ContainsPatternPredicate;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Contains Pattern Predicate</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.reqcycle.predicates.core.impl.ContainsPatternPredicateImpl#getInput <em>Input</em>}</li>
 *   <li>{@link org.eclipse.reqcycle.predicates.core.impl.ContainsPatternPredicateImpl#getExpectedPattern <em>Expected Pattern</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ContainsPatternPredicateImpl extends IEAttrPredicateImpl implements ContainsPatternPredicate {
    /**
     * The default value of the '{@link #getInput() <em>Input</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInput()
     * @generated
     * @ordered
     */
    protected static final CharSequence INPUT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getInput() <em>Input</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInput()
     * @generated
     * @ordered
     */
    protected CharSequence input = INPUT_EDEFAULT;

    /**
     * The default value of the '{@link #getExpectedPattern() <em>Expected Pattern</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExpectedPattern()
     * @generated
     * @ordered
     */
    protected static final Pattern EXPECTED_PATTERN_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getExpectedPattern() <em>Expected Pattern</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExpectedPattern()
     * @generated
     * @ordered
     */
    protected Pattern expectedPattern = EXPECTED_PATTERN_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ContainsPatternPredicateImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PredicatesPackage.Literals.CONTAINS_PATTERN_PREDICATE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CharSequence getInput() {
        return input;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInput(CharSequence newInput) {
        CharSequence oldInput = input;
        input = newInput;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PredicatesPackage.CONTAINS_PATTERN_PREDICATE__INPUT, oldInput, input));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Pattern getExpectedPattern() {
        return expectedPattern;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setExpectedPattern(Pattern newExpectedPattern) {
        Pattern oldExpectedPattern = expectedPattern;
        expectedPattern = newExpectedPattern;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PredicatesPackage.CONTAINS_PATTERN_PREDICATE__EXPECTED_PATTERN, oldExpectedPattern, expectedPattern));
    }
    
    @Override
    public boolean match(Object input) {
        try {
           CharSequence inputValue = (CharSequence) this.getInputValueFromEObject(input);
           return this.getExpectedPattern().matcher(inputValue).find();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case PredicatesPackage.CONTAINS_PATTERN_PREDICATE__INPUT:
                return getInput();
            case PredicatesPackage.CONTAINS_PATTERN_PREDICATE__EXPECTED_PATTERN:
                return getExpectedPattern();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case PredicatesPackage.CONTAINS_PATTERN_PREDICATE__INPUT:
                setInput((CharSequence)newValue);
                return;
            case PredicatesPackage.CONTAINS_PATTERN_PREDICATE__EXPECTED_PATTERN:
                setExpectedPattern((Pattern)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case PredicatesPackage.CONTAINS_PATTERN_PREDICATE__INPUT:
                setInput(INPUT_EDEFAULT);
                return;
            case PredicatesPackage.CONTAINS_PATTERN_PREDICATE__EXPECTED_PATTERN:
                setExpectedPattern(EXPECTED_PATTERN_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case PredicatesPackage.CONTAINS_PATTERN_PREDICATE__INPUT:
                return INPUT_EDEFAULT == null ? input != null : !INPUT_EDEFAULT.equals(input);
            case PredicatesPackage.CONTAINS_PATTERN_PREDICATE__EXPECTED_PATTERN:
                return EXPECTED_PATTERN_EDEFAULT == null ? expectedPattern != null : !EXPECTED_PATTERN_EDEFAULT.equals(expectedPattern);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (input: ");
        result.append(input);
        result.append(", expectedPattern: ");
        result.append(expectedPattern);
        result.append(')');
        return result.toString();
    }

} //ContainsPatternPredicateImpl

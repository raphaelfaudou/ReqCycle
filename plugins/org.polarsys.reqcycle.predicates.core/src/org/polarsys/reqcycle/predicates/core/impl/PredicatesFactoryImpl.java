/**
 */
package org.polarsys.reqcycle.predicates.core.impl;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.polarsys.reqcycle.predicates.core.PredicatesFactory;
import org.polarsys.reqcycle.predicates.core.PredicatesPackage;
import org.polarsys.reqcycle.predicates.core.api.AndPredicate;
import org.polarsys.reqcycle.predicates.core.api.BooleanEqualPredicate;
import org.polarsys.reqcycle.predicates.core.api.CompareNumberPredicate;
import org.polarsys.reqcycle.predicates.core.api.ContainsPatternPredicate;
import org.polarsys.reqcycle.predicates.core.api.DateEqualPredicate;
import org.polarsys.reqcycle.predicates.core.api.EnumEqualPredicate;
import org.polarsys.reqcycle.predicates.core.api.EnumIntoPredicate;
import org.polarsys.reqcycle.predicates.core.api.NotPredicate;
import org.polarsys.reqcycle.predicates.core.api.OPERATOR;
import org.polarsys.reqcycle.predicates.core.api.OrPredicate;
import org.polarsys.reqcycle.predicates.core.api.StringEqualPredicate;
import org.polarsys.reqcycle.predicates.core.api.StringIntoPredicate;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class PredicatesFactoryImpl extends EFactoryImpl implements PredicatesFactory {

	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static PredicatesFactory init() {
		try {
			PredicatesFactory thePredicatesFactory = (PredicatesFactory)EPackage.Registry.INSTANCE.getEFactory(PredicatesPackage.eNS_URI);
			if(thePredicatesFactory != null) {
				return thePredicatesFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new PredicatesFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PredicatesFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch(eClass.getClassifierID()) {
		case PredicatesPackage.STRING_EQUAL_PREDICATE:
			return createStringEqualPredicate();
		case PredicatesPackage.DATE_EQUAL_PREDICATE:
			return createDateEqualPredicate();
		case PredicatesPackage.ENUM_EQUAL_PREDICATE:
			return createEnumEqualPredicate();
		case PredicatesPackage.BOOLEAN_EQUAL_PREDICATE:
			return createBooleanEqualPredicate();
		case PredicatesPackage.CONTAINS_PATTERN_PREDICATE:
			return createContainsPatternPredicate();
		case PredicatesPackage.STRING_INTO_PREDICATE:
			return createStringIntoPredicate();
		case PredicatesPackage.ENUM_INTO_PREDICATE:
			return createEnumIntoPredicate();
		case PredicatesPackage.AND_PREDICATE:
			return createAndPredicate();
		case PredicatesPackage.OR_PREDICATE:
			return createOrPredicate();
		case PredicatesPackage.NOT_PREDICATE:
			return createNotPredicate();
		case PredicatesPackage.COMPARE_NUMBER_PREDICATE:
			return createCompareNumberPredicate();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch(eDataType.getClassifierID()) {
		case PredicatesPackage.OPERATOR:
			return createOPERATORFromString(eDataType, initialValue);
		case PredicatesPackage.PATTERN:
			return createPatternFromString(eDataType, initialValue);
		case PredicatesPackage.CHAR_SEQUENCE:
			return createCharSequenceFromString(eDataType, initialValue);
		case PredicatesPackage.NUMBER:
			return createNumberFromString(eDataType, initialValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch(eDataType.getClassifierID()) {
		case PredicatesPackage.OPERATOR:
			return convertOPERATORToString(eDataType, instanceValue);
		case PredicatesPackage.PATTERN:
			return convertPatternToString(eDataType, instanceValue);
		case PredicatesPackage.CHAR_SEQUENCE:
			return convertCharSequenceToString(eDataType, instanceValue);
		case PredicatesPackage.NUMBER:
			return convertNumberToString(eDataType, instanceValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public StringEqualPredicate createStringEqualPredicate() {
		StringEqualPredicateImpl stringEqualPredicate = new StringEqualPredicateImpl();
		return stringEqualPredicate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public DateEqualPredicate createDateEqualPredicate() {
		DateEqualPredicateImpl dateEqualPredicate = new DateEqualPredicateImpl();
		return dateEqualPredicate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EnumEqualPredicate createEnumEqualPredicate() {
		EnumEqualPredicateImpl enumEqualPredicate = new EnumEqualPredicateImpl();
		return enumEqualPredicate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public BooleanEqualPredicate createBooleanEqualPredicate() {
		BooleanEqualPredicateImpl booleanEqualPredicate = new BooleanEqualPredicateImpl();
		return booleanEqualPredicate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ContainsPatternPredicate createContainsPatternPredicate() {
		ContainsPatternPredicateImpl containsPatternPredicate = new ContainsPatternPredicateImpl();
		return containsPatternPredicate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public StringIntoPredicate createStringIntoPredicate() {
		StringIntoPredicateImpl stringIntoPredicate = new StringIntoPredicateImpl();
		return stringIntoPredicate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EnumIntoPredicate createEnumIntoPredicate() {
		EnumIntoPredicateImpl enumIntoPredicate = new EnumIntoPredicateImpl();
		return enumIntoPredicate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public AndPredicate createAndPredicate() {
		AndPredicateImpl andPredicate = new AndPredicateImpl();
		return andPredicate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public OrPredicate createOrPredicate() {
		OrPredicateImpl orPredicate = new OrPredicateImpl();
		return orPredicate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public CompareNumberPredicate createCompareNumberPredicate() {
		CompareNumberPredicateImpl compareNumberPredicate = new CompareNumberPredicateImpl();
		return compareNumberPredicate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotPredicate createNotPredicate() {
		NotPredicateImpl notPredicate = new NotPredicateImpl();
		return notPredicate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public OPERATOR createOPERATOR(String literal) {
		OPERATOR result = OPERATOR.get(literal);
		if(result == null)
			throw new IllegalArgumentException("The value '" + literal + "' is not a valid enumerator of '" + PredicatesPackage.Literals.OPERATOR.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public OPERATOR createOPERATORFromString(EDataType eDataType, String initialValue) {
		return createOPERATOR(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertOPERATOR(OPERATOR instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertOPERATORToString(EDataType eDataType, Object instanceValue) {
		return convertOPERATOR((OPERATOR)instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Pattern createPattern(String literal) {
		try {
			return Pattern.compile(literal);
		} catch (PatternSyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Pattern createPatternFromString(EDataType eDataType, String initialValue) {
		return createPattern(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertPattern(Pattern instanceValue) {
		return super.convertToString(PredicatesPackage.Literals.PATTERN, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertPatternToString(EDataType eDataType, Object instanceValue) {
		return convertPattern((Pattern)instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public CharSequence createCharSequence(String literal) {
		return (CharSequence)super.createFromString(PredicatesPackage.Literals.CHAR_SEQUENCE, literal);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public CharSequence createCharSequenceFromString(EDataType eDataType, String initialValue) {
		return createCharSequence(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertCharSequence(CharSequence instanceValue) {
		return super.convertToString(PredicatesPackage.Literals.CHAR_SEQUENCE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertCharSequenceToString(EDataType eDataType, Object instanceValue) {
		return convertCharSequence((CharSequence)instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Number createNumber(String literal) {
		if(literal.contains(".")) {
			return Double.parseDouble(literal);
		}
		return Long.parseLong(literal);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Number createNumberFromString(EDataType eDataType, String initialValue) {
		return createNumber(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertNumber(Number instanceValue) {
		return super.convertToString(PredicatesPackage.Literals.NUMBER, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertNumberToString(EDataType eDataType, Object instanceValue) {
		return convertNumber((Number)instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PredicatesPackage getPredicatesPackage() {
		return (PredicatesPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static PredicatesPackage getPackage() {
		return PredicatesPackage.eINSTANCE;
	}

} //PredicatesFactoryImpl

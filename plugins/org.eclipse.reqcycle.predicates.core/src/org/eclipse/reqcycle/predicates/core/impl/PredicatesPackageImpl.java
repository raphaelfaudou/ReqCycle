/**
 */
package org.eclipse.reqcycle.predicates.core.impl;

import java.lang.CharSequence;
import java.util.regex.Pattern;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.reqcycle.predicates.core.PredicatesFactory;
import org.eclipse.reqcycle.predicates.core.PredicatesPackage;
import org.eclipse.reqcycle.predicates.core.api.AndPredicate;
import org.eclipse.reqcycle.predicates.core.api.BooleanEqualPredicate;
import org.eclipse.reqcycle.predicates.core.api.CompareNumberPredicate;
import org.eclipse.reqcycle.predicates.core.api.CompositePredicate;
import org.eclipse.reqcycle.predicates.core.api.ContainsPatternPredicate;
import org.eclipse.reqcycle.predicates.core.api.CustomPredicate;
import org.eclipse.reqcycle.predicates.core.api.DateEqualPredicate;
import org.eclipse.reqcycle.predicates.core.api.EnumEqualPredicate;
import org.eclipse.reqcycle.predicates.core.api.EnumIntoPredicate;
import org.eclipse.reqcycle.predicates.core.api.EqualPredicate;
import org.eclipse.reqcycle.predicates.core.api.IEAttrPredicate;
import org.eclipse.reqcycle.predicates.core.api.IEClassifierPredicate;
import org.eclipse.reqcycle.predicates.core.api.IPredicate;
import org.eclipse.reqcycle.predicates.core.api.ITypedPredicate;
import org.eclipse.reqcycle.predicates.core.api.IntoPredicate;
import org.eclipse.reqcycle.predicates.core.api.NotPredicate;
import org.eclipse.reqcycle.predicates.core.api.OrPredicate;
import org.eclipse.reqcycle.predicates.core.api.StringEqualPredicate;
import org.eclipse.reqcycle.predicates.core.api.StringIntoPredicate;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PredicatesPackageImpl extends EPackageImpl implements PredicatesPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass iPredicateEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass compositePredicateEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass customPredicateEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass equalPredicateEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass stringEqualPredicateEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass dateEqualPredicateEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass enumEqualPredicateEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass booleanEqualPredicateEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass containsPatternPredicateEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass intoPredicateEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass stringIntoPredicateEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass enumIntoPredicateEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass andPredicateEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass orPredicateEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass compareNumberPredicateEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass iTypedPredicateEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass ieAttrPredicateEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass ieClassifierPredicateEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass notPredicateEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum operatorEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType patternEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType charSequenceEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType numberEDataType = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.eclipse.reqcycle.predicates.core.PredicatesPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private PredicatesPackageImpl() {
        super(eNS_URI, PredicatesFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * 
     * <p>This method is used to initialize {@link PredicatesPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static PredicatesPackage init() {
        if (isInited) return (PredicatesPackage)EPackage.Registry.INSTANCE.getEPackage(PredicatesPackage.eNS_URI);

        // Obtain or create and register package
        PredicatesPackageImpl thePredicatesPackage = (PredicatesPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof PredicatesPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new PredicatesPackageImpl());

        isInited = true;

        // Create package meta-data objects
        thePredicatesPackage.createPackageContents();

        // Initialize created meta-data
        thePredicatesPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        thePredicatesPackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(PredicatesPackage.eNS_URI, thePredicatesPackage);
        return thePredicatesPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getIPredicate() {
        return iPredicateEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getIPredicate_DisplayName() {
        return (EAttribute)iPredicateEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EOperation getIPredicate__Match__Object() {
        return iPredicateEClass.getEOperations().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCompositePredicate() {
        return compositePredicateEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCompositePredicate_Predicates() {
        return (EReference)compositePredicateEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCustomPredicate() {
        return customPredicateEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getEqualPredicate() {
        return equalPredicateEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEqualPredicate_Input() {
        return (EAttribute)equalPredicateEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEqualPredicate_ExpectedObject() {
        return (EAttribute)equalPredicateEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getStringEqualPredicate() {
        return stringEqualPredicateEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDateEqualPredicate() {
        return dateEqualPredicateEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getEnumEqualPredicate() {
        return enumEqualPredicateEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getBooleanEqualPredicate() {
        return booleanEqualPredicateEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getContainsPatternPredicate() {
        return containsPatternPredicateEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getContainsPatternPredicate_Input() {
        return (EAttribute)containsPatternPredicateEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getContainsPatternPredicate_ExpectedPattern() {
        return (EAttribute)containsPatternPredicateEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getIntoPredicate() {
        return intoPredicateEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getIntoPredicate_Input() {
        return (EAttribute)intoPredicateEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getIntoPredicate_AllowedEntries() {
        return (EAttribute)intoPredicateEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getStringIntoPredicate() {
        return stringIntoPredicateEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getEnumIntoPredicate() {
        return enumIntoPredicateEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getAndPredicate() {
        return andPredicateEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getOrPredicate() {
        return orPredicateEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCompareNumberPredicate() {
        return compareNumberPredicateEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCompareNumberPredicate_Input() {
        return (EAttribute)compareNumberPredicateEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCompareNumberPredicate_ExpectedValue() {
        return (EAttribute)compareNumberPredicateEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCompareNumberPredicate_Operator() {
        return (EAttribute)compareNumberPredicateEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getITypedPredicate() {
        return iTypedPredicateEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getITypedPredicate_TypedElement() {
        return (EReference)iTypedPredicateEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getIEAttrPredicate() {
        return ieAttrPredicateEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getIEClassifierPredicate() {
        return ieClassifierPredicateEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getNotPredicate() {
        return notPredicateEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getOPERATOR() {
        return operatorEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getPattern() {
        return patternEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getCharSequence() {
        return charSequenceEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getNumber() {
        return numberEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PredicatesFactory getPredicatesFactory() {
        return (PredicatesFactory)getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        iPredicateEClass = createEClass(IPREDICATE);
        createEAttribute(iPredicateEClass, IPREDICATE__DISPLAY_NAME);
        createEOperation(iPredicateEClass, IPREDICATE___MATCH__OBJECT);

        compositePredicateEClass = createEClass(COMPOSITE_PREDICATE);
        createEReference(compositePredicateEClass, COMPOSITE_PREDICATE__PREDICATES);

        customPredicateEClass = createEClass(CUSTOM_PREDICATE);

        equalPredicateEClass = createEClass(EQUAL_PREDICATE);
        createEAttribute(equalPredicateEClass, EQUAL_PREDICATE__INPUT);
        createEAttribute(equalPredicateEClass, EQUAL_PREDICATE__EXPECTED_OBJECT);

        stringEqualPredicateEClass = createEClass(STRING_EQUAL_PREDICATE);

        dateEqualPredicateEClass = createEClass(DATE_EQUAL_PREDICATE);

        enumEqualPredicateEClass = createEClass(ENUM_EQUAL_PREDICATE);

        booleanEqualPredicateEClass = createEClass(BOOLEAN_EQUAL_PREDICATE);

        containsPatternPredicateEClass = createEClass(CONTAINS_PATTERN_PREDICATE);
        createEAttribute(containsPatternPredicateEClass, CONTAINS_PATTERN_PREDICATE__INPUT);
        createEAttribute(containsPatternPredicateEClass, CONTAINS_PATTERN_PREDICATE__EXPECTED_PATTERN);

        intoPredicateEClass = createEClass(INTO_PREDICATE);
        createEAttribute(intoPredicateEClass, INTO_PREDICATE__INPUT);
        createEAttribute(intoPredicateEClass, INTO_PREDICATE__ALLOWED_ENTRIES);

        stringIntoPredicateEClass = createEClass(STRING_INTO_PREDICATE);

        enumIntoPredicateEClass = createEClass(ENUM_INTO_PREDICATE);

        andPredicateEClass = createEClass(AND_PREDICATE);

        orPredicateEClass = createEClass(OR_PREDICATE);

        notPredicateEClass = createEClass(NOT_PREDICATE);

        compareNumberPredicateEClass = createEClass(COMPARE_NUMBER_PREDICATE);
        createEAttribute(compareNumberPredicateEClass, COMPARE_NUMBER_PREDICATE__INPUT);
        createEAttribute(compareNumberPredicateEClass, COMPARE_NUMBER_PREDICATE__EXPECTED_VALUE);
        createEAttribute(compareNumberPredicateEClass, COMPARE_NUMBER_PREDICATE__OPERATOR);

        iTypedPredicateEClass = createEClass(ITYPED_PREDICATE);
        createEReference(iTypedPredicateEClass, ITYPED_PREDICATE__TYPED_ELEMENT);

        ieAttrPredicateEClass = createEClass(IE_ATTR_PREDICATE);

        ieClassifierPredicateEClass = createEClass(IE_CLASSIFIER_PREDICATE);

        // Create enums
        operatorEEnum = createEEnum(OPERATOR);

        // Create data types
        patternEDataType = createEDataType(PATTERN);
        charSequenceEDataType = createEDataType(CHAR_SEQUENCE);
        numberEDataType = createEDataType(NUMBER);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Create type parameters
        ETypeParameter equalPredicateEClass_T = addETypeParameter(equalPredicateEClass, "T");
        ETypeParameter intoPredicateEClass_T = addETypeParameter(intoPredicateEClass, "T");
        ETypeParameter iTypedPredicateEClass_T = addETypeParameter(iTypedPredicateEClass, "T");

        // Set bounds for type parameters
        EGenericType g1 = createEGenericType(ecorePackage.getEJavaObject());
        equalPredicateEClass_T.getEBounds().add(g1);

        // Add supertypes to classes
        compositePredicateEClass.getESuperTypes().add(this.getIPredicate());
        customPredicateEClass.getESuperTypes().add(this.getIEAttrPredicate());
        equalPredicateEClass.getESuperTypes().add(this.getIEAttrPredicate());
        g1 = createEGenericType(this.getEqualPredicate());
        EGenericType g2 = createEGenericType(ecorePackage.getEString());
        g1.getETypeArguments().add(g2);
        stringEqualPredicateEClass.getEGenericSuperTypes().add(g1);
        g1 = createEGenericType(this.getEqualPredicate());
        g2 = createEGenericType(ecorePackage.getEDate());
        g1.getETypeArguments().add(g2);
        dateEqualPredicateEClass.getEGenericSuperTypes().add(g1);
        g1 = createEGenericType(this.getEqualPredicate());
        g2 = createEGenericType(ecorePackage.getEEnumerator());
        g1.getETypeArguments().add(g2);
        enumEqualPredicateEClass.getEGenericSuperTypes().add(g1);
        g1 = createEGenericType(this.getEqualPredicate());
        g2 = createEGenericType(ecorePackage.getEBooleanObject());
        g1.getETypeArguments().add(g2);
        booleanEqualPredicateEClass.getEGenericSuperTypes().add(g1);
        containsPatternPredicateEClass.getESuperTypes().add(this.getIEAttrPredicate());
        intoPredicateEClass.getESuperTypes().add(this.getIEAttrPredicate());
        g1 = createEGenericType(this.getIntoPredicate());
        g2 = createEGenericType(ecorePackage.getEString());
        g1.getETypeArguments().add(g2);
        stringIntoPredicateEClass.getEGenericSuperTypes().add(g1);
        g1 = createEGenericType(this.getIntoPredicate());
        g2 = createEGenericType(ecorePackage.getEEnumerator());
        g1.getETypeArguments().add(g2);
        enumIntoPredicateEClass.getEGenericSuperTypes().add(g1);
        andPredicateEClass.getESuperTypes().add(this.getCompositePredicate());
        orPredicateEClass.getESuperTypes().add(this.getCompositePredicate());
        notPredicateEClass.getESuperTypes().add(this.getCompositePredicate());
        compareNumberPredicateEClass.getESuperTypes().add(this.getIEAttrPredicate());
        iTypedPredicateEClass.getESuperTypes().add(this.getIPredicate());
        g1 = createEGenericType(this.getITypedPredicate());
        g2 = createEGenericType(ecorePackage.getEAttribute());
        g1.getETypeArguments().add(g2);
        ieAttrPredicateEClass.getEGenericSuperTypes().add(g1);
        g1 = createEGenericType(this.getITypedPredicate());
        g2 = createEGenericType(ecorePackage.getEClassifier());
        g1.getETypeArguments().add(g2);
        ieClassifierPredicateEClass.getEGenericSuperTypes().add(g1);

        // Initialize classes, features, and operations; add parameters
        initEClass(iPredicateEClass, IPredicate.class, "IPredicate", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getIPredicate_DisplayName(), ecorePackage.getEString(), "displayName", null, 0, 1, IPredicate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        EOperation op = initEOperation(getIPredicate__Match__Object(), ecorePackage.getEBoolean(), "match", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEJavaObject(), "input", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(compositePredicateEClass, CompositePredicate.class, "CompositePredicate", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCompositePredicate_Predicates(), this.getIPredicate(), null, "predicates", null, 0, -1, CompositePredicate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(customPredicateEClass, CustomPredicate.class, "CustomPredicate", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(equalPredicateEClass, EqualPredicate.class, "EqualPredicate", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        g1 = createEGenericType(equalPredicateEClass_T);
        initEAttribute(getEqualPredicate_Input(), g1, "input", null, 0, 1, EqualPredicate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        g1 = createEGenericType(equalPredicateEClass_T);
        initEAttribute(getEqualPredicate_ExpectedObject(), g1, "expectedObject", null, 0, 1, EqualPredicate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(stringEqualPredicateEClass, StringEqualPredicate.class, "StringEqualPredicate", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(dateEqualPredicateEClass, DateEqualPredicate.class, "DateEqualPredicate", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(enumEqualPredicateEClass, EnumEqualPredicate.class, "EnumEqualPredicate", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(booleanEqualPredicateEClass, BooleanEqualPredicate.class, "BooleanEqualPredicate", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(containsPatternPredicateEClass, ContainsPatternPredicate.class, "ContainsPatternPredicate", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getContainsPatternPredicate_Input(), this.getCharSequence(), "input", null, 0, 1, ContainsPatternPredicate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getContainsPatternPredicate_ExpectedPattern(), this.getPattern(), "expectedPattern", null, 0, 1, ContainsPatternPredicate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(intoPredicateEClass, IntoPredicate.class, "IntoPredicate", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        g1 = createEGenericType(intoPredicateEClass_T);
        initEAttribute(getIntoPredicate_Input(), g1, "input", null, 0, 1, IntoPredicate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        g1 = createEGenericType(intoPredicateEClass_T);
        initEAttribute(getIntoPredicate_AllowedEntries(), g1, "allowedEntries", null, 0, -1, IntoPredicate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(stringIntoPredicateEClass, StringIntoPredicate.class, "StringIntoPredicate", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(enumIntoPredicateEClass, EnumIntoPredicate.class, "EnumIntoPredicate", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(andPredicateEClass, AndPredicate.class, "AndPredicate", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(orPredicateEClass, OrPredicate.class, "OrPredicate", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(notPredicateEClass, NotPredicate.class, "NotPredicate", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(compareNumberPredicateEClass, CompareNumberPredicate.class, "CompareNumberPredicate", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getCompareNumberPredicate_Input(), this.getNumber(), "input", null, 0, 1, CompareNumberPredicate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getCompareNumberPredicate_ExpectedValue(), this.getNumber(), "expectedValue", null, 0, 1, CompareNumberPredicate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getCompareNumberPredicate_Operator(), this.getOPERATOR(), "operator", null, 0, 1, CompareNumberPredicate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(iTypedPredicateEClass, ITypedPredicate.class, "ITypedPredicate", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        g1 = createEGenericType(iTypedPredicateEClass_T);
        initEReference(getITypedPredicate_TypedElement(), g1, null, "typedElement", null, 0, 1, ITypedPredicate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(ieAttrPredicateEClass, IEAttrPredicate.class, "IEAttrPredicate", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(ieClassifierPredicateEClass, IEClassifierPredicate.class, "IEClassifierPredicate", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        // Initialize enums and add enum literals
        initEEnum(operatorEEnum, org.eclipse.reqcycle.predicates.core.api.OPERATOR.class, "OPERATOR");
        addEEnumLiteral(operatorEEnum, org.eclipse.reqcycle.predicates.core.api.OPERATOR.EQ);
        addEEnumLiteral(operatorEEnum, org.eclipse.reqcycle.predicates.core.api.OPERATOR.NQ);
        addEEnumLiteral(operatorEEnum, org.eclipse.reqcycle.predicates.core.api.OPERATOR.GT);
        addEEnumLiteral(operatorEEnum, org.eclipse.reqcycle.predicates.core.api.OPERATOR.GET);
        addEEnumLiteral(operatorEEnum, org.eclipse.reqcycle.predicates.core.api.OPERATOR.LT);
        addEEnumLiteral(operatorEEnum, org.eclipse.reqcycle.predicates.core.api.OPERATOR.LET);

        // Initialize data types
        initEDataType(patternEDataType, Pattern.class, "Pattern", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(charSequenceEDataType, CharSequence.class, "CharSequence", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(numberEDataType, Number.class, "Number", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

        // Create resource
        createResource(eNS_URI);

        // Create annotations
        // www.eclipse.org/reqcycle/predicates/userInput
        createUserInputAnnotations();
        // www.eclipse.org/reqcycle/predicates/specificUserInput
        createSpecificUserInputAnnotations();
        // www.eclipse.org/reqcycle/predicates/input_javaclass_type
        createInput_javaclass_typeAnnotations();
        // www.eclipse.org/reqcycle/predicates/adaptInput
        createAdaptInputAnnotations();
    }

    /**
     * Initializes the annotations for <b>www.eclipse.org/reqcycle/predicates/userInput</b>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void createUserInputAnnotations() {
        String source = "www.eclipse.org/reqcycle/predicates/userInput";		
        addAnnotation
          (equalPredicateEClass, 
           source, 
           new String[] {
           });							
        addAnnotation
          (containsPatternPredicateEClass, 
           source, 
           new String[] {
           });				
        addAnnotation
          (intoPredicateEClass, 
           source, 
           new String[] {
           });					
        addAnnotation
          (compareNumberPredicateEClass, 
           source, 
           new String[] {
           });			
        addAnnotation
          (getCompareNumberPredicate_Operator(), 
           source, 
           new String[] {
           });
    }

    /**
     * Initializes the annotations for <b>www.eclipse.org/reqcycle/predicates/specificUserInput</b>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void createSpecificUserInputAnnotations() {
        String source = "www.eclipse.org/reqcycle/predicates/specificUserInput";			
        addAnnotation
          (getEqualPredicate_ExpectedObject(), 
           source, 
           new String[] {
           });							
        addAnnotation
          (getContainsPatternPredicate_ExpectedPattern(), 
           source, 
           new String[] {
           });				
        addAnnotation
          (getIntoPredicate_AllowedEntries(), 
           source, 
           new String[] {
           });					
        addAnnotation
          (getCompareNumberPredicate_ExpectedValue(), 
           source, 
           new String[] {
           });	
    }

    /**
     * Initializes the annotations for <b>www.eclipse.org/reqcycle/predicates/input_javaclass_type</b>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void createInput_javaclass_typeAnnotations() {
        String source = "www.eclipse.org/reqcycle/predicates/input_javaclass_type";				
        addAnnotation
          (stringEqualPredicateEClass, 
           source, 
           new String[] {
             "inputType", "java.lang.String"
           });		
        addAnnotation
          (dateEqualPredicateEClass, 
           source, 
           new String[] {
             "inputType", "java.util.Date"
           });		
        addAnnotation
          (enumEqualPredicateEClass, 
           source, 
           new String[] {
             "inputType", "org.eclipse.emf.common.util.Enumerator"
           });		
        addAnnotation
          (booleanEqualPredicateEClass, 
           source, 
           new String[] {
             "inputType", "java.lang.Boolean"
           });							
        addAnnotation
          (stringIntoPredicateEClass, 
           source, 
           new String[] {
             "inputType", "java.util.Collection",
             "objectType", "java.lang.String"
           });		
        addAnnotation
          (enumIntoPredicateEClass, 
           source, 
           new String[] {
             "inputType", "java.util.Collection",
             "objectType", "org.eclipse.emf.common.util.Enumerator"
           });			
    }

    /**
     * Initializes the annotations for <b>www.eclipse.org/reqcycle/predicates/adaptInput</b>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void createAdaptInputAnnotations() {
        String source = "www.eclipse.org/reqcycle/predicates/adaptInput";										
        addAnnotation
          (getContainsPatternPredicate_ExpectedPattern(), 
           source, 
           new String[] {
           });							
    }

} //PredicatesPackageImpl

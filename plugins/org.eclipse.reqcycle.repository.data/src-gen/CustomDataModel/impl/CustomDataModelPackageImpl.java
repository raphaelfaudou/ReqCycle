/**
 */
package CustomDataModel.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import CustomDataModel.CustomDataModelFactory;
import CustomDataModel.CustomDataModelPackage;
import CustomDataModel.Enumeration;
import CustomDataModel.RequirementType;
import CustomDataModel.SpecificationType;
import DataModel.DataModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CustomDataModelPackageImpl extends EPackageImpl implements CustomDataModelPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass specificationTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass requirementTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum enumerationEEnum = null;

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
	 * @see CustomDataModel.CustomDataModelPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private CustomDataModelPackageImpl() {
		super(eNS_URI, CustomDataModelFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link CustomDataModelPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static CustomDataModelPackage init() {
		if (isInited) return (CustomDataModelPackage)EPackage.Registry.INSTANCE.getEPackage(CustomDataModelPackage.eNS_URI);

		// Obtain or create and register package
		CustomDataModelPackageImpl theCustomDataModelPackage = (CustomDataModelPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof CustomDataModelPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new CustomDataModelPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		DataModelPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theCustomDataModelPackage.createPackageContents();

		// Initialize created meta-data
		theCustomDataModelPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theCustomDataModelPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(CustomDataModelPackage.eNS_URI, theCustomDataModelPackage);
		return theCustomDataModelPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSpecificationType() {
		return specificationTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSpecificationType_Description() {
		return (EAttribute)specificationTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSpecificationType_String_Attribute() {
		return (EAttribute)specificationTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSpecificationType_Boolean_Attribute() {
		return (EAttribute)specificationTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSpecificationType_Integer_Attribute() {
		return (EAttribute)specificationTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRequirementType() {
		return requirementTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRequirementType_Description() {
		return (EAttribute)requirementTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRequirementType_String_Attribute() {
		return (EAttribute)requirementTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRequirementType_Boolean_Attribute() {
		return (EAttribute)requirementTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRequirementType_Integer_Attribute() {
		return (EAttribute)requirementTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRequirementType_Enumeration_Attribute() {
		return (EAttribute)requirementTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getEnumeration() {
		return enumerationEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CustomDataModelFactory getCustomDataModelFactory() {
		return (CustomDataModelFactory)getEFactoryInstance();
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
		specificationTypeEClass = createEClass(SPECIFICATION_TYPE);
		createEAttribute(specificationTypeEClass, SPECIFICATION_TYPE__DESCRIPTION);
		createEAttribute(specificationTypeEClass, SPECIFICATION_TYPE__STRING_ATTRIBUTE);
		createEAttribute(specificationTypeEClass, SPECIFICATION_TYPE__BOOLEAN_ATTRIBUTE);
		createEAttribute(specificationTypeEClass, SPECIFICATION_TYPE__INTEGER_ATTRIBUTE);

		requirementTypeEClass = createEClass(REQUIREMENT_TYPE);
		createEAttribute(requirementTypeEClass, REQUIREMENT_TYPE__DESCRIPTION);
		createEAttribute(requirementTypeEClass, REQUIREMENT_TYPE__STRING_ATTRIBUTE);
		createEAttribute(requirementTypeEClass, REQUIREMENT_TYPE__BOOLEAN_ATTRIBUTE);
		createEAttribute(requirementTypeEClass, REQUIREMENT_TYPE__INTEGER_ATTRIBUTE);
		createEAttribute(requirementTypeEClass, REQUIREMENT_TYPE__ENUMERATION_ATTRIBUTE);

		// Create enums
		enumerationEEnum = createEEnum(ENUMERATION);
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

		// Obtain other dependent packages
		DataModelPackage theDataModelPackage = (DataModelPackage)EPackage.Registry.INSTANCE.getEPackage(DataModelPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		specificationTypeEClass.getESuperTypes().add(theDataModelPackage.getReachableSection());
		requirementTypeEClass.getESuperTypes().add(theDataModelPackage.getRequirementSection());

		// Initialize classes, features, and operations; add parameters
		initEClass(specificationTypeEClass, SpecificationType.class, "SpecificationType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSpecificationType_Description(), ecorePackage.getEString(), "Description", null, 0, 1, SpecificationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpecificationType_String_Attribute(), ecorePackage.getEString(), "String_Attribute", null, 0, 1, SpecificationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpecificationType_Boolean_Attribute(), ecorePackage.getEBoolean(), "Boolean_Attribute", null, 0, 1, SpecificationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpecificationType_Integer_Attribute(), ecorePackage.getEBigInteger(), "Integer_Attribute", null, 0, 1, SpecificationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(requirementTypeEClass, RequirementType.class, "RequirementType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRequirementType_Description(), ecorePackage.getEString(), "Description", null, 0, 1, RequirementType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRequirementType_String_Attribute(), ecorePackage.getEString(), "String_Attribute", null, 0, 1, RequirementType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRequirementType_Boolean_Attribute(), ecorePackage.getEBoolean(), "Boolean_Attribute", null, 0, 1, RequirementType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRequirementType_Integer_Attribute(), ecorePackage.getEBigInteger(), "Integer_Attribute", null, 0, 1, RequirementType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRequirementType_Enumeration_Attribute(), this.getEnumeration(), "Enumeration_Attribute", null, 0, 1, RequirementType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(enumerationEEnum, Enumeration.class, "Enumeration");
		addEEnumLiteral(enumerationEEnum, Enumeration.OFF);
		addEEnumLiteral(enumerationEEnum, Enumeration.ON);

		// Create resource
		createResource(eNS_URI);
	}

} //CustomDataModelPackageImpl

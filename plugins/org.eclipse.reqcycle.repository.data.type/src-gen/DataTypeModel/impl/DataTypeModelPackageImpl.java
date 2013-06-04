/**
 */
package DataTypeModel.impl;

import DataTypeModel.ContainedType;
import DataTypeModel.DataTypeModelFactory;
import DataTypeModel.DataTypeModelPackage;
import DataTypeModel.ModelType;
import DataTypeModel.ReachableObjectType;
import DataTypeModel.ReachableSectionType;
import DataTypeModel.RequirementSectionType;
import DataTypeModel.RequirementType;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DataTypeModelPackageImpl extends EPackageImpl implements DataTypeModelPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass reachableObjectTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass reachableSectionTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass containedTypeEClass = null;

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
	private EClass requirementSectionTypeEClass = null;

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
	 * @see DataTypeModel.DataTypeModelPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private DataTypeModelPackageImpl() {
		super(eNS_URI, DataTypeModelFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link DataTypeModelPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static DataTypeModelPackage init() {
		if (isInited) return (DataTypeModelPackage)EPackage.Registry.INSTANCE.getEPackage(DataTypeModelPackage.eNS_URI);

		// Obtain or create and register package
		DataTypeModelPackageImpl theDataTypeModelPackage = (DataTypeModelPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof DataTypeModelPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new DataTypeModelPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theDataTypeModelPackage.createPackageContents();

		// Initialize created meta-data
		theDataTypeModelPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theDataTypeModelPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(DataTypeModelPackage.eNS_URI, theDataTypeModelPackage);
		return theDataTypeModelPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReachableObjectType() {
		return reachableObjectTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelType() {
		return modelTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModelType_ObjectTypes() {
		return (EReference)modelTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReachableSectionType() {
		return reachableSectionTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContainedType() {
		return containedTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainedType_CustomAttributes() {
		return (EReference)containedTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContainedType_Name() {
		return (EAttribute)containedTypeEClass.getEStructuralFeatures().get(1);
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
	public EClass getRequirementSectionType() {
		return requirementSectionTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataTypeModelFactory getDataTypeModelFactory() {
		return (DataTypeModelFactory)getEFactoryInstance();
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
		reachableObjectTypeEClass = createEClass(REACHABLE_OBJECT_TYPE);

		modelTypeEClass = createEClass(MODEL_TYPE);
		createEReference(modelTypeEClass, MODEL_TYPE__OBJECT_TYPES);

		reachableSectionTypeEClass = createEClass(REACHABLE_SECTION_TYPE);

		containedTypeEClass = createEClass(CONTAINED_TYPE);
		createEReference(containedTypeEClass, CONTAINED_TYPE__CUSTOM_ATTRIBUTES);
		createEAttribute(containedTypeEClass, CONTAINED_TYPE__NAME);

		requirementTypeEClass = createEClass(REQUIREMENT_TYPE);

		requirementSectionTypeEClass = createEClass(REQUIREMENT_SECTION_TYPE);
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
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		reachableObjectTypeEClass.getESuperTypes().add(this.getContainedType());
		reachableSectionTypeEClass.getESuperTypes().add(this.getContainedType());
		requirementTypeEClass.getESuperTypes().add(this.getReachableObjectType());
		requirementSectionTypeEClass.getESuperTypes().add(this.getReachableObjectType());
		requirementSectionTypeEClass.getESuperTypes().add(this.getReachableSectionType());

		// Initialize classes, features, and operations; add parameters
		initEClass(reachableObjectTypeEClass, ReachableObjectType.class, "ReachableObjectType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(modelTypeEClass, ModelType.class, "ModelType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getModelType_ObjectTypes(), this.getContainedType(), null, "ObjectTypes", null, 0, -1, ModelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(reachableSectionTypeEClass, ReachableSectionType.class, "ReachableSectionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(containedTypeEClass, ContainedType.class, "ContainedType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getContainedType_CustomAttributes(), theEcorePackage.getEAttribute(), null, "customAttributes", null, 0, -1, ContainedType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getContainedType_Name(), theEcorePackage.getEString(), "name", null, 0, 1, ContainedType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(requirementTypeEClass, RequirementType.class, "RequirementType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(requirementSectionTypeEClass, RequirementSectionType.class, "RequirementSectionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //DataTypeModelPackageImpl

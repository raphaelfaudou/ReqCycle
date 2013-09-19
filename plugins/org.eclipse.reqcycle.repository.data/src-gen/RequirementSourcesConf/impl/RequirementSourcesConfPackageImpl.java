/**
 */
package RequirementSourcesConf.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import RequirementSourceData.RequirementSourceDataPackage;
import RequirementSourcesConf.RequirementSources;
import RequirementSourcesConf.RequirementSourcesConfFactory;
import RequirementSourcesConf.RequirementSourcesConfPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class RequirementSourcesConfPackageImpl extends EPackageImpl implements RequirementSourcesConfPackage {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass requirementSourcesEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the
	 * package
	 * package URI value.
	 * <p>
	 * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also performs initialization of the
	 * package, or returns the registered package, if one already exists. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see RequirementSourcesConf.RequirementSourcesConfPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private RequirementSourcesConfPackageImpl() {
		super(eNS_URI, RequirementSourcesConfFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>
	 * This method is used to initialize {@link RequirementSourcesConfPackage#eINSTANCE} when that field is accessed. Clients should not invoke it
	 * directly. Instead, they should simply access that field to obtain the package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static RequirementSourcesConfPackage init() {
		if(isInited)
			return (RequirementSourcesConfPackage)EPackage.Registry.INSTANCE.getEPackage(RequirementSourcesConfPackage.eNS_URI);

		// Obtain or create and register package
		RequirementSourcesConfPackageImpl theRequirementSourcesConfPackage = (RequirementSourcesConfPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof RequirementSourcesConfPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new RequirementSourcesConfPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		RequirementSourceDataPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theRequirementSourcesConfPackage.createPackageContents();

		// Initialize created meta-data
		theRequirementSourcesConfPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theRequirementSourcesConfPackage.freeze();


		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(RequirementSourcesConfPackage.eNS_URI, theRequirementSourcesConfPackage);
		return theRequirementSourcesConfPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getRequirementSources() {
		return requirementSourcesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getRequirementSources_RequirementSources() {
		return (EReference)requirementSourcesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public RequirementSourcesConfFactory getRequirementSourcesConfFactory() {
		return (RequirementSourcesConfFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package. This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void createPackageContents() {
		if(isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		requirementSourcesEClass = createEClass(REQUIREMENT_SOURCES);
		createEReference(requirementSourcesEClass, REQUIREMENT_SOURCES__REQUIREMENT_SOURCES);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model. This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void initializePackageContents() {
		if(isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		RequirementSourceDataPackage theDataModelPackage = (RequirementSourceDataPackage)EPackage.Registry.INSTANCE.getEPackage(RequirementSourceDataPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(requirementSourcesEClass, RequirementSources.class, "RequirementSources", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRequirementSources_RequirementSources(), theDataModelPackage.getRequirementSource(), null, "requirementSources", null, 0, -1, RequirementSources.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //RequirementSourcesConfPackageImpl

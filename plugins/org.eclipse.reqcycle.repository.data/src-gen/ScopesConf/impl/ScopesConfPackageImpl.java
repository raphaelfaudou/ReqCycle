/**
 */
package ScopesConf.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import DataModel.DataModelPackage;
import ScopesConf.Scopes;
import ScopesConf.ScopesConfFactory;
import ScopesConf.ScopesConfPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ScopesConfPackageImpl extends EPackageImpl implements ScopesConfPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scopesEClass = null;

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
	 * @see ScopesConf.ScopesConfPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ScopesConfPackageImpl() {
		super(eNS_URI, ScopesConfFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link ScopesConfPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ScopesConfPackage init() {
		if (isInited) return (ScopesConfPackage)EPackage.Registry.INSTANCE.getEPackage(ScopesConfPackage.eNS_URI);

		// Obtain or create and register package
		ScopesConfPackageImpl theScopesConfPackage = (ScopesConfPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ScopesConfPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ScopesConfPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		DataModelPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theScopesConfPackage.createPackageContents();

		// Initialize created meta-data
		theScopesConfPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theScopesConfPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ScopesConfPackage.eNS_URI, theScopesConfPackage);
		return theScopesConfPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getScopes() {
		return scopesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScopes_Scopes() {
		return (EReference)scopesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScopesConfFactory getScopesConfFactory() {
		return (ScopesConfFactory)getEFactoryInstance();
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
		scopesEClass = createEClass(SCOPES);
		createEReference(scopesEClass, SCOPES__SCOPES);
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

		// Initialize classes, features, and operations; add parameters
		initEClass(scopesEClass, Scopes.class, "Scopes", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getScopes_Scopes(), theDataModelPackage.getScope(), null, "scopes", null, 0, -1, Scopes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //ScopesConfPackageImpl

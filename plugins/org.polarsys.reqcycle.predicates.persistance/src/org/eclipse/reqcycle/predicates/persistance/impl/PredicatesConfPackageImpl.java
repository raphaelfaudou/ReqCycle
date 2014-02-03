/**
 */
package org.eclipse.reqcycle.predicates.persistance.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.reqcycle.predicates.core.PredicatesPackage;
import org.eclipse.reqcycle.predicates.persistance.PredicatesConfFactory;
import org.eclipse.reqcycle.predicates.persistance.PredicatesConfPackage;
import org.eclipse.reqcycle.predicates.persistance.api.PredicatesConf;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class PredicatesConfPackageImpl extends EPackageImpl implements PredicatesConfPackage {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass predicatesConfEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the
	 * package
	 * package URI value.
	 * <p>
	 * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also performs initialization of the
	 * package, or returns the registered package, if one already exists. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.reqcycle.predicates.persistance.PredicatesConfPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private PredicatesConfPackageImpl() {
		super(eNS_URI, PredicatesConfFactory.eINSTANCE);
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
	 * This method is used to initialize {@link PredicatesConfPackage#eINSTANCE} when that field is accessed. Clients should not invoke it directly.
	 * Instead, they should simply access that field to obtain the package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static PredicatesConfPackage init() {
		if(isInited)
			return (PredicatesConfPackage)EPackage.Registry.INSTANCE.getEPackage(PredicatesConfPackage.eNS_URI);

		// Obtain or create and register package
		PredicatesConfPackageImpl thePredicatesConfPackage = (PredicatesConfPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof PredicatesConfPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new PredicatesConfPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		PredicatesPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		thePredicatesConfPackage.createPackageContents();

		// Initialize created meta-data
		thePredicatesConfPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		thePredicatesConfPackage.freeze();


		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(PredicatesConfPackage.eNS_URI, thePredicatesConfPackage);
		return thePredicatesConfPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getPredicatesConf() {
		return predicatesConfEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getPredicatesConf_Predicates() {
		return (EReference)predicatesConfEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PredicatesConfFactory getPredicatesConfFactory() {
		return (PredicatesConfFactory)getEFactoryInstance();
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
		predicatesConfEClass = createEClass(PREDICATES_CONF);
		createEReference(predicatesConfEClass, PREDICATES_CONF__PREDICATES);
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
		PredicatesPackage thePredicatesPackage = (PredicatesPackage)EPackage.Registry.INSTANCE.getEPackage(PredicatesPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(predicatesConfEClass, PredicatesConf.class, "PredicatesConf", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPredicatesConf_Predicates(), thePredicatesPackage.getIPredicate(), null, "predicates", null, 0, -1, PredicatesConf.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //PredicatesConfPackageImpl

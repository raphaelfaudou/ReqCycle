/**
 */
package RequirementSourcesConf;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see RequirementSourcesConf.RequirementSourcesConfFactory
 * @model kind="package"
 * @generated
 */
public interface RequirementSourcesConfPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "RequirementSourcesConf";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/ReqCycle/RequirementSourcesConf";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "RequirementSourcesConf";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	RequirementSourcesConfPackage eINSTANCE = RequirementSourcesConf.impl.RequirementSourcesConfPackageImpl.init();

	/**
	 * The meta object id for the '{@link RequirementSourcesConf.impl.RequirementSourcesImpl <em>Requirement Sources</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see RequirementSourcesConf.impl.RequirementSourcesImpl
	 * @see RequirementSourcesConf.impl.RequirementSourcesConfPackageImpl#getRequirementSources()
	 * @generated
	 */
	int REQUIREMENT_SOURCES = 0;

	/**
	 * The feature id for the '<em><b>Requirement Sources</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_SOURCES__REQUIREMENT_SOURCES = 0;

	/**
	 * The number of structural features of the '<em>Requirement Sources</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_SOURCES_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Requirement Sources</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_SOURCES_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link RequirementSourcesConf.RequirementSources <em>Requirement Sources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Requirement Sources</em>'.
	 * @see RequirementSourcesConf.RequirementSources
	 * @generated
	 */
	EClass getRequirementSources();

	/**
	 * Returns the meta object for the containment reference list '{@link RequirementSourcesConf.RequirementSources#getRequirementSources <em>Requirement Sources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Requirement Sources</em>'.
	 * @see RequirementSourcesConf.RequirementSources#getRequirementSources()
	 * @see #getRequirementSources()
	 * @generated
	 */
	EReference getRequirementSources_RequirementSources();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	RequirementSourcesConfFactory getRequirementSourcesConfFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link RequirementSourcesConf.impl.RequirementSourcesImpl <em>Requirement Sources</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see RequirementSourcesConf.impl.RequirementSourcesImpl
		 * @see RequirementSourcesConf.impl.RequirementSourcesConfPackageImpl#getRequirementSources()
		 * @generated
		 */
		EClass REQUIREMENT_SOURCES = eINSTANCE.getRequirementSources();

		/**
		 * The meta object literal for the '<em><b>Requirement Sources</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REQUIREMENT_SOURCES__REQUIREMENT_SOURCES = eINSTANCE.getRequirementSources_RequirementSources();

	}

} //RequirementSourcesConfPackage

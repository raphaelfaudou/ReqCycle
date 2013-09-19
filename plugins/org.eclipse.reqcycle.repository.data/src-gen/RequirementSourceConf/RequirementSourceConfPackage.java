/**
 */
package RequirementSourceConf;

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
 * @see RequirementSourceConf.RequirementSourceConfFactory
 * @model kind="package"
 * @generated
 */
public interface RequirementSourceConfPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "RequirementSourceConf";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/ReqCycle/RequirementSourceConf";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "RequirementSourceConf";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	RequirementSourceConfPackage eINSTANCE = RequirementSourceConf.impl.RequirementSourceConfPackageImpl.init();

	/**
	 * The meta object id for the '{@link RequirementSourceConf.impl.RequirementSourcesImpl <em>Requirement Sources</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see RequirementSourceConf.impl.RequirementSourcesImpl
	 * @see RequirementSourceConf.impl.RequirementSourceConfPackageImpl#getRequirementSources()
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
	 * Returns the meta object for class '{@link RequirementSourceConf.RequirementSources <em>Requirement Sources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Requirement Sources</em>'.
	 * @see RequirementSourceConf.RequirementSources
	 * @generated
	 */
	EClass getRequirementSources();

	/**
	 * Returns the meta object for the containment reference list '{@link RequirementSourceConf.RequirementSources#getRequirementSources <em>Requirement Sources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Requirement Sources</em>'.
	 * @see RequirementSourceConf.RequirementSources#getRequirementSources()
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
	RequirementSourceConfFactory getRequirementSourceConfFactory();

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
		 * The meta object literal for the '{@link RequirementSourceConf.impl.RequirementSourcesImpl <em>Requirement Sources</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see RequirementSourceConf.impl.RequirementSourcesImpl
		 * @see RequirementSourceConf.impl.RequirementSourceConfPackageImpl#getRequirementSources()
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

} //RequirementSourceConfPackage

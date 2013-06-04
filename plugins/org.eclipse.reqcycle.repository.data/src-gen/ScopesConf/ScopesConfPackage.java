/**
 */
package ScopesConf;

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
 * @see ScopesConf.ScopesConfFactory
 * @model kind="package"
 * @generated
 */
public interface ScopesConfPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ScopesConf";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/ReqCycle/ScopesConf";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "ScopesConf";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ScopesConfPackage eINSTANCE = ScopesConf.impl.ScopesConfPackageImpl.init();

	/**
	 * The meta object id for the '{@link ScopesConf.impl.ScopesImpl <em>Scopes</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ScopesConf.impl.ScopesImpl
	 * @see ScopesConf.impl.ScopesConfPackageImpl#getScopes()
	 * @generated
	 */
	int SCOPES = 0;

	/**
	 * The feature id for the '<em><b>Scopes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPES__SCOPES = 0;

	/**
	 * The number of structural features of the '<em>Scopes</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPES_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Scopes</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPES_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link ScopesConf.Scopes <em>Scopes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Scopes</em>'.
	 * @see ScopesConf.Scopes
	 * @generated
	 */
	EClass getScopes();

	/**
	 * Returns the meta object for the containment reference list '{@link ScopesConf.Scopes#getScopes <em>Scopes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Scopes</em>'.
	 * @see ScopesConf.Scopes#getScopes()
	 * @see #getScopes()
	 * @generated
	 */
	EReference getScopes_Scopes();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ScopesConfFactory getScopesConfFactory();

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
		 * The meta object literal for the '{@link ScopesConf.impl.ScopesImpl <em>Scopes</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ScopesConf.impl.ScopesImpl
		 * @see ScopesConf.impl.ScopesConfPackageImpl#getScopes()
		 * @generated
		 */
		EClass SCOPES = eINSTANCE.getScopes();

		/**
		 * The meta object literal for the '<em><b>Scopes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCOPES__SCOPES = eINSTANCE.getScopes_Scopes();

	}

} //ScopesConfPackage

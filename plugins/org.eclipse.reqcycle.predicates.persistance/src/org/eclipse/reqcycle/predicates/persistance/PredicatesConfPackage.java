/**
 */
package org.eclipse.reqcycle.predicates.persistance;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * 
 * @see org.eclipse.reqcycle.predicates.persistance.PredicatesConfFactory
 * @model kind="package"
 * @generated
 */
public interface PredicatesConfPackage extends EPackage {

	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "predicates";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/reqcycle/predicates/persistance/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "predicatesconf";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	PredicatesConfPackage eINSTANCE = org.eclipse.reqcycle.predicates.persistance.impl.PredicatesConfPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.reqcycle.predicates.persistance.impl.PredicatesConfImpl <em>Predicates Conf</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.reqcycle.predicates.persistance.impl.PredicatesConfImpl
	 * @see org.eclipse.reqcycle.predicates.persistance.impl.PredicatesConfPackageImpl#getPredicatesConf()
	 * @generated
	 */
	int PREDICATES_CONF = 0;

	/**
	 * The feature id for the '<em><b>Predicates</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PREDICATES_CONF__PREDICATES = 0;

	/**
	 * The number of structural features of the '<em>Predicates Conf</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PREDICATES_CONF_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Predicates Conf</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PREDICATES_CONF_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link org.eclipse.reqcycle.predicates.persistance.api.PredicatesConf <em>Predicates Conf</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Predicates Conf</em>'.
	 * @see org.eclipse.reqcycle.predicates.persistance.api.PredicatesConf
	 * @generated
	 */
	EClass getPredicatesConf();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.reqcycle.predicates.persistance.api.PredicatesConf#getPredicates <em>Predicates</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Predicates</em>'.
	 * @see org.eclipse.reqcycle.predicates.persistance.api.PredicatesConf#getPredicates()
	 * @see #getPredicatesConf()
	 * @generated
	 */
	EReference getPredicatesConf_Predicates();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PredicatesConfFactory getPredicatesConfFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 * <li>each class,</li>
	 * <li>each feature of each class,</li>
	 * <li>each operation of each class,</li>
	 * <li>each enum,</li>
	 * <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	interface Literals {

		/**
		 * The meta object literal for the '{@link org.eclipse.reqcycle.predicates.persistance.impl.PredicatesConfImpl <em>Predicates Conf</em>}'
		 * class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.reqcycle.predicates.persistance.impl.PredicatesConfImpl
		 * @see org.eclipse.reqcycle.predicates.persistance.impl.PredicatesConfPackageImpl#getPredicatesConf()
		 * @generated
		 */
		EClass PREDICATES_CONF = eINSTANCE.getPredicatesConf();

		/**
		 * The meta object literal for the '<em><b>Predicates</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PREDICATES_CONF__PREDICATES = eINSTANCE.getPredicatesConf_Predicates();

	}

} //PredicatesConfPackage

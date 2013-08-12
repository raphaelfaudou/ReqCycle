/**
 */
package org.eclipse.reqcycle.predicates.persistance;

import org.eclipse.emf.ecore.EFactory;
import org.eclipse.reqcycle.predicates.persistance.api.PredicatesConf;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * 
 * @see org.eclipse.reqcycle.predicates.persistance.PredicatesConfPackage
 * @generated
 */
public interface PredicatesConfFactory extends EFactory {

	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	PredicatesConfFactory eINSTANCE = org.eclipse.reqcycle.predicates.persistance.impl.PredicatesConfFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Predicates Conf</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Predicates Conf</em>'.
	 * @generated
	 */
	PredicatesConf createPredicatesConf();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the package supported by this factory.
	 * @generated
	 */
	PredicatesConfPackage getPredicatesConfPackage();

} //PredicatesConfFactory

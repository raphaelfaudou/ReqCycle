/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Papa Issa Diakhate (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
/**
 */
package org.polarsys.reqcycle.predicates.persistance;

import org.eclipse.emf.ecore.EFactory;
import org.polarsys.reqcycle.predicates.persistance.api.PredicatesConf;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * 
 * @see org.polarsys.reqcycle.predicates.persistance.PredicatesConfPackage
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
	PredicatesConfFactory eINSTANCE = org.polarsys.reqcycle.predicates.persistance.impl.PredicatesConfFactoryImpl.init();

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

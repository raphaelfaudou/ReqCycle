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
package org.polarsys.reqcycle.predicates.persistance.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.polarsys.reqcycle.predicates.persistance.PredicatesConfFactory;
import org.polarsys.reqcycle.predicates.persistance.PredicatesConfPackage;
import org.polarsys.reqcycle.predicates.persistance.api.PredicatesConf;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class PredicatesConfFactoryImpl extends EFactoryImpl implements PredicatesConfFactory {

	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static PredicatesConfFactory init() {
		try {
			PredicatesConfFactory thePredicatesConfFactory = (PredicatesConfFactory)EPackage.Registry.INSTANCE.getEFactory(PredicatesConfPackage.eNS_URI);
			if(thePredicatesConfFactory != null) {
				return thePredicatesConfFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new PredicatesConfFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PredicatesConfFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch(eClass.getClassifierID()) {
		case PredicatesConfPackage.PREDICATES_CONF:
			return createPredicatesConf();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PredicatesConf createPredicatesConf() {
		PredicatesConfImpl predicatesConf = new PredicatesConfImpl();
		return predicatesConf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PredicatesConfPackage getPredicatesConfPackage() {
		return (PredicatesConfPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static PredicatesConfPackage getPackage() {
		return PredicatesConfPackage.eINSTANCE;
	}

} //PredicatesConfFactoryImpl

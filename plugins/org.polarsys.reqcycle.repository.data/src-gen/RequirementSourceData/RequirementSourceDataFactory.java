/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Anass Radouani (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
/**
 */
package RequirementSourceData;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see RequirementSourceData.RequirementSourceDataPackage
 * @generated
 */
public interface RequirementSourceDataFactory extends EFactory {

	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	RequirementSourceDataFactory eINSTANCE = RequirementSourceData.impl.RequirementSourceDataFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Section</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Section</em>'.
	 * @generated
	 */
	Section createSection();

	/**
	 * Returns a new object of class '<em>Simple Requirement</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Simple Requirement</em>'.
	 * @generated
	 */
	SimpleRequirement createSimpleRequirement();

	/**
	 * Returns a new object of class '<em>Requirement</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Requirement</em>'.
	 * @generated
	 */
	Requirement createRequirement();

	/**
	 * Returns a new object of class '<em>Requirements Container</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Requirements Container</em>'.
	 * @generated
	 */
	RequirementsContainer createRequirementsContainer();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	RequirementSourceDataPackage getRequirementSourceDataPackage();

} //RequirementSourceDataFactory

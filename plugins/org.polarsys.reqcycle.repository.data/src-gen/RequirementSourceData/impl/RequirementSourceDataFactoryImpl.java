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
package RequirementSourceData.impl;

import RequirementSourceData.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import RequirementSourceData.Requirement;
import RequirementSourceData.RequirementSourceDataFactory;
import RequirementSourceData.RequirementSourceDataPackage;
import RequirementSourceData.Section;
import RequirementSourceData.SimpleRequirement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RequirementSourceDataFactoryImpl extends EFactoryImpl implements RequirementSourceDataFactory {

	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static RequirementSourceDataFactory init() {
		try {
			RequirementSourceDataFactory theRequirementSourceDataFactory = (RequirementSourceDataFactory)EPackage.Registry.INSTANCE.getEFactory(RequirementSourceDataPackage.eNS_URI);
			if (theRequirementSourceDataFactory != null) {
				return theRequirementSourceDataFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new RequirementSourceDataFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RequirementSourceDataFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case RequirementSourceDataPackage.SECTION: return createSection();
			case RequirementSourceDataPackage.SIMPLE_REQUIREMENT: return createSimpleRequirement();
			case RequirementSourceDataPackage.REQUIREMENT: return createRequirement();
			case RequirementSourceDataPackage.REQUIREMENTS_CONTAINER: return createRequirementsContainer();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Section createSection() {
		SectionImpl section = new SectionImpl();
		return section;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SimpleRequirement createSimpleRequirement() {
		SimpleRequirementImpl simpleRequirement = new SimpleRequirementImpl();
		return simpleRequirement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Requirement createRequirement() {
		RequirementImpl requirement = new RequirementImpl();
		return requirement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RequirementsContainer createRequirementsContainer() {
		RequirementsContainerImpl requirementsContainer = new RequirementsContainerImpl();
		return requirementsContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RequirementSourceDataPackage getRequirementSourceDataPackage() {
		return (RequirementSourceDataPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static RequirementSourceDataPackage getPackage() {
		return RequirementSourceDataPackage.eINSTANCE;
	}

} //RequirementSourceDataFactoryImpl

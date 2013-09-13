/*****************************************************************************
 * Copyright (c) 2013 AtoS.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Anass RADOUANI (AtoS) anass.radouani@atos.net - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.reqcycle.repository.connector.local.editor.provider;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.reqcycle.repository.data.IDataModelManager;

import DataModel.provider.DataModelItemProviderAdapterFactory;


public class CustomDataModelItemProviderAdapterFactory extends DataModelItemProviderAdapterFactory {

	/**
	 * Instantiates a new custom data model item provider adapter factory.
	 */
	public CustomDataModelItemProviderAdapterFactory() {
		super();
		supportedTypes.add(EPackageImpl.class);
	}

	@Override
	public Adapter createRequirementAdapter() {
		if(requirementItemProvider == null) {
			//Use Custom Requirement Item Provider
			requirementItemProvider = new CustomRequirementItemProvider(this);
		};
		return requirementItemProvider;
	}

	@Override
	public Adapter createRequirementSourceAdapter() {
		if(requirementSourceItemProvider == null) {
			//Use Custom Requirement Source Item Provider
			requirementSourceItemProvider = new CustomRequirementSourceItemProvider(this);
		}
		return requirementSourceItemProvider;
	}

	@Override
	public Adapter createSectionAdapter() {
		if(sectionItemProvider == null) {
			//Use Custom Section Item Provider
			sectionItemProvider = new CustomSectionItemProvider(this);
		}
		return sectionItemProvider;
	}

	@Override
	public Adapter createRequirementSectionAdapter() {
		if(requirementSectionItemProvider == null) {
			//Use Custom Requirement Section Item Provider
			requirementSectionItemProvider = new CustomRequirementSectionItemProvider(this);
		}
		return requirementSectionItemProvider;
	}

	@Override
	public boolean isFactoryForType(Object type) {
		// Check the meta model NS_URI to support elements created with the Dynamic Data Model
		return (type instanceof EPackage && ((EPackage)type).getNsURI().contains(IDataModelManager.MODEL_NS_URI)) || super.isFactoryForType(type);
	}

}

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
package org.eclipse.reqcycle.repository.data.editor.provider;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.reqcycle.repository.data.IDataModelManager;
import org.eclipse.ziggurat.inject.ZigguratInject;

import DataModel.provider.DataModelItemProviderAdapterFactory;


public class CustomDataModelItemProviderAdapterFactory extends DataModelItemProviderAdapterFactory {

	IDataModelManager manager = ZigguratInject.make(IDataModelManager.class);

	public CustomDataModelItemProviderAdapterFactory() {
		super();
		supportedTypes.add(EPackageImpl.class);
	}

	@Override
	public Adapter createRequirementAdapter() {
		if(requirementItemProvider == null) {
			requirementItemProvider = new CustomRequirementItemProvider(this);
		};
		return requirementItemProvider;
	}

	@Override
	public Adapter createRequirementSourceAdapter() {
		if(requirementSourceItemProvider == null) {
			requirementSourceItemProvider = new CustomRequirementSourceItemProvider(this);
		}

		return requirementSourceItemProvider;
	}

	@Override
	public Adapter createSectionAdapter() {
		if(sectionItemProvider == null) {
			sectionItemProvider = new CustomSectionItemProvider(this);
		}

		return sectionItemProvider;
	}

	@Override
	public Adapter createRequirementSectionAdapter() {
		if(requirementSectionItemProvider == null) {
			requirementSectionItemProvider = new CustomRequirementSectionItemProvider(this);
		}

		return requirementSectionItemProvider;
	}

	@Override
	public boolean isFactoryForType(Object type) {
		// TODO Auto-generated method stub
		return (type instanceof EPackage && ((EPackage)type).getNsURI().contains(IDataModelManager.NS_URI)) || super.isFactoryForType(type);
	}

}

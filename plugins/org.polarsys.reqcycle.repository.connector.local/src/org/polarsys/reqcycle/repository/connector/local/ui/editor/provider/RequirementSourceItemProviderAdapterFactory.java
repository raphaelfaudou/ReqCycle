/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Anass Radouani (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.repository.connector.local.ui.editor.provider;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.polarsys.reqcycle.repository.data.IDataModelManager;

import RequirementSourceConf.provider.RequirementSourceConfItemProviderAdapterFactory;


public class RequirementSourceItemProviderAdapterFactory extends RequirementSourceConfItemProviderAdapterFactory {


	public RequirementSourceItemProviderAdapterFactory() {
		super();
		supportedTypes.add(EPackageImpl.class);
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
	public boolean isFactoryForType(Object type) {
		// Check the meta model NS_URI to support elements created with the Dynamic Data Model
		return (type instanceof EPackage && ((EPackage)type).getNsURI().contains(IDataModelManager.MODEL_NS_URI)) || super.isFactoryForType(type);
	}

}

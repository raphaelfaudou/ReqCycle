/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Anass Radouani (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.repository.data.util;

import java.util.Collection;

import org.eclipse.core.runtime.IAdapterFactory;
import org.polarsys.reqcycle.repository.data.IDataModelManager;
import org.polarsys.reqcycle.repository.data.types.IDataModel;
import org.polarsys.reqcycle.repository.data.types.IRequirementType;
import org.eclipse.ziggurat.inject.ZigguratInject;

import ScopeConf.Scope;


public class DataTypesAdapterFactory implements IAdapterFactory {

	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if(adaptableObject instanceof IDataModel) {
			if(String.class == adapterType) {
				return ((IDataModel)adaptableObject).getName();
			}
		}
		if(adaptableObject instanceof IRequirementType) {
			if(String.class == adapterType) {
				IRequirementType requirementType = (IRequirementType)adaptableObject;
				IDataModel dataModel = requirementType.getDataModel();
				return dataModel.getName() + "::" + requirementType.getName();
			}
		}
		if(adaptableObject instanceof Scope) {
			if(String.class == adapterType) {
				Scope scope = (Scope)adaptableObject;
				String dataModelURI = scope.getDataModelURI();
				IDataModelManager dataModelManager = ZigguratInject.make(IDataModelManager.class);
				Collection<IDataModel> dataModels = dataModelManager.getDataModelByURI(dataModelURI);
				IDataModel dataModel = null;
				if(dataModels.size() > 0) {
					dataModel = dataModels.iterator().next();
				}
				return dataModel != null ? dataModel.getName() + "::" + scope.getName() : scope.getName();
			}
		}
		return null;
	}

	@Override
	public Class[] getAdapterList() {
		return null;
	}

}

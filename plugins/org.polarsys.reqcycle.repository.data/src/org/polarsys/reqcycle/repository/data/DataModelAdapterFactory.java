/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Anass Radouani (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.repository.data;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.polarsys.reqcycle.repository.data.types.DataModelLabelProvider;
import org.polarsys.reqcycle.repository.data.types.IDataModel;
import org.polarsys.reqcycle.repository.data.types.IRequirementType;
import org.polarsys.reqcycle.repository.data.types.RequirementTypeLabelProvider;



public class DataModelAdapterFactory extends AdapterFactoryImpl {

	@Override
	public boolean isFactoryForType(Object type) {
		return type instanceof IDataModel || type instanceof IRequirementType;
	}

	@Override
	public Adapter adapt(Notifier target, Object type) {
		return super.adapt(target, type);
	}

	@Override
	protected Adapter createAdapter(Notifier target) {
		if(target instanceof IDataModel) {
			return new DataModelLabelProvider(this);
		}
		if(target instanceof IRequirementType) {
			return new RequirementTypeLabelProvider(this);
		}
		return super.createAdapter(target);
	}


}

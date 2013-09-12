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

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.reqcycle.repository.data.IDataModelManager;
import org.eclipse.reqcycle.repository.data.types.IRequirementType;
import org.eclipse.ziggurat.inject.ZigguratInject;

import DataModel.DataModelFactory;
import DataModel.DataModelPackage;
import DataModel.RequirementSource;
import DataModel.provider.RequirementSourceItemProvider;


/**
 * The Class CustomRequirementSourceItemProvider.
 */
public class CustomRequirementSourceItemProvider extends RequirementSourceItemProvider {

	IDataModelManager manager = ZigguratInject.make(IDataModelManager.class);

	/**
	 * Instantiates a new custom requirement source item provider.
	 * 
	 * @param adapterFactory
	 *        the adapter factory
	 */
	public CustomRequirementSourceItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if(childrenFeatures == null) {
			childrenFeatures = new ArrayList<EStructuralFeature>();
			childrenFeatures.add(DataModelPackage.Literals.REQUIREMENT_SOURCE__REQUIREMENTS);
		}
		return childrenFeatures;
	}

	@Override
	public String getText(Object object) {
		String text = "";

		String label = ((RequirementSource)object).getName();
		if(label != null && !label.isEmpty()) {
			text += label;
		}

		String uri = ((RequirementSource)object).getRepositoryUri();
		if(uri != null && !uri.isEmpty()) {
			text += " [ " + uri + " ] ";
		}

		return text;
	}

	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {

		//FIXME : Use element Data Model to get possible children
		for(IRequirementType type : manager.getAllRequirementTypes()) {
			newChildDescriptors.add(createChildParameter(DataModelPackage.Literals.REQUIREMENT_SOURCE__REQUIREMENTS, type.createInstance()));
		}

		newChildDescriptors.add(createChildParameter(DataModelPackage.Literals.REQUIREMENT_SOURCE__REQUIREMENTS, DataModelFactory.eINSTANCE.createSection()));

	}

}

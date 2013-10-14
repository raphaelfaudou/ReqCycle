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
package org.eclipse.reqcycle.repository.connector.local.ui.editor.provider;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.reqcycle.repository.data.IDataModelManager;
import org.eclipse.reqcycle.repository.data.types.IRequirementType;
import org.eclipse.ziggurat.inject.ZigguratInject;

import RequirementSourceData.AbstractElement;
import RequirementSourceData.Requirement;
import RequirementSourceData.RequirementSourceDataFactory;
import RequirementSourceData.RequirementSourceDataPackage;
import RequirementSourceData.provider.RequirementItemProvider;


/**
 * The Class CustomRequirementItemProvider.
 */
public class CustomRequirementItemProvider extends RequirementItemProvider {

	@Inject
	IDataModelManager manager;

	/**
	 * Instantiates a new custom requirement section item provider.
	 * 
	 * @param adapterFactory
	 *        the adapter factory
	 */
	public CustomRequirementItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
		ZigguratInject.inject(this);
	}

	@Override
	public String getText(Object object) {
		String text = "";

		String id = ((Requirement)object).getId();
		String name = ((Requirement)object).getName();

		if(id != null && !id.isEmpty()) {
			text += "[ id : " + id;
		}

		if(name != null && !name.isEmpty()) {
			text += text.isEmpty() ? "[ " : " | ";
			text += "name : " + name;
		}

		if(!text.isEmpty()) {
			text += " ]";
		}

		return "Requirement " + text;
	}

	@Override
	public void setPropertyValue(Object object, String property, Object value) {
		// TODO Auto-generated method stub
		super.setPropertyValue(object, property, value);
	}

	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if(itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);
			if(object instanceof Requirement) {
				EList<EStructuralFeature> features = ((Requirement)object).eClass().getEStructuralFeatures();
				for(EStructuralFeature eStructuralFeature : features) {
					itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(), eStructuralFeature.getName(), "", eStructuralFeature, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
				}
			}
		}
		return itemPropertyDescriptors;
	}

	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		//FIXME : Use element Data Model to get possible children
		//Gets Dynamic Data Model possible children
		for(IRequirementType type : manager.getAllRequirementTypes()) {
			Requirement instance = type.createInstance();
			if(object instanceof AbstractElement) {
				instance.getScopes().addAll(((AbstractElement)object).getScopes());
			}
			newChildDescriptors.add(createChildParameter(RequirementSourceDataPackage.Literals.SECTION__CHILDREN, instance));
		}
		newChildDescriptors.add(createChildParameter(RequirementSourceDataPackage.Literals.SECTION__CHILDREN, RequirementSourceDataFactory.eINSTANCE.createSection()));
	}
}

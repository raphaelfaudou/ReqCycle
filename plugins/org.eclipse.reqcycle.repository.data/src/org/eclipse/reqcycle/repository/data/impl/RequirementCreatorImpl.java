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
package org.eclipse.reqcycle.repository.data.impl;

import javax.inject.Singleton;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.reqcycle.repository.data.IRequirementCreator;

import DataModel.Contained;
import DataModel.DataModelPackage;
import DataModel.Requirement;
import DataModel.RequirementSection;
import DataModel.Section;
import MappingModel.AttributeMapping;

@Singleton
public class RequirementCreatorImpl implements IRequirementCreator {

	public RequirementCreatorImpl() {
	}

	@Override
	public Contained addObject(EClass objectType, String id, String name, String uri) throws Exception {

		Contained contained = null;
		EObject object;

		object = objectType.getEPackage().getEFactoryInstance().create(objectType);

		if(object instanceof Contained) {
			contained = (Contained) object;
		} else {
			throw new Exception("Error while creating a " + objectType.getName() + " element.");
		}
		
		//		EStructuralFeature sf = objectType.getEStructuralFeature("uri");
		//		
		//		if(uri != null && sf != null) {
		//			object.eSet(sf, uri);
		//		}
		//		
		//		sf = objectType.getEStructuralFeature("id");
		//		if(id != null && sf != null) {
		//			object.eSet(sf, id);
		//		}
		//		
		//		sf = objectType.getEStructuralFeature("name");
		//		if(name != null && sf != null) {
		//			object.eSet(sf, name);
		//		}

		if(uri != null) {
			contained.setUri(uri);
		}

		if(id != null) {
			contained.setId(id);
		}

		if(name != null) {
			contained.setName(name);
		}
		return contained;

	}

	@Override
	public void addAttribute(Contained object, EAttribute attributeName, Object value) throws Exception {
		EStructuralFeature feature = object.eClass().getEStructuralFeature(attributeName.getName());
		object.eSet(feature, value);
	}

	public void addAttribute(AttributeMapping attributeMapping, Contained element, Object value) throws Exception {
		if(attributeMapping == null) {
			throw new Exception();
		}
		addAttribute(element, attributeMapping.getTargetAttribute(), value);
	}

	@Override
	public Requirement createRequirement(String id, String name, String uri) throws Exception {
		return (Requirement)addObject(DataModelPackage.Literals.REQUIREMENT, id, name, uri);
	}

	@Override
	public RequirementSection createRequirementSection(String id, String name, String uri) throws Exception {
		return (RequirementSection)addObject(DataModelPackage.Literals.REQUIREMENT_SECTION, id, name, uri);
	}

	@Override
	public Section createSection(String id, String name, String uri) throws Exception {
		return (Section)addObject(DataModelPackage.Literals.SECTION, id, name, uri);
	}
}

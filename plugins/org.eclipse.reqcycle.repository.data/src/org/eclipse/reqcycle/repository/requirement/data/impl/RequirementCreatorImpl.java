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
package org.eclipse.reqcycle.repository.requirement.data.impl;

import javax.inject.Singleton;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.reqcycle.repository.requirement.data.IRequirementCreator;

import CustomDataModel.CustomDataModelFactory;
import CustomDataModel.CustomDataModelPackage;
import DataModel.Contained;
import DataModel.DataModelPackage;
import DataModel.ReachableObject;
import DataModel.ReachableSection;
import DataModel.Requirement;
import DataModel.RequirementSection;
import MappingModel.AttributeMapping;

@Singleton
public class RequirementCreatorImpl implements IRequirementCreator {

	public RequirementCreatorImpl() {
	}

	@Override
	public Contained addObject(EClass objectType, String id, String name, String uri) {
		
		Contained contained = null;
		EObject object;
		if (CustomDataModelPackage.eNS_URI.equals(objectType.getEPackage().getNsURI())){
			object = CustomDataModelFactory.eINSTANCE.create(objectType);
		} else {
			object = EcoreUtil.create(objectType);
		}
		
		if(object instanceof Contained) {
			contained = (Contained)object;
			if (uri!=null)
			{
				((Contained) contained).setUri(uri);
			}
			
			if(id != null) {
				contained.setId(id);
			}
			if(name != null) {
				contained.setName(name);
			}
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
	public ReachableSection createReachableSection(String id, String name, String uri) {
		return (ReachableSection)addObject(DataModelPackage.Literals.REACHABLE_SECTION, id, name, uri);
	}

	@Override
	public ReachableObject createReachableObject(String id, String name, String uri) {
		return (ReachableObject)addObject(DataModelPackage.Literals.REACHABLE_OBJECT, id, name, uri);
	}

	@Override
	public Requirement createRequirement(String id, String name, String uri) {
		return (Requirement)addObject(DataModelPackage.Literals.REQUIREMENT, id, name, uri);
	}

	@Override
	public RequirementSection createRequirementSection(String id, String name, String uri) {
		return (RequirementSection)addObject(DataModelPackage.Literals.REQUIREMENT_SECTION, id, name, uri);
	}
}

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

/**
 * 
 */
package org.eclipse.reqcycle.repository.requirement.data;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;

import DataModel.Contained;
import DataModel.ReachableObject;
import DataModel.ReachableSection;
import DataModel.Requirement;
import DataModel.RequirementSection;
import MappingModel.AttributeMapping;

public interface IRequirementCreator {
	
	public Contained addObject(EClass eClass, String id, String name, String uri);
	
	public void addAttribute(Contained obj, EAttribute attributeName, Object object) throws Exception;
	
	public void addAttribute(AttributeMapping attributeMapping, Contained element, Object value) throws Exception;
	
	public ReachableSection createReachableSection(String id, String name, String uri);
	
	public ReachableObject createReachableObject(String id, String name, String uri);
	
	public Requirement createRequirement(String id, String name, String uri);
	
	public RequirementSection createRequirementSection(String id, String name, String uri);
}

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
package org.eclipse.reqcycle.repository.data;

import java.util.Collection;

import org.eclipse.reqcycle.repository.data.types.IAttribute;
import org.eclipse.reqcycle.repository.data.types.IAttributeType;
import org.eclipse.reqcycle.repository.data.types.IDataModel;
import org.eclipse.reqcycle.repository.data.types.IEnumerationType;
import org.eclipse.reqcycle.repository.data.types.IEnumerator;
import org.eclipse.reqcycle.repository.data.types.IRequirementType;

import DataModel.Scope;

/**
 * The Interface IDataModelManager.
 */
public interface IDataModelManager {

	/**
	 * Discard unsaved changes.
	 */
	public void discardUnsavedChanges();

	/**
	 * Save.
	 */
	public void save();

	/**
	 * Adds the data model.
	 * 
	 * @param dataModel
	 *        the data model to add
	 */
	public void addDataModel(IDataModel dataModel);

	/**
	 * Creates the data model.
	 * 
	 * @param name
	 *        the data model name
	 * @return the Data Model
	 */
	public IDataModel createDataModel(String name);

	/**
	 * Creates the scope.
	 * 
	 * @param name
	 *        the scope name
	 * @return the scope
	 */
	public Scope createScope(String name);

	/**
	 * Adds the requirement types to a Data Model.
	 * 
	 * @param dataModel
	 *        the data model container
	 * @param dataTypes
	 *        the data types to add
	 */
	public void addRequirementTypes(IDataModel dataModel, IRequirementType... dataTypes);

	/**
	 * Adds the enumeration types to a Data Model.
	 * 
	 * @param dataModel
	 *        the data model container
	 * @param enumerationTypes
	 *        the enumeration types to add
	 */
	public void addEnumerationTypes(IDataModel dataModel, IEnumerationType... enumerationTypes);

	/**
	 * Adds the scopes to a Data Model.
	 * 
	 * @param dataModel
	 *        the data model container
	 * @param scopes
	 *        the scopes to add
	 */
	public void addScopes(IDataModel dataModel, Scope... scopes);

	/**
	 * Gets the data model.
	 * 
	 * @param name
	 *        the data model name
	 * @return the data model
	 */
	public IDataModel getDataModel(String name);

	/**
	 * Gets all data models.
	 * 
	 * @return all data models
	 */
	public Collection<IDataModel> getAllDataModels();

	//	/**
	//	 * Gets the requirement type.
	//	 *
	//	 * @param dataModel the data model container
	//	 * @param name the name 
	//	 * @return the requirement type
	//	 */
	//	public IRequirementType getRequirementType(IDataModel dataModel, String name);
	//
	//	/**
	//	 * Gets the requirement types.
	//	 *
	//	 * @param dataModel the data model
	//	 * @return the requirement types
	//	 */
	//	public Collection<IRequirementType> getRequirementTypes(IDataModel dataModel);

	/**
	 * Gets all requirement types.
	 * 
	 * @return all requirement types
	 */
	public Collection<IRequirementType> getAllRequirementTypes();

	//	/**
	//	 * Gets the enumeration type.
	//	 * 
	//	 * @param dataModel
	//	 *        the data model
	//	 * @param name
	//	 *        the name
	//	 * @return the enumeration type
	//	 */
	//	public IEnumerationType getEnumerationType(IDataModel dataModel, String name);
	//
	//	/**
	//	 * Gets the enumeration types.
	//	 * 
	//	 * @param dataModel
	//	 *        the data model
	//	 * @return the enumeration types
	//	 */
	//	public Collection<IEnumerationType> getEnumerationTypes(IDataModel dataModel);

	/**
	 * Gets all enumeration types.
	 * 
	 * @return all enumeration types
	 */
	public Collection<IEnumerationType> getAllEnumerationTypes();

	//	/**
	//	 * Gets the scope.
	//	 * 
	//	 * @param dataModel
	//	 *        the data model
	//	 * @param name
	//	 *        the name
	//	 * @return the scope
	//	 */
	//	public Scope getScope(IDataModel dataModel, String name);

	//	/**
	//	 * Gets the scope.
	//	 * 
	//	 * @param DataModelName
	//	 *        the data model name
	//	 * @param ScopeName
	//	 *        the scope name
	//	 * @return the scope
	//	 */
	//	public Scope getScope(String DataModelName, String ScopeName);

	/**
	 * Gets the scopes.
	 * 
	 * @param name
	 *        the scope name
	 * @return the scopes with the given name
	 */
	public Collection<Scope> getScopes(String name);

	//	/**
	//	 * Gets the scopes.
	//	 * 
	//	 * @param dataModel
	//	 *        the data model
	//	 * @return the scopes
	//	 */
	//	public Collection<Scope> getScopes(IDataModel dataModel);

	/**
	 * Gets all scopes.
	 * 
	 * @return all scopes
	 */
	public Collection<Scope> getAllScopes();

	//	/**
	//	 * Creates the instance.
	//	 * 
	//	 * @param requirementType
	//	 *        the requirement type
	//	 * @return the e object
	//	 */
	//	public EObject createInstance(IRequirementType requirementType);

	/**
	 * Creates requirement type.
	 * 
	 * @param name
	 *        the requirement type name
	 * @return the requirement type
	 */
	public IRequirementType createRequirementType(String name);

	/**
	 * Creates the enumeration type.
	 * 
	 * @param name
	 *        the enumeration type name
	 * @return the enumeration type
	 */
	public IEnumerationType createEnumerationType(String name);

	/**
	 * Creates enumerator.
	 * 
	 * @param name
	 *        the enumerator name
	 * @return the enumerator
	 */
	public IEnumerator createEnumerator(String name);

	/**
	 * Creates attribute
	 * 
	 * @param name
	 *        the attribute name
	 * @param type
	 *        the Attribute type
	 * @return the attribute
	 */
	public IAttribute createAttribute(String name, IAttributeType type);

	/**
	 * Creates attribute.
	 * 
	 * @param name
	 *        the attribute name
	 * @param type
	 *        the Enumeration type
	 * @return the attribute
	 */
	public IAttribute createAttribute(String name, IEnumerationType type);

	/**
	 * Gets the data model.
	 * 
	 * @param s
	 *        the scope
	 * @return the data model containing the given scope
	 */
	public IDataModel getDataModel(Scope s);

	/**
	 * Gets the analysis scope.
	 * 
	 * @return the analysis scope
	 */
	public Scope getAnalysisScope();

}

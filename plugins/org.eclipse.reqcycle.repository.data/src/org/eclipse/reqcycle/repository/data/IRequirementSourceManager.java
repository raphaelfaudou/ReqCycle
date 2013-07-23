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

import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.resource.ResourceSet;

import DataModel.RequirementSource;


public interface IRequirementSourceManager {

	/**
	 * Adds a newly created repository to repositories list
	 * 
	 * @param repository
	 *        the repository to add
	 */
	public void addRepository(final RequirementSource repository, ResourceSet rs);

	/**
	 * Remove a repository from repositories list
	 * 
	 * @param repository
	 */
	public void removeRequirementSource(final RequirementSource repository);

	/**
	 * Gets an existing repository
	 * 
	 * @param kind
	 *        the repository kind
	 * @param urlString
	 *        the repository url
	 * @return
	 */
	public RequirementSource getRepository(String kind, String urlString);

	/**
	 * Gets repositories for a connector Id
	 * 
	 * @param connectorId
	 *        the connector Id
	 * @return Set of repositories
	 */
	public Set<RequirementSource> getRepositories(String connectorId);

	/**
	 * Gets all repositories
	 * 
	 * @return Repositories collection
	 */
	public Set<RequirementSource> getRepositories();
	
	/**
	 * Gets Connector id to repositories map
	 * 
	 * @return map Connector id to repositories
	 */
	public Map<String, Set<RequirementSource>> getRepositoryMap();
	
	/**
	 * Removes a connector repositories 
	 * 
	 * @param connectorId the connector id
	 */
	public void removeConnectorRepositories(String connectorId);

	public void remove(Object toRemove);
	
	public void removeRequirements(RequirementSource repository);
	
}

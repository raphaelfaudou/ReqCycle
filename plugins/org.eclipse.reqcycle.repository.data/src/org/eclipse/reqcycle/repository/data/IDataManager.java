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

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import DataModel.RequirementSource;


public interface IDataManager {

	/**
	 * Adds a newly created repository to repositories list
	 * 
	 * @param requirementSource
	 *        the repository to add
	 */
	public void addRequirementSource(final RequirementSource requirementSource);

	/**
	 * Remove a repository from repositories list
	 * 
	 * @param requirementSource
	 */
	public void removeRequirementSource(final RequirementSource requirementSource);

	/**
	 * Gets an existing repository
	 * 
	 * @param kind
	 *        the repository kind
	 * @param urlString
	 *        the repository url
	 * @return
	 */
	public RequirementSource getRequirementSource(String kind, String urlString);

	/**
	 * Gets repositories for a connector Id
	 * 
	 * @param connectorId
	 *        the connector Id
	 * @return Set of repositories
	 */
	public Set<RequirementSource> getRequirementSources(String connectorId);

	/**
	 * Gets all repositories
	 * 
	 * @return Repositories collection
	 */
	public Set<RequirementSource> getRequirementSource();

	/**
	 * Gets Connector id to repositories map
	 * 
	 * @return map Connector id to repositories
	 */
	public Map<String, Set<RequirementSource>> getRepositoryMap();

	/**
	 * Removes a connector repositories
	 * 
	 * @param connectorId
	 *        the connector id
	 */
	public void removeRequirementSources(String connectorId);

	public void addListener(IListener listener);

	public void removeListener(IListener listener);

	/**
	 * Save the Data Model
	 * 
	 * @throws IOException
	 *         Signals that an I/O exception has occurred.
	 */
	public void save() throws IOException;

	/**
	 * Notify change.
	 * 
	 * @param event
	 *        the event
	 * @param data
	 *        the data
	 */
	public void notifyChanger(String event, Object data);

}

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
package org.eclipse.reqcycle.repository.connector;

import java.util.Collection;

import DataModel.RequirementSource;


public interface IConnectorManager {


	/**
	 * Gets all repositories connectors
	 * 
	 * @return Collection of repositories connectors
	 */
	public Collection<IConnector> getAllConnectors();

	/**
	 * Gets a repository connector by his id
	 * 
	 * @param connectorId the connector id
	 * 
	 * @return the corresponding connector
	 */
	public IConnector getConnector(String connectorId);

	/**
	 * Get the requirement source connector id
	 * 
	 * @param requirementSource the Requirement Source
	 * 
	 * @return the corresponding connector id
	 */
	public String getRequirementSourceConnectorId(RequirementSource requirementSource);

	/**
	 * Get the requirement source connector
	 * 
	 * @param requirementSource the Requirement Source
	 * 
	 * @return the corresponding connector
	 */
	public IConnector getRequirementSourceConnector(RequirementSource requirementSource);
	
}

/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Anass Radouani (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.repository.connector;

import java.util.Collection;


public interface IConnectorManager {


	/**
	 * Gets all repositories connectors
	 * 
	 * @return Collection of repositories connectors
	 */
	public Collection<ConnectorDescriptor> getAllConnectors();

	/**
	 * Gets a repository connector by his id
	 * 
	 * @param connectorId
	 *        the connector id
	 * @return the corresponding connector
	 */
	public ConnectorDescriptor get(String connectorId);


}

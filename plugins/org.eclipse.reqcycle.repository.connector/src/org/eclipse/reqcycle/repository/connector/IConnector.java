/*****************************************************************************
 * Copyright (c) 2013 AtoS.
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

import org.eclipse.core.runtime.IProgressMonitor;

import DataModel.RequirementSource;

/**
 *
 */
public interface IConnector {

	/**
	 * Gets the connector type
	 * 
	 * @return the connector type
	 */
	public String getConnectorId();

	/**
	 * Gets the connector label
	 * 
	 * @return the connector label
	 */
	public String getLabel();

	/**
	 * Creates new requirement source repository
	 * 
	 * @return new requirement source repository (not null)
	 */
	public RequirementSource createRequirementSource();

	
	/**
	 * Fills the repository requirements
	 * 
	 * @param repository the repository
	 * 
	 * @param progressMonitor
	 * 
	 * @throws Exception
	 */
	public void fillRequirements(RequirementSource repository, IProgressMonitor progressMonitor) throws Exception;

	/**
	 * @param requirementSource
	 */
	public void editMapping(RequirementSource requirementSource);

}

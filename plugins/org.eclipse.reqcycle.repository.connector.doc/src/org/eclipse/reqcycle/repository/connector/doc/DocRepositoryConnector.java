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
 *  Anass RADOUANI (AtoS) anass.radouani@gmail.com - Initial API and implementation
 *
 *****************************************************************************/

package org.eclipse.reqcycle.repository.connector.doc;

import javax.inject.Inject;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.reqcycle.core.ILogger;
import org.eclipse.reqcycle.repository.connector.IRepositoryConnector;
import org.eclipse.reqcycle.repository.connector.IRepositoryConstants;
import org.eclipse.reqcycle.repository.connector.IRequirementSourceRepository;
import org.eclipse.reqcycle.repository.connector.impl.RequirementSourceRepository;

public class DocRepositoryConnector implements IRepositoryConnector {

	@Inject ILogger logger;
	
	/**
	 * Constructor
	 */
	public DocRepositoryConnector() {
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.reqcycle.repository.connector.IRepositoryConnector#getConnectorId()
	 */
	@Override
	public String getConnectorId() {
		return "DOC";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.reqcycle.repository.connector.IRepositoryConnector#getLabel()
	 */
	@Override
	public String getLabel() {
		return "Document Connector Stub";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.reqcycle.repository.connector.IRepositoryConnector#createRepository()
	 */
	@Override
	public IRequirementSourceRepository createRepository() {
		IRequirementSourceRepository repository = new RequirementSourceRepository();
		try {
			repository.setProperty(IRepositoryConstants.PROPERTY_CONNECTOR_ID, getConnectorId());
		} catch (Exception e) {
			boolean debug = logger.isDebug(Activator.OPTIONS_DEBUG, Activator.getDefault());
			if(debug) {
				logger.trace("Property " + IRepositoryConstants.PROPERTY_CONNECTOR_ID + " can't be set on " + repository.getRepositoryLabel()
					+ "\n Trace : " + e.getMessage());
			}
		}
		return repository;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.reqcycle.repository.connector.IRepositoryConnector#getRequirements(org.eclipse.reqcycle.repository.connector.IRequirementSourceRepository, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void fillRequirements(IRequirementSourceRepository repository, IProgressMonitor progressMonitor) {
	}

}

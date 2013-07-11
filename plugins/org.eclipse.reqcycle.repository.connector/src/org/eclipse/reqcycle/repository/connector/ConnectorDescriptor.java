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

import org.eclipse.jface.resource.ImageDescriptor;

public class ConnectorDescriptor {

	/** The repository connector */
	private IConnector connector;

	/** The connector extension point name attribute */
	private String name;

	/** The connector extension point id attribute */
	private String id;

	/** The image descriptor of the connector */
	private ImageDescriptor imageDescriptor;

	/**
	 * The Constructor
	 * 
	 * @param connector
	 *        The connector
	 * @param name
	 *        The connector extension point name attribute
	 * @param id
	 *        The connector extension point id attribute
	 */
	public ConnectorDescriptor(IConnector connector, String name, String id, ImageDescriptor imageDescriptor) {
		this.connector = connector;
		this.name = name;
		this.id = id;
		this.imageDescriptor = imageDescriptor;
	}

	/**
	 * Gets the connector
	 * 
	 * @return the connector
	 */
	public IConnector getConnector() {
		return connector;
	}

	/**
	 * Gets the connector extension point name attribute
	 * 
	 * @return the connector extension point name attribute
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the connector extension point id attribute
	 * 
	 * @return The connector extension point id attribute
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Gets the image descriptor of the connector.
	 * 
	 * @return The connector extension point id attribute
	 */
	public ImageDescriptor getImageDescriptor() {
		return imageDescriptor;
	}

}

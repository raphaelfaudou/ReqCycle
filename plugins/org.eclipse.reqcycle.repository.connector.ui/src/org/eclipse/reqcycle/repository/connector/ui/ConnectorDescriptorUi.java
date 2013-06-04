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
package org.eclipse.reqcycle.repository.connector.ui;

import org.eclipse.jface.resource.ImageDescriptor;

/**
 * The Connector Descriptor UI containts ui information about the connect like the image Descriptor 
 */
public class ConnectorDescriptorUi {

	/** The connector Ui */
	private IConnectorUi connectorUi;

	/** The connector ui extension point id attribute */
	private String id;

	/** The connector ui image */
	private ImageDescriptor imageDescriptor;

	/**
	 * Constructor
	 * 
	 * @param connectorUi
	 *        The connector Ui
	 * @param id
	 *        The connector ui extension point id attribute
	 * @param imageDescriptor
	 *        The connector ui image
	 */
	public ConnectorDescriptorUi(IConnectorUi connectorUi, String id, ImageDescriptor imageDescriptor) {
		this.connectorUi = connectorUi;
		this.id = id;
		this.imageDescriptor = imageDescriptor;
	}

	/**
	 * Gets the connector ui
	 * 
	 * @return the connector ui
	 */
	public IConnectorUi getConnectorUi() {
		return connectorUi;
	}

	/**
	 * Gets the connector ui extension point id attribute
	 * 
	 * @return The connector ui extension point id attribute
	 */
	public String getId() {
		return id;
	}

	/**
	 * Gets the connector ui image
	 * 
	 * @return the connector ui image
	 */
	public ImageDescriptor getImageDescriptor() {
		return imageDescriptor;
	}
}

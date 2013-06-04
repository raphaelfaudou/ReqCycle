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
import org.eclipse.swt.graphics.Image;


public interface IConnectorManagerUi {

	/**
	 * Gets the connector Ui from the connector Id
	 * 
	 * @param connectorId
	 *        The connector Id
	 * @return The corresponding connector Ui
	 */
	public IConnectorUi getConnectorUi(String connectorId);

	/**
	 * Gets the connector image
	 * 
	 * @param connectorId
	 *        the connector Id
	 * @return the corresponding Image Descriptor
	 */
	public ImageDescriptor getImageDescriptor(String connectorId);

	/**
	 * Gets the connector image scaled
	 * 
	 * @param connectorId The connector Id
	 * @param width the width of the image
	 * @param height the height of the image
	 * @return the scaled image
	 */
	public Image getImage(String connectorId, int width, int height);
}

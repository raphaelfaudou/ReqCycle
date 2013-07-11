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
package org.eclipse.reqcycle.repository.connector.ui.impl;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.reqcycle.core.ILogger;
import org.eclipse.reqcycle.repository.connector.ui.Activator;
import org.eclipse.reqcycle.repository.connector.ui.ConnectorDescriptorUi;
import org.eclipse.reqcycle.repository.connector.ui.IConnectorManagerUi;
import org.eclipse.reqcycle.repository.connector.ui.IConnectorUi;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ziggurat.inject.ZigguratInject;

@Singleton
public class ConnectorManagerUiImpl implements IConnectorManagerUi {

	/** Connector ID to Connector ui Descriptor */
	private Map<String, ConnectorDescriptorUi> connectorsUi = new HashMap<String, ConnectorDescriptorUi>();

	/** Logger */
	ILogger logger = ZigguratInject.make(ILogger.class);
	
	/**
	 * Constructor
	 */
	ConnectorManagerUiImpl() {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IConfigurationElement[] extensions = reg.getConfigurationElementsFor(Activator.PLUGIN_ID, "connectorUi");
		for(IConfigurationElement iConfigurationElement : extensions) {
			try {
				IConnectorUi connector = (IConnectorUi)iConfigurationElement.createExecutableExtension("class");
				ZigguratInject.inject(connector);
				String id = iConfigurationElement.getAttribute("id");
				String icon = iConfigurationElement.getAttribute("icon");
				ImageDescriptor imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(iConfigurationElement.getNamespaceIdentifier(), icon);
				addConnectorUi(new ConnectorDescriptorUi(connector, id, imageDescriptor));
			} catch (CoreException e) {
				boolean debug = logger.isDebug(Activator.OPTIONS_DEBUG, Activator.getDefault());
				if(debug) {
					logger.trace("Can't create executable for : " + iConfigurationElement.getNamespaceIdentifier() + "\n Trace : " + e.getMessage());
				}
			}
		}
	}

	/**
	 * Adds connector ui descriptor
	 * 
	 * @param connectorDescriptorUi
	 *        the repository connector Ui descriptor
	 */
	protected void addConnectorUi(ConnectorDescriptorUi connectorDescriptorUi) {
		if(!connectorsUi.values().contains(connectorDescriptorUi)) {
			connectorsUi.put(connectorDescriptorUi.getConnectorUi().getConnectorId(), connectorDescriptorUi);
		}
	}

	public IConnectorUi getConnectorUi(String connectorId) {
		if(connectorsUi.get(connectorId) != null)
			return connectorsUi.get(connectorId).getConnectorUi();
		return null;
	}

	public ImageDescriptor getImageDescriptor(String connectorId) {
		if(connectorsUi.get(connectorId) != null)
			return connectorsUi.get(connectorId).getImageDescriptor();
		return null;
	}

	public Image getImage(String connectorId, int width, int height) {
		if(connectorsUi.get(connectorId) != null) {
			ImageDescriptor imageDescriptor = connectorsUi.get(connectorId).getImageDescriptor();
			return resize(imageDescriptor.createImage(), width, height);
		}
		return null;
	}

	private Image resize(Image image, int width, int height) {
		Image scaled = new Image(Display.getDefault(), width, height);
		GC gc = new GC(scaled);
		gc.setAntialias(SWT.ON);
		gc.setInterpolation(SWT.HIGH);
		gc.drawImage(image, 0, 0, image.getBounds().width, image.getBounds().height, 0, 0, width, height);
		gc.dispose();
		image.dispose();
		return scaled;
	}

}

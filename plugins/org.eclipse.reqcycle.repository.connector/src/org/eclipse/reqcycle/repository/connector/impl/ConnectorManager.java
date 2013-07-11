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
package org.eclipse.reqcycle.repository.connector.impl;

import java.util.Collection;
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
import org.eclipse.reqcycle.repository.connector.Activator;
import org.eclipse.reqcycle.repository.connector.ConnectorDescriptor;
import org.eclipse.reqcycle.repository.connector.IConnector;
import org.eclipse.reqcycle.repository.connector.IConnectorManager;
import org.eclipse.ui.plugin.AbstractUIPlugin;
//import org.eclipse.reqcycle.repository.connector.IRequirementSourceRepository;
import org.eclipse.ziggurat.inject.ZigguratInject;

@Singleton
public class ConnectorManager implements IConnectorManager {

	/** Connector ID to Connector Descriptor */
	private Map<String, ConnectorDescriptor> connectors = new HashMap<String, ConnectorDescriptor>();

	/** Logger */
	ILogger logger = ZigguratInject.make(ILogger.class);
	
	/**
	 * Constructor
	 */
	ConnectorManager() {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IConfigurationElement[] extensions = reg.getConfigurationElementsFor(Activator.PLUGIN_ID, "connectorCore");
		for(IConfigurationElement iConfigurationElement : extensions) {
			try {
				IConnector connector = (IConnector)iConfigurationElement.createExecutableExtension("class");
				ZigguratInject.inject(connector);
				String name = iConfigurationElement.getAttribute("name");
				String id = iConfigurationElement.getAttribute("id");
				String icon = iConfigurationElement.getAttribute("icon");
				ImageDescriptor imageDescriptor = null;
				if (icon != null && ! icon.isEmpty()){
					imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(iConfigurationElement.getNamespaceIdentifier(), icon);
				}
				ConnectorDescriptor repositoryConnectorDescriptor = new ConnectorDescriptor(connector, name, id, imageDescriptor);
				ZigguratInject.inject(repositoryConnectorDescriptor);
				addConnector(repositoryConnectorDescriptor, id);
			} catch (CoreException e) {
				boolean debug = logger.isDebug(Activator.OPTIONS_DEBUG, Activator.getDefault());
				if(debug) {
					logger.trace("Can't create executable for : " + iConfigurationElement.getNamespaceIdentifier() + "\n Stack : " +  e.getMessage());
				}
			}
		}
	}

	/**
	 * Adds a connector descriptor
	 * 
	 * @param connectorDescriptor
	 *        the connector descriptor
	 */
	protected void addConnector(ConnectorDescriptor connectorDescriptor, String connectorID) {
		if(!connectors.values().contains(connectorDescriptor)) {
			connectors.put(connectorID, connectorDescriptor);
		}
	}

	public Collection<ConnectorDescriptor> getAllConnectors() {
		return connectors.values();
	}

	@Override
	public ConnectorDescriptor get(String connectorId) {
		return connectors.get(connectorId);
	}
	
}

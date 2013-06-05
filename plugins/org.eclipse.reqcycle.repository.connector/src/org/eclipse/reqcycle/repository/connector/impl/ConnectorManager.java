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
import org.eclipse.reqcycle.core.ILogger;
import org.eclipse.reqcycle.repository.connector.Activator;
import org.eclipse.reqcycle.repository.connector.IConnector;
import org.eclipse.reqcycle.repository.connector.IConnectorManager;
//import org.eclipse.reqcycle.repository.connector.IRequirementSourceRepository;
import org.eclipse.ziggurat.inject.ZigguratInject;

import DataModel.RequirementSource;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;

@Singleton
public class ConnectorManager implements IConnectorManager {

	/** Connector ID to Connector Descriptor */
	private Map<String, ConnectorDescriptor> connectors = new HashMap<String, ConnectorDescriptor>();

	/** Logger */
	@Inject ILogger logger = ZigguratInject.make(ILogger.class);
	
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
				ConnectorDescriptor repositoryConnectorDescriptor = new ConnectorDescriptor(connector, name, id);
				ZigguratInject.inject(repositoryConnectorDescriptor);
				addConnector(repositoryConnectorDescriptor);
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
	protected void addConnector(ConnectorDescriptor connectorDescriptor) {
		if(!connectors.values().contains(connectorDescriptor)) {
			connectors.put(connectorDescriptor.getConnector().getConnectorId(), connectorDescriptor);
		}
	}

	public Collection<IConnector> getAllConnectors() {
		Collection<ConnectorDescriptor> descriptors = connectors.values();
		Collection<IConnector> connectors = Collections2.transform(descriptors, new Function<ConnectorDescriptor, IConnector>() {

			public IConnector apply(ConnectorDescriptor arg0) {
				return arg0.getConnector();
			}
		});
		
		return connectors;
	}

	public IConnector getConnector(String connectorId) {
		if (connectors.get(connectorId) != null) {
			return connectors.get(connectorId).getConnector();
		}
		return null;
	}

	public String getRequirementSourceConnectorId(RequirementSource requirementSource) {
		return requirementSource.getConnectorID();
	}

	public IConnector getRequirementSourceConnector(RequirementSource requirementSource) {
		return getConnector(requirementSource.getConnectorID());
	}
	
}

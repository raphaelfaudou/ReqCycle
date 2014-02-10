/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Anass Radouani (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/

/**
 * 
 */
package org.polarsys.reqcycle.repository.ui.providers;

import javax.inject.Inject;

import org.eclipse.jface.viewers.LabelProvider;
import org.polarsys.reqcycle.repository.connector.ConnectorDescriptor;
import org.polarsys.reqcycle.repository.connector.IConnectorManager;
import org.polarsys.reqcycle.repository.connector.ui.providers.ConnectorLabelProvider;
import org.polarsys.reqcycle.repository.data.util.DataUtil;
import org.polarsys.reqcycle.repository.ui.Activator;
import org.polarsys.reqcycle.repository.ui.Messages;
import org.polarsys.reqcycle.utils.inject.ZigguratInject;
import org.eclipse.swt.graphics.Image;

import RequirementSourceConf.RequirementSource;

public class RequirementSourceLabelProvider extends LabelProvider {

	/** Repository icon */
	protected static final String ICONS_STATUS_OFFLINE = Messages.GREY_STATUS_ICON;

	/** Repository icon */
	protected static final String ICONS_STATUS_ONLINE = Messages.GREEN_STATUS_ICON;

	/** Repository icon */
	protected static final String ICONS_STATUS_UNSYNCHRONIZED = Messages.YELLOW_STATUS_ICON;

	private @Inject
	IConnectorManager repositoryConnectorManager = ZigguratInject.make(IConnectorManager.class);

	@Override
	public String getText(Object obj) {
		if(obj instanceof String) {
			ConnectorDescriptor connectorDescriptor = repositoryConnectorManager.get((String)obj);
			if(connectorDescriptor != null) {
				return connectorDescriptor.getName();
			} else {
				return "";
			}
		}
		if(obj instanceof RequirementSource) {
			return DataUtil.getLabel(obj);
		}
		return obj.toString();
	}

	@Override
	public Image getImage(Object obj) {

		if(obj instanceof String) {
			ConnectorDescriptor connectorDescriptor = repositoryConnectorManager.get((String)obj);
			if(connectorDescriptor != null) {
				return ConnectorLabelProvider.createImage(connectorDescriptor, 16, 16);
			} else {
				return null;
			}
		}

		return Activator.getImageDescriptor(ICONS_STATUS_OFFLINE).createImage();
	}
}

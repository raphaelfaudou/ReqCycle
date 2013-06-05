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

package org.eclipse.reqcycle.repository.connector.ui.providers;

import javax.inject.Inject;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.reqcycle.repository.connector.IConnector;
import org.eclipse.reqcycle.repository.connector.ui.IConnectorManagerUi;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ziggurat.inject.ZigguratInject;

/**
 * Connector Label Provider
 */
public class ConnectorLabelProvider extends LabelProvider
{
	
	private @Inject IConnectorManagerUi connectorManagerUi = ZigguratInject.make(IConnectorManagerUi.class);
	
    public String getText(Object obj)
    {
        if (obj instanceof IConnector)
        {
            return ((IConnector) obj).getLabel();
        }
        return obj.toString();
    }

    public Image getImage(Object obj)
    {
        if (obj instanceof IConnector)
        {
            return connectorManagerUi.getImage(((IConnector) obj).getConnectorId(), 20, 20);
        }
        return null;
    }
}

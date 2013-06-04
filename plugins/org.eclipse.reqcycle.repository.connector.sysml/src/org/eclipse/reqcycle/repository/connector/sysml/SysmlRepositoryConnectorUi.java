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

package org.eclipse.reqcycle.repository.connector.sysml;

import org.eclipse.reqcycle.repository.connector.ui.IRepositoryConnectorUi;
import org.eclipse.reqcycle.repository.connector.ui.wizard.IRequirementSourceRepositoryPage;


public class SysmlRepositoryConnectorUi implements IRepositoryConnectorUi {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.reqcycle.repository.connector.ui.IRepositoryConnectorUi#getConnectorId()
	 */
	@Override
	public String getConnectorId() {
		return "SysML";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.reqcycle.repository.connector.ui.AbstractRepositoryConnectorUi#getSettingsPage(org.eclipse.reqcycle.repository.connector.
	 * ConnectorRepository)
	 */
	@Override
	public IRequirementSourceRepositoryPage getSettingsPage() {
		return new SysmlRepositorySettingPage("SysML Requirement Stub", "SysML Requirement Stub Description");
	}
}

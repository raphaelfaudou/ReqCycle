/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Anass Radouani (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.repository.connector.ui.wizard;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.IWizard;
import org.polarsys.reqcycle.repository.connector.IConnector;

/**
 * If the connector's configuration is made through a wizard, this interface should be used.
 */
public interface IConnectorWizard extends IConnector, IWizard {

	/** Initialize the wizard with the user selection */
	public void init(ISelection selection);

}

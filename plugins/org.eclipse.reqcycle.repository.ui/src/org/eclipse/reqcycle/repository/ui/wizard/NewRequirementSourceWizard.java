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
package org.eclipse.reqcycle.repository.ui.wizard;

import java.util.concurrent.Callable;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.reqcycle.repository.connector.ConnectorDescriptor;
import org.eclipse.reqcycle.repository.connector.IConnector;
import org.eclipse.reqcycle.repository.connector.ui.wizard.IConnectorWizard;
import org.eclipse.reqcycle.repository.ui.wizard.pages.SelectConnectorPage;
import org.eclipse.ziggurat.inject.ZigguratInject;

import DataModel.RequirementSource;
import DataModel.Scope;

public class NewRequirementSourceWizard extends Wizard implements IWizard {

	/** connector selection wizard page */
	private SelectConnectorPage selectConnectorPage;

	private Callable<RequirementSource> createRequirementSource = null;

	public NewRequirementSourceWizard() {
		setForcePreviousAndNextButtons(true);
		setNeedsProgressMonitor(true);
		setWindowTitle("Add Requirement Source");
	}

	@Override
	public void addPages() {
		selectConnectorPage = new SelectConnectorPage();
		ZigguratInject.inject(selectConnectorPage);
		addPage(selectConnectorPage);
	}

	@Override
	public boolean performFinish() {
		IConnector connector = getConnector();
		if (connector instanceof IConnectorWizard){
			boolean finish = ((IConnectorWizard)connector).performFinish();
			if (! finish){
				return false;
			}
		}
		createRequirementSource = connector.createRequirementSource();
		return createRequirementSource != null;
	}

	@Override
	public boolean canFinish() {
		if(getConnectorDescriptor() == null || getConnector() == null || getSourceName() == null || getSourceName().isEmpty()) {
			return false;
		}
		IConnector connector = getConnector();
		if(connector instanceof IConnectorWizard && ((IConnectorWizard)connector).getPageCount() == 0) {
			return false;
		}
		if(connector instanceof IConnectorWizard && !((IConnectorWizard)connector).canFinish()) {
			return false;
		}
		return true;
	}

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		IConnector connector = getConnector();
		if(page instanceof SelectConnectorPage && connector instanceof IConnectorWizard) {
			IConnectorWizard connectorWizard = (IConnectorWizard)connector;
			if(connectorWizard.getPageCount() == 0) {
				connectorWizard.addPages();
			}
			IWizardPage startingPage = connectorWizard.getStartingPage();
			if(startingPage != null) {
				startingPage.setWizard(this);
			}
			return startingPage;
		}
		
		if(connector instanceof IConnectorWizard) {
			IWizardPage nextPage = ((IConnectorWizard)connector).getNextPage(page);
			if(nextPage != null) {
				nextPage.setWizard(this);
			}
			return nextPage;
		}
		return null;
	}

	public ConnectorDescriptor getConnectorDescriptor() {
		if(selectConnectorPage != null) {
			return selectConnectorPage.getConnectorDescriptor();
		}
		return null;
	}

	public IConnector getConnector() {
		if(selectConnectorPage != null) {
			return selectConnectorPage.getConnector();
		}
		return null;
	}

//	public Scope getScope() {
//		if(selectConnectorPage != null) {
//			return selectConnectorPage.getScope();
//		}
//		//FIXME call
//		return null;
//	}

	public String getSourceName() {
		if(selectConnectorPage != null) {
			return selectConnectorPage.getSourceName();
		}
		return null;
	}

	public Callable<RequirementSource> getResult() {
		return createRequirementSource;
	}

}

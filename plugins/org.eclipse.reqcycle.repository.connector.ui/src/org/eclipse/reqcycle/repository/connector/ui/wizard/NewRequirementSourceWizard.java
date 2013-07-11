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
package org.eclipse.reqcycle.repository.connector.ui.wizard;

import javax.inject.Inject;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.reqcycle.core.ILogger;
import org.eclipse.reqcycle.repository.connector.IConnector;
import org.eclipse.reqcycle.repository.connector.ui.IConnectorManagerUi;
import org.eclipse.reqcycle.repository.connector.ui.IConnectorUi;
import org.eclipse.ziggurat.inject.ZigguratInject;

import DataModel.RequirementSource;
import DataModel.Scope;

public class NewRequirementSourceWizard extends Wizard implements IWizard
{
    /** connector selection wizard page */
    private SelectConnectorPage selectConnectorPage;
    
    /** selected connector */
    private IConnector connector;
    
    /** requirement source */
    private IRequirementSourceSettingPage settingsPage;
    
    /** the connector ui manager*/
    private IConnectorManagerUi connectorManagerUi = ZigguratInject.make(IConnectorManagerUi.class);
    
    /** the requirement source */
    private RequirementSource requirementSource;
    
    /** the connector ui */
	private IConnectorUi connectorUi;
	
	/** selected scope */
	private Scope scope;
	
	/** logger */
	private ILogger logger = ZigguratInject.make(ILogger.class);
	
	/** Setting wizard */
	private IWizard settingWizard;
    
    public NewRequirementSourceWizard()
    {
        setForcePreviousAndNextButtons(true);
        setNeedsProgressMonitor(true);
        setWindowTitle("Add Requirement Source");
    }

    @Override
    public void addPages()
    {
        selectConnectorPage = new SelectConnectorPage();
        addPage(selectConnectorPage);
    }
    
    @Override
    public boolean performFinish()
    {
//    	if(!canFinish()) {
//			return false;
//		}
//
//    	requirementSource = connector.createRequirementSource();
//    	
//		boolean finishAccepted = connectorUi.preFinish();
//		if(finishAccepted) {
//			connectorUi.performFinish(requirementSource);
//			if(!connectorUi.skipMapping()) {
//				try {
//					//TODO : give scope to fill requirement
//					scope = connectorUi.getScope();
//					connector.fillRequirements(requirementSource, new NullProgressMonitor());
//				} catch (Exception e) {
//					logger.error("Error while filling requirement");
//				}
//			}
//			return true;
//		}
//		return false;
    	
    	return true;
	}
    
    
    
    @Override
    public boolean canFinish()
    {
    	//Check that
    	//Connector, ConnectorUI and the wizard are set
    	//Can finish the connector's setting wizard
    	//SelectConnectorPage complete
    	
    	return connector != null && connectorUi != null && settingWizard != null && settingWizard.canFinish();
//        return (selectConnectorPage == null || selectConnectorPage.isPageComplete())
//                && getContainer().getCurrentPage() != selectConnectorPage 
//                && connectorUi.canFinish();
    }
    
    
    @Override
    public IWizardPage getNextPage(IWizardPage page)
    {
    	
    	if(page == selectConnectorPage) {
			connector = selectConnectorPage.getConnector();
			if(connector != null) {
				connectorUi = connectorManagerUi.getConnectorUi(connector.getConnectorId());
				settingWizard = connectorUi.getSettingWizard();
//				settingWizard.setContainer(getContainer());
				settingWizard.addPages();
				return settingWizard.getStartingPage();
			}
		}
//    	else if (connector != null && connectorUi!= null && settingWizard != null)
//    	{
//    		return settingWizard.getNextPage(page);
//    	}
//    	
//    	//TODO refactor method
//    	IRequirementSourceSettingPage nextPage = null;
//
//    	if (page == selectConnectorPage) {
//            connector = selectConnectorPage.getConnector();
//            if(connector != null) {
//            	connectorUi = connectorManagerUi.getConnectorUi(connector.getConnectorId());
//			}
//            settingsPage = null;
//        } 
//    	else if ( page instanceof IRequirementSourceSettingPage)
//    	{
//    		settingsPage = (IRequirementSourceSettingPage)page;
//    	}
//        
//		if(connector != null && connectorUi != null) {
//			nextPage = connectorUi.getNextPage(settingsPage);
//		}
//		
//		if(nextPage != null) {
//			settingsPage = nextPage;
//			if(settingsPage.getImage() == null) {
//        		settingsPage.setImageDescriptor( ImageDescriptor.createFromImage(connectorManagerUi.getImage(connector.getConnectorId(), 60, 60)));
//        	}
//        	settingsPage.setWizard(this);
//        	return nextPage;
//		}
        
        return super.getNextPage(page);
    }

    /**
     * Gets the repository connector
     * 
     * @return the repository connector
     */
    public IConnector getConnector()
    {
        return connector;
    }
    
    
	/**
	 * Gets the requirement source
	 * 
	 * @return the requirement source
	 */
	public RequirementSource getRequirementSource() {
		return requirementSource;
	}
	
	/**
	 * Gets selected scope
	 * 
	 * @return Scope
	 */
	public Scope getScope() {
		return scope;
	}
	
}

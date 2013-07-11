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
package org.eclipse.reqcycle.repository.connector.ui;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.reqcycle.repository.connector.ui.wizard.IRequirementSourceSettingPage;

import DataModel.RequirementSource;
import DataModel.Scope;

public interface IConnectorUi {

	/**
	 * Gets the connector Id
	 * 
	 * @return the connector Id
	 */
	public String getConnectorId();

	/**
	 * Gets the connector next configuration wizard page
	 * 
	 * @return the next configuration wizard page
	 */
	public IRequirementSourceSettingPage getNextPage(IRequirementSourceSettingPage page);
	
	/**
	 * Creates filled with setting information, must be filled with the IConnector
	 * 
	 * @param requirementSource the requirement source
	 */
	public  void performFinish(RequirementSource requirementSource);
	
	public IWizard getSettingWizard();
	
	/**
	 * Gets if the requirement source can be created
	 * 
	 * @return true if the requirement source can be created
	 */
	public boolean preFinish();

	/**
	 * Gets if the wizard can finish
	 * 
	 * @return true if the wizard can finish
	 */
	public boolean canFinish();
	
	
	/**
	 * Gets the selected scope
	 * 
	 * @return the selected scope
	 */
	public Scope getScope();
	
	/**
	 * Gets the label provider corresponding to the source
	 * 
	 * @return the source label provider
	 */
	public LabelProvider getSourceLabelProvider();

	boolean skipMapping();
}

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

import org.eclipse.jface.wizard.IWizardPage;

import DataModel.RequirementSource;

public interface IRequirementSourceSettingPage extends IWizardPage
{
    /**
     * Gets if wizard can finish or not
     * 
     * @param repository the new repository
     * 
     * @return true if the wizard can finish
     */
    public boolean preFinish(RequirementSource repository);
    
    public boolean performMapping();
    
}

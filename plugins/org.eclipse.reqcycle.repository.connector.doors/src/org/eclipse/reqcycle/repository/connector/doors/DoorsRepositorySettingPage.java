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

package org.eclipse.reqcycle.repository.connector.doors;

import javax.inject.Inject;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.reqcycle.core.ILogger;
import org.eclipse.reqcycle.repository.connector.IRepositoryConstants;
import org.eclipse.reqcycle.repository.connector.IRequirementSourceRepository;
import org.eclipse.reqcycle.repository.connector.ui.wizard.IRequirementSourceRepositoryPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class DoorsRepositorySettingPage extends WizardPage implements IRequirementSourceRepositoryPage {

    private Text name;

    private String text;
    
    @Inject ILogger logger;

    
	public DoorsRepositorySettingPage(String title, String description) {
		super(title);
		setTitle(title);
		setDescription(description);
	}
    
	/**
	 * @return the connector Id
	 */
	public String getConnectorId() {
		return "DOORS";
	}
	
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(Composite parent)
    {
        Composite compositeContainer = new Composite(parent, SWT.NULL);
        setControl(compositeContainer);
        compositeContainer.setLayout(new GridLayout(2, false));

        Label lblName = new Label(compositeContainer, SWT.NONE);
        lblName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
        lblName.setText("Name"); //$NON-NLS-1$

        name = new Text(compositeContainer, SWT.BORDER);
        name.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

        hookListeners();
        setControl(compositeContainer);
    }

    /**
     * Adds components listeners
     */
    private void hookListeners()
    {
        name.addModifyListener(new ModifyListener()
        {

            @Override
            public void modifyText(ModifyEvent e)
            {
                text = name.getText();
            }
        });
    }

	/* (non-Javadoc)
	 * @see org.eclipse.reqcycle.repository.connector.ui.wizard.IRequirementSourceRepositoryPage#getRepositoryUrl()
	 */
	@Override
	public String getRepositoryUrl() {
		return text;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.reqcycle.repository.connector.ui.wizard.IRequirementSourceRepositoryPage#performFinish(org.eclipse.reqcycle.repository.connector.IRequirementSourceRepository)
	 */
	@Override
	public void performFinish(IRequirementSourceRepository repository) {
		try {
			repository.setProperty(IRepositoryConstants.PROPERTY_URL, text);
			repository.setProperty(IRepositoryConstants.PROPERTY_LABEL, text);
		} catch (Exception e) {
			boolean debug = logger.isDebug(Activator.OPTIONS_DEBUG, Activator.getDefault());
			if(debug) {
				logger.trace("Properties " + IRepositoryConstants.PROPERTY_URL + " and " + IRepositoryConstants.PROPERTY_LABEL + " can't be set on " + repository.getRepositoryLabel()
					+ "\n Trace : " + e.getMessage());
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.reqcycle.repository.connector.ui.wizard.IRequirementSourceRepositoryPage#preFinish(org.eclipse.reqcycle.repository.connector.IRequirementSourceRepository)
	 */
	@Override
	public boolean preFinish(IRequirementSourceRepository repository) {
		return true;
	}
    

}

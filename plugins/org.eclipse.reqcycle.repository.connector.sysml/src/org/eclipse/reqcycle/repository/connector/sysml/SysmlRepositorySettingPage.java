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

package org.eclipse.reqcycle.repository.connector.sysml;

import javax.inject.Inject;

import org.eclipse.emf.common.ui.dialogs.ResourceDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.reqcycle.core.ILogger;
import org.eclipse.reqcycle.repository.connector.IRepositoryConstants;
import org.eclipse.reqcycle.repository.connector.IRequirementSourceRepository;
import org.eclipse.reqcycle.repository.connector.ui.wizard.IRequirementSourceRepositoryPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class SysmlRepositorySettingPage extends WizardPage implements IRequirementSourceRepositoryPage {

    private Text fileUri;

    private String text;

    private Button btnBrowse;

    @Inject ILogger logger;
    
	public SysmlRepositorySettingPage(String title, String description) {
		super(title);
		setTitle(title);
		setDescription(description);
	}
    
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(Composite parent)
    {
        Composite container = new Composite(parent, SWT.NULL);
        setControl(container);
        container.setLayout(new GridLayout(3, false));

        Label lblFile = new Label(container, SWT.NONE);
        lblFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
        lblFile.setText("File"); //$NON-NLS-1$

        fileUri = new Text(container, SWT.BORDER);
        fileUri.setEditable(false);
        fileUri.setEnabled(false);
        fileUri.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

        btnBrowse = new Button(container, SWT.NONE);
        btnBrowse.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
        btnBrowse.setText("Browse"); //$NON-NLS-1$

        hookListeners();
    }

    /**
     * Adds components listeners
     */
    private void hookListeners()
    {
        btnBrowse.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent e)
            {
                ResourceDialog dialog = new ResourceDialog(getShell(), "resource selection", SWT.OPEN | SWT.SINGLE); //$NON-NLS-1$
                if (dialog.open() == ResourceDialog.OK)
                {
                	text = dialog.getURIText();
                    fileUri.setText(text);
                }
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

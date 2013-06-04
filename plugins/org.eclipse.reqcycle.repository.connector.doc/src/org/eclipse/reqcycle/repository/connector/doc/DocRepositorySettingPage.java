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

/**
 * 
 */
package org.eclipse.reqcycle.repository.connector.doc;

import java.util.List;

import javax.inject.Inject;

import org.eclipse.emf.common.ui.dialogs.ResourceDialog;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.reqcycle.core.ILogger;
import org.eclipse.reqcycle.repository.connector.IRepositoryConstants;
import org.eclipse.reqcycle.repository.connector.IRequirementSourceRepository;
import org.eclipse.reqcycle.repository.connector.ui.wizard.IRequirementSourceRepositoryPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class DocRepositorySettingPage extends WizardPage implements IRequirementSourceRepositoryPage {

	/** Repository url */
	private Text uriText;
	
	private String uriString;
	
	private Button browseButton;
	
	@Inject ILogger logger;

	/**
	 * @param title
	 *        Page title
	 * @param description
	 *        page description
	 */
	protected DocRepositorySettingPage(String title, String description) {
		super(title);
		setTitle(title);
		setDescription(description);
	}

	/**
	 * @return the connector Id
	 */
	public String getConnectorId() {
		return "DOC";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		Composite compositeContainer = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(3, false);
		compositeContainer.setLayout(layout);

		Label lblFile = new Label(compositeContainer, SWT.NONE);
		lblFile.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFile.setText("File :");
		uriText = new Text(compositeContainer, SWT.BORDER);
		uriText.setEnabled(false);
		uriText.setEditable(false);
		uriText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		browseButton = new Button(compositeContainer, SWT.NONE);
		browseButton.setText("Browse");
		
		hookListeners();
		setControl(compositeContainer);
	}

	/**
	 * 
	 */
	private void hookListeners() {
		browseButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				ResourceDialog dialog = new ResourceDialog(getShell(), "Select req file", SWT.NONE);
				int res = dialog.open();
				if(res == ResourceDialog.OK) {
					List<URI> uris = dialog.getURIs();
					if(!uris.isEmpty()) {
						uriString = uris.get(0).toString();
						uriText.setText(uriString);
						
					}
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.reqcycle.repository.connector.ui.wizard.IConnectorRepositoryPage#getRepositoryUrl()
	 */
	@Override
	public String getRepositoryUrl() {
		return uriText.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.reqcycle.repository.connector.ui.wizard.IConnectorRepositoryPage#performFinish(org.eclipse.reqcycle.repository.connector.
	 * RequirementRepository)
	 */
	@Override
	public void performFinish(IRequirementSourceRepository repository) {
		try {
			repository.setProperty(IRepositoryConstants.PROPERTY_URL, uriString);
			repository.setProperty(IRepositoryConstants.PROPERTY_LABEL, uriString);
		} catch (Exception e) {
			boolean debug = logger.isDebug(Activator.OPTIONS_DEBUG, Activator.getDefault());
			if(debug) {
				logger.trace("Properties " + IRepositoryConstants.PROPERTY_URL + " and " + IRepositoryConstants.PROPERTY_LABEL + " can't be set on " + repository.getRepositoryLabel()
					+ "\n Trace : " + e.getMessage());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.reqcycle.repository.connector.ui.wizard.IRequirementRepositoryPage#preFinish(org.eclipse.reqcycle.repository.connector.
	 * RequirementRepository)
	 */
	@Override
	public boolean preFinish(IRequirementSourceRepository repository) {
		return true;
	}


}

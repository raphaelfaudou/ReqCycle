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
package org.eclipse.reqcycle.repository.connector.rmf.ui;

import java.util.List;

import org.eclipse.emf.common.ui.dialogs.ResourceDialog;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import DataModel.RequirementSource;

public class RMFSettingPage extends WizardPage {

	private Text fileURIText;

	private Button browseFileBtn;

	private String fileURIString;

	private String uri;

	private Button btnSkipMapping;

	private boolean skipMapping;

	/**
	 * @param title
	 *        Page title
	 * @param description
	 *        page description
	 * @wbp.parser.constructor
	 */
	public RMFSettingPage(String title, String description) {
		super(title);
		setTitle(title);
		setDescription(description);
	}

	/**
	 * @param title
	 *        Page title
	 * @param description
	 *        Page description
	 * @param uri
	 *        input uri
	 */
	public RMFSettingPage(String title, String description, String uri) {
		this(title, description);
		this.uri = uri;
	}

	@Override
	public boolean canFlipToNextPage() {
		return isPageComplete() && !skipMapping;
	}

	@Override
	public void createControl(Composite parent) {
		Composite compositeContainer = new Composite(parent, SWT.NONE);
		compositeContainer.setLayout(new GridLayout(3, false));
		setControl(compositeContainer);

		Label lblReqIfFile = new Label(compositeContainer, SWT.NONE);
		lblReqIfFile.setText("ReqIF file :");

		fileURIText = new Text(compositeContainer, SWT.BORDER);
		fileURIText.setEditable(false);
		fileURIText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		browseFileBtn = new Button(compositeContainer, SWT.NONE);
		browseFileBtn.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		browseFileBtn.setText("Browse");
		
		
		btnSkipMapping = new Button(compositeContainer, SWT.CHECK);

		Label lblSkipMapping = new Label(compositeContainer, SWT.NONE);
		lblSkipMapping.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		lblSkipMapping.setText("Skip mapping (Perform mapping later)");

		hookListeners();
		init();
	}
	
	
	protected void hookListeners() {
		btnSkipMapping.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if(btnSkipMapping != null) {
					skipMapping = btnSkipMapping.getSelection();
				}
			}
		});
		
		browseFileBtn.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				ResourceDialog dialog = new ResourceDialog(getShell(), "Select ReqIF file", SWT.NONE);
				int res = dialog.open();
				if(res == ResourceDialog.OK) {
					List<URI> uris = dialog.getURIs();
					if(!uris.isEmpty()) {
						fileURIString = uris.get(0).toString();
						fileURIText.setText(fileURIString);
						setPageComplete(isPageComplete());
					}
				}
			}
		});
	}
	
	
	@Override
	public boolean isPageComplete() {
		StringBuffer error = new StringBuffer();
		if(fileURIString == null || fileURIString.isEmpty()) {
			error.append(" Choose a ReqIF File.\n");
			return false;
		}
		return true;
	}
	

	public String getSourceURI() {
		return fileURIString;
	}

	public boolean preFinish(RequirementSource repository) {
		return true;
	}

	private void init() {
		if(uri != null && !uri.isEmpty()) {
			fileURIString = uri;
			if(fileURIText != null) {
				fileURIText.setText(uri);
			}
		}
	}
	
	public boolean skipMapping() {
		return skipMapping;
	}
}

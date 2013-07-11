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

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.ui.dialogs.ResourceDialog;
import org.eclipse.emf.common.util.URI;
import org.eclipse.reqcycle.repository.connector.ui.wizard.AbstractSettingPage2;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import DataModel.RequirementSource;
import DataModel.Scope;

public class RMFSettingPage extends AbstractSettingPage2 {

	private Text fileURIText;

	private Button browseFileBtn;

	private String fileURIString;

	private String uri;

	/**
	 * @param title
	 *        Page title
	 * @param description
	 *        page description
	 */
	public RMFSettingPage(String title, String description) {
		super(title, description);
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public RMFSettingPage(String title, String description, String label, String uri, Collection<Scope> scopes){
		super(title, description, label, ((scopes!=null && scopes.size()>0)?scopes.iterator().next():null));
		this.uri = uri;
	}

	/**
	 * @return the connector Id
	 */
	public String getConnectorId() {
		return "ReqIF";
	}

	@Override
	public boolean canFlipToNextPage() {
		return isPageComplete() && !skipMapping;
	}
	
	@Override
	public void addCustomControl(Composite parent) {
		Label lblReqIfFile = new Label(parent, SWT.NONE);
		lblReqIfFile.setText("ReqIF file :");
		
		fileURIText = new Text(parent, SWT.BORDER);
		fileURIText.setEditable(false);
		fileURIText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		browseFileBtn = new Button(parent, SWT.NONE);
		browseFileBtn.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		browseFileBtn.setText("Browse");
	}

	@Override
	protected void hookCustomListeners() {
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
	protected boolean isCustomPageComplete(StringBuffer error) {
		if(fileURIString == null || fileURIString.isEmpty()) {
			error.append(" Choose a ReqIF File.\n");
			return false;
		}
		return true;
	}

	public String getSourceUrl() {
		return fileURIString;
	}

	@Override
	public boolean preFinish(RequirementSource repository) {
		return true;
	}

	@Override
	protected void customInit() {
		if(uri != null && !uri.isEmpty()) {
			fileURIString = uri;
			if(fileURIText != null) {
				fileURIText.setText(uri);
			}
		}
	}
}

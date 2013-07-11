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
package org.eclipse.reqcycle.repository.connector.ui.wizard.pages;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import DataModel.Scope;


/**
 * Abstract setting page with escape mapping checkbox
 */
public abstract class AbstractSettingPage2 extends AbstractSettingPage {

	private Button btnSkipMapping;
	
	protected boolean skipMapping;

	public AbstractSettingPage2(String title, String description) {
		super(title, description);
	}

	public AbstractSettingPage2(String title, String description, String label, Scope scope) {
		super(title, description, label, scope);
	}



	@Override
	public void createControl(Composite parent) {

		super.createControl(parent);

		Composite bottomComposite = new Composite((Composite)super.getControl(), SWT.NONE);
		bottomComposite.setLayout(new GridLayout(2, false));
		bottomComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 3, 1));

		btnSkipMapping = new Button(bottomComposite, SWT.CHECK);
		btnSkipMapping.setEnabled(false);

		Label lblSkipMapping = new Label(bottomComposite, SWT.NONE);
		lblSkipMapping.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		lblSkipMapping.setText("Skip mapping (Perform mapping later)");
		lblSkipMapping.setEnabled(false);

		hookListeners2();
	}


	private void hookListeners2() {

		btnSkipMapping.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if(btnSkipMapping != null) {
					skipMapping = btnSkipMapping.getSelection();
				}
			}
		});
	}

	public boolean skipMapping() {
		return skipMapping;
	}

}

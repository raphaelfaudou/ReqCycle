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

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.reqcycle.repository.data.IScopeManager;
import org.eclipse.reqcycle.repository.data.util.DataUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ziggurat.inject.ZigguratInject;

import DataModel.Scope;


public abstract class AbstractSettingPage extends WizardPage {

	protected Text requirementSourceNameText;

	private ComboViewer scopeComboViewer;

	protected String requirementSourceNameString;

	protected Scope selectedScope;

	private IScopeManager scopeManager = ZigguratInject.make(IScopeManager.class);

	private String repositoryLabel;

	private Scope scope;

	
	/**
	 * @wbp.parser.constructor
	 */
	public AbstractSettingPage(String pageTitle, String pageDescription) {
		super(pageTitle);
		setTitle(pageTitle);
		setDescription(pageDescription);
	}
	
	public AbstractSettingPage(String pageTitle, String pageDescription, String repositoryLabel, Scope scope) {
		this(pageTitle, pageDescription);
		this.repositoryLabel = repositoryLabel;
		this.scope = scope;
	}

	@Override
	public void createControl(Composite parent) {
		Composite compositeContainer = new Composite(parent, SWT.NONE);
		compositeContainer.setLayout(new GridLayout(1, false));
		setControl(compositeContainer);

		Composite mainComposite = new Composite(compositeContainer, SWT.NONE);
		mainComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		mainComposite.setLayout(new GridLayout());
		
		Composite infoComposite = new Composite(mainComposite, SWT.NONE);
		infoComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		infoComposite.setLayout(new GridLayout(3, false));

		Label lblRepositoryName = new Label(infoComposite, SWT.NONE);
		lblRepositoryName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRepositoryName.setText("Repository Name :");

		requirementSourceNameText = new Text(infoComposite, SWT.BORDER);
		requirementSourceNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Label lblScope = new Label(infoComposite, SWT.NONE);
		lblScope.setText("Scope :");

		scopeComboViewer = new ComboViewer(infoComposite);
		scopeComboViewer.getCombo().setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		scopeComboViewer.setContentProvider(new ArrayContentProvider());
		scopeComboViewer.setLabelProvider(new LabelProvider() {

			@Override
			public String getText(Object element) {
				return DataUtil.getLabel(element);
			}
		});
		scopeComboViewer.setInput(scopeManager.getAllScopes());
		
		if(scope != null && scopeManager.getAllScopes().contains(scope)) {
			scopeComboViewer.setSelection(new StructuredSelection(scope));
		}
		

		hookListeners();
		init();
		
		addCustomControl(mainComposite);
		hookCustomListeners();
		customInit();
	}

	

	private void init() {
		if(repositoryLabel != null && !repositoryLabel.isEmpty()) {
			requirementSourceNameString = repositoryLabel;
			if (requirementSourceNameText != null ){
				requirementSourceNameText.setText(repositoryLabel);
			}
		}
		if(scope != null) {
			selectedScope = scope;
			if(scopeComboViewer != null) {
				scopeComboViewer.setSelection(new StructuredSelection(scope));
			}
		}
	}

	protected abstract void customInit();
	
	/**
	 * Override this method to add specific ui elements
	 * 
	 * @param composite
	 */
	protected abstract void addCustomControl(Composite parent);

	protected void hookListeners() {

		requirementSourceNameText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				requirementSourceNameString = requirementSourceNameText.getText();
			}
		});

		scopeComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				ISelection selection = event.getSelection();
				if(selection instanceof IStructuredSelection) {
					Object fElement = ((IStructuredSelection)selection).getFirstElement();
					if(fElement instanceof Scope) {
						selectedScope = (Scope)fElement;
						setPageComplete(isPageComplete());
					}
				}
			}
		});
	}

	/**
	 * Override this method to hook specific listeners
	 */
	protected abstract void hookCustomListeners();

	@Override
	public boolean canFlipToNextPage() {
		return isPageComplete();
	}

	@Override
	public boolean isPageComplete() {
		boolean result = true;
		StringBuffer error = new StringBuffer(""); //$NON-NLS-1$

		if (requirementSourceNameString == null || requirementSourceNameString.isEmpty()) {
			result = false;
			error.append("Choose a requirement source name.\n");
		}

		if (selectedScope == null) {
			result = false;
			error.append(" Choose a scope.\n");
		}

		result &= isCustomPageComplete(error);
		
		if(result) {
			setErrorMessage(null);
		} else {
			setErrorMessage(error.toString());
		}
		return result;
	}
	
	/**
	 * Override this method
	 * 
	 * @param result
	 * @param error
	 */
	protected boolean isCustomPageComplete(StringBuffer error) {
		return true;
	}

	public String getRequirementSourceName() {
		return requirementSourceNameString;
	}

	public Scope getScope() {
		return selectedScope;
	}

}

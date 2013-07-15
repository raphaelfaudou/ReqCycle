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
package org.eclipse.reqcycle.repository.ui.wizard.pages;

import javax.inject.Inject;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.reqcycle.core.ILogger;
import org.eclipse.reqcycle.repository.connector.ConnectorDescriptor;
import org.eclipse.reqcycle.repository.connector.IConnector;
import org.eclipse.reqcycle.repository.connector.IConnectorManager;
import org.eclipse.reqcycle.repository.connector.ui.providers.ConnectorLabelProvider;
import org.eclipse.reqcycle.repository.connector.ui.wizard.IConnectorWizard;
import org.eclipse.reqcycle.repository.requirement.data.IScopeManager;
import org.eclipse.reqcycle.repository.requirement.data.util.DataUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import DataModel.Scope;

public class SelectConnectorPage extends WizardPage {

	private ConnectorDescriptor connectorDescriptor;
	
	private IConnector connector;

	@Inject
	private IConnectorManager connectorManager;

	@Inject
	private IScopeManager scopeManager;
	
	@Inject ILogger logger;

	private TableViewer viewer;

	private Text requirementSourceNameText;

	private ComboViewer scopeComboViewer;
	
	private Scope scope;

	private String sourceName;
	


	public SelectConnectorPage() {
		super("Select a requirement source repository type");
		setTitle("Select a requirement source repository type");
	}

	@Override
	public boolean canFlipToNextPage() {
		return connectorDescriptor != null && connector != null && connector instanceof IConnectorWizard && sourceName != null && !sourceName.isEmpty() && getScope() != null;
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayoutFactory.fillDefaults().numColumns(1).spacing(0, 3).applyTo(container);

		viewer = new TableViewer(container, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		viewer.setContentProvider(new ArrayContentProvider());
		ConnectorLabelProvider labelProvider = new ConnectorLabelProvider();
		viewer.setLabelProvider(labelProvider);
		viewer.setInput(connectorManager.getAllConnectors());

		Composite infoComposite = new Composite(container, SWT.NONE);
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

		GridDataFactory.fillDefaults().grab(true, true).applyTo(viewer.getControl());
		Dialog.applyDialogFont(container);
		setControl(container);
		hookListeners();
	}

	private void hookListeners() {
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				ISelection selection = event.getSelection();
				if(selection instanceof IStructuredSelection) {
					Object selectedElement = ((IStructuredSelection)selection).getFirstElement();
					if(selectedElement instanceof ConnectorDescriptor) {
						connectorDescriptor = (ConnectorDescriptor)selectedElement;
						try {
							connector = connectorDescriptor.createConnector();
						} catch (CoreException e) {
							logger.log(e.getStatus());
						}
						getWizard().getContainer().updateButtons();
					}
				}
			}
		});

		viewer.addOpenListener(new IOpenListener() {

			public void open(OpenEvent event) {
				getWizard().getContainer().updateButtons();
				if(canFlipToNextPage()) {
					getContainer().showPage(getNextPage());
				}
			}
		});

		requirementSourceNameText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				sourceName = requirementSourceNameText.getText();
				getWizard().getContainer().updateButtons();
			}
		});

		scopeComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				ISelection selection = event.getSelection();
				if(selection instanceof IStructuredSelection) {
					Object fElement = ((IStructuredSelection)selection).getFirstElement();
					if(fElement instanceof Scope) {
						scope = (Scope) fElement;
						getWizard().getContainer().updateButtons();
					}
				}
			}
		});
	}
	
	public Scope getScope(){
		return scope;
	}

	public ConnectorDescriptor getConnectorDescriptor() {
		return connectorDescriptor;
	}
	
	public String getSourceName(){
		return sourceName;
	}
	
	public IConnector getConnector(){
		return connector;
	}

}

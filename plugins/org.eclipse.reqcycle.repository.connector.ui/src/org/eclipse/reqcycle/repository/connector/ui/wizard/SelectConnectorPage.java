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
package org.eclipse.reqcycle.repository.connector.ui.wizard;

import javax.inject.Inject;

import org.agesys.inject.AgesysInject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.reqcycle.repository.connector.IConnector;
import org.eclipse.reqcycle.repository.connector.IConnectorManager;
import org.eclipse.reqcycle.repository.connector.ui.providers.ConnectorLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class SelectConnectorPage extends WizardPage {

	/** selected requirement source connector  */
	private IConnector connector;

	/** connectors table viewer */
	private TableViewer viewer;

	/** the connector manager */
	private @Inject IConnectorManager connectorManager = AgesysInject.make(IConnectorManager.class);

	protected SelectConnectorPage() {
		super("Select a requirement source repository type");
		setTitle("Select a requirement source repository type");
	}

	@Override
	public boolean canFlipToNextPage() {
		return connector != null;
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayoutFactory.fillDefaults().numColumns(1).spacing(0, 3).applyTo(container);

		viewer = new TableViewer(container, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new ConnectorLabelProvider());
		
		viewer.setInput(connectorManager.getAllConnectors());
		
		GridDataFactory.fillDefaults().grab(true, true).applyTo(viewer.getControl());
		Dialog.applyDialogFont(container);
		setControl(container);

		hookListeners();
	}

	private void hookListeners() {
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				ISelection selection = event.getSelection();
				if (selection instanceof IStructuredSelection) {
					Object selectedElement = ((IStructuredSelection) selection).getFirstElement();
					if (selectedElement instanceof IConnector) {
						connector = (IConnector) selectedElement;
						setPageComplete(true);
					}
				}
			}
		});

		viewer.addOpenListener(new IOpenListener() {
			public void open(OpenEvent event) {
				getContainer().showPage(getNextPage());
			}
		});
	}

	/**
	 * Gets the selected connector
	 * 
	 * @return the selected connector
	 */
	public IConnector getConnector() {
		return connector;
	}

}

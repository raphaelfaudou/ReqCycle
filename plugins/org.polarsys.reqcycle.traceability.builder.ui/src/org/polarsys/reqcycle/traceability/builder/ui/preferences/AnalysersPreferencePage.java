/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.builder.ui.preferences;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ICheckStateProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.polarsys.reqcycle.traceability.builder.LabelledVisitor;
import org.polarsys.reqcycle.traceability.builder.ui.Activator;
import org.polarsys.reqcycle.utils.configuration.IConfigurationManager;
import org.polarsys.reqcycle.utils.configuration.IConfigurationManager.Scope;
import org.polarsys.reqcycle.utils.inject.ZigguratInject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class AnalysersPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	IConfigurationManager manager = ZigguratInject
			.make(IConfigurationManager.class);
	public static final String PREF_ID = Activator.PLUGIN_ID
			+ ".disabledEngines";

	private Table table;
	private Label descriptionLabel;
	private Map<String, Object> prefs = new HashMap<String, Object>();
	private CheckboxTableViewer checkboxTableViewer;

	/**
	 * Create the preference page.
	 */
	public AnalysersPreferencePage() {
		Map<String, Object> pref = manager.getSimpleConfiguration(null,
				Scope.WORKSPACE, PREF_ID, true);
		if (pref != null) {
			prefs = pref;
		}
	}

	/**
	 * Create contents of the preference page.
	 * 
	 * @param parent
	 */
	@Override
	public Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		container.setLayout(new FormLayout());

		Label lblNewLabel = new Label(container, SWT.NONE);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.right = new FormAttachment(0, 365);
		fd_lblNewLabel.top = new FormAttachment(0);
		fd_lblNewLabel.left = new FormAttachment(0, 5);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel
				.setText("Checked analysers will be used for traceability analysis");

		checkboxTableViewer = CheckboxTableViewer.newCheckList(container,
				SWT.BORDER | SWT.FULL_SELECTION);
		table = checkboxTableViewer.getTable();
		FormData fd_table = new FormData();
		fd_table.top = new FormAttachment(lblNewLabel);
		fd_table.left = new FormAttachment(0, 5);
		fd_table.bottom = new FormAttachment(75);
		fd_table.right = new FormAttachment(100, -5);
		table.setLayoutData(fd_table);
		checkboxTableViewer.setLabelProvider(new LabelProvider() {

			@Override
			public String getText(Object element) {
				if (element instanceof LabelledVisitor) {
					LabelledVisitor visitor = (LabelledVisitor) element;
					return visitor.getLabel();
				}
				return super.getText(element);
			}

		});
		checkboxTableViewer.setContentProvider(ArrayContentProvider
				.getInstance());
		checkboxTableViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {

					@Override
					public void selectionChanged(SelectionChangedEvent event) {
						Object firstElement = ((IStructuredSelection) event
								.getSelection()).getFirstElement();
						if (firstElement instanceof LabelledVisitor) {
							LabelledVisitor labelled = (LabelledVisitor) firstElement;
							descriptionLabel.setText(labelled.getDescription());
						}
					}
				});
		checkboxTableViewer.setCheckStateProvider(new ICheckStateProvider() {

			@Override
			public boolean isGrayed(Object element) {
				return false;
			}

			@Override
			public boolean isChecked(Object element) {

				if (element instanceof LabelledVisitor) {
					LabelledVisitor labelled = (LabelledVisitor) element;

					return !(Boolean.FALSE.equals(prefs.get(labelled
							.getVisitorClass().getName())));
				}
				return true;
			}
		});
		checkboxTableViewer.addCheckStateListener(new ICheckStateListener() {

			@Override
			public void checkStateChanged(CheckStateChangedEvent event) {
				Object element = event.getElement();
				if (element instanceof LabelledVisitor) {
					LabelledVisitor labelled = (LabelledVisitor) element;
					prefs.put(labelled.getVisitorClass().getName(),
							event.getChecked());
				}
			}
		});
		checkboxTableViewer.setInput(LabelledVisitor.getRegisteredVisitors());
		Group grpDescription = new Group(container, SWT.NONE);
		FormData fd_grpDescription = new FormData();
		fd_grpDescription.top = new FormAttachment(table, 5);
		fd_grpDescription.bottom = new FormAttachment(100);
		fd_grpDescription.left = new FormAttachment(0, 5);
		fd_grpDescription.right = new FormAttachment(100, -5);
		grpDescription.setLayoutData(fd_grpDescription);
		grpDescription.setLayout(new GridLayout(1, false));
		grpDescription.setText("Description");

		descriptionLabel = new Label(grpDescription, SWT.WRAP);
		descriptionLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1));

		return container;
	}

	@Override
	protected void performDefaults() {
		Map<String, Object> pref = manager.getSimpleConfiguration(null,
				Scope.WORKSPACE, PREF_ID, true);
		if (pref != null) {
			prefs = pref;
		}
		checkboxTableViewer.refresh(true);
		super.performDefaults();
	}

	@Override
	public boolean performOk() {
		try {
			manager.saveSimpleConfiguration(prefs, null, Scope.WORKSPACE,
					PREF_ID);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.performOk();
	}

	/**
	 * Initialize the preference page.
	 */
	public void init(IWorkbench workbench) {
		// Initialize the preference page
	}
}

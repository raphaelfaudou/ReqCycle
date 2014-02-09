/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.jdt.traceability.preferences;

import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.polarsys.reqcycle.jdt.traceability.Activator;
import org.polarsys.reqcycle.jdt.traceability.JDTPreferences;
import org.polarsys.reqcycle.traceability.model.TType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class JDTAnnotationPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {
	private Table table;
	private Map<String, TType> preferences;
	private TableViewer tableViewer;
	private static final String IMAGE_PATH = "/icons/annot.gif";
	private static final String IMAGE_KEY = Activator.PLUGIN_ID
			+ "/icons/annot.gif";

	public JDTAnnotationPreferencePage() {
		super();
	}

	/**
	 * @wbp.parser.constructor
	 */
	public JDTAnnotationPreferencePage(String title, ImageDescriptor image) {
		super(title, image);
	}

	@Override
	public void init(IWorkbench workbench) {

	}

	@Override
	protected Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,
				1));
		composite.setLayout(new GridLayout(2, false));

		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				1, 1));

		tableViewer = new TableViewer(composite_1, SWT.BORDER
				| SWT.FULL_SELECTION);
		tableViewer.setUseHashlookup(true);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		TableViewerColumn tableViewerColumn = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tblclmnAnnotation = tableViewerColumn.getColumn();
		tblclmnAnnotation.setWidth(100);
		tblclmnAnnotation.setText("Annotation");

		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tblclmnKind = tableViewerColumn_1.getColumn();
		tblclmnKind.setWidth(100);
		tblclmnKind.setText("Kind");

		Composite composite_2 = new Composite(composite, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.VERTICAL));
		composite_2.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, true,
				1, 1));

		Button btnAdd = new Button(composite_2, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				NewAnnotDialog dialog = new NewAnnotDialog(getShell());
				if (dialog.open() == NewAnnotDialog.OK) {
					preferences.put(dialog.getAnnotName(), dialog.getLink());
					tableViewer.refresh();
				}
			}
		});
		btnAdd.setText("Add");

		Button btnRemove = new Button(composite_2, SWT.NONE);
		btnRemove.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selec = (IStructuredSelection) tableViewer
						.getSelection();
				@SuppressWarnings("unchecked")
				Entry<String, TType> entry = (Entry<String, TType>) selec
						.getFirstElement();
				preferences.remove(entry.getKey());
				tableViewer.refresh(true);
			}
		});
		btnRemove.setText("Remove");
		// providers
		tableViewer.setLabelProvider(new ITableLabelProvider() {

			@Override
			public void removeListener(ILabelProviderListener listener) {
			}

			@Override
			public boolean isLabelProperty(Object element, String property) {
				return true;
			}

			@Override
			public void dispose() {
			}

			@Override
			public void addListener(ILabelProviderListener listener) {
			}

			@Override
			public String getColumnText(Object element, int columnIndex) {
				if (element instanceof Entry) {
					Entry<String, TType> entry = (Entry) element;
					if (columnIndex == 0) {
						return entry.getKey();
					} else {
						return entry.getValue().toString();
					}
				}
				return null;
			}

			@Override
			public Image getColumnImage(Object element, int columnIndex) {
				if (columnIndex == 0) {
					return getImageAnnot();
				}
				return null;
			}
		});
		tableViewer.setContentProvider(new IStructuredContentProvider() {

			@Override
			public void inputChanged(Viewer viewer, Object oldInput,
					Object newInput) {
			}

			@Override
			public void dispose() {

			}

			@Override
			public Object[] getElements(Object inputElement) {
				if (inputElement instanceof Map) {
					Map<String, TType> map = (Map<String, TType>) inputElement;
					return map.entrySet().toArray();
				}
				return new Object[] {};
			}
		});
		preferences = JDTPreferences.getPreferences();
		tableViewer.setInput(preferences);
		return parent;
	}

	protected Image getImageAnnot() {
		ImageRegistry imageRegistry = Activator.getDefault().getImageRegistry();
		Image image = imageRegistry.get(IMAGE_KEY);
		if (image == null) {
			imageRegistry.put(
					IMAGE_KEY,
					ImageDescriptor.createFromURL(Activator.getDefault()
							.getBundle().getEntry(IMAGE_PATH)));
			image = imageRegistry.get(IMAGE_KEY);
		}
		return image;
	}

	@Override
	public boolean performOk() {
		JDTPreferences.savePreferences(preferences);
		return super.performOk();
	}

}

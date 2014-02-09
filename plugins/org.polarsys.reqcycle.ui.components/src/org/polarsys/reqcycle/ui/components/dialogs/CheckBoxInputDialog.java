/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Papa Issa Diakhate (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.ui.components.dialogs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

public class CheckBoxInputDialog extends AbstractCustomDialog {

	private IContentProvider contentProvider;

	private ITableLabelProvider labelProvider;

	private CheckboxTableViewer checkboxTableViewer;

	private final List<Object> selectedItems;

	private Collection<Object> initialSelection;

	public CheckBoxInputDialog(Shell parentShell, String dialogTitle, String dialogMessage, Object initialInput, IInputValidator validator, Collection<Object> initialSelection) {
		super(parentShell, dialogTitle, dialogMessage, initialInput, validator);
		this.selectedItems = new ArrayList<Object>();
		this.initialSelection = initialSelection;
	}

	@Override
	protected void createCustomDialogArea(Composite parent) {

		checkboxTableViewer = CheckboxTableViewer.newCheckList(parent, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		Table tableOfCustomPredicates = checkboxTableViewer.getTable();
		tableOfCustomPredicates.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		TableLayout tableLayout = new TableLayout();
		tableLayout.addColumnData(new ColumnWeightData(3, 100, true));
		tableOfCustomPredicates.setLayout(tableLayout);

		TableViewerColumn column = new TableViewerColumn(checkboxTableViewer, SWT.None);
		column.getColumn().setResizable(true);

		if(this.contentProvider == null)
			this.contentProvider = ArrayContentProvider.getInstance();
		if(this.labelProvider == null)
			this.labelProvider = this.getDefaultLabelProvider();

		this.checkboxTableViewer.setContentProvider(this.contentProvider);
		this.checkboxTableViewer.setLabelProvider(this.labelProvider);
		this.checkboxTableViewer.setInput(getInput());
		if(initialSelection != null && !initialSelection.isEmpty()) {
			this.checkboxTableViewer.setCheckedElements(initialSelection.toArray());
		}
	}

	private ITableLabelProvider getDefaultLabelProvider() {
		return new ITableLabelProvider() {

			@Override
			public void removeListener(ILabelProviderListener listener) {
			}

			@Override
			public boolean isLabelProperty(Object element, String property) {
				return false;
			}

			@Override
			public void dispose() {
			}

			@Override
			public void addListener(ILabelProviderListener listener) {
			}

			@Override
			public String getColumnText(Object element, int columnIndex) {
				return element.toString();
			}

			@Override
			public Image getColumnImage(Object element, int columnIndex) {
				return null;
			}
		};
	}

	@Override
	protected void buttonPressed(int buttonId) {
		if(buttonId == IDialogConstants.OK_ID) {
			this.selectedItems.addAll(Arrays.asList(this.checkboxTableViewer.getCheckedElements()));
		} else {
			this.selectedItems.removeAll(selectedItems);
		}
		super.buttonPressed(buttonId);
	}

	@Override
	public Collection<Object> getSelectedItems() {
		return this.selectedItems;
	}

	public void setContentProvider(IContentProvider contentProvider) {
		this.contentProvider = contentProvider;
	}

	public void setLabelProvider(ITableLabelProvider labelProvider) {
		this.labelProvider = labelProvider;
	}
}

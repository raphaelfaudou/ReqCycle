/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Papa Issa Diakhate (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.ui.collectionspropseditor.internal.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerComparator;
import org.polarsys.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditorComponent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class CollectionsPropsEditorComponent extends AbstractPropsEditorComponent<Collection<Object>> {

	private TableViewer tableViewer;

	private final List<Object> enteredValues = new ArrayList<Object>();

	private final CustomViewerComparator comparator;

	public CollectionsPropsEditorComponent(EAttribute attribute, Composite parent, int style) {

		super(attribute, parent, style);

		final Label lblName = new Label(this, SWT.NONE);
		lblName.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, false, 1, 1));
		lblName.setText(attribute.getName());

		final EClassifier eType = attribute.getEType();

		if(eType instanceof EEnum) {
			setLayout(new GridLayout(2, false));
			this.initTableViewerForEEnum(parent, (EEnum)eType);

		} else {
			setLayout(new GridLayout(4, false));
			this.initSimpleTableViewer(parent);
		}

		tableViewer.setLabelProvider(new DefaultTableLabelProvider());
		this.comparator = new CustomViewerComparator();
		tableViewer.setComparator(comparator);

		final Table table = tableViewer.getTable();

		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setSize(200, 0);

		final TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn.setLabelProvider(new CellLabelProvider() {

			@Override
			public void update(ViewerCell cell) {
				Object obj = cell.getElement();
				if(obj instanceof ItemValue) {
					cell.setText(obj.toString());
				} else if(obj instanceof Enumerator) {
					cell.setText(((Enumerator)obj).getLiteral());
				} else {
					throw new IllegalArgumentException("Cannot update the cell label, unknown content type ...");
				}
			}
		});
		tableViewerColumn.setEditingSupport(new ItemValueEditingSupport(tableViewer));

		final TableColumn valuesColumn = tableViewerColumn.getColumn();
		valuesColumn.setText("values");
		valuesColumn.setResizable(true);
		valuesColumn.setMoveable(true);
		valuesColumn.setWidth(table.getSize().x);
		valuesColumn.addSelectionListener(getSelectionAdapter(valuesColumn));
	}

	private void initSimpleTableViewer(final Composite parent) {

		tableViewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		tableViewer.setContentProvider(ArrayContentProvider.getInstance());
		tableViewer.setInput(this.enteredValues);

		this.initTableMenu(this.tableViewer.getTable());

		Composite buttonsComposite = new Composite(this, SWT.NONE);
		buttonsComposite.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, false, 1, 1));
		buttonsComposite.setLayout(new FillLayout(SWT.VERTICAL));

		Button btnAdd = new Button(buttonsComposite, SWT.NONE);
		btnAdd.setText("Add");
		btnAdd.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				addNewItem();
			}
		});

		Button btnDelete = new Button(buttonsComposite, SWT.NONE);
		btnDelete.setText("Delete");
		btnDelete.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				removeSelectedItems();
			}
		});
	}

	private void initTableViewerForEEnum(Composite parent, EEnum eType) {
		tableViewer = CheckboxTableViewer.newCheckList(parent, SWT.BORDER | SWT.MULTI);
		Table table = tableViewer.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tableViewer.setContentProvider(new IStructuredContentProvider() {

			@Override
			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			}

			@Override
			public void dispose() {
			}

			@Override
			public Object[] getElements(Object inputElement) {
				return (Object[])inputElement;
			}
		});
		((CheckboxTableViewer)tableViewer).addCheckStateListener(new ICheckStateListener() {

			@Override
			public void checkStateChanged(CheckStateChangedEvent event) {
				Object obj = event.getElement();
				if(obj instanceof EEnumLiteral) {
					if(event.getChecked()) {
						enteredValues.add(((EEnumLiteral)obj).getInstance());
					} else {
						enteredValues.remove(((EEnumLiteral)obj).getInstance());
					}
				}
			}
		});
		tableViewer.setInput(eType.getELiterals().toArray());
	}

	private void initTableMenu(final Table table) {
		Menu menu = new Menu(table);
		table.setMenu(menu);

		MenuItem menuItemAdd = new MenuItem(menu, SWT.NONE);
		menuItemAdd.setText("Add");
		menuItemAdd.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				addNewItem();
			}
		});

		MenuItem menuItemRemove = new MenuItem(menu, SWT.NONE);
		menuItemRemove.setText("Remove");
		menuItemRemove.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				removeSelectedItems();
			}
		});
	}

	private void addNewItem() {
		ItemValue<?> newValue = new CharSequenceValue("<enter a value>");
		this.enteredValues.add(newValue);
		this.tableViewer.refresh();
	}

	private void removeSelectedItems() {
		Table t = tableViewer.getTable();
		for(int i : t.getSelectionIndices()) {
			Object obj = tableViewer.getElementAt(i);
			this.enteredValues.remove(obj);
		}
		tableViewer.refresh();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Collection<Object> getValue() {
		Collection<Object> result = new ArrayList<Object>();
		for(Object item : this.enteredValues) {
			if(item instanceof ItemValue) {
				result.add(((ItemValue)item).getValue());
			} else if(item instanceof EEnumLiteral) {
				result.add(((EEnumLiteral)item).getLiteral());
			} else {
				result.add(item);
			}
		}
		return result;
	}

	@Override
	public boolean isValid() {
		return true;
	}

	private SelectionAdapter getSelectionAdapter(final TableColumn column) {
		SelectionAdapter selectionAdapter = new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				comparator.changeOrder();
				int dir = comparator.getDirection();
				tableViewer.getTable().setSortDirection(dir);
				tableViewer.getTable().setSortColumn(column);
				tableViewer.refresh();
			}
		};
		return selectionAdapter;
	}

	private class DefaultTableLabelProvider implements ITableLabelProvider {

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
			if(element instanceof EEnumLiteral) {
				return ((EEnumLiteral)element).getLiteral();
			}
			return element.toString();
		}

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
	}

	private class CustomViewerComparator extends ViewerComparator {

		private static final int DESCENDING = 1;

		private int direction = DESCENDING;

		public CustomViewerComparator() {
			direction = DESCENDING;
		}

		public int getDirection() {
			return direction == 1 ? SWT.DOWN : SWT.UP;
		}

		public void changeOrder() {
			// we only have one column.
			direction = 1 - direction;
		}

		@Override
		public int compare(Viewer viewer, Object e1, Object e2) {

			int rc = 0;

			if(e1 instanceof EEnumLiteral && e2 instanceof EEnumLiteral) {
				rc = ((EEnumLiteral)e1).getLiteral().compareTo(((EEnumLiteral)e2).getLiteral());
			} else {
				rc = super.compare(viewer, e1, e2);
			}

			// If descending order, flip the direction
			if(direction == DESCENDING) {
				rc = -rc;
			}
			return rc;
		}
	}
}

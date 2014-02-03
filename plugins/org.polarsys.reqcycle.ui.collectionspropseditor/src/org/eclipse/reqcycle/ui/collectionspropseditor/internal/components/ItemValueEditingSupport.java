/*******************************************************************************
 *  * Copyright (c) 2013-2014 AtoS.
 *  * All rights reserved. This program and the accompanying materials
 *  * are made available under the terms of the Eclipse Public License v1.0
 *  * which accompanies this distribution, and is available at
 *  * http://www.eclipse.org/legal/epl-v10.html
 *  *
 *  * Contributors:
 *  *    {INITIAL AUTHOR} - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.reqcycle.ui.collectionspropseditor.internal.components;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;

class ItemValueEditingSupport extends EditingSupport {

	private final TableViewer tableViewer;

	public ItemValueEditingSupport(TableViewer tableViewer) {
		super(tableViewer);
		this.tableViewer = tableViewer;
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		return new TextCellEditor(this.tableViewer.getTable());
	}

	@Override
	protected boolean canEdit(Object element) {
		return (element instanceof ItemValue);
	}

	@Override
	protected Object getValue(Object element) {
		return ((ItemValue<?>)element).getValue();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected void setValue(Object element, Object value) {
		ItemValue itemValue = (ItemValue)element;
		itemValue.setValue(value);
		this.tableViewer.update(element, null);
	}

}

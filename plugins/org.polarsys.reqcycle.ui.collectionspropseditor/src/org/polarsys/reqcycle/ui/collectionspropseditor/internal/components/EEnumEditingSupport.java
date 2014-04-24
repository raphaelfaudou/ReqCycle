/*******************************************************************************
 * Copyright (c) 2013 AtoS
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html *
 * Contributors:
 * Papa Issa Diakhate (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.ui.collectionspropseditor.internal.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;

class EEnumEditingSupport extends EditingSupport {

	private final TableViewer tableViewer;

	public EEnumEditingSupport(TableViewer tableViewer) {
		super(tableViewer);
		this.tableViewer = tableViewer;
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		if(element instanceof EEnumLiteral) {
			return new ComboBoxCellEditor(this.tableViewer.getTable(), this.getItems((EEnumLiteral)element));
		}
		return null;
	}

	private String[] getItems(final EEnumLiteral literal) {
		Collection<EEnumLiteral> literals = literal.getEEnum().getELiterals();
		List<String> items = new ArrayList<String>();
		for(EEnumLiteral l : literals) {
			items.add(l.getLiteral());
		}
		return items.toArray(new String[items.size()]);
	}

	@Override
	protected boolean canEdit(Object element) {
		return false;
	}

	@Override
	protected Object getValue(Object element) {
		if(element instanceof EEnumLiteral) {
			return element;
		}
		return null;
	}

	@Override
	protected void setValue(Object element, Object value) {
		// TODO Auto-generated method stub

	}

}

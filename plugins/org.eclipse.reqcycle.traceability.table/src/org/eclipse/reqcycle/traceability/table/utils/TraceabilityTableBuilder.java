/*******************************************************************************
 * Copyright (c) 2012 Original authors and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Original authors and others - initial API and implementation
 ******************************************************************************/
package org.eclipse.reqcycle.traceability.table.utils;

import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.extension.builder.NatTableBuilder;
import org.eclipse.nebula.widgets.nattable.extension.builder.model.TableColumn;
import org.eclipse.nebula.widgets.nattable.extension.builder.model.TableModel;
import org.eclipse.reqcycle.traceability.table.TraceabilityRow;
import org.eclipse.swt.widgets.Composite;

import ca.odell.glazedlists.EventList;

public class TraceabilityTableBuilder extends NatTableBuilder<TraceabilityRow> {

	private static TableColumn[] columnProperties = new TableColumn[]{ new TableColumn(0, "Link type"), new TableColumn(1, "From").setWidth(300), new TableColumn(2, "To").setWidth(300) };

	protected EventList<TraceabilityRow> eventList = null;

	protected NatTable natTable = null;

	public TraceabilityTableBuilder(Composite parent, EventList<TraceabilityRow> list) {
		super(parent, new CustomTableModel(), list, TraceabilityRow.rowIDAccessor);
		this.eventList = list;
	}

	public static class CustomTableModel extends TableModel {

		public CustomTableModel() {
			super(TraceabilityTableBuilder.columnProperties);
			this.enableColumnGroups = false;
			this.enableColumnCategories = false;
			this.enableFullRowSelection = true;
		}
	}


	@Override
	public NatTable setupLayerStacks() {
		this.natTable = super.setupLayerStacks();
		return this.natTable;
	}

	@Override
	protected void configureColumnHeaderRightClickMenu() {
	}


}

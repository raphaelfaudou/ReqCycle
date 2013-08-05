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
package org.eclipse.reqcycle.traceability.table;

import java.io.Serializable;
import java.util.Set;

import org.eclipse.nebula.widgets.nattable.data.IRowIdAccessor;
import org.eclipse.nebula.widgets.nattable.extension.builder.model.TableRow;
import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.traceability.table.utils.SerializableLink;
import org.eclipse.reqcycle.traceability.ui.TraceabilityUtils;
import org.eclipse.reqcycle.uri.model.Reachable;

import com.google.common.collect.Iterables;

public class TraceabilityRow implements TableRow {

	public static IRowIdAccessor<TraceabilityRow> rowIDAccessor = new IRowIdAccessor<TraceabilityRow>() {

		@Override
		public Serializable getRowId(TraceabilityRow rowObject) {
			//Assuming there's only 1 source and 1 target.
			Reachable r0 = Iterables.get(rowObject.link.getSources(), 0);
			Reachable r1 = Iterables.get(rowObject.link.getTargets(), 0);
			return new SerializableLink(r0, r1);
		}
	};

	protected Link link;

	public TraceabilityRow(Link traceabilityLink) {
		this.link = traceabilityLink;
	}

	@Override
	public Object getIdentifier() {
		return link;
	}

	@Override
	public Object getData() {
		return link;
	}

	@Override
	public Object getValue(int columnIndex) {
		Set<Reachable> set = null;
		if(columnIndex == 0) {
			return link.getKind().getLabel();
		} else if(columnIndex == 1) {
			set = link.getSources();
		} else if(columnIndex == 2) {
			set = link.getTargets();
		}
		if(set != null && set.size() == 1) {
			Reachable reachable = Iterables.get(set, 0);
			return TraceabilityUtils.getText(reachable);
		}
		return null;
	}

	@Override
	public void setValue(int columnIndex, Object value) {
		throw new UnsupportedOperationException();
	}



}

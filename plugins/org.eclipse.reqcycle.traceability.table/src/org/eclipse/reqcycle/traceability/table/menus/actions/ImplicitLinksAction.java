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
package org.eclipse.reqcycle.traceability.table.menus.actions;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine;
import org.eclipse.reqcycle.traceability.table.TraceabilityRow;
import org.eclipse.reqcycle.traceability.table.utils.TableUtils;
import org.eclipse.reqcycle.traceability.table.utils.TraceabilityTableBuilder;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.progress.UIJob;

import ca.odell.glazedlists.EventList;

import com.google.common.collect.Iterables;


public class ImplicitLinksAction extends Action {

	private TraceabilityTableBuilder tableBuilder;

	private ITraceabilityEngine engine;


	public ImplicitLinksAction(ITraceabilityEngine engine, TraceabilityTableBuilder builder) {
		super();
		setText("Implicit links");
		setToolTipText("Display all implicit traceability links");
		this.tableBuilder = builder;
		this.engine = engine;
	}


	@Override
	public void run() {
		new UIJob(Display.getCurrent(), "Fetching traceability links") {
			
			@Override
			public IStatus runInUIThread(IProgressMonitor monitor) {
				monitor.beginTask("Fetching traceability links", 100);
				Iterable<TraceabilityRow> requirementRows = TableUtils.createRequirementRows(engine);
				EventList<TraceabilityRow> eventList = tableBuilder.getEventList();
				eventList.getReadWriteLock().writeLock().lock();
				eventList.clear();
				Iterables.addAll(eventList, requirementRows);
				tableBuilder.getNatTable().refresh();
				eventList.getReadWriteLock().writeLock().unlock();
				monitor.done();
				return Status.OK_STATUS;
			}
		}.schedule();
	}
}

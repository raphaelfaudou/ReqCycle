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
import org.eclipse.reqcycle.traceability.table.model.TableController;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.progress.UIJob;


public class AllLinksAction extends Action {

	private TableController control;

	public AllLinksAction(TableController control) {
		super();
		setText("All links");
		setToolTipText("Display all traceability links");
		this.control = control;
	}


	@Override
	public void run() {
		new UIJob(Display.getCurrent(), "Fetching traceability links") {

			@Override
			public IStatus runInUIThread(IProgressMonitor monitor) {
				monitor.beginTask("Fetching traceability links", 100);
				control.displayAllLinks();
				return Status.OK_STATUS;
			}
		}.schedule();
	}
}

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

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.ui.dialogs.WorkspaceResourceDialog;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.reqcycle.traceability.table.TraceabilityTablePlugin;
import org.eclipse.reqcycle.traceability.table.model.TableController;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.progress.UIJob;


public class ExplicitLinksAction extends Action {

	private TableViewer viewer;

	private TableController control;

	public ExplicitLinksAction(TableViewer viewer, TableController control) {
		super();
		setText("Transverse links");
		setToolTipText("Select the project from which transverse links should will be retrieved");
		this.viewer = viewer;
		this.control = control;
	}


	@Override
	public void run() {
		Shell shell = viewer.getTable().getShell(); 
		BaseWorkbenchContentProvider contentProvider = new BaseWorkbenchContentProvider();
		WorkspaceResourceDialog dialog = new WorkspaceResourceDialog(shell, labelProvider, contentProvider);
		dialog.setTitle("Project selection");
		dialog.setMessage("Select the project from which transverse links will be retrieved");
		dialog.addFilter(projectFilter);
		dialog.setAllowMultiple(false);
		dialog.setInput(ResourcesPlugin.getWorkspace().getRoot());
		dialog.setValidator(new ISelectionStatusValidator() {

			@Override
			public IStatus validate(Object[] selection) {
				if((selection.length == 1) && (selection[0] instanceof IProject))
					return Status.OK_STATUS;
				return new Status(IStatus.ERROR, TraceabilityTablePlugin.PLUGIN_ID, "Select a project");
			}
		});
		int open = dialog.open();
		if(open == 0) {
			IContainer[] selectedContainers = dialog.getSelectedContainers();
			final IProject project = (IProject)selectedContainers[0];
			new UIJob(Display.getCurrent(), "Fetching traceability links") {

				@Override
				public IStatus runInUIThread(IProgressMonitor monitor) {
					monitor.beginTask("Fetching traceability links", 100);
					ExplicitLinksAction.this.control.displayExplicitLinks(project);
					return Status.OK_STATUS;
				}
			}.schedule();
		}
	}

	/**
	 * Label provider for the workspace resource dialog.
	 */
	protected static ILabelProvider labelProvider = new WorkbenchLabelProvider() {

		@Override
		public Color getForeground(Object element) {
			return Display.getDefault().getSystemColor(SWT.COLOR_BLACK);
		}

		@Override
		public Color getBackground(Object element) {
			return Display.getDefault().getSystemColor(SWT.COLOR_WHITE);
		}
	};

	protected static ViewerFilter projectFilter = new ViewerFilter() {

		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			if(element instanceof IProject) {
				return true;
			}
			return false;
		}
	};


}

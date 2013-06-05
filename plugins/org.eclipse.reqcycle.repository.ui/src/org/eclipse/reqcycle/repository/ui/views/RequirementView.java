/*****************************************************************************
 * Copyright (c) 2013 AtoS.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Anass RADOUANI (AtoS) anass.radouani@atos.net - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.reqcycle.repository.ui.views;

import java.util.Collection;

import javax.inject.Inject;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.reqcycle.core.ILogger;
import org.eclipse.reqcycle.dnd.DragRequirementSourceAdapter;
import org.eclipse.reqcycle.repository.requirement.data.util.DataUtil;
import org.eclipse.reqcycle.repository.ui.Activator;
import org.eclipse.reqcycle.repository.ui.providers.RequirementContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.PluginTransfer;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ziggurat.inject.ZigguratInject;

import DataModel.RequirementSource;

public class RequirementView extends ViewPart {

	private @Inject
	static ILogger logger = ZigguratInject.make(ILogger.class);

	public RequirementView() {
	}

	/** Requirement repositories TreeViewer */
	private TreeViewer viewer;

	@Override
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL) {
			@Override
			public void refresh() {
				super.refresh();
				refreshButton(getSelection());
			}
		};
		viewer.setContentProvider(new RequirementContentProvider());
		viewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				return DataUtil.getLabel(element);
			}
		});
		int dndOperations = DND.DROP_COPY | DND.DROP_MOVE;

		Transfer[] transfers;
		transfers = new Transfer[] { PluginTransfer.getInstance() };

		DragRequirementSourceAdapter listener = new DragRequirementSourceAdapter(viewer);
		ZigguratInject.inject(listener);
		viewer.addDragSupport(dndOperations, transfers,
				listener);
		getViewSite().setSelectionProvider(viewer);
	}

	public void setInput(Collection<RequirementSource> input) {
		viewer.setInput(input);
		viewer.refresh();
	}

	private void refreshButton(ISelection selection) {
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	// TODO : extract generic method
	public static void openRequirementView(Collection<RequirementSource> input) {
		if (!input.isEmpty()) {
			IWorkbenchPage page = Activator.getDefault().getWorkbench()
					.getActiveWorkbenchWindow().getActivePage();
			RequirementView requirementView = (RequirementView) page
					.findView("org.eclipse.reqcycle.repository.ui.views.requirements");
			if (requirementView == null) {
				try {
					page.showView("org.eclipse.reqcycle.repository.ui.views.requirements");
				} catch (PartInitException e) {
					boolean debug = logger.isDebug(Activator.OPTIONS_DEBUG,
							Activator.getDefault());
					if (debug) {
						logger.trace("Can't show the view : "
								+ "org.eclipse.reqcycle.repository.ui.views.requirements");
					}
				}
				requirementView = (RequirementView) page
						.findView("org.eclipse.reqcycle.repository.ui.views.requirements");
			}
			requirementView.setInput(input);
			try {
				page.showView("org.eclipse.reqcycle.repository.ui.views.requirements");
			} catch (PartInitException e) {
				boolean debug = logger.isDebug(Activator.OPTIONS_DEBUG,
						Activator.getDefault());
				if (debug) {
					logger.trace("Can't show the view : "
							+ "org.eclipse.reqcycle.repository.ui.views.requirements");
				}
			}
		}
	}

}

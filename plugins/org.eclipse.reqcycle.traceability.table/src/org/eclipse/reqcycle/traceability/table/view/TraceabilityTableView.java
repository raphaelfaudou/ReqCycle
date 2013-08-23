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
package org.eclipse.reqcycle.traceability.table.view;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine;
import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.traceability.storage.IStorageProvider;
import org.eclipse.reqcycle.traceability.table.model.TableController;
import org.eclipse.reqcycle.traceability.table.providers.TraceabilityLazyContentProvider;
import org.eclipse.reqcycle.traceability.ui.TraceabilityUtils;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.internal.PartSite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ziggurat.inject.ZigguratInject;

import com.google.common.collect.Iterables;

@SuppressWarnings("restriction")
public class TraceabilityTableView extends ViewPart {

	private static final String VIEW_ID = "org.eclipse.reqcycle.traceability.table.partdescriptor.traceability.table"; //$NON-NLS-1$

	@Inject
	protected ITraceabilityEngine engine;

	@Inject
	@Named("RDF")
	protected IStorageProvider provider;

	protected TableViewer viewer;

	protected TableController tableControl;

	public TraceabilityTableView() {
		ZigguratInject.inject(this);
	}

	@Override
	public void createPartControl(Composite parent) {

		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER | SWT.VIRTUAL);
		TraceabilityLazyContentProvider<Link> provider = TraceabilityLazyContentProvider.create(Link.class, viewer);
		viewer.setContentProvider(provider);

		//Creating the control.
		tableControl = new TableController(viewer);
		ZigguratInject.inject(tableControl);

		// special settings for the lazy Content provider
		viewer.setUseHashlookup(true);

		final Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		//Creating the columns.
		createModel();

		// A menu should react on the selection in the table.
		MenuManager menuManager = new MenuManager(null, VIEW_ID);
		menuManager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		getSite().setSelectionProvider(viewer);
		getSite().registerContextMenu(menuManager, viewer);
		hookActions();

		//Setting the input.
		tableControl.displayAllLinks();
	}

	private void createModel() {
		createTableViewerColumn("Link type", 50, 0).setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				if(element instanceof Link) {
					return ((Link)element).getKind().getLabel();
				}
				return super.getText(element);
			}
		});

		createTableViewerColumn("Upstream", 200, 1).setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				if(element instanceof Link) {
					Set<Reachable> set = ((Link)element).getSources();
					if(set != null && set.size() == 1) {
						Reachable reachable = Iterables.get(set, 0);
						return TraceabilityUtils.getText(reachable);
					}
				}
				return super.getText(element);
			}
		});

		createTableViewerColumn("Downstream", 200, 2).setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				if(element instanceof Link) {
					Set<Reachable> set = ((Link)element).getTargets();
					if(set != null && set.size() == 1) {
						Reachable reachable = Iterables.get(set, 0);
						return TraceabilityUtils.getText(reachable);
					}
				}
				return super.getText(element);
			}
		});
	}

	private void hookActions() {
		IActionBars bars = ((PartSite)getSite()).getActionBars();
		//		ExplicitLinksAction explicitAction = new ExplicitLinksAction(provider, builder);
		//		ImplicitLinksAction implicitAction = new ImplicitLinksAction(engine, builder);
		//		ZigguratInject.inject(explicitAction, implicitAction);
		//		bars.getToolBarManager().add(explicitAction);
		//		bars.getToolBarManager().add(implicitAction);
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(false);
		return viewerColumn;
	}

}

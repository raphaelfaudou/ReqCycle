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

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.data.ListDataProvider;
import org.eclipse.nebula.widgets.nattable.extension.builder.layers.BodyLayerStack;
import org.eclipse.nebula.widgets.nattable.selection.RowSelectionProvider;
import org.eclipse.nebula.widgets.nattable.selection.SelectionLayer;
import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine;
import org.eclipse.reqcycle.traceability.storage.IStorageProvider;
import org.eclipse.reqcycle.traceability.table.TraceabilityRow;
import org.eclipse.reqcycle.traceability.table.menus.actions.ExplicitLinksAction;
import org.eclipse.reqcycle.traceability.table.menus.actions.ImplicitLinksAction;
import org.eclipse.reqcycle.traceability.table.menus.conf.PopupMenuConfiguration;
import org.eclipse.reqcycle.traceability.table.utils.TraceabilityTableBuilder;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.internal.PartSite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ziggurat.inject.ZigguratInject;

import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;

import com.google.common.collect.Lists;

@SuppressWarnings("restriction")
public class TraceabilityTableView extends ViewPart {

	private static final String VIEW_ID = "org.eclipse.reqcycle.traceability.table.partdescriptor.traceability.table"; //$NON-NLS-1$

	@Inject
	protected ITraceabilityEngine engine;

	@Inject
	@Named("RDF")
	protected IStorageProvider provider;

	protected TraceabilityTableBuilder builder;

	public TraceabilityTableView() {
		ZigguratInject.inject(this);
	}

	@Override
	public void createPartControl(Composite parent) {

		List<TraceabilityRow> list = Lists.newArrayList();
		EventList<TraceabilityRow> eventList = GlazedLists.eventList(list);
		this.builder = new TraceabilityTableBuilder(parent, eventList);
		NatTable natTable = builder.setupLayerStacks();

		BodyLayerStack<TraceabilityRow> bodyLayerStack = builder.getBodyLayerStack();
		ListDataProvider<TraceabilityRow> dataProvider = bodyLayerStack.getDataProvider();
		SelectionLayer selectionLayer = bodyLayerStack.getSelectionLayer();

		/*
		 * Hooking extendable menus.
		 */
		ISelectionProvider selectionProvider = new RowSelectionProvider<TraceabilityRow>(selectionLayer, dataProvider, false);
		MenuManager menuManager = new MenuManager(null, VIEW_ID);
		menuManager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		getSite().setSelectionProvider(selectionProvider);
		getSite().registerContextMenu(menuManager, selectionProvider);
		natTable.addConfiguration(new PopupMenuConfiguration(natTable, menuManager));
		builder.build();

		/*
		 * Hooking actions in the action bar.
		 */
		hookActions(builder);
	}

	private void hookActions(TraceabilityTableBuilder builder) {
		IActionBars bars = ((PartSite)getSite()).getActionBars();
		ExplicitLinksAction explicitAction = new ExplicitLinksAction(provider, builder);
		ImplicitLinksAction implicitAction = new ImplicitLinksAction(engine, builder);
		ZigguratInject.inject(explicitAction, implicitAction);
		bars.getToolBarManager().add(explicitAction);
		bars.getToolBarManager().add(implicitAction);
	}

	@Override
	public void setFocus() {
	}

}

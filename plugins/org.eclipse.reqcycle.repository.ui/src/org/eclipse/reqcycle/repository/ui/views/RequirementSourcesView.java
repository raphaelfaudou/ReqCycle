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

import javax.inject.Inject;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.reqcycle.repository.requirement.data.IRequirementSourceManager;
import org.eclipse.reqcycle.repository.ui.Activator;
import org.eclipse.reqcycle.repository.ui.Messages;
import org.eclipse.reqcycle.repository.ui.actions.AddRequirementSourceAction;
import org.eclipse.reqcycle.repository.ui.actions.DeleteRequirementSourceAction;
import org.eclipse.reqcycle.repository.ui.actions.EditMappingAction;
import org.eclipse.reqcycle.repository.ui.actions.OpenRequirementViewAction;
import org.eclipse.reqcycle.repository.ui.actions.RefreshViewAction;
import org.eclipse.reqcycle.repository.ui.actions.SynchronizeRequirementSourceActionStub;
import org.eclipse.reqcycle.repository.ui.providers.RequirementSourceContentProvider;
import org.eclipse.reqcycle.repository.ui.providers.RequirementSourceLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ziggurat.inject.ZigguratInject;

import DataModel.RequirementSource;

/**
 * The view for connected requirement resources
 */
public class RequirementSourcesView extends ViewPart {

	/** The ID of the view as specified by the extension. */
	public static final String ID = Messages.REQ_RESOURCE_VIEW_ID;

	/** Requirement repositories TreeViewer */
	private TreeViewer viewer;

	/** Adds requirement repository Action */
	private Action addRepoAction;

	/** Removes requirement repository Action */
	private Action deleteRequirementSourceAction;
	
	/** Opens requirement view action */
	private Action openRequirementViewAction;
	
	/** Changes the repository mapping Action */
	private Action editMappingAction;
	
	/** Refreshes the view Action */
	private Action refreshViewAction;

	/** Navigation bar adapter for the tree viewer */
	private DrillDownAdapter drillDownAdapter;

	/** Synchronize Resource Stub Action */
	private SynchronizeRequirementSourceActionStub synchResourceAction;

	/** Add location icon */
	private static final String ICON_ADD_LOCATION = Messages.ADD_RESOURCE_ICON;

	/** Remove location icon */
	private static final String ICON_DELETE_LOCATION = Messages.DELETE_RESOURCE_ICON;

	/** Synchronize resource icon */
	private static final String ICON_SYNCHRONIZE = Messages.SYNC_RESOURCE_ICON;
	
	/** Open Requirement View button icon */
	private static final String ICON_OPEN = "icons/open.png";

	/** Requirement Source Manager */
	private @Inject
	IRequirementSourceManager requirementSourceManager = ZigguratInject.make(IRequirementSourceManager.class);

	
	/**
	 * The constructor.
	 */
	public RequirementSourcesView() {
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL) {
			@Override
			public void refresh() {
				super.refresh();
				refreshButton(getSelection());
			}
		};
		
		drillDownAdapter = new DrillDownAdapter(viewer);
		viewer.setContentProvider(new RequirementSourceContentProvider());
		viewer.setLabelProvider(new RequirementSourceLabelProvider());
		viewer.setInput(requirementSourceManager.getRepositoryMap().keySet());

		makeActions();
		hookContextMenu();
		contributeToActionBars();
		hookListeners();
	}

	/**
	 * Refresh the View buttons
	 * 
	 * @param iSelection
	 *        the View iSelection
	 */
	public void refreshButton(ISelection iSelection) {
		Object selectedElement = null;
		if (iSelection instanceof IStructuredSelection) {
			selectedElement = ((IStructuredSelection) iSelection).getFirstElement();
		}
		
		if(deleteRequirementSourceAction != null) {
			deleteRequirementSourceAction.setEnabled(selectedElement!=null);
		}
		if(openRequirementViewAction != null) {
			openRequirementViewAction.setEnabled(selectedElement!=null);
		}
		if(editMappingAction != null) {
			editMappingAction.setEnabled(selectedElement instanceof RequirementSource);
		}
	}

	/**
	 * Adds all components Listeners
	 */
	private void hookListeners() {
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				ISelection selection = event.getSelection();
				refreshButton(selection);
			}
		});
	}

	/**
	 * Create The context menu
	 */
	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu"); //$NON-NLS-1$
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				RequirementSourcesView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	/**
	 * Fills the action Bars
	 */
	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	/**
	 * Fills the local Pull Down menu
	 * 
	 * @param manager
	 *        the menu manager
	 */
	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(addRepoAction);
		manager.add(new Separator());
		manager.add(deleteRequirementSourceAction);
		manager.add(new Separator());
		manager.add(openRequirementViewAction);
		manager.add(new Separator());
		manager.add(synchResourceAction);
	}

	/**
	 * Fills the context menu
	 * 
	 * @param manager
	 *        the context menu manager
	 */
	private void fillContextMenu(IMenuManager manager) {
		manager.add(addRepoAction);
		manager.add(deleteRequirementSourceAction);
		manager.add(openRequirementViewAction);
		manager.add(synchResourceAction);
		manager.add(editMappingAction);
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	/**
	 * Fills the local Tool Bar
	 * 
	 * @param manager
	 *        The tool Bar manager
	 */
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(addRepoAction);
		manager.add(deleteRequirementSourceAction);
		manager.add(openRequirementViewAction);
		manager.add(synchResourceAction);
		manager.add(refreshViewAction);
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
	}

	/**
	 * Creates the View Actions
	 */
	private void makeActions() {
		addRepoAction = new AddRequirementSourceAction(viewer);
		addRepoAction.setText(Messages.ADD_RESOURCE_TEXT);
		addRepoAction.setToolTipText(Messages.ADD_RESOURCE_TEXT);
		addRepoAction.setImageDescriptor(Activator.getImageDescriptor(ICON_ADD_LOCATION));

		deleteRequirementSourceAction = new DeleteRequirementSourceAction(viewer);
		deleteRequirementSourceAction.setText(Messages.REMOVE_RESOURCE_TEXT);
		deleteRequirementSourceAction.setToolTipText(Messages.REMOVE_RESOURCE_TEXT);
		deleteRequirementSourceAction.setImageDescriptor(Activator.getImageDescriptor(ICON_DELETE_LOCATION));
		deleteRequirementSourceAction.setEnabled(false);
		
		openRequirementViewAction = new OpenRequirementViewAction(viewer);
		openRequirementViewAction.setText("Open Requirement View");
		openRequirementViewAction.setToolTipText("Open Requirement View");
		openRequirementViewAction.setImageDescriptor(Activator.getImageDescriptor(ICON_OPEN));
		openRequirementViewAction.setEnabled(false);

		synchResourceAction = new SynchronizeRequirementSourceActionStub(viewer);
		synchResourceAction.setText(Messages.SYNC_RESOURCE_TEXT);
		synchResourceAction.setToolTipText("Synchronization not available");//Messages.SYNC_RESOURCE_TEXT);
		synchResourceAction.setImageDescriptor(Activator.getImageDescriptor(ICON_SYNCHRONIZE));
		synchResourceAction.setEnabled(false);
		
		editMappingAction = new EditMappingAction(viewer);
		editMappingAction.setText("Edit Mapping");
		editMappingAction.setToolTipText("Edit Mapping");
		// TODO : add image change mapping
//		changeMappingAction.setImageDescriptor();
		editMappingAction.setEnabled(false);
		
		refreshViewAction = new RefreshViewAction(viewer);
		refreshViewAction.setToolTipText("Refresh View");
		refreshViewAction.setImageDescriptor(Activator.getImageDescriptor("icons/refresh.gif"));
		refreshViewAction.setEnabled(true);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}

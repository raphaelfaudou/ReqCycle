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

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import javax.inject.Inject;

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.reqcycle.core.ILogger;
import org.eclipse.reqcycle.dnd.DragRequirementSourceAdapter;
import org.eclipse.reqcycle.predicates.core.api.IPredicate;
import org.eclipse.reqcycle.repository.data.util.DataUtil;
import org.eclipse.reqcycle.repository.ui.Activator;
import org.eclipse.reqcycle.repository.ui.actions.SelectPredicatesFiltersAction;
import org.eclipse.reqcycle.repository.ui.actions.SelectRequirementSourcesAction;
import org.eclipse.reqcycle.repository.ui.providers.DummyInputContentProvider;
import org.eclipse.reqcycle.repository.ui.providers.DummyInputContentProvider.DummyInput;
import org.eclipse.reqcycle.repository.ui.providers.DummyInputContentProvider.DummyObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.PluginTransfer;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ziggurat.inject.ZigguratInject;

import DataModel.RequirementSource;

public class RequirementView extends ViewPart {

	public static final String VIEW_ID = "org.eclipse.reqcycle.repository.ui.views.requirements";

	private @Inject
	static ILogger logger = ZigguratInject.make(ILogger.class);

	private Action selectPredicatesFilterAction;

	private Action selectRequirementSourcesAction;

	public RequirementView() {
	}

	/** Requirement repositories TreeViewer */
	private TreeViewer viewer;

	private Collection<IPredicate> predicates;

	@Override
	public void createPartControl(Composite parent) {
		this.viewer = new TreeViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL) {

			@Override
			public void refresh() {
				super.refresh();
				refreshButton(getSelection());
			}
		};
		final ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		// final DummyInputContentProvider dummyInputContentProvider = new DummyInputContentProvider(adapterFactory,
		// null); XXX
		final DummyInputContentProvider dummyInputContentProvider = new DummyInputContentProvider(adapterFactory);
		getViewer().setContentProvider(dummyInputContentProvider);
		getViewer().setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory) {

			@Override
			public String getText(Object element) {
				if(element instanceof DummyInput) {
					return ((DummyInput)element).getDisplayName();
				} else if(element instanceof DummyObject) {
					return super.getText(((DummyObject)element).getEobj());
				}
				return DataUtil.getLabel(element);
			}

			@Override
			public Image getImage(Object object) {
				if(object instanceof DummyObject) {
					return super.getImage(((DummyObject)object).getEobj());
				}
				return super.getImage(object);
			}
		});
		int dndOperations = DND.DROP_COPY | DND.DROP_MOVE;

		Transfer[] transfers;
		transfers = new Transfer[]{ PluginTransfer.getInstance() };

		DragRequirementSourceAdapter listener = new DragRequirementSourceAdapter(getViewer());
		ZigguratInject.inject(listener);
		getViewer().addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				// TODO Auto-generated method stub
				event.toString();
			}
		});
		getViewer().addDragSupport(dndOperations, transfers, listener);
		getViewSite().setSelectionProvider(getViewer());

		makeActions();
		contributeToActionBars();
		hookListeners();
	}

	/**
	 * Should be a Collection {@link DummyInput} objects.
	 * 
	 * @param input
	 */
	public void setInput(Collection<DummyInput> input) {
		getViewer().setInput(input);
		getViewer().refresh();
	}

	private void refreshButton(ISelection selection) {
	}

	@Override
	public void setFocus() {
		getViewer().getControl().setFocus();
	}

	// TODO : extract generic method
	public static void openRequirementView(Collection<RequirementSource> input) {
		openRequirementView(input, null);
	}

	// TODO : extract generic method
	public static void openRequirementView(final Collection<RequirementSource> input, ViewerFilter filter) {

		if(!input.isEmpty()) {
			IWorkbenchPage page = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
			RequirementView requirementView = (RequirementView)page.findView(VIEW_ID);
			if(requirementView == null) {
				try {
					page.showView(VIEW_ID);
				} catch (PartInitException e) {
					boolean debug = logger.isDebug(Activator.OPTIONS_DEBUG, Activator.getDefault());
					if(debug) {
						logger.trace("Can't show the view : " + VIEW_ID);
					}
				}
				requirementView = (RequirementView)page.findView(VIEW_ID);
			}

			final Collection<DummyInput> dummyInputs = new ArrayList<DummyInput>();
			dummyInputs.add(new DummyInput(input));
			requirementView.setInput(dummyInputs);

			if(filter != null) {
				requirementView.getViewer().addFilter(filter);
			}
			try {
				page.showView(VIEW_ID);
			} catch (PartInitException e) {
				boolean debug = logger.isDebug(Activator.OPTIONS_DEBUG, Activator.getDefault());
				if(debug) {
					logger.trace("Can't show the view : " + VIEW_ID);
				}
			}
		}
	}
	
	protected static IViewPart createNewView() {
		IWorkbenchPage activePage = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		int nbView = 0;
		for (IViewReference ref : activePage.getViewReferences()) {
			if (ref.getId().startsWith(VIEW_ID)) {
				nbView++;
			}
		}
		// increment to have the second view named #2
		nbView++;
		IViewPart view = null;
		try {
			view = activePage.showView(VIEW_ID, VIEW_ID + "_" + nbView,
					IWorkbenchPage.VIEW_ACTIVATE);
			// view.
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		return view;

	}

	/**
	 * @param input
	 * @param predicates
	 *        - The collection of predicates to use for filtering the same input.
	 */
	public static void openNewFilteredRequirementView(final Collection<RequirementSource> input, final Collection<IPredicate> predicates) {

		if(!input.isEmpty()) {
			
			IViewPart view = createNewView();
			if(view == null) {
				return;
			}
			
			RequirementView reqView = (RequirementView)view;

			final Collection<DummyInput> dummyInputs = new ArrayList<DummyInput>();
			if(predicates == null || predicates.isEmpty()) {
				dummyInputs.add(new DummyInput(input));
			} else {
				for(IPredicate p : predicates) {
					final DummyInput dInput = new DummyInput(input);
					dInput.setPredicate(p);
					dummyInputs.add(dInput);
				}
			}

			final ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

			final DummyInputContentProvider dummyInputContentProvider = new DummyInputContentProvider(adapterFactory);

			reqView.getViewer().setContentProvider(dummyInputContentProvider);

			reqView.setPredicates(predicates);

			reqView.setInput(dummyInputs);
		}
	}

	public TreeViewer getViewer() {
		return viewer;
	}

	public Collection<IPredicate> getPredicates() {
		return predicates;
	}

	public void setPredicates(Collection<IPredicate> predicates) {
		this.predicates = predicates;
	}

	/**
	 * Fills the action Bars
	 */
	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalToolBar(bars.getToolBarManager());
	}

	/**
	 * Fills the local ToolBar
	 * 
	 * @param manager
	 *        The tool Bar manager
	 */
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(selectPredicatesFilterAction);
		manager.add(selectRequirementSourcesAction);
		manager.add(new Separator());
	}

	private void makeActions() {
		selectPredicatesFilterAction = new SelectPredicatesFiltersAction(getViewer());
		ZigguratInject.inject(selectPredicatesFilterAction);
		selectPredicatesFilterAction.setText("Select Predicates Filters");
		selectPredicatesFilterAction.setToolTipText("Select the list of predicates to use for filtering into the tree viewer.");
		selectPredicatesFilterAction.setEnabled(false);
		// TODO: change icon ...
		// selectPredicatesFilterAction.setImageDescriptor(Activator.getImageDescriptor(ICON_OPEN));

		selectRequirementSourcesAction = new SelectRequirementSourcesAction(getViewer());
		ZigguratInject.inject(selectRequirementSourcesAction);
		selectRequirementSourcesAction.setText("Select Requirement Sources");
		selectRequirementSourcesAction.setToolTipText("Select the list of requirement sources.");
		selectRequirementSourcesAction.setEnabled(false);
		// TODO: change icon ...
		// selectRequirementSourcesAction.setImageDescriptor(Activator.getImageDescriptor(ICON_DELETE_LOCATION));
	}

	/**
	 * Adds all components listeners
	 */
	private void hookListeners() {
		getViewer().addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				ISelection selection = event.getSelection();
				refreshButton(selection);
			}
		});
	}
}

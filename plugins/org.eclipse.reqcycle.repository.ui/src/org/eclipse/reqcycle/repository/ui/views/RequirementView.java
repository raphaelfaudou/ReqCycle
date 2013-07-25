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
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.reqcycle.core.ILogger;
import org.eclipse.reqcycle.dnd.DragRequirementSourceAdapter;
import org.eclipse.reqcycle.predicates.core.api.IPredicate;
import org.eclipse.reqcycle.repository.data.util.DataUtil;
import org.eclipse.reqcycle.repository.ui.Activator;
import org.eclipse.reqcycle.repository.ui.providers.ComposedRequirementContentProvider;
import org.eclipse.reqcycle.repository.ui.providers.ComposedRequirementContentProvider.DummyInput;
import org.eclipse.reqcycle.repository.ui.providers.RequirementContentProvider;
import org.eclipse.reqcycle.repository.ui.views.filters.ComposedRequirementViewerFilter;
import org.eclipse.reqcycle.repository.ui.views.filters.RequirementViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.PluginTransfer;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ziggurat.inject.ZigguratInject;

import DataModel.RequirementSource;

public class RequirementView extends ViewPart {

    public static final String VIEW_ID = "org.eclipse.reqcycle.repository.ui.views.requirements";

    private @Inject
    static ILogger             logger  = ZigguratInject.make(ILogger.class);

    public RequirementView() {
    }

    /** Requirement repositories TreeViewer */
    private TreeViewer viewer;

    @Override
    public void createPartControl(Composite parent) {
        this.viewer = new TreeViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL) {
            @Override
            public void refresh() {
                super.refresh();
                refreshButton(getSelection());
            }
        };
        ComposedAdapterFactory factory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        // final ComposedRequirementContentProvider cReqContentProvider = new ComposedRequirementContentProvider(
        // new AdapterFactoryContentProvider(factory));
        final ComposedRequirementContentProvider cReqContentProvider = new ComposedRequirementContentProvider(
                new RequirementContentProvider());
        getViewer().setContentProvider(cReqContentProvider);
        getViewer().setLabelProvider(new AdapterFactoryLabelProvider(factory) {
            @Override
            public String getText(Object element) {
                if (element instanceof DummyInput) {
                    return ((DummyInput) element).getName();
                }
                return DataUtil.getLabel(element);
            }
        });
        int dndOperations = DND.DROP_COPY | DND.DROP_MOVE;

        Transfer[] transfers;
        transfers = new Transfer[] { PluginTransfer.getInstance() };

        DragRequirementSourceAdapter listener = new DragRequirementSourceAdapter(getViewer());
        ZigguratInject.inject(listener);
        getViewer().addDragSupport(dndOperations, transfers, listener);
        getViewSite().setSelectionProvider(getViewer());
    }

    /**
     * Should be a Collection of {@link RequirementSource} or {@link DummyInput} objects.
     * 
     * @param input
     */
    public void setInput(Object input) {
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
    public static void openRequirementView(Collection<RequirementSource> input, ViewerFilter filter) {
        if (!input.isEmpty()) {
            IWorkbenchPage page = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
            RequirementView requirementView = (RequirementView) page.findView(VIEW_ID);
            if (requirementView == null) {
                try {
                    page.showView(VIEW_ID);
                } catch (PartInitException e) {
                    boolean debug = logger.isDebug(Activator.OPTIONS_DEBUG, Activator.getDefault());
                    if (debug) {
                        logger.trace("Can't show the view : " + VIEW_ID);
                    }
                }
                requirementView = (RequirementView) page.findView(VIEW_ID);
            }
            requirementView.setInput(input);

            if (filter != null) {
                requirementView.getViewer().addFilter(filter);
            }
            try {
                page.showView(VIEW_ID);
            } catch (PartInitException e) {
                boolean debug = logger.isDebug(Activator.OPTIONS_DEBUG, Activator.getDefault());
                if (debug) {
                    logger.trace("Can't show the view : " + VIEW_ID);
                }
            }
        }
    }

    // TODO : extract generic method
    public static void openNewRequirementView(Collection<RequirementSource> input, ViewerFilter filter) {
        if (!input.isEmpty()) {
            IWorkbenchPage page = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
            try {
                String secondaryID = UUID.randomUUID().toString();
                page.showView(VIEW_ID, secondaryID, IWorkbenchPage.VIEW_ACTIVATE);
                String fullId = VIEW_ID + ":" + secondaryID;
                for (IViewReference viewRef : page.getViewReferences()) {
                    if (fullId.equals(viewRef.getId())) {
                        RequirementView reqView = (RequirementView) viewRef.getView(true);
                        reqView.setInput(input);
                        if (filter != null) {
                            reqView.getViewer().addFilter(filter);
                            if (filter instanceof RequirementViewerFilter) {
                                String predicateName = ((RequirementViewerFilter) filter).getPredicate()
                                        .getDisplayName();
                                reqView.setPartName(reqView.getPartName() + " (" + predicateName + ")");
                            }
                        }
                        break;
                    }
                }

            } catch (PartInitException e) {
                boolean debug = logger.isDebug(Activator.OPTIONS_DEBUG, Activator.getDefault());
                if (debug) {
                    logger.trace("Can't show the view : " + VIEW_ID);
                }
            }
        }
    }

    /**
     * @param input
     * @param predicates - The collection of predicates to use for filtering the same input.
     */
    public static void openNewFilteredRequirementView(final Collection<RequirementSource> input,
            final Collection<IPredicate> predicates) {

        if (!input.isEmpty()) {
            IWorkbenchPage page = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
            try {
                String secondaryID = UUID.randomUUID().toString();
                page.showView(VIEW_ID, secondaryID, IWorkbenchPage.VIEW_ACTIVATE);
                String fullId = VIEW_ID + ":" + secondaryID;
                for (IViewReference viewRef : page.getViewReferences()) {
                    if (fullId.equals(viewRef.getId())) {
                        RequirementView reqView = (RequirementView) viewRef.getView(true);

                        final List<DummyInput> dummyInputs = new ArrayList<ComposedRequirementContentProvider.DummyInput>();

                        final ComposedRequirementViewerFilter composedReqFilter = new ComposedRequirementViewerFilter(
                                new ArrayList<RequirementViewerFilter>());
                        for (IPredicate p : predicates) {
                            final DummyInput dInput = new DummyInput(input);
                            dInput.setName(p.getDisplayName());
                            dummyInputs.add(dInput);
                            RequirementViewerFilter reqFilter = new RequirementViewerFilter(p);
                            composedReqFilter.getReqFilters().add(reqFilter);
                        }
                        reqView.setInput(dummyInputs);
                        reqView.getViewer().addFilter(composedReqFilter);
                        break;
                    }
                }

            } catch (PartInitException e) {
                boolean debug = logger.isDebug(Activator.OPTIONS_DEBUG, Activator.getDefault());
                if (debug) {
                    logger.trace("Can't show the view : " + VIEW_ID);
                }
            }
        }
    }

    public TreeViewer getViewer() {
        return viewer;
    }

}

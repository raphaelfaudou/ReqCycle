package org.eclipse.reqcycle.repository.ui.actions;

import javax.inject.Inject;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.reqcycle.repository.data.IRequirementSourceManager;
import org.eclipse.ziggurat.inject.ZigguratInject;

public class SelectRequirementSourcesAction extends Action {

    private final TreeViewer  treeViewer;

    /** Requirement Source Manager */
    private @Inject
    IRequirementSourceManager requirementSourceManager;

    public SelectRequirementSourcesAction(TreeViewer viewer) {
        this.requirementSourceManager = ZigguratInject.make(IRequirementSourceManager.class);
        this.treeViewer = viewer;
    }

    @Override
    public void run() {
        super.run();

        treeViewer.refresh();
    }
}

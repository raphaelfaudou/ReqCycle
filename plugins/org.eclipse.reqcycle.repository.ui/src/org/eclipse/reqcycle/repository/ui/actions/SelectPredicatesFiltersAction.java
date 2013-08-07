package org.eclipse.reqcycle.repository.ui.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.reqcycle.predicates.core.api.IPredicate;
import org.eclipse.reqcycle.predicates.ui.util.PredicatesUIHelper;
import org.eclipse.reqcycle.repository.ui.providers.DummyInputContentProvider.DummyInput;

import DataModel.RequirementSource;

public class SelectPredicatesFiltersAction extends Action {

    private final TreeViewer treeViewer;

    public SelectPredicatesFiltersAction(TreeViewer viewer) {
        this.treeViewer = viewer;
    }

    @Override
    public void run() {
        super.run();
        Collection<IPredicate> selectedPredicates = new ArrayList<IPredicate>();
        //FIXME : use the result of openPredicatesChooser
        PredicatesUIHelper.openPredicatesChooser(selectedPredicates);
        final Collection<DummyInput> dummyInputs = new ArrayList<DummyInput>();
        @SuppressWarnings("unchecked")
        Collection<DummyInput> input = (Collection<DummyInput>) this.treeViewer.getInput();
        if (input != null && !input.isEmpty()) {
            for (Iterator<DummyInput> iterator = input.iterator(); iterator.hasNext();) {
                DummyInput dummyInput = (DummyInput) iterator.next();
                if (dummyInput != null) {
                    Collection<RequirementSource> realInput = dummyInput.getInput();
                    for (Iterator<IPredicate> iterator2 = selectedPredicates.iterator(); iterator2.hasNext();) {
                        IPredicate p = (IPredicate) iterator2.next();
                        DummyInput newDummyInput = new DummyInput(realInput);
                        newDummyInput.setPredicate(p);
                        dummyInputs.add(newDummyInput);
                    }
                    if (dummyInputs.isEmpty()) {
                        dummyInputs.add(new DummyInput(realInput));
                    }
                    this.treeViewer.setInput(dummyInputs);
                    this.treeViewer.refresh();
                    break;
                }
            }
        }
    }

}

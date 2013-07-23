package org.eclipse.reqcycle.repository.ui.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.inject.Inject;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.reqcycle.core.ILogger;
import org.eclipse.reqcycle.predicates.core.api.IPredicate;
import org.eclipse.reqcycle.predicates.persistance.util.PredicatesConfManager;
import org.eclipse.reqcycle.predicates.ui.providers.PredicatesTableLabelProvider;
import org.eclipse.reqcycle.repository.data.util.DataUtil;
import org.eclipse.reqcycle.repository.ui.views.RequirementView;
import org.eclipse.reqcycle.ui.components.dialogs.CheckBoxInputDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ziggurat.inject.ZigguratInject;

import DataModel.RequirementSource;

public class OpenFilteredRequirementViewAction extends Action {

    @Inject
    ILogger                             logger = ZigguratInject.make(ILogger.class);

    private final PredicatesConfManager confManager;

    private TreeViewer                  viewer;

    public OpenFilteredRequirementViewAction(TreeViewer viewer) {
        this.viewer = viewer;
        this.confManager = new PredicatesConfManager();
    }

    @Override
    public void run() {
        ISelection selection = viewer.getSelection();
        if (selection instanceof IStructuredSelection) {

            Collection<Object> selectedObj = new ArrayList<Object>();
            for (Iterator<?> iterator = ((IStructuredSelection) selection).iterator(); iterator.hasNext();) {
                selectedObj.add(iterator.next());
            }

            Collection<RequirementSource> input = new ArrayList<RequirementSource>();
            for (Iterator<Object> iterator = selectedObj.iterator(); iterator.hasNext();) {
                Object obj = iterator.next();
                input.addAll(DataUtil.getRepositories(obj));
            }

            if (!input.isEmpty()) {
                try {
                    Collection<IPredicate> selectedPredicates = selectPredicatesToApply();
                    if (selectedPredicates != null && !selectedPredicates.isEmpty()) {
                        RequirementView.openNewFilteredRequirementView(input, selectedPredicates);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("Unable to open the View of filtered requirements : " + e.getMessage());
                    logger.error(e.toString());
                }
            }
        }
    }

    /**
     * Open a dialog which proposes to select a predicate to apply for filtering.
     * 
     * @return The selected predicates or an empty list if nothing is selected.
     */
    private Collection<IPredicate> selectPredicatesToApply() {
        Display display = Display.getCurrent();
        if (display == null) display = Display.getDefault();
        Collection<IPredicate> storedPredicates = confManager.getStoredPredicates();
        final CheckBoxInputDialog dialog = new CheckBoxInputDialog(display.getActiveShell(), "Requirement filtering",
                "Select a predicate to apply for filtering", storedPredicates, null);

        dialog.setLabelProvider(new PredicatesTableLabelProvider());
        if (Window.OK == dialog.open()) {
            Collection<IPredicate> predicates = new ArrayList<IPredicate>();
            for (Object obj : dialog.getSelectedItems()) {
                if (obj instanceof IPredicate) {
                    predicates.add((IPredicate) obj);
                }
            }
            return predicates;
        }
        return null;
    }

}

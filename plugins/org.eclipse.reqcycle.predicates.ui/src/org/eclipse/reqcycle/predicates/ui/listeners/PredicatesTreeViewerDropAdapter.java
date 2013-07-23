package org.eclipse.reqcycle.predicates.ui.listeners;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.dnd.EditingDomainViewerDropAdapter;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.reqcycle.predicates.core.api.IPredicate;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;

public class PredicatesTreeViewerDropAdapter extends EditingDomainViewerDropAdapter {

    public PredicatesTreeViewerDropAdapter(EditingDomain editingDomain, StructuredViewer viewer) {
        super(editingDomain, viewer);
    }

    @Override
    public void dragOver(DropTargetEvent event) {
        Object target = determineTarget(event);
        if (target == null || validateDrop(target)) {
            super.dragOver(event);
        } else {
            event.detail = DND.DROP_NONE;
        }
    }

    /**
     * Returns the target item of the given drop event.
     * 
     * @param event the event
     * @return The target of the drop, may be <code>null</code>.
     */
    protected Object determineTarget(DropTargetEvent event) {
        return event.item == null ? null : event.item.getData();
    }

    protected boolean validateDrop(Object target) {
        if (target instanceof IPredicate) {
            if (((IPredicate) target).getDisplayName() != null) {
                return false;
            }
        }
        return true;
    }

}

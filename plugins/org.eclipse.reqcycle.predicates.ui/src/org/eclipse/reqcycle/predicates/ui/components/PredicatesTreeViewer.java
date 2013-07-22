package org.eclipse.reqcycle.predicates.ui.components;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.reqcycle.predicates.core.api.IPredicate;
import org.eclipse.swt.widgets.Tree;

public class PredicatesTreeViewer extends TreeViewer {

    public PredicatesTreeViewer(Tree tree) {
        super(tree);
    }

    @Override
    public boolean isExpandable(Object element) {
        if (element instanceof IPredicate) {
            IPredicate predicate = (IPredicate) element;
            String displayName = predicate.getDisplayName();
            return displayName == null;
        }
        return super.isExpandable(element);
    }

}

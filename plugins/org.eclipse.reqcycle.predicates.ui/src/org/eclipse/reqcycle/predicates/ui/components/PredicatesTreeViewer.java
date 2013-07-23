package org.eclipse.reqcycle.predicates.ui.components;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.reqcycle.predicates.core.api.IPredicate;
import org.eclipse.swt.widgets.Tree;

public class PredicatesTreeViewer extends TreeViewer {

    private boolean mayExpandCustomPredicates;

    public PredicatesTreeViewer(Tree tree) {
        super(tree);
    }

    @Override
    public boolean isExpandable(Object element) {
        if (getMayExpandCustomPredicates() == false) {
            if (element instanceof IPredicate) {
                IPredicate predicate = (IPredicate) element;
                String displayName = predicate.getDisplayName();
                Resource resource = predicate.eResource();
                if (resource != null) {
                    EList<EObject> contents = resource.getContents();
                    EObject firstContent = contents.get(0);
                    if (firstContent instanceof IPredicate) {
                        if (predicate.equals(firstContent)) {
                            return super.isExpandable(element);
                        }
                    }
                }
                return displayName == null;
            }
        }
        return super.isExpandable(element);
    }

    public boolean getMayExpandCustomPredicates() {
        return mayExpandCustomPredicates;
    }

    public void setMayExpandCustomPredicates(boolean mayExpandCustomPredicates) {
        this.mayExpandCustomPredicates = mayExpandCustomPredicates;
    }

}

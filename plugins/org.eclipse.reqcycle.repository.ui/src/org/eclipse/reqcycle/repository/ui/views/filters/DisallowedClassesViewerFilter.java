package org.eclipse.reqcycle.repository.ui.views.filters;

import java.util.Collection;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.reqcycle.repository.ui.providers.DummyInputContentProvider.DummyObject;

/**
 * TODO : This class is supposed to be used only for temporary situations or testing purposes.
 */
public class DisallowedClassesViewerFilter extends ViewerFilter {

    private final Collection<Class<?>> disallowedClasses;

    public DisallowedClassesViewerFilter(Collection<Class<?>> disallowedClasses) {
        this.disallowedClasses = disallowedClasses;
    }

    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {
        if (this.disallowedClasses == null) return true;
        Class<?> parentElementClass = (parentElement instanceof DummyObject) ? ((DummyObject) parentElement).getEobj()
                .getClass() : parentElement.getClass();
        Class<?> elementClass = (element instanceof DummyObject) ? ((DummyObject) element).getEobj().getClass()
                : element.getClass();
        return !(this.disallowedClasses.contains(parentElementClass) || this.disallowedClasses.contains(elementClass));
    }

}

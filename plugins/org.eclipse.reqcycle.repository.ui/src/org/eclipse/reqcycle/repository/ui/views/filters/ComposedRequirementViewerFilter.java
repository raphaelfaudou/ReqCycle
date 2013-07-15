package org.eclipse.reqcycle.repository.ui.views.filters;

import java.util.Collection;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.reqcycle.repository.ui.providers.ComposedRequirementContentProvider.DummyInput;

public class ComposedRequirementViewerFilter extends ViewerFilter {

    private final Collection<RequirementViewerFilter> reqFilters;

    public ComposedRequirementViewerFilter(Collection<RequirementViewerFilter> requirementViewerFilters) {
        this.reqFilters = requirementViewerFilters;
    }

    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {
        if (element instanceof DummyInput || parentElement instanceof DummyInput) {
            return true;
        } else {
            RequirementViewerFilter reqFilter = this.getFilterForElement(viewer, parentElement, element);
            return reqFilter.select(viewer, parentElement, element);
        }
    }

    private RequirementViewerFilter getFilterForElement(final Viewer viewer, Object parentElement, Object element) {

        // FIXME: Got to return the correct & expected filter (predicate ...)
        return this.getReqFilters().iterator().next();
//        throw new UnsupportedOperationException("Unable to retrieve the " + this.getClass().getSimpleName()
//                + " filter.");
    }

    public Collection<RequirementViewerFilter> getReqFilters() {
        return this.reqFilters;
    }

}

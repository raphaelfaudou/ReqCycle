package org.polarsys.reqcycle.repository.ui.views.filters;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.polarsys.reqcycle.predicates.core.api.IPredicate;

public class RequirementViewerFilter extends ViewerFilter {

	/** The predicate to apply for filtering. */
	private final IPredicate predicate;

	public RequirementViewerFilter(IPredicate predicate) {
		super();
		this.predicate = predicate;
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		boolean match = this.predicate.match(element);
		return match;
	}

	public IPredicate getPredicate() {
		return this.predicate;
	}

}

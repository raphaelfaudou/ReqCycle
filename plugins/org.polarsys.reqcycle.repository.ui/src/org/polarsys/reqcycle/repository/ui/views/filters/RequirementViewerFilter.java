/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Anass Radouani (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
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

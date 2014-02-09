/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Papa Issa Diakhate (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.predicates.ui.components;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.TreeViewer;
import org.polarsys.reqcycle.predicates.core.api.IPredicate;
import org.eclipse.swt.widgets.Tree;

public class PredicatesTreeViewer extends TreeViewer {

	private boolean mayExpandCustomPredicates;

	public PredicatesTreeViewer(Tree tree) {
		super(tree);
	}

	@Override
	public boolean isExpandable(Object element) {
		if(getMayExpandCustomPredicates() == false) {
			if(element instanceof IPredicate) {
				IPredicate predicate = (IPredicate)element;
				String displayName = predicate.getDisplayName();
				Resource resource = predicate.eResource();
				if(resource != null) {
					EList<EObject> contents = resource.getContents();
					EObject firstContent = contents.get(0);
					if(firstContent instanceof IPredicate) {
						if(predicate.equals(firstContent)) {
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

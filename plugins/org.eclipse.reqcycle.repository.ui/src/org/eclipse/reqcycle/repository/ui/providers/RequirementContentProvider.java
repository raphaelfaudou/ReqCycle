/*****************************************************************************
 * Copyright (c) 2013 AtoS.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Anass RADOUANI (AtoS) anass.radouani@atos.net - Initial API and implementation
 *
  *****************************************************************************/

/**
 * 
 */
package org.eclipse.reqcycle.repository.ui.providers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import DataModel.Contained;
import DataModel.ReachableSection;
import DataModel.Requirement;
import DataModel.RequirementSource;

public class RequirementContentProvider implements ITreeContentProvider, IStructuredContentProvider {

	@Override
	public void dispose() {
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@Override
	public Object[] getElements(Object inputElement) {

		if(inputElement instanceof RequirementSource) {
			RequirementSource reqSource = (RequirementSource)inputElement;
			return (Requirement[])reqSource.getRequirements().toArray(new Requirement[reqSource.getRequirements().size()]);
		}
		
		if(inputElement instanceof Collection) {
			ArrayList<Contained> requirements = new ArrayList<Contained>();
			Iterator<?> iter = ((Collection<?>)inputElement).iterator();
			while (iter.hasNext()) {
				Object element = iter.next();
				if(element instanceof RequirementSource) {
					requirements.addAll(((RequirementSource)element).getRequirements());
				} else if (element instanceof Contained) {
					requirements.add((Contained)element);
				}
			}
			return (Contained[])requirements.toArray(new Contained[requirements.size()]);
		}
		return new Object[0];
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		
		if(parentElement instanceof ReachableSection) {
			EList<Contained> children = ((ReachableSection)parentElement).getChildren();
			return (Contained[])children.toArray(new Contained[children.size()]);
			
		}
		
		return new Object[0];
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if(element instanceof RequirementSource || (element instanceof ReachableSection && !((ReachableSection)element).getChildren().isEmpty()))
		{
			return true;
		}
		return false;
	}
}

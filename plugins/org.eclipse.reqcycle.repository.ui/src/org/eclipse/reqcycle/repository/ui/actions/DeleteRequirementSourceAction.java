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
package org.eclipse.reqcycle.repository.ui.actions;

import javax.inject.Inject;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.reqcycle.repository.data.IRequirementSourceManager;

/**
 * Action to delete a requirement source
 */
public class DeleteRequirementSourceAction extends Action {

	/** Requirement Source TreeViewer */
	private TreeViewer viewer;

	/** Requirement Source Manager */
	@Inject
	private IRequirementSourceManager requirementSourceManager;

	/**
	 * Constructor
	 * 
	 * @param viewer
	 *        The Requirement Resource Viewer
	 */
	public DeleteRequirementSourceAction(TreeViewer viewer) {
		this.viewer = viewer;
	}

	@Override
	public void run() {
		ISelection selection = viewer.getSelection();
		if(selection instanceof IStructuredSelection) {
			Object toRemove = ((IStructuredSelection)selection).getFirstElement();
			if(toRemove != null) {
				requirementSourceManager.remove(toRemove);
				viewer.refresh();
			}
		}
	}
}

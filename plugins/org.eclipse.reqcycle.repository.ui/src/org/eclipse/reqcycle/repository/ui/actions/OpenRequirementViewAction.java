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

import java.util.Collection;

import javax.inject.Inject;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.reqcycle.core.ILogger;
import org.eclipse.reqcycle.repository.data.util.DataUtil;
import org.eclipse.reqcycle.repository.ui.views.RequirementView;

import DataModel.RequirementSource;

/**
 * Action to open the requirement view
 */
public class OpenRequirementViewAction extends Action {

	/** Requirement repositories TreeViewer */
	private TreeViewer viewer;

	@Inject
	ILogger logger;


	/**
	 * Constructor
	 * 
	 * @param viewer
	 *        The Requirement Resource Viewer
	 */
	public OpenRequirementViewAction(TreeViewer viewer) {
		this.viewer = viewer;
	}

	@Override
	public void run() {
		ISelection selection = viewer.getSelection();
		if(selection instanceof IStructuredSelection) {

			Object obj = ((IStructuredSelection)selection).getFirstElement();

			Collection<RequirementSource> input = DataUtil.getRepositories(obj);

			if(!input.isEmpty()) {
				RequirementView.openRequirementView(input);
			}
		}
	}


}

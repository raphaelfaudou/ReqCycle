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

import org.agesys.inject.AgesysInject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.reqcycle.core.ILogger;

/**
 * Action to Synchronize a Requirement Resource
 */
public class SynchronizeRequirementSourceActionStub extends Action {

	@Inject ILogger logger = AgesysInject.make(ILogger.class);
	
	/** Requirement repositories TreeViewer */
	private TreeViewer viewer;

	/**
	 * Constructor
	 * 
	 * @param viewer
	 *        The requirement Resource Viewer
	 */
	public SynchronizeRequirementSourceActionStub(TreeViewer viewer) {
		this.viewer = viewer;
	}

	@Override
	public void run() {
		ISelection selection = viewer.getSelection();
		if(selection instanceof IStructuredSelection) {
			IStructuredSelection iSelection = (IStructuredSelection)selection;
			//TODO : Synch with the source using Map
		}
	}

}

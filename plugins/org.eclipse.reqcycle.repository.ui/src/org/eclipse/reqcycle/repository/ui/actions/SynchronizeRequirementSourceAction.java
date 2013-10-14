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
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.reqcycle.core.ILogger;
import org.eclipse.reqcycle.repository.data.util.IRequirementSourceProperties;
import org.eclipse.reqcycle.repository.data.util.SVNUtils;
import org.eclipse.swt.widgets.Display;
import org.eclipse.team.svn.core.connector.SVNRevision;
import org.eclipse.ziggurat.inject.ZigguratInject;

import RequirementSourceConf.RequirementSource;

/**
 * Action to Synchronize a Requirement Resource
 */
public class SynchronizeRequirementSourceAction extends Action {

	@Inject
	ILogger logger;

	/** Requirement repositories TreeViewer */
	private TreeViewer viewer;

	/**
	 * Constructor
	 * 
	 * @param viewer
	 *        The requirement Resource Viewer
	 */
	public SynchronizeRequirementSourceAction(TreeViewer viewer) {
		this.viewer = viewer;
		ZigguratInject.inject(this);
	}

	@Override
	public void run() {
		ISelection selection = viewer.getSelection();
		if(selection instanceof IStructuredSelection) {
			Object element = ((IStructuredSelection)selection).getFirstElement();
			if(element instanceof RequirementSource) {
				RequirementSource source = (RequirementSource)element;
				if(Boolean.parseBoolean(source.getProperty(IRequirementSourceProperties.IS_LOCAL))) {

					long[] result = null;

					try {
						result = SVNUtils.synchronizeSVNSource(source);
					} catch (Exception e) {
						//FIXME : Use logger
						e.printStackTrace();
					}
					if(result != null && result.length > 0 && result[0] != SVNRevision.INVALID_REVISION_NUMBER) {
						MessageDialog.openInformation(Display.getDefault().getActiveShell(), "Synchronize Requirement Source", "Synchronize finished without errors.");
					} else if(result != null && result.length == 0) {
						MessageDialog.openInformation(Display.getDefault().getActiveShell(), "Synchronize Requirement Source", "Nothing to Commit.");
					} else {
						MessageDialog.openError(Display.getDefault().getActiveShell(), "Synchronize Requirement Source", "Error while syncing the Requirement Source.");
					}
				}
			}
		}
	}

}

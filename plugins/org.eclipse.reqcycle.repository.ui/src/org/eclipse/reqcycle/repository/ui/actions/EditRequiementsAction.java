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

import java.net.URI;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.reqcycle.repository.data.editor.RequirementsEditor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PlatformUI;

import DataModel.RequirementSource;


/**
 * The Class EditRequiementSourceAction.
 */
public class EditRequiementsAction extends Action {

	//FIXME : Use manager or local connector to retrieve this ID
	public final static String LOCAL_CONNECTOR_ID = "org.eclipse.reqcycle.repository.connector.local.connectorCore";

	/** The viewer. */
	protected TreeViewer viewer;

	/**
	 * Instantiates a new edit action.
	 * 
	 * @param viewer
	 *        the viewer
	 */
	public EditRequiementsAction(TreeViewer viewer) {
		this.viewer = viewer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run() {
		IEditorReference[] editorReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
		for(IEditorReference iEditorReference : editorReferences) {
			if(RequirementsEditor.EDITOR_ID.equals(iEditorReference.getId())) {
				MessageDialog.openInformation(Display.getDefault().getActiveShell(), "Open Requiement Editor", "Please close the opened Requirements Editor before beginning a new edition.");
				return;
			}
		}
		ISelection selection = viewer.getSelection();
		if(selection instanceof IStructuredSelection) {
			Object firstElement = ((IStructuredSelection)selection).getFirstElement();
			if(firstElement instanceof RequirementSource) {
				RequirementSource requirementSource = (RequirementSource)firstElement;
				String connectorId = requirementSource.getConnectorId();
				if(LOCAL_CONNECTOR_ID.equals(connectorId)) {
					URI uri = URI.create(requirementSource.eResource().getURI().toString());
					RequirementsEditor.openEditor(requirementSource, uri);
				}

			}

		}

	}

}

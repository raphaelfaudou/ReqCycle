package org.eclipse.reqcycle.repository.connector.local;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.reqcycle.repository.connector.local.editor.CustomDataModelEditor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import DataModel.RequirementSource;


public class EditRequirementsHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		IEditorReference[] editorReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
		for(IEditorReference iEditorReference : editorReferences) {
			if(CustomDataModelEditor.EDITOR_ID.equals(iEditorReference.getId())) {
				MessageDialog.openInformation(Display.getDefault().getActiveShell(), "Open Requiement Editor", "Please close the opened Requirements Editor before beginning a new edition.");
				return null;
			}
		}

		ISelection selection = HandlerUtil.getCurrentSelection(event);
		if(selection instanceof IStructuredSelection) {
			Object firstElement = ((IStructuredSelection)selection).getFirstElement();
			if(firstElement instanceof RequirementSource) {
				RequirementSource requirementSource = (RequirementSource)firstElement;
				String connectorId = requirementSource.getConnectorId();
				if(LocalConnector.LOCAL_CONNECTOR_ID.equals(connectorId)) {
					Resource eResource = requirementSource.eResource();
					URI uri = eResource.getURI().appendFragment(eResource.getURIFragment(requirementSource));
					CustomDataModelEditor.openEditor(uri);
				} else {
					MessageDialog.openInformation(Display.getDefault().getActiveShell(), "Open Requiement Editor", "Can't Edit this Requirement Source. Only Local Requirement Source can be edited.");
					return null;
				}
			}
		}

		return null;
	}

}

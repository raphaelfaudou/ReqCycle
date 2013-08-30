package org.eclipse.reqcycle.repository.connector.local;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.CompoundContributionItem;

import DataModel.RequirementSource;


public class ReqCycleContributionItem extends CompoundContributionItem {

	@Override
	protected IContributionItem[] getContributionItems() {

		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if(window != null) {
			ISelection selection = window.getSelectionService().getSelection();
			if(selection instanceof IStructuredSelection) {
				Object firstElement = ((IStructuredSelection)selection).getFirstElement();

				if(firstElement instanceof RequirementSource) {
				}
			}
		}

		return null;
	}

}

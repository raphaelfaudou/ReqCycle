package org.eclipse.reqcycle.repository.connector.rmf.handlers;

import java.util.Collection;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.reqcycle.repository.connector.rmf.ui.SettingWizard;
import org.eclipse.reqcycle.repository.requirement.data.util.DataUtil;
import org.eclipse.rmf.reqif10.SpecType;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import DataModel.RequirementSource;
import DataModel.Scope;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SampleHandler2 extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public SampleHandler2() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		
		if(selection instanceof TreeSelection) {
			Object element = ((TreeSelection)selection).getFirstElement();
			if(element instanceof RequirementSource) {
				RequirementSource repo = (RequirementSource)element;
				
				ResourceSet resourceSet = new ResourceSetImpl();
				Collection<EClassifier> targetEClassifiers = DataUtil.getTargetEPackage(resourceSet, "org.eclipse.reqcycle.repository.data/model/CustomDataModel.ecore");;
				
				Collection<SpecType> specTypes = null;
				Collection<EObject> mapping = (Collection)repo.getMapping(); 
				String label = repo.getRepositoryLabel(); 
				String uri = repo.getRepositoryUri();
				Collection<Scope> scopes = null;
				
				SettingWizard settingWizard = new SettingWizard(targetEClassifiers, specTypes, mapping, label, uri, scopes);
				
				WizardDialog wd = new WizardDialog(window.getShell(), settingWizard);
				wd.open();
			}
		}
		return null;
	}
}

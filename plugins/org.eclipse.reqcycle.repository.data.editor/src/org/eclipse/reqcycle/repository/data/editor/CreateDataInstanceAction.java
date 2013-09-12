package org.eclipse.reqcycle.repository.data.editor;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.action.StaticSelectionCommandAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.reqcycle.repository.data.IRequirementCreator;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ziggurat.inject.ZigguratInject;

import DataModel.DataModelPackage;
import DataModel.RequirementSource;
import DataModel.Section;

public class CreateDataInstanceAction extends StaticSelectionCommandAction {

	private EClass eClass;
	private ISelection selection;
	IRequirementCreator reqCreator = ZigguratInject
			.make(IRequirementCreator.class);

	public CreateDataInstanceAction(IWorkbenchPart workbenchPart, EClass eClass, ISelection selection) {
		super(workbenchPart);
		this.eClass = eClass;
		this.selection = selection;
		setText(eClass.getName());
		configureAction(selection);
	}
	

	@Override
	protected Command createActionCommand(EditingDomain editingDomain,
			Collection<?> collection) {
		if(collection.size() != 1) {
			return UnexecutableCommand.INSTANCE;
		}
		
		Object object;
		Command cmd = null;
		Object owner = collection.iterator().next();
		if (selection instanceof IStructuredSelection) {
			object = ((IStructuredSelection) selection).getFirstElement();
			
//			EList<Contained> children = null;
			EReference feature = null;
			if (object instanceof RequirementSource) {
				feature = DataModelPackage.Literals.REQUIREMENT_SOURCE__REQUIREMENTS;
//				children = ((RequirementSource) object).getRequirements();
			} else if (object instanceof Section) {
				feature = DataModelPackage.Literals.SECTION__CHILDREN;
//				children = ((Section) object).getChildren();
			}
			try {
//				cmd = new AddCommand(editingDomain, children, reqCreator.addObject(eClass, "", "", ""));
				cmd = new AddCommand(editingDomain, (EObject)owner, feature, reqCreator.addObject(eClass, "", "", ""));
			} catch (Exception e) {
			} finally {
			}
		}
		
		return cmd != null ? cmd : UnexecutableCommand.INSTANCE;
	}

}

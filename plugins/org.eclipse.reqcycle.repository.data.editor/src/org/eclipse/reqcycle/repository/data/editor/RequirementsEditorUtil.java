package org.eclipse.reqcycle.repository.data.editor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.reqcycle.repository.data.IDataModelManager;
import org.eclipse.reqcycle.repository.data.types.IRequirementType;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ziggurat.inject.ZigguratInject;

import DataModel.DataModelPackage;
import DataModel.RequirementSource;
import DataModel.Section;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;

public class RequirementsEditorUtil {

	static IDataModelManager dataManager = ZigguratInject.make(IDataModelManager.class);

	public static Collection<IAction> generateCreateChildActions(IWorkbenchPart workbenchPart, ISelection object) {
		Collection<IAction> actions = new ArrayList<IAction>();
		if(object instanceof IStructuredSelection) {
			Set<EClass> dataEClasses = getDataEClasses(((IStructuredSelection)object).getFirstElement());
			for(EClass eClass : dataEClasses) {
				actions.add(new CreateDataInstanceAction(workbenchPart, eClass, object));
			}
		}
		return actions;
	}

	public static Set<EClass> getDataEClasses(Object selectedElement) {
		if(!(selectedElement instanceof Section || selectedElement instanceof RequirementSource)) {
			return Sets.newHashSet();
		}
		Set<EClass> classes = new HashSet<EClass>();
		Collection<IRequirementType> dataTypes = dataManager.getAllRequirementTypes();
		classes.addAll(Collections2.transform(dataTypes, new Function<IRequirementType, EClass>() {

			@Override
			public EClass apply(IRequirementType arg0) {
				if(arg0 instanceof IAdaptable) {
					return (EClass)((IAdaptable)arg0).getAdapter(EClass.class);
				}
				return null;
			}
		}));

		classes.add(DataModelPackage.Literals.SECTION);
		classes.add(DataModelPackage.Literals.REQUIREMENT_SECTION);
		return classes;
	}

}

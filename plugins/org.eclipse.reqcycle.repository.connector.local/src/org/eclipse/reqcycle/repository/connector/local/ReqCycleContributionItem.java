package org.eclipse.reqcycle.repository.connector.local;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.reqcycle.repository.data.IDataModelManager;
import org.eclipse.reqcycle.repository.data.IRequirementCreator;
import org.eclipse.reqcycle.repository.data.IRequirementSourceManager;
import org.eclipse.reqcycle.repository.data.types.RequirementType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.ziggurat.inject.ZigguratInject;

import DataModel.Contained;
import DataModel.DataModelPackage;
import DataModel.RequirementSource;
import DataModel.Section;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;


public class ReqCycleContributionItem extends CompoundContributionItem {

	IRequirementCreator reqCreator = ZigguratInject.make(IRequirementCreator.class);

	IDataModelManager dataManager = ZigguratInject.make(IDataModelManager.class);
	
	IRequirementSourceManager reqManager = ZigguratInject.make(IRequirementSourceManager.class);

	protected EObject selectedElement;

	@Override
	protected IContributionItem[] getContributionItems() {

		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		Set<EClass> classes = new HashSet<EClass>();
		if(window != null) {
			ISelection selection = window.getSelectionService().getSelection();
			if(selection instanceof IStructuredSelection) {
				Object firstElement = ((IStructuredSelection)selection).getFirstElement();
				selectedElement = null;
				if(firstElement instanceof RequirementSource || firstElement instanceof Section) {
					selectedElement = (EObject)firstElement;
				}
				Collection<RequirementType> dataTypes = dataManager.getAllDataTypes();
				classes.addAll(Collections2.transform(dataTypes, new Function<RequirementType, EClass>() {
					
					@Override
					public EClass apply(RequirementType arg0) {
						if(arg0 instanceof IAdaptable) {
							return (EClass)((IAdaptable)arg0).getAdapter(EClass.class);
						}
						return null;
					}
				}));
				
				classes.add(DataModelPackage.Literals.SECTION);
				classes.add(DataModelPackage.Literals.REQUIREMENT_SECTION);
			}
		}

		Collection<IContributionItem> menuContributionList = Collections2.transform(classes, new Function<EClass, IContributionItem>() {

			@Override
			public IContributionItem apply(final EClass arg0) {
				ContributionItem contributionItem = new ContributionItem() {

					@Override
					public void fill(Menu menu, int index) {
						// TODO Auto-generated method stub
						MenuItem menuItem = new MenuItem(menu, SWT.None);
						menuItem.setText(arg0.getName());
						menuItem.addSelectionListener(new SelectionAdapter() {

							@Override
							public void widgetSelected(SelectionEvent e) {
								
								try {
									
									Contained element = reqCreator.addObject(arg0, "toto", "toto", "toto");
									
									
									if(selectedElement instanceof RequirementSource) {
										((RequirementSource)selectedElement).getRequirements().add(element);
									}
									
									if(selectedElement instanceof Section) {
										((Section)selectedElement).getChildren().add(element);
									}
									reqManager.save();
									
								} catch (Exception e1) {
									e1.printStackTrace();
								}
								
							}
						});
					}
				};
				return contributionItem;
			}
		});

		IContributionItem[] array = new IContributionItem[menuContributionList.size()];
		return menuContributionList.toArray(array);
	}

}

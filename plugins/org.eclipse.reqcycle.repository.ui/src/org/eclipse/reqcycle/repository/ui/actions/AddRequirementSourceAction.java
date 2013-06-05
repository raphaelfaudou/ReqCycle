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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.reqcycle.repository.connector.ui.wizard.NewRequirementSourceWizard;
import org.eclipse.reqcycle.repository.requirement.data.IRequirementSourceManager;
import org.eclipse.reqcycle.repository.requirement.data.IScopeManager;
import org.eclipse.reqcycle.repository.requirement.data.util.DataUtil;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ziggurat.inject.ZigguratInject;

import DataModel.Contained;
import DataModel.Requirement;
import DataModel.RequirementSection;
import DataModel.RequirementSource;
import MappingModel.AttributeMapping;
import MappingModel.ElementMapping;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

/**
 * Action to create a new requirement source
 */
public class AddRequirementSourceAction extends Action {

	/** Requirement repositories TreeViewer */
	private TreeViewer viewer;

	private @Inject
	IRequirementSourceManager requirementSourceManager = ZigguratInject.make(IRequirementSourceManager.class);

	private @Inject
	IScopeManager scopeManager = ZigguratInject.make(IScopeManager.class);


	/**
	 * Constructor
	 * 
	 * @param viewer
	 *        The Requirement Resource Viewer
	 */
	public AddRequirementSourceAction(TreeViewer viewer) {
		this.viewer = viewer;
	}

	@Override
	public void run() {
		NewRequirementSourceWizard wizard = new NewRequirementSourceWizard();

		//TODO : Create a custom wizard dialog
		Shell shell = new Shell();
		WizardDialog wd = new WizardDialog(shell, wizard);
		
		wd.setHelpAvailable(false);
		
		if(wd.open() == Window.OK) {
			
			//TODO : remove (Test)
			RequirementSource repository = wizard.getRequirementSource();
			EList<ElementMapping> mapping = repository.getMapping();
			ResourceSet rs = new ResourceSetImpl();
			for(ElementMapping elementMapping : mapping) {
				rs.getResources().add(elementMapping.getTargetElement().eResource());
				for(AttributeMapping attributeMapping : elementMapping.getAttributes()) {
					rs.getResources().add(attributeMapping.getTargetAttribute().eResource());
				}
			}
			
			requirementSourceManager.addRepository(repository, rs);
			
			Collection<Contained> containedElements = DataUtil.getAllContainedElements(repository.getRequirements()); 
			
			Collection<Contained> requirements = Collections2.filter(containedElements, new Predicate<Contained>() {
				@Override
				public boolean apply(Contained arg0) {
					if(arg0 instanceof Requirement || arg0 instanceof RequirementSection) {
						return true;
					}
					return false;
				}
			});
			scopeManager.addToScope(wizard.getScope(), requirements);
			if(viewer != null) {
				viewer.refresh();
			}
		}
		if(shell != null && !shell.isDisposed()) {
			shell.dispose();
		}
	}
	
}

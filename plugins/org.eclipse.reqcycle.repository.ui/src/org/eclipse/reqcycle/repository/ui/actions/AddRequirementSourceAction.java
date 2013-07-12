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
import java.util.concurrent.Callable;

import javax.inject.Inject;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.reqcycle.core.ILogger;
import org.eclipse.reqcycle.repository.connector.ConnectorDescriptor;
import org.eclipse.reqcycle.repository.connector.ui.Activator;
import org.eclipse.reqcycle.repository.requirement.data.IRequirementSourceManager;
import org.eclipse.reqcycle.repository.requirement.data.IScopeManager;
import org.eclipse.reqcycle.repository.requirement.data.util.DataUtil;
import org.eclipse.reqcycle.repository.ui.wizard.NewRequirementSourceWizard;
import org.eclipse.swt.widgets.Display;
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
	IRequirementSourceManager requirementSourceManager;

	private @Inject
	IScopeManager scopeManager;

	private @Inject
	ILogger logger;


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
		ZigguratInject.inject(wizard);
		
		Shell shell = Display.getDefault().getActiveShell();
		
		//TODO : Create a custom wizard dialog
		WizardDialog wd = new WizardDialog(shell, wizard);

		wd.setHelpAvailable(false);

		if(wd.open() == Window.OK) {
			Callable<RequirementSource> createRequirementSource = wizard.getResult();
			if(createRequirementSource == null) {
				logger.error("Could not create the requirement repository");
				return;
			}
			RequirementSource source;
			try {
				source = createRequirementSource.call();
				
				ConnectorDescriptor connector = wizard.getConnectorDescriptor();
				source.setConnectorID(connector.getId());
				
				String sourceName = wizard.getSourceName();
				source.setName(sourceName);
				
				//TODO : solve scope problems (scope isn't stored if the mapping has been skipped)
				String scopeName = wizard.getScope().eClass().getName();
				source.setProperty("SCOPE_NAME", scopeName);

				EList<ElementMapping> mapping = source.getMapping();
				
				ResourceSet rs = new ResourceSetImpl();
				for(ElementMapping elementMapping : mapping) {
					rs.getResources().add(elementMapping.getTargetElement().eResource());
					for(AttributeMapping attributeMapping : elementMapping.getAttributes()) {
						rs.getResources().add(attributeMapping.getTargetAttribute().eResource());
					}
				}
				requirementSourceManager.addRepository(source, rs);
				Collection<Contained> containedElements = DataUtil.getAllContainedElements(source.getRequirements());
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
			} catch (CoreException e) {
				logger.log(e.getStatus());
			} catch (Exception e) {
				e.printStackTrace();
				logger.log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Could not create the requirement repository", e));
			}

		}
	}
}

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
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.reqcycle.core.ILogger;
import org.eclipse.reqcycle.repository.connector.ConnectorDescriptor;
import org.eclipse.reqcycle.repository.connector.IConnector;
import org.eclipse.reqcycle.repository.connector.IConnectorManager;
import org.eclipse.reqcycle.repository.connector.ui.Activator;
import org.eclipse.reqcycle.repository.connector.ui.wizard.IConnectorWizard;
import org.eclipse.reqcycle.repository.requirement.data.IRequirementSourceManager;
import org.eclipse.reqcycle.repository.requirement.data.IScopeManager;
import org.eclipse.reqcycle.repository.requirement.data.util.DataUtil;
import org.eclipse.swt.widgets.Display;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

import DataModel.Contained;
import DataModel.Requirement;
import DataModel.RequirementSection;
import DataModel.RequirementSource;
import DataModel.Scope;
import MappingModel.AttributeMapping;
import MappingModel.ElementMapping;

/**
 * Action to change the requirementSourceMapping mapping
 */
public class EditRequirementSourceAction extends Action {

	@Inject
	private IConnectorManager connectorManager;
	
	@Inject
	private IScopeManager scopeManager;

	@Inject
	private ILogger logger;
	
	@Inject
	private IRequirementSourceManager requirementSourceManager;

	private IConnector connector;

	private TreeViewer viewer;

	public EditRequirementSourceAction(TreeViewer viewer) {
		this.viewer = viewer;
	}

	@Override
	public void run() {
		ISelection selection = viewer.getSelection();
		if(selection instanceof IStructuredSelection) {
			Object element = ((IStructuredSelection)selection).getFirstElement();
			if(element instanceof RequirementSource) {
				try {
					RequirementSource requirementSource = (RequirementSource)element;
					String connectorID = requirementSource.getConnectorID();
					ConnectorDescriptor connectorDescriptor = connectorManager.get(connectorID);

					this.connector = connectorDescriptor.createConnector();
					this.connector.initializeWithRequirementSource(requirementSource);
					Callable<RequirementSource> callable = null;
					if(connector instanceof IConnectorWizard) {
						WizardDialog wd = new WizardDialog(Display.getDefault().getActiveShell(), (IConnectorWizard) connector);
						wd.setHelpAvailable(false);
						if(wd.open() == Window.OK) {
							callable = this.connector.createRequirementSource();
						}
					} else {
						callable = this.connector.createRequirementSource();
					}
					if (callable != null){
						callable.call();
						
						
						EList<ElementMapping> mapping = requirementSource.getMapping();
						
						ResourceSet rs = new ResourceSetImpl();
						for(ElementMapping elementMapping : mapping) {
							rs.getResources().add(elementMapping.getTargetElement().eResource());
							for(AttributeMapping attributeMapping : elementMapping.getAttributes()) {
								rs.getResources().add(attributeMapping.getTargetAttribute().eResource());
							}
						}
						requirementSourceManager.addRepository(requirementSource, rs);
						
						
						//TODO : solve scope problems (scope isn't stored if the mapping has been skipped)
						String scopeName = requirementSource.getProperty("SCOPE_NAME");
						Scope scope = getScope(scopeName);
						
						Collection<Contained> containedElements = DataUtil.getAllContainedElements(requirementSource.getRequirements());
						Collection<Contained> requirements = Collections2.filter(containedElements, new Predicate<Contained>() {
							
							@Override
							public boolean apply(Contained arg0) {
								if(arg0 instanceof Requirement || arg0 instanceof RequirementSection) {
									return true;
								}
								return false;
							}
						});
						
						
						scopeManager.addToScope(scope, requirements);
						
					}
				} catch (CoreException e) {
					logger.log(e.getStatus());
				} catch (Exception e) {
					logger.log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Could not modify the requirement source"));
				}

			}
		}
	}

	private Scope getScope(String scopeName) {
		if(scopeName == null) {
			return null;
		}
		
		for(Scope scope : scopeManager.getAllScopes()) {
			if(scopeName.equalsIgnoreCase(scope.eClass().getName())) {
				return scope;
			}
		}
		
		return null;
	}

}

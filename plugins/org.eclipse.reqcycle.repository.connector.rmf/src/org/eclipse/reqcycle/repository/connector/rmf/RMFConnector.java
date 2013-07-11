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
package org.eclipse.reqcycle.repository.connector.rmf;

import java.util.Collection;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.reqcycle.core.ILogger;
import org.eclipse.reqcycle.repository.connector.rmf.ui.RMFRepositoryMappingPage;
import org.eclipse.reqcycle.repository.connector.rmf.ui.RMFSettingPage;
import org.eclipse.reqcycle.repository.connector.ui.wizard.IConnectorWizard;
import org.eclipse.reqcycle.repository.requirement.data.IRequirementSourceManager;
import org.eclipse.reqcycle.repository.requirement.data.IScopeManager;
import org.eclipse.reqcycle.repository.requirement.data.util.DataUtil;
import org.eclipse.reqcycle.repository.requirement.data.util.RepositoryConstants;
import org.eclipse.rmf.reqif10.SpecType;
import org.eclipse.swt.widgets.Shell;

import DataModel.Contained;
import DataModel.DataModelFactory;
import DataModel.Requirement;
import DataModel.RequirementSection;
import DataModel.RequirementSource;
import DataModel.Scope;
import MappingModel.ElementMapping;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;


public class RMFConnector extends Wizard implements IConnectorWizard {

	@Inject
	private static ILogger logger;

	@Inject
	private static IRequirementSourceManager requirementSourceManager;

	@Inject
	private static IScopeManager scopeManager;


	/** Skip mapping page */
	private boolean skipMapping;

	/** Page containing mapping information */
	private RMFRepositoryMappingPage mappingPage;

	/** Page containing the ReqIF file and skip mapping check box */
	private RMFSettingPage settingPage;

	private Collection mapping;

	private String uri;

	public RMFConnector() {
	}

	public Callable<RequirementSource> createRequirementSource() {

		return new Callable<RequirementSource>() {

			@Override
			public RequirementSource call() throws Exception {
				RequirementSource requirementSource = DataModelFactory.eINSTANCE.createRequirementSource();
				requirementSource.setProperty(RepositoryConstants.PROPERTY_URL, uri);
				if(!skipMapping && mapping != null && !mapping.isEmpty()) {
					requirementSource.getMapping().addAll(mapping);
					RMFUtils.fillRequirements(requirementSource, new NullProgressMonitor());
				}
				return requirementSource;
			}
		};
	}


	public void editMapping(final RequirementSource requirementSource) {

		ResourceSet resourceSet = new ResourceSetImpl();
		Collection<EClassifier> targetEPackage = DataUtil.getTargetEPackage(resourceSet, "org.eclipse.reqcycle.repository.data/model/CustomDataModel.ecore");

		EList<SpecType> specTypes = RMFUtils.getReqIFTypes(resourceSet, requirementSource.getRepositoryUri());

		EList<ElementMapping> mapping = requirementSource.getMapping();

		final RMFRepositoryMappingPage mappingPage = createMappingPage(specTypes, targetEPackage, mapping);

		Wizard wizard = new Wizard() {

			@Override
			public void addPages() {
				addPage(mappingPage);
			}

			@Override
			public boolean performFinish() {
				//TODO :  refactor duplicate code (addding reqs to scope) add scope selection in the edit mapping wizard
				Collection<EObject> mapping = mappingPage.getResult();
				requirementSource.getMapping().clear();
				requirementSource.getMapping().addAll((Collection<? extends ElementMapping>)mapping);
				Scope scope = null;
				requirementSourceManager.removeRequirements(requirementSource);
				RMFUtils.fillRequirements(requirementSource, new NullProgressMonitor());

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
				for(Contained reqs : requirements) {
					if(reqs.getScopes().size() > 0) {
						scopeManager.addToScope(reqs.getScopes().get(0), requirements);
						break;
					}
				}

				return true;
			}
		};
		Shell shell = new Shell();
		WizardDialog wd = new WizardDialog(shell, wizard);
		wd.open();
		if(shell != null && !shell.isDisposed()) {
			shell.dispose();
		}
	}


	/*
	 * 
	 * 
	 * Wizard Code
	 * 
	 * 
	 */
	
	
	

	@Override
	public void addPages() {
		settingPage = new RMFSettingPage("ReqIF Setting", "");
		addPage(settingPage);
	}


	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if(page instanceof RMFSettingPage) {

			RMFSettingPage rmfSettingPage = ((RMFSettingPage)page);
			uri = rmfSettingPage.getSourceURI();
			skipMapping = rmfSettingPage.skipMapping();

			ResourceSet rs = new ResourceSetImpl();
			final EList<SpecType> specTypes = RMFUtils.getReqIFTypes(rs, uri);
			final Collection<EClassifier> EClassifiers = DataUtil.getTargetEPackage(rs, "org.eclipse.reqcycle.repository.data/model/CustomDataModel.ecore");

			mappingPage = createMappingPage(specTypes, EClassifiers, mapping);
			mappingPage.setWizard(this);

			return mappingPage;
		}

		return super.getNextPage(page);
	}

	private RMFRepositoryMappingPage createMappingPage(final EList<SpecType> specTypes, final Collection<EClassifier> EClassifiers, final Collection mapping) {
		return new RMFRepositoryMappingPage("ReqIF Mapping", "") {

			@Override
			protected Object getTargetInput() {
				return EClassifiers;
			}

			@Override
			protected String getTargetDetail() {
				return "ReqIF";
			}

			@Override
			protected IBaseLabelProvider getSourceLabelProvider() {
				return new LabelProvider() {

					@Override
					public String getText(Object element) {
						if(element instanceof SpecType) {
							return ((SpecType)element).getLongName();
						}
						return super.getText(element);
					}
				};
			}

			@Override
			protected Object getSourceInput() {
				return specTypes;
			}

			@Override
			protected String getSourceDetail() {
				return "ReqIF";
			}

			@Override
			protected IContentProvider getSourceContentProvider() {
				return RMFUtils.contentProvider;
			}

			@Override
			protected String getResultDetail() {
				return null;
			}

			@Override
			protected Collection<EObject> addToMapping() {
				return mapping;
			}
		};
	}



	@Override
	public boolean performFinish() {
		
		if(!skipMapping && mappingPage != null) {
			mapping = mappingPage.getResult();
		}
		
		return true;
	}


	@Override
	public boolean canFinish() {
		if(settingPage != null && settingPage.isPageComplete()) {
			if(settingPage.skipMapping()) {
				return true;
			} else {
				return mappingPage != null && mappingPage.isPageComplete();
			}
		}
		return false;
	}

	@Override
	public void initializeWithRequirementSource(RequirementSource requirementSource) {
		if(requirementSource == null) {
			return;
		}

		if(requirementSource.getRepositoryUri() != null && !requirementSource.getRepositoryUri().isEmpty()) {
			uri = requirementSource.getRepositoryUri();
		}

		mapping = requirementSource.getMapping();

	}


}

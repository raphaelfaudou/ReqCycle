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
 *  Anass RADOUANI (AtoS) anass.radouani@gmail.com - Initial API and implementation
 *
  *****************************************************************************/

/**
 * 
 */
package org.eclipse.reqcycle.repository.connector.rmf.ui;

import java.util.Collection;

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
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.reqcycle.core.ILogger;
import org.eclipse.reqcycle.repository.connector.rmf.Activator;
import org.eclipse.reqcycle.repository.connector.rmf.RMFConnector;
import org.eclipse.reqcycle.repository.connector.rmf.RMFUtils;
import org.eclipse.reqcycle.repository.requirement.data.IRequirementSourceManager;
import org.eclipse.reqcycle.repository.requirement.data.util.DataUtil;
import org.eclipse.reqcycle.repository.requirement.data.util.RepositoryConstants;
import org.eclipse.rmf.reqif10.SpecType;
import org.eclipse.ziggurat.inject.ZigguratInject;

import DataModel.RequirementSource;
import DataModel.Scope;
import MappingModel.ElementMapping;


public class SettingWizard extends Wizard implements IWizard {

	
	private Collection<EClassifier> targetEClassifiers;
	private Collection<SpecType> specTypes;
	private Collection<EObject> mapping;
	private String label;
	private String uri;
	private Collection<Scope> scopes;

	private RMFSettingPage settingPage;
	private RMFRepositoryMappingPage mappingPage;
	private Scope scope;
	
	private RMFConnector connector = new RMFConnector();
	
	private @Inject ILogger logger = ZigguratInject.make(ILogger.class);
	private @Inject IRequirementSourceManager sourceManager = ZigguratInject.make(IRequirementSourceManager.class);
	private boolean skipMapping;
	private RequirementSource requirementSource;
	

	public SettingWizard(Collection<EClassifier> targetEClassifiers, Collection<SpecType> specTypes, Collection<EObject> mapping, String label, String uri, Collection<Scope> scopes) {
		super();
		this.targetEClassifiers = targetEClassifiers;
		this.specTypes = specTypes; 
		this.mapping = mapping;
		this.label = label;
		this.uri = uri;
		this.scopes = scopes;
		
		settingPage = new RMFSettingPage("ReqIF Setting", "", uri);
		settingPage.setWizard(this);
		setForcePreviousAndNextButtons(true);
	}
	
	public SettingWizard() {
		super();
		settingPage = new RMFSettingPage("ReqIF Setting", "");
		setForcePreviousAndNextButtons(true);
	}
	
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if(page == settingPage) {
			uri = settingPage.getSourceURI();
			skipMapping = settingPage.skipMapping();
			
			//TODO : check mapping input information, if they are unchanged and mappingPage != null => return mappingPage
			ResourceSet resourceSet = new ResourceSetImpl();
			final EList<SpecType> specTypes = RMFUtils.getReqIFTypes(resourceSet, uri);
			final Collection<EClassifier> EClassifiers = DataUtil.getTargetEPackage(resourceSet, "org.eclipse.reqcycle.repository.data/model/CustomDataModel.ecore");
			mappingPage = new RMFRepositoryMappingPage("ReqIF Mapping", "") {

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
			mappingPage.setWizard(this);
			return mappingPage;
		}
		return super.getNextPage(page);
	}
	
	@Override
	public void addPages() {
		addPage(settingPage);
	}
	
	@Override
	public boolean performFinish() {
		if(settingPage == null || mappingPage == null) {
			return false;
		}
		try {
			requirementSource = connector.createRequirementSource().call();
			requirementSource.setProperty(RepositoryConstants.PROPERTY_URL, uri);
			requirementSource.setProperty(RepositoryConstants.PROPERTY_LABEL, label);
			sourceManager.addRepository(requirementSource, null);

			if(!settingPage.skipMapping()) {
				requirementSource.getMapping().addAll((Collection<? extends ElementMapping>)mappingPage.getResult());
				RMFUtils.fillRequirements(requirementSource, new NullProgressMonitor());
			}
			
		} catch (Exception e) {
			boolean debug = logger.isDebug(Activator.OPTIONS_DEBUG, Activator.getDefault());
			if(debug) {
				logger.trace("Properties " + RepositoryConstants.PROPERTY_URL + " and " + RepositoryConstants.PROPERTY_LABEL + " can't be set on " + requirementSource.getRepositoryLabel() + "\n Trace : " + e.getMessage());
			}
		}
		return true;
	}
	
	@Override
	public boolean canFinish() {
		if(settingPage != null && settingPage.isPageComplete()) {
			if(settingPage.skipMapping()) {
				return true;
			}
			else
			{
				return mappingPage != null && mappingPage.isPageComplete();
			}
		}
		return false;
	}

	
	public String getLabel() {
		return label;
	}

	
	public String getUri() {
		return uri;
	}

	
	public boolean isSkipMapping() {
		return skipMapping;
	}
}

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
import org.eclipse.reqcycle.repository.connector.rmf.RMFConnectorUi;
import org.eclipse.reqcycle.repository.requirement.data.util.DataUtil;
import org.eclipse.rmf.reqif10.SpecType;

import DataModel.Scope;


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
	

	public SettingWizard(Collection<EClassifier> targetEClassifiers, Collection<SpecType> specTypes, Collection<EObject> mapping, String label, String uri, Collection<Scope> scopes) {
		super();
		this.targetEClassifiers = targetEClassifiers;
		this.specTypes = specTypes; 
		this.mapping = mapping;
		this.label = label;
		this.uri = uri;
		this.scopes = scopes;
		
		settingPage = new RMFSettingPage("RMF Setting", "", label, uri, scopes);
		settingPage.setWizard(this);
		setForcePreviousAndNextButtons(true);
	}
	
	public SettingWizard() {
		super();
		settingPage = new RMFSettingPage("RMF Setting", "");
		setForcePreviousAndNextButtons(true);
	}
	
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if(page == settingPage) {
			scope = settingPage.getScope();
			label = settingPage.getRequirementSourceName();
			uri = settingPage.getSourceUrl();
			
			ResourceSet resourceSet = new ResourceSetImpl();
			final EList<SpecType> specTypes = RMFConnectorUi.getReqIFTypes(resourceSet, uri);
			
			final Collection<EClassifier> EClassifiers = DataUtil.getTargetEPackage(resourceSet, "org.eclipse.reqcycle.repository.data/model/CustomDataModel.ecore");
			
			//TODO : Create RMFRepositoryMappingPage
			mappingPage = new RMFRepositoryMappingPage("RMF Mapping", "") {
				
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
					return new LabelProvider(){
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
					return RMFConnectorUi.contentProvider;
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
		return true;
	}
	
	

}

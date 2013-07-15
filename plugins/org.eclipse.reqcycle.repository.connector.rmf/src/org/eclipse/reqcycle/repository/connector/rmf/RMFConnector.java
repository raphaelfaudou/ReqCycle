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
import org.eclipse.reqcycle.repository.connector.rmf.ui.RMFRepositoryMappingPage;
import org.eclipse.reqcycle.repository.connector.rmf.ui.RMFSettingPage;
import org.eclipse.reqcycle.repository.connector.rmf.ui.RMFSettingPage.RMFSettingPageBean;
import org.eclipse.reqcycle.repository.connector.ui.wizard.IConnectorWizard;
import org.eclipse.reqcycle.repository.requirement.data.util.DataUtil;
import org.eclipse.reqcycle.repository.requirement.data.util.RepositoryConstants;
import org.eclipse.rmf.reqif10.SpecType;

import DataModel.DataModelFactory;
import DataModel.RequirementSource;
import MappingModel.ElementMapping;


public class RMFConnector extends Wizard implements IConnectorWizard {

	/** Page containing mapping information */
	private RMFRepositoryMappingPage mappingPage;

	/** Page containing the ReqIF file and skip mapping check box */
	private RMFSettingPage settingPage;

	private Collection mapping;

	private RequirementSource initSource;

	private RMFSettingPageBean settingPageBean;

	private boolean edition = false;

	public RMFConnector() {
	}

	public Callable<RequirementSource> createRequirementSource() {

		return new Callable<RequirementSource>() {

			@Override
			public RequirementSource call() throws Exception {

				RequirementSource requirementSource;

				if(edition) {
					requirementSource = initSource;
				} else {
					requirementSource = DataModelFactory.eINSTANCE.createRequirementSource();
					if(settingPageBean != null && settingPageBean.getUri() != null && !settingPageBean.getUri().isEmpty()) {
						requirementSource.setProperty(RepositoryConstants.PROPERTY_URL, settingPageBean.getUri());
					}
				}

				if(((settingPageBean != null && !settingPageBean.skipMapping()) || edition) && mapping != null && !mapping.isEmpty()) {
					//it's a creation without skip mapping or an edition
					requirementSource.getMapping().clear();
					requirementSource.getMapping().addAll(mapping);
					requirementSource.getRequirements().clear();
					RMFUtils.fillRequirements(requirementSource, new NullProgressMonitor());
				}

				return requirementSource;
			}
		};
	}

	@Override
	public void addPages() {
		// if the wizard is initialized with a requirement source (requirement source edition)
		// then the mapping page is added instead of settingPage
		if(edition) {
			ResourceSet rs = new ResourceSetImpl();
			EList<SpecType> specTypes = RMFUtils.getReqIFTypes(rs, initSource.getRepositoryUri());
			Collection<EClassifier> EClassifiers = DataUtil.getTargetEPackage(rs, "org.eclipse.reqcycle.repository.data/model/CustomDataModel.ecore");
			EList<ElementMapping> mapping = initSource.getMapping();
			mappingPage = createMappingPage(specTypes, EClassifiers, mapping);
			addPage(mappingPage);
		} else {
			settingPageBean = new RMFSettingPageBean();
			settingPage = new RMFSettingPage("ReqIF Setting", "", settingPageBean);
			addPage(settingPage);
		}
	}


	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if(page instanceof RMFSettingPage) {
			ResourceSet rs = new ResourceSetImpl();
			final EList<SpecType> specTypes = RMFUtils.getReqIFTypes(rs, settingPageBean.getUri());
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
		//TODO : use mapping bean
		if(((settingPageBean != null && !settingPageBean.skipMapping()) || edition) && mappingPage != null) {
			mapping = mappingPage.getResult();
		}

		return true;
	}


	@Override
	public boolean canFinish() {
		//TODO : refactor using beans
		if(settingPage != null && settingPage.isPageComplete() && settingPageBean != null) {
			if(settingPageBean.skipMapping()) {
				return true;
			} else {
				return mappingPage != null && mappingPage.isPageComplete();
			}
		} else if(initSource != null) {
			return mappingPage != null && mappingPage.isPageComplete();
		}
		return false;
	}

	@Override
	public void initializeWithRequirementSource(RequirementSource requirementSource) {
		initSource = requirementSource;
		edition = true;
	}
}

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

import javax.inject.Inject;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.reqcycle.core.ILogger;
import org.eclipse.reqcycle.repository.connector.rmf.ui.RMFRepositoryMappingPage;
import org.eclipse.reqcycle.repository.connector.rmf.ui.RMFSettingPage;
import org.eclipse.reqcycle.repository.connector.ui.wizard.IConnectorWizard;
import org.eclipse.reqcycle.repository.connector.ui.wizard.IRequirementSourceSettingPage;
import org.eclipse.reqcycle.repository.requirement.data.util.DataUtil;
import org.eclipse.reqcycle.repository.requirement.data.util.RepositoryConstants;
import org.eclipse.rmf.reqif10.ReqIF;
import org.eclipse.rmf.reqif10.ReqIFContent;
import org.eclipse.rmf.reqif10.SpecType;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ziggurat.inject.ZigguratInject;

import DataModel.RequirementSource;
import DataModel.Scope;
import MappingModel.ElementMapping;


public class RMFConnectorUi implements IConnectorWizard {

	/** ReqIf file uri */
	private String reqIfFile;

	private RMFSettingPage selectDocumentsPage;

	private RMFRepositoryMappingPage rmfMappingPage;
	
	@Inject ILogger logger = ZigguratInject.make(ILogger.class);
	
	private Scope scope;

	private String repositoryLabel;
	
	public static ITreeContentProvider contentProvider =  new ITreeContentProvider() {
		
		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
		
		@Override
		public void dispose() {
		}
		
		@Override
		public boolean hasChildren(Object element) {
			return false;
		}
		
		@Override
		public Object getParent(Object element) {
			return null;
		}
		
		@Override
		public Object[] getElements(Object inputElement) {
			return ArrayContentProvider.getInstance().getElements(inputElement);
		}
		
		@Override
		public Object[] getChildren(Object parentElement) {
			return null;
		}
	};
	
	final static LabelProvider targetLabelProvider = new LabelProvider() {
		@Override
		public String getText(Object element) {
			if(element instanceof EClass) {
				return ((EClass)element).getName();
			}
			return super.getText(element);
		}
	};

	@Override
	public String getConnectorId() {
		return "ReqIF";
	}

	@Override
	public IRequirementSourceSettingPage getNextPage(IRequirementSourceSettingPage page) {

		if(page == null) {
			rmfMappingPage = null;
			selectDocumentsPage = new RMFSettingPage("ReqIF Connector", "");
			return selectDocumentsPage;
		}

		if(page instanceof RMFSettingPage) {

			ResourceSet resourceSet = new ResourceSetImpl();

			repositoryLabel = ((RMFSettingPage) page).getRequirementSourceName();
			
			reqIfFile = ((RMFSettingPage)page).getSourceUrl();
			EList<SpecType> specTypes = getReqIFTypes(resourceSet, reqIfFile);
			
			scope = ((RMFSettingPage)page).getScope();

			Collection<EClassifier> targetEClassifiers = DataUtil.getTargetEPackage(resourceSet, "org.eclipse.reqcycle.repository.data/model/CustomDataModel.ecore");

			rmfMappingPage = getMappingPage(specTypes, targetEClassifiers, null);

			return rmfMappingPage;
		}
		return null;
	}


	/**
	 * @param specTypes
	 * @param targetEClassifiers
	 * @param targetLabelProvider
	 * @return
	 */
	protected static RMFRepositoryMappingPage getMappingPage(final EList<SpecType> specTypes, final Collection<EClassifier> targetEClassifiers, final Collection<EObject> mapping) {
		RMFRepositoryMappingPage mappingPage = new RMFRepositoryMappingPage("ReqIF Mapping Page", ""){

			@Override
			protected String getSourceDetail() {
				return "ReqIF";
			}

			@Override
			protected String getTargetDetail() {
				return "Data Type";
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
			protected IContentProvider getSourceContentProvider() {
				return RMFConnectorUi.contentProvider;
			}

			@Override
			protected Object getSourceInput() {
				return specTypes;
			}

			@Override
			protected Object getTargetInput() {
				// TODO Auto-generated method stub
				return targetEClassifiers;
			}

			@Override
			protected String getResultDetail() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			protected Collection<EObject> addToMapping() {
				return mapping;
			}
			
		};
		
		return mappingPage;
	}

	//TODO : move to an rmf util class
	public static EList<SpecType> getReqIFTypes(ResourceSet resourceSet, String fileLocation) {
		URI uriReqIf = URI.createURI(fileLocation, false);
		Resource reqIfResource = resourceSet.getResource(uriReqIf, true);
		EList<EObject> contents = reqIfResource.getContents();
		if(contents.size() > 0) {
			EObject content = contents.get(0);
			if(content instanceof ReqIF) {
				ReqIFContent coreContent = ((ReqIF)content).getCoreContent();
				return coreContent.getSpecTypes();
			}
		}

		return null;
	}

	@Override
	public void performFinish(RequirementSource repository) {
		try {
			repository.setProperty(RepositoryConstants.PROPERTY_URL, reqIfFile);
			repository.setProperty(RepositoryConstants.PROPERTY_LABEL, repositoryLabel);
			repository.getMapping().addAll((Collection<? extends ElementMapping>)rmfMappingPage.getResult());
		} catch (Exception e) {
			boolean debug = logger.isDebug(Activator.OPTIONS_DEBUG, Activator.getDefault());
			if(debug) {
				logger.trace("Properties " + RepositoryConstants.PROPERTY_URL + " and " + RepositoryConstants.PROPERTY_LABEL + " can't be set on " + repository.getRepositoryLabel() + "\n Trace : " + e.getMessage());
			}
		}
	}

	@Override
	public boolean preFinish() {
		boolean result = true;
		Collection<EObject> mapping = rmfMappingPage.getResult();
		for(EObject eObject : mapping) {
			if(eObject instanceof ElementMapping) {
				EList<EClass> superTypes = ((ElementMapping)eObject).getTargetElement().getESuperTypes();
				if (!isSectionSuperType(superTypes))
				{
					Shell shell = new Shell();
					boolean dialog = MessageDialog.openQuestion(shell, "Type WARNING", "One or more mapping element doesn't inherit from Reachable Section Type. Its children will be ignored. Would you continue?");
					shell.dispose();
					if (!dialog) {
						result = false;
					}
					break;
				}
			}
		}
		return result;
	}

	public boolean isSectionSuperType(Collection<EClass> superTypes){
		for(EClass eClass : superTypes) {
			if("ReachableSection".equals(eClass.getName()) || "RequirementSection".equals(eClass.getName())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean canFinish() {
		
		if(selectDocumentsPage == null || !selectDocumentsPage.isPageComplete()) {
			return false;
		}
		
		if(rmfMappingPage == null || !rmfMappingPage.isPageComplete()) {
			return false;
		}
		
		return true;
	}

	
	public Scope getScope() {
		return scope;
	}

	@Override
	public LabelProvider getSourceLabelProvider() {
		return null;
	}
	
}

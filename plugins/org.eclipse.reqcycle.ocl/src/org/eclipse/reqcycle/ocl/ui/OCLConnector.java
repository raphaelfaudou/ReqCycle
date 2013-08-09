package org.eclipse.reqcycle.ocl.ui;

import java.util.Collection;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.reqcycle.ocl.utils.OCLEvaluationUtilities;
import org.eclipse.reqcycle.repository.connector.ui.wizard.IConnectorWizard;
import org.eclipse.reqcycle.repository.data.IDataModelManager;
import org.eclipse.reqcycle.repository.data.IRequirementCreator;
import org.eclipse.reqcycle.repository.data.types.DataTypePackage;
import org.eclipse.reqcycle.repository.data.types.RequirementType;
import org.eclipse.reqcycle.repository.data.types.RequirementTypeAttribute;
import org.eclipse.reqcycle.repository.data.types.internal.RequirementTypeAttributeImpl;
import org.eclipse.reqcycle.repository.data.util.RepositoryConstants;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ziggurat.ocl.OCLEvaluator;

import DataModel.Contained;
import DataModel.DataModelFactory;
import DataModel.RequirementSource;
import DataModel.Scope;
import MappingModel.ElementMapping;

import com.google.common.collect.Iterables;

@SuppressWarnings("restriction")
public class OCLConnector extends Wizard implements IConnectorWizard, Listener {

	protected SettingBean bean = new SettingBean();

	private RequirementSource requirementSource = null;

	@Inject
	IRequirementCreator creator;

	@Inject
	IDataModelManager manager;

	@Override
	public void addPages() {
		addPage(new SettingPage(bean));
		addPage(new OCLPage(bean));
		super.addPages();
	}


	@Override
	public Callable<RequirementSource> createRequirementSource() {
		return new Callable<RequirementSource>() {

			@Override
			public RequirementSource call() throws Exception {
				RequirementSource result = null;

				if(OCLConnector.this.requirementSource != null) {
					result = OCLConnector.this.requirementSource;
				} else {
					result = DataModelFactory.eINSTANCE.createRequirementSource();
				}

				result.setProperty(RepositoryConstants.PROPERTY_URL, bean.getUri());
				fillRequirements(result);
				return result;
			}
		};
	}

	protected void fillRequirements(RequirementSource requirementSource) throws Exception {
		requirementSource.getRequirements().clear();
		Collection<ElementMapping> mapping = requirementSource.getMappings();
		ResourceSet resourceSet = new ResourceSetImpl();

		String repositoryUri = requirementSource.getRepositoryUri();

		Resource resource = resourceSet.getResource(URI.createPlatformResourceURI(repositoryUri, true), true);
		OCLEvaluator evaluator = OCLEvaluationUtilities.compileOCL(resourceSet, URI.createPlatformResourceURI(bean.getOclUri(), true));

		TreeIterator<EObject> contents = resource.getAllContents();
		Collection<RequirementType> requirementTypes = bean.getDataPackage().getDataTypes();;
		while(contents.hasNext()) {
			EObject eObject = contents.next();
			for(RequirementType reqType : requirementTypes) {
				if(OCLEvaluationUtilities.isDataType(evaluator, eObject, reqType)) {
					Contained requirement = createRequirement(evaluator, mapping, eObject, reqType);
					requirementSource.getRequirements().add(requirement);
				}
			}
		}
	}

	protected Contained createRequirement(OCLEvaluator evaluator, Collection<ElementMapping> mappings, EObject eObject, RequirementType reqType) throws Exception {
		Contained contained = (Contained)manager.createInstance(reqType);
		for(RequirementTypeAttribute attribute : Iterables.filter(reqType.getAttributes(), RequirementTypeAttribute.class)) {
			Object value = OCLEvaluationUtilities.getAttributeValue(evaluator, eObject, attribute);
			if (value != null){
				EAttribute eAttribute = ((RequirementTypeAttributeImpl)attribute).getEAttribute();
				creator.addAttribute(contained, eAttribute, value);
			}
		}
		return contained;
	}

	@Override
	public void initializeWithRequirementSource(RequirementSource requirementSource) {
		this.requirementSource = requirementSource;
	}

	@Override
	public boolean performFinish() {
		return true;
	}

	protected class SettingBean {

		private String uri = "";

		private String oclUri = "";

		private DataTypePackage dataPackage;

		private Scope scope;

		public SettingBean() {
		}

		public String getUri() {
			return uri;
		}

		public void setUri(String uri) {
			this.uri = uri;
			notifyChange();
		}

		public String getOclUri() {
			return oclUri;
		}

		public void setOclUri(String oclUri) {
			this.oclUri = oclUri;
			notifyChange();
		}

		public DataTypePackage getDataPackage() {
			return dataPackage;
		}

		public void setDataPackage(DataTypePackage dataPackage) {
			this.dataPackage = dataPackage;
			notifyChange();
		}

		public Scope getScope() {
			return scope;
		}

		public void setScope(Scope scope) {
			this.scope = scope;
			notifyChange();
		}

		public void notifyChange() {
			IWizardPage[] pages = getPages();
			if(pages != null) {
				for(int i = 0; i < pages.length; i++) {
					IWizardPage iWizardPage = pages[i];
					iWizardPage.getWizard().getContainer().updateButtons();
					if(iWizardPage instanceof Listener) {
						((Listener)iWizardPage).handleEvent(new Event());
					}
				}
			}
		}
	}

	@Override
	public boolean canFinish() {
		return bean != null && bean.getOclUri() != null && bean.getDataPackage() != null && bean.getUri() != null && !bean.getOclUri().isEmpty() && !bean.getUri().isEmpty();
	}

	@Override
	public void handleEvent(Event event) {
		getContainer().updateButtons();
	}

}

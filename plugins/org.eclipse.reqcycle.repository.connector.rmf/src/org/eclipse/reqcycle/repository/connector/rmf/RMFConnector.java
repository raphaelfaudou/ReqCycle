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

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.reqcycle.core.ILogger;
import org.eclipse.reqcycle.repository.connector.IConnector;
import org.eclipse.reqcycle.repository.connector.rmf.ui.RMFRepositoryMappingPage;
import org.eclipse.reqcycle.repository.requirement.data.IRequirementCreator;
import org.eclipse.reqcycle.repository.requirement.data.IRequirementSourceManager;
import org.eclipse.reqcycle.repository.requirement.data.IScopeManager;
import org.eclipse.reqcycle.repository.requirement.data.util.DataUtil;
import org.eclipse.reqcycle.repository.requirement.data.util.RepositoryConstants;
import org.eclipse.rmf.reqif10.AttributeValue;
import org.eclipse.rmf.reqif10.AttributeValueEnumeration;
import org.eclipse.rmf.reqif10.EnumValue;
import org.eclipse.rmf.reqif10.ReqIF;
import org.eclipse.rmf.reqif10.ReqIFContent;
import org.eclipse.rmf.reqif10.SpecElementWithAttributes;
import org.eclipse.rmf.reqif10.SpecHierarchy;
import org.eclipse.rmf.reqif10.SpecObject;
import org.eclipse.rmf.reqif10.SpecType;
import org.eclipse.rmf.reqif10.Specification;
import org.eclipse.rmf.reqif10.common.util.ReqIF10Util;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ziggurat.inject.ZigguratInject;

import CustomDataModel.CustomDataModelFactory;
import CustomDataModel.Enumeration;
import CustomDataModel.impl.CustomDataModelFactoryImpl;
import DataModel.Contained;
import DataModel.DataModelFactory;
import DataModel.ReachableSection;
import DataModel.Requirement;
import DataModel.RequirementSection;
import DataModel.RequirementSource;
import DataModel.Scope;
import MappingModel.AttributeMapping;
import MappingModel.ElementMapping;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;


public class RMFConnector implements IConnector {

	private @Inject
	static ILogger logger = ZigguratInject.make(ILogger.class);

	private @Inject
	static IRequirementSourceManager requirementSourceManager = ZigguratInject.make(IRequirementSourceManager.class);

	private @Inject
	static IScopeManager scopeManager = ZigguratInject.make(IScopeManager.class);

	private @Inject
	static IRequirementCreator creator = ZigguratInject.make(IRequirementCreator.class);

	public RMFConnector() {
	}

	public Callable<RequirementSource> createRequirementSource() {
		
		RequirementSource requirementSource = DataModelFactory.eINSTANCE.createRequirementSource();
		try {
			requirementSource.setProperty(RepositoryConstants.PROPERTY_CONNECTOR_ID, getConnectorId());
		} catch (Exception e) {
			boolean debug = logger.isDebug(Activator.OPTIONS_DEBUG, Activator.getDefault());
			if(debug) {
				logger.trace("Property " + RepositoryConstants.PROPERTY_CONNECTOR_ID + " can't be set on " + requirementSource.getRepositoryLabel() + "\n Trace : " + e.getMessage());
			}
		}
		return requirementSource;
	}

	@Override
	public void fillRequirements(RequirementSource requirementSource, IProgressMonitor progressMonitor) {

		Collection<ElementMapping> mapping = requirementSource.getMapping();

		//TODO : check that the requirement source is always contained in a resource
		//		Assert.isNotNull(requirementSource.eResource(), "Requirement Source isn't contained in resource");
		ResourceSet resourceSet = new ResourceSetImpl();

		String repositoryUri = requirementSource.getRepositoryUri();
		Resource resource = resourceSet.getResource(URI.createURI(repositoryUri, false), true);

		EList<EObject> contents = resource.getContents();
		for(EObject eObject : contents) {
			if(eObject instanceof ReqIF) {
				ReqIF reqIF = (ReqIF)eObject;

				ReqIFContent coreContent = reqIF.getCoreContent();
				EList<Specification> specifications = coreContent.getSpecifications();

				for(Specification specification : specifications) {

					EList<SpecHierarchy> specHierarchies = specification.getChildren();
					Collection<Contained> children = createChildren(specHierarchies, mapping);

					ElementMapping elementMapping = DataUtil.getElementMapping(mapping, specification.getType().getIdentifier());

					String id = getID(elementMapping, specification);
					String name = getName(elementMapping, specification);

					Contained section = createElement(mapping, specification, ReqIF10Util.getSpecType(specification).getIdentifier(), id, name);
					if(section != null) {
						requirementSource.getRequirements().add(section);
						addAttributes(elementMapping, specification.getValues(), section);
					}

					if(children != null && !children.isEmpty()) {
						if(section instanceof ReachableSection) {
							((ReachableSection)section).getChildren().addAll(children);
						} else if(section != null) {
							logger.error("Specification is not mapped with a Section typed element, children can be missed");
						}
					}
				}
			}
		}
	}

	private String getName(ElementMapping elementMapping, SpecElementWithAttributes element) {
		EList<AttributeMapping> attributes = elementMapping.getAttributes();
		for(AttributeMapping attribute : attributes) {
			if("name".equalsIgnoreCase(attribute.getTargetAttribute().getName())) {
				String sourceId = attribute.getSourceId();
				for(AttributeValue value : element.getValues()) {
					if(sourceId.equals(ReqIF10Util.getAttributeDefinition(value).getIdentifier())) {
						return ReqIF10Util.getTheValue(value).toString();
					}
				}
			}
		}
		return "";
	}

	private String getID(ElementMapping elementMapping, SpecElementWithAttributes element) {
		EList<AttributeMapping> attributes = elementMapping.getAttributes();
		for(AttributeMapping attribute : attributes) {
			if("id".equalsIgnoreCase(attribute.getTargetAttribute().getName())) {
				String sourceId = attribute.getSourceId();
				for(AttributeValue value : element.getValues()) {
					if(sourceId.equals(ReqIF10Util.getAttributeDefinition(value).getIdentifier())) {
						return ReqIF10Util.getTheValue(value).toString();
					}
				}
			}
		}
		return "";
	}

	protected Collection<Contained> createChildren(EList<SpecHierarchy> specHierarchies, Collection<ElementMapping> mapping) {

		Collection<Contained> result = new ArrayList<Contained>();

		for(SpecHierarchy specHierarchy : specHierarchies) {

			Collection<Contained> children = createChildren(specHierarchy.getChildren(), mapping);

			Contained createdObject = null;

			SpecObject specObject = specHierarchy.getObject();
			if(specObject != null) {

				ElementMapping elementMapping = DataUtil.getElementMapping(mapping, ReqIF10Util.getSpecType(specObject).getIdentifier());

				String id = getID(elementMapping, specObject);
				String name = getName(elementMapping, specObject);

				createdObject = createElement(mapping, specObject, ReqIF10Util.getSpecType(specObject).getIdentifier(), id, name);
			} else {
				createdObject = creator.createReachableSection(specHierarchy.getLongName(), specHierarchy.getDesc(), specHierarchy.getIdentifier());
			}

			if(createdObject != null) {
				if(children != null && !children.isEmpty()) {
					if(createdObject instanceof ReachableSection) {
						((ReachableSection)createdObject).getChildren().addAll(children);
					} else {
						logger.error("The element " + createdObject.getId() + " is not a Section Type, his children will be missed");
					}
				}
				result.add(createdObject);
			} else {
				if(children != null && !children.isEmpty()) {
					logger.error("The element " + specHierarchy.getIdentifier() + " can't be created, his children will be missed");
				}
			}
		}
		return result;
	}

	protected Contained createElement(Collection<ElementMapping> mapping, SpecElementWithAttributes specElement, String sourceQualifier, String id, String name) {
		ElementMapping elementMapping = DataUtil.getElementMapping(mapping, sourceQualifier);
		Contained createdObject = null;
		if(elementMapping != null) {
			createdObject = creator.addObject(elementMapping.getTargetElement(), id, name, id);
			addAttributes(elementMapping, specElement.getValues(), createdObject);
		}
		return createdObject;
	}

	protected void addAttributes(ElementMapping elementMapping, Collection<AttributeValue> values, Contained element) {
		for(AttributeValue attributeValue : values) {
			AttributeMapping attributeMapping = DataUtil.getAttributeMapping(elementMapping, ReqIF10Util.getAttributeDefinition(attributeValue).getIdentifier());
			if(attributeMapping == null || "id".equalsIgnoreCase(attributeMapping.getTargetAttribute().getName()) || "name".equalsIgnoreCase(attributeMapping.getTargetAttribute().getName())) {
				continue;
			}
			try {
				if(attributeValue instanceof AttributeValueEnumeration) {
					for(EnumValue enumValue : ((AttributeValueEnumeration)attributeValue).getValues()) {
						String name = enumValue.getLongName();
						EAttribute targetAttribute = attributeMapping.getTargetAttribute();
						if(targetAttribute.getEType() instanceof EEnum) {

							EEnumLiteral enumLiteral = ((EEnum)targetAttribute.getEType()).getEEnumLiteral(name);
							CustomDataModelFactory einstance = CustomDataModelFactory.eINSTANCE;

							if(einstance instanceof CustomDataModelFactoryImpl) {
								Enumeration result = ((CustomDataModelFactoryImpl)einstance).createEnumerationFromString(targetAttribute.getEAttributeType(), enumLiteral.getInstance().toString());
								creator.addAttribute(attributeMapping, element, result);
							}
						}
					}
				}
				creator.addAttribute(attributeMapping, element, ReqIF10Util.getTheValue(attributeValue));
			} catch (Exception e) {
				logger.error("Can't add the attribute " + ReqIF10Util.getAttributeDefinition(attributeValue).getIdentifier() + " to the element " + element.getName());
			}
		}
	}

	public void editProperties(RequirementSource requirementSource) {
		ResourceSet resourceSet = new ResourceSetImpl();

		Collection<EClassifier> targetEPackage = DataUtil.getTargetEPackage(resourceSet, "org.eclipse.reqcycle.repository.data/model/CustomDataModel.ecore");
		EList<SpecType> specTypes = RMFConnectorUi.getReqIFTypes(resourceSet, requirementSource.getRepositoryUri());
		EList<ElementMapping> mapping = requirementSource.getMapping();

		String label = requirementSource.getRepositoryLabel();
		String uri = requirementSource.getRepositoryUri();

		Collection<Scope> scopes = scopeManager.getAllScopes();

	}

	@Override
	public void editMapping(final RequirementSource requirementSource) {
		ResourceSet resourceSet = new ResourceSetImpl();
		Collection<EClassifier> targetEPackage = DataUtil.getTargetEPackage(resourceSet, "org.eclipse.reqcycle.repository.data/model/CustomDataModel.ecore");
		EList<SpecType> specTypes = RMFConnectorUi.getReqIFTypes(resourceSet, requirementSource.getRepositoryUri());
		EList<EObject> mapping = (EList)requirementSource.getMapping();
		final RMFRepositoryMappingPage mappingPage = RMFConnectorUi.getMappingPage(specTypes, targetEPackage, mapping);

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
				fillRequirements(requirementSource, new NullProgressMonitor());

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

}

package org.eclipse.reqcycle.repository.connector.rmf;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.reqcycle.core.ILogger;
import org.eclipse.reqcycle.repository.data.IDataTypeManager;
import org.eclipse.reqcycle.repository.data.IRequirementCreator;
import org.eclipse.reqcycle.repository.data.types.impl.internal.RequirementTypeImpl;
import org.eclipse.reqcycle.repository.data.util.DataUtil;
import org.eclipse.rmf.reqif10.AttributeValue;
import org.eclipse.rmf.reqif10.AttributeValueEnumeration;
import org.eclipse.rmf.reqif10.EnumValue;
import org.eclipse.rmf.reqif10.ReqIF;
import org.eclipse.rmf.reqif10.ReqIFContent;
import org.eclipse.rmf.reqif10.SpecElementWithAttributes;
import org.eclipse.rmf.reqif10.SpecHierarchy;
import org.eclipse.rmf.reqif10.SpecObject;
import org.eclipse.rmf.reqif10.SpecObjectType;
import org.eclipse.rmf.reqif10.SpecType;
import org.eclipse.rmf.reqif10.Specification;
import org.eclipse.rmf.reqif10.common.util.ReqIF10Util;
import org.eclipse.ziggurat.inject.ZigguratInject;

import DataModel.Contained;
import DataModel.RequirementSource;
import DataModel.Section;
import MappingModel.AttributeMapping;
import MappingModel.ElementMapping;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;


public class RMFUtils {

	private static ILogger logger = ZigguratInject.make(ILogger.class);

	private static IRequirementCreator creator = ZigguratInject.make(IRequirementCreator.class);
	
	private static IDataTypeManager dataTypeManager = ZigguratInject.make(IDataTypeManager.class);
	
	public static Collection<SpecType> getReqIFTypes(ResourceSet resourceSet, String fileLocation) {
		URI uriReqIf = URI.createURI(fileLocation, false);
		Resource reqIfResource = resourceSet.getResource(uriReqIf, true);
		EList<EObject> contents = reqIfResource.getContents();
		if(contents.size() > 0) {
			EObject content = contents.get(0);
			if(content instanceof ReqIF) {
				ReqIFContent coreContent = ((ReqIF)content).getCoreContent();
				EList<SpecType> specTypes = coreContent.getSpecTypes();
				//Gets SpecObjectTypes, specification elements are automatically transformed in sections
				return Collections2.filter(specTypes, new Predicate<SpecType>() {

					@Override
					public boolean apply(SpecType arg0) {
						if(arg0 instanceof SpecObjectType) {
							return true;
						}
						return false;
					}
				});
			}
		}
		return null;
	}

	public static void fillRequirements(RequirementSource requirementSource, IProgressMonitor progressMonitor) {

		Collection<ElementMapping> mapping = requirementSource.getMappings();
		if(mapping == null) {
			return;
		}

		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resource = resourceSet.getResource(URI.createURI(requirementSource.getRepositoryUri(), false), true);

		EList<EObject> contents = resource.getContents();
		for(EObject eObject : contents) {
			if(eObject instanceof ReqIF) {
				ReqIF reqIF = (ReqIF)eObject;

				ReqIFContent coreContent = reqIF.getCoreContent();
				EList<Specification> specifications = coreContent.getSpecifications();

				for(Specification specification : specifications) {

					EList<SpecHierarchy> specHierarchies = specification.getChildren();
					Collection<Contained> children = createChildren(specHierarchies, mapping);

//					ElementMapping elementMapping = DataUtil.getElementMapping(mapping, specification.getType().getIdentifier());

					String id = specification.getLongName();//getID(elementMapping, specification);
					String name = specification.getDesc();//getName(elementMapping, specification);

					Contained section = null;
					try {
						section = creator.createSection(id, name, ReqIF10Util.getSpecType(specification).getIdentifier());
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					if(section != null) {
						requirementSource.getRequirements().add(section);
//						addAttributes(elementMapping, specification.getValues(), section);
					}

					if(children != null && !children.isEmpty()) {
						if(section instanceof Section) {
							((Section)section).getChildren().addAll(children);
						} else if(section != null) {
							logger.error("Specification is not mapped with a Section typed element, children can be missed");
						}
					}
				}
			}
		}
	}

	protected static Collection<Contained> createChildren(EList<SpecHierarchy> specHierarchies, Collection<ElementMapping> mapping) {

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
				try {
					createdObject = creator.createSection(specHierarchy.getLongName(), specHierarchy.getDesc(), specHierarchy.getIdentifier());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if(createdObject != null) {
				if(children != null && !children.isEmpty()) {
					if(createdObject instanceof Section) {
						((Section)createdObject).getChildren().addAll(children);
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

	private static String getName(ElementMapping elementMapping, SpecElementWithAttributes element) {
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

	private static String getID(ElementMapping elementMapping, SpecElementWithAttributes element) {
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

	protected static Contained createElement(Collection<ElementMapping> mapping, SpecElementWithAttributes specElement, String sourceQualifier, String id, String name) {
		ElementMapping elementMapping = DataUtil.getElementMapping(mapping, sourceQualifier);
		Contained createdObject = null;
		if(elementMapping != null) {
			try {
				createdObject = creator.addObject(elementMapping.getTargetElement(), id, name, id);
				EObject instance = dataTypeManager.createInstance(new RequirementTypeImpl(elementMapping.getTargetElement()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			addAttributes(elementMapping, specElement.getValues(), createdObject);
		}
		return createdObject;
	}

	
	protected static void addAttributes(ElementMapping elementMapping, Collection<AttributeValue> values, Contained element) {
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
						if(targetAttribute.getEAttributeType() instanceof EEnum) {

							EEnumLiteral enumLiteral = ((EEnum)targetAttribute.getEAttributeType()).getEEnumLiteral(name);
							creator.addAttribute(attributeMapping, element, enumLiteral);
						}
					}
				}
				creator.addAttribute(attributeMapping, element, ReqIF10Util.getTheValue(attributeValue));
			} catch (Exception e) {
				logger.error("Can't add the attribute " + ReqIF10Util.getAttributeDefinition(attributeValue).getIdentifier() + " to the element " + element.getName());
			}
		}
	}

	public static ITreeContentProvider contentProvider = new ITreeContentProvider() {

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

}

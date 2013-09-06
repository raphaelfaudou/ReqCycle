package org.eclipse.reqcycle.repository.data.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import javax.inject.Inject;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.reqcycle.repository.data.IDataManager;
import org.eclipse.reqcycle.repository.data.types.IAttributeType;
import org.eclipse.reqcycle.repository.data.types.internal.AttributeTypeImpl;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ziggurat.inject.ZigguratInject;

import DataModel.Contained;
import DataModel.RequirementSource;
import DataModel.Scope;
import DataModel.Section;
import MappingModel.AttributeMapping;
import MappingModel.ElementMapping;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

public class DataUtil {

	/** Requirement Source Manager */
	private @Inject
	static IDataManager requirementSourceManager = ZigguratInject.make(IDataManager.class);

	public static final IAttributeType BYTE = new AttributeTypeImpl(EcorePackage.Literals.EBYTE.getName().replaceFirst("E", ""), EcorePackage.Literals.EBYTE);

	public static final IAttributeType STRING = new AttributeTypeImpl(EcorePackage.Literals.ESTRING.getName().replaceFirst("E", ""), EcorePackage.Literals.ESTRING);

	public static final IAttributeType INT = new AttributeTypeImpl(EcorePackage.Literals.EINT.getName().replaceFirst("E", ""), EcorePackage.Literals.EINT);

	public static final IAttributeType LONG = new AttributeTypeImpl(EcorePackage.Literals.ELONG.getName().replaceFirst("E", ""), EcorePackage.Literals.ELONG);

	public static final IAttributeType BIG_DECIMAL = new AttributeTypeImpl(EcorePackage.Literals.ELONG.getName().replaceFirst("E", ""), EcorePackage.Literals.ELONG);

	public static final IAttributeType CHAR = new AttributeTypeImpl(EcorePackage.Literals.ECHAR.getName().replaceFirst("E", ""), EcorePackage.Literals.ECHAR);

	public static final IAttributeType FLOAT = new AttributeTypeImpl(EcorePackage.Literals.EFLOAT.getName().replaceFirst("E", ""), EcorePackage.Literals.EFLOAT);

	public static final IAttributeType DOUBLE = new AttributeTypeImpl(EcorePackage.Literals.EDOUBLE.getName().replaceFirst("E", ""), EcorePackage.Literals.EDOUBLE);

	public static final IAttributeType SHORT = new AttributeTypeImpl(EcorePackage.Literals.ESHORT.getName().replaceFirst("E", ""), EcorePackage.Literals.ESHORT);

	public static final IAttributeType BIG_INTEGER = new AttributeTypeImpl(EcorePackage.Literals.EBIG_INTEGER.getName().replaceFirst("E", ""), EcorePackage.Literals.EBIG_INTEGER);

	public static final IAttributeType BOOLEAN = new AttributeTypeImpl(EcorePackage.Literals.EBOOLEAN.getName().replaceFirst("E", ""), EcorePackage.Literals.EBOOLEAN);

	public static final Collection<IAttributeType> eDataTypes = Arrays.asList(BYTE, STRING, INT, LONG, BIG_DECIMAL, CHAR, FLOAT, DOUBLE, SHORT, BIG_INTEGER, BOOLEAN);


	protected static ComposedAdapterFactory cAdapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

	public static String getLabel(Object obj) {
		IItemLabelProvider itemLabelProvider = (IItemLabelProvider)cAdapterFactory.adapt(obj, IItemLabelProvider.class);
		if(itemLabelProvider != null) {
			return itemLabelProvider.getText(obj);
		}
		if(obj instanceof Scope) {
			return ((Scope)obj).eClass().getName();
		}
		return obj.toString();
	}

	public static Image getImage(Object obj) {
		IItemLabelProvider itemLabelProvider = (IItemLabelProvider)cAdapterFactory.adapt(obj, IItemLabelProvider.class);
		if(itemLabelProvider != null) {
			return null;// Activator.getImageDescriptor(itemLabelProvider.getImage(obj).toString()).createImage();
		}
		return null;
	}

	public static LabelProvider labelProvider = new LabelProvider() {

		@Override
		public String getText(Object element) {
			return getLabel(element);
		};

		@Override
		public Image getImage(Object element) {
			return DataUtil.getImage(element);
		};

	};

	public static String getInformation(Contained object) {
		String result = "";

		EList<EStructuralFeature> structuralFeatures = object.eClass().getEStructuralFeatures();
		if(object.getId() != null && !object.getId().isEmpty()) {
			result += " id : " + object.getId() + " ";
		}
		if(object.getName() != null && !object.getName().isEmpty()) {
			if(!result.isEmpty()) {
				result += " | ";
			}
			result += " name : " + object.getName() + " ";
		}

		for(EStructuralFeature eStructuralFeature : structuralFeatures) {
			if(!(eStructuralFeature instanceof EReference) && object.eGet(eStructuralFeature) != null) {
				result += "[ " + eStructuralFeature.getName() + " : " + object.eGet(eStructuralFeature) + "]";
			}
		}
		if(result.isEmpty()) {
			result = "Requirement Type : " + object.eClass().getName() + " [ this element doesn't have any attribute ]";
		}

		return result;
	}

	/**
	 * Retrieves attribute mapping by his ID from an element mapping
	 * 
	 * @param elementMapping
	 *        the element mapping
	 * @param id
	 *        the attribute mapping ID
	 * @return the attribute mapping found or null
	 */
	public static AttributeMapping getAttributeMapping(ElementMapping elementMapping, String id) {
		for(AttributeMapping attribute : elementMapping.getAttributes()) {
			if(id.equals(attribute.getSourceId())) {
				return attribute;
			}
		}
		return null;
	}

	/**
	 * Retrieves element mapping from a mapping by his qualifier
	 * 
	 * @param mapping
	 *        the mapping
	 * @param qualifier
	 *        the source element qualifier
	 * @return the element mapping found or null
	 */
	public static ElementMapping getElementMapping(Collection<ElementMapping> mapping, String qualifier) {
		for(ElementMapping elementMapping : mapping) {
			if(qualifier.equals(elementMapping.getSourceQualifier())) {
				return elementMapping;
			}
		}
		return null;
	}

	public static Collection<Contained> getAllContainedElements(EList<Contained> containedElements) {
		Collection<Contained> result = new ArrayList<Contained>();
		result.addAll(containedElements);
		for(Contained contained : containedElements) {
			if(contained instanceof Section) {
				result.addAll(getAllContainedElements(((Section)contained).getChildren()));
			}
		}
		return result;
	}

	/**
	 * @param resourceSet
	 * @param modelLocation
	 * @return
	 */
	public static Collection<EClassifier> getTargetEPackage(ResourceSet resourceSet, String modelLocation) {
		//TODO : check uri creation
		URI uriAttributeModel = URI.createPlatformPluginURI(modelLocation, false);
		Resource attributeModelResource = resourceSet.getResource(uriAttributeModel, true);
		EList<EObject> modelContent = attributeModelResource.getContents();
		if(modelContent.size() > 0) {
			Collection<EClassifier> result = new ArrayList<EClassifier>();
			Iterator<EObject> iter = modelContent.iterator();
			while(iter.hasNext()) {
				EObject eObject = iter.next();
				if(eObject instanceof EPackage) {
					Collection<EClassifier> filtered = Collections2.filter(((EPackage)eObject).getEClassifiers(), new Predicate<EClassifier>() {

						@Override
						public boolean apply(EClassifier arg0) {
							if(arg0 instanceof EClass) {
								EList<EClass> superTypes = ((EClass)arg0).getEAllSuperTypes();
								for(EClassifier superType : superTypes) {
									if("Contained".equals(superType.getName())) {
										return true;
									}
								}
							}
							return false;
						}
					});
					result.addAll(filtered);
				}
			}
			return result;
		}
		return null;
	}

	public static Collection<RequirementSource> getRepositories(Object obj) {
		if(obj instanceof String && requirementSourceManager.getRequirementSources((String)obj) != null) {
			return requirementSourceManager.getRequirementSources((String)obj);
		}
		if(obj instanceof RequirementSource) {
			return Arrays.asList(((RequirementSource)obj));
		}
		return Collections.emptyList();
	}

}

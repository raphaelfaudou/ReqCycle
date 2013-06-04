package org.eclipse.reqcycle.repository.requirement.data.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import javax.inject.Inject;

import org.agesys.inject.AgesysInject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.reqcycle.repository.requirement.data.IRequirementSourceManager;
import org.eclipse.swt.graphics.Image;

import DataModel.Contained;
import DataModel.ReachableSection;
import DataModel.RequirementSource;
import DataModel.Scope;
import MappingModel.AttributeMapping;
import MappingModel.ElementMapping;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

public class DataUtil {

	/** Requirement Source Manager */
	private @Inject
	static IRequirementSourceManager requirementSourceManager = AgesysInject.make(IRequirementSourceManager.class);

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

	public static LabelProvider labelProvider = new LabelProvider(){
		public String getText(Object element) {
			return getLabel(element);
		};
		
		public Image getImage(Object element) {
			return DataUtil.getImage(element);
		};
		
	};
	
	public static String getInformation(Contained object){
		String label = object.getName();
		EList<EStructuralFeature> structuralFeatures = object.eClass().getEStructuralFeatures();
		String attr = " [ id : " + object.getId() + " ]" + "[ name : " + object.getName() + " ]";

		for(EStructuralFeature eStructuralFeature : structuralFeatures) {
			attr += "[ " + eStructuralFeature.getName() + " : " + object.eGet(eStructuralFeature) + "]";
		}
		return label + attr;
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
	 * @return the element maaping found or null
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
			if(contained instanceof ReachableSection) {
				result.addAll(getAllContainedElements(((ReachableSection)contained).getChildren()));
			}
		}
		return result;
	}

	/**
	 * @param resourceSet
	 * @param uriAttributeModelString2
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
				EObject eObject = (EObject)iter.next();
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
		if(obj instanceof String && requirementSourceManager.getRepositories((String)obj) != null) {
			return requirementSourceManager.getRepositories((String)obj);
		}
		if(obj instanceof RequirementSource) {
			return Arrays.asList(((RequirementSource)obj));
		}
		return Collections.emptyList();
	}
	
	public static ITreeContentProvider CollectionTreeContentProvider = new ITreeContentProvider() {
		
		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void dispose() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public boolean hasChildren(Object element) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public Object getParent(Object element) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Object[] getElements(Object inputElement) {
			return ArrayContentProvider.getInstance().getElements(inputElement);
		}
		
		@Override
		public Object[] getChildren(Object parentElement) {
			// TODO Auto-generated method stub
			return null;
		}
	};

}

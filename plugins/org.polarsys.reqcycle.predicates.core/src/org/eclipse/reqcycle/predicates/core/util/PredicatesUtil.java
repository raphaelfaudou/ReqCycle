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
 *  Papa Issa DIAKHATE (AtoS) papa-issa.diakhate@atos.net - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.reqcycle.predicates.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.ClassUtils;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.reqcycle.predicates.core.IPredicatesAnnotationSources;
import org.eclipse.reqcycle.predicates.core.PredicatesFactory;
import org.eclipse.reqcycle.predicates.core.PredicatesPackage;
import org.eclipse.reqcycle.predicates.core.api.IPredicate;
import org.eclipse.reqcycle.predicates.core.api.ITypedPredicate;

public class PredicatesUtil implements IPredicatesAnnotationSources {

	private PredicatesUtil() {
	}

	/**
	 * @return A collection containing the (non abstract and non interface) IPredicate objects from the default model.
	 */
	public static Collection<IPredicate> getDefaultPredicates() {
		final Collection<IPredicate> predicates = new ArrayList<IPredicate>();
		for(final EClassifier classifier : PredicatesPackage.eINSTANCE.getEClassifiers()) {
			if(classifier instanceof EClass) {
				final EClass eClass = (EClass)classifier;
				if(PredicatesPackage.Literals.IPREDICATE.isSuperTypeOf(eClass)) {
					if(!(eClass.isInterface() || eClass.isAbstract())) {
						predicates.add((IPredicate)PredicatesFactory.eINSTANCE.create(eClass));
					}
				}
			}
		}
		return predicates;
	}

	/**
	 * @param attribute
	 * @param type
	 * @return The boolean value indicating whether objects of the specified type "type" can be assigned to objects of
	 *         this attribute.
	 * 
	 */
	public static boolean isSubType(final EAttribute attribute, final Class<?> type) {
		if(attribute == null || type == null) {
			throw new NullPointerException("The attribute or the type cannot be null.");
		}
		EDataType dataType = null;
		try {
			dataType = (EDataType)attribute.getEType();
		} catch (ClassCastException e) {
			return false;
		}
		Class<?> attributeClass = dataType.getInstanceClass();
		if(attributeClass.isPrimitive()) {
			attributeClass = ClassUtils.primitiveToWrapper(attributeClass);
		}
		boolean result = false;
		if(type.isPrimitive()) {
			result = attributeClass.isAssignableFrom(ClassUtils.primitiveToWrapper(type));
		} else {
			result = attributeClass.isAssignableFrom(type);
		}
		return result;
	}

	/**
	 * @param predicate
	 * @return The single EAttribute annotated with the {@value #EANNOTATION_SOURCE_SPECIFIC_INPUT} annotation source.
	 */
	public static EAttribute getUserSpecificInputAttribute(final ITypedPredicate<?> predicate) {
		final EAttribute result = getEAllAttributesAnnotatedBy(predicate.eClass(), EANNOTATION_SOURCE_SPECIFIC_INPUT).iterator().next();
		return result;
	}

	/**
	 * @param eClass
	 * @param annotationSource
	 * @return A collection of EAttribute annotated by the specified annotation source from the specified EClass and all
	 *         its super types (java superclasses).
	 */
	public static Collection<EAttribute> getEAllAttributesAnnotatedBy(final EClass eClass, final String annotationSource) {
		Set<EAttribute> attributes = new HashSet<EAttribute>();
		for(EClass c : getEAllSuperTypes(eClass)) {
			attributes.addAll(getEAttributesAnnotatedBy(c, annotationSource));
		}
		attributes.addAll(getEAttributesAnnotatedBy(eClass, annotationSource));
		return attributes;
	}

	/**
	 * @param eClass
	 * @param annotationSource
	 * @return A collection of EAttribute annotated by the specified annotation source for the specified EClass.
	 */
	public static Collection<EAttribute> getEAttributesAnnotatedBy(final EClass eClass, final String annotationSource) {
		final Collection<EAttribute> attributes = new ArrayList<EAttribute>();
		for(final EAttribute attr : eClass.getEAllAttributes()) {
			if(attr.getEAnnotation(annotationSource) != null) {
				attributes.add(attr);
			}
		}
		return attributes;
	}

	private static Set<EClass> getEAllSuperTypes(final EClass eClass) {
		final Set<EClass> superTypes = new HashSet<EClass>();
		superTypes.addAll(eClass.getEAllSuperTypes());
		for(EClass c : eClass.getESuperTypes()) {
			if(!c.getESuperTypes().isEmpty()) {
				superTypes.addAll(getEAllSuperTypes(c));
			}
		}
		return superTypes;
	}

	/**
	 * @param attribute
	 * @return <code>true</code> if the EAttribute is annotated with the annotation source {@value #EANNOTATION_SOURCE_ADAPT_INPUT}
	 */
	public static boolean isAdaptable(final EAttribute attribute) {
		return attribute.getEAnnotation(EANNOTATION_SOURCE_ADAPT_INPUT) != null;
	}

	/**
	 * @param predicate
	 * @return The class cast that is to be used for the input, <tt>null</tt> if the annotation is not found or if the
	 *         java class is not found.
	 */
	public static Class<?> getCastClassForInput(final IPredicate predicate) {
		final EAnnotation annotation = predicate.eClass().getEAnnotation(EANNOTATION_SOURCE_INPUT_JAVACLASS_TYPE);
		if(annotation != null) {
			String javaClassname = annotation.getDetails().get("inputType");
			try {
				return Class.forName(javaClassname);
			} catch (ClassNotFoundException e) {
				// TODO: log
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * @param predicate
	 * @return If the predicate is annotated with {@link IPredicatesAnnotationSources#EANNOTATION_SOURCE_INPUT_JAVACLASS_TYPE} and contains also the
	 *         key
	 *         "objectType" which represents the fully qualified name of a java class, then the Class object of the
	 *         value will be returned. If that annotation is not found, <code>null</code> is returned.
	 */
	public static Class<?> getObjectClassForInput(final IPredicate predicate) {
		final EAnnotation annotation = predicate.eClass().getEAnnotation(EANNOTATION_SOURCE_INPUT_JAVACLASS_TYPE);
		if(annotation != null) {
			String javaClassname = annotation.getDetails().get("objectType");
			try {
				return Class.forName(javaClassname);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

}

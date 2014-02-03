/*******************************************************************************
 *  * Copyright (c) 2013-2014 AtoS.
 *  * All rights reserved. This program and the accompanying materials
 *  * are made available under the terms of the Eclipse Public License v1.0
 *  * which accompanies this distribution, and is available at
 *  * http://www.eclipse.org/legal/epl-v10.html
 *  *
 *  * Contributors:
 *  *    {INITIAL AUTHOR} - initial API and implementation and/or initial documentation
 *******************************************************************************/
/**
 */
package org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CacheTracabilityFactoryImpl extends EFactoryImpl implements CacheTracabilityFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CacheTracabilityFactory init() {
		try {
			CacheTracabilityFactory theCacheTracabilityFactory = (CacheTracabilityFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.eclipse.org/tracability/1.0"); 
			if (theCacheTracabilityFactory != null) {
				return theCacheTracabilityFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CacheTracabilityFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CacheTracabilityFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case CacheTracabilityPackage.TRACEABILITY_LINK: return createTraceabilityLink();
			case CacheTracabilityPackage.ATTRIBUTE: return createAttribute();
			case CacheTracabilityPackage.TRACEABLE_ELEMENT: return createTraceableElement();
			case CacheTracabilityPackage.MODEL: return createModel();
			case CacheTracabilityPackage.ANALYZED_RESOURCE: return createAnalyzedResource();
			case CacheTracabilityPackage.PROPERTY: return createProperty();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TraceabilityLink createTraceabilityLink() {
		TraceabilityLinkImpl traceabilityLink = new TraceabilityLinkImpl();
		return traceabilityLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Attribute createAttribute() {
		AttributeImpl attribute = new AttributeImpl();
		return attribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TraceableElement createTraceableElement() {
		TraceableElementImpl traceableElement = new TraceableElementImpl();
		return traceableElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Model createModel() {
		ModelImpl model = new ModelImpl();
		return model;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnalyzedResource createAnalyzedResource() {
		AnalyzedResourceImpl analyzedResource = new AnalyzedResourceImpl();
		return analyzedResource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Property createProperty() {
		PropertyImpl property = new PropertyImpl();
		return property;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CacheTracabilityPackage getCacheTracabilityPackage() {
		return (CacheTracabilityPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static CacheTracabilityPackage getPackage() {
		return CacheTracabilityPackage.eINSTANCE;
	}

} //CacheTracabilityFactoryImpl

/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
/**
 */
package org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.AnalyzedResource;
import org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.Attribute;
import org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.CacheTracabilityFactory;
import org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.CacheTracabilityPackage;
import org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.Model;
import org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.Property;
import org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceabilityLink;
import org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceableElement;
import org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.URIElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CacheTracabilityPackageImpl extends EPackageImpl implements CacheTracabilityPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass traceabilityLinkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass attributeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass traceableElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass analyzedResourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uriElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.CacheTracabilityPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private CacheTracabilityPackageImpl() {
		super(eNS_URI, CacheTracabilityFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link CacheTracabilityPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static CacheTracabilityPackage init() {
		if (isInited) return (CacheTracabilityPackage)EPackage.Registry.INSTANCE.getEPackage(CacheTracabilityPackage.eNS_URI);

		// Obtain or create and register package
		CacheTracabilityPackageImpl theCacheTracabilityPackage = (CacheTracabilityPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof CacheTracabilityPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new CacheTracabilityPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theCacheTracabilityPackage.createPackageContents();

		// Initialize created meta-data
		theCacheTracabilityPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theCacheTracabilityPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(CacheTracabilityPackage.eNS_URI, theCacheTracabilityPackage);
		return theCacheTracabilityPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTraceabilityLink() {
		return traceabilityLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTraceabilityLink_Attributes() {
		return (EReference)traceabilityLinkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTraceabilityLink_Sources() {
		return (EReference)traceabilityLinkEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTraceabilityLink_Targets() {
		return (EReference)traceabilityLinkEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTraceabilityLink_Deleted() {
		return (EAttribute)traceabilityLinkEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTraceabilityLink_Resource() {
		return (EReference)traceabilityLinkEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTraceabilityLink_Label() {
		return (EAttribute)traceabilityLinkEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAttribute() {
		return attributeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTraceableElement() {
		return traceableElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTraceableElement_Outgoings() {
		return (EReference)traceableElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTraceableElement_Incomings() {
		return (EReference)traceableElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTraceableElement_Properties() {
		return (EReference)traceableElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModel() {
		return modelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_Traceables() {
		return (EReference)modelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_Resources() {
		return (EReference)modelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAnalyzedResource() {
		return analyzedResourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAnalyzedResource_ModificationTime() {
		return (EAttribute)analyzedResourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnalyzedResource_Contained() {
		return (EReference)analyzedResourceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnalyzedResource_Links() {
		return (EReference)analyzedResourceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getURIElement() {
		return uriElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getURIElement_Uri() {
		return (EAttribute)uriElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProperty() {
		return propertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProperty_Name() {
		return (EAttribute)propertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProperty_Value() {
		return (EAttribute)propertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CacheTracabilityFactory getCacheTracabilityFactory() {
		return (CacheTracabilityFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		traceabilityLinkEClass = createEClass(TRACEABILITY_LINK);
		createEReference(traceabilityLinkEClass, TRACEABILITY_LINK__ATTRIBUTES);
		createEReference(traceabilityLinkEClass, TRACEABILITY_LINK__SOURCES);
		createEReference(traceabilityLinkEClass, TRACEABILITY_LINK__TARGETS);
		createEAttribute(traceabilityLinkEClass, TRACEABILITY_LINK__DELETED);
		createEReference(traceabilityLinkEClass, TRACEABILITY_LINK__RESOURCE);
		createEAttribute(traceabilityLinkEClass, TRACEABILITY_LINK__LABEL);

		attributeEClass = createEClass(ATTRIBUTE);

		traceableElementEClass = createEClass(TRACEABLE_ELEMENT);
		createEReference(traceableElementEClass, TRACEABLE_ELEMENT__OUTGOINGS);
		createEReference(traceableElementEClass, TRACEABLE_ELEMENT__INCOMINGS);
		createEReference(traceableElementEClass, TRACEABLE_ELEMENT__PROPERTIES);

		modelEClass = createEClass(MODEL);
		createEReference(modelEClass, MODEL__TRACEABLES);
		createEReference(modelEClass, MODEL__RESOURCES);

		analyzedResourceEClass = createEClass(ANALYZED_RESOURCE);
		createEAttribute(analyzedResourceEClass, ANALYZED_RESOURCE__MODIFICATION_TIME);
		createEReference(analyzedResourceEClass, ANALYZED_RESOURCE__CONTAINED);
		createEReference(analyzedResourceEClass, ANALYZED_RESOURCE__LINKS);

		uriElementEClass = createEClass(URI_ELEMENT);
		createEAttribute(uriElementEClass, URI_ELEMENT__URI);

		propertyEClass = createEClass(PROPERTY);
		createEAttribute(propertyEClass, PROPERTY__NAME);
		createEAttribute(propertyEClass, PROPERTY__VALUE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		traceableElementEClass.getESuperTypes().add(this.getURIElement());
		analyzedResourceEClass.getESuperTypes().add(this.getURIElement());

		// Initialize classes and features; add operations and parameters
		initEClass(traceabilityLinkEClass, TraceabilityLink.class, "TraceabilityLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTraceabilityLink_Attributes(), this.getAttribute(), null, "attributes", null, 0, -1, TraceabilityLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTraceabilityLink_Sources(), this.getTraceableElement(), this.getTraceableElement_Outgoings(), "sources", null, 0, -1, TraceabilityLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTraceabilityLink_Targets(), this.getTraceableElement(), this.getTraceableElement_Incomings(), "targets", null, 0, -1, TraceabilityLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTraceabilityLink_Deleted(), ecorePackage.getEBoolean(), "deleted", null, 0, 1, TraceabilityLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTraceabilityLink_Resource(), this.getAnalyzedResource(), this.getAnalyzedResource_Links(), "resource", null, 0, 1, TraceabilityLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTraceabilityLink_Label(), ecorePackage.getEString(), "label", null, 0, 1, TraceabilityLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(attributeEClass, Attribute.class, "Attribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(traceableElementEClass, TraceableElement.class, "TraceableElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTraceableElement_Outgoings(), this.getTraceabilityLink(), this.getTraceabilityLink_Sources(), "outgoings", null, 0, -1, TraceableElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTraceableElement_Incomings(), this.getTraceabilityLink(), this.getTraceabilityLink_Targets(), "incomings", null, 0, -1, TraceableElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTraceableElement_Properties(), this.getProperty(), null, "properties", null, 0, -1, TraceableElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modelEClass, Model.class, "Model", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getModel_Traceables(), this.getTraceableElement(), null, "traceables", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_Resources(), this.getAnalyzedResource(), null, "resources", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(analyzedResourceEClass, AnalyzedResource.class, "AnalyzedResource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAnalyzedResource_ModificationTime(), ecorePackage.getEString(), "modificationTime", null, 0, 1, AnalyzedResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAnalyzedResource_Contained(), this.getTraceabilityLink(), null, "contained", null, 0, 1, AnalyzedResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAnalyzedResource_Links(), this.getTraceabilityLink(), this.getTraceabilityLink_Resource(), "links", null, 0, -1, AnalyzedResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uriElementEClass, URIElement.class, "URIElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getURIElement_Uri(), ecorePackage.getEString(), "uri", null, 0, 1, URIElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(propertyEClass, Property.class, "Property", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProperty_Name(), ecorePackage.getEString(), "name", null, 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProperty_Value(), ecorePackage.getEString(), "value", null, 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //CacheTracabilityPackageImpl

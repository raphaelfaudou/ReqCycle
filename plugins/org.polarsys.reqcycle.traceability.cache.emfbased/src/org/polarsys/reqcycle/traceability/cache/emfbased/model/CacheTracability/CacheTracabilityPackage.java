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
package org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.CacheTracabilityFactory
 * @model kind="package"
 * @generated
 */
public interface CacheTracabilityPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "CacheTracability";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/tracability/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "CacheTracability";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CacheTracabilityPackage eINSTANCE = org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.CacheTracabilityPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.TraceabilityLinkImpl <em>Traceability Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.TraceabilityLinkImpl
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.CacheTracabilityPackageImpl#getTraceabilityLink()
	 * @generated
	 */
	int TRACEABILITY_LINK = 0;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACEABILITY_LINK__ATTRIBUTES = 0;

	/**
	 * The feature id for the '<em><b>Sources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACEABILITY_LINK__SOURCES = 1;

	/**
	 * The feature id for the '<em><b>Targets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACEABILITY_LINK__TARGETS = 2;

	/**
	 * The feature id for the '<em><b>Deleted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACEABILITY_LINK__DELETED = 3;

	/**
	 * The feature id for the '<em><b>Resource</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACEABILITY_LINK__RESOURCE = 4;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACEABILITY_LINK__LABEL = 5;

	/**
	 * The number of structural features of the '<em>Traceability Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACEABILITY_LINK_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.AttributeImpl <em>Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.AttributeImpl
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.CacheTracabilityPackageImpl#getAttribute()
	 * @generated
	 */
	int ATTRIBUTE = 1;

	/**
	 * The number of structural features of the '<em>Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.URIElementImpl <em>URI Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.URIElementImpl
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.CacheTracabilityPackageImpl#getURIElement()
	 * @generated
	 */
	int URI_ELEMENT = 5;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URI_ELEMENT__URI = 0;

	/**
	 * The number of structural features of the '<em>URI Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URI_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.TraceableElementImpl <em>Traceable Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.TraceableElementImpl
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.CacheTracabilityPackageImpl#getTraceableElement()
	 * @generated
	 */
	int TRACEABLE_ELEMENT = 2;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACEABLE_ELEMENT__URI = URI_ELEMENT__URI;

	/**
	 * The feature id for the '<em><b>Outgoings</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACEABLE_ELEMENT__OUTGOINGS = URI_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Incomings</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACEABLE_ELEMENT__INCOMINGS = URI_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACEABLE_ELEMENT__PROPERTIES = URI_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Traceable Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACEABLE_ELEMENT_FEATURE_COUNT = URI_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.ModelImpl <em>Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.ModelImpl
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.CacheTracabilityPackageImpl#getModel()
	 * @generated
	 */
	int MODEL = 3;

	/**
	 * The feature id for the '<em><b>Traceables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__TRACEABLES = 0;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__RESOURCES = 1;

	/**
	 * The number of structural features of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_FEATURE_COUNT = 2;


	/**
	 * The meta object id for the '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.AnalyzedResourceImpl <em>Analyzed Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.AnalyzedResourceImpl
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.CacheTracabilityPackageImpl#getAnalyzedResource()
	 * @generated
	 */
	int ANALYZED_RESOURCE = 4;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYZED_RESOURCE__URI = URI_ELEMENT__URI;

	/**
	 * The feature id for the '<em><b>Modification Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYZED_RESOURCE__MODIFICATION_TIME = URI_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Contained</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYZED_RESOURCE__CONTAINED = URI_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYZED_RESOURCE__LINKS = URI_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Analyzed Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYZED_RESOURCE_FEATURE_COUNT = URI_ELEMENT_FEATURE_COUNT + 3;


	/**
	 * The meta object id for the '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.PropertyImpl <em>Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.PropertyImpl
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.CacheTracabilityPackageImpl#getProperty()
	 * @generated
	 */
	int PROPERTY = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__NAME = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_FEATURE_COUNT = 2;


	/**
	 * Returns the meta object for class '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceabilityLink <em>Traceability Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Traceability Link</em>'.
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceabilityLink
	 * @generated
	 */
	EClass getTraceabilityLink();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceabilityLink#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceabilityLink#getAttributes()
	 * @see #getTraceabilityLink()
	 * @generated
	 */
	EReference getTraceabilityLink_Attributes();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceabilityLink#getSources <em>Sources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Sources</em>'.
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceabilityLink#getSources()
	 * @see #getTraceabilityLink()
	 * @generated
	 */
	EReference getTraceabilityLink_Sources();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceabilityLink#getTargets <em>Targets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Targets</em>'.
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceabilityLink#getTargets()
	 * @see #getTraceabilityLink()
	 * @generated
	 */
	EReference getTraceabilityLink_Targets();

	/**
	 * Returns the meta object for the attribute '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceabilityLink#isDeleted <em>Deleted</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Deleted</em>'.
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceabilityLink#isDeleted()
	 * @see #getTraceabilityLink()
	 * @generated
	 */
	EAttribute getTraceabilityLink_Deleted();

	/**
	 * Returns the meta object for the container reference '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceabilityLink#getResource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Resource</em>'.
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceabilityLink#getResource()
	 * @see #getTraceabilityLink()
	 * @generated
	 */
	EReference getTraceabilityLink_Resource();

	/**
	 * Returns the meta object for the attribute '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceabilityLink#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceabilityLink#getLabel()
	 * @see #getTraceabilityLink()
	 * @generated
	 */
	EAttribute getTraceabilityLink_Label();

	/**
	 * Returns the meta object for class '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.Attribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute</em>'.
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.Attribute
	 * @generated
	 */
	EClass getAttribute();

	/**
	 * Returns the meta object for class '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceableElement <em>Traceable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Traceable Element</em>'.
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceableElement
	 * @generated
	 */
	EClass getTraceableElement();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceableElement#getOutgoings <em>Outgoings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Outgoings</em>'.
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceableElement#getOutgoings()
	 * @see #getTraceableElement()
	 * @generated
	 */
	EReference getTraceableElement_Outgoings();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceableElement#getIncomings <em>Incomings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Incomings</em>'.
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceableElement#getIncomings()
	 * @see #getTraceableElement()
	 * @generated
	 */
	EReference getTraceableElement_Incomings();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceableElement#getProperties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Properties</em>'.
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceableElement#getProperties()
	 * @see #getTraceableElement()
	 * @generated
	 */
	EReference getTraceableElement_Properties();

	/**
	 * Returns the meta object for class '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.Model <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model</em>'.
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.Model
	 * @generated
	 */
	EClass getModel();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.Model#getTraceables <em>Traceables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Traceables</em>'.
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.Model#getTraceables()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Traceables();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.Model#getResources <em>Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resources</em>'.
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.Model#getResources()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Resources();

	/**
	 * Returns the meta object for class '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.AnalyzedResource <em>Analyzed Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Analyzed Resource</em>'.
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.AnalyzedResource
	 * @generated
	 */
	EClass getAnalyzedResource();

	/**
	 * Returns the meta object for the attribute '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.AnalyzedResource#getModificationTime <em>Modification Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Modification Time</em>'.
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.AnalyzedResource#getModificationTime()
	 * @see #getAnalyzedResource()
	 * @generated
	 */
	EAttribute getAnalyzedResource_ModificationTime();

	/**
	 * Returns the meta object for the reference '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.AnalyzedResource#getContained <em>Contained</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Contained</em>'.
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.AnalyzedResource#getContained()
	 * @see #getAnalyzedResource()
	 * @generated
	 */
	EReference getAnalyzedResource_Contained();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.AnalyzedResource#getLinks <em>Links</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Links</em>'.
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.AnalyzedResource#getLinks()
	 * @see #getAnalyzedResource()
	 * @generated
	 */
	EReference getAnalyzedResource_Links();

	/**
	 * Returns the meta object for class '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.URIElement <em>URI Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>URI Element</em>'.
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.URIElement
	 * @generated
	 */
	EClass getURIElement();

	/**
	 * Returns the meta object for the attribute '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.URIElement#getUri <em>Uri</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uri</em>'.
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.URIElement#getUri()
	 * @see #getURIElement()
	 * @generated
	 */
	EAttribute getURIElement_Uri();

	/**
	 * Returns the meta object for class '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.Property <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property</em>'.
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.Property
	 * @generated
	 */
	EClass getProperty();

	/**
	 * Returns the meta object for the attribute '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.Property#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.Property#getName()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.Property#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.Property#getValue()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Value();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CacheTracabilityFactory getCacheTracabilityFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.TraceabilityLinkImpl <em>Traceability Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.TraceabilityLinkImpl
		 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.CacheTracabilityPackageImpl#getTraceabilityLink()
		 * @generated
		 */
		EClass TRACEABILITY_LINK = eINSTANCE.getTraceabilityLink();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACEABILITY_LINK__ATTRIBUTES = eINSTANCE.getTraceabilityLink_Attributes();

		/**
		 * The meta object literal for the '<em><b>Sources</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACEABILITY_LINK__SOURCES = eINSTANCE.getTraceabilityLink_Sources();

		/**
		 * The meta object literal for the '<em><b>Targets</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACEABILITY_LINK__TARGETS = eINSTANCE.getTraceabilityLink_Targets();

		/**
		 * The meta object literal for the '<em><b>Deleted</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRACEABILITY_LINK__DELETED = eINSTANCE.getTraceabilityLink_Deleted();

		/**
		 * The meta object literal for the '<em><b>Resource</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACEABILITY_LINK__RESOURCE = eINSTANCE.getTraceabilityLink_Resource();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRACEABILITY_LINK__LABEL = eINSTANCE.getTraceabilityLink_Label();

		/**
		 * The meta object literal for the '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.AttributeImpl <em>Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.AttributeImpl
		 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.CacheTracabilityPackageImpl#getAttribute()
		 * @generated
		 */
		EClass ATTRIBUTE = eINSTANCE.getAttribute();

		/**
		 * The meta object literal for the '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.TraceableElementImpl <em>Traceable Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.TraceableElementImpl
		 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.CacheTracabilityPackageImpl#getTraceableElement()
		 * @generated
		 */
		EClass TRACEABLE_ELEMENT = eINSTANCE.getTraceableElement();

		/**
		 * The meta object literal for the '<em><b>Outgoings</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACEABLE_ELEMENT__OUTGOINGS = eINSTANCE.getTraceableElement_Outgoings();

		/**
		 * The meta object literal for the '<em><b>Incomings</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACEABLE_ELEMENT__INCOMINGS = eINSTANCE.getTraceableElement_Incomings();

		/**
		 * The meta object literal for the '<em><b>Properties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACEABLE_ELEMENT__PROPERTIES = eINSTANCE.getTraceableElement_Properties();

		/**
		 * The meta object literal for the '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.ModelImpl <em>Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.ModelImpl
		 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.CacheTracabilityPackageImpl#getModel()
		 * @generated
		 */
		EClass MODEL = eINSTANCE.getModel();

		/**
		 * The meta object literal for the '<em><b>Traceables</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__TRACEABLES = eINSTANCE.getModel_Traceables();

		/**
		 * The meta object literal for the '<em><b>Resources</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__RESOURCES = eINSTANCE.getModel_Resources();

		/**
		 * The meta object literal for the '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.AnalyzedResourceImpl <em>Analyzed Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.AnalyzedResourceImpl
		 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.CacheTracabilityPackageImpl#getAnalyzedResource()
		 * @generated
		 */
		EClass ANALYZED_RESOURCE = eINSTANCE.getAnalyzedResource();

		/**
		 * The meta object literal for the '<em><b>Modification Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ANALYZED_RESOURCE__MODIFICATION_TIME = eINSTANCE.getAnalyzedResource_ModificationTime();

		/**
		 * The meta object literal for the '<em><b>Contained</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANALYZED_RESOURCE__CONTAINED = eINSTANCE.getAnalyzedResource_Contained();

		/**
		 * The meta object literal for the '<em><b>Links</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANALYZED_RESOURCE__LINKS = eINSTANCE.getAnalyzedResource_Links();

		/**
		 * The meta object literal for the '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.URIElementImpl <em>URI Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.URIElementImpl
		 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.CacheTracabilityPackageImpl#getURIElement()
		 * @generated
		 */
		EClass URI_ELEMENT = eINSTANCE.getURIElement();

		/**
		 * The meta object literal for the '<em><b>Uri</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute URI_ELEMENT__URI = eINSTANCE.getURIElement_Uri();

		/**
		 * The meta object literal for the '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.PropertyImpl <em>Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.PropertyImpl
		 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.CacheTracabilityPackageImpl#getProperty()
		 * @generated
		 */
		EClass PROPERTY = eINSTANCE.getProperty();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__NAME = eINSTANCE.getProperty_Name();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__VALUE = eINSTANCE.getProperty_Value();

	}

} //CacheTracabilityPackage

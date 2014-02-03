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
package org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
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
 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationFactory
 * @model kind="package"
 * @generated
 */
public interface TypeconfigurationPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "typeconfiguration";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.org.polarsys.reqcycle/typeconfiguration/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "typeconf";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TypeconfigurationPackage eINSTANCE = org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeConfigContainerImpl <em>Type Config Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeConfigContainerImpl
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getTypeConfigContainer()
	 * @generated
	 */
	int TYPE_CONFIG_CONTAINER = 0;

	/**
	 * The feature id for the '<em><b>Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_CONFIG_CONTAINER__TYPES = 0;

	/**
	 * The feature id for the '<em><b>Configurations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_CONFIG_CONTAINER__CONFIGURATIONS = 1;

	/**
	 * The feature id for the '<em><b>Default Configuration</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_CONFIG_CONTAINER__DEFAULT_CONFIGURATION = 2;

	/**
	 * The feature id for the '<em><b>Mappings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_CONFIG_CONTAINER__MAPPINGS = 3;

	/**
	 * The number of structural features of the '<em>Type Config Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_CONFIG_CONTAINER_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeImpl <em>Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeImpl
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getType()
	 * @generated
	 */
	int TYPE = 1;

	/**
	 * The feature id for the '<em><b>Type Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE__TYPE_ID = 0;

	/**
	 * The feature id for the '<em><b>Outgoings</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE__OUTGOINGS = 1;

	/**
	 * The feature id for the '<em><b>Incomings</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE__INCOMINGS = 2;

	/**
	 * The feature id for the '<em><b>Is Extensible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE__IS_EXTENSIBLE = 3;

	/**
	 * The number of structural features of the '<em>Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.RelationImpl <em>Relation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.RelationImpl
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getRelation()
	 * @generated
	 */
	int RELATION = 2;

	/**
	 * The feature id for the '<em><b>Upstream Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION__UPSTREAM_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Downstream Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION__DOWNSTREAM_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION__KIND = 2;

	/**
	 * The feature id for the '<em><b>Agregated Types</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION__AGREGATED_TYPES = 3;

	/**
	 * The feature id for the '<em><b>Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION__ICON = 4;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION__ATTRIBUTES = 5;

	/**
	 * The number of structural features of the '<em>Relation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.ConfigurationImpl <em>Configuration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.ConfigurationImpl
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getConfiguration()
	 * @generated
	 */
	int CONFIGURATION = 3;

	/**
	 * The feature id for the '<em><b>Relations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION__RELATIONS = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION__NAME = 1;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION__PARENT = 2;

	/**
	 * The number of structural features of the '<em>Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_FEATURE_COUNT = 3;


	/**
	 * The meta object id for the '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.CustomTypeImpl <em>Custom Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.CustomTypeImpl
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getCustomType()
	 * @generated
	 */
	int CUSTOM_TYPE = 4;

	/**
	 * The feature id for the '<em><b>Type Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_TYPE__TYPE_ID = TYPE__TYPE_ID;

	/**
	 * The feature id for the '<em><b>Outgoings</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_TYPE__OUTGOINGS = TYPE__OUTGOINGS;

	/**
	 * The feature id for the '<em><b>Incomings</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_TYPE__INCOMINGS = TYPE__INCOMINGS;

	/**
	 * The feature id for the '<em><b>Is Extensible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_TYPE__IS_EXTENSIBLE = TYPE__IS_EXTENSIBLE;

	/**
	 * The feature id for the '<em><b>Super Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_TYPE__SUPER_TYPE = TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Entries</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_TYPE__ENTRIES = TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Custom Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_TYPE_FEATURE_COUNT = TYPE_FEATURE_COUNT + 2;


	/**
	 * The meta object id for the '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.EntryImpl <em>Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.EntryImpl
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getEntry()
	 * @generated
	 */
	int ENTRY = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY__NAME = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_FEATURE_COUNT = 2;


	/**
	 * The meta object id for the '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.AttributeImpl <em>Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.AttributeImpl
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getAttribute()
	 * @generated
	 */
	int ATTRIBUTE = 6;

	/**
	 * The number of structural features of the '<em>Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.RegisteredAttributeImpl <em>Registered Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.RegisteredAttributeImpl
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getRegisteredAttribute()
	 * @generated
	 */
	int REGISTERED_ATTRIBUTE = 7;

	/**
	 * The meta object id for the '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.StdAttributeImpl <em>Std Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.StdAttributeImpl
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getStdAttribute()
	 * @generated
	 */
	int STD_ATTRIBUTE = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STD_ATTRIBUTE__NAME = ATTRIBUTE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Possible Values</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STD_ATTRIBUTE__POSSIBLE_VALUES = ATTRIBUTE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STD_ATTRIBUTE__TYPE = ATTRIBUTE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Std Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STD_ATTRIBUTE_FEATURE_COUNT = ATTRIBUTE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGISTERED_ATTRIBUTE__NAME = STD_ATTRIBUTE__NAME;

	/**
	 * The feature id for the '<em><b>Possible Values</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGISTERED_ATTRIBUTE__POSSIBLE_VALUES = STD_ATTRIBUTE__POSSIBLE_VALUES;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGISTERED_ATTRIBUTE__TYPE = STD_ATTRIBUTE__TYPE;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGISTERED_ATTRIBUTE__ID = STD_ATTRIBUTE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Registered Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGISTERED_ATTRIBUTE_FEATURE_COUNT = STD_ATTRIBUTE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.RelationsPredicatesMappingImpl <em>Relations Predicates Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.RelationsPredicatesMappingImpl
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getRelationsPredicatesMapping()
	 * @generated
	 */
	int RELATIONS_PREDICATES_MAPPING = 9;

	/**
	 * The feature id for the '<em><b>Relation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONS_PREDICATES_MAPPING__RELATION = 0;

	/**
	 * The feature id for the '<em><b>Decorations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONS_PREDICATES_MAPPING__DECORATIONS = 1;

	/**
	 * The number of structural features of the '<em>Relations Predicates Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONS_PREDICATES_MAPPING_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.IPredicateLinkImpl <em>IPredicate Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.IPredicateLinkImpl
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getIPredicateLink()
	 * @generated
	 */
	int IPREDICATE_LINK = 11;

	/**
	 * The feature id for the '<em><b>Predicate</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IPREDICATE_LINK__PREDICATE = 0;

	/**
	 * The number of structural features of the '<em>IPredicate Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IPREDICATE_LINK_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.DecorationPredicateImpl <em>Decoration Predicate</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.DecorationPredicateImpl
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getDecorationPredicate()
	 * @generated
	 */
	int DECORATION_PREDICATE = 10;

	/**
	 * The feature id for the '<em><b>Predicate</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECORATION_PREDICATE__PREDICATE = IPREDICATE_LINK__PREDICATE;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECORATION_PREDICATE__STYLE = IPREDICATE_LINK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECORATION_PREDICATE__COLOR = IPREDICATE_LINK_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Decoration Predicate</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECORATION_PREDICATE_FEATURE_COUNT = IPREDICATE_LINK_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.AttributeType <em>Attribute Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.AttributeType
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getAttributeType()
	 * @generated
	 */
	int ATTRIBUTE_TYPE = 12;

	/**
	 * The meta object id for the '<em>IType</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.reqcycle.types.IType
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getIType()
	 * @generated
	 */
	int ITYPE = 13;


	/**
	 * The meta object id for the '<em>TType</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.reqcycle.traceability.model.TType
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getTType()
	 * @generated
	 */
	int TTYPE = 14;


	/**
	 * Returns the meta object for class '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer <em>Type Config Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Config Container</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer
	 * @generated
	 */
	EClass getTypeConfigContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer#getTypes <em>Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Types</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer#getTypes()
	 * @see #getTypeConfigContainer()
	 * @generated
	 */
	EReference getTypeConfigContainer_Types();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer#getConfigurations <em>Configurations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Configurations</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer#getConfigurations()
	 * @see #getTypeConfigContainer()
	 * @generated
	 */
	EReference getTypeConfigContainer_Configurations();

	/**
	 * Returns the meta object for the reference '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer#getDefaultConfiguration <em>Default Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Default Configuration</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer#getDefaultConfiguration()
	 * @see #getTypeConfigContainer()
	 * @generated
	 */
	EReference getTypeConfigContainer_DefaultConfiguration();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer#getMappings <em>Mappings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Mappings</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer#getMappings()
	 * @see #getTypeConfigContainer()
	 * @generated
	 */
	EReference getTypeConfigContainer_Mappings();

	/**
	 * Returns the meta object for class '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Type <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Type
	 * @generated
	 */
	EClass getType();

	/**
	 * Returns the meta object for the attribute '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Type#getTypeId <em>Type Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type Id</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Type#getTypeId()
	 * @see #getType()
	 * @generated
	 */
	EAttribute getType_TypeId();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Type#getOutgoings <em>Outgoings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Outgoings</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Type#getOutgoings()
	 * @see #getType()
	 * @generated
	 */
	EReference getType_Outgoings();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Type#getIncomings <em>Incomings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Incomings</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Type#getIncomings()
	 * @see #getType()
	 * @generated
	 */
	EReference getType_Incomings();

	/**
	 * Returns the meta object for the attribute '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Type#isIsExtensible <em>Is Extensible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Extensible</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Type#isIsExtensible()
	 * @see #getType()
	 * @generated
	 */
	EAttribute getType_IsExtensible();

	/**
	 * Returns the meta object for class '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation <em>Relation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relation</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation
	 * @generated
	 */
	EClass getRelation();

	/**
	 * Returns the meta object for the reference '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getUpstreamType <em>Upstream Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Upstream Type</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getUpstreamType()
	 * @see #getRelation()
	 * @generated
	 */
	EReference getRelation_UpstreamType();

	/**
	 * Returns the meta object for the reference '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getDownstreamType <em>Downstream Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Downstream Type</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getDownstreamType()
	 * @see #getRelation()
	 * @generated
	 */
	EReference getRelation_DownstreamType();

	/**
	 * Returns the meta object for the attribute '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getKind()
	 * @see #getRelation()
	 * @generated
	 */
	EAttribute getRelation_Kind();

	/**
	 * Returns the meta object for the attribute list '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getAgregatedTypes <em>Agregated Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Agregated Types</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getAgregatedTypes()
	 * @see #getRelation()
	 * @generated
	 */
	EAttribute getRelation_AgregatedTypes();

	/**
	 * Returns the meta object for the attribute '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getIcon <em>Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Icon</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getIcon()
	 * @see #getRelation()
	 * @generated
	 */
	EAttribute getRelation_Icon();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getAttributes()
	 * @see #getRelation()
	 * @generated
	 */
	EReference getRelation_Attributes();

	/**
	 * Returns the meta object for class '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Configuration <em>Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Configuration</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Configuration
	 * @generated
	 */
	EClass getConfiguration();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Configuration#getRelations <em>Relations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Relations</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Configuration#getRelations()
	 * @see #getConfiguration()
	 * @generated
	 */
	EReference getConfiguration_Relations();

	/**
	 * Returns the meta object for the attribute '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Configuration#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Configuration#getName()
	 * @see #getConfiguration()
	 * @generated
	 */
	EAttribute getConfiguration_Name();

	/**
	 * Returns the meta object for the container reference '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Configuration#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Configuration#getParent()
	 * @see #getConfiguration()
	 * @generated
	 */
	EReference getConfiguration_Parent();

	/**
	 * Returns the meta object for class '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.CustomType <em>Custom Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Custom Type</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.CustomType
	 * @generated
	 */
	EClass getCustomType();

	/**
	 * Returns the meta object for the reference '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.CustomType#getSuperType <em>Super Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Super Type</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.CustomType#getSuperType()
	 * @see #getCustomType()
	 * @generated
	 */
	EReference getCustomType_SuperType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.CustomType#getEntries <em>Entries</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Entries</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.CustomType#getEntries()
	 * @see #getCustomType()
	 * @generated
	 */
	EReference getCustomType_Entries();

	/**
	 * Returns the meta object for class '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Entry <em>Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entry</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Entry
	 * @generated
	 */
	EClass getEntry();

	/**
	 * Returns the meta object for the attribute '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Entry#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Entry#getName()
	 * @see #getEntry()
	 * @generated
	 */
	EAttribute getEntry_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Entry#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Entry#getValue()
	 * @see #getEntry()
	 * @generated
	 */
	EAttribute getEntry_Value();

	/**
	 * Returns the meta object for class '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Attribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Attribute
	 * @generated
	 */
	EClass getAttribute();

	/**
	 * Returns the meta object for class '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.RegisteredAttribute <em>Registered Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Registered Attribute</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.RegisteredAttribute
	 * @generated
	 */
	EClass getRegisteredAttribute();

	/**
	 * Returns the meta object for the attribute '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.RegisteredAttribute#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.RegisteredAttribute#getId()
	 * @see #getRegisteredAttribute()
	 * @generated
	 */
	EAttribute getRegisteredAttribute_Id();

	/**
	 * Returns the meta object for class '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.StdAttribute <em>Std Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Std Attribute</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.StdAttribute
	 * @generated
	 */
	EClass getStdAttribute();

	/**
	 * Returns the meta object for the attribute '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.StdAttribute#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.StdAttribute#getName()
	 * @see #getStdAttribute()
	 * @generated
	 */
	EAttribute getStdAttribute_Name();

	/**
	 * Returns the meta object for the attribute list '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.StdAttribute#getPossibleValues <em>Possible Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Possible Values</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.StdAttribute#getPossibleValues()
	 * @see #getStdAttribute()
	 * @generated
	 */
	EAttribute getStdAttribute_PossibleValues();

	/**
	 * Returns the meta object for the attribute '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.StdAttribute#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.StdAttribute#getType()
	 * @see #getStdAttribute()
	 * @generated
	 */
	EAttribute getStdAttribute_Type();

	/**
	 * Returns the meta object for class '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.RelationsPredicatesMapping <em>Relations Predicates Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relations Predicates Mapping</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.RelationsPredicatesMapping
	 * @generated
	 */
	EClass getRelationsPredicatesMapping();

	/**
	 * Returns the meta object for the reference '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.RelationsPredicatesMapping#getRelation <em>Relation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Relation</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.RelationsPredicatesMapping#getRelation()
	 * @see #getRelationsPredicatesMapping()
	 * @generated
	 */
	EReference getRelationsPredicatesMapping_Relation();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.RelationsPredicatesMapping#getDecorations <em>Decorations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Decorations</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.RelationsPredicatesMapping#getDecorations()
	 * @see #getRelationsPredicatesMapping()
	 * @generated
	 */
	EReference getRelationsPredicatesMapping_Decorations();

	/**
	 * Returns the meta object for class '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.DecorationPredicate <em>Decoration Predicate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Decoration Predicate</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.DecorationPredicate
	 * @generated
	 */
	EClass getDecorationPredicate();

	/**
	 * Returns the meta object for the attribute '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.DecorationPredicate#getStyle <em>Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Style</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.DecorationPredicate#getStyle()
	 * @see #getDecorationPredicate()
	 * @generated
	 */
	EAttribute getDecorationPredicate_Style();

	/**
	 * Returns the meta object for the attribute '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.DecorationPredicate#getColor <em>Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Color</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.DecorationPredicate#getColor()
	 * @see #getDecorationPredicate()
	 * @generated
	 */
	EAttribute getDecorationPredicate_Color();

	/**
	 * Returns the meta object for class '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.IPredicateLink <em>IPredicate Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IPredicate Link</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.IPredicateLink
	 * @generated
	 */
	EClass getIPredicateLink();

	/**
	 * Returns the meta object for the reference '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.IPredicateLink#getPredicate <em>Predicate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Predicate</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.IPredicateLink#getPredicate()
	 * @see #getIPredicateLink()
	 * @generated
	 */
	EReference getIPredicateLink_Predicate();

	/**
	 * Returns the meta object for enum '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.AttributeType <em>Attribute Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Attribute Type</em>'.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.AttributeType
	 * @generated
	 */
	EEnum getAttributeType();

	/**
	 * Returns the meta object for data type '{@link org.polarsys.reqcycle.types.IType <em>IType</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>IType</em>'.
	 * @see org.polarsys.reqcycle.types.IType
	 * @model instanceClass="org.polarsys.reqcycle.types.IType"
	 * @generated
	 */
	EDataType getIType();

	/**
	 * Returns the meta object for data type '{@link org.polarsys.reqcycle.traceability.model.TType <em>TType</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>TType</em>'.
	 * @see org.polarsys.reqcycle.traceability.model.TType
	 * @model instanceClass="org.polarsys.reqcycle.traceability.model.TType"
	 * @generated
	 */
	EDataType getTType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TypeconfigurationFactory getTypeconfigurationFactory();

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
		 * The meta object literal for the '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeConfigContainerImpl <em>Type Config Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeConfigContainerImpl
		 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getTypeConfigContainer()
		 * @generated
		 */
		EClass TYPE_CONFIG_CONTAINER = eINSTANCE.getTypeConfigContainer();

		/**
		 * The meta object literal for the '<em><b>Types</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_CONFIG_CONTAINER__TYPES = eINSTANCE.getTypeConfigContainer_Types();

		/**
		 * The meta object literal for the '<em><b>Configurations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_CONFIG_CONTAINER__CONFIGURATIONS = eINSTANCE.getTypeConfigContainer_Configurations();

		/**
		 * The meta object literal for the '<em><b>Default Configuration</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_CONFIG_CONTAINER__DEFAULT_CONFIGURATION = eINSTANCE.getTypeConfigContainer_DefaultConfiguration();

		/**
		 * The meta object literal for the '<em><b>Mappings</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_CONFIG_CONTAINER__MAPPINGS = eINSTANCE.getTypeConfigContainer_Mappings();

		/**
		 * The meta object literal for the '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeImpl <em>Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeImpl
		 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getType()
		 * @generated
		 */
		EClass TYPE = eINSTANCE.getType();

		/**
		 * The meta object literal for the '<em><b>Type Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TYPE__TYPE_ID = eINSTANCE.getType_TypeId();

		/**
		 * The meta object literal for the '<em><b>Outgoings</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE__OUTGOINGS = eINSTANCE.getType_Outgoings();

		/**
		 * The meta object literal for the '<em><b>Incomings</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE__INCOMINGS = eINSTANCE.getType_Incomings();

		/**
		 * The meta object literal for the '<em><b>Is Extensible</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TYPE__IS_EXTENSIBLE = eINSTANCE.getType_IsExtensible();

		/**
		 * The meta object literal for the '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.RelationImpl <em>Relation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.RelationImpl
		 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getRelation()
		 * @generated
		 */
		EClass RELATION = eINSTANCE.getRelation();

		/**
		 * The meta object literal for the '<em><b>Upstream Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATION__UPSTREAM_TYPE = eINSTANCE.getRelation_UpstreamType();

		/**
		 * The meta object literal for the '<em><b>Downstream Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATION__DOWNSTREAM_TYPE = eINSTANCE.getRelation_DownstreamType();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELATION__KIND = eINSTANCE.getRelation_Kind();

		/**
		 * The meta object literal for the '<em><b>Agregated Types</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELATION__AGREGATED_TYPES = eINSTANCE.getRelation_AgregatedTypes();

		/**
		 * The meta object literal for the '<em><b>Icon</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELATION__ICON = eINSTANCE.getRelation_Icon();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATION__ATTRIBUTES = eINSTANCE.getRelation_Attributes();

		/**
		 * The meta object literal for the '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.ConfigurationImpl <em>Configuration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.ConfigurationImpl
		 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getConfiguration()
		 * @generated
		 */
		EClass CONFIGURATION = eINSTANCE.getConfiguration();

		/**
		 * The meta object literal for the '<em><b>Relations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONFIGURATION__RELATIONS = eINSTANCE.getConfiguration_Relations();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONFIGURATION__NAME = eINSTANCE.getConfiguration_Name();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONFIGURATION__PARENT = eINSTANCE.getConfiguration_Parent();

		/**
		 * The meta object literal for the '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.CustomTypeImpl <em>Custom Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.CustomTypeImpl
		 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getCustomType()
		 * @generated
		 */
		EClass CUSTOM_TYPE = eINSTANCE.getCustomType();

		/**
		 * The meta object literal for the '<em><b>Super Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CUSTOM_TYPE__SUPER_TYPE = eINSTANCE.getCustomType_SuperType();

		/**
		 * The meta object literal for the '<em><b>Entries</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CUSTOM_TYPE__ENTRIES = eINSTANCE.getCustomType_Entries();

		/**
		 * The meta object literal for the '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.EntryImpl <em>Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.EntryImpl
		 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getEntry()
		 * @generated
		 */
		EClass ENTRY = eINSTANCE.getEntry();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTRY__NAME = eINSTANCE.getEntry_Name();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTRY__VALUE = eINSTANCE.getEntry_Value();

		/**
		 * The meta object literal for the '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.AttributeImpl <em>Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.AttributeImpl
		 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getAttribute()
		 * @generated
		 */
		EClass ATTRIBUTE = eINSTANCE.getAttribute();

		/**
		 * The meta object literal for the '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.RegisteredAttributeImpl <em>Registered Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.RegisteredAttributeImpl
		 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getRegisteredAttribute()
		 * @generated
		 */
		EClass REGISTERED_ATTRIBUTE = eINSTANCE.getRegisteredAttribute();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REGISTERED_ATTRIBUTE__ID = eINSTANCE.getRegisteredAttribute_Id();

		/**
		 * The meta object literal for the '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.StdAttributeImpl <em>Std Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.StdAttributeImpl
		 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getStdAttribute()
		 * @generated
		 */
		EClass STD_ATTRIBUTE = eINSTANCE.getStdAttribute();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STD_ATTRIBUTE__NAME = eINSTANCE.getStdAttribute_Name();

		/**
		 * The meta object literal for the '<em><b>Possible Values</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STD_ATTRIBUTE__POSSIBLE_VALUES = eINSTANCE.getStdAttribute_PossibleValues();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STD_ATTRIBUTE__TYPE = eINSTANCE.getStdAttribute_Type();

		/**
		 * The meta object literal for the '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.RelationsPredicatesMappingImpl <em>Relations Predicates Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.RelationsPredicatesMappingImpl
		 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getRelationsPredicatesMapping()
		 * @generated
		 */
		EClass RELATIONS_PREDICATES_MAPPING = eINSTANCE.getRelationsPredicatesMapping();

		/**
		 * The meta object literal for the '<em><b>Relation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATIONS_PREDICATES_MAPPING__RELATION = eINSTANCE.getRelationsPredicatesMapping_Relation();

		/**
		 * The meta object literal for the '<em><b>Decorations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATIONS_PREDICATES_MAPPING__DECORATIONS = eINSTANCE.getRelationsPredicatesMapping_Decorations();

		/**
		 * The meta object literal for the '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.DecorationPredicateImpl <em>Decoration Predicate</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.DecorationPredicateImpl
		 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getDecorationPredicate()
		 * @generated
		 */
		EClass DECORATION_PREDICATE = eINSTANCE.getDecorationPredicate();

		/**
		 * The meta object literal for the '<em><b>Style</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DECORATION_PREDICATE__STYLE = eINSTANCE.getDecorationPredicate_Style();

		/**
		 * The meta object literal for the '<em><b>Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DECORATION_PREDICATE__COLOR = eINSTANCE.getDecorationPredicate_Color();

		/**
		 * The meta object literal for the '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.IPredicateLinkImpl <em>IPredicate Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.IPredicateLinkImpl
		 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getIPredicateLink()
		 * @generated
		 */
		EClass IPREDICATE_LINK = eINSTANCE.getIPredicateLink();

		/**
		 * The meta object literal for the '<em><b>Predicate</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IPREDICATE_LINK__PREDICATE = eINSTANCE.getIPredicateLink_Predicate();

		/**
		 * The meta object literal for the '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.AttributeType <em>Attribute Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.AttributeType
		 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getAttributeType()
		 * @generated
		 */
		EEnum ATTRIBUTE_TYPE = eINSTANCE.getAttributeType();

		/**
		 * The meta object literal for the '<em>IType</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.reqcycle.types.IType
		 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getIType()
		 * @generated
		 */
		EDataType ITYPE = eINSTANCE.getIType();

		/**
		 * The meta object literal for the '<em>TType</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.reqcycle.traceability.model.TType
		 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getTType()
		 * @generated
		 */
		EDataType TTYPE = eINSTANCE.getTType();

	}

} //TypeconfigurationPackage

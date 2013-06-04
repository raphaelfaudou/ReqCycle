/**
 */
package org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationFactory
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
	String eNS_URI = "http://www.org.eclipse.reqcycle/typeconfiguration/1.0";

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
	TypeconfigurationPackage eINSTANCE = org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeConfigContainerImpl <em>Type Config Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeConfigContainerImpl
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getTypeConfigContainer()
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
	 * The number of structural features of the '<em>Type Config Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_CONFIG_CONTAINER_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeImpl <em>Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeImpl
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getType()
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
	 * The meta object id for the '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.RelationImpl <em>Relation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.RelationImpl
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getRelation()
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
	 * The number of structural features of the '<em>Relation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.ConfigurationImpl <em>Configuration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.ConfigurationImpl
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getConfiguration()
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
	 * The meta object id for the '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.CustomTypeImpl <em>Custom Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.CustomTypeImpl
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getCustomType()
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
	 * The meta object id for the '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.EntryImpl <em>Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.EntryImpl
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getEntry()
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
	 * The meta object id for the '<em>IType</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.reqcycle.types.IType
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getIType()
	 * @generated
	 */
	int ITYPE = 6;


	/**
	 * Returns the meta object for class '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer <em>Type Config Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Config Container</em>'.
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer
	 * @generated
	 */
	EClass getTypeConfigContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer#getTypes <em>Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Types</em>'.
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer#getTypes()
	 * @see #getTypeConfigContainer()
	 * @generated
	 */
	EReference getTypeConfigContainer_Types();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer#getConfigurations <em>Configurations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Configurations</em>'.
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer#getConfigurations()
	 * @see #getTypeConfigContainer()
	 * @generated
	 */
	EReference getTypeConfigContainer_Configurations();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer#getDefaultConfiguration <em>Default Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Default Configuration</em>'.
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer#getDefaultConfiguration()
	 * @see #getTypeConfigContainer()
	 * @generated
	 */
	EReference getTypeConfigContainer_DefaultConfiguration();

	/**
	 * Returns the meta object for class '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Type <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type</em>'.
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Type
	 * @generated
	 */
	EClass getType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Type#getTypeId <em>Type Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type Id</em>'.
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Type#getTypeId()
	 * @see #getType()
	 * @generated
	 */
	EAttribute getType_TypeId();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Type#getOutgoings <em>Outgoings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Outgoings</em>'.
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Type#getOutgoings()
	 * @see #getType()
	 * @generated
	 */
	EReference getType_Outgoings();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Type#getIncomings <em>Incomings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Incomings</em>'.
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Type#getIncomings()
	 * @see #getType()
	 * @generated
	 */
	EReference getType_Incomings();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Type#isIsExtensible <em>Is Extensible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Extensible</em>'.
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Type#isIsExtensible()
	 * @see #getType()
	 * @generated
	 */
	EAttribute getType_IsExtensible();

	/**
	 * Returns the meta object for class '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Relation <em>Relation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relation</em>'.
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Relation
	 * @generated
	 */
	EClass getRelation();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getUpstreamType <em>Upstream Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Upstream Type</em>'.
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getUpstreamType()
	 * @see #getRelation()
	 * @generated
	 */
	EReference getRelation_UpstreamType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getDownstreamType <em>Downstream Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Downstream Type</em>'.
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getDownstreamType()
	 * @see #getRelation()
	 * @generated
	 */
	EReference getRelation_DownstreamType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Relation#getKind()
	 * @see #getRelation()
	 * @generated
	 */
	EAttribute getRelation_Kind();

	/**
	 * Returns the meta object for class '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Configuration <em>Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Configuration</em>'.
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Configuration
	 * @generated
	 */
	EClass getConfiguration();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Configuration#getRelations <em>Relations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Relations</em>'.
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Configuration#getRelations()
	 * @see #getConfiguration()
	 * @generated
	 */
	EReference getConfiguration_Relations();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Configuration#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Configuration#getName()
	 * @see #getConfiguration()
	 * @generated
	 */
	EAttribute getConfiguration_Name();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Configuration#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent</em>'.
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Configuration#getParent()
	 * @see #getConfiguration()
	 * @generated
	 */
	EReference getConfiguration_Parent();

	/**
	 * Returns the meta object for class '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.CustomType <em>Custom Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Custom Type</em>'.
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.CustomType
	 * @generated
	 */
	EClass getCustomType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.CustomType#getSuperType <em>Super Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Super Type</em>'.
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.CustomType#getSuperType()
	 * @see #getCustomType()
	 * @generated
	 */
	EReference getCustomType_SuperType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.CustomType#getEntries <em>Entries</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Entries</em>'.
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.CustomType#getEntries()
	 * @see #getCustomType()
	 * @generated
	 */
	EReference getCustomType_Entries();

	/**
	 * Returns the meta object for class '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Entry <em>Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entry</em>'.
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Entry
	 * @generated
	 */
	EClass getEntry();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Entry#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Entry#getName()
	 * @see #getEntry()
	 * @generated
	 */
	EAttribute getEntry_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Entry#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Entry#getValue()
	 * @see #getEntry()
	 * @generated
	 */
	EAttribute getEntry_Value();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.reqcycle.types.IType <em>IType</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>IType</em>'.
	 * @see org.eclipse.reqcycle.types.IType
	 * @model instanceClass="org.eclipse.reqcycle.types.IType"
	 * @generated
	 */
	EDataType getIType();

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
		 * The meta object literal for the '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeConfigContainerImpl <em>Type Config Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeConfigContainerImpl
		 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getTypeConfigContainer()
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
		 * The meta object literal for the '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeImpl <em>Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeImpl
		 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getType()
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
		 * The meta object literal for the '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.RelationImpl <em>Relation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.RelationImpl
		 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getRelation()
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
		 * The meta object literal for the '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.ConfigurationImpl <em>Configuration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.ConfigurationImpl
		 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getConfiguration()
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
		 * The meta object literal for the '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.CustomTypeImpl <em>Custom Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.CustomTypeImpl
		 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getCustomType()
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
		 * The meta object literal for the '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.EntryImpl <em>Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.EntryImpl
		 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getEntry()
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
		 * The meta object literal for the '<em>IType</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.reqcycle.types.IType
		 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeconfigurationPackageImpl#getIType()
		 * @generated
		 */
		EDataType ITYPE = eINSTANCE.getIType();

	}

} //TypeconfigurationPackage

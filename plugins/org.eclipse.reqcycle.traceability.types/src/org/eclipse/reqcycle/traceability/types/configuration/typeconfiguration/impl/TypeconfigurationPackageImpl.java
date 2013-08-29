/**
 */
package org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.reqcycle.predicates.core.PredicatesPackage;
import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Attribute;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.AttributeType;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Configuration;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.CustomType;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.DecorationPredicate;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Entry;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.IPredicateLink;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.RegisteredAttribute;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Relation;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.RelationsPredicatesMapping;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.SelectionPredicate;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.StdAttribute;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Type;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationFactory;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationPackage;
import org.eclipse.reqcycle.types.IType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TypeconfigurationPackageImpl extends EPackageImpl implements TypeconfigurationPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeConfigContainerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass relationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass configurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass customTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass entryEClass = null;

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
	private EClass registeredAttributeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stdAttributeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass relationsPredicatesMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass decorationPredicateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iPredicateLinkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum attributeTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType iTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType tTypeEDataType = null;

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
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private TypeconfigurationPackageImpl() {
		super(eNS_URI, TypeconfigurationFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link TypeconfigurationPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static TypeconfigurationPackage init() {
		if (isInited) return (TypeconfigurationPackage)EPackage.Registry.INSTANCE.getEPackage(TypeconfigurationPackage.eNS_URI);

		// Obtain or create and register package
		TypeconfigurationPackageImpl theTypeconfigurationPackage = (TypeconfigurationPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof TypeconfigurationPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new TypeconfigurationPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		PredicatesPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theTypeconfigurationPackage.createPackageContents();

		// Initialize created meta-data
		theTypeconfigurationPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theTypeconfigurationPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(TypeconfigurationPackage.eNS_URI, theTypeconfigurationPackage);
		return theTypeconfigurationPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTypeConfigContainer() {
		return typeConfigContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTypeConfigContainer_Types() {
		return (EReference)typeConfigContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTypeConfigContainer_Configurations() {
		return (EReference)typeConfigContainerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTypeConfigContainer_DefaultConfiguration() {
		return (EReference)typeConfigContainerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTypeConfigContainer_Mappings() {
		return (EReference)typeConfigContainerEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getType() {
		return typeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getType_TypeId() {
		return (EAttribute)typeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getType_Outgoings() {
		return (EReference)typeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getType_Incomings() {
		return (EReference)typeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getType_IsExtensible() {
		return (EAttribute)typeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRelation() {
		return relationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRelation_UpstreamType() {
		return (EReference)relationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRelation_DownstreamType() {
		return (EReference)relationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRelation_Kind() {
		return (EAttribute)relationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRelation_AgregatedTypes() {
		return (EAttribute)relationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRelation_Icon() {
		return (EAttribute)relationEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRelation_Attributes() {
		return (EReference)relationEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConfiguration() {
		return configurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConfiguration_Relations() {
		return (EReference)configurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConfiguration_Name() {
		return (EAttribute)configurationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConfiguration_Parent() {
		return (EReference)configurationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCustomType() {
		return customTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCustomType_SuperType() {
		return (EReference)customTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCustomType_Entries() {
		return (EReference)customTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEntry() {
		return entryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntry_Name() {
		return (EAttribute)entryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntry_Value() {
		return (EAttribute)entryEClass.getEStructuralFeatures().get(1);
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
	public EClass getRegisteredAttribute() {
		return registeredAttributeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRegisteredAttribute_Id() {
		return (EAttribute)registeredAttributeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStdAttribute() {
		return stdAttributeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStdAttribute_Name() {
		return (EAttribute)stdAttributeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStdAttribute_PossibleValues() {
		return (EAttribute)stdAttributeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStdAttribute_Type() {
		return (EAttribute)stdAttributeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRelationsPredicatesMapping() {
		return relationsPredicatesMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRelationsPredicatesMapping_Relation() {
		return (EReference)relationsPredicatesMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRelationsPredicatesMapping_Decorations() {
		return (EReference)relationsPredicatesMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDecorationPredicate() {
		return decorationPredicateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDecorationPredicate_Style() {
		return (EAttribute)decorationPredicateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDecorationPredicate_Color() {
		return (EAttribute)decorationPredicateEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIPredicateLink() {
		return iPredicateLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIPredicateLink_Predicate() {
		return (EReference)iPredicateLinkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getAttributeType() {
		return attributeTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getIType() {
		return iTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTType() {
		return tTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeconfigurationFactory getTypeconfigurationFactory() {
		return (TypeconfigurationFactory)getEFactoryInstance();
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
		typeConfigContainerEClass = createEClass(TYPE_CONFIG_CONTAINER);
		createEReference(typeConfigContainerEClass, TYPE_CONFIG_CONTAINER__TYPES);
		createEReference(typeConfigContainerEClass, TYPE_CONFIG_CONTAINER__CONFIGURATIONS);
		createEReference(typeConfigContainerEClass, TYPE_CONFIG_CONTAINER__DEFAULT_CONFIGURATION);
		createEReference(typeConfigContainerEClass, TYPE_CONFIG_CONTAINER__MAPPINGS);

		typeEClass = createEClass(TYPE);
		createEAttribute(typeEClass, TYPE__TYPE_ID);
		createEReference(typeEClass, TYPE__OUTGOINGS);
		createEReference(typeEClass, TYPE__INCOMINGS);
		createEAttribute(typeEClass, TYPE__IS_EXTENSIBLE);

		relationEClass = createEClass(RELATION);
		createEReference(relationEClass, RELATION__UPSTREAM_TYPE);
		createEReference(relationEClass, RELATION__DOWNSTREAM_TYPE);
		createEAttribute(relationEClass, RELATION__KIND);
		createEAttribute(relationEClass, RELATION__AGREGATED_TYPES);
		createEAttribute(relationEClass, RELATION__ICON);
		createEReference(relationEClass, RELATION__ATTRIBUTES);

		configurationEClass = createEClass(CONFIGURATION);
		createEReference(configurationEClass, CONFIGURATION__RELATIONS);
		createEAttribute(configurationEClass, CONFIGURATION__NAME);
		createEReference(configurationEClass, CONFIGURATION__PARENT);

		customTypeEClass = createEClass(CUSTOM_TYPE);
		createEReference(customTypeEClass, CUSTOM_TYPE__SUPER_TYPE);
		createEReference(customTypeEClass, CUSTOM_TYPE__ENTRIES);

		entryEClass = createEClass(ENTRY);
		createEAttribute(entryEClass, ENTRY__NAME);
		createEAttribute(entryEClass, ENTRY__VALUE);

		attributeEClass = createEClass(ATTRIBUTE);

		registeredAttributeEClass = createEClass(REGISTERED_ATTRIBUTE);
		createEAttribute(registeredAttributeEClass, REGISTERED_ATTRIBUTE__ID);

		stdAttributeEClass = createEClass(STD_ATTRIBUTE);
		createEAttribute(stdAttributeEClass, STD_ATTRIBUTE__NAME);
		createEAttribute(stdAttributeEClass, STD_ATTRIBUTE__POSSIBLE_VALUES);
		createEAttribute(stdAttributeEClass, STD_ATTRIBUTE__TYPE);

		relationsPredicatesMappingEClass = createEClass(RELATIONS_PREDICATES_MAPPING);
		createEReference(relationsPredicatesMappingEClass, RELATIONS_PREDICATES_MAPPING__RELATION);
		createEReference(relationsPredicatesMappingEClass, RELATIONS_PREDICATES_MAPPING__DECORATIONS);

		decorationPredicateEClass = createEClass(DECORATION_PREDICATE);
		createEAttribute(decorationPredicateEClass, DECORATION_PREDICATE__STYLE);
		createEAttribute(decorationPredicateEClass, DECORATION_PREDICATE__COLOR);

		iPredicateLinkEClass = createEClass(IPREDICATE_LINK);
		createEReference(iPredicateLinkEClass, IPREDICATE_LINK__PREDICATE);

		// Create enums
		attributeTypeEEnum = createEEnum(ATTRIBUTE_TYPE);

		// Create data types
		iTypeEDataType = createEDataType(ITYPE);
		tTypeEDataType = createEDataType(TTYPE);
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

		// Obtain other dependent packages
		PredicatesPackage thePredicatesPackage = (PredicatesPackage)EPackage.Registry.INSTANCE.getEPackage(PredicatesPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		customTypeEClass.getESuperTypes().add(this.getType());
		registeredAttributeEClass.getESuperTypes().add(this.getStdAttribute());
		stdAttributeEClass.getESuperTypes().add(this.getAttribute());
		decorationPredicateEClass.getESuperTypes().add(this.getIPredicateLink());

		// Initialize classes and features; add operations and parameters
		initEClass(typeConfigContainerEClass, TypeConfigContainer.class, "TypeConfigContainer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTypeConfigContainer_Types(), this.getType(), null, "types", null, 0, -1, TypeConfigContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTypeConfigContainer_Configurations(), this.getConfiguration(), this.getConfiguration_Parent(), "configurations", null, 0, -1, TypeConfigContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTypeConfigContainer_DefaultConfiguration(), this.getConfiguration(), null, "defaultConfiguration", null, 0, 1, TypeConfigContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTypeConfigContainer_Mappings(), this.getRelationsPredicatesMapping(), null, "mappings", null, 0, -1, TypeConfigContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(typeEClass, Type.class, "Type", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getType_TypeId(), ecorePackage.getEString(), "typeId", null, 0, 1, Type.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getType_Outgoings(), this.getRelation(), this.getRelation_UpstreamType(), "outgoings", null, 0, -1, Type.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getType_Incomings(), this.getRelation(), this.getRelation_DownstreamType(), "incomings", null, 0, -1, Type.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getType_IsExtensible(), ecorePackage.getEBoolean(), "isExtensible", null, 0, 1, Type.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(typeEClass, this.getIType(), "getIType", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(relationEClass, Relation.class, "Relation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRelation_UpstreamType(), this.getType(), this.getType_Outgoings(), "upstreamType", null, 0, 1, Relation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRelation_DownstreamType(), this.getType(), this.getType_Incomings(), "downstreamType", null, 0, 1, Relation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelation_Kind(), ecorePackage.getEString(), "kind", null, 0, 1, Relation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelation_AgregatedTypes(), ecorePackage.getEString(), "agregatedTypes", null, 0, -1, Relation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelation_Icon(), ecorePackage.getEString(), "icon", null, 0, 1, Relation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRelation_Attributes(), this.getAttribute(), null, "attributes", null, 0, -1, Relation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(relationEClass, this.getTType(), "getAgregated", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(relationEClass, this.getTType(), "getTType", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(configurationEClass, Configuration.class, "Configuration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConfiguration_Relations(), this.getRelation(), null, "relations", null, 0, -1, Configuration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConfiguration_Name(), ecorePackage.getEString(), "name", null, 0, 1, Configuration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConfiguration_Parent(), this.getTypeConfigContainer(), this.getTypeConfigContainer_Configurations(), "parent", null, 0, 1, Configuration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(customTypeEClass, CustomType.class, "CustomType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCustomType_SuperType(), this.getType(), null, "superType", null, 0, 1, CustomType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCustomType_Entries(), this.getEntry(), null, "entries", null, 0, -1, CustomType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(entryEClass, Entry.class, "Entry", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEntry_Name(), ecorePackage.getEString(), "name", null, 0, 1, Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEntry_Value(), ecorePackage.getEJavaObject(), "value", null, 0, 1, Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(attributeEClass, Attribute.class, "Attribute", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(attributeEClass, ecorePackage.getEString(), "getName", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(attributeEClass, ecorePackage.getEString(), "getPossibleValues", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(attributeEClass, this.getAttributeType(), "getType", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(registeredAttributeEClass, RegisteredAttribute.class, "RegisteredAttribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRegisteredAttribute_Id(), ecorePackage.getEString(), "id", null, 0, 1, RegisteredAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stdAttributeEClass, StdAttribute.class, "StdAttribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStdAttribute_Name(), ecorePackage.getEString(), "name", null, 0, 1, StdAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStdAttribute_PossibleValues(), ecorePackage.getEString(), "possibleValues", null, 0, -1, StdAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStdAttribute_Type(), this.getAttributeType(), "type", null, 0, 1, StdAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(relationsPredicatesMappingEClass, RelationsPredicatesMapping.class, "RelationsPredicatesMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRelationsPredicatesMapping_Relation(), this.getRelation(), null, "relation", null, 0, 1, RelationsPredicatesMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRelationsPredicatesMapping_Decorations(), this.getDecorationPredicate(), null, "decorations", null, 0, -1, RelationsPredicatesMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(decorationPredicateEClass, DecorationPredicate.class, "DecorationPredicate", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDecorationPredicate_Style(), ecorePackage.getEString(), "style", null, 0, 1, DecorationPredicate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDecorationPredicate_Color(), ecorePackage.getEString(), "color", null, 0, 1, DecorationPredicate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iPredicateLinkEClass, IPredicateLink.class, "IPredicateLink", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIPredicateLink_Predicate(), thePredicatesPackage.getIPredicate(), null, "predicate", null, 0, 1, IPredicateLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(attributeTypeEEnum, AttributeType.class, "AttributeType");
		addEEnumLiteral(attributeTypeEEnum, AttributeType.STRING);
		addEEnumLiteral(attributeTypeEEnum, AttributeType.BOOLEAN);
		addEEnumLiteral(attributeTypeEEnum, AttributeType.INT);

		// Initialize data types
		initEDataType(iTypeEDataType, IType.class, "IType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(tTypeEDataType, TType.class, "TType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //TypeconfigurationPackageImpl

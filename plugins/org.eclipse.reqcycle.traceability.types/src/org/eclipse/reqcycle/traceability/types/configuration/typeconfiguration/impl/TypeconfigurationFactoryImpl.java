/**
 */
package org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.*;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Configuration;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.CustomType;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Entry;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Relation;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Type;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationFactory;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationPackage;
import org.eclipse.reqcycle.types.IType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TypeconfigurationFactoryImpl extends EFactoryImpl implements TypeconfigurationFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TypeconfigurationFactory init() {
		try {
			TypeconfigurationFactory theTypeconfigurationFactory = (TypeconfigurationFactory)EPackage.Registry.INSTANCE.getEFactory(TypeconfigurationPackage.eNS_URI);
			if (theTypeconfigurationFactory != null) {
				return theTypeconfigurationFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new TypeconfigurationFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeconfigurationFactoryImpl() {
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
			case TypeconfigurationPackage.TYPE_CONFIG_CONTAINER: return createTypeConfigContainer();
			case TypeconfigurationPackage.TYPE: return createType();
			case TypeconfigurationPackage.RELATION: return createRelation();
			case TypeconfigurationPackage.CONFIGURATION: return createConfiguration();
			case TypeconfigurationPackage.CUSTOM_TYPE: return createCustomType();
			case TypeconfigurationPackage.ENTRY: return createEntry();
			case TypeconfigurationPackage.REGISTERED_ATTRIBUTE: return createRegisteredAttribute();
			case TypeconfigurationPackage.STD_ATTRIBUTE: return createStdAttribute();
			case TypeconfigurationPackage.RELATIONS_PREDICATES_MAPPING: return createRelationsPredicatesMapping();
			case TypeconfigurationPackage.DECORATION_PREDICATE: return createDecorationPredicate();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case TypeconfigurationPackage.ATTRIBUTE_TYPE:
				return createAttributeTypeFromString(eDataType, initialValue);
			case TypeconfigurationPackage.ITYPE:
				return createITypeFromString(eDataType, initialValue);
			case TypeconfigurationPackage.TTYPE:
				return createTTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case TypeconfigurationPackage.ATTRIBUTE_TYPE:
				return convertAttributeTypeToString(eDataType, instanceValue);
			case TypeconfigurationPackage.ITYPE:
				return convertITypeToString(eDataType, instanceValue);
			case TypeconfigurationPackage.TTYPE:
				return convertTTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeConfigContainer createTypeConfigContainer() {
		TypeConfigContainerImpl typeConfigContainer = new TypeConfigContainerImpl();
		return typeConfigContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type createType() {
		TypeImpl type = new TypeImpl();
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Relation createRelation() {
		RelationImpl relation = new RelationImpl();
		return relation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Configuration createConfiguration() {
		ConfigurationImpl configuration = new ConfigurationImpl();
		return configuration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CustomType createCustomType() {
		CustomTypeImpl customType = new CustomTypeImpl();
		return customType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Entry createEntry() {
		EntryImpl entry = new EntryImpl();
		return entry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RegisteredAttribute createRegisteredAttribute() {
		RegisteredAttributeImpl registeredAttribute = new RegisteredAttributeImpl();
		return registeredAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StdAttribute createStdAttribute() {
		StdAttributeImpl stdAttribute = new StdAttributeImpl();
		return stdAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelationsPredicatesMapping createRelationsPredicatesMapping() {
		RelationsPredicatesMappingImpl relationsPredicatesMapping = new RelationsPredicatesMappingImpl();
		return relationsPredicatesMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DecorationPredicate createDecorationPredicate() {
		DecorationPredicateImpl decorationPredicate = new DecorationPredicateImpl();
		return decorationPredicate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AttributeType createAttributeTypeFromString(EDataType eDataType, String initialValue) {
		AttributeType result = AttributeType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertAttributeTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IType createITypeFromString(EDataType eDataType, String initialValue) {
		return (IType)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertITypeToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TType createTTypeFromString(EDataType eDataType, String initialValue) {
		return (TType)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTTypeToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeconfigurationPackage getTypeconfigurationPackage() {
		return (TypeconfigurationPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static TypeconfigurationPackage getPackage() {
		return TypeconfigurationPackage.eINSTANCE;
	}

} //TypeconfigurationFactoryImpl

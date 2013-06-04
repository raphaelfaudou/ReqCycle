/**
 */
package CustomDataModel;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;

import DataModel.DataModelPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see CustomDataModel.CustomDataModelFactory
 * @model kind="package"
 * @generated
 */
public interface CustomDataModelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "CustomDataModel";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/ReqCycle/CustomDataModel";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "CustomDataModel";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CustomDataModelPackage eINSTANCE = CustomDataModel.impl.CustomDataModelPackageImpl.init();

	/**
	 * The meta object id for the '{@link CustomDataModel.impl.SpecificationTypeImpl <em>Specification Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see CustomDataModel.impl.SpecificationTypeImpl
	 * @see CustomDataModel.impl.CustomDataModelPackageImpl#getSpecificationType()
	 * @generated
	 */
	int SPECIFICATION_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFICATION_TYPE__ID = DataModelPackage.REACHABLE_SECTION__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFICATION_TYPE__NAME = DataModelPackage.REACHABLE_SECTION__NAME;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFICATION_TYPE__URI = DataModelPackage.REACHABLE_SECTION__URI;

	/**
	 * The feature id for the '<em><b>Scopes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFICATION_TYPE__SCOPES = DataModelPackage.REACHABLE_SECTION__SCOPES;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFICATION_TYPE__CHILDREN = DataModelPackage.REACHABLE_SECTION__CHILDREN;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFICATION_TYPE__DESCRIPTION = DataModelPackage.REACHABLE_SECTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>String Attribute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFICATION_TYPE__STRING_ATTRIBUTE = DataModelPackage.REACHABLE_SECTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Boolean Attribute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFICATION_TYPE__BOOLEAN_ATTRIBUTE = DataModelPackage.REACHABLE_SECTION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Integer Attribute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFICATION_TYPE__INTEGER_ATTRIBUTE = DataModelPackage.REACHABLE_SECTION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Specification Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFICATION_TYPE_FEATURE_COUNT = DataModelPackage.REACHABLE_SECTION_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Specification Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFICATION_TYPE_OPERATION_COUNT = DataModelPackage.REACHABLE_SECTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link CustomDataModel.impl.RequirementTypeImpl <em>Requirement Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see CustomDataModel.impl.RequirementTypeImpl
	 * @see CustomDataModel.impl.CustomDataModelPackageImpl#getRequirementType()
	 * @generated
	 */
	int REQUIREMENT_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_TYPE__ID = DataModelPackage.REQUIREMENT_SECTION__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_TYPE__NAME = DataModelPackage.REQUIREMENT_SECTION__NAME;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_TYPE__URI = DataModelPackage.REQUIREMENT_SECTION__URI;

	/**
	 * The feature id for the '<em><b>Scopes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_TYPE__SCOPES = DataModelPackage.REQUIREMENT_SECTION__SCOPES;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_TYPE__CHILDREN = DataModelPackage.REQUIREMENT_SECTION__CHILDREN;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_TYPE__DESCRIPTION = DataModelPackage.REQUIREMENT_SECTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>String Attribute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_TYPE__STRING_ATTRIBUTE = DataModelPackage.REQUIREMENT_SECTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Boolean Attribute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_TYPE__BOOLEAN_ATTRIBUTE = DataModelPackage.REQUIREMENT_SECTION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Integer Attribute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_TYPE__INTEGER_ATTRIBUTE = DataModelPackage.REQUIREMENT_SECTION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Enumeration Attribute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_TYPE__ENUMERATION_ATTRIBUTE = DataModelPackage.REQUIREMENT_SECTION_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Requirement Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_TYPE_FEATURE_COUNT = DataModelPackage.REQUIREMENT_SECTION_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>Requirement Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_TYPE_OPERATION_COUNT = DataModelPackage.REQUIREMENT_SECTION_OPERATION_COUNT + 0;


	/**
	 * The meta object id for the '{@link CustomDataModel.Enumeration <em>Enumeration</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see CustomDataModel.Enumeration
	 * @see CustomDataModel.impl.CustomDataModelPackageImpl#getEnumeration()
	 * @generated
	 */
	int ENUMERATION = 2;


	/**
	 * Returns the meta object for class '{@link CustomDataModel.SpecificationType <em>Specification Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Specification Type</em>'.
	 * @see CustomDataModel.SpecificationType
	 * @generated
	 */
	EClass getSpecificationType();

	/**
	 * Returns the meta object for the attribute '{@link CustomDataModel.SpecificationType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see CustomDataModel.SpecificationType#getDescription()
	 * @see #getSpecificationType()
	 * @generated
	 */
	EAttribute getSpecificationType_Description();

	/**
	 * Returns the meta object for the attribute '{@link CustomDataModel.SpecificationType#getString_Attribute <em>String Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>String Attribute</em>'.
	 * @see CustomDataModel.SpecificationType#getString_Attribute()
	 * @see #getSpecificationType()
	 * @generated
	 */
	EAttribute getSpecificationType_String_Attribute();

	/**
	 * Returns the meta object for the attribute '{@link CustomDataModel.SpecificationType#isBoolean_Attribute <em>Boolean Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Boolean Attribute</em>'.
	 * @see CustomDataModel.SpecificationType#isBoolean_Attribute()
	 * @see #getSpecificationType()
	 * @generated
	 */
	EAttribute getSpecificationType_Boolean_Attribute();

	/**
	 * Returns the meta object for the attribute '{@link CustomDataModel.SpecificationType#getInteger_Attribute <em>Integer Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Integer Attribute</em>'.
	 * @see CustomDataModel.SpecificationType#getInteger_Attribute()
	 * @see #getSpecificationType()
	 * @generated
	 */
	EAttribute getSpecificationType_Integer_Attribute();

	/**
	 * Returns the meta object for class '{@link CustomDataModel.RequirementType <em>Requirement Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Requirement Type</em>'.
	 * @see CustomDataModel.RequirementType
	 * @generated
	 */
	EClass getRequirementType();

	/**
	 * Returns the meta object for the attribute '{@link CustomDataModel.RequirementType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see CustomDataModel.RequirementType#getDescription()
	 * @see #getRequirementType()
	 * @generated
	 */
	EAttribute getRequirementType_Description();

	/**
	 * Returns the meta object for the attribute '{@link CustomDataModel.RequirementType#getString_Attribute <em>String Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>String Attribute</em>'.
	 * @see CustomDataModel.RequirementType#getString_Attribute()
	 * @see #getRequirementType()
	 * @generated
	 */
	EAttribute getRequirementType_String_Attribute();

	/**
	 * Returns the meta object for the attribute '{@link CustomDataModel.RequirementType#isBoolean_Attribute <em>Boolean Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Boolean Attribute</em>'.
	 * @see CustomDataModel.RequirementType#isBoolean_Attribute()
	 * @see #getRequirementType()
	 * @generated
	 */
	EAttribute getRequirementType_Boolean_Attribute();

	/**
	 * Returns the meta object for the attribute '{@link CustomDataModel.RequirementType#getInteger_Attribute <em>Integer Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Integer Attribute</em>'.
	 * @see CustomDataModel.RequirementType#getInteger_Attribute()
	 * @see #getRequirementType()
	 * @generated
	 */
	EAttribute getRequirementType_Integer_Attribute();

	/**
	 * Returns the meta object for the attribute '{@link CustomDataModel.RequirementType#getEnumeration_Attribute <em>Enumeration Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enumeration Attribute</em>'.
	 * @see CustomDataModel.RequirementType#getEnumeration_Attribute()
	 * @see #getRequirementType()
	 * @generated
	 */
	EAttribute getRequirementType_Enumeration_Attribute();

	/**
	 * Returns the meta object for enum '{@link CustomDataModel.Enumeration <em>Enumeration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Enumeration</em>'.
	 * @see CustomDataModel.Enumeration
	 * @generated
	 */
	EEnum getEnumeration();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CustomDataModelFactory getCustomDataModelFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link CustomDataModel.impl.SpecificationTypeImpl <em>Specification Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see CustomDataModel.impl.SpecificationTypeImpl
		 * @see CustomDataModel.impl.CustomDataModelPackageImpl#getSpecificationType()
		 * @generated
		 */
		EClass SPECIFICATION_TYPE = eINSTANCE.getSpecificationType();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPECIFICATION_TYPE__DESCRIPTION = eINSTANCE.getSpecificationType_Description();

		/**
		 * The meta object literal for the '<em><b>String Attribute</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPECIFICATION_TYPE__STRING_ATTRIBUTE = eINSTANCE.getSpecificationType_String_Attribute();

		/**
		 * The meta object literal for the '<em><b>Boolean Attribute</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPECIFICATION_TYPE__BOOLEAN_ATTRIBUTE = eINSTANCE.getSpecificationType_Boolean_Attribute();

		/**
		 * The meta object literal for the '<em><b>Integer Attribute</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPECIFICATION_TYPE__INTEGER_ATTRIBUTE = eINSTANCE.getSpecificationType_Integer_Attribute();

		/**
		 * The meta object literal for the '{@link CustomDataModel.impl.RequirementTypeImpl <em>Requirement Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see CustomDataModel.impl.RequirementTypeImpl
		 * @see CustomDataModel.impl.CustomDataModelPackageImpl#getRequirementType()
		 * @generated
		 */
		EClass REQUIREMENT_TYPE = eINSTANCE.getRequirementType();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REQUIREMENT_TYPE__DESCRIPTION = eINSTANCE.getRequirementType_Description();

		/**
		 * The meta object literal for the '<em><b>String Attribute</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REQUIREMENT_TYPE__STRING_ATTRIBUTE = eINSTANCE.getRequirementType_String_Attribute();

		/**
		 * The meta object literal for the '<em><b>Boolean Attribute</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REQUIREMENT_TYPE__BOOLEAN_ATTRIBUTE = eINSTANCE.getRequirementType_Boolean_Attribute();

		/**
		 * The meta object literal for the '<em><b>Integer Attribute</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REQUIREMENT_TYPE__INTEGER_ATTRIBUTE = eINSTANCE.getRequirementType_Integer_Attribute();

		/**
		 * The meta object literal for the '<em><b>Enumeration Attribute</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REQUIREMENT_TYPE__ENUMERATION_ATTRIBUTE = eINSTANCE.getRequirementType_Enumeration_Attribute();

		/**
		 * The meta object literal for the '{@link CustomDataModel.Enumeration <em>Enumeration</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see CustomDataModel.Enumeration
		 * @see CustomDataModel.impl.CustomDataModelPackageImpl#getEnumeration()
		 * @generated
		 */
		EEnum ENUMERATION = eINSTANCE.getEnumeration();

	}

} //CustomDataModelPackage

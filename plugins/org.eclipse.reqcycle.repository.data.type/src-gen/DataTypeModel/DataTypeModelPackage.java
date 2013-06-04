/**
 */
package DataTypeModel;

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
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see DataTypeModel.DataTypeModelFactory
 * @model kind="package"
 * @generated
 */
public interface DataTypeModelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "DataTypeModel";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/ReqCycle/DataTypeModel";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "DataTypeModel";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DataTypeModelPackage eINSTANCE = DataTypeModel.impl.DataTypeModelPackageImpl.init();

	/**
	 * The meta object id for the '{@link DataTypeModel.impl.ContainedTypeImpl <em>Contained Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DataTypeModel.impl.ContainedTypeImpl
	 * @see DataTypeModel.impl.DataTypeModelPackageImpl#getContainedType()
	 * @generated
	 */
	int CONTAINED_TYPE = 3;

	/**
	 * The feature id for the '<em><b>Custom Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_TYPE__CUSTOM_ATTRIBUTES = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_TYPE__NAME = 1;

	/**
	 * The number of structural features of the '<em>Contained Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_TYPE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Contained Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link DataTypeModel.impl.ReachableObjectTypeImpl <em>Reachable Object Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DataTypeModel.impl.ReachableObjectTypeImpl
	 * @see DataTypeModel.impl.DataTypeModelPackageImpl#getReachableObjectType()
	 * @generated
	 */
	int REACHABLE_OBJECT_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Custom Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACHABLE_OBJECT_TYPE__CUSTOM_ATTRIBUTES = CONTAINED_TYPE__CUSTOM_ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACHABLE_OBJECT_TYPE__NAME = CONTAINED_TYPE__NAME;

	/**
	 * The number of structural features of the '<em>Reachable Object Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACHABLE_OBJECT_TYPE_FEATURE_COUNT = CONTAINED_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Reachable Object Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACHABLE_OBJECT_TYPE_OPERATION_COUNT = CONTAINED_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DataTypeModel.impl.ModelTypeImpl <em>Model Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DataTypeModel.impl.ModelTypeImpl
	 * @see DataTypeModel.impl.DataTypeModelPackageImpl#getModelType()
	 * @generated
	 */
	int MODEL_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Object Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__OBJECT_TYPES = 0;

	/**
	 * The number of structural features of the '<em>Model Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Model Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link DataTypeModel.impl.ReachableSectionTypeImpl <em>Reachable Section Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DataTypeModel.impl.ReachableSectionTypeImpl
	 * @see DataTypeModel.impl.DataTypeModelPackageImpl#getReachableSectionType()
	 * @generated
	 */
	int REACHABLE_SECTION_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Custom Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACHABLE_SECTION_TYPE__CUSTOM_ATTRIBUTES = CONTAINED_TYPE__CUSTOM_ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACHABLE_SECTION_TYPE__NAME = CONTAINED_TYPE__NAME;

	/**
	 * The number of structural features of the '<em>Reachable Section Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACHABLE_SECTION_TYPE_FEATURE_COUNT = CONTAINED_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Reachable Section Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACHABLE_SECTION_TYPE_OPERATION_COUNT = CONTAINED_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DataTypeModel.impl.RequirementTypeImpl <em>Requirement Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DataTypeModel.impl.RequirementTypeImpl
	 * @see DataTypeModel.impl.DataTypeModelPackageImpl#getRequirementType()
	 * @generated
	 */
	int REQUIREMENT_TYPE = 4;

	/**
	 * The feature id for the '<em><b>Custom Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_TYPE__CUSTOM_ATTRIBUTES = REACHABLE_OBJECT_TYPE__CUSTOM_ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_TYPE__NAME = REACHABLE_OBJECT_TYPE__NAME;

	/**
	 * The number of structural features of the '<em>Requirement Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_TYPE_FEATURE_COUNT = REACHABLE_OBJECT_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Requirement Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_TYPE_OPERATION_COUNT = REACHABLE_OBJECT_TYPE_OPERATION_COUNT + 0;


	/**
	 * The meta object id for the '{@link DataTypeModel.impl.RequirementSectionTypeImpl <em>Requirement Section Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DataTypeModel.impl.RequirementSectionTypeImpl
	 * @see DataTypeModel.impl.DataTypeModelPackageImpl#getRequirementSectionType()
	 * @generated
	 */
	int REQUIREMENT_SECTION_TYPE = 5;

	/**
	 * The feature id for the '<em><b>Custom Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_SECTION_TYPE__CUSTOM_ATTRIBUTES = REACHABLE_OBJECT_TYPE__CUSTOM_ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_SECTION_TYPE__NAME = REACHABLE_OBJECT_TYPE__NAME;

	/**
	 * The number of structural features of the '<em>Requirement Section Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_SECTION_TYPE_FEATURE_COUNT = REACHABLE_OBJECT_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Requirement Section Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_SECTION_TYPE_OPERATION_COUNT = REACHABLE_OBJECT_TYPE_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link DataTypeModel.ReachableObjectType <em>Reachable Object Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reachable Object Type</em>'.
	 * @see DataTypeModel.ReachableObjectType
	 * @generated
	 */
	EClass getReachableObjectType();

	/**
	 * Returns the meta object for class '{@link DataTypeModel.ModelType <em>Model Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Type</em>'.
	 * @see DataTypeModel.ModelType
	 * @generated
	 */
	EClass getModelType();

	/**
	 * Returns the meta object for the containment reference list '{@link DataTypeModel.ModelType#getObjectTypes <em>Object Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Object Types</em>'.
	 * @see DataTypeModel.ModelType#getObjectTypes()
	 * @see #getModelType()
	 * @generated
	 */
	EReference getModelType_ObjectTypes();

	/**
	 * Returns the meta object for class '{@link DataTypeModel.ReachableSectionType <em>Reachable Section Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reachable Section Type</em>'.
	 * @see DataTypeModel.ReachableSectionType
	 * @generated
	 */
	EClass getReachableSectionType();

	/**
	 * Returns the meta object for class '{@link DataTypeModel.ContainedType <em>Contained Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Contained Type</em>'.
	 * @see DataTypeModel.ContainedType
	 * @generated
	 */
	EClass getContainedType();

	/**
	 * Returns the meta object for the containment reference list '{@link DataTypeModel.ContainedType#getCustomAttributes <em>Custom Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Custom Attributes</em>'.
	 * @see DataTypeModel.ContainedType#getCustomAttributes()
	 * @see #getContainedType()
	 * @generated
	 */
	EReference getContainedType_CustomAttributes();

	/**
	 * Returns the meta object for the attribute '{@link DataTypeModel.ContainedType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see DataTypeModel.ContainedType#getName()
	 * @see #getContainedType()
	 * @generated
	 */
	EAttribute getContainedType_Name();

	/**
	 * Returns the meta object for class '{@link DataTypeModel.RequirementType <em>Requirement Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Requirement Type</em>'.
	 * @see DataTypeModel.RequirementType
	 * @generated
	 */
	EClass getRequirementType();

	/**
	 * Returns the meta object for class '{@link DataTypeModel.RequirementSectionType <em>Requirement Section Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Requirement Section Type</em>'.
	 * @see DataTypeModel.RequirementSectionType
	 * @generated
	 */
	EClass getRequirementSectionType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DataTypeModelFactory getDataTypeModelFactory();

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
		 * The meta object literal for the '{@link DataTypeModel.impl.ReachableObjectTypeImpl <em>Reachable Object Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DataTypeModel.impl.ReachableObjectTypeImpl
		 * @see DataTypeModel.impl.DataTypeModelPackageImpl#getReachableObjectType()
		 * @generated
		 */
		EClass REACHABLE_OBJECT_TYPE = eINSTANCE.getReachableObjectType();

		/**
		 * The meta object literal for the '{@link DataTypeModel.impl.ModelTypeImpl <em>Model Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DataTypeModel.impl.ModelTypeImpl
		 * @see DataTypeModel.impl.DataTypeModelPackageImpl#getModelType()
		 * @generated
		 */
		EClass MODEL_TYPE = eINSTANCE.getModelType();

		/**
		 * The meta object literal for the '<em><b>Object Types</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_TYPE__OBJECT_TYPES = eINSTANCE.getModelType_ObjectTypes();

		/**
		 * The meta object literal for the '{@link DataTypeModel.impl.ReachableSectionTypeImpl <em>Reachable Section Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DataTypeModel.impl.ReachableSectionTypeImpl
		 * @see DataTypeModel.impl.DataTypeModelPackageImpl#getReachableSectionType()
		 * @generated
		 */
		EClass REACHABLE_SECTION_TYPE = eINSTANCE.getReachableSectionType();

		/**
		 * The meta object literal for the '{@link DataTypeModel.impl.ContainedTypeImpl <em>Contained Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DataTypeModel.impl.ContainedTypeImpl
		 * @see DataTypeModel.impl.DataTypeModelPackageImpl#getContainedType()
		 * @generated
		 */
		EClass CONTAINED_TYPE = eINSTANCE.getContainedType();

		/**
		 * The meta object literal for the '<em><b>Custom Attributes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINED_TYPE__CUSTOM_ATTRIBUTES = eINSTANCE.getContainedType_CustomAttributes();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTAINED_TYPE__NAME = eINSTANCE.getContainedType_Name();

		/**
		 * The meta object literal for the '{@link DataTypeModel.impl.RequirementTypeImpl <em>Requirement Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DataTypeModel.impl.RequirementTypeImpl
		 * @see DataTypeModel.impl.DataTypeModelPackageImpl#getRequirementType()
		 * @generated
		 */
		EClass REQUIREMENT_TYPE = eINSTANCE.getRequirementType();

		/**
		 * The meta object literal for the '{@link DataTypeModel.impl.RequirementSectionTypeImpl <em>Requirement Section Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DataTypeModel.impl.RequirementSectionTypeImpl
		 * @see DataTypeModel.impl.DataTypeModelPackageImpl#getRequirementSectionType()
		 * @generated
		 */
		EClass REQUIREMENT_SECTION_TYPE = eINSTANCE.getRequirementSectionType();

	}

} //DataTypeModelPackage

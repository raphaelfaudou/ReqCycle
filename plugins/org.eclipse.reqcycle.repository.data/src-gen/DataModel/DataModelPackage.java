/**
 */
package DataModel;

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
 * @see DataModel.DataModelFactory
 * @model kind="package"
 * @generated
 */
public interface DataModelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "DataModel";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/ReqCycle/DataModel";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "DataModel";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DataModelPackage eINSTANCE = DataModel.impl.DataModelPackageImpl.init();

	/**
	 * The meta object id for the '{@link DataModel.impl.RequirementSourceImpl <em>Requirement Source</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DataModel.impl.RequirementSourceImpl
	 * @see DataModel.impl.DataModelPackageImpl#getRequirementSource()
	 * @generated
	 */
	int REQUIREMENT_SOURCE = 0;

	/**
	 * The feature id for the '<em><b>Requirements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_SOURCE__REQUIREMENTS = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_SOURCE__NAME = 1;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_SOURCE__PROPERTIES = 2;

	/**
	 * The feature id for the '<em><b>Connector ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_SOURCE__CONNECTOR_ID = 3;

	/**
	 * The feature id for the '<em><b>Mapping</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_SOURCE__MAPPING = 4;

	/**
	 * The number of structural features of the '<em>Requirement Source</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_SOURCE_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Requirement Source</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_SOURCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link DataModel.impl.ContainedImpl <em>Contained</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DataModel.impl.ContainedImpl
	 * @see DataModel.impl.DataModelPackageImpl#getContained()
	 * @generated
	 */
	int CONTAINED = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED__NAME = 1;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED__URI = 2;

	/**
	 * The feature id for the '<em><b>Scopes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED__SCOPES = 3;

	/**
	 * The number of structural features of the '<em>Contained</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Contained</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link DataModel.impl.ReachableSectionImpl <em>Reachable Section</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DataModel.impl.ReachableSectionImpl
	 * @see DataModel.impl.DataModelPackageImpl#getReachableSection()
	 * @generated
	 */
	int REACHABLE_SECTION = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACHABLE_SECTION__ID = CONTAINED__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACHABLE_SECTION__NAME = CONTAINED__NAME;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACHABLE_SECTION__URI = CONTAINED__URI;

	/**
	 * The feature id for the '<em><b>Scopes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACHABLE_SECTION__SCOPES = CONTAINED__SCOPES;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACHABLE_SECTION__CHILDREN = CONTAINED_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Reachable Section</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACHABLE_SECTION_FEATURE_COUNT = CONTAINED_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Reachable Section</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACHABLE_SECTION_OPERATION_COUNT = CONTAINED_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DataModel.impl.ReachableObjectImpl <em>Reachable Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DataModel.impl.ReachableObjectImpl
	 * @see DataModel.impl.DataModelPackageImpl#getReachableObject()
	 * @generated
	 */
	int REACHABLE_OBJECT = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACHABLE_OBJECT__ID = CONTAINED__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACHABLE_OBJECT__NAME = CONTAINED__NAME;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACHABLE_OBJECT__URI = CONTAINED__URI;

	/**
	 * The feature id for the '<em><b>Scopes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACHABLE_OBJECT__SCOPES = CONTAINED__SCOPES;

	/**
	 * The number of structural features of the '<em>Reachable Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACHABLE_OBJECT_FEATURE_COUNT = CONTAINED_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Reachable Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACHABLE_OBJECT_OPERATION_COUNT = CONTAINED_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DataModel.impl.RequirementImpl <em>Requirement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DataModel.impl.RequirementImpl
	 * @see DataModel.impl.DataModelPackageImpl#getRequirement()
	 * @generated
	 */
	int REQUIREMENT = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__ID = REACHABLE_OBJECT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__NAME = REACHABLE_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__URI = REACHABLE_OBJECT__URI;

	/**
	 * The feature id for the '<em><b>Scopes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__SCOPES = REACHABLE_OBJECT__SCOPES;

	/**
	 * The number of structural features of the '<em>Requirement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_FEATURE_COUNT = REACHABLE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Requirement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_OPERATION_COUNT = REACHABLE_OBJECT_OPERATION_COUNT + 0;


	/**
	 * The meta object id for the '{@link DataModel.impl.RequirementSectionImpl <em>Requirement Section</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DataModel.impl.RequirementSectionImpl
	 * @see DataModel.impl.DataModelPackageImpl#getRequirementSection()
	 * @generated
	 */
	int REQUIREMENT_SECTION = 5;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_SECTION__ID = REACHABLE_OBJECT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_SECTION__NAME = REACHABLE_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_SECTION__URI = REACHABLE_OBJECT__URI;

	/**
	 * The feature id for the '<em><b>Scopes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_SECTION__SCOPES = REACHABLE_OBJECT__SCOPES;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_SECTION__CHILDREN = REACHABLE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Requirement Section</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_SECTION_FEATURE_COUNT = REACHABLE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Requirement Section</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_SECTION_OPERATION_COUNT = REACHABLE_OBJECT_OPERATION_COUNT + 0;


	/**
	 * The meta object id for the '{@link DataModel.impl.ScopeImpl <em>Scope</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DataModel.impl.ScopeImpl
	 * @see DataModel.impl.DataModelPackageImpl#getScope()
	 * @generated
	 */
	int SCOPE = 6;

	/**
	 * The feature id for the '<em><b>Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE__REQUIREMENTS = 0;

	/**
	 * The number of structural features of the '<em>Scope</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Scope</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link DataModel.impl.SystemImpl <em>System</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DataModel.impl.SystemImpl
	 * @see DataModel.impl.DataModelPackageImpl#getSystem()
	 * @generated
	 */
	int SYSTEM = 7;

	/**
	 * The feature id for the '<em><b>Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__REQUIREMENTS = SCOPE__REQUIREMENTS;

	/**
	 * The number of structural features of the '<em>System</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FEATURE_COUNT = SCOPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>System</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_OPERATION_COUNT = SCOPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DataModel.impl.CustomerImpl <em>Customer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DataModel.impl.CustomerImpl
	 * @see DataModel.impl.DataModelPackageImpl#getCustomer()
	 * @generated
	 */
	int CUSTOMER = 8;

	/**
	 * The feature id for the '<em><b>Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER__REQUIREMENTS = SCOPE__REQUIREMENTS;

	/**
	 * The number of structural features of the '<em>Customer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_FEATURE_COUNT = SCOPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Customer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_OPERATION_COUNT = SCOPE_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link DataModel.RequirementSource <em>Requirement Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Requirement Source</em>'.
	 * @see DataModel.RequirementSource
	 * @generated
	 */
	EClass getRequirementSource();

	/**
	 * Returns the meta object for the containment reference list '{@link DataModel.RequirementSource#getRequirements <em>Requirements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Requirements</em>'.
	 * @see DataModel.RequirementSource#getRequirements()
	 * @see #getRequirementSource()
	 * @generated
	 */
	EReference getRequirementSource_Requirements();

	/**
	 * Returns the meta object for the attribute '{@link DataModel.RequirementSource#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see DataModel.RequirementSource#getName()
	 * @see #getRequirementSource()
	 * @generated
	 */
	EAttribute getRequirementSource_Name();

	/**
	 * Returns the meta object for the map '{@link DataModel.RequirementSource#getProperties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Properties</em>'.
	 * @see DataModel.RequirementSource#getProperties()
	 * @see #getRequirementSource()
	 * @generated
	 */
	EReference getRequirementSource_Properties();

	/**
	 * Returns the meta object for the attribute '{@link DataModel.RequirementSource#getConnectorID <em>Connector ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connector ID</em>'.
	 * @see DataModel.RequirementSource#getConnectorID()
	 * @see #getRequirementSource()
	 * @generated
	 */
	EAttribute getRequirementSource_ConnectorID();

	/**
	 * Returns the meta object for the containment reference list '{@link DataModel.RequirementSource#getMapping <em>Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Mapping</em>'.
	 * @see DataModel.RequirementSource#getMapping()
	 * @see #getRequirementSource()
	 * @generated
	 */
	EReference getRequirementSource_Mapping();

	/**
	 * Returns the meta object for class '{@link DataModel.Contained <em>Contained</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Contained</em>'.
	 * @see DataModel.Contained
	 * @generated
	 */
	EClass getContained();

	/**
	 * Returns the meta object for the attribute '{@link DataModel.Contained#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see DataModel.Contained#getId()
	 * @see #getContained()
	 * @generated
	 */
	EAttribute getContained_Id();

	/**
	 * Returns the meta object for the attribute '{@link DataModel.Contained#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see DataModel.Contained#getName()
	 * @see #getContained()
	 * @generated
	 */
	EAttribute getContained_Name();

	/**
	 * Returns the meta object for the attribute '{@link DataModel.Contained#getUri <em>Uri</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uri</em>'.
	 * @see DataModel.Contained#getUri()
	 * @see #getContained()
	 * @generated
	 */
	EAttribute getContained_Uri();

	/**
	 * Returns the meta object for the reference list '{@link DataModel.Contained#getScopes <em>Scopes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Scopes</em>'.
	 * @see DataModel.Contained#getScopes()
	 * @see #getContained()
	 * @generated
	 */
	EReference getContained_Scopes();

	/**
	 * Returns the meta object for class '{@link DataModel.ReachableSection <em>Reachable Section</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reachable Section</em>'.
	 * @see DataModel.ReachableSection
	 * @generated
	 */
	EClass getReachableSection();

	/**
	 * Returns the meta object for the containment reference list '{@link DataModel.ReachableSection#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see DataModel.ReachableSection#getChildren()
	 * @see #getReachableSection()
	 * @generated
	 */
	EReference getReachableSection_Children();

	/**
	 * Returns the meta object for class '{@link DataModel.ReachableObject <em>Reachable Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reachable Object</em>'.
	 * @see DataModel.ReachableObject
	 * @generated
	 */
	EClass getReachableObject();

	/**
	 * Returns the meta object for class '{@link DataModel.Requirement <em>Requirement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Requirement</em>'.
	 * @see DataModel.Requirement
	 * @generated
	 */
	EClass getRequirement();

	/**
	 * Returns the meta object for class '{@link DataModel.RequirementSection <em>Requirement Section</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Requirement Section</em>'.
	 * @see DataModel.RequirementSection
	 * @generated
	 */
	EClass getRequirementSection();

	/**
	 * Returns the meta object for class '{@link DataModel.Scope <em>Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Scope</em>'.
	 * @see DataModel.Scope
	 * @generated
	 */
	EClass getScope();

	/**
	 * Returns the meta object for the reference list '{@link DataModel.Scope#getRequirements <em>Requirements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Requirements</em>'.
	 * @see DataModel.Scope#getRequirements()
	 * @see #getScope()
	 * @generated
	 */
	EReference getScope_Requirements();

	/**
	 * Returns the meta object for class '{@link DataModel.System <em>System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>System</em>'.
	 * @see DataModel.System
	 * @generated
	 */
	EClass getSystem();

	/**
	 * Returns the meta object for class '{@link DataModel.Customer <em>Customer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Customer</em>'.
	 * @see DataModel.Customer
	 * @generated
	 */
	EClass getCustomer();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DataModelFactory getDataModelFactory();

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
		 * The meta object literal for the '{@link DataModel.impl.RequirementSourceImpl <em>Requirement Source</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DataModel.impl.RequirementSourceImpl
		 * @see DataModel.impl.DataModelPackageImpl#getRequirementSource()
		 * @generated
		 */
		EClass REQUIREMENT_SOURCE = eINSTANCE.getRequirementSource();

		/**
		 * The meta object literal for the '<em><b>Requirements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REQUIREMENT_SOURCE__REQUIREMENTS = eINSTANCE.getRequirementSource_Requirements();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REQUIREMENT_SOURCE__NAME = eINSTANCE.getRequirementSource_Name();

		/**
		 * The meta object literal for the '<em><b>Properties</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REQUIREMENT_SOURCE__PROPERTIES = eINSTANCE.getRequirementSource_Properties();

		/**
		 * The meta object literal for the '<em><b>Connector ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REQUIREMENT_SOURCE__CONNECTOR_ID = eINSTANCE.getRequirementSource_ConnectorID();

		/**
		 * The meta object literal for the '<em><b>Mapping</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REQUIREMENT_SOURCE__MAPPING = eINSTANCE.getRequirementSource_Mapping();

		/**
		 * The meta object literal for the '{@link DataModel.impl.ContainedImpl <em>Contained</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DataModel.impl.ContainedImpl
		 * @see DataModel.impl.DataModelPackageImpl#getContained()
		 * @generated
		 */
		EClass CONTAINED = eINSTANCE.getContained();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTAINED__ID = eINSTANCE.getContained_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTAINED__NAME = eINSTANCE.getContained_Name();

		/**
		 * The meta object literal for the '<em><b>Uri</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTAINED__URI = eINSTANCE.getContained_Uri();

		/**
		 * The meta object literal for the '<em><b>Scopes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINED__SCOPES = eINSTANCE.getContained_Scopes();

		/**
		 * The meta object literal for the '{@link DataModel.impl.ReachableSectionImpl <em>Reachable Section</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DataModel.impl.ReachableSectionImpl
		 * @see DataModel.impl.DataModelPackageImpl#getReachableSection()
		 * @generated
		 */
		EClass REACHABLE_SECTION = eINSTANCE.getReachableSection();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REACHABLE_SECTION__CHILDREN = eINSTANCE.getReachableSection_Children();

		/**
		 * The meta object literal for the '{@link DataModel.impl.ReachableObjectImpl <em>Reachable Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DataModel.impl.ReachableObjectImpl
		 * @see DataModel.impl.DataModelPackageImpl#getReachableObject()
		 * @generated
		 */
		EClass REACHABLE_OBJECT = eINSTANCE.getReachableObject();

		/**
		 * The meta object literal for the '{@link DataModel.impl.RequirementImpl <em>Requirement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DataModel.impl.RequirementImpl
		 * @see DataModel.impl.DataModelPackageImpl#getRequirement()
		 * @generated
		 */
		EClass REQUIREMENT = eINSTANCE.getRequirement();

		/**
		 * The meta object literal for the '{@link DataModel.impl.RequirementSectionImpl <em>Requirement Section</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DataModel.impl.RequirementSectionImpl
		 * @see DataModel.impl.DataModelPackageImpl#getRequirementSection()
		 * @generated
		 */
		EClass REQUIREMENT_SECTION = eINSTANCE.getRequirementSection();

		/**
		 * The meta object literal for the '{@link DataModel.impl.ScopeImpl <em>Scope</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DataModel.impl.ScopeImpl
		 * @see DataModel.impl.DataModelPackageImpl#getScope()
		 * @generated
		 */
		EClass SCOPE = eINSTANCE.getScope();

		/**
		 * The meta object literal for the '<em><b>Requirements</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCOPE__REQUIREMENTS = eINSTANCE.getScope_Requirements();

		/**
		 * The meta object literal for the '{@link DataModel.impl.SystemImpl <em>System</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DataModel.impl.SystemImpl
		 * @see DataModel.impl.DataModelPackageImpl#getSystem()
		 * @generated
		 */
		EClass SYSTEM = eINSTANCE.getSystem();

		/**
		 * The meta object literal for the '{@link DataModel.impl.CustomerImpl <em>Customer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DataModel.impl.CustomerImpl
		 * @see DataModel.impl.DataModelPackageImpl#getCustomer()
		 * @generated
		 */
		EClass CUSTOMER = eINSTANCE.getCustomer();

	}

} //DataModelPackage

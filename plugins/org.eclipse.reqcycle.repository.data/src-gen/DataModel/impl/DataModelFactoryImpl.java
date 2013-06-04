/**
 */
package DataModel.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import DataModel.Customer;
import DataModel.DataModelFactory;
import DataModel.DataModelPackage;
import DataModel.ReachableObject;
import DataModel.ReachableSection;
import DataModel.Requirement;
import DataModel.RequirementSection;
import DataModel.RequirementSource;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DataModelFactoryImpl extends EFactoryImpl implements DataModelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DataModelFactory init() {
		try {
			DataModelFactory theDataModelFactory = (DataModelFactory)EPackage.Registry.INSTANCE.getEFactory(DataModelPackage.eNS_URI);
			if (theDataModelFactory != null) {
				return theDataModelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new DataModelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataModelFactoryImpl() {
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
			case DataModelPackage.REQUIREMENT_SOURCE: return createRequirementSource();
			case DataModelPackage.REACHABLE_SECTION: return createReachableSection();
			case DataModelPackage.REACHABLE_OBJECT: return createReachableObject();
			case DataModelPackage.REQUIREMENT: return createRequirement();
			case DataModelPackage.REQUIREMENT_SECTION: return createRequirementSection();
			case DataModelPackage.SYSTEM: return createSystem();
			case DataModelPackage.CUSTOMER: return createCustomer();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RequirementSource createRequirementSource() {
		RequirementSourceImpl requirementSource = new RequirementSourceImpl();
		return requirementSource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReachableSection createReachableSection() {
		ReachableSectionImpl reachableSection = new ReachableSectionImpl();
		return reachableSection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReachableObject createReachableObject() {
		ReachableObjectImpl reachableObject = new ReachableObjectImpl();
		return reachableObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Requirement createRequirement() {
		RequirementImpl requirement = new RequirementImpl();
		return requirement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RequirementSection createRequirementSection() {
		RequirementSectionImpl requirementSection = new RequirementSectionImpl();
		return requirementSection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataModel.System createSystem() {
		SystemImpl system = new SystemImpl();
		return system;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Customer createCustomer() {
		CustomerImpl customer = new CustomerImpl();
		return customer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataModelPackage getDataModelPackage() {
		return (DataModelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static DataModelPackage getPackage() {
		return DataModelPackage.eINSTANCE;
	}

} //DataModelFactoryImpl

/**
 */
package DataModel.impl;

import DataModel.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import DataModel.DataModelFactory;
import DataModel.DataModelPackage;
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
			case DataModelPackage.SECTION: return createSection();
			case DataModelPackage.REQUIREMENT: return createRequirement();
			case DataModelPackage.REQUIREMENT_SECTION: return createRequirementSection();
			case DataModelPackage.SCOPE: return createScope();
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
	public Section createSection() {
		SectionImpl section = new SectionImpl();
		return section;
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
	public Scope createScope() {
		ScopeImpl scope = new ScopeImpl();
		return scope;
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

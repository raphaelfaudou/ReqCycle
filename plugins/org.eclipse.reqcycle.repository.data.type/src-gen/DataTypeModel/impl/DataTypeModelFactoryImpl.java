/**
 */
package DataTypeModel.impl;

import DataTypeModel.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DataTypeModelFactoryImpl extends EFactoryImpl implements DataTypeModelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DataTypeModelFactory init() {
		try {
			DataTypeModelFactory theDataTypeModelFactory = (DataTypeModelFactory)EPackage.Registry.INSTANCE.getEFactory(DataTypeModelPackage.eNS_URI);
			if (theDataTypeModelFactory != null) {
				return theDataTypeModelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new DataTypeModelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataTypeModelFactoryImpl() {
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
			case DataTypeModelPackage.REACHABLE_OBJECT_TYPE: return createReachableObjectType();
			case DataTypeModelPackage.MODEL_TYPE: return createModelType();
			case DataTypeModelPackage.REACHABLE_SECTION_TYPE: return createReachableSectionType();
			case DataTypeModelPackage.REQUIREMENT_TYPE: return createRequirementType();
			case DataTypeModelPackage.REQUIREMENT_SECTION_TYPE: return createRequirementSectionType();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReachableObjectType createReachableObjectType() {
		ReachableObjectTypeImpl reachableObjectType = new ReachableObjectTypeImpl();
		return reachableObjectType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelType createModelType() {
		ModelTypeImpl modelType = new ModelTypeImpl();
		return modelType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReachableSectionType createReachableSectionType() {
		ReachableSectionTypeImpl reachableSectionType = new ReachableSectionTypeImpl();
		return reachableSectionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RequirementType createRequirementType() {
		RequirementTypeImpl requirementType = new RequirementTypeImpl();
		return requirementType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RequirementSectionType createRequirementSectionType() {
		RequirementSectionTypeImpl requirementSectionType = new RequirementSectionTypeImpl();
		return requirementSectionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataTypeModelPackage getDataTypeModelPackage() {
		return (DataTypeModelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static DataTypeModelPackage getPackage() {
		return DataTypeModelPackage.eINSTANCE;
	}

} //DataTypeModelFactoryImpl

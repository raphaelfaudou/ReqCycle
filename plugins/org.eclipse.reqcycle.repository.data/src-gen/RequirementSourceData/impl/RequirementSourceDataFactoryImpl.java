/**
 */
package RequirementSourceData.impl;

import RequirementSourceData.*;

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
public class RequirementSourceDataFactoryImpl extends EFactoryImpl implements RequirementSourceDataFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static RequirementSourceDataFactory init() {
		try {
			RequirementSourceDataFactory theRequirementSourceDataFactory = (RequirementSourceDataFactory)EPackage.Registry.INSTANCE.getEFactory(RequirementSourceDataPackage.eNS_URI);
			if (theRequirementSourceDataFactory != null) {
				return theRequirementSourceDataFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new RequirementSourceDataFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RequirementSourceDataFactoryImpl() {
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
			case RequirementSourceDataPackage.REQUIREMENT_SOURCE: return createRequirementSource();
			case RequirementSourceDataPackage.SECTION: return createSection();
			case RequirementSourceDataPackage.SIMPLE_REQUIREMENT: return createSimpleRequirement();
			case RequirementSourceDataPackage.REQUIREMENT: return createRequirement();
			case RequirementSourceDataPackage.SCOPE: return createScope();
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
	public SimpleRequirement createSimpleRequirement() {
		SimpleRequirementImpl simpleRequirement = new SimpleRequirementImpl();
		return simpleRequirement;
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
	public Scope createScope() {
		ScopeImpl scope = new ScopeImpl();
		return scope;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RequirementSourceDataPackage getRequirementSourceDataPackage() {
		return (RequirementSourceDataPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static RequirementSourceDataPackage getPackage() {
		return RequirementSourceDataPackage.eINSTANCE;
	}

} //RequirementSourceDataFactoryImpl

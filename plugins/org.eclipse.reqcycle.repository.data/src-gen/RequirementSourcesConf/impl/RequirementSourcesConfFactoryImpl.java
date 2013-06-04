/**
 */
package RequirementSourcesConf.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import RequirementSourcesConf.RequirementSources;
import RequirementSourcesConf.RequirementSourcesConfFactory;
import RequirementSourcesConf.RequirementSourcesConfPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RequirementSourcesConfFactoryImpl extends EFactoryImpl implements RequirementSourcesConfFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static RequirementSourcesConfFactory init() {
		try {
			RequirementSourcesConfFactory theRequirementSourcesConfFactory = (RequirementSourcesConfFactory)EPackage.Registry.INSTANCE.getEFactory(RequirementSourcesConfPackage.eNS_URI);
			if (theRequirementSourcesConfFactory != null) {
				return theRequirementSourcesConfFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new RequirementSourcesConfFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RequirementSourcesConfFactoryImpl() {
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
			case RequirementSourcesConfPackage.REQUIREMENT_SOURCES: return createRequirementSources();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RequirementSources createRequirementSources() {
		RequirementSourcesImpl requirementSources = new RequirementSourcesImpl();
		return requirementSources;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RequirementSourcesConfPackage getRequirementSourcesConfPackage() {
		return (RequirementSourcesConfPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static RequirementSourcesConfPackage getPackage() {
		return RequirementSourcesConfPackage.eINSTANCE;
	}

} //RequirementSourcesConfFactoryImpl

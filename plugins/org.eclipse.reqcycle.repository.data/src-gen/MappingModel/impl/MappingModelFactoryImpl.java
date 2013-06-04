/**
 */
package MappingModel.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import MappingModel.AttributeMapping;
import MappingModel.ElementMapping;
import MappingModel.MappingModelFactory;
import MappingModel.MappingModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MappingModelFactoryImpl extends EFactoryImpl implements MappingModelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MappingModelFactory init() {
		try {
			MappingModelFactory theMappingModelFactory = (MappingModelFactory)EPackage.Registry.INSTANCE.getEFactory(MappingModelPackage.eNS_URI);
			if (theMappingModelFactory != null) {
				return theMappingModelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new MappingModelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MappingModelFactoryImpl() {
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
			case MappingModelPackage.ELEMENT_MAPPING: return createElementMapping();
			case MappingModelPackage.ATTRIBUTE_MAPPING: return createAttributeMapping();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ElementMapping createElementMapping() {
		ElementMappingImpl elementMapping = new ElementMappingImpl();
		return elementMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AttributeMapping createAttributeMapping() {
		AttributeMappingImpl attributeMapping = new AttributeMappingImpl();
		return attributeMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MappingModelPackage getMappingModelPackage() {
		return (MappingModelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static MappingModelPackage getPackage() {
		return MappingModelPackage.eINSTANCE;
	}

} //MappingModelFactoryImpl

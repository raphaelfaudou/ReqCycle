/**
 */
package CustomDataModel;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see CustomDataModel.CustomDataModelPackage
 * @generated
 */
public interface CustomDataModelFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CustomDataModelFactory eINSTANCE = CustomDataModel.impl.CustomDataModelFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Specification Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Specification Type</em>'.
	 * @generated
	 */
	SpecificationType createSpecificationType();

	/**
	 * Returns a new object of class '<em>Requirement Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Requirement Type</em>'.
	 * @generated
	 */
	RequirementType createRequirementType();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	CustomDataModelPackage getCustomDataModelPackage();

} //CustomDataModelFactory

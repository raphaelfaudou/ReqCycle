/**
 */
package DataTypeModel;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see DataTypeModel.DataTypeModelPackage
 * @generated
 */
public interface DataTypeModelFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DataTypeModelFactory eINSTANCE = DataTypeModel.impl.DataTypeModelFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Reachable Object Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Reachable Object Type</em>'.
	 * @generated
	 */
	ReachableObjectType createReachableObjectType();

	/**
	 * Returns a new object of class '<em>Model Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Model Type</em>'.
	 * @generated
	 */
	ModelType createModelType();

	/**
	 * Returns a new object of class '<em>Reachable Section Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Reachable Section Type</em>'.
	 * @generated
	 */
	ReachableSectionType createReachableSectionType();

	/**
	 * Returns a new object of class '<em>Requirement Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Requirement Type</em>'.
	 * @generated
	 */
	RequirementType createRequirementType();

	/**
	 * Returns a new object of class '<em>Requirement Section Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Requirement Section Type</em>'.
	 * @generated
	 */
	RequirementSectionType createRequirementSectionType();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	DataTypeModelPackage getDataTypeModelPackage();

} //DataTypeModelFactory

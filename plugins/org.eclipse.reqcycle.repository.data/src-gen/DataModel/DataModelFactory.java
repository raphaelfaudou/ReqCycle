/**
 */
package DataModel;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see DataModel.DataModelPackage
 * @generated
 */
public interface DataModelFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DataModelFactory eINSTANCE = DataModel.impl.DataModelFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Requirement Source</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Requirement Source</em>'.
	 * @generated
	 */
	RequirementSource createRequirementSource();

	/**
	 * Returns a new object of class '<em>Section</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Section</em>'.
	 * @generated
	 */
	Section createSection();

	/**
	 * Returns a new object of class '<em>Requirement</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Requirement</em>'.
	 * @generated
	 */
	Requirement createRequirement();

	/**
	 * Returns a new object of class '<em>Requirement Section</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Requirement Section</em>'.
	 * @generated
	 */
	RequirementSection createRequirementSection();

	/**
	 * Returns a new object of class '<em>Scope</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Scope</em>'.
	 * @generated
	 */
	Scope createScope();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	DataModelPackage getDataModelPackage();

} //DataModelFactory

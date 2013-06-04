/**
 */
package ScopesConf;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see ScopesConf.ScopesConfPackage
 * @generated
 */
public interface ScopesConfFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ScopesConfFactory eINSTANCE = ScopesConf.impl.ScopesConfFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Scopes</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Scopes</em>'.
	 * @generated
	 */
	Scopes createScopes();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ScopesConfPackage getScopesConfPackage();

} //ScopesConfFactory

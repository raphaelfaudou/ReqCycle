/**
 */
package org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Config Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer#getTypes <em>Types</em>}</li>
 *   <li>{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer#getConfigurations <em>Configurations</em>}</li>
 *   <li>{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer#getDefaultConfiguration <em>Default Configuration</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationPackage#getTypeConfigContainer()
 * @model
 * @generated
 */
public interface TypeConfigContainer extends EObject {
	/**
	 * Returns the value of the '<em><b>Types</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Type}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Types</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Types</em>' containment reference list.
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationPackage#getTypeConfigContainer_Types()
	 * @model containment="true"
	 * @generated
	 */
	EList<Type> getTypes();

	/**
	 * Returns the value of the '<em><b>Configurations</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Configuration}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Configuration#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Configurations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Configurations</em>' containment reference list.
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationPackage#getTypeConfigContainer_Configurations()
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Configuration#getParent
	 * @model opposite="parent" containment="true"
	 * @generated
	 */
	EList<Configuration> getConfigurations();

	/**
	 * Returns the value of the '<em><b>Default Configuration</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Configuration</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Configuration</em>' reference.
	 * @see #setDefaultConfiguration(Configuration)
	 * @see org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationPackage#getTypeConfigContainer_DefaultConfiguration()
	 * @model
	 * @generated
	 */
	Configuration getDefaultConfiguration();

	/**
	 * Sets the value of the '{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer#getDefaultConfiguration <em>Default Configuration</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Configuration</em>' reference.
	 * @see #getDefaultConfiguration()
	 * @generated
	 */
	void setDefaultConfiguration(Configuration value);

} // TypeConfigContainer

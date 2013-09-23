/**
 */
package ScopesConf;

import RequirementSourceData.AbstractElement;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Scope</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ScopesConf.Scope#getName <em>Name</em>}</li>
 *   <li>{@link ScopesConf.Scope#getRequirements <em>Requirements</em>}</li>
 * </ul>
 * </p>
 *
 * @see ScopesConf.ScopesConfPackage#getScope()
 * @model
 * @generated
 */
public interface Scope extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see ScopesConf.ScopesConfPackage#getScope_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link ScopesConf.Scope#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Requirements</b></em>' reference list.
	 * The list contents are of type {@link RequirementSourceData.AbstractElement}.
	 * It is bidirectional and its opposite is '{@link RequirementSourceData.AbstractElement#getScopes <em>Scopes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Requirements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Requirements</em>' reference list.
	 * @see #isSetRequirements()
	 * @see ScopesConf.ScopesConfPackage#getScope_Requirements()
	 * @see RequirementSourceData.AbstractElement#getScopes
	 * @model opposite="scopes" resolveProxies="false" unsettable="true" transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	EList<AbstractElement> getRequirements();

	/**
	 * Returns whether the value of the '{@link ScopesConf.Scope#getRequirements <em>Requirements</em>}' reference list is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Requirements</em>' reference list is set.
	 * @see #getRequirements()
	 * @generated
	 */
	boolean isSetRequirements();

} // Scope

/**
 */
package DataModel;

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
 *   <li>{@link DataModel.Scope#getRequirements <em>Requirements</em>}</li>
 *   <li>{@link DataModel.Scope#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see DataModel.DataModelPackage#getScope()
 * @model
 * @generated
 */
public interface Scope extends EObject {
	/**
	 * Returns the value of the '<em><b>Requirements</b></em>' reference list.
	 * The list contents are of type {@link DataModel.Contained}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Requirements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Requirements</em>' reference list.
	 * @see #isSetRequirements()
	 * @see DataModel.DataModelPackage#getScope_Requirements()
	 * @model resolveProxies="false" unsettable="true" transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	EList<Contained> getRequirements();

	/**
	 * Returns whether the value of the '{@link DataModel.Scope#getRequirements <em>Requirements</em>}' reference list is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Requirements</em>' reference list is set.
	 * @see #getRequirements()
	 * @generated
	 */
	boolean isSetRequirements();

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
	 * @see DataModel.DataModelPackage#getScope_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link DataModel.Scope#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // Scope

/**
 */
package DataTypeModel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Contained Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link DataTypeModel.ContainedType#getCustomAttributes <em>Custom Attributes</em>}</li>
 *   <li>{@link DataTypeModel.ContainedType#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see DataTypeModel.DataTypeModelPackage#getContainedType()
 * @model abstract="true"
 * @generated
 */
public interface ContainedType extends EObject {
	/**
	 * Returns the value of the '<em><b>Custom Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EAttribute}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Custom Attributes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Custom Attributes</em>' containment reference list.
	 * @see DataTypeModel.DataTypeModelPackage#getContainedType_CustomAttributes()
	 * @model containment="true"
	 * @generated
	 */
	EList<EAttribute> getCustomAttributes();

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
	 * @see DataTypeModel.DataTypeModelPackage#getContainedType_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link DataTypeModel.ContainedType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // ContainedType

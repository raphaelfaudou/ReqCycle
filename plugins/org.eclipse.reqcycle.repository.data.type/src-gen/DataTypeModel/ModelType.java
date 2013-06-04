/**
 */
package DataTypeModel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link DataTypeModel.ModelType#getObjectTypes <em>Object Types</em>}</li>
 * </ul>
 * </p>
 *
 * @see DataTypeModel.DataTypeModelPackage#getModelType()
 * @model
 * @generated
 */
public interface ModelType extends EObject {
	/**
	 * Returns the value of the '<em><b>Object Types</b></em>' containment reference list.
	 * The list contents are of type {@link DataTypeModel.ContainedType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Object Types</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Object Types</em>' containment reference list.
	 * @see DataTypeModel.DataTypeModelPackage#getModelType_ObjectTypes()
	 * @model containment="true"
	 * @generated
	 */
	EList<ContainedType> getObjectTypes();

} // ModelType

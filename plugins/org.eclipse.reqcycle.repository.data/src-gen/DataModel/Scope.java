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
 * </ul>
 * </p>
 *
 * @see DataModel.DataModelPackage#getScope()
 * @model abstract="true"
 * @generated
 */
public interface Scope extends EObject {
	/**
	 * Returns the value of the '<em><b>Requirements</b></em>' reference list.
	 * The list contents are of type {@link DataModel.Contained}.
	 * It is bidirectional and its opposite is '{@link DataModel.Contained#getScopes <em>Scopes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Requirements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Requirements</em>' reference list.
	 * @see DataModel.DataModelPackage#getScope_Requirements()
	 * @see DataModel.Contained#getScopes
	 * @model opposite="scopes"
	 * @generated
	 */
	EList<Contained> getRequirements();

} // Scope

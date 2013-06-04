/**
 */
package DataModel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reachable Section</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link DataModel.ReachableSection#getChildren <em>Children</em>}</li>
 * </ul>
 * </p>
 *
 * @see DataModel.DataModelPackage#getReachableSection()
 * @model
 * @generated
 */
public interface ReachableSection extends Contained {
	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link DataModel.Contained}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see DataModel.DataModelPackage#getReachableSection_Children()
	 * @model containment="true"
	 * @generated
	 */
	EList<Contained> getChildren();

} // ReachableSection

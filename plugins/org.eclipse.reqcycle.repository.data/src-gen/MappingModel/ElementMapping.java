/**
 */
package MappingModel;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link MappingModel.ElementMapping#getDescription <em>Description</em>}</li>
 *   <li>{@link MappingModel.ElementMapping#getTargetElement <em>Target Element</em>}</li>
 *   <li>{@link MappingModel.ElementMapping#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link MappingModel.ElementMapping#getSourceQualifier <em>Source Qualifier</em>}</li>
 * </ul>
 * </p>
 *
 * @see MappingModel.MappingModelPackage#getElementMapping()
 * @model
 * @generated
 */
public interface ElementMapping extends EObject {
	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see MappingModel.MappingModelPackage#getElementMapping_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link MappingModel.ElementMapping#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Target Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Element</em>' reference.
	 * @see #setTargetElement(EClass)
	 * @see MappingModel.MappingModelPackage#getElementMapping_TargetElement()
	 * @model required="true"
	 * @generated
	 */
	EClass getTargetElement();

	/**
	 * Sets the value of the '{@link MappingModel.ElementMapping#getTargetElement <em>Target Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Element</em>' reference.
	 * @see #getTargetElement()
	 * @generated
	 */
	void setTargetElement(EClass value);

	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link MappingModel.AttributeMapping}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' containment reference list.
	 * @see MappingModel.MappingModelPackage#getElementMapping_Attributes()
	 * @model containment="true"
	 * @generated
	 */
	EList<AttributeMapping> getAttributes();

	/**
	 * Returns the value of the '<em><b>Source Qualifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Qualifier</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source Qualifier</em>' attribute.
	 * @see #setSourceQualifier(String)
	 * @see MappingModel.MappingModelPackage#getElementMapping_SourceQualifier()
	 * @model
	 * @generated
	 */
	String getSourceQualifier();

	/**
	 * Sets the value of the '{@link MappingModel.ElementMapping#getSourceQualifier <em>Source Qualifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Qualifier</em>' attribute.
	 * @see #getSourceQualifier()
	 * @generated
	 */
	void setSourceQualifier(String value);

} // ElementMapping

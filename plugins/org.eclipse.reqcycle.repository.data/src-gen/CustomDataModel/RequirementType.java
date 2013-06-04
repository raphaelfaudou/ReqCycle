/**
 */
package CustomDataModel;

import java.math.BigInteger;

import DataModel.RequirementSection;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Requirement Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link CustomDataModel.RequirementType#getDescription <em>Description</em>}</li>
 *   <li>{@link CustomDataModel.RequirementType#getString_Attribute <em>String Attribute</em>}</li>
 *   <li>{@link CustomDataModel.RequirementType#isBoolean_Attribute <em>Boolean Attribute</em>}</li>
 *   <li>{@link CustomDataModel.RequirementType#getInteger_Attribute <em>Integer Attribute</em>}</li>
 *   <li>{@link CustomDataModel.RequirementType#getEnumeration_Attribute <em>Enumeration Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @see CustomDataModel.CustomDataModelPackage#getRequirementType()
 * @model
 * @generated
 */
public interface RequirementType extends RequirementSection {
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
	 * @see CustomDataModel.CustomDataModelPackage#getRequirementType_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link CustomDataModel.RequirementType#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>String Attribute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>String Attribute</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>String Attribute</em>' attribute.
	 * @see #setString_Attribute(String)
	 * @see CustomDataModel.CustomDataModelPackage#getRequirementType_String_Attribute()
	 * @model
	 * @generated
	 */
	String getString_Attribute();

	/**
	 * Sets the value of the '{@link CustomDataModel.RequirementType#getString_Attribute <em>String Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>String Attribute</em>' attribute.
	 * @see #getString_Attribute()
	 * @generated
	 */
	void setString_Attribute(String value);

	/**
	 * Returns the value of the '<em><b>Boolean Attribute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Boolean Attribute</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Boolean Attribute</em>' attribute.
	 * @see #setBoolean_Attribute(boolean)
	 * @see CustomDataModel.CustomDataModelPackage#getRequirementType_Boolean_Attribute()
	 * @model
	 * @generated
	 */
	boolean isBoolean_Attribute();

	/**
	 * Sets the value of the '{@link CustomDataModel.RequirementType#isBoolean_Attribute <em>Boolean Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Boolean Attribute</em>' attribute.
	 * @see #isBoolean_Attribute()
	 * @generated
	 */
	void setBoolean_Attribute(boolean value);

	/**
	 * Returns the value of the '<em><b>Integer Attribute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Integer Attribute</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Integer Attribute</em>' attribute.
	 * @see #setInteger_Attribute(BigInteger)
	 * @see CustomDataModel.CustomDataModelPackage#getRequirementType_Integer_Attribute()
	 * @model
	 * @generated
	 */
	BigInteger getInteger_Attribute();

	/**
	 * Sets the value of the '{@link CustomDataModel.RequirementType#getInteger_Attribute <em>Integer Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Integer Attribute</em>' attribute.
	 * @see #getInteger_Attribute()
	 * @generated
	 */
	void setInteger_Attribute(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Enumeration Attribute</b></em>' attribute.
	 * The literals are from the enumeration {@link CustomDataModel.Enumeration}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enumeration Attribute</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enumeration Attribute</em>' attribute.
	 * @see CustomDataModel.Enumeration
	 * @see #setEnumeration_Attribute(Enumeration)
	 * @see CustomDataModel.CustomDataModelPackage#getRequirementType_Enumeration_Attribute()
	 * @model
	 * @generated
	 */
	Enumeration getEnumeration_Attribute();

	/**
	 * Sets the value of the '{@link CustomDataModel.RequirementType#getEnumeration_Attribute <em>Enumeration Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enumeration Attribute</em>' attribute.
	 * @see CustomDataModel.Enumeration
	 * @see #getEnumeration_Attribute()
	 * @generated
	 */
	void setEnumeration_Attribute(Enumeration value);

} // RequirementType

/**
 */
package CustomDataModel;

import java.math.BigInteger;

import DataModel.ReachableSection;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Custom Section</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link CustomDataModel.CustomSection#getDescription <em>Description</em>}</li>
 *   <li>{@link CustomDataModel.CustomSection#getString_Attribute <em>String Attribute</em>}</li>
 *   <li>{@link CustomDataModel.CustomSection#isBoolean_Attribute <em>Boolean Attribute</em>}</li>
 *   <li>{@link CustomDataModel.CustomSection#getInteger_Attribute <em>Integer Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @see CustomDataModel.CustomDataModelPackage#getCustomSection()
 * @model
 * @generated
 */
public interface CustomSection extends ReachableSection {
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
	 * @see CustomDataModel.CustomDataModelPackage#getCustomSection_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link CustomDataModel.CustomSection#getDescription <em>Description</em>}' attribute.
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
	 * @see CustomDataModel.CustomDataModelPackage#getCustomSection_String_Attribute()
	 * @model
	 * @generated
	 */
	String getString_Attribute();

	/**
	 * Sets the value of the '{@link CustomDataModel.CustomSection#getString_Attribute <em>String Attribute</em>}' attribute.
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
	 * @see CustomDataModel.CustomDataModelPackage#getCustomSection_Boolean_Attribute()
	 * @model
	 * @generated
	 */
	boolean isBoolean_Attribute();

	/**
	 * Sets the value of the '{@link CustomDataModel.CustomSection#isBoolean_Attribute <em>Boolean Attribute</em>}' attribute.
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
	 * @see CustomDataModel.CustomDataModelPackage#getCustomSection_Integer_Attribute()
	 * @model
	 * @generated
	 */
	BigInteger getInteger_Attribute();

	/**
	 * Sets the value of the '{@link CustomDataModel.CustomSection#getInteger_Attribute <em>Integer Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Integer Attribute</em>' attribute.
	 * @see #getInteger_Attribute()
	 * @generated
	 */
	void setInteger_Attribute(BigInteger value);

} // CustomSection

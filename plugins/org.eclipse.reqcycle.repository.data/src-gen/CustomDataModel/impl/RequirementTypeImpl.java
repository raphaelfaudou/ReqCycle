/**
 */
package CustomDataModel.impl;

import java.math.BigInteger;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import CustomDataModel.CustomDataModelPackage;
import CustomDataModel.Enumeration;
import CustomDataModel.RequirementType;
import DataModel.impl.RequirementSectionImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Requirement Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link CustomDataModel.impl.RequirementTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link CustomDataModel.impl.RequirementTypeImpl#getString_Attribute <em>String Attribute</em>}</li>
 *   <li>{@link CustomDataModel.impl.RequirementTypeImpl#isBoolean_Attribute <em>Boolean Attribute</em>}</li>
 *   <li>{@link CustomDataModel.impl.RequirementTypeImpl#getInteger_Attribute <em>Integer Attribute</em>}</li>
 *   <li>{@link CustomDataModel.impl.RequirementTypeImpl#getEnumeration_Attribute <em>Enumeration Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RequirementTypeImpl extends RequirementSectionImpl implements RequirementType {
	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getString_Attribute() <em>String Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getString_Attribute()
	 * @generated
	 * @ordered
	 */
	protected static final String STRING_ATTRIBUTE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getString_Attribute() <em>String Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getString_Attribute()
	 * @generated
	 * @ordered
	 */
	protected String string_Attribute = STRING_ATTRIBUTE_EDEFAULT;

	/**
	 * The default value of the '{@link #isBoolean_Attribute() <em>Boolean Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBoolean_Attribute()
	 * @generated
	 * @ordered
	 */
	protected static final boolean BOOLEAN_ATTRIBUTE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isBoolean_Attribute() <em>Boolean Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBoolean_Attribute()
	 * @generated
	 * @ordered
	 */
	protected boolean boolean_Attribute = BOOLEAN_ATTRIBUTE_EDEFAULT;

	/**
	 * The default value of the '{@link #getInteger_Attribute() <em>Integer Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInteger_Attribute()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger INTEGER_ATTRIBUTE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInteger_Attribute() <em>Integer Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInteger_Attribute()
	 * @generated
	 * @ordered
	 */
	protected BigInteger integer_Attribute = INTEGER_ATTRIBUTE_EDEFAULT;

	/**
	 * The default value of the '{@link #getEnumeration_Attribute() <em>Enumeration Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnumeration_Attribute()
	 * @generated
	 * @ordered
	 */
	protected static final Enumeration ENUMERATION_ATTRIBUTE_EDEFAULT = Enumeration.OFF;

	/**
	 * The cached value of the '{@link #getEnumeration_Attribute() <em>Enumeration Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnumeration_Attribute()
	 * @generated
	 * @ordered
	 */
	protected Enumeration enumeration_Attribute = ENUMERATION_ATTRIBUTE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RequirementTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CustomDataModelPackage.Literals.REQUIREMENT_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CustomDataModelPackage.REQUIREMENT_TYPE__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getString_Attribute() {
		return string_Attribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setString_Attribute(String newString_Attribute) {
		String oldString_Attribute = string_Attribute;
		string_Attribute = newString_Attribute;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CustomDataModelPackage.REQUIREMENT_TYPE__STRING_ATTRIBUTE, oldString_Attribute, string_Attribute));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isBoolean_Attribute() {
		return boolean_Attribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBoolean_Attribute(boolean newBoolean_Attribute) {
		boolean oldBoolean_Attribute = boolean_Attribute;
		boolean_Attribute = newBoolean_Attribute;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CustomDataModelPackage.REQUIREMENT_TYPE__BOOLEAN_ATTRIBUTE, oldBoolean_Attribute, boolean_Attribute));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getInteger_Attribute() {
		return integer_Attribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInteger_Attribute(BigInteger newInteger_Attribute) {
		BigInteger oldInteger_Attribute = integer_Attribute;
		integer_Attribute = newInteger_Attribute;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CustomDataModelPackage.REQUIREMENT_TYPE__INTEGER_ATTRIBUTE, oldInteger_Attribute, integer_Attribute));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Enumeration getEnumeration_Attribute() {
		return enumeration_Attribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnumeration_Attribute(Enumeration newEnumeration_Attribute) {
		Enumeration oldEnumeration_Attribute = enumeration_Attribute;
		enumeration_Attribute = newEnumeration_Attribute == null ? ENUMERATION_ATTRIBUTE_EDEFAULT : newEnumeration_Attribute;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CustomDataModelPackage.REQUIREMENT_TYPE__ENUMERATION_ATTRIBUTE, oldEnumeration_Attribute, enumeration_Attribute));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CustomDataModelPackage.REQUIREMENT_TYPE__DESCRIPTION:
				return getDescription();
			case CustomDataModelPackage.REQUIREMENT_TYPE__STRING_ATTRIBUTE:
				return getString_Attribute();
			case CustomDataModelPackage.REQUIREMENT_TYPE__BOOLEAN_ATTRIBUTE:
				return isBoolean_Attribute();
			case CustomDataModelPackage.REQUIREMENT_TYPE__INTEGER_ATTRIBUTE:
				return getInteger_Attribute();
			case CustomDataModelPackage.REQUIREMENT_TYPE__ENUMERATION_ATTRIBUTE:
				return getEnumeration_Attribute();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CustomDataModelPackage.REQUIREMENT_TYPE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case CustomDataModelPackage.REQUIREMENT_TYPE__STRING_ATTRIBUTE:
				setString_Attribute((String)newValue);
				return;
			case CustomDataModelPackage.REQUIREMENT_TYPE__BOOLEAN_ATTRIBUTE:
				setBoolean_Attribute((Boolean)newValue);
				return;
			case CustomDataModelPackage.REQUIREMENT_TYPE__INTEGER_ATTRIBUTE:
				setInteger_Attribute((BigInteger)newValue);
				return;
			case CustomDataModelPackage.REQUIREMENT_TYPE__ENUMERATION_ATTRIBUTE:
				setEnumeration_Attribute((Enumeration)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case CustomDataModelPackage.REQUIREMENT_TYPE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case CustomDataModelPackage.REQUIREMENT_TYPE__STRING_ATTRIBUTE:
				setString_Attribute(STRING_ATTRIBUTE_EDEFAULT);
				return;
			case CustomDataModelPackage.REQUIREMENT_TYPE__BOOLEAN_ATTRIBUTE:
				setBoolean_Attribute(BOOLEAN_ATTRIBUTE_EDEFAULT);
				return;
			case CustomDataModelPackage.REQUIREMENT_TYPE__INTEGER_ATTRIBUTE:
				setInteger_Attribute(INTEGER_ATTRIBUTE_EDEFAULT);
				return;
			case CustomDataModelPackage.REQUIREMENT_TYPE__ENUMERATION_ATTRIBUTE:
				setEnumeration_Attribute(ENUMERATION_ATTRIBUTE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CustomDataModelPackage.REQUIREMENT_TYPE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case CustomDataModelPackage.REQUIREMENT_TYPE__STRING_ATTRIBUTE:
				return STRING_ATTRIBUTE_EDEFAULT == null ? string_Attribute != null : !STRING_ATTRIBUTE_EDEFAULT.equals(string_Attribute);
			case CustomDataModelPackage.REQUIREMENT_TYPE__BOOLEAN_ATTRIBUTE:
				return boolean_Attribute != BOOLEAN_ATTRIBUTE_EDEFAULT;
			case CustomDataModelPackage.REQUIREMENT_TYPE__INTEGER_ATTRIBUTE:
				return INTEGER_ATTRIBUTE_EDEFAULT == null ? integer_Attribute != null : !INTEGER_ATTRIBUTE_EDEFAULT.equals(integer_Attribute);
			case CustomDataModelPackage.REQUIREMENT_TYPE__ENUMERATION_ATTRIBUTE:
				return enumeration_Attribute != ENUMERATION_ATTRIBUTE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (Description: ");
		result.append(description);
		result.append(", String_Attribute: ");
		result.append(string_Attribute);
		result.append(", Boolean_Attribute: ");
		result.append(boolean_Attribute);
		result.append(", Integer_Attribute: ");
		result.append(integer_Attribute);
		result.append(", Enumeration_Attribute: ");
		result.append(enumeration_Attribute);
		result.append(')');
		return result.toString();
	}

} //RequirementTypeImpl

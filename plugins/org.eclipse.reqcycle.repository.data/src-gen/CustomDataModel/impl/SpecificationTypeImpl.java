/**
 */
package CustomDataModel.impl;

import java.math.BigInteger;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import CustomDataModel.CustomDataModelPackage;
import CustomDataModel.SpecificationType;
import DataModel.impl.ReachableSectionImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Specification Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link CustomDataModel.impl.SpecificationTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link CustomDataModel.impl.SpecificationTypeImpl#getString_Attribute <em>String Attribute</em>}</li>
 *   <li>{@link CustomDataModel.impl.SpecificationTypeImpl#isBoolean_Attribute <em>Boolean Attribute</em>}</li>
 *   <li>{@link CustomDataModel.impl.SpecificationTypeImpl#getInteger_Attribute <em>Integer Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SpecificationTypeImpl extends ReachableSectionImpl implements SpecificationType {
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SpecificationTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CustomDataModelPackage.Literals.SPECIFICATION_TYPE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, CustomDataModelPackage.SPECIFICATION_TYPE__DESCRIPTION, oldDescription, description));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CustomDataModelPackage.SPECIFICATION_TYPE__STRING_ATTRIBUTE, oldString_Attribute, string_Attribute));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CustomDataModelPackage.SPECIFICATION_TYPE__BOOLEAN_ATTRIBUTE, oldBoolean_Attribute, boolean_Attribute));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CustomDataModelPackage.SPECIFICATION_TYPE__INTEGER_ATTRIBUTE, oldInteger_Attribute, integer_Attribute));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CustomDataModelPackage.SPECIFICATION_TYPE__DESCRIPTION:
				return getDescription();
			case CustomDataModelPackage.SPECIFICATION_TYPE__STRING_ATTRIBUTE:
				return getString_Attribute();
			case CustomDataModelPackage.SPECIFICATION_TYPE__BOOLEAN_ATTRIBUTE:
				return isBoolean_Attribute();
			case CustomDataModelPackage.SPECIFICATION_TYPE__INTEGER_ATTRIBUTE:
				return getInteger_Attribute();
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
			case CustomDataModelPackage.SPECIFICATION_TYPE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case CustomDataModelPackage.SPECIFICATION_TYPE__STRING_ATTRIBUTE:
				setString_Attribute((String)newValue);
				return;
			case CustomDataModelPackage.SPECIFICATION_TYPE__BOOLEAN_ATTRIBUTE:
				setBoolean_Attribute((Boolean)newValue);
				return;
			case CustomDataModelPackage.SPECIFICATION_TYPE__INTEGER_ATTRIBUTE:
				setInteger_Attribute((BigInteger)newValue);
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
			case CustomDataModelPackage.SPECIFICATION_TYPE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case CustomDataModelPackage.SPECIFICATION_TYPE__STRING_ATTRIBUTE:
				setString_Attribute(STRING_ATTRIBUTE_EDEFAULT);
				return;
			case CustomDataModelPackage.SPECIFICATION_TYPE__BOOLEAN_ATTRIBUTE:
				setBoolean_Attribute(BOOLEAN_ATTRIBUTE_EDEFAULT);
				return;
			case CustomDataModelPackage.SPECIFICATION_TYPE__INTEGER_ATTRIBUTE:
				setInteger_Attribute(INTEGER_ATTRIBUTE_EDEFAULT);
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
			case CustomDataModelPackage.SPECIFICATION_TYPE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case CustomDataModelPackage.SPECIFICATION_TYPE__STRING_ATTRIBUTE:
				return STRING_ATTRIBUTE_EDEFAULT == null ? string_Attribute != null : !STRING_ATTRIBUTE_EDEFAULT.equals(string_Attribute);
			case CustomDataModelPackage.SPECIFICATION_TYPE__BOOLEAN_ATTRIBUTE:
				return boolean_Attribute != BOOLEAN_ATTRIBUTE_EDEFAULT;
			case CustomDataModelPackage.SPECIFICATION_TYPE__INTEGER_ATTRIBUTE:
				return INTEGER_ATTRIBUTE_EDEFAULT == null ? integer_Attribute != null : !INTEGER_ATTRIBUTE_EDEFAULT.equals(integer_Attribute);
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
		result.append(')');
		return result.toString();
	}

} //SpecificationTypeImpl

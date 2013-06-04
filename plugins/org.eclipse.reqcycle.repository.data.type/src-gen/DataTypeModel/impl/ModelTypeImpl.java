/**
 */
package DataTypeModel.impl;

import DataTypeModel.ContainedType;
import DataTypeModel.DataTypeModelPackage;
import DataTypeModel.ModelType;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DataTypeModel.impl.ModelTypeImpl#getObjectTypes <em>Object Types</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelTypeImpl extends MinimalEObjectImpl.Container implements ModelType {
	/**
	 * The cached value of the '{@link #getObjectTypes() <em>Object Types</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getObjectTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<ContainedType> objectTypes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DataTypeModelPackage.Literals.MODEL_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ContainedType> getObjectTypes() {
		if (objectTypes == null) {
			objectTypes = new EObjectContainmentEList<ContainedType>(ContainedType.class, this, DataTypeModelPackage.MODEL_TYPE__OBJECT_TYPES);
		}
		return objectTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DataTypeModelPackage.MODEL_TYPE__OBJECT_TYPES:
				return ((InternalEList<?>)getObjectTypes()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DataTypeModelPackage.MODEL_TYPE__OBJECT_TYPES:
				return getObjectTypes();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DataTypeModelPackage.MODEL_TYPE__OBJECT_TYPES:
				getObjectTypes().clear();
				getObjectTypes().addAll((Collection<? extends ContainedType>)newValue);
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
			case DataTypeModelPackage.MODEL_TYPE__OBJECT_TYPES:
				getObjectTypes().clear();
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
			case DataTypeModelPackage.MODEL_TYPE__OBJECT_TYPES:
				return objectTypes != null && !objectTypes.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ModelTypeImpl

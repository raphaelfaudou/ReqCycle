/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
/**
 */
package org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Type;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationPackage;
import org.polarsys.reqcycle.types.IType;
import org.polarsys.reqcycle.types.ITypesManager;
import org.polarsys.reqcycle.utils.inject.ZigguratInject;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeImpl#getTypeId <em>Type Id</em>}</li>
 *   <li>{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeImpl#getOutgoings <em>Outgoings</em>}</li>
 *   <li>{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeImpl#getIncomings <em>Incomings</em>}</li>
 *   <li>{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeImpl#isIsExtensible <em>Is Extensible</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TypeImpl extends EObjectImpl implements Type {
	private ITypesManager manager = ZigguratInject.make(ITypesManager.class);

	/**
	 * The default value of the '{@link #getTypeId() <em>Type Id</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTypeId()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTypeId() <em>Type Id</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTypeId()
	 * @generated
	 * @ordered
	 */
	protected String typeId = TYPE_ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOutgoings() <em>Outgoings</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOutgoings()
	 * @generated
	 * @ordered
	 */
	protected EList<Relation> outgoings;

	/**
	 * The cached value of the '{@link #getIncomings() <em>Incomings</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getIncomings()
	 * @generated
	 * @ordered
	 */
	protected EList<Relation> incomings;

	/**
	 * The default value of the '{@link #isIsExtensible() <em>Is Extensible</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #isIsExtensible()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_EXTENSIBLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsExtensible() <em>Is Extensible</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isIsExtensible()
	 * @generated
	 * @ordered
	 */
	protected boolean isExtensible = IS_EXTENSIBLE_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected TypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypeconfigurationPackage.Literals.TYPE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getTypeId() {
		return typeId;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeId(String newTypeId) {
		String oldTypeId = typeId;
		typeId = newTypeId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypeconfigurationPackage.TYPE__TYPE_ID, oldTypeId, typeId));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Relation> getOutgoings() {
		if (outgoings == null) {
			outgoings = new EObjectWithInverseResolvingEList<Relation>(Relation.class, this, TypeconfigurationPackage.TYPE__OUTGOINGS, TypeconfigurationPackage.RELATION__UPSTREAM_TYPE);
		}
		return outgoings;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Relation> getIncomings() {
		if (incomings == null) {
			incomings = new EObjectWithInverseResolvingEList<Relation>(Relation.class, this, TypeconfigurationPackage.TYPE__INCOMINGS, TypeconfigurationPackage.RELATION__DOWNSTREAM_TYPE);
		}
		return incomings;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsExtensible() {
		return isExtensible;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsExtensible(boolean newIsExtensible) {
		boolean oldIsExtensible = isExtensible;
		isExtensible = newIsExtensible;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypeconfigurationPackage.TYPE__IS_EXTENSIBLE, oldIsExtensible, isExtensible));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public IType getIType() {
		return manager.getType(getTypeId());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TypeconfigurationPackage.TYPE__OUTGOINGS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutgoings()).basicAdd(otherEnd, msgs);
			case TypeconfigurationPackage.TYPE__INCOMINGS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIncomings()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TypeconfigurationPackage.TYPE__OUTGOINGS:
				return ((InternalEList<?>)getOutgoings()).basicRemove(otherEnd, msgs);
			case TypeconfigurationPackage.TYPE__INCOMINGS:
				return ((InternalEList<?>)getIncomings()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TypeconfigurationPackage.TYPE__TYPE_ID:
				return getTypeId();
			case TypeconfigurationPackage.TYPE__OUTGOINGS:
				return getOutgoings();
			case TypeconfigurationPackage.TYPE__INCOMINGS:
				return getIncomings();
			case TypeconfigurationPackage.TYPE__IS_EXTENSIBLE:
				return isIsExtensible();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TypeconfigurationPackage.TYPE__TYPE_ID:
				setTypeId((String)newValue);
				return;
			case TypeconfigurationPackage.TYPE__OUTGOINGS:
				getOutgoings().clear();
				getOutgoings().addAll((Collection<? extends Relation>)newValue);
				return;
			case TypeconfigurationPackage.TYPE__INCOMINGS:
				getIncomings().clear();
				getIncomings().addAll((Collection<? extends Relation>)newValue);
				return;
			case TypeconfigurationPackage.TYPE__IS_EXTENSIBLE:
				setIsExtensible((Boolean)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case TypeconfigurationPackage.TYPE__TYPE_ID:
				setTypeId(TYPE_ID_EDEFAULT);
				return;
			case TypeconfigurationPackage.TYPE__OUTGOINGS:
				getOutgoings().clear();
				return;
			case TypeconfigurationPackage.TYPE__INCOMINGS:
				getIncomings().clear();
				return;
			case TypeconfigurationPackage.TYPE__IS_EXTENSIBLE:
				setIsExtensible(IS_EXTENSIBLE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TypeconfigurationPackage.TYPE__TYPE_ID:
				return TYPE_ID_EDEFAULT == null ? typeId != null : !TYPE_ID_EDEFAULT.equals(typeId);
			case TypeconfigurationPackage.TYPE__OUTGOINGS:
				return outgoings != null && !outgoings.isEmpty();
			case TypeconfigurationPackage.TYPE__INCOMINGS:
				return incomings != null && !incomings.isEmpty();
			case TypeconfigurationPackage.TYPE__IS_EXTENSIBLE:
				return isExtensible != IS_EXTENSIBLE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (typeId: ");
		result.append(typeId);
		result.append(", isExtensible: ");
		result.append(isExtensible);
		result.append(')');
		return result.toString();
	}

} // TypeImpl

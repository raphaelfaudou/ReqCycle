/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Papa Issa Diakhate (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
/**
 */
package org.polarsys.reqcycle.predicates.persistance.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.reqcycle.predicates.core.api.IPredicate;
import org.polarsys.reqcycle.predicates.persistance.PredicatesConfPackage;
import org.polarsys.reqcycle.predicates.persistance.api.PredicatesConf;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Predicates Conf</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.polarsys.reqcycle.predicates.persistance.impl.PredicatesConfImpl#getPredicates <em>Predicates</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class PredicatesConfImpl extends MinimalEObjectImpl.Container implements PredicatesConf {

	/**
	 * The cached value of the '{@link #getPredicates() <em>Predicates</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getPredicates()
	 * @generated
	 * @ordered
	 */
	protected EList<IPredicate> predicates;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected PredicatesConfImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PredicatesConfPackage.Literals.PREDICATES_CONF;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<IPredicate> getPredicates() {
		if(predicates == null) {
			predicates = new EObjectContainmentEList<IPredicate>(IPredicate.class, this, PredicatesConfPackage.PREDICATES_CONF__PREDICATES);
		}
		return predicates;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch(featureID) {
		case PredicatesConfPackage.PREDICATES_CONF__PREDICATES:
			return ((InternalEList<?>)getPredicates()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch(featureID) {
		case PredicatesConfPackage.PREDICATES_CONF__PREDICATES:
			return getPredicates();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch(featureID) {
		case PredicatesConfPackage.PREDICATES_CONF__PREDICATES:
			getPredicates().clear();
			getPredicates().addAll((Collection<? extends IPredicate>)newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch(featureID) {
		case PredicatesConfPackage.PREDICATES_CONF__PREDICATES:
			getPredicates().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch(featureID) {
		case PredicatesConfPackage.PREDICATES_CONF__PREDICATES:
			return predicates != null && !predicates.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //PredicatesConfImpl

/*******************************************************************************
 *  * Copyright (c) 2013-2014 AtoS.
 *  * All rights reserved. This program and the accompanying materials
 *  * are made available under the terms of the Eclipse Public License v1.0
 *  * which accompanies this distribution, and is available at
 *  * http://www.eclipse.org/legal/epl-v10.html
 *  *
 *  * Contributors:
 *  *    {INITIAL AUTHOR} - initial API and implementation and/or initial documentation
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
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Configuration;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.RelationsPredicatesMapping;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Type;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type Config Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeConfigContainerImpl#getTypes <em>Types</em>}</li>
 *   <li>{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeConfigContainerImpl#getConfigurations <em>Configurations</em>}</li>
 *   <li>{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeConfigContainerImpl#getDefaultConfiguration <em>Default Configuration</em>}</li>
 *   <li>{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.impl.TypeConfigContainerImpl#getMappings <em>Mappings</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TypeConfigContainerImpl extends EObjectImpl implements TypeConfigContainer {
	/**
	 * The cached value of the '{@link #getTypes() <em>Types</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<Type> types;

	/**
	 * The cached value of the '{@link #getConfigurations() <em>Configurations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfigurations()
	 * @generated
	 * @ordered
	 */
	protected EList<Configuration> configurations;

	/**
	 * The cached value of the '{@link #getDefaultConfiguration() <em>Default Configuration</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultConfiguration()
	 * @generated
	 * @ordered
	 */
	protected Configuration defaultConfiguration;

	/**
	 * The cached value of the '{@link #getMappings() <em>Mappings</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMappings()
	 * @generated
	 * @ordered
	 */
	protected EList<RelationsPredicatesMapping> mappings;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TypeConfigContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypeconfigurationPackage.Literals.TYPE_CONFIG_CONTAINER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Type> getTypes() {
		if (types == null) {
			types = new EObjectContainmentEList<Type>(Type.class, this, TypeconfigurationPackage.TYPE_CONFIG_CONTAINER__TYPES);
		}
		return types;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Configuration> getConfigurations() {
		if (configurations == null) {
			configurations = new EObjectContainmentWithInverseEList<Configuration>(Configuration.class, this, TypeconfigurationPackage.TYPE_CONFIG_CONTAINER__CONFIGURATIONS, TypeconfigurationPackage.CONFIGURATION__PARENT);
		}
		return configurations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Configuration getDefaultConfiguration() {
		if (defaultConfiguration != null && defaultConfiguration.eIsProxy()) {
			InternalEObject oldDefaultConfiguration = (InternalEObject)defaultConfiguration;
			defaultConfiguration = (Configuration)eResolveProxy(oldDefaultConfiguration);
			if (defaultConfiguration != oldDefaultConfiguration) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TypeconfigurationPackage.TYPE_CONFIG_CONTAINER__DEFAULT_CONFIGURATION, oldDefaultConfiguration, defaultConfiguration));
			}
		}
		return defaultConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Configuration basicGetDefaultConfiguration() {
		return defaultConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultConfiguration(Configuration newDefaultConfiguration) {
		Configuration oldDefaultConfiguration = defaultConfiguration;
		defaultConfiguration = newDefaultConfiguration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypeconfigurationPackage.TYPE_CONFIG_CONTAINER__DEFAULT_CONFIGURATION, oldDefaultConfiguration, defaultConfiguration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RelationsPredicatesMapping> getMappings() {
		if (mappings == null) {
			mappings = new EObjectContainmentEList<RelationsPredicatesMapping>(RelationsPredicatesMapping.class, this, TypeconfigurationPackage.TYPE_CONFIG_CONTAINER__MAPPINGS);
		}
		return mappings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TypeconfigurationPackage.TYPE_CONFIG_CONTAINER__CONFIGURATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getConfigurations()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TypeconfigurationPackage.TYPE_CONFIG_CONTAINER__TYPES:
				return ((InternalEList<?>)getTypes()).basicRemove(otherEnd, msgs);
			case TypeconfigurationPackage.TYPE_CONFIG_CONTAINER__CONFIGURATIONS:
				return ((InternalEList<?>)getConfigurations()).basicRemove(otherEnd, msgs);
			case TypeconfigurationPackage.TYPE_CONFIG_CONTAINER__MAPPINGS:
				return ((InternalEList<?>)getMappings()).basicRemove(otherEnd, msgs);
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
			case TypeconfigurationPackage.TYPE_CONFIG_CONTAINER__TYPES:
				return getTypes();
			case TypeconfigurationPackage.TYPE_CONFIG_CONTAINER__CONFIGURATIONS:
				return getConfigurations();
			case TypeconfigurationPackage.TYPE_CONFIG_CONTAINER__DEFAULT_CONFIGURATION:
				if (resolve) return getDefaultConfiguration();
				return basicGetDefaultConfiguration();
			case TypeconfigurationPackage.TYPE_CONFIG_CONTAINER__MAPPINGS:
				return getMappings();
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
			case TypeconfigurationPackage.TYPE_CONFIG_CONTAINER__TYPES:
				getTypes().clear();
				getTypes().addAll((Collection<? extends Type>)newValue);
				return;
			case TypeconfigurationPackage.TYPE_CONFIG_CONTAINER__CONFIGURATIONS:
				getConfigurations().clear();
				getConfigurations().addAll((Collection<? extends Configuration>)newValue);
				return;
			case TypeconfigurationPackage.TYPE_CONFIG_CONTAINER__DEFAULT_CONFIGURATION:
				setDefaultConfiguration((Configuration)newValue);
				return;
			case TypeconfigurationPackage.TYPE_CONFIG_CONTAINER__MAPPINGS:
				getMappings().clear();
				getMappings().addAll((Collection<? extends RelationsPredicatesMapping>)newValue);
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
			case TypeconfigurationPackage.TYPE_CONFIG_CONTAINER__TYPES:
				getTypes().clear();
				return;
			case TypeconfigurationPackage.TYPE_CONFIG_CONTAINER__CONFIGURATIONS:
				getConfigurations().clear();
				return;
			case TypeconfigurationPackage.TYPE_CONFIG_CONTAINER__DEFAULT_CONFIGURATION:
				setDefaultConfiguration((Configuration)null);
				return;
			case TypeconfigurationPackage.TYPE_CONFIG_CONTAINER__MAPPINGS:
				getMappings().clear();
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
			case TypeconfigurationPackage.TYPE_CONFIG_CONTAINER__TYPES:
				return types != null && !types.isEmpty();
			case TypeconfigurationPackage.TYPE_CONFIG_CONTAINER__CONFIGURATIONS:
				return configurations != null && !configurations.isEmpty();
			case TypeconfigurationPackage.TYPE_CONFIG_CONTAINER__DEFAULT_CONFIGURATION:
				return defaultConfiguration != null;
			case TypeconfigurationPackage.TYPE_CONFIG_CONTAINER__MAPPINGS:
				return mappings != null && !mappings.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TypeConfigContainerImpl

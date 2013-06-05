/**
 */
package org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.CacheTracabilityPackage;
import org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.Property;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.PropertyImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.PropertyImpl#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PropertyImpl extends MinimalEObjectImpl.Container implements Property {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CacheTracabilityPackage.Literals.PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected int eStaticFeatureCount() {
		return 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return (String)eGet(CacheTracabilityPackage.Literals.PROPERTY__NAME, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		eSet(CacheTracabilityPackage.Literals.PROPERTY__NAME, newName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getValue() {
		return (String)eGet(CacheTracabilityPackage.Literals.PROPERTY__VALUE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(String newValue) {
		eSet(CacheTracabilityPackage.Literals.PROPERTY__VALUE, newValue);
	}

} //PropertyImpl

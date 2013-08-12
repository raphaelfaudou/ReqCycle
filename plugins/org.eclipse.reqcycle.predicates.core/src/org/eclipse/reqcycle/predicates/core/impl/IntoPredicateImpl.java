/**
 */
package org.eclipse.reqcycle.predicates.core.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.reqcycle.predicates.core.PredicatesPackage;
import org.eclipse.reqcycle.predicates.core.api.IntoPredicate;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Into Predicate</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.reqcycle.predicates.core.impl.IntoPredicateImpl#getInput <em>Input</em>}</li>
 * <li>{@link org.eclipse.reqcycle.predicates.core.impl.IntoPredicateImpl#getAllowedEntries <em>Allowed Entries</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class IntoPredicateImpl<T> extends IEAttrPredicateImpl implements IntoPredicate<T> {

	/**
	 * The cached value of the '{@link #getInput() <em>Input</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getInput()
	 * @generated
	 * @ordered
	 */
	protected T input;

	/**
	 * The cached value of the '{@link #getAllowedEntries() <em>Allowed Entries</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getAllowedEntries()
	 * @generated
	 * @ordered
	 */
	protected EList<T> allowedEntries;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected IntoPredicateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PredicatesPackage.Literals.INTO_PREDICATE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public T getInput() {
		return input;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setInput(T newInput) {
		T oldInput = input;
		input = newInput;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PredicatesPackage.INTO_PREDICATE__INPUT, oldInput, input));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<T> getAllowedEntries() {
		if(allowedEntries == null) {
			allowedEntries = new EDataTypeUniqueEList<T>(Object.class, this, PredicatesPackage.INTO_PREDICATE__ALLOWED_ENTRIES);
		}
		return allowedEntries;
	}

	@Override
	public boolean match(Object input) {
		try {
			@SuppressWarnings("unchecked")
			T realInput = (T)this.getInputValueFromEObject(input);
			boolean found = this.getAllowedEntries().contains(realInput);
			return found;
		} catch (ClassCastException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch(featureID) {
		case PredicatesPackage.INTO_PREDICATE__INPUT:
			return getInput();
		case PredicatesPackage.INTO_PREDICATE__ALLOWED_ENTRIES:
			return getAllowedEntries();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch(featureID) {
		case PredicatesPackage.INTO_PREDICATE__INPUT:
			setInput((T)newValue);
			return;
		case PredicatesPackage.INTO_PREDICATE__ALLOWED_ENTRIES:
			getAllowedEntries().clear();
			getAllowedEntries().addAll((Collection<? extends T>)newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch(featureID) {
		case PredicatesPackage.INTO_PREDICATE__INPUT:
			setInput((T)null);
			return;
		case PredicatesPackage.INTO_PREDICATE__ALLOWED_ENTRIES:
			getAllowedEntries().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch(featureID) {
		case PredicatesPackage.INTO_PREDICATE__INPUT:
			return input != null;
		case PredicatesPackage.INTO_PREDICATE__ALLOWED_ENTRIES:
			return allowedEntries != null && !allowedEntries.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if(eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (input: ");
		result.append(input);
		result.append(", allowedEntries: ");
		result.append(allowedEntries);
		result.append(')');
		return result.toString();
	}

} // IntoPredicateImpl

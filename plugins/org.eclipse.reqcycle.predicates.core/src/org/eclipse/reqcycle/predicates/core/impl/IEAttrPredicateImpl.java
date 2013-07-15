/**
 */
package org.eclipse.reqcycle.predicates.core.impl;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.reqcycle.predicates.core.PredicatesPackage;
import org.eclipse.reqcycle.predicates.core.api.IEAttrPredicate;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IE Attr Predicate</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.reqcycle.predicates.core.impl.IEAttrPredicateImpl#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.reqcycle.predicates.core.impl.IEAttrPredicateImpl#getTypedElement <em>Typed Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class IEAttrPredicateImpl extends MinimalEObjectImpl.Container implements IEAttrPredicate {
    /**
     * The default value of the '{@link #getDisplayName() <em>Display Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDisplayName()
     * @generated
     * @ordered
     */
    protected static final String DISPLAY_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDisplayName() <em>Display Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDisplayName()
     * @generated
     * @ordered
     */
    protected String displayName = DISPLAY_NAME_EDEFAULT;

    /**
     * The cached value of the '{@link #getTypedElement() <em>Typed Element</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTypedElement()
     * @generated
     * @ordered
     */
    protected EAttribute typedElement;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected IEAttrPredicateImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PredicatesPackage.Literals.IE_ATTR_PREDICATE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDisplayName(String newDisplayName) {
        String oldDisplayName = displayName;
        displayName = newDisplayName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PredicatesPackage.IE_ATTR_PREDICATE__DISPLAY_NAME, oldDisplayName, displayName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTypedElement() {
        if (typedElement != null && ((EObject)typedElement).eIsProxy()) {
            InternalEObject oldTypedElement = (InternalEObject)typedElement;
            typedElement = (EAttribute)eResolveProxy(oldTypedElement);
            if (typedElement != oldTypedElement) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PredicatesPackage.IE_ATTR_PREDICATE__TYPED_ELEMENT, oldTypedElement, typedElement));
            }
        }
        return typedElement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute basicGetTypedElement() {
        return typedElement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTypedElement(EAttribute newTypedElement) {
        EAttribute oldTypedElement = typedElement;
        typedElement = newTypedElement;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PredicatesPackage.IE_ATTR_PREDICATE__TYPED_ELEMENT, oldTypedElement, typedElement));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public abstract boolean match(Object input);
    
    /**
     * @param eObj
     * @return The input value from the specified object.
     */
    protected Object getInputValueFromEObject(final Object eObj) {
        assertIsEObject(eObj);
        Object value = ((EObject) eObj).eGet(getTypedElement());
        return value;
    }

    protected void assertIsEObject(final Object input) {
        if (!(input instanceof EObject)) {
            throw new IllegalArgumentException("The input must be of type 'EObject'.");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case PredicatesPackage.IE_ATTR_PREDICATE__DISPLAY_NAME:
                return getDisplayName();
            case PredicatesPackage.IE_ATTR_PREDICATE__TYPED_ELEMENT:
                if (resolve) return getTypedElement();
                return basicGetTypedElement();
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
            case PredicatesPackage.IE_ATTR_PREDICATE__DISPLAY_NAME:
                setDisplayName((String)newValue);
                return;
            case PredicatesPackage.IE_ATTR_PREDICATE__TYPED_ELEMENT:
                setTypedElement((EAttribute)newValue);
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
            case PredicatesPackage.IE_ATTR_PREDICATE__DISPLAY_NAME:
                setDisplayName(DISPLAY_NAME_EDEFAULT);
                return;
            case PredicatesPackage.IE_ATTR_PREDICATE__TYPED_ELEMENT:
                setTypedElement((EAttribute)null);
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
            case PredicatesPackage.IE_ATTR_PREDICATE__DISPLAY_NAME:
                return DISPLAY_NAME_EDEFAULT == null ? displayName != null : !DISPLAY_NAME_EDEFAULT.equals(displayName);
            case PredicatesPackage.IE_ATTR_PREDICATE__TYPED_ELEMENT:
                return typedElement != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
        switch (operationID) {
            case PredicatesPackage.IE_ATTR_PREDICATE___MATCH__OBJECT:
                return match(arguments.get(0));
        }
        return super.eInvoke(operationID, arguments);
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
        result.append(" (displayName: ");
        result.append(displayName);
        result.append(')');
        return result.toString();
    }

} //IEAttrPredicateImpl

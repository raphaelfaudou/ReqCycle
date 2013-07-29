/**
 */
package org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Attribute;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Relation;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Type;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationPackage;
import org.eclipse.reqcycle.traceability.types.impl.TraceTypesManager;
import org.eclipse.ziggurat.inject.ZigguratInject;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Relation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.RelationImpl#getUpstreamType <em>Upstream Type</em>}</li>
 *   <li>{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.RelationImpl#getDownstreamType <em>Downstream Type</em>}</li>
 *   <li>{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.RelationImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.RelationImpl#getAgregatedTypes <em>Agregated Types</em>}</li>
 *   <li>{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.RelationImpl#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.impl.RelationImpl#getAttributes <em>Attributes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RelationImpl extends EObjectImpl implements Relation {
	/**
	 * The cached value of the '{@link #getUpstreamType() <em>Upstream Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpstreamType()
	 * @generated
	 * @ordered
	 */
	protected Type upstreamType;

	/**
	 * The cached value of the '{@link #getDownstreamType() <em>Downstream Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDownstreamType()
	 * @generated
	 * @ordered
	 */
	protected Type downstreamType;

	/**
	 * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected static final String KIND_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected String kind = KIND_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAgregatedTypes() <em>Agregated Types</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAgregatedTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<String> agregatedTypes;

	/**
	 * The default value of the '{@link #getIcon() <em>Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIcon()
	 * @generated
	 * @ordered
	 */
	protected static final String ICON_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIcon() <em>Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIcon()
	 * @generated
	 * @ordered
	 */
	protected String icon = ICON_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAttributes() <em>Attributes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributes()
	 * @generated
	 * @ordered
	 */
	protected EList<Attribute> attributes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RelationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypeconfigurationPackage.Literals.RELATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getUpstreamType() {
		if (upstreamType != null && upstreamType.eIsProxy()) {
			InternalEObject oldUpstreamType = (InternalEObject)upstreamType;
			upstreamType = (Type)eResolveProxy(oldUpstreamType);
			if (upstreamType != oldUpstreamType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TypeconfigurationPackage.RELATION__UPSTREAM_TYPE, oldUpstreamType, upstreamType));
			}
		}
		return upstreamType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type basicGetUpstreamType() {
		return upstreamType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUpstreamType(Type newUpstreamType, NotificationChain msgs) {
		Type oldUpstreamType = upstreamType;
		upstreamType = newUpstreamType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TypeconfigurationPackage.RELATION__UPSTREAM_TYPE, oldUpstreamType, newUpstreamType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUpstreamType(Type newUpstreamType) {
		if (newUpstreamType != upstreamType) {
			NotificationChain msgs = null;
			if (upstreamType != null)
				msgs = ((InternalEObject)upstreamType).eInverseRemove(this, TypeconfigurationPackage.TYPE__OUTGOINGS, Type.class, msgs);
			if (newUpstreamType != null)
				msgs = ((InternalEObject)newUpstreamType).eInverseAdd(this, TypeconfigurationPackage.TYPE__OUTGOINGS, Type.class, msgs);
			msgs = basicSetUpstreamType(newUpstreamType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypeconfigurationPackage.RELATION__UPSTREAM_TYPE, newUpstreamType, newUpstreamType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getDownstreamType() {
		if (downstreamType != null && downstreamType.eIsProxy()) {
			InternalEObject oldDownstreamType = (InternalEObject)downstreamType;
			downstreamType = (Type)eResolveProxy(oldDownstreamType);
			if (downstreamType != oldDownstreamType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TypeconfigurationPackage.RELATION__DOWNSTREAM_TYPE, oldDownstreamType, downstreamType));
			}
		}
		return downstreamType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type basicGetDownstreamType() {
		return downstreamType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDownstreamType(Type newDownstreamType, NotificationChain msgs) {
		Type oldDownstreamType = downstreamType;
		downstreamType = newDownstreamType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TypeconfigurationPackage.RELATION__DOWNSTREAM_TYPE, oldDownstreamType, newDownstreamType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDownstreamType(Type newDownstreamType) {
		if (newDownstreamType != downstreamType) {
			NotificationChain msgs = null;
			if (downstreamType != null)
				msgs = ((InternalEObject)downstreamType).eInverseRemove(this, TypeconfigurationPackage.TYPE__INCOMINGS, Type.class, msgs);
			if (newDownstreamType != null)
				msgs = ((InternalEObject)newDownstreamType).eInverseAdd(this, TypeconfigurationPackage.TYPE__INCOMINGS, Type.class, msgs);
			msgs = basicSetDownstreamType(newDownstreamType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypeconfigurationPackage.RELATION__DOWNSTREAM_TYPE, newDownstreamType, newDownstreamType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getKind() {
		return kind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKind(String newKind) {
		String oldKind = kind;
		kind = newKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypeconfigurationPackage.RELATION__KIND, oldKind, kind));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getAgregatedTypes() {
		if (agregatedTypes == null) {
			agregatedTypes = new EDataTypeUniqueEList<String>(String.class, this, TypeconfigurationPackage.RELATION__AGREGATED_TYPES);
		}
		return agregatedTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIcon(String newIcon) {
		String oldIcon = icon;
		icon = newIcon;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypeconfigurationPackage.RELATION__ICON, oldIcon, icon));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Attribute> getAttributes() {
		if (attributes == null) {
			attributes = new EObjectContainmentEList<Attribute>(Attribute.class, this, TypeconfigurationPackage.RELATION__ATTRIBUTES);
		}
		return attributes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated not
	 */
	public EList<TType> getAgregated() {
		TraceTypesManager typeManager = ZigguratInject.make(TraceTypesManager.class);
		EList<TType> result = new BasicEList<TType>();
		for (String typeID : this.getAgregatedTypes()){
			result.add(typeManager.getTType(typeID));
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TypeconfigurationPackage.RELATION__UPSTREAM_TYPE:
				if (upstreamType != null)
					msgs = ((InternalEObject)upstreamType).eInverseRemove(this, TypeconfigurationPackage.TYPE__OUTGOINGS, Type.class, msgs);
				return basicSetUpstreamType((Type)otherEnd, msgs);
			case TypeconfigurationPackage.RELATION__DOWNSTREAM_TYPE:
				if (downstreamType != null)
					msgs = ((InternalEObject)downstreamType).eInverseRemove(this, TypeconfigurationPackage.TYPE__INCOMINGS, Type.class, msgs);
				return basicSetDownstreamType((Type)otherEnd, msgs);
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
			case TypeconfigurationPackage.RELATION__UPSTREAM_TYPE:
				return basicSetUpstreamType(null, msgs);
			case TypeconfigurationPackage.RELATION__DOWNSTREAM_TYPE:
				return basicSetDownstreamType(null, msgs);
			case TypeconfigurationPackage.RELATION__ATTRIBUTES:
				return ((InternalEList<?>)getAttributes()).basicRemove(otherEnd, msgs);
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
			case TypeconfigurationPackage.RELATION__UPSTREAM_TYPE:
				if (resolve) return getUpstreamType();
				return basicGetUpstreamType();
			case TypeconfigurationPackage.RELATION__DOWNSTREAM_TYPE:
				if (resolve) return getDownstreamType();
				return basicGetDownstreamType();
			case TypeconfigurationPackage.RELATION__KIND:
				return getKind();
			case TypeconfigurationPackage.RELATION__AGREGATED_TYPES:
				return getAgregatedTypes();
			case TypeconfigurationPackage.RELATION__ICON:
				return getIcon();
			case TypeconfigurationPackage.RELATION__ATTRIBUTES:
				return getAttributes();
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
			case TypeconfigurationPackage.RELATION__UPSTREAM_TYPE:
				setUpstreamType((Type)newValue);
				return;
			case TypeconfigurationPackage.RELATION__DOWNSTREAM_TYPE:
				setDownstreamType((Type)newValue);
				return;
			case TypeconfigurationPackage.RELATION__KIND:
				setKind((String)newValue);
				return;
			case TypeconfigurationPackage.RELATION__AGREGATED_TYPES:
				getAgregatedTypes().clear();
				getAgregatedTypes().addAll((Collection<? extends String>)newValue);
				return;
			case TypeconfigurationPackage.RELATION__ICON:
				setIcon((String)newValue);
				return;
			case TypeconfigurationPackage.RELATION__ATTRIBUTES:
				getAttributes().clear();
				getAttributes().addAll((Collection<? extends Attribute>)newValue);
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
			case TypeconfigurationPackage.RELATION__UPSTREAM_TYPE:
				setUpstreamType((Type)null);
				return;
			case TypeconfigurationPackage.RELATION__DOWNSTREAM_TYPE:
				setDownstreamType((Type)null);
				return;
			case TypeconfigurationPackage.RELATION__KIND:
				setKind(KIND_EDEFAULT);
				return;
			case TypeconfigurationPackage.RELATION__AGREGATED_TYPES:
				getAgregatedTypes().clear();
				return;
			case TypeconfigurationPackage.RELATION__ICON:
				setIcon(ICON_EDEFAULT);
				return;
			case TypeconfigurationPackage.RELATION__ATTRIBUTES:
				getAttributes().clear();
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
			case TypeconfigurationPackage.RELATION__UPSTREAM_TYPE:
				return upstreamType != null;
			case TypeconfigurationPackage.RELATION__DOWNSTREAM_TYPE:
				return downstreamType != null;
			case TypeconfigurationPackage.RELATION__KIND:
				return KIND_EDEFAULT == null ? kind != null : !KIND_EDEFAULT.equals(kind);
			case TypeconfigurationPackage.RELATION__AGREGATED_TYPES:
				return agregatedTypes != null && !agregatedTypes.isEmpty();
			case TypeconfigurationPackage.RELATION__ICON:
				return ICON_EDEFAULT == null ? icon != null : !ICON_EDEFAULT.equals(icon);
			case TypeconfigurationPackage.RELATION__ATTRIBUTES:
				return attributes != null && !attributes.isEmpty();
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
		result.append(" (kind: ");
		result.append(kind);
		result.append(", agregatedTypes: ");
		result.append(agregatedTypes);
		result.append(", icon: ");
		result.append(icon);
		result.append(')');
		return result.toString();
	}

} //RelationImpl

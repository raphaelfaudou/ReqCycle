/**
 */
package DataModel.impl;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.reqcycle.repository.data.util.DataUtil;
import org.eclipse.reqcycle.repository.data.util.RepositoryConstants;

import DataModel.Contained;
import DataModel.DataModelPackage;
import DataModel.RequirementSource;
import MappingModel.ElementMapping;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Requirement Source</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DataModel.impl.RequirementSourceImpl#getRequirements <em>Requirements</em>}</li>
 *   <li>{@link DataModel.impl.RequirementSourceImpl#getName <em>Name</em>}</li>
 *   <li>{@link DataModel.impl.RequirementSourceImpl#getProperties <em>Properties</em>}</li>
 *   <li>{@link DataModel.impl.RequirementSourceImpl#getConnectorId <em>Connector Id</em>}</li>
 *   <li>{@link DataModel.impl.RequirementSourceImpl#getMappings <em>Mappings</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RequirementSourceImpl extends MinimalEObjectImpl.Container implements RequirementSource {
		
	private Set<PropertyChangeListener> propertyChangeListeners = new HashSet<PropertyChangeListener>();
	
	/**
	 * The cached value of the '{@link #getRequirements() <em>Requirements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequirements()
	 * @generated
	 * @ordered
	 */
	protected EList<Contained> requirements;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProperties() <em>Properties</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperties()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> properties;

	/**
	 * The default value of the '{@link #getConnectorId() <em>Connector Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectorId()
	 * @generated
	 * @ordered
	 */
	protected static final String CONNECTOR_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMappings() <em>Mappings</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMappings()
	 * @generated
	 * @ordered
	 */
	protected EList<ElementMapping> mappings;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RequirementSourceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DataModelPackage.Literals.REQUIREMENT_SOURCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Contained> getRequirements() {
		if (requirements == null) {
			requirements = new EObjectContainmentEList<Contained>(Contained.class, this, DataModelPackage.REQUIREMENT_SOURCE__REQUIREMENTS);
		}
		return requirements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getName() {
		String type = getProperties().get(RepositoryConstants.PROPERTY_LABEL);
		return type != null ? type : RepositoryConstants.TYPE_UNKNOWN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setName(String newName) {
		getProperties().put(RepositoryConstants.PROPERTY_LABEL, newName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getProperties() {
		if (properties == null) {
			properties = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, DataModelPackage.REQUIREMENT_SOURCE__PROPERTIES);
		}
		return properties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getConnectorId() {
		String type = getProperties().get(RepositoryConstants.PROPERTY_CONNECTOR_ID);
		return type != null ? type : RepositoryConstants.TYPE_UNKNOWN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setConnectorID(String newConnectorID) {
		getProperties().put(RepositoryConstants.PROPERTY_CONNECTOR_ID, newConnectorID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setConnectorId(String newConnectorId) {
		getProperties().put(RepositoryConstants.PROPERTY_CONNECTOR_ID, newConnectorId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ElementMapping> getMappings() {
		if (mappings == null) {
			mappings = new EObjectContainmentEList<ElementMapping>(ElementMapping.class, this, DataModelPackage.REQUIREMENT_SOURCE__MAPPINGS);
		}
		return mappings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DataModelPackage.REQUIREMENT_SOURCE__REQUIREMENTS:
				return ((InternalEList<?>)getRequirements()).basicRemove(otherEnd, msgs);
			case DataModelPackage.REQUIREMENT_SOURCE__PROPERTIES:
				return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
			case DataModelPackage.REQUIREMENT_SOURCE__MAPPINGS:
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
			case DataModelPackage.REQUIREMENT_SOURCE__REQUIREMENTS:
				return getRequirements();
			case DataModelPackage.REQUIREMENT_SOURCE__NAME:
				return getName();
			case DataModelPackage.REQUIREMENT_SOURCE__PROPERTIES:
				if (coreType) return getProperties();
				else return getProperties().map();
			case DataModelPackage.REQUIREMENT_SOURCE__CONNECTOR_ID:
				return getConnectorId();
			case DataModelPackage.REQUIREMENT_SOURCE__MAPPINGS:
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
			case DataModelPackage.REQUIREMENT_SOURCE__REQUIREMENTS:
				getRequirements().clear();
				getRequirements().addAll((Collection<? extends Contained>)newValue);
				return;
			case DataModelPackage.REQUIREMENT_SOURCE__NAME:
				setName((String)newValue);
				return;
			case DataModelPackage.REQUIREMENT_SOURCE__PROPERTIES:
				((EStructuralFeature.Setting)getProperties()).set(newValue);
				return;
			case DataModelPackage.REQUIREMENT_SOURCE__CONNECTOR_ID:
				setConnectorId((String)newValue);
				return;
			case DataModelPackage.REQUIREMENT_SOURCE__MAPPINGS:
				getMappings().clear();
				getMappings().addAll((Collection<? extends ElementMapping>)newValue);
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
			case DataModelPackage.REQUIREMENT_SOURCE__REQUIREMENTS:
				getRequirements().clear();
				return;
			case DataModelPackage.REQUIREMENT_SOURCE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case DataModelPackage.REQUIREMENT_SOURCE__PROPERTIES:
				getProperties().clear();
				return;
			case DataModelPackage.REQUIREMENT_SOURCE__CONNECTOR_ID:
				setConnectorId(CONNECTOR_ID_EDEFAULT);
				return;
			case DataModelPackage.REQUIREMENT_SOURCE__MAPPINGS:
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
			case DataModelPackage.REQUIREMENT_SOURCE__REQUIREMENTS:
				return requirements != null && !requirements.isEmpty();
			case DataModelPackage.REQUIREMENT_SOURCE__NAME:
				return NAME_EDEFAULT == null ? getName() != null : !NAME_EDEFAULT.equals(getName());
			case DataModelPackage.REQUIREMENT_SOURCE__PROPERTIES:
				return properties != null && !properties.isEmpty();
			case DataModelPackage.REQUIREMENT_SOURCE__CONNECTOR_ID:
				return CONNECTOR_ID_EDEFAULT == null ? getConnectorId() != null : !CONNECTOR_ID_EDEFAULT.equals(getConnectorId());
			case DataModelPackage.REQUIREMENT_SOURCE__MAPPINGS:
				return mappings != null && !mappings.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	public void setProperty(String property, String newValue) {
		String oldValue = getProperties().get(property);

		if((oldValue != null && oldValue.equals(newValue)) || (oldValue == null && newValue != null)) {
			properties.put(property, newValue);
			notifyChangeListeners(property, oldValue, newValue);

		}
	}
	
	private void notifyChangeListeners(String key, String old, String value) {
		PropertyChangeEvent event = new PropertyChangeEvent(this, key, old, value);
		for(PropertyChangeListener listener : propertyChangeListeners) {
			listener.propertyChange(event);
		}
	}
	
	public String getRepositoryLabel() {
		return getProperty(RepositoryConstants.PROPERTY_LABEL);
	}
	
	public String getRepositoryUri() {
		return getProperty(RepositoryConstants.PROPERTY_URL);
	}
	
	public boolean hasProperty(String propertyKey) {
		String value = getProperty(propertyKey);
		return value != null && value.trim().length() > 0;
	}

	public String getProperty(String propertyKey) {
		return getProperties().get(propertyKey);
	}
	
	public void removeProperty(String key) {
		getProperties().remove(key);
	}

	public void removeChangeListeners(PropertyChangeListener listener) {
		propertyChangeListeners.remove(listener);
	}
	
	public void addChangeListeners(PropertyChangeListener listener) {
		propertyChangeListeners.add(listener);
	}

	public void store() {
	}

	public void dispose() {
	}

	public Collection<EClass> getTargetEPackage() {
		return Collections2.transform(mappings, new Function<ElementMapping, EClass>() {

			@Override
			public EClass apply(ElementMapping arg0) {
				return arg0.getTargetElement();
			}
		});
	}
	
	@Override
	public String toString() {
		return DataUtil.getLabel(this);
	}
	
} //RequirementSourceImpl

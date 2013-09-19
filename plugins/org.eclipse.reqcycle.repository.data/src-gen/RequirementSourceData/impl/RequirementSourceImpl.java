/**
 */
package RequirementSourceData.impl;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.reqcycle.repository.data.util.DataUtil;
import org.eclipse.reqcycle.repository.data.util.RepositoryConstants;

import MappingModel.ElementMapping;
import RequirementSourceData.AbstractElement;
import RequirementSourceData.RequirementSource;
import RequirementSourceData.RequirementSourceDataPackage;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Requirement Source</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link RequirementSourceData.impl.RequirementSourceImpl#getRequirements <em>Requirements</em>}</li>
 * <li>{@link RequirementSourceData.impl.RequirementSourceImpl#getName <em>Name</em>}</li>
 * <li>{@link RequirementSourceData.impl.RequirementSourceImpl#getProperties <em>Properties</em>}</li>
 * <li>{@link RequirementSourceData.impl.RequirementSourceImpl#getConnectorId <em>Connector Id</em>}</li>
 * <li>{@link RequirementSourceData.impl.RequirementSourceImpl#getMappings <em>Mappings</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class RequirementSourceImpl extends MinimalEObjectImpl.Container implements RequirementSource {

	protected Set<PropertyChangeListener> propertyChangeListeners = new HashSet<PropertyChangeListener>();

	/**
	 * The cached value of the '{@link #getRequirements() <em>Requirements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getRequirements()
	 * @generated
	 * @ordered
	 */
	protected EList<AbstractElement> requirements;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProperties() <em>Properties</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getProperties()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> properties;

	/**
	 * The default value of the '{@link #getConnectorId() <em>Connector Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getConnectorId()
	 * @generated
	 * @ordered
	 */
	protected static final String CONNECTOR_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMappings() <em>Mappings</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getMappings()
	 * @generated
	 * @ordered
	 */
	protected EList<ElementMapping> mappings;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected RequirementSourceImpl() {
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
		return RequirementSourceDataPackage.Literals.REQUIREMENT_SOURCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<AbstractElement> getRequirements() {
		if(requirements == null) {
			requirements = new EObjectContainmentEList<AbstractElement>(AbstractElement.class, this, RequirementSourceDataPackage.REQUIREMENT_SOURCE__REQUIREMENTS);
		}
		return requirements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated not
	 */
	@Override
	public String getName() {
		String type = getProperties().get(RepositoryConstants.PROPERTY_LABEL);
		return type != null ? type : RepositoryConstants.TYPE_UNKNOWN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated not
	 */
	@Override
	public void setName(String newName) {
		getProperties().put(RepositoryConstants.PROPERTY_LABEL, newName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EMap<String, String> getProperties() {
		if(properties == null) {
			properties = new EcoreEMap<String, String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, RequirementSourceDataPackage.REQUIREMENT_SOURCE__PROPERTIES);
		}
		return properties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated not
	 */
	@Override
	public String getConnectorId() {
		String type = getProperties().get(RepositoryConstants.PROPERTY_CONNECTOR_ID);
		return type != null ? type : RepositoryConstants.TYPE_UNKNOWN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated not
	 */
	@Override
	public void setConnectorId(String newConnectorId) {
		getProperties().put(RepositoryConstants.PROPERTY_CONNECTOR_ID, newConnectorId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<ElementMapping> getMappings() {
		if(mappings == null) {
			mappings = new EObjectContainmentEList<ElementMapping>(ElementMapping.class, this, RequirementSourceDataPackage.REQUIREMENT_SOURCE__MAPPINGS);
		}
		return mappings;
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
		case RequirementSourceDataPackage.REQUIREMENT_SOURCE__REQUIREMENTS:
			return ((InternalEList<?>)getRequirements()).basicRemove(otherEnd, msgs);
		case RequirementSourceDataPackage.REQUIREMENT_SOURCE__PROPERTIES:
			return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
		case RequirementSourceDataPackage.REQUIREMENT_SOURCE__MAPPINGS:
			return ((InternalEList<?>)getMappings()).basicRemove(otherEnd, msgs);
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
		case RequirementSourceDataPackage.REQUIREMENT_SOURCE__REQUIREMENTS:
			return getRequirements();
		case RequirementSourceDataPackage.REQUIREMENT_SOURCE__NAME:
			return getName();
		case RequirementSourceDataPackage.REQUIREMENT_SOURCE__PROPERTIES:
			if(coreType)
				return getProperties();
			else
				return getProperties().map();
		case RequirementSourceDataPackage.REQUIREMENT_SOURCE__CONNECTOR_ID:
			return getConnectorId();
		case RequirementSourceDataPackage.REQUIREMENT_SOURCE__MAPPINGS:
			return getMappings();
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
		case RequirementSourceDataPackage.REQUIREMENT_SOURCE__REQUIREMENTS:
			getRequirements().clear();
			getRequirements().addAll((Collection<? extends AbstractElement>)newValue);
			return;
		case RequirementSourceDataPackage.REQUIREMENT_SOURCE__NAME:
			setName((String)newValue);
			return;
		case RequirementSourceDataPackage.REQUIREMENT_SOURCE__PROPERTIES:
			((EStructuralFeature.Setting)getProperties()).set(newValue);
			return;
		case RequirementSourceDataPackage.REQUIREMENT_SOURCE__CONNECTOR_ID:
			setConnectorId((String)newValue);
			return;
		case RequirementSourceDataPackage.REQUIREMENT_SOURCE__MAPPINGS:
			getMappings().clear();
			getMappings().addAll((Collection<? extends ElementMapping>)newValue);
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
		case RequirementSourceDataPackage.REQUIREMENT_SOURCE__REQUIREMENTS:
			getRequirements().clear();
			return;
		case RequirementSourceDataPackage.REQUIREMENT_SOURCE__NAME:
			setName(NAME_EDEFAULT);
			return;
		case RequirementSourceDataPackage.REQUIREMENT_SOURCE__PROPERTIES:
			getProperties().clear();
			return;
		case RequirementSourceDataPackage.REQUIREMENT_SOURCE__CONNECTOR_ID:
			setConnectorId(CONNECTOR_ID_EDEFAULT);
			return;
		case RequirementSourceDataPackage.REQUIREMENT_SOURCE__MAPPINGS:
			getMappings().clear();
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
		case RequirementSourceDataPackage.REQUIREMENT_SOURCE__REQUIREMENTS:
			return requirements != null && !requirements.isEmpty();
		case RequirementSourceDataPackage.REQUIREMENT_SOURCE__NAME:
			return NAME_EDEFAULT == null ? getName() != null : !NAME_EDEFAULT.equals(getName());
		case RequirementSourceDataPackage.REQUIREMENT_SOURCE__PROPERTIES:
			return properties != null && !properties.isEmpty();
		case RequirementSourceDataPackage.REQUIREMENT_SOURCE__CONNECTOR_ID:
			return CONNECTOR_ID_EDEFAULT == null ? getConnectorId() != null : !CONNECTOR_ID_EDEFAULT.equals(getConnectorId());
		case RequirementSourceDataPackage.REQUIREMENT_SOURCE__MAPPINGS:
			return mappings != null && !mappings.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	@Override
	public void setProperty(String property, String newValue) {
		String oldValue = getProperties().get(property);

		if((oldValue != null && oldValue.equals(newValue)) || (oldValue == null && newValue != null)) {
			properties.put(property, newValue);
			notifyChangeListeners(property, oldValue, newValue);

		}
	}

	protected void notifyChangeListeners(String key, String old, String value) {
		PropertyChangeEvent event = new PropertyChangeEvent(this, key, old, value);
		for(PropertyChangeListener listener : propertyChangeListeners) {
			listener.propertyChange(event);
		}
	}

	@Override
	public String getRepositoryLabel() {
		return getProperty(RepositoryConstants.PROPERTY_LABEL);
	}

	@Override
	public String getRepositoryUri() {
		return getProperty(RepositoryConstants.PROPERTY_URL);
	}

	@Override
	public boolean hasProperty(String propertyKey) {
		String value = getProperty(propertyKey);
		return value != null && value.trim().length() > 0;
	}

	@Override
	public String getProperty(String propertyKey) {
		return getProperties().get(propertyKey);
	}

	@Override
	public void removeProperty(String key) {
		getProperties().remove(key);
	}

	@Override
	public void removeChangeListeners(PropertyChangeListener listener) {
		propertyChangeListeners.remove(listener);
	}

	@Override
	public void addChangeListeners(PropertyChangeListener listener) {
		propertyChangeListeners.add(listener);
	}

	@Override
	public void store() {
	}

	@Override
	public void dispose() {
	}

	@Override
	public Collection<EClass> getTargetEPackage() {
		if(mappings == null) {
			return Collections.emptyList();
		}
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

	@Override
	public boolean contains(AbstractElement abstractElement) {

		if(abstractElement == null) {
			return false;
		}

		EObject container = abstractElement.eContainer();

		if(container instanceof RequirementSource) {
			return EcoreUtil.equals(container, this);
		} else if(container instanceof AbstractElement) {
			return contains((AbstractElement)container);
		}

		return false;
	}


} //RequirementSourceImpl

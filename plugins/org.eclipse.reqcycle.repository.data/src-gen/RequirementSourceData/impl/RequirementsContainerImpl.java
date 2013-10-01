/**
 */
package RequirementSourceData.impl;

import RequirementSourceData.AbstractElement;
import RequirementSourceData.RequirementSourceDataPackage;
import RequirementSourceData.RequirementsContainer;
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
 * An implementation of the model object '<em><b>Requirements Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link RequirementSourceData.impl.RequirementsContainerImpl#getRequirements <em>Requirements</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RequirementsContainerImpl extends MinimalEObjectImpl.Container implements RequirementsContainer {
	/**
	 * The cached value of the '{@link #getRequirements() <em>Requirements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequirements()
	 * @generated
	 * @ordered
	 */
	protected EList<AbstractElement> requirements;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RequirementsContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RequirementSourceDataPackage.Literals.REQUIREMENTS_CONTAINER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AbstractElement> getRequirements() {
		if (requirements == null) {
			requirements = new EObjectContainmentEList<AbstractElement>(AbstractElement.class, this, RequirementSourceDataPackage.REQUIREMENTS_CONTAINER__REQUIREMENTS);
		}
		return requirements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RequirementSourceDataPackage.REQUIREMENTS_CONTAINER__REQUIREMENTS:
				return ((InternalEList<?>)getRequirements()).basicRemove(otherEnd, msgs);
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
			case RequirementSourceDataPackage.REQUIREMENTS_CONTAINER__REQUIREMENTS:
				return getRequirements();
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
			case RequirementSourceDataPackage.REQUIREMENTS_CONTAINER__REQUIREMENTS:
				getRequirements().clear();
				getRequirements().addAll((Collection<? extends AbstractElement>)newValue);
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
			case RequirementSourceDataPackage.REQUIREMENTS_CONTAINER__REQUIREMENTS:
				getRequirements().clear();
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
			case RequirementSourceDataPackage.REQUIREMENTS_CONTAINER__REQUIREMENTS:
				return requirements != null && !requirements.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //RequirementsContainerImpl

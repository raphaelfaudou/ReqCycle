/**
 */
package org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.AnalyzedResource;
import org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.Attribute;
import org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.CacheTracabilityPackage;
import org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceabilityLink;
import org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceableElement;

import com.google.common.base.Objects;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Traceability Link</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.TraceabilityLinkImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.TraceabilityLinkImpl#getSources <em>Sources</em>}</li>
 *   <li>{@link org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.TraceabilityLinkImpl#getTargets <em>Targets</em>}</li>
 *   <li>{@link org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.TraceabilityLinkImpl#isDeleted <em>Deleted</em>}</li>
 *   <li>{@link org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.TraceabilityLinkImpl#getResource <em>Resource</em>}</li>
 *   <li>{@link org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.TraceabilityLinkImpl#getLabel <em>Label</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TraceabilityLinkImpl extends MinimalEObjectImpl.Container
		implements TraceabilityLink {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected TraceabilityLinkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CacheTracabilityPackage.Literals.TRACEABILITY_LINK;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected int eStaticFeatureCount() {
		return 0;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Attribute> getAttributes() {
		return (EList<Attribute>)eGet(CacheTracabilityPackage.Literals.TRACEABILITY_LINK__ATTRIBUTES, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<TraceableElement> getSources() {
		return (EList<TraceableElement>)eGet(CacheTracabilityPackage.Literals.TRACEABILITY_LINK__SOURCES, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<TraceableElement> getTargets() {
		return (EList<TraceableElement>)eGet(CacheTracabilityPackage.Literals.TRACEABILITY_LINK__TARGETS, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDeleted() {
		return (Boolean)eGet(CacheTracabilityPackage.Literals.TRACEABILITY_LINK__DELETED, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeleted(boolean newDeleted) {
		eSet(CacheTracabilityPackage.Literals.TRACEABILITY_LINK__DELETED, newDeleted);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public AnalyzedResource getResource() {
		return (AnalyzedResource)eGet(CacheTracabilityPackage.Literals.TRACEABILITY_LINK__RESOURCE, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setResource(AnalyzedResource newResource) {
		eSet(CacheTracabilityPackage.Literals.TRACEABILITY_LINK__RESOURCE, newResource);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getLabel() {
		return (String)eGet(CacheTracabilityPackage.Literals.TRACEABILITY_LINK__LABEL, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabel(String newLabel) {
		eSet(CacheTracabilityPackage.Literals.TRACEABILITY_LINK__LABEL, newLabel);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TraceabilityLink) {
			TraceabilityLink tl = (TraceabilityLink) obj;
			return Objects.equal(getSources(), tl.getSources())
					&& Objects.equal(getTargets(), tl.getTargets())
					&& Objects.equal(getLabel(), tl.getLabel());
		}
		return super.equals(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 * 
	 * @generated not
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(getTargets(), getSources(), getLabel());
	}

} // TraceabilityLinkImpl

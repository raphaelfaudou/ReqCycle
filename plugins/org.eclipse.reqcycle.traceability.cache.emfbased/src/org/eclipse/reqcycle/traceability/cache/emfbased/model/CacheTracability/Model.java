/**
 */
package org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.Model#getTraceables <em>Traceables</em>}</li>
 *   <li>{@link org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.Model#getResources <em>Resources</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.CacheTracabilityPackage#getModel()
 * @model
 * @generated
 */
public interface Model extends EObject {
	/**
	 * Returns the value of the '<em><b>Traceables</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceableElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Traceables</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Traceables</em>' containment reference list.
	 * @see org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.CacheTracabilityPackage#getModel_Traceables()
	 * @model containment="true"
	 * @generated
	 */
	EList<TraceableElement> getTraceables();

	/**
	 * Returns the value of the '<em><b>Resources</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.AnalyzedResource}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resources</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resources</em>' containment reference list.
	 * @see org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.CacheTracabilityPackage#getModel_Resources()
	 * @model containment="true"
	 * @generated
	 */
	EList<AnalyzedResource> getResources();

} // Model

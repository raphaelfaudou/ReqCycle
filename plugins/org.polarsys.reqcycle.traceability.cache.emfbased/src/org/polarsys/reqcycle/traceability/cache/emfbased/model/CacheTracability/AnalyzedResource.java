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
package org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Analyzed Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.AnalyzedResource#getModificationTime <em>Modification Time</em>}</li>
 *   <li>{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.AnalyzedResource#getContained <em>Contained</em>}</li>
 *   <li>{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.AnalyzedResource#getLinks <em>Links</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.CacheTracabilityPackage#getAnalyzedResource()
 * @model
 * @generated
 */
public interface AnalyzedResource extends URIElement {
	/**
	 * Returns the value of the '<em><b>Modification Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Modification Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Modification Time</em>' attribute.
	 * @see #setModificationTime(String)
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.CacheTracabilityPackage#getAnalyzedResource_ModificationTime()
	 * @model
	 * @generated
	 */
	String getModificationTime();

	/**
	 * Sets the value of the '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.AnalyzedResource#getModificationTime <em>Modification Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Modification Time</em>' attribute.
	 * @see #getModificationTime()
	 * @generated
	 */
	void setModificationTime(String value);

	/**
	 * Returns the value of the '<em><b>Contained</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contained</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contained</em>' reference.
	 * @see #setContained(TraceabilityLink)
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.CacheTracabilityPackage#getAnalyzedResource_Contained()
	 * @model
	 * @generated
	 */
	TraceabilityLink getContained();

	/**
	 * Sets the value of the '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.AnalyzedResource#getContained <em>Contained</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Contained</em>' reference.
	 * @see #getContained()
	 * @generated
	 */
	void setContained(TraceabilityLink value);

	/**
	 * Returns the value of the '<em><b>Links</b></em>' containment reference list.
	 * The list contents are of type {@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceabilityLink}.
	 * It is bidirectional and its opposite is '{@link org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceabilityLink#getResource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Links</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Links</em>' containment reference list.
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.CacheTracabilityPackage#getAnalyzedResource_Links()
	 * @see org.polarsys.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceabilityLink#getResource
	 * @model opposite="resource" containment="true"
	 * @generated
	 */
	EList<TraceabilityLink> getLinks();

} // AnalyzedResource

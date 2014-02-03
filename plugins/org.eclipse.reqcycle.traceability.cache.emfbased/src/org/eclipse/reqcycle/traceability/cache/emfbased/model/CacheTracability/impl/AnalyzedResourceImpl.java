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
package org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.AnalyzedResource;
import org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.CacheTracabilityPackage;
import org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceabilityLink;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Analyzed Resource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.AnalyzedResourceImpl#getModificationTime <em>Modification Time</em>}</li>
 *   <li>{@link org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.AnalyzedResourceImpl#getContained <em>Contained</em>}</li>
 *   <li>{@link org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.AnalyzedResourceImpl#getLinks <em>Links</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AnalyzedResourceImpl extends URIElementImpl implements AnalyzedResource {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AnalyzedResourceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CacheTracabilityPackage.Literals.ANALYZED_RESOURCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getModificationTime() {
		return (String)eGet(CacheTracabilityPackage.Literals.ANALYZED_RESOURCE__MODIFICATION_TIME, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModificationTime(String newModificationTime) {
		eSet(CacheTracabilityPackage.Literals.ANALYZED_RESOURCE__MODIFICATION_TIME, newModificationTime);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TraceabilityLink getContained() {
		return (TraceabilityLink)eGet(CacheTracabilityPackage.Literals.ANALYZED_RESOURCE__CONTAINED, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContained(TraceabilityLink newContained) {
		eSet(CacheTracabilityPackage.Literals.ANALYZED_RESOURCE__CONTAINED, newContained);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<TraceabilityLink> getLinks() {
		return (EList<TraceabilityLink>)eGet(CacheTracabilityPackage.Literals.ANALYZED_RESOURCE__LINKS, true);
	}

} //AnalyzedResourceImpl

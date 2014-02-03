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
import org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.CacheTracabilityPackage;
import org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.Property;
import org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceabilityLink;
import org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceableElement;

import com.google.common.base.Objects;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Traceable Element</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.TraceableElementImpl#getOutgoings <em>Outgoings</em>}</li>
 *   <li>{@link org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.TraceableElementImpl#getIncomings <em>Incomings</em>}</li>
 *   <li>{@link org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.impl.TraceableElementImpl#getProperties <em>Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TraceableElementImpl extends URIElementImpl implements
		TraceableElement {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected TraceableElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CacheTracabilityPackage.Literals.TRACEABLE_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<TraceabilityLink> getOutgoings() {
		return (EList<TraceabilityLink>)eGet(CacheTracabilityPackage.Literals.TRACEABLE_ELEMENT__OUTGOINGS, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<TraceabilityLink> getIncomings() {
		return (EList<TraceabilityLink>)eGet(CacheTracabilityPackage.Literals.TRACEABLE_ELEMENT__INCOMINGS, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Property> getProperties() {
		return (EList<Property>)eGet(CacheTracabilityPackage.Literals.TRACEABLE_ELEMENT__PROPERTIES, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 * 
	 * @generated not
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TraceableElement) {
			TraceableElement te = (TraceableElement) obj;
			return Objects.equal(getUri(), te.getUri());
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
		return Objects.hashCode(getUri());
	}

} // TraceableElementImpl

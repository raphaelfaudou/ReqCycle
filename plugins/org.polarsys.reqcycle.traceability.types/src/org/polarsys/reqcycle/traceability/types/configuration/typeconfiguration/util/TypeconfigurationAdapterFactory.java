/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
/**
 */
package org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.*;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Configuration;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.CustomType;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Entry;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Type;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationPackage
 * @generated
 */
public class TypeconfigurationAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static TypeconfigurationPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeconfigurationAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = TypeconfigurationPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TypeconfigurationSwitch<Adapter> modelSwitch =
		new TypeconfigurationSwitch<Adapter>() {
			@Override
			public Adapter caseTypeConfigContainer(TypeConfigContainer object) {
				return createTypeConfigContainerAdapter();
			}
			@Override
			public Adapter caseType(Type object) {
				return createTypeAdapter();
			}
			@Override
			public Adapter caseRelation(Relation object) {
				return createRelationAdapter();
			}
			@Override
			public Adapter caseConfiguration(Configuration object) {
				return createConfigurationAdapter();
			}
			@Override
			public Adapter caseCustomType(CustomType object) {
				return createCustomTypeAdapter();
			}
			@Override
			public Adapter caseEntry(Entry object) {
				return createEntryAdapter();
			}
			@Override
			public Adapter caseAttribute(Attribute object) {
				return createAttributeAdapter();
			}
			@Override
			public Adapter caseRegisteredAttribute(RegisteredAttribute object) {
				return createRegisteredAttributeAdapter();
			}
			@Override
			public Adapter caseStdAttribute(StdAttribute object) {
				return createStdAttributeAdapter();
			}
			@Override
			public Adapter caseRelationsPredicatesMapping(RelationsPredicatesMapping object) {
				return createRelationsPredicatesMappingAdapter();
			}
			@Override
			public Adapter caseDecorationPredicate(DecorationPredicate object) {
				return createDecorationPredicateAdapter();
			}
			@Override
			public Adapter caseIPredicateLink(IPredicateLink object) {
				return createIPredicateLinkAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer <em>Type Config Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer
	 * @generated
	 */
	public Adapter createTypeConfigContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Type <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Type
	 * @generated
	 */
	public Adapter createTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation <em>Relation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation
	 * @generated
	 */
	public Adapter createRelationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Configuration <em>Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Configuration
	 * @generated
	 */
	public Adapter createConfigurationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.CustomType <em>Custom Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.CustomType
	 * @generated
	 */
	public Adapter createCustomTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Entry <em>Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Entry
	 * @generated
	 */
	public Adapter createEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Attribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Attribute
	 * @generated
	 */
	public Adapter createAttributeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.RegisteredAttribute <em>Registered Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.RegisteredAttribute
	 * @generated
	 */
	public Adapter createRegisteredAttributeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.StdAttribute <em>Std Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.StdAttribute
	 * @generated
	 */
	public Adapter createStdAttributeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.RelationsPredicatesMapping <em>Relations Predicates Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.RelationsPredicatesMapping
	 * @generated
	 */
	public Adapter createRelationsPredicatesMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.DecorationPredicate <em>Decoration Predicate</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.DecorationPredicate
	 * @generated
	 */
	public Adapter createDecorationPredicateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.IPredicateLink <em>IPredicate Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.IPredicateLink
	 * @generated
	 */
	public Adapter createIPredicateLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //TypeconfigurationAdapterFactory

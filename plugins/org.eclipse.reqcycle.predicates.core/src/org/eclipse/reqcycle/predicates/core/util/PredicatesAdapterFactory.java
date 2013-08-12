/**
 */
package org.eclipse.reqcycle.predicates.core.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.reqcycle.predicates.core.PredicatesPackage;
import org.eclipse.reqcycle.predicates.core.api.AndPredicate;
import org.eclipse.reqcycle.predicates.core.api.BooleanEqualPredicate;
import org.eclipse.reqcycle.predicates.core.api.CompareNumberPredicate;
import org.eclipse.reqcycle.predicates.core.api.CompositePredicate;
import org.eclipse.reqcycle.predicates.core.api.ContainsPatternPredicate;
import org.eclipse.reqcycle.predicates.core.api.CustomPredicate;
import org.eclipse.reqcycle.predicates.core.api.DateEqualPredicate;
import org.eclipse.reqcycle.predicates.core.api.EnumEqualPredicate;
import org.eclipse.reqcycle.predicates.core.api.EnumIntoPredicate;
import org.eclipse.reqcycle.predicates.core.api.EqualPredicate;
import org.eclipse.reqcycle.predicates.core.api.IEAttrPredicate;
import org.eclipse.reqcycle.predicates.core.api.IEClassifierPredicate;
import org.eclipse.reqcycle.predicates.core.api.IPredicate;
import org.eclipse.reqcycle.predicates.core.api.ITypedPredicate;
import org.eclipse.reqcycle.predicates.core.api.IntoPredicate;
import org.eclipse.reqcycle.predicates.core.api.NotPredicate;
import org.eclipse.reqcycle.predicates.core.api.OrPredicate;
import org.eclipse.reqcycle.predicates.core.api.StringEqualPredicate;
import org.eclipse.reqcycle.predicates.core.api.StringIntoPredicate;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * 
 * @see org.eclipse.reqcycle.predicates.core.PredicatesPackage
 * @generated
 */
public class PredicatesAdapterFactory extends AdapterFactoryImpl {

	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static PredicatesPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PredicatesAdapterFactory() {
		if(modelPackage == null) {
			modelPackage = PredicatesPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * 
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if(object == modelPackage) {
			return true;
		}
		if(object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected PredicatesSwitch<Adapter> modelSwitch = new PredicatesSwitch<Adapter>() {

		@Override
		public Adapter caseIPredicate(IPredicate object) {
			return createIPredicateAdapter();
		}

		@Override
		public Adapter caseCompositePredicate(CompositePredicate object) {
			return createCompositePredicateAdapter();
		}

		@Override
		public Adapter caseCustomPredicate(CustomPredicate object) {
			return createCustomPredicateAdapter();
		}

		@Override
		public <T extends Object> Adapter caseEqualPredicate(EqualPredicate<T> object) {
			return createEqualPredicateAdapter();
		}

		@Override
		public Adapter caseStringEqualPredicate(StringEqualPredicate object) {
			return createStringEqualPredicateAdapter();
		}

		@Override
		public Adapter caseDateEqualPredicate(DateEqualPredicate object) {
			return createDateEqualPredicateAdapter();
		}

		@Override
		public Adapter caseEnumEqualPredicate(EnumEqualPredicate object) {
			return createEnumEqualPredicateAdapter();
		}

		@Override
		public Adapter caseBooleanEqualPredicate(BooleanEqualPredicate object) {
			return createBooleanEqualPredicateAdapter();
		}

		@Override
		public Adapter caseContainsPatternPredicate(ContainsPatternPredicate object) {
			return createContainsPatternPredicateAdapter();
		}

		@Override
		public <T> Adapter caseIntoPredicate(IntoPredicate<T> object) {
			return createIntoPredicateAdapter();
		}

		@Override
		public Adapter caseStringIntoPredicate(StringIntoPredicate object) {
			return createStringIntoPredicateAdapter();
		}

		@Override
		public Adapter caseEnumIntoPredicate(EnumIntoPredicate object) {
			return createEnumIntoPredicateAdapter();
		}

		@Override
		public Adapter caseAndPredicate(AndPredicate object) {
			return createAndPredicateAdapter();
		}

		@Override
		public Adapter caseOrPredicate(OrPredicate object) {
			return createOrPredicateAdapter();
		}

		@Override
		public Adapter caseNotPredicate(NotPredicate object) {
			return createNotPredicateAdapter();
		}

		@Override
		public Adapter caseCompareNumberPredicate(CompareNumberPredicate object) {
			return createCompareNumberPredicateAdapter();
		}

		@Override
		public <T> Adapter caseITypedPredicate(ITypedPredicate<T> object) {
			return createITypedPredicateAdapter();
		}

		@Override
		public Adapter caseIEAttrPredicate(IEAttrPredicate object) {
			return createIEAttrPredicateAdapter();
		}

		@Override
		public Adapter caseIEClassifierPredicate(IEClassifierPredicate object) {
			return createIEClassifierPredicateAdapter();
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
	 * 
	 * @param target
	 *        the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.reqcycle.predicates.core.api.IPredicate <em>IPredicate</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.reqcycle.predicates.core.api.IPredicate
	 * @generated
	 */
	public Adapter createIPredicateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.reqcycle.predicates.core.api.CompositePredicate <em>Composite Predicate</em>}
	 * '.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.reqcycle.predicates.core.api.CompositePredicate
	 * @generated
	 */
	public Adapter createCompositePredicateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.reqcycle.predicates.core.api.CustomPredicate <em>Custom Predicate</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.reqcycle.predicates.core.api.CustomPredicate
	 * @generated
	 */
	public Adapter createCustomPredicateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.reqcycle.predicates.core.api.EqualPredicate <em>Equal Predicate</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.reqcycle.predicates.core.api.EqualPredicate
	 * @generated
	 */
	public Adapter createEqualPredicateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.reqcycle.predicates.core.api.StringEqualPredicate
	 * <em>String Equal Predicate</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.reqcycle.predicates.core.api.StringEqualPredicate
	 * @generated
	 */
	public Adapter createStringEqualPredicateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.reqcycle.predicates.core.api.DateEqualPredicate <em>Date Equal Predicate</em>}
	 * '.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.reqcycle.predicates.core.api.DateEqualPredicate
	 * @generated
	 */
	public Adapter createDateEqualPredicateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.reqcycle.predicates.core.api.EnumEqualPredicate <em>Enum Equal Predicate</em>}
	 * '.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.reqcycle.predicates.core.api.EnumEqualPredicate
	 * @generated
	 */
	public Adapter createEnumEqualPredicateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.reqcycle.predicates.core.api.BooleanEqualPredicate
	 * <em>Boolean Equal Predicate</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.reqcycle.predicates.core.api.BooleanEqualPredicate
	 * @generated
	 */
	public Adapter createBooleanEqualPredicateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.reqcycle.predicates.core.api.ContainsPatternPredicate
	 * <em>Contains Pattern Predicate</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.reqcycle.predicates.core.api.ContainsPatternPredicate
	 * @generated
	 */
	public Adapter createContainsPatternPredicateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.reqcycle.predicates.core.api.IntoPredicate <em>Into Predicate</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.reqcycle.predicates.core.api.IntoPredicate
	 * @generated
	 */
	public Adapter createIntoPredicateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.reqcycle.predicates.core.api.StringIntoPredicate
	 * <em>String Into Predicate</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.reqcycle.predicates.core.api.StringIntoPredicate
	 * @generated
	 */
	public Adapter createStringIntoPredicateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.reqcycle.predicates.core.api.EnumIntoPredicate <em>Enum Into Predicate</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.reqcycle.predicates.core.api.EnumIntoPredicate
	 * @generated
	 */
	public Adapter createEnumIntoPredicateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.reqcycle.predicates.core.api.AndPredicate <em>And Predicate</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.reqcycle.predicates.core.api.AndPredicate
	 * @generated
	 */
	public Adapter createAndPredicateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.reqcycle.predicates.core.api.OrPredicate <em>Or Predicate</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.reqcycle.predicates.core.api.OrPredicate
	 * @generated
	 */
	public Adapter createOrPredicateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.reqcycle.predicates.core.api.CompareNumberPredicate
	 * <em>Compare Number Predicate</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.reqcycle.predicates.core.api.CompareNumberPredicate
	 * @generated
	 */
	public Adapter createCompareNumberPredicateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.reqcycle.predicates.core.api.ITypedPredicate <em>ITyped Predicate</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.reqcycle.predicates.core.api.ITypedPredicate
	 * @generated
	 */
	public Adapter createITypedPredicateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.reqcycle.predicates.core.api.IEAttrPredicate <em>IE Attr Predicate</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.reqcycle.predicates.core.api.IEAttrPredicate
	 * @generated
	 */
	public Adapter createIEAttrPredicateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.reqcycle.predicates.core.api.IEClassifierPredicate
	 * <em>IE Classifier Predicate</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.reqcycle.predicates.core.api.IEClassifierPredicate
	 * @generated
	 */
	public Adapter createIEClassifierPredicateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.reqcycle.predicates.core.api.NotPredicate <em>Not Predicate</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.reqcycle.predicates.core.api.NotPredicate
	 * @generated
	 */
	public Adapter createNotPredicateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //PredicatesAdapterFactory

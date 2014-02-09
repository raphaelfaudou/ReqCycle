/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Papa Issa Diakhate (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
/**
 */
package org.polarsys.reqcycle.predicates.ui.providers;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemColorProvider;
import org.eclipse.emf.edit.provider.IItemFontProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.polarsys.reqcycle.predicates.core.PredicatesFactory;
import org.polarsys.reqcycle.predicates.core.PredicatesPackage;
import org.polarsys.reqcycle.predicates.core.api.CompositePredicate;
import org.polarsys.reqcycle.predicates.ui.PredicatesUIPlugin;

/**
 * This is the item provider adapter for a {@link org.polarsys.reqcycle.predicates.core.api.CompositePredicate} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class CompositePredicateItemProvider extends PredicatesItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource, IItemColorProvider, IItemFontProvider {

	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public CompositePredicateItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if(itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addDisplayNamePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Display Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addDisplayNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_IPredicate_displayName_feature"), getString("_UI_PropertyDescriptor_description", "_UI_IPredicate_displayName_feature", "_UI_IPredicate_type"), PredicatesPackage.Literals.IPREDICATE__DISPLAY_NAME, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if(childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(PredicatesPackage.Literals.COMPOSITE_PREDICATE__PREDICATES);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((CompositePredicate)object).getDisplayName();
		return label == null || label.length() == 0 ? getString("_UI_CompositePredicate_type") : getString("_UI_CompositePredicate_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch(notification.getFeatureID(CompositePredicate.class)) {
		case PredicatesPackage.COMPOSITE_PREDICATE__DISPLAY_NAME:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case PredicatesPackage.COMPOSITE_PREDICATE__PREDICATES:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
			return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add(createChildParameter(PredicatesPackage.Literals.COMPOSITE_PREDICATE__PREDICATES, PredicatesFactory.eINSTANCE.createStringEqualPredicate()));

		newChildDescriptors.add(createChildParameter(PredicatesPackage.Literals.COMPOSITE_PREDICATE__PREDICATES, PredicatesFactory.eINSTANCE.createDateEqualPredicate()));

		newChildDescriptors.add(createChildParameter(PredicatesPackage.Literals.COMPOSITE_PREDICATE__PREDICATES, PredicatesFactory.eINSTANCE.createEnumEqualPredicate()));

		newChildDescriptors.add(createChildParameter(PredicatesPackage.Literals.COMPOSITE_PREDICATE__PREDICATES, PredicatesFactory.eINSTANCE.createBooleanEqualPredicate()));

		newChildDescriptors.add(createChildParameter(PredicatesPackage.Literals.COMPOSITE_PREDICATE__PREDICATES, PredicatesFactory.eINSTANCE.createContainsPatternPredicate()));

		newChildDescriptors.add(createChildParameter(PredicatesPackage.Literals.COMPOSITE_PREDICATE__PREDICATES, PredicatesFactory.eINSTANCE.createStringIntoPredicate()));

		newChildDescriptors.add(createChildParameter(PredicatesPackage.Literals.COMPOSITE_PREDICATE__PREDICATES, PredicatesFactory.eINSTANCE.createEnumIntoPredicate()));

		newChildDescriptors.add(createChildParameter(PredicatesPackage.Literals.COMPOSITE_PREDICATE__PREDICATES, PredicatesFactory.eINSTANCE.createAndPredicate()));

		newChildDescriptors.add(createChildParameter(PredicatesPackage.Literals.COMPOSITE_PREDICATE__PREDICATES, PredicatesFactory.eINSTANCE.createOrPredicate()));

		newChildDescriptors.add(createChildParameter(PredicatesPackage.Literals.COMPOSITE_PREDICATE__PREDICATES, PredicatesFactory.eINSTANCE.createNotPredicate()));

		newChildDescriptors.add(createChildParameter(PredicatesPackage.Literals.COMPOSITE_PREDICATE__PREDICATES, PredicatesFactory.eINSTANCE.createCompareNumberPredicate()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return PredicatesUIPlugin.INSTANCE;
	}

}

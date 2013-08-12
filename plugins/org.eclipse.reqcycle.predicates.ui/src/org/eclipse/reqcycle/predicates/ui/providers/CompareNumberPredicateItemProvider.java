/**
 */
package org.eclipse.reqcycle.predicates.ui.providers;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
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
import org.eclipse.reqcycle.predicates.core.PredicatesPackage;
import org.eclipse.reqcycle.predicates.core.api.CompareNumberPredicate;

/**
 * This is the item provider adapter for a {@link org.eclipse.reqcycle.predicates.core.api.CompareNumberPredicate} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class CompareNumberPredicateItemProvider extends IEAttrPredicateItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource, IItemColorProvider, IItemFontProvider {

	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public CompareNumberPredicateItemProvider(AdapterFactory adapterFactory) {
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

			addInputPropertyDescriptor(object);
			addExpectedValuePropertyDescriptor(object);
			addOperatorPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Input feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addInputPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_CompareNumberPredicate_input_feature"), getString("_UI_PropertyDescriptor_description", "_UI_CompareNumberPredicate_input_feature", "_UI_CompareNumberPredicate_type"), PredicatesPackage.Literals.COMPARE_NUMBER_PREDICATE__INPUT, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Expected Value feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addExpectedValuePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_CompareNumberPredicate_expectedValue_feature"), getString("_UI_PropertyDescriptor_description", "_UI_CompareNumberPredicate_expectedValue_feature", "_UI_CompareNumberPredicate_type"), PredicatesPackage.Literals.COMPARE_NUMBER_PREDICATE__EXPECTED_VALUE, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Operator feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addOperatorPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_CompareNumberPredicate_operator_feature"), getString("_UI_PropertyDescriptor_description", "_UI_CompareNumberPredicate_operator_feature", "_UI_CompareNumberPredicate_type"), PredicatesPackage.Literals.COMPARE_NUMBER_PREDICATE__OPERATOR, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This returns CompareNumberPredicate.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/CompareNumberPredicate"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {
		Object labelValue = ((CompareNumberPredicate)object).getTypedElement();
		String label = labelValue == null ? null : this.getLabelForEditedPredicate((CompareNumberPredicate)object);
		return label == null || label.length() == 0 ? getString("_UI_CompareNumberPredicate_type") : getString("_UI_CompareNumberPredicate_type") + " " + label;
	}

	private String getLabelForEditedPredicate(final CompareNumberPredicate predicate) {
		StringBuilder label = new StringBuilder("(");
		final Object typedElement = predicate.getTypedElement();
		if(typedElement instanceof EStructuralFeature) {
			EStructuralFeature feature = ((EStructuralFeature)typedElement);
			if(feature.eContainer() instanceof EClass) {
				label.append(((EClass)feature.eContainer()).getName()).append(".");
			}
			label.append(((EStructuralFeature)typedElement).getName()).append(" ").append(predicate.getOperator().getLiteral()).append(" ").append(predicate.getExpectedValue());
		}
		return label.append(")").toString();
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

		switch(notification.getFeatureID(CompareNumberPredicate.class)) {
		case PredicatesPackage.COMPARE_NUMBER_PREDICATE__INPUT:
		case PredicatesPackage.COMPARE_NUMBER_PREDICATE__EXPECTED_VALUE:
		case PredicatesPackage.COMPARE_NUMBER_PREDICATE__OPERATOR:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
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
	}

}

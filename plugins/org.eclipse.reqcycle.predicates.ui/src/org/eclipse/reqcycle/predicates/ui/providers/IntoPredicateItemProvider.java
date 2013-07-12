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
import org.eclipse.reqcycle.predicates.core.api.IntoPredicate;

/**
 * This is the item provider adapter for a {@link org.eclipse.reqcycle.predicates.core.api.IntoPredicate} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class IntoPredicateItemProvider
    extends IEAttrPredicateItemProvider
    implements
        IEditingDomainItemProvider,
        IStructuredItemContentProvider,
        ITreeItemContentProvider,
        IItemLabelProvider,
        IItemPropertySource,
        IItemColorProvider,
        IItemFontProvider {
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IntoPredicateItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * This returns the property descriptors for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
        if (itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

            addInputPropertyDescriptor(object);
            addAllowedEntriesPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Input feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addInputPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_IntoPredicate_input_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_IntoPredicate_input_feature", "_UI_IntoPredicate_type"),
                 PredicatesPackage.Literals.INTO_PREDICATE__INPUT,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Allowed Entries feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addAllowedEntriesPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_IntoPredicate_allowedEntries_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_IntoPredicate_allowedEntries_feature", "_UI_IntoPredicate_type"),
                 PredicatesPackage.Literals.INTO_PREDICATE__ALLOWED_ENTRIES,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((IntoPredicate<?>)object).getDisplayName();
        return label == null || label.length() == 0 ?
            getString("_UI_IntoPredicate_type") :
            getString("_UI_IntoPredicate_type") + " " + label;
    }

    /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached
     * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void notifyChanged(Notification notification) {
        updateChildren(notification);

        switch (notification.getFeatureID(IntoPredicate.class)) {
            case PredicatesPackage.INTO_PREDICATE__INPUT:
            case PredicatesPackage.INTO_PREDICATE__ALLOWED_ENTRIES:
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
     * @generated
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);
    }
    
    protected String getLabelForEditedPredicate(final IntoPredicate<?> predicate) {
        final StringBuilder label = new StringBuilder("(");
        final Object typedElement = predicate.getTypedElement();
        if (typedElement instanceof EStructuralFeature) {
            EStructuralFeature feature = (EStructuralFeature) typedElement;
            if (feature.eContainer() instanceof EClass) {
                label.append(((EClass) feature.eContainer()).getName()).append(".");
            }
            label.append(((EStructuralFeature) typedElement).getName()).append(" is into [");
            boolean dropLastComma = true;
            for (Object obj : predicate.getAllowedEntries()) {
                String incoming = label.toString() + obj.toString();
                if (incoming.length() < 80) {
                    label.append(obj).append(", ");
                } else {
                    label.append("...");
                    dropLastComma = false;
                    break;
                }
            }
            if (dropLastComma) {
                label.replace(label.length() - 2, label.length(), "");
            }
            label.append("]");
        }
        return label.append(")").toString();
    }

}

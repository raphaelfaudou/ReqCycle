package org.eclipse.reqcycle.predicates.ui.providers;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.reqcycle.predicates.core.api.IPredicate;
import org.eclipse.swt.graphics.Image;

public class EnhancedPredicatesTreeLabelProvider implements ILabelProvider {

    private final AdapterFactoryLabelProvider labelProvider;

    public EnhancedPredicatesTreeLabelProvider(AdapterFactory adapterFactory) {
        this.labelProvider = new AdapterFactoryLabelProvider(adapterFactory);
    }

    @Override
    public void addListener(ILabelProviderListener listener) {
        labelProvider.addListener(listener);
    }

    @Override
    public void dispose() {
        labelProvider.dispose();
    }

    @Override
    public boolean isLabelProperty(Object element, String property) {
        return labelProvider.isLabelProperty(element, property);
    }

    @Override
    public void removeListener(ILabelProviderListener listener) {
        labelProvider.removeListener(listener);
    }

    @Override
    public Image getImage(Object element) {
        return labelProvider.getImage(element);
    }

    @Override
    public String getText(Object element) {
        if (element instanceof IPredicate) {
            String displayName = ((IPredicate) element).getDisplayName();
            if (displayName != null) {
                return displayName;
            }
        }
        return labelProvider.getText(element);
    }

}

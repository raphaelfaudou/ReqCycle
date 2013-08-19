package org.eclipse.reqcycle.predicates.ui.providers;

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.reqcycle.predicates.core.api.IPredicate;
import org.eclipse.swt.graphics.Image;

/**
 * A {@link ITableLabelProvider} to use for tables having inputs of type {@link IPredicate}.
 * 
 * @author Papa Issa DIAKHATE
 * 
 */
public class PredicatesTableLabelProvider implements ITableLabelProvider {

	private final ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

	private final AdapterFactoryLabelProvider adapterFactoryLabelProvider = new AdapterFactoryLabelProvider(adapterFactory);

	@Override
	public void addListener(ILabelProviderListener listener) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return true;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return adapterFactoryLabelProvider.getColumnImage(element, columnIndex);
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		if(element instanceof IPredicate) {
			return ((IPredicate)element).getDisplayName();
		}
		return adapterFactoryLabelProvider.getColumnText(element, columnIndex);
	}
}

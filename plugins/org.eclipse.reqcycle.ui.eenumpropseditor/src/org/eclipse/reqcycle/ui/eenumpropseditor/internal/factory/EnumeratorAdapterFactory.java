package org.eclipse.reqcycle.ui.eenumpropseditor.internal.factory;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.reqcycle.ui.eenumpropseditor.internal.AdaptableEEnumLiteral;

public class EnumeratorAdapterFactory implements IAdapterFactory {

    @Override
    public Object getAdapter(Object adaptableObject, @SuppressWarnings("rawtypes") Class adapterType) {
        if (adapterType == Enumerator.class && (adaptableObject instanceof EEnumLiteral)) {
            return new AdaptableEEnumLiteral((EEnumLiteral) adaptableObject).getAdapter(Enumerator.class);
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Class[] getAdapterList() {
        return new Class[] { Enumerator.class };
    }
}

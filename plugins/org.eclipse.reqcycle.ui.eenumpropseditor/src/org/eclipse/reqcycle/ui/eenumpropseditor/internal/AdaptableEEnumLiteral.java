package org.eclipse.reqcycle.ui.eenumpropseditor.internal;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EEnumLiteral;

/**
 * <p>
 * This class helps to adapt an {@link EEnumLiteral} to an {@link Enumerator}.
 * </p>
 * <p>
 * Currently, this the adapting operation is just a proxy oF {@link EEnumLiteral#getInstance()}. But since the
 * {@link EEnumLiteral#getInstance()} may not return a sole and "restricted" Enumerator, using this adapter would be
 * wiser so that if custom implementation is needed, it could be provider from here.
 * </p>
 * 
 * @author Papa Issa DIAKHATE
 * 
 */
public class AdaptableEEnumLiteral implements IAdaptable {

    private final EEnumLiteral eEnumLiteral;

    public AdaptableEEnumLiteral(EEnumLiteral eEnumLiteral) {
        this.eEnumLiteral = eEnumLiteral;
    }

    @Override
    public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
        if (adapter == Enumerator.class) {
            return eEnumLiteral.getInstance();
        }
        return null;
    }

}

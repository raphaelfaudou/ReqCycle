package org.eclipse.reqcycle.ui.eenumpropseditor.internal;

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditor;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditorComponent;
import org.eclipse.reqcycle.ui.eenumpropseditor.internal.components.EEnumPropsEditorComponent;

public class EEnumPropsEditor extends AbstractPropsEditor<Enumerator> {

    @Override
    protected AbstractPropsEditorComponent<Enumerator> initAndGetComponent() {
        return new EEnumPropsEditorComponent(getEAttribute(), getContainer(), getStyle());
    }

}

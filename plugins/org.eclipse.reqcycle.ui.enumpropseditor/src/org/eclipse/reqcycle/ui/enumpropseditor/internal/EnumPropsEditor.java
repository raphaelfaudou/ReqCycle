package org.eclipse.reqcycle.ui.enumpropseditor.internal;

import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditor;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditorComponent;
import org.eclipse.reqcycle.ui.enumpropseditor.internal.components.EnumPropsEditorComponent;

public class EnumPropsEditor extends AbstractPropsEditor<Enum<?>> {

    @Override
    protected AbstractPropsEditorComponent<Enum<?>> initAndGetComponent() {
        return new EnumPropsEditorComponent(getEAttribute(), getContainer(), getStyle());
    }

}

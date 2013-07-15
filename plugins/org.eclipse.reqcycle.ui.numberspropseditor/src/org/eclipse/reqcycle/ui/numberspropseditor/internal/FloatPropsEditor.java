package org.eclipse.reqcycle.ui.numberspropseditor.internal;

import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditor;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditorComponent;
import org.eclipse.reqcycle.ui.numberspropseditor.internal.components.FloatPropsEditorComponent;

public class FloatPropsEditor extends AbstractPropsEditor<Float> {

    @Override
    protected AbstractPropsEditorComponent<Float> initAndGetComponent() {
        return new FloatPropsEditorComponent(getEAttribute(), getContainer(), getStyle());
    }

}

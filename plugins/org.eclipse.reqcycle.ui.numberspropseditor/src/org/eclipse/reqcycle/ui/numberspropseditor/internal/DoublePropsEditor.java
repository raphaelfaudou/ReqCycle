package org.eclipse.reqcycle.ui.numberspropseditor.internal;

import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditor;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditorComponent;
import org.eclipse.reqcycle.ui.numberspropseditor.internal.components.DoublePropsEditorComponent;

public class DoublePropsEditor extends AbstractPropsEditor<Double> {

    @Override
    protected AbstractPropsEditorComponent<Double> initAndGetComponent() {
        return new DoublePropsEditorComponent(getEAttribute(), getContainer(), getStyle());
    }

}

package org.eclipse.reqcycle.ui.numberspropseditor.internal;

import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditor;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditorComponent;
import org.eclipse.reqcycle.ui.numberspropseditor.internal.components.IntegerPropsEditorComponent;

public class IntegerPropsEditor extends AbstractPropsEditor<Integer> {

    @Override
    protected AbstractPropsEditorComponent<Integer> initAndGetComponent() {
        return new IntegerPropsEditorComponent(getEAttribute(), getContainer(), getStyle());
    }

}

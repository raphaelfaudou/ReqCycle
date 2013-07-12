package org.eclipse.reqcycle.ui.numberspropseditor.internal;

import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditor;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditorComponent;
import org.eclipse.reqcycle.ui.numberspropseditor.internal.components.LongPropsEditorComponent;

public class LongPropsEditor extends AbstractPropsEditor<Long> {

    @Override
    protected AbstractPropsEditorComponent<Long> initAndGetComponent() {
        return new LongPropsEditorComponent(getEAttribute(), getContainer(), getStyle());
    }

}

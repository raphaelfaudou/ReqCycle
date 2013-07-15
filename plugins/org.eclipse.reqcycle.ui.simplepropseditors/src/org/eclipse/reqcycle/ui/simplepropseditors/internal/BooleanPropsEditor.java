package org.eclipse.reqcycle.ui.simplepropseditors.internal;

import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditor;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditorComponent;
import org.eclipse.reqcycle.ui.simplepropseditors.internal.components.BooleanPropsEditorComponent;

public class BooleanPropsEditor extends AbstractPropsEditor<Boolean> {

    @Override
    protected AbstractPropsEditorComponent<Boolean> initAndGetComponent() {
        return new BooleanPropsEditorComponent(getEAttribute(), getContainer(), getStyle());
    }

}

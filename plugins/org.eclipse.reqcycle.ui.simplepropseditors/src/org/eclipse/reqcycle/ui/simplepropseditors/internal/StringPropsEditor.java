package org.eclipse.reqcycle.ui.simplepropseditors.internal;

import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditorComponent;
import org.eclipse.reqcycle.ui.simplepropseditors.internal.components.StringPropsEditorComponent;

public class StringPropsEditor extends CharSequencePropsEditor {

    @Override
    protected AbstractPropsEditorComponent<CharSequence> initAndGetComponent() {
        return new StringPropsEditorComponent(getEAttribute(), getContainer(), getStyle());
    }

}

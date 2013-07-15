package org.eclipse.reqcycle.ui.simplepropseditors.internal;

import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditor;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditorComponent;
import org.eclipse.reqcycle.ui.simplepropseditors.internal.components.CharSequencePropsEditorComponent;

public class CharSequencePropsEditor extends AbstractPropsEditor<CharSequence> {

    @Override
    protected AbstractPropsEditorComponent<CharSequence> initAndGetComponent() {
        return new CharSequencePropsEditorComponent(getEAttribute(), getContainer(), getStyle());
    }

}

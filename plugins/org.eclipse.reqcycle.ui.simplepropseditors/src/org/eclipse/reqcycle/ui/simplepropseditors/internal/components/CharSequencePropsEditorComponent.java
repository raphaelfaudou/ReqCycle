package org.eclipse.reqcycle.ui.simplepropseditors.internal.components;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsTextEditorComponent;
import org.eclipse.swt.widgets.Composite;

public class CharSequencePropsEditorComponent extends AbstractPropsTextEditorComponent<CharSequence> {

    public CharSequencePropsEditorComponent(EAttribute attribute, Composite parent, int style) {
        super(attribute, parent, style);
    }

    @Override
    protected CharSequence convertFromString(String textValue) {
        return textValue;
    }

    @Override
    protected boolean isTextValid(String textValue) {
        return true;
    }

}

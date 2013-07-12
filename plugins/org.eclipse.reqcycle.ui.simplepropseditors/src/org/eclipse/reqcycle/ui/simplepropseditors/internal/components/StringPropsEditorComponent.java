package org.eclipse.reqcycle.ui.simplepropseditors.internal.components;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.swt.widgets.Composite;

public class StringPropsEditorComponent extends CharSequencePropsEditorComponent {

    public StringPropsEditorComponent(EAttribute attribute, Composite parent, int style) {
        super(attribute, parent, style);
    }

    @Override
    protected String convertFromString(String textValue) {
        return textValue;
    }

}

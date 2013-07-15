package org.eclipse.reqcycle.ui.numberspropseditor.internal.components;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsTextEditorComponent;
import org.eclipse.swt.widgets.Composite;

public class LongPropsEditorComponent extends AbstractPropsTextEditorComponent<Long> {

    private String errorMessage;

    public LongPropsEditorComponent(EAttribute attribute, Composite parent, int style) {
        super(attribute, parent, style);
    }

    @Override
    protected Long convertFromString(String textValue) {
        try {
            return Long.parseLong(textValue);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    protected boolean isTextValid(String textValue) {
        try {
            Long.parseLong(textValue);
            return true;
        } catch (NumberFormatException e) {
            this.errorMessage = "Not a long. " + e.getMessage();
            return false;
        }
    }

    @Override
    protected String getErrorMessage() {
        return this.errorMessage;
    }

}

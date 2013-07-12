package org.eclipse.reqcycle.ui.numberspropseditor.internal.components;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsTextEditorComponent;
import org.eclipse.swt.widgets.Composite;

public class FloatPropsEditorComponent extends AbstractPropsTextEditorComponent<Float> {

    private String errorMessage;

    public FloatPropsEditorComponent(EAttribute attribute, Composite parent, int style) {
        super(attribute, parent, style);
    }

    @Override
    protected Float convertFromString(String textValue) {
        try {
            return Float.parseFloat(textValue);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    protected boolean isTextValid(String textValue) {
        try {
            Float.parseFloat(textValue);
            return true;
        } catch (NumberFormatException e) {
            this.errorMessage = "Not a float. " + e.getMessage();
            return false;
        }
    }

    @Override
    protected String getErrorMessage() {
        return this.errorMessage;
    }

}

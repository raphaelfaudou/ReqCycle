package org.eclipse.reqcycle.ui.numberspropseditor.internal.components;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsTextEditorComponent;
import org.eclipse.swt.widgets.Composite;

public class DoublePropsEditorComponent extends AbstractPropsTextEditorComponent<Double> {

    private String errorMessage;

    public DoublePropsEditorComponent(EAttribute attribute, Composite parent, int style) {
        super(attribute, parent, style);
    }

    @Override
    protected Double convertFromString(String textValue) {
        try {
            return Double.parseDouble(textValue);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    protected boolean isTextValid(String textValue) {
        try {
            Double.parseDouble(textValue);
            return true;
        } catch (NumberFormatException e) {
            this.errorMessage = "Not a double. " + e.getMessage();
            return false;
        }
    }

    @Override
    protected String getErrorMessage() {
        return this.errorMessage;
    }

}

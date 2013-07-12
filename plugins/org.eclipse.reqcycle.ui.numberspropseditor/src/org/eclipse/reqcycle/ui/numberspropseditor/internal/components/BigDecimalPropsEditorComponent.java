package org.eclipse.reqcycle.ui.numberspropseditor.internal.components;

import java.math.BigDecimal;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsTextEditorComponent;
import org.eclipse.swt.widgets.Composite;

public class BigDecimalPropsEditorComponent extends AbstractPropsTextEditorComponent<BigDecimal> {

    private String errorMessage;

    public BigDecimalPropsEditorComponent(EAttribute attribute, Composite parent, int style) {
        super(attribute, parent, style);
    }

    @Override
    protected BigDecimal convertFromString(String textValue) {
        try {
            return new BigDecimal(textValue);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    protected boolean isTextValid(String textValue) {
        try {
            new BigDecimal(textValue);
            return true;
        } catch (NumberFormatException e) {
            this.errorMessage = e.getMessage();
            return false;
        }
    }

    @Override
    protected String getErrorMessage() {
        return this.errorMessage;
    }

}

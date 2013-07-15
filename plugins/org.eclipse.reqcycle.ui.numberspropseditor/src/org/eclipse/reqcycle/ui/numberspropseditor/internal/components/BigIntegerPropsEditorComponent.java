package org.eclipse.reqcycle.ui.numberspropseditor.internal.components;

import java.math.BigInteger;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsTextEditorComponent;
import org.eclipse.swt.widgets.Composite;

public class BigIntegerPropsEditorComponent extends AbstractPropsTextEditorComponent<BigInteger> {

    private String errorMessage;

    public BigIntegerPropsEditorComponent(EAttribute attribute, Composite parent, int style) {
        super(attribute, parent, style);
    }

    @Override
    protected BigInteger convertFromString(String textValue) {
        try {
            return new BigInteger(textValue);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    protected boolean isTextValid(String textValue) {
        try {
            new BigInteger(textValue);
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

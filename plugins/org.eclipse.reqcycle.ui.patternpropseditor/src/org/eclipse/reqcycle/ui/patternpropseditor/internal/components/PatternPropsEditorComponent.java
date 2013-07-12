package org.eclipse.reqcycle.ui.patternpropseditor.internal.components;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsTextEditorComponent;
import org.eclipse.swt.widgets.Composite;

public class PatternPropsEditorComponent extends AbstractPropsTextEditorComponent<Pattern> {

    private String errorMessage;

    public PatternPropsEditorComponent(EAttribute attribute, Composite parent, int style) {
        super(attribute, parent, style);
    }

    @Override
    protected Pattern convertFromString(String textValue) {
        try {
            return Pattern.compile(textValue);
        } catch (PatternSyntaxException e) {
            return null;
        }
    }

    @Override
    protected boolean isTextValid(String textValue) {
        try {
            Pattern.compile(textValue);
            return true;
        } catch (PatternSyntaxException e) {
            this.errorMessage = "The pattern is not valid. " + e.getMessage();
            return false;
        }
    }

    @Override
    protected String getErrorMessage() {
        return this.errorMessage;
    }

}

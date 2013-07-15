package org.eclipse.reqcycle.ui.patternpropseditor.internal;

import java.util.regex.Pattern;

import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditor;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditorComponent;
import org.eclipse.reqcycle.ui.patternpropseditor.internal.components.PatternPropsEditorComponent;

public class PatternPropsEditor extends AbstractPropsEditor<Pattern> {

    @Override
    protected AbstractPropsEditorComponent<Pattern> initAndGetComponent() {
        return new PatternPropsEditorComponent(getEAttribute(), getContainer(), getStyle());
    }

}

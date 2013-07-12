package org.eclipse.reqcycle.ui.datepropseditor.internal;

import java.util.Date;

import org.eclipse.reqcycle.ui.datepropseditor.internal.components.DatePropsEditorComponent;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditor;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditorComponent;

public class DatePropsEditor extends AbstractPropsEditor<Date> {

    @Override
    protected AbstractPropsEditorComponent<Date> initAndGetComponent() {
        return new DatePropsEditorComponent(getEAttribute(), getContainer(), getStyle());
    }

}

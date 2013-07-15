package org.eclipse.reqcycle.ui.datepropseditor.internal;

import java.util.Calendar;

import org.eclipse.reqcycle.ui.datepropseditor.internal.components.CalendarPropsEditorComponent;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditor;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditorComponent;

public class CalendarPropsEditor extends AbstractPropsEditor<Calendar> {

    @Override
    protected AbstractPropsEditorComponent<Calendar> initAndGetComponent() {
        return new CalendarPropsEditorComponent(getEAttribute(), getContainer(), getStyle());
    }

}

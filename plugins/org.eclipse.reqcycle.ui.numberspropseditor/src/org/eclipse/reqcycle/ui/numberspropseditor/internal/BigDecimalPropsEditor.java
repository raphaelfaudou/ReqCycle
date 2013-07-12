package org.eclipse.reqcycle.ui.numberspropseditor.internal;

import java.math.BigDecimal;

import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditor;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditorComponent;
import org.eclipse.reqcycle.ui.numberspropseditor.internal.components.BigDecimalPropsEditorComponent;

public class BigDecimalPropsEditor extends AbstractPropsEditor<BigDecimal> {

    @Override
    protected AbstractPropsEditorComponent<BigDecimal> initAndGetComponent() {
        return new BigDecimalPropsEditorComponent(getEAttribute(), getContainer(), getStyle());
    }

}

package org.eclipse.reqcycle.ui.numberspropseditor.internal;

import java.math.BigInteger;

import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditor;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditorComponent;
import org.eclipse.reqcycle.ui.numberspropseditor.internal.components.BigIntegerPropsEditorComponent;

public class BigIntegerPropsEditor extends AbstractPropsEditor<BigInteger> {

    @Override
    protected AbstractPropsEditorComponent<BigInteger> initAndGetComponent() {
        return new BigIntegerPropsEditorComponent(getEAttribute(), getContainer(), getStyle());
    }
}

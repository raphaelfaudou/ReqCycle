package org.eclipse.reqcycle.ui.collectionspropseditor.internal;

import java.util.Collection;

import org.eclipse.reqcycle.ui.collectionspropseditor.internal.components.CollectionsPropsEditorComponent;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditor;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditorComponent;

public class CollectionPropsEditor extends AbstractPropsEditor<Collection<Object>> {

    @Override
    protected AbstractPropsEditorComponent<Collection<Object>> initAndGetComponent() {
        return new CollectionsPropsEditorComponent(getEAttribute(), getContainer(), getStyle());
    }

}

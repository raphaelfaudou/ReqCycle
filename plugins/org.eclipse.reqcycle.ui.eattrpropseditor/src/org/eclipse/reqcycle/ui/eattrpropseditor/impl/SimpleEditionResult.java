package org.eclipse.reqcycle.ui.eattrpropseditor.impl;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.IEditionResult;

public class SimpleEditionResult implements IEditionResult {

    private final EObject                         editObject;

    private final Map<EStructuralFeature, Object> editedEntries;

    public SimpleEditionResult(final EObject eObject) {
        this.editObject = eObject;
        this.editedEntries = new HashMap<EStructuralFeature, Object>();
    }

    @Override
    public EObject getEObjectToEdit() {
        return this.editObject;
    }

    @Override
    public Map<EStructuralFeature, Object> getEditedEntries() {
        return this.editedEntries;
    }

}

package org.eclipse.reqcycle.ui.eattrpropseditor.api;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Represents the result of the edition done into an {@link IEAttrPropsEditor}.
 * 
 * @author Papa Issa DIAKHATE
 */
public interface IEditionResult {

    /**
     * @return The {@link EObject} that is to be edited.
     */
    EObject getEObjectToEdit();

    /**
     * @return The Map containing the edited {@link EStructuralFeature} of the <tt>EObject</tt>.
     */
    Map<EStructuralFeature, Object> getEditedEntries();

}

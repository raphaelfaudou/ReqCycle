package org.eclipse.reqcycle.ui.eattrpropseditor.api;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.swt.widgets.Composite;

/**
 * A simple and abstract UI component that represent an editor with no widgets.
 * 
 * @author Papa Issa DIAKHATE
 * 
 * @param <T> - Type type for which this component is to be used for edition.
 */
public abstract class AbstractPropsEditorComponent<T> extends Composite {

    private T value;

    public AbstractPropsEditorComponent(final EAttribute attribute, final Composite parent, final int style) {
        super(parent, style);
    }

    /**
     * @return The value of the input value set from this properties editor.
     */
    public T getValue() {
        return this.value;
    }

    /**
     * 
     * @param value - The current input value set from this properties editor. This value will be returned by
     *            {@link #getValue()}.
     */
    protected void setValue(T value) {
        this.value = value;
    }

    /**
     * @return <code>true</code> if the edited text value is valid according to the type that this editor supports,
     *         <code>false</code> otherwise.
     */
    abstract public boolean isValid();

}

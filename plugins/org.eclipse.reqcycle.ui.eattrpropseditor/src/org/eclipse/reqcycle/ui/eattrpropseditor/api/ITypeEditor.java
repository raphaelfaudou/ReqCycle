package org.eclipse.reqcycle.ui.eattrpropseditor.api;

import org.eclipse.swt.widgets.Composite;

/**
 * 
 * @author Papa Issa DIAKHATE
 * 
 * @param <T>
 *            - The type of the object for which this editor is to be used.
 */
public interface ITypeEditor<T> {

	// /**
	// * @param attribute - The attribute for which the editor will be used.
	// */
	// void setEAttribute(final EAttribute attribute);

	/**
	 * @return The UI component that represents the editor.
	 */
	Composite getEditor();

	/**
	 * @return
	 */
	T getValue();

	/**
	 * @return <code>true</code> if the edition is valid, <code>false</code>
	 *         otherwise.
	 */
	boolean isValid();

	/**
	 * @return The parent component that contains the editor.
	 */
	Composite getContainer();

	/**
	 * Sets the parent component that must contain the editor.
	 * 
	 * @param container
	 */
	void setContainer(final Composite container);

	/**
	 * @return The style applied to the editor.
	 */
	int getStyle();

	/**
	 * Sets the style to apply to the editor.
	 * 
	 * @param style
	 */
	void setStyle(final int style);
}

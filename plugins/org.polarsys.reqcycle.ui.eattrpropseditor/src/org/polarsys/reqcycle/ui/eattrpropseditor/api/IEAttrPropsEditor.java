/*******************************************************************************
 *  * Copyright (c) 2013-2014 AtoS.
 *  * All rights reserved. This program and the accompanying materials
 *  * are made available under the terms of the Eclipse Public License v1.0
 *  * which accompanies this distribution, and is available at
 *  * http://www.eclipse.org/legal/epl-v10.html
 *  *
 *  * Contributors:
 *  *    {INITIAL AUTHOR} - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.ui.eattrpropseditor.api;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.swt.widgets.Composite;

/**
 * 
 * @author Papa Issa DIAKHATE
 * 
 * @param <T>
 *        - The type of the object for which this editor is to be used.
 */
public interface IEAttrPropsEditor<T> {

	/**
	 * @param attribute
	 *        - The attribute for which the editor will be used.
	 */
	void setEAttribute(final EAttribute attribute);

	/**
	 * @return The UI component that represents the editor.
	 */
	Composite getEditor();

	/**
	 * @return
	 */
	T getValue();

	/**
	 * @return <code>true</code> if the edition is valid, <code>false</code> otherwise.
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

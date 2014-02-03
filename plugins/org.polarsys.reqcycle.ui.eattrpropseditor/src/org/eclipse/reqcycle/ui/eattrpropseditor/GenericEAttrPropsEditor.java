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
package org.eclipse.reqcycle.ui.eattrpropseditor;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.IEAttrPropsEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * <p>
 * This is a generic SWT Composite that holds an {@link IEAttrPropsEditor}. When setting the attribute and optionally the javaClass type of the
 * attribute which is to be editing, this class GenericEAttrPropsEditor will instantiate the correct IEAttrPropsEditor to use and show to the end
 * user. The IEAttrPropsEditor which is used is search into the available implementations. In order to set the attribute which will trigger this class
 * to use the correct properties editor, just & simply use the {@link #init(EAttribute, String)} method.
 * </p>
 * <p>
 * <b>NOTE :</b> It is then by far easier to use this class instead of the trying to use directly a IEAttrPropsEditor which would require to know the
 * type of the attribute first and also know whether or not an IEAttrPropsEditor is available for editing that attribute.
 * </p>
 * 
 * @author Papa Issa DIAKHATE
 */
public class GenericEAttrPropsEditor extends Composite {

	private EAttribute eAttribute;

	private IEAttrPropsEditor<?> propsEditor;

	private Composite currentComponent;

	public GenericEAttrPropsEditor(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
	}

	/**
	 * Initialize the editor by specifying the attribute to edit and the editor type to use.
	 * 
	 * @param eAttribute
	 *        - The attribute to edit.
	 * @param javaClassTypeName
	 *        - The javaClassType of the attribute. Optional, and so may be <tt>null</tt>. If not <code>null</code>, then no further lookup will be
	 *        done onto the attribute in order to retrieve its
	 *        java class type, but the specified javaClassType will be used directly instead.
	 * @see EAttrPropsEditorPlugin#getStructuralFeatureEditor(EAttribute, String, Composite, int)
	 */
	public void init(final EAttribute eAttribute, final String javaClassTypeName) {
		this.eAttribute = eAttribute;
		this.propsEditor = EAttrPropsEditorPlugin.getStructuralFeatureEditor(eAttribute, javaClassTypeName, this, getStyle());
		if(this.currentComponent != null) {
			this.currentComponent.dispose();
		}
		if(this.propsEditor != null) {
			this.currentComponent = this.propsEditor.getEditor();
			this.currentComponent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
			this.layout();
		}
	}

	/**
	 * @return The {@link EAttribute} (not modified).
	 */
	public EAttribute getEAttribute() {
		return this.eAttribute;
	}

	/**
	 * @return The value that has been set for the eAttribute.
	 */
	public Object getEnteredValue() {
		if(this.propsEditor != null) {
			return this.propsEditor.getValue();
		}
		return null;
	}

	/**
	 * This result of this method is delegated to {@link IEAttrPropsEditor#isValid()}.
	 * 
	 * @return true if the edition is valid, false otherwise.
	 * @see IEAttrPropsEditor#isValid()
	 */
	public boolean isEditionValid() {
		return this.propsEditor.isValid();
	}

}

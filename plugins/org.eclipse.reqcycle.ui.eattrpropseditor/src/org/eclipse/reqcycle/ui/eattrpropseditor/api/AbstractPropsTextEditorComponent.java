/*****************************************************************************
 * Copyright (c) 2013 AtoS.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Papa Issa DIAKHATE (AtoS) papa-issa.diakhate@atos.net - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.reqcycle.ui.eattrpropseditor.api;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * A simple and abstract UI component that represent an editor composed of a simple {@link Label} and a {@link Text}.
 * 
 * @author Papa Issa DIAKHATE
 * 
 * @param <T>
 *        - Type type for which this component is to be used for edition.
 */
public abstract class AbstractPropsTextEditorComponent<T> extends AbstractPropsEditorComponent<T> {

	private static final Image ERROR_IMAGE = FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry.DEC_ERROR).getImage();

	private final Text text;

	private ControlDecoration controlDecoration;

	private String currentTextValue;

	public AbstractPropsTextEditorComponent(final EAttribute attribute, final Composite parent, final int style) {

		super(attribute, parent, style);
		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.marginRight = 10;
		setLayout(gridLayout);

		Label lblName = new Label(this, SWT.NONE);
		lblName.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblName.setText(attribute.getName());

		text = new Text(this, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		if(useControlDecoration()) {
			this.controlDecoration = new ControlDecoration(text, SWT.RIGHT | SWT.TOP);
			this.controlDecoration.setShowOnlyOnFocus(true);
			this.controlDecoration.setShowHover(true);
			this.controlDecoration.setMarginWidth(3);
		}

		text.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				currentTextValue = text.getText();
				if(isTextValid(currentTextValue)) {
					if(useControlDecoration()) {
						getControlDecoration().setImage(null);
					}
					setValue(convertFromString(currentTextValue));
				} else {
					if(useControlDecoration()) {
						getControlDecoration().setImage(ERROR_IMAGE);
						getControlDecoration().setDescriptionText(getErrorMessage());
					}
					setValue(null);
				}
			}
		});
	}

	@Override
	public boolean isValid() {
		return isTextValid(currentTextValue);
	}

	/**
	 * @param textValue
	 *        - The current text value entered into the {@link Text} widget.
	 * @return The converted value from the text content. May be <tt>null</tt>.
	 */
	abstract protected T convertFromString(final String textValue);

	/**
	 * @param textValue
	 *        - The current text value entered into the {@link Text} widget.
	 * @return <code>true</code> if the text value is valid according to the expected supported type, <code>false</code> otherwise.
	 */
	abstract protected boolean isTextValid(final String textValue);

	protected boolean useControlDecoration() {
		return true;
	}

	protected ControlDecoration getControlDecoration() {
		return this.controlDecoration;
	}

	/**
	 * @return The message to display by the control decoration whenever the input is not valid.
	 */
	protected String getErrorMessage() {
		return "The input is not valid.";
	}

}

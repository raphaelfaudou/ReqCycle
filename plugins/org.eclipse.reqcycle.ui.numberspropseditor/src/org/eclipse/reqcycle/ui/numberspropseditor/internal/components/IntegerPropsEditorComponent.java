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
package org.eclipse.reqcycle.ui.numberspropseditor.internal.components;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsTextEditorComponent;
import org.eclipse.swt.widgets.Composite;

public class IntegerPropsEditorComponent extends AbstractPropsTextEditorComponent<Integer> {

	private String errorMessage;

	public IntegerPropsEditorComponent(EAttribute attribute, Composite parent, int style) {
		super(attribute, parent, style);
	}

	@Override
	protected Integer convertFromString(String textValue) {
		try {
			return Integer.parseInt(textValue);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	@Override
	protected boolean isTextValid(String textValue) {
		try {
			Integer.parseInt(textValue);
			return true;
		} catch (NumberFormatException e) {
			this.errorMessage = "Not an int. " + e.getMessage();
			return false;
		}
	}

	@Override
	protected String getErrorMessage() {
		return this.errorMessage;
	}

}

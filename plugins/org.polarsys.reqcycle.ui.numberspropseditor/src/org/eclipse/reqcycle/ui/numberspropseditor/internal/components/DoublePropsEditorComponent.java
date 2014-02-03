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
package org.eclipse.reqcycle.ui.numberspropseditor.internal.components;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsTextEditorComponent;
import org.eclipse.swt.widgets.Composite;

public class DoublePropsEditorComponent extends AbstractPropsTextEditorComponent<Double> {

	private String errorMessage;

	public DoublePropsEditorComponent(EAttribute attribute, Composite parent, int style) {
		super(attribute, parent, style);
	}

	@Override
	protected Double convertFromString(String textValue) {
		try {
			return Double.parseDouble(textValue);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	@Override
	protected boolean isTextValid(String textValue) {
		try {
			Double.parseDouble(textValue);
			return true;
		} catch (NumberFormatException e) {
			this.errorMessage = "Not a double. " + e.getMessage();
			return false;
		}
	}

	@Override
	protected String getErrorMessage() {
		return this.errorMessage;
	}

}

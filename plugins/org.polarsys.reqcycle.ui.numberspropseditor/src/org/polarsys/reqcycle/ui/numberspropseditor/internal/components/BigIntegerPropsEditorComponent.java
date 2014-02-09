/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Papa Issa Diakhate (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.ui.numberspropseditor.internal.components;

import java.math.BigInteger;

import org.eclipse.emf.ecore.EAttribute;
import org.polarsys.reqcycle.ui.eattrpropseditor.api.AbstractPropsTextEditorComponent;
import org.eclipse.swt.widgets.Composite;

public class BigIntegerPropsEditorComponent extends AbstractPropsTextEditorComponent<BigInteger> {

	private String errorMessage;

	public BigIntegerPropsEditorComponent(EAttribute attribute, Composite parent, int style) {
		super(attribute, parent, style);
	}

	@Override
	protected BigInteger convertFromString(String textValue) {
		try {
			return new BigInteger(textValue);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	@Override
	protected boolean isTextValid(String textValue) {
		try {
			new BigInteger(textValue);
			return true;
		} catch (NumberFormatException e) {
			this.errorMessage = e.getMessage();
			return false;
		}
	}

	@Override
	protected String getErrorMessage() {
		return this.errorMessage;
	}

}

/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Papa Issa Diakhate (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.ui.simplepropseditors.internal.components;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.swt.widgets.Composite;

public class StringPropsEditorComponent extends CharSequencePropsEditorComponent {

	public StringPropsEditorComponent(EAttribute attribute, Composite parent, int style) {
		super(attribute, parent, style);
	}

	@Override
	protected String convertFromString(String textValue) {
		return textValue;
	}

}

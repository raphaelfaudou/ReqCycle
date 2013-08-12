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
package org.eclipse.reqcycle.ui.datepropseditor.internal.components;

import java.util.Calendar;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditorComponent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class CalendarPropsEditorComponent extends AbstractPropsEditorComponent<Calendar> {

	private final CommonDatePropsEditor commonDatePropsEditor;

	public CalendarPropsEditorComponent(EAttribute attribute, Composite parent, int style) {
		super(attribute, parent, style);
		setLayout(new GridLayout(1, false));
		this.commonDatePropsEditor = new CommonDatePropsEditor(attribute, this, getStyle());
		this.commonDatePropsEditor.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	}

	@Override
	public Calendar getValue() {
		return this.commonDatePropsEditor.getCalendarValue();
	}

	@Override
	public boolean isValid() {
		return true;
	}
}

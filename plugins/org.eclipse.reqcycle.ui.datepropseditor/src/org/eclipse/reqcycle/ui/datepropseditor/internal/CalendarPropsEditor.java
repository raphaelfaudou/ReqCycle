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
package org.eclipse.reqcycle.ui.datepropseditor.internal;

import java.util.Calendar;

import org.eclipse.reqcycle.ui.datepropseditor.internal.components.CalendarPropsEditorComponent;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditor;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditorComponent;

public class CalendarPropsEditor extends AbstractPropsEditor<Calendar> {

	@Override
	protected AbstractPropsEditorComponent<Calendar> initAndGetComponent() {
		return new CalendarPropsEditorComponent(getEAttribute(), getContainer(), getStyle());
	}

}

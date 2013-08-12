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

import java.util.Date;

import org.eclipse.reqcycle.ui.datepropseditor.internal.components.DatePropsEditorComponent;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditor;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditorComponent;

public class DatePropsEditor extends AbstractPropsEditor<Date> {

	@Override
	protected AbstractPropsEditorComponent<Date> initAndGetComponent() {
		return new DatePropsEditorComponent(getEAttribute(), getContainer(), getStyle());
	}

}

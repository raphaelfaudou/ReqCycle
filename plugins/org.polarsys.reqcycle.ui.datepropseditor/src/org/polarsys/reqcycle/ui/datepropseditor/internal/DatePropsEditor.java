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
package org.polarsys.reqcycle.ui.datepropseditor.internal;

import java.util.Date;

import org.polarsys.reqcycle.ui.datepropseditor.internal.components.DatePropsEditorComponent;
import org.polarsys.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditor;
import org.polarsys.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditorComponent;

public class DatePropsEditor extends AbstractPropsEditor<Date> {

	@Override
	protected AbstractPropsEditorComponent<Date> initAndGetComponent() {
		return new DatePropsEditorComponent(getEAttribute(), getContainer(), getStyle());
	}

}

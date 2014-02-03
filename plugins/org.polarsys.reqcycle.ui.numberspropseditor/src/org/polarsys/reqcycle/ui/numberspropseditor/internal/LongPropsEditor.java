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
package org.polarsys.reqcycle.ui.numberspropseditor.internal;

import org.polarsys.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditor;
import org.polarsys.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditorComponent;
import org.polarsys.reqcycle.ui.numberspropseditor.internal.components.LongPropsEditorComponent;

public class LongPropsEditor extends AbstractPropsEditor<Long> {

	@Override
	protected AbstractPropsEditorComponent<Long> initAndGetComponent() {
		return new LongPropsEditorComponent(getEAttribute(), getContainer(), getStyle());
	}

}
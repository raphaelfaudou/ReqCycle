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
package org.eclipse.reqcycle.ui.numberspropseditor.internal;

import java.math.BigDecimal;

import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditor;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditorComponent;
import org.eclipse.reqcycle.ui.numberspropseditor.internal.components.BigDecimalPropsEditorComponent;

public class BigDecimalPropsEditor extends AbstractPropsEditor<BigDecimal> {

	@Override
	protected AbstractPropsEditorComponent<BigDecimal> initAndGetComponent() {
		return new BigDecimalPropsEditorComponent(getEAttribute(), getContainer(), getStyle());
	}

}

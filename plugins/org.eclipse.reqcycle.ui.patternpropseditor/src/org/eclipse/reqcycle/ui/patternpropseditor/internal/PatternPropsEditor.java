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
package org.eclipse.reqcycle.ui.patternpropseditor.internal;

import java.util.regex.Pattern;

import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditor;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditorComponent;
import org.eclipse.reqcycle.ui.patternpropseditor.internal.components.PatternPropsEditorComponent;

public class PatternPropsEditor extends AbstractPropsEditor<Pattern> {

	@Override
	protected AbstractPropsEditorComponent<Pattern> initAndGetComponent() {
		return new PatternPropsEditorComponent(getEAttribute(), getContainer(), getStyle());
	}

}

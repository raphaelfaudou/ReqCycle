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
 *  Anass RADOUANI (AtoS) anass.radouani@atos.net - Use literal value for EEnumLiteral
 *
 *****************************************************************************/
package org.eclipse.reqcycle.ui.eenumpropseditor.internal;

import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditor;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditorComponent;
import org.eclipse.reqcycle.ui.eenumpropseditor.internal.components.EEnumPropsEditorComponent;

public class EEnumPropsEditor extends AbstractPropsEditor<String> {

	@Override
	protected AbstractPropsEditorComponent<String> initAndGetComponent() {
		return new EEnumPropsEditorComponent(getEAttribute(), getContainer(), getStyle());
	}

}

/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Papa Issa Diakhate (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.predicates.ui.util;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class SWTUtil {

	private SWTUtil() {
	}

	public static void recursiveSetEnabled(Control control, boolean enabled) {
		if(control instanceof Composite) {
			for(final Control c : ((Composite)control).getChildren()) {
				recursiveSetEnabled(c, enabled);
			}
		} else {
			control.setEnabled(enabled);
		}
	}
}

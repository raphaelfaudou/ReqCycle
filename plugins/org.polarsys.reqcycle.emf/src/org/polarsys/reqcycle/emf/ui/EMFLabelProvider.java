/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.emf.ui;

import org.eclipse.jface.viewers.LabelProvider;
import org.polarsys.reqcycle.uri.model.Reachable;
import org.eclipse.swt.graphics.Image;

public class EMFLabelProvider extends LabelProvider {

	@Override
	public Image getImage(Object element) {
		if (element instanceof Reachable) {
			Reachable r = (Reachable) element;
			return EMFEditExtender.getImage(r);
		}
		return null;
	}

	@Override
	public String getText(Object element) {
		if (element instanceof Reachable) {
			Reachable r = (Reachable) element;
			return EMFEditExtender.getLabel(r);
		}
		return "";
	}

}

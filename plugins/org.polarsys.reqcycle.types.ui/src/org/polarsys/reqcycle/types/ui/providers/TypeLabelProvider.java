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
package org.polarsys.reqcycle.types.ui.providers;

import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.LabelProvider;
import org.polarsys.reqcycle.types.Activator;
import org.polarsys.reqcycle.types.IType;
import org.eclipse.swt.graphics.Image;

public class TypeLabelProvider extends LabelProvider {

	@Override
	public Image getImage(Object element) {
		if (element instanceof IType) {
			IType type = (IType) element;
			return getImage(type);
		}
		return super.getImage(element);
	}

	@Override
	public String getText(Object element) {
		if (element instanceof IType) {
			IType type = (IType) element;
			return type.getLabel();
		}
		return super.getText(element);
	}

	public Image getImage(IType type) {
		ImageRegistry imageRegistry = Activator.getDefault().getImageRegistry();
		Image result = imageRegistry.get(type.getId());
		if (result == null) {
			imageRegistry.put(type.getId(), type.getIcon());
		}
		result = imageRegistry.get(type.getId());
		return result;
	}

}

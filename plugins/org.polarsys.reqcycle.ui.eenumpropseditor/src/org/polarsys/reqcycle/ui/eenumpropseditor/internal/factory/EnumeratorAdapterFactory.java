/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Papa Issa Diakhate (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.ui.eenumpropseditor.internal.factory;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.polarsys.reqcycle.ui.eenumpropseditor.internal.AdaptableEEnumLiteral;

public class EnumeratorAdapterFactory implements IAdapterFactory {

	@Override
	public Object getAdapter(Object adaptableObject, @SuppressWarnings("rawtypes") Class adapterType) {

		if((adapterType == Enumerator.class || adapterType == String.class) && (adaptableObject instanceof EEnumLiteral)) {
			return new AdaptableEEnumLiteral((EEnumLiteral)adaptableObject).getAdapter(adapterType);
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class[] getAdapterList() {
		return new Class[]{ Enumerator.class, String.class };
	}
}

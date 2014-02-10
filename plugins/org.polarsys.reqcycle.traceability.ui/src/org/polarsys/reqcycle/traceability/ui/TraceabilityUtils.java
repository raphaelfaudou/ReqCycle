/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.ui;

import org.eclipse.jface.viewers.ILabelProvider;
import org.polarsys.reqcycle.uri.IReachableManager;
import org.polarsys.reqcycle.uri.exceptions.IReachableHandlerException;
import org.polarsys.reqcycle.uri.model.IReachableHandler;
import org.polarsys.reqcycle.uri.model.Reachable;
import org.polarsys.reqcycle.uri.model.ReachableObject;
import org.polarsys.reqcycle.utils.inject.ZigguratInject;
import org.eclipse.swt.graphics.Image;

public class TraceabilityUtils {
	public static String getText(Reachable r) {
		ILabelProvider provider = getProvider(r);
		if (provider != null) {
			return provider.getText(r);
		}
		return r.toString();

	}

	public static ILabelProvider getProvider(Reachable r) {
		IReachableManager manager = ZigguratInject.make(IReachableManager.class);
		try {
			IReachableHandler handler = manager.getHandlerFromReachable(r);
			ReachableObject reachableObject = handler.getFromReachable(r);
			if (reachableObject != null) {
				ILabelProvider provider = (ILabelProvider) reachableObject
						.getAdapter(ILabelProvider.class);
				return provider;
			}
		} catch (IReachableHandlerException e) {
		}
		return null;
	}

	public static Image getImage(Reachable r) {
		ILabelProvider provider = getProvider(r);
		if (provider != null) {
			return provider.getImage(r);
		}
		return null;
	}
}

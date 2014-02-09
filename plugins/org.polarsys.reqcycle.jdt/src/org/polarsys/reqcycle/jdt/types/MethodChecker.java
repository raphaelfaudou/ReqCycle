/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Olivier Melois (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.jdt.types;

import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMethod;
import org.polarsys.reqcycle.jdt.handlers.JDTReachableHandler;
import org.polarsys.reqcycle.types.ITypeChecker;
import org.polarsys.reqcycle.uri.model.Reachable;
import org.polarsys.reqcycle.uri.model.ReachableObject;

public class MethodChecker implements ITypeChecker {

	@Override
	public boolean apply(Reachable arg0) {
		JDTReachableHandler jdtReachableHandler = new JDTReachableHandler();
		if (!jdtReachableHandler.apply(arg0)) {
			return false;
		}
		ReachableObject handler = jdtReachableHandler.getFromReachable(arg0);
		if (handler == null) {
			return false;
		}
		IJavaElement adapter = (IJavaElement) handler
				.getAdapter(IJavaElement.class);
		return adapter != null && adapter instanceof IMethod;
	}
}

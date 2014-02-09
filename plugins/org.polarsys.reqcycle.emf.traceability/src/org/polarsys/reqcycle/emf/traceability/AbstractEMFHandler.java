/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.emf.traceability;

import org.eclipse.emf.common.util.URI;
import org.polarsys.reqcycle.emf.utils.EMFUtils;
import org.polarsys.reqcycle.uri.model.IReachableHandler;
import org.polarsys.reqcycle.uri.model.Reachable;

public abstract class AbstractEMFHandler implements IReachableHandler {
	public boolean handles(Reachable t) {
		URI uri = EMFUtils.getEMFURI(t);
		return uri.isPlatform() || uri.isFile();
	}
}

/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Anass Radouani (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.repository.data.reachables;

import org.eclipse.emf.common.util.URI;
import org.polarsys.reqcycle.emf.handlers.EMFReachableObject;
import org.polarsys.reqcycle.emf.handlers.EMFURIHandler;
import org.polarsys.reqcycle.uri.model.Reachable;


public class ConfReachableHandler extends EMFURIHandler {

	@Override
	public boolean handlesURI(URI uri) {
		return super.handlesURI(uri) && (uri.path().endsWith("emfconf") || uri.path().endsWith("reqcycle"));
	}

	@Override
	protected EMFReachableObject doGetReachableObject(Reachable t) {
		return new ConfReachableObject(t);
	}

}

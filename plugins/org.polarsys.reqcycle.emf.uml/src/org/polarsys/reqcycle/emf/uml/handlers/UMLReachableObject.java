/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.emf.uml.handlers;

import org.eclipse.emf.common.util.URI;
import org.polarsys.reqcycle.emf.handlers.EMFReachableObject;
import org.polarsys.reqcycle.emf.visitors.EMFVisitable;
import org.polarsys.reqcycle.uri.model.Reachable;

public class UMLReachableObject extends EMFReachableObject {
	public UMLReachableObject(Reachable r) {
		super(r);
	}

	@Override
	protected EMFVisitable doGetVisitable(URI uri) {
		return new UMLVisitable(uri);
	}

}

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
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.polarsys.reqcycle.emf.utils.EMFUtils;
import org.polarsys.reqcycle.emf.visitors.EMFVisitable;

public class UMLVisitable extends EMFVisitable {

	public UMLVisitable(URI uri) {
		super(uri);
	}

	@Override
	protected ResourceSet getResourceSet() {
		return EMFUtils.getFAURSWithPathMaps();
	}

}

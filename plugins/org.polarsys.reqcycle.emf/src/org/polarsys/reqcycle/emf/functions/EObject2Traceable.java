/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.emf.functions;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.reqcycle.emf.utils.EMFUtils;
import org.polarsys.reqcycle.uri.model.Reachable;

import com.google.common.base.Function;

public class EObject2Traceable implements Function<EObject, Reachable> {

	@Override
	public Reachable apply(EObject arg0) {
		return EMFUtils.getReachable(EcoreUtil.getURI(arg0));
	}

}

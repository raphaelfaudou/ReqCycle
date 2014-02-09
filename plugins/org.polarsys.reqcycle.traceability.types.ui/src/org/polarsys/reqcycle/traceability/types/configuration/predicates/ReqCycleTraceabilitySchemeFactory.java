/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.types.configuration.predicates;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

public class ReqCycleTraceabilitySchemeFactory implements Factory {

	@Override
	public Resource createResource(URI uri) {
		try {
			EPackage epackage = ReqCycleDynamicPackage.getEPackage();
			Resource r = new EcoreResourceFactoryImpl().createResource(uri);
			r.getContents().add(epackage);
			return r;

		} catch (EPackageCreationException e) {
		}
		return null;
	}

}

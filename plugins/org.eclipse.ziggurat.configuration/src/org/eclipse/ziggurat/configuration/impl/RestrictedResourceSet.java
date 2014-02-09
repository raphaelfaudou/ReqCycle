/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Mathieu Velten (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.ziggurat.configuration.impl;

import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import com.google.common.collect.Sets;

public class RestrictedResourceSet extends ResourceSetImpl {

	protected Set<URI> authorizedUris = Sets.newHashSet();

	public void addAuthorizedUri(URI uri) {
		authorizedUris.add(uri);
	}

	@Override
	public Resource getResource(URI uri, boolean loadOnDemand) {
		if((uri.isPlatformResource() || uri.isFile()) && !authorizedUris.contains(uri)) {
			loadOnDemand = false;
		}
		Resource r = null;
		r =  super.getResource(uri, loadOnDemand);
		return r;
	}
	
	
	public RestrictedResourceSet() {
	}
}
/*******************************************************************************
 *  * Copyright (c) 2013-2014 AtoS.
 *  * All rights reserved. This program and the accompanying materials
 *  * are made available under the terms of the Eclipse Public License v1.0
 *  * which accompanies this distribution, and is available at
 *  * http://www.eclipse.org/legal/epl-v10.html
 *  *
 *  * Contributors:
 *  *    {INITIAL AUTHOR} - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.reqcycle.traceability.cache.emfbased.functions;

import java.net.URI;
import java.net.URISyntaxException;

import javax.inject.Inject;

import org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.Property;
import org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceableElement;
import org.eclipse.reqcycle.uri.IReachableCreator;
import org.eclipse.reqcycle.uri.model.Reachable;

import com.google.common.base.Function;

public class TraceableElement2Traceable implements
		Function<TraceableElement, Reachable> {
	@Inject
	IReachableCreator creator;

	@Override
	public Reachable apply(TraceableElement arg0) {
		if (arg0 == null) {
			return null;
		}
		Reachable t;
		try {
			t = creator.getReachable(new URI(arg0.getUri()));
			for (Property p : arg0.getProperties()) {
				t.put(p.getName(), p.getValue());
			}
			return t;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
}

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
package org.eclipse.reqcycle.xcos.traceability;

import java.net.URI;
import java.net.URISyntaxException;

import javax.inject.Inject;

import org.eclipse.reqcycle.uri.IIDContributor;
import org.eclipse.reqcycle.uri.IReachableCreator;
import org.eclipse.reqcycle.uri.IReachableManager;
import org.eclipse.reqcycle.uri.exceptions.IReachableHandlerException;
import org.eclipse.reqcycle.uri.model.Reachable;

public class XcosIDContributor implements IIDContributor {
	
	@Inject
	IReachableCreator creator;

	@Inject
	IReachableManager manager;

	public XcosIDContributor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Reachable getReachable(String logicalID) {
		
			URI uri;
			try {
				uri = new URI(logicalID);
				Reachable r = creator.getReachable(uri);
				return r;
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			return null;
			
		
	}

}

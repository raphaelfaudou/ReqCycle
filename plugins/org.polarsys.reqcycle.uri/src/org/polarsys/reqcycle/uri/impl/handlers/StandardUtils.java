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
package org.polarsys.reqcycle.uri.impl.handlers;

import java.net.URI;
import java.net.URISyntaxException;

import org.polarsys.reqcycle.uri.IReachableCreator;
import org.polarsys.reqcycle.uri.IReachableManager;
import org.polarsys.reqcycle.uri.model.Reachable;
import org.eclipse.ziggurat.inject.ZigguratInject;

public class StandardUtils {

	public static String SCHEME = "reqcycleStd"; //$NON-NLS-1$

	static IReachableCreator creator = ZigguratInject.make(IReachableCreator.class);

	static IReachableManager manager = ZigguratInject.make(IReachableManager.class);

	public static Reachable getReachable(Object o) {
		return getReachable(getURI(o));
	}

	private static URI getURI(Object o) {
		if(o instanceof URI) {
			return (URI)o;
		} else {
			URI arrayToURI = arrayToURI(o);
			if(arrayToURI != null) {
				return arrayToURI;
			}
		}
		try {
			URI uri = new URI(SCHEME, o.toString(), "");
			return uri;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Reachable getReachable(URI o) {
		return creator.getReachable(o, o);
	}

	public static URI arrayToURI(Object object) {
		if(object instanceof Object[] && ((Object[])object).length == 2) {
			Object object0 = ((Object[])object)[0];
			Object object1 = ((Object[])object)[1];
			Reachable parentReachable = (Reachable)object0;
			if(parentReachable instanceof Reachable && (object1 instanceof String || object1 instanceof Integer)) {
				Reachable reachable = creator.getReachable(parentReachable.getURI());
				reachable.setScheme(SCHEME);
				reachable.setFragment(object1.toString());
				return reachable.getURI();
			}
		}
		return null;
	}

}

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
package org.eclipse.reqcycle.uri.functions;

import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.ziggurat.inject.ZigguratInject;

import com.google.common.base.Function;

public class URIFunctions {
	public static Function<Reachable, Reachable> newTrimFragmentFunction() {
		Function<Reachable, Reachable> result = new TrimFragmentFunction();
		ZigguratInject.inject(result);
		return result;
	}

	public static Function<Object, Reachable> newObject2ReachableFunction() {
		Function<Object, Reachable> result = new Object2Reachable();
		ZigguratInject.inject(result);
		return result;
	}
}

/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.uri.functions;

import org.polarsys.reqcycle.uri.model.Reachable;
import org.polarsys.reqcycle.utils.inject.ZigguratInject;

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

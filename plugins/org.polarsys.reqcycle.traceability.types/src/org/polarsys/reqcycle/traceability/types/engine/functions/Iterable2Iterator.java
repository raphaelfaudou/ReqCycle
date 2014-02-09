/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.types.engine.functions;

import java.util.Iterator;

import com.google.common.base.Function;

public class Iterable2Iterator<T> implements Function<Iterable<T>, Iterator<T>> {

	@Override
	public Iterator<T> apply(Iterable<T> arg0) {
		return arg0.iterator();
	}

}

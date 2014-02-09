/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Olivier Melois (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.ziggurat.collect;


/**
 * A class to convert methods that implement the Collector<> class into a standard Iterable<>.
 */
public interface YieldAdapter<T> {

	YieldAdapterIterable<T> adapt(Collector<T> client);
}

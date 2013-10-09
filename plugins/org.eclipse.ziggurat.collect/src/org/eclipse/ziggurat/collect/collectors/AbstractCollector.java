/*******************************************************************************
 * Copyright (c) 2013 Atos.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Atos - initial API and implementation
 ******************************************************************************/
package org.eclipse.ziggurat.collect.collectors;

import java.util.Arrays;

import org.eclipse.ziggurat.collect.Collector;
import org.eclipse.ziggurat.collect.Picker;


/**
 * Basic abstract realization of a collector, that should delegate the collecting job to the pickers.
 */
public abstract class AbstractCollector<T> implements Collector<T> {

	/**
	 * The element from which the collector should start collecting elements.
	 */
	protected T start;

	/**
	 * Pickers, referenced in an attribute.
	 */
	protected Iterable<Picker<T>> pickers;

	public AbstractCollector(T startingElement, Picker<T>... pickers) {
		this.start = startingElement;
		this.pickers = Arrays.asList(pickers);
	}

	public Object getStartingElement() {
		return this.start;
	}

	public Iterable<Picker<T>> getPickers() {
		return pickers;
	}

}

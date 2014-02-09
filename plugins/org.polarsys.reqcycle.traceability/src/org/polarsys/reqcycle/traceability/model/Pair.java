/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.model;

public class Pair<F,S> {
	private final F first;
	private final S second;
	
	public Pair(F first, S second){
		this.first = first;
		this.second = second;
	}
	public F getFirst(){
		return first;
	}
	public S getSecond(){
		return second;
	}
}

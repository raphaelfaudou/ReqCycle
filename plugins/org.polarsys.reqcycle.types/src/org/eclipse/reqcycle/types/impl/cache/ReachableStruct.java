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
package org.eclipse.reqcycle.types.impl.cache;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.reqcycle.uri.model.Reachable;

public class ReachableStruct {
	Reachable resource;
	Map<Integer, TypeCouple> couples = new HashMap<Integer, TypeCouple>();

	public ReachableStruct(Reachable trimFragment) {
		resource = trimFragment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((couples == null) ? 0 : couples.hashCode());
		result = prime * result
				+ ((resource == null) ? 0 : resource.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReachableStruct other = (ReachableStruct) obj;
		if (couples == null) {
			if (other.couples != null)
				return false;
		} else if (!couples.equals(other.couples))
			return false;
		if (resource == null) {
			if (other.resource != null)
				return false;
		} else if (!resource.equals(other.resource))
			return false;
		return true;
	}

	public boolean contains(TypeCouple couple) {
		return couples.containsKey(couple.hashCode());
	}

	public TypeCouple get(TypeCouple couple) {
		TypeCouple newCouple = couples.get(couple.hashCode());
		return newCouple;
	}

	public void clear() {
		couples.clear();
	}

	public void add(TypeCouple couple) {
		couples.put(couple.hashCode(), couple);
	}
}
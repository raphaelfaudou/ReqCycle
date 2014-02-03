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
package org.polarsys.reqcycle.types.impl.cache;

import org.polarsys.reqcycle.types.ITypeChecker;
import org.polarsys.reqcycle.uri.model.Reachable;

public class TypeCouple {
	ITypeChecker checker;
	Reachable reachableTested;
	boolean result;

	public TypeCouple(Reachable reachable, ITypeChecker typeChecker) {
		reachableTested = reachable;
		checker = typeChecker;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((checker == null) ? 0 : checker.hashCode());
		result = prime * result
				+ ((reachableTested == null) ? 0 : reachableTested.hashCode());
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
		TypeCouple other = (TypeCouple) obj;
		if (checker == null) {
			if (other.checker != null)
				return false;
		} else if (!checker.equals(other.checker))
			return false;

		if (reachableTested == null) {
			if (other.reachableTested != null)
				return false;
		} else if (!reachableTested.equals(other.reachableTested))
			return false;
		return true;
	}

	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

}
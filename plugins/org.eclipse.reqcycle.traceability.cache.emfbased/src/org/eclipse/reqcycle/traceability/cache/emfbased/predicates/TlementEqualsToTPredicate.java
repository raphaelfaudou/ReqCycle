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
package org.eclipse.reqcycle.traceability.cache.emfbased.predicates;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;

public class TlementEqualsToTPredicate <S,T> implements Predicate<T> {

	private S s;

	public TlementEqualsToTPredicate  (S  s){
		this.s = s;
	}
	
	@Override
	public boolean apply(T arg0) {
		return Objects.equal(arg0, s);
	}

}

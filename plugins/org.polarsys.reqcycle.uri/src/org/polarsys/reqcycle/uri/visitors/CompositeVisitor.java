/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.uri.visitors;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import org.eclipse.core.runtime.IAdaptable;

public class CompositeVisitor implements IVisitor {

	protected Queue<IVisitor> visitors = null;
	Map<IVisitor, Boolean> status = new HashMap<IVisitor, Boolean>();

	public CompositeVisitor(IVisitor... visitors) {
		this(Arrays.asList(visitors));
	}

	public CompositeVisitor(Collection<IVisitor> visitors) {
		this.visitors = new ArrayDeque<IVisitor>(visitors);
	}

	@Override
	public boolean visit(Object o, IAdaptable adaptable) {
		boolean result = false;
		for (IVisitor v : visitors) {
			Boolean statusBool = status.get(v);
			if (statusBool == null || statusBool) {
				boolean tmp = v.visit(o, adaptable);
				status.put(v, tmp);
				result |= tmp;
			}
		}
		return result;
	}

	@Override
	public void start(IAdaptable adaptable) {
		status.clear();
		for (IVisitor v : visitors) {
			status.put(v, true);
			v.start(adaptable);
		}
	}

	@Override
	public void end(IAdaptable adaptable) {
		for (IVisitor v : visitors) {
			v.end(adaptable);
		}
	}

}

/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.utils;

import org.polarsys.reqcycle.traceability.model.Link;
import org.polarsys.reqcycle.traceability.model.Pair;
import org.polarsys.reqcycle.traceability.model.StopCondition;
import org.polarsys.reqcycle.traceability.model.StopCondition.ScopedStopCondition;
import org.polarsys.reqcycle.traceability.model.scopes.IScope;
import org.polarsys.reqcycle.traceability.model.scopes.ResourceScope;
import org.polarsys.reqcycle.uri.model.Reachable;

public class Conditions {
	public static StopCondition reachableEquals(Reachable r) {
		return new ReachableEquals(r);
	}

	public static StopCondition emptyCondition() {
		return new StopCondition() {

			@Override
			public boolean apply(Pair<Link, Reachable> reachable) {
				return true;
			}
		};
	}

	private static class ReachableEquals implements ScopedStopCondition {
		private Reachable toCheck;

		public ReachableEquals(Reachable toCheck) {
			this.toCheck = toCheck;
		}

		@Override
		public boolean apply(Pair<Link, Reachable> pair) {
			return toCheck.equals(pair.getSecond());
		}

		@Override
		public IScope getScope() {
			return new ResourceScope(toCheck);
		}

	}
}

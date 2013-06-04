package org.eclipse.reqcycle.traceability.utils;

import org.eclipse.reqcycle.traceability.model.StopCondition;
import org.eclipse.reqcycle.traceability.model.StopCondition.ScopedStopCondition;
import org.eclipse.reqcycle.traceability.model.scopes.IScope;
import org.eclipse.reqcycle.traceability.model.scopes.ResourceScope;
import org.eclipse.reqcycle.uri.model.Reachable;

public class Conditions {
	public static StopCondition reachableEquals(Reachable r) {
		return new ReachableEquals(r);
	}

	public static StopCondition emptyCondition() {
		return new StopCondition() {

			@Override
			public boolean apply(Reachable reachable) {
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
		public boolean apply(Reachable reachable) {
			return toCheck.equals(reachable);
		}

		@Override
		public IScope getScope() {
			return new ResourceScope(toCheck);
		}

	}
}

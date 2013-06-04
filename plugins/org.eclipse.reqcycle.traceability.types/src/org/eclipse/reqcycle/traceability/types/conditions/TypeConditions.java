package org.eclipse.reqcycle.traceability.types.conditions;

import org.eclipse.reqcycle.traceability.model.StopCondition;
import org.eclipse.reqcycle.types.IType;
import org.eclipse.reqcycle.uri.model.Reachable;

public class TypeConditions {
	public static StopCondition is(IType type) {
		return new IsCondition(type);
	}

	private static class IsCondition implements StopCondition {
		private IType type;

		public IsCondition(IType type) {
			this.type = type;
		}

		@Override
		public boolean apply(Reachable reachable) {
			return type.is(reachable);
		}

	}
}

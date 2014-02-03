package org.polarsys.reqcycle.traceability.engine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.polarsys.reqcycle.traceability.engine.ITraceabilityEngine.DIRECTION;
import org.polarsys.reqcycle.traceability.model.Filter;
import org.polarsys.reqcycle.traceability.model.StopCondition;
import org.polarsys.reqcycle.traceability.model.StopCondition.ScopedStopCondition;
import org.polarsys.reqcycle.traceability.model.scopes.CompositeScope;
import org.polarsys.reqcycle.traceability.model.scopes.IScope;
import org.polarsys.reqcycle.traceability.model.scopes.ResourceScope;
import org.polarsys.reqcycle.traceability.utils.Conditions;
import org.polarsys.reqcycle.uri.model.Reachable;

import com.google.common.collect.Iterables;

/**
 * a {@link Request} contains the data to determine traceability the scope can
 * be set. By default it contains the traceable corresponding to the source and
 * the target
 * 
 * 
 * @author tfaure
 * 
 */
public class Request {
	protected DIRECTION direction = DIRECTION.DOWNWARD;
	protected Map<String, Object> extraProperties = new HashMap<String, Object>();
	protected CompositeScope scope = new CompositeScope();
	protected Filter filter = null;
	private Set<Couple> elements = new HashSet<Couple>();
	private DEPTH depth = DEPTH.INFINITE;

	public enum DEPTH {
		ONE, INFINITE
	};

	public Request() {
	}

	public Request setDepth(DEPTH depth) {
		this.depth = depth;
		return this;
	}

	public DEPTH getDepth() {
		return depth;
	}

	public Request setFilter(Filter f) {
		this.filter = f;
		return this;
	}

	public Request setScope(IScope s) {
		scope.add(s);
		return this;
	}

	public Request addSourceAndTarget(Reachable s, Reachable t) {
		if (t != null) {
			addSourceAndCondition(s, Conditions.reachableEquals(t));
		} else {
			addSourceAndCondition(s, null);
		}
		return this;
	}

	public Request addSourceAndCondition(Reachable s, StopCondition t) {
		Couple sourceTarget = new Couple(s, t);
		elements.add(sourceTarget);
		addScope(sourceTarget);
		return this;
	}

	private void addScope(Couple sourceTarget) {
		scope.add(sourceTarget.getScope());
	}

	public Request addSource(Reachable r) {
		addSourceAndTarget(r, null);
		return this;
	}

	public Request setDirection(DIRECTION d) {
		direction = d;
		return this;
	}

	public DIRECTION getDirection() {
		return direction;
	}

	public Filter getFilter() {
		return filter;
	}

	public Iterable<Couple> getCouples() {
		return Iterables.unmodifiableIterable(elements);
	}

	public IScope getScope() {
		return scope;
	}

	public Request addProperty(String key, Object value) {
		extraProperties.put(key, value);
		return this;
	}

	public Object getProperty(String key) {
		return extraProperties.get(key);
	}

	public static class Couple {
		private Reachable s;
		private StopCondition t;

		public Couple(Reachable s, StopCondition t) {
			this.s = s;
			this.t = t;
		}

		public IScope getScope() {
			CompositeScope scope = new CompositeScope();
			if (s != null) {
				scope.add(new ResourceScope(s));
			}
			if (t instanceof ScopedStopCondition) {
				scope.add(((ScopedStopCondition) t).getScope());
			}
			return scope;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((s == null) ? 0 : s.hashCode());
			result = prime * result + ((t == null) ? 0 : t.hashCode());
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
			Couple other = (Couple) obj;
			if (s == null) {
				if (other.s != null)
					return false;
			} else if (!s.equals(other.s))
				return false;
			if (t == null) {
				if (other.t != null)
					return false;
			} else if (!t.equals(other.t))
				return false;
			return true;
		}

		public Reachable getSource() {
			return s;
		}

		public StopCondition getStopCondition() {
			return t;
		}
	}

}

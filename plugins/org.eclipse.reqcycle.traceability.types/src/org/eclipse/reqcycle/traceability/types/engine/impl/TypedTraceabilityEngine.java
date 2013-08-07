package org.eclipse.reqcycle.traceability.types.engine.impl;

import java.util.Iterator;

import javax.inject.Inject;

import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine;
import org.eclipse.reqcycle.traceability.engine.Request;
import org.eclipse.reqcycle.traceability.exceptions.EngineException;
import org.eclipse.reqcycle.traceability.model.Filter;
import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.traceability.model.Pair;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Configuration;
import org.eclipse.reqcycle.traceability.types.engine.ITypedTraceabilityEngine;
import org.eclipse.reqcycle.traceability.types.engine.functions.Configuration2Filter;
import org.eclipse.reqcycle.uri.model.Reachable;

public class TypedTraceabilityEngine implements ITypedTraceabilityEngine {

	@Inject
	ITraceabilityEngine engine;

	@Override
	public Iterator<Pair<Link, Reachable>> getTraceability(
			Configuration typeConfig, Request... requests)
			throws EngineException {
		if (typeConfig != null) {
			for (Request r : requests) {
				handleFilter(typeConfig, r);
			}
		}
		return engine.getTraceability(requests);
	}

	private void handleFilter(Configuration typeConfig, Request r) {
		Filter confFilter = new Configuration2Filter(r.getDirection())
				.apply(typeConfig);
		r.setFilter(new AndFilter(r.getFilter(), confFilter));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine#getTraceability
	 * (org.eclipse.reqcycle.traceability.engine.Request[])
	 */
	@Override
	public Iterator<Pair<Link, Reachable>> getTraceability(Request... requests)
			throws EngineException {
		return getTraceability(null, requests);

	}

	private class AndFilter implements Filter {
		private Filter[] filters;

		public AndFilter(Filter... f) {
			this.filters = f;
		}

		@Override
		public boolean apply(Pair<Link, Reachable> pair) {
			boolean result = true;
			for (Filter f : filters) {
				if (f != null) {
					result &= f.apply(pair);
				}
			}
			return result;
		}
	}

}

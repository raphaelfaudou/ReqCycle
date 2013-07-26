package org.eclipse.reqcycle.traceability.types.engine.impl;

import javax.inject.Inject;

import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine.DIRECTION;
import org.eclipse.reqcycle.traceability.model.Filter;
import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.traceability.model.Pair;
import org.eclipse.reqcycle.traceability.types.ITraceTypesManager;
import org.eclipse.reqcycle.traceability.types.RelationBasedType;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Configuration;
import org.eclipse.reqcycle.types.ITypesManager;
import org.eclipse.reqcycle.uri.model.Reachable;

public class ConfigurationBasedFilter implements Filter {

	private Configuration config;
	@Inject
	ITypesManager manager;
	@Inject
	ITraceTypesManager tManager;

	private DIRECTION direction;

	public ConfigurationBasedFilter(DIRECTION direction, Configuration config) {
		this.config = config;
		this.direction = direction;
	}

	@Override
	public boolean apply(Pair<Link, Reachable> pair) {
		// Iterable<Relation> relations =
		// RelationUtils.getMatchingRelations(pair,
		// direction, config);
		// return relations.iterator().hasNext();
		return pair.getFirst().getKind() instanceof RelationBasedType;
	}

}

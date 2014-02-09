/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.types.engine.impl;

import javax.inject.Inject;

import org.polarsys.reqcycle.traceability.engine.ITraceabilityEngine.DIRECTION;
import org.polarsys.reqcycle.traceability.model.Filter;
import org.polarsys.reqcycle.traceability.model.Link;
import org.polarsys.reqcycle.traceability.model.Pair;
import org.polarsys.reqcycle.traceability.types.ITraceTypesManager;
import org.polarsys.reqcycle.traceability.types.RelationBasedType;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Configuration;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation;
import org.polarsys.reqcycle.types.ITypesManager;
import org.polarsys.reqcycle.uri.model.Reachable;

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
		boolean result = pair.getFirst().getKind() instanceof RelationBasedType;
		if (result) {
			result = false;
			RelationBasedType relb = (RelationBasedType) pair.getFirst()
					.getKind();
			for (Relation r : config.getRelations()) {
				// fix me improve the id check
				result |= (r != null && r.getKind().equals(relb.getLabel()));
			}
		}
		return result;
	}

}

package org.eclipse.reqcycle.traceability.types.engine.impl;

import java.util.List;

import javax.inject.Inject;

import org.eclipse.emf.common.util.EList;
import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine.DIRECTION;
import org.eclipse.reqcycle.traceability.model.Filter;
import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.traceability.model.Pair;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Configuration;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Relation;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Type;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.util.ConfigUtils;
import org.eclipse.reqcycle.types.IType;
import org.eclipse.reqcycle.types.ITypesManager;
import org.eclipse.reqcycle.uri.model.Reachable;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class ConfigurationBasedFilter implements Filter {

	private Configuration config;
	@Inject
	ITypesManager manager;
	private DIRECTION direction;

	public ConfigurationBasedFilter(DIRECTION direction, Configuration config) {
		this.config = config;
		this.direction = direction;
	}

	@Override
	public boolean apply(Pair<Link, Reachable> pair) {
		Reachable source = pair.getFirst().getSources().iterator().next();
		Iterable<IType> allTypesFromSource = manager
				.getAllApplicableTypes(source);
		for (IType t : allTypesFromSource) {
			Type type = ConfigUtils.getType(config, t.getId());
			if (type != null) {
				List<Relation> relations = getRelations(type);
				for (Relation r : relations) {
					if (r.getKind()
							.equals(pair.getFirst().getKind().getSuperType()
									.getLabel())) {
						Type typeTarget = getTypeTarget(r);
						IType targetTypeFromManager = typeTarget.getIType();
						if (targetTypeFromManager != null
								&& targetTypeFromManager.is(pair.getSecond())) {
							return true;
						}
					}
				}
			}

		}
		return false;
	}

	private Type getTypeTarget(Relation r) {
		if (direction == DIRECTION.DOWNWARD) {
			return r.getDownstreamType();
		} else {
			return r.getUpstreamType();
		}

	}

	private List<Relation> getRelations(Type type) {
		if (direction == DIRECTION.DOWNWARD) {
			return filter(type.getOutgoings());
		} else {
			return filter(type.getIncomings());
		}
	}

	/**
	 * Return the relations contained in the current configuration
	 * 
	 * @param incomings
	 * @return
	 */
	private List<Relation> filter(EList<Relation> incomings) {
		return Lists.newArrayList(Iterables.filter(incomings,
				new Predicate<Relation>() {
					public boolean apply(Relation r) {
						return r.eContainer() == config;
					}
				}));
	}
}

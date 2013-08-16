package org.eclipse.reqcycle.traceability.types;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine.DIRECTION;
import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.traceability.model.Pair;
import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Configuration;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Relation;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Type;
import org.eclipse.reqcycle.traceability.types.impl.TraceTypesManager;
import org.eclipse.reqcycle.types.ITypesManager;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.ziggurat.inject.ZigguratInject;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class RelationUtils {

	static ITypesManager manager = ZigguratInject.make(ITypesManager.class);
	static TraceTypesManager tManager = ZigguratInject
			.make(TraceTypesManager.class);

	public static Iterable<Relation> getMatchingRelations(
			Pair<Link, Reachable> pair, DIRECTION direction,
			Configuration config) {
		Reachable source = pair.getFirst().getSources().iterator().next();
		Reachable target = pair.getFirst().getTargets().iterator().next();
		return getAgregatingRelations(pair.getFirst().getKind(), config,
				source, target, direction);
		// Iterable<IType> allTypesFromSource = manager
		// .getAllApplicableTypes(source);
		// Set<Relation> result = new HashSet<Relation>();
		// for (IType t : allTypesFromSource) {
		// Type type = ConfigUtils.getType(config, t.getId());
		// if (type != null) {
		// List<Relation> relations = getRelations(type, direction, config);
		// for (Relation r : relations) {
		// if (r.getKind()
		// .equals(pair.getFirst().getKind().getSuperType()
		// .getLabel())) {
		// Type typeTarget = getTypeTarget(r, direction);
		// IType targetTypeFromManager = typeTarget.getIType();
		// if (targetTypeFromManager != null
		// && targetTypeFromManager.is(pair.getSecond())) {
		// result.add(r);
		// }
		// }
		// }
		// }
		// }
		// return result;
	}

	private static Type getTypeTarget(Relation r, DIRECTION direction) {
		if (direction == DIRECTION.DOWNWARD) {
			return r.getDownstreamType();
		} else {
			return r.getUpstreamType();
		}
	}

	private static List<Relation> getRelations(Type type, DIRECTION direction,
			Configuration config) {
		if (direction == DIRECTION.DOWNWARD) {
			return filter(type.getOutgoings(), config);
		} else {
			return filter(type.getIncomings(), config);
		}
	}

	/**
	 * Return the relations contained in the current configuration
	 * 
	 * @param incomings
	 * @return
	 */
	private static List<Relation> filter(EList<Relation> incomings,
			final Configuration config) {
		return Lists.newArrayList(Iterables.filter(incomings,
				new Predicate<Relation>() {
					public boolean apply(Relation r) {
						return r.eContainer() == config;
					}
				}));
	}

	public static Iterable<Relation> getAgregatingRelations(TType type,
			Configuration config, Reachable source, Reachable target,
			DIRECTION direction) {
		Set<Relation> relations = new HashSet<Relation>();
		for (Relation r : config.getRelations()) {
			for (String s : r.getAgregatedTypes()) {
				if (type.equals(tManager.getTType(s))) {
					Type sourceType = getSourceType(r, direction);
					if (sourceType != null && sourceType.getIType().is(source)) {
						if (getTargetType(r, direction).getIType().is(target)) {
							relations.add(r);
						}
					}
				}
			}
		}
		return relations;
	}

	private static Type getTargetType(Relation r, DIRECTION direction) {
		if (direction == DIRECTION.UPWARD) {
			return r.getUpstreamType();
		} else {
			return r.getDownstreamType();
		}
	}

	private static Type getSourceType(Relation r, DIRECTION direction) {
		if (direction == DIRECTION.DOWNWARD) {
			return r.getUpstreamType();
		} else {
			return r.getDownstreamType();
		}
	}

	public static Relation getRelation(String kind, Configuration conf) {
		for (Relation r : conf.getRelations()) {
			if (r.getKind().equals(kind)) {
				return r;
			}
		}
		return null;
	}
}

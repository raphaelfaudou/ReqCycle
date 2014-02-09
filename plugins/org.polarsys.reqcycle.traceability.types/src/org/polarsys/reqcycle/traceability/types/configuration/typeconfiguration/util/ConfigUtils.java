/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.util;

import java.util.LinkedList;
import java.util.List;

import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Configuration;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.CustomType;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Type;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

public class ConfigUtils {
	public static Type getType(Configuration conf, final String id) {
		TypeConfigContainer container = conf.getParent();
		return getType(container, id);
	}

	public static Iterable<CustomType> getCustomTypeSubTypeOf(
			Configuration conf, final String id) {
		TypeConfigContainer container = conf.getParent();
		return getCustomTypeSubTypeOf(container, id);
	}

	public static Iterable<CustomType> getCustomTypeSubTypeOf(
			TypeConfigContainer container, final String id) {
		return Iterables.filter(
				Iterables.filter(container.getTypes(), new Predicate<Type>() {
					public boolean apply(Type t) {
						if (t instanceof CustomType) {
							CustomType custom = (CustomType) t;
							return id.equals(custom.getSuperType().getTypeId());
						}
						return false;
					}
				}), CustomType.class);
	}

	public static Type getType(TypeConfigContainer container, final String id) {
		return Iterables.find(container.getTypes(), new Predicate<Type>() {
			public boolean apply(Type t) {
				return id.equals(t.getTypeId());
			}
		}, null);
	}

	public static Iterable<CustomType> getCustomTypesUsedInRelations(
			Configuration config, final String typeId) {
		return Sets.newHashSet(Iterables.concat(Iterables.transform(
				config.getRelations(),
				new Function<Relation, Iterable<CustomType>>() {
					public Iterable<CustomType> apply(Relation r) {
						List<CustomType> result = new LinkedList<CustomType>();
						if (r.getDownstreamType() instanceof CustomType) {
							CustomType downstreamType = (CustomType) r
									.getDownstreamType();
							if (downstreamType.getSuperType().getTypeId()
									.equals(typeId)) {
								result.add(downstreamType);
							}
						}
						if (r.getUpstreamType() instanceof CustomType) {
							CustomType upstreamType = (CustomType) r
									.getUpstreamType();
							if (upstreamType.getSuperType().getTypeId()
									.equals(typeId)) {
								result.add(upstreamType);
							}
						}
						return result;
					}
				})));
	}
}

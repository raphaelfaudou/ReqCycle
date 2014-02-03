/*******************************************************************************
 *  * Copyright (c) 2013-2014 AtoS.
 *  * All rights reserved. This program and the accompanying materials
 *  * are made available under the terms of the Eclipse Public License v1.0
 *  * which accompanies this distribution, and is available at
 *  * http://www.eclipse.org/legal/epl-v10.html
 *  *
 *  * Contributors:
 *  *    {INITIAL AUTHOR} - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.reqcycle.traceability.types.engine.functions;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine.DIRECTION;
import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.traceability.model.Pair;
import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.traceability.types.RelationBasedType;
import org.eclipse.reqcycle.traceability.types.RelationUtils;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Configuration;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Relation;
import org.eclipse.reqcycle.uri.model.Reachable;

import com.google.common.base.Function;

public class Link2RegisteredLink implements
		Function<Pair<Link, Reachable>, Iterable<Pair<Link, Reachable>>> {

	private Configuration typeConfig;
	private DIRECTION direction;

	public Link2RegisteredLink(Configuration typeConfig, DIRECTION direction) {
		this.typeConfig = typeConfig;
		this.direction = direction;
	}

	@Override
	public Iterable<Pair<Link, Reachable>> apply(
			final Pair<Link, Reachable> arg0) {
		Link link = arg0.getFirst();
		TType type = link.getKind();
		Reachable source = arg0.getFirst().getSources().iterator().next();
		Reachable target = arg0.getFirst().getTargets().iterator().next();
		Iterable<Relation> relations = RelationUtils.getAgregatingRelations(
				type, typeConfig, source, target, direction);
		List<Pair<Link, Reachable>> result = new LinkedList<Pair<Link, Reachable>>();
		for (Relation r : relations) {
			result.add(new Pair<Link, Reachable>(new Link(link.getId(),
					new RelationBasedType(r, type), source, target), target));
		}
		return result;
	}
}

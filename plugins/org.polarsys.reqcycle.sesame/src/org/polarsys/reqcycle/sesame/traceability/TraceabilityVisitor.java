/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.sesame.traceability;

import java.util.Collections;

import javax.annotation.PostConstruct;

import org.eclipse.core.runtime.IAdaptable;
import org.polarsys.reqcycle.sesame.graph.SailBusinessOperations;
import org.polarsys.reqcycle.traceability.builder.ITraceabilityBuilder.IBuilderCallBack;
import org.polarsys.reqcycle.uri.visitors.IVisitor;
import org.eclipse.ziggurat.inject.ZigguratInject;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

public class TraceabilityVisitor implements IVisitor {

	SailBusinessOperations op = new SailBusinessOperations();

	@PostConstruct
	public void init() {
		op = new SailBusinessOperations();
		ZigguratInject.inject(op);
	}

	@Override
	public void start(IAdaptable adaptable) {

	}

	@Override
	public boolean visit(Object o, IAdaptable adaptable) {
		if (o instanceof Edge) {
			Edge edge = (Edge) o;
			if (SailBusinessOperations.VERTEX_OUTGOING.equals(edge.getLabel())) {
				Vertex traceabilityVertex = edge.getVertex(Direction.IN);
				Object container = op
						.getContainerOfTraceability(traceabilityVertex);
				Object source = edge.getVertex(Direction.OUT);
				Object target = traceabilityVertex
						.getEdges(Direction.OUT,
								SailBusinessOperations.TRACE_TARGET).iterator()
						.next().getVertex(Direction.IN);
				getCallBack(adaptable).newUpwardRelation(traceabilityVertex,
						container, source, Collections.singletonList(target),
						op.getKind(traceabilityVertex));
			}
			return true;
		}
		return false;
	}

	IBuilderCallBack getCallBack(IAdaptable adaptable) {
		return (IBuilderCallBack) adaptable.getAdapter(IBuilderCallBack.class);
	}

	@Override
	public void end(IAdaptable adaptable) {

	}

}

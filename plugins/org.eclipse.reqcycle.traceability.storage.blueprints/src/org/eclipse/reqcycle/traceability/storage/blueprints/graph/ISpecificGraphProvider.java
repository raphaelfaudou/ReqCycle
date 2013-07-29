package org.eclipse.reqcycle.traceability.storage.blueprints.graph;

import java.util.Map;

import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.uri.model.Reachable;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Vertex;

public interface ISpecificGraphProvider extends IGraphProvider {
	IBusinessOperations getBusinessOperation();

	interface IBusinessOperations {
		Vertex getVertex(Graph graph, Reachable reachable);

		Vertex getVertex(Graph graph, String reachableUri);

		void addRelation(Graph graph, Vertex source, Vertex target,
				String relation);

		void addRelation(Graph graph, Reachable source, Reachable target,
				String relation);

		Vertex addTraceabilityRelation(Graph graph, Reachable source,
				Reachable target, TType relation);

		void addChildrenRelation(Graph graph, Vertex container, Vertex children);

		Iterable<Vertex> getTraceability(Vertex v,
				final Direction graphDirection);

		Vertex getTraceabilityTarget(Vertex trac, Direction direction);

		TType getKind(Vertex trac);

		Iterable<Vertex> getTraceabilityIn(Graph graph, Reachable reachable);

		void delete(Vertex v);

		TType getTType(Vertex traceabilityvertex);

		Map<String, String> getProperties(Vertex v);

		Iterable<Vertex> getAllTraceabilityVertices(Graph graph);

	}
}
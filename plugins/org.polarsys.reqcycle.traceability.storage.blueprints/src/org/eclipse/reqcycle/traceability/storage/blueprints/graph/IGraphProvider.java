package org.eclipse.reqcycle.traceability.storage.blueprints.graph;

import com.tinkerpop.blueprints.Graph;

public interface IGraphProvider {

	Graph getGraph(String path);

}

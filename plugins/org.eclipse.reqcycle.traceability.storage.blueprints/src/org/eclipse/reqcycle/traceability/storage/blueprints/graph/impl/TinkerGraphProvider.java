package org.eclipse.reqcycle.traceability.storage.blueprints.graph.impl;

import java.io.File;
import java.io.IOException;

import org.eclipse.reqcycle.traceability.storage.blueprints.graph.IGraphProvider;

import com.google.common.io.Files;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;

public class TinkerGraphProvider implements IGraphProvider {

	@Override
	public Graph getGraph(String path) {
		String directory = path + "/tinker/";
		TinkerGraph graph = null;
		try {
			graph = new TinkerGraph(directory);
		} catch (RuntimeException e) {
			// eclipse has crashed recreate the graph
			// TODO verify eclipse knows cache is inconsistent
//			try {
////				Files.(new File(directory));
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
			graph = new TinkerGraph(directory);
		}
		return graph;
	}
}

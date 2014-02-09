/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.storage.blueprints.graph.impl;

import org.polarsys.reqcycle.traceability.storage.blueprints.graph.IGraphProvider;

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

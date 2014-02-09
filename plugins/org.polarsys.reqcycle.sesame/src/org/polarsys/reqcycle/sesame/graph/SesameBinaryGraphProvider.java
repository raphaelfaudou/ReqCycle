/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.sesame.graph;

import java.io.File;

import org.polarsys.reqcycle.traceability.storage.blueprints.graph.IBusinessOperationProvider;
import org.polarsys.reqcycle.traceability.storage.blueprints.graph.IGraphProvider;
import org.eclipse.ziggurat.inject.ZigguratInject;
import org.openrdf.sail.nativerdf.NativeStore;

import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.impls.sail.SailGraph;

public class SesameBinaryGraphProvider implements IGraphProvider, IBusinessOperationProvider {

	@Override
	public Graph getGraph(String path) {
		NativeStore store = new NativeStore(new File(path + "/binary"));
		return new SailGraph(store);
	}

	@Override
	public IBusinessOperations getBusinessOperation() {
		SailBusinessOperations op = new SailBusinessOperations();
		ZigguratInject.inject(op);
		return op;
	}
}

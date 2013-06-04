package org.eclipse.reqcycle.sesame.graph;

import java.io.File;

import org.agesys.inject.AgesysInject;
import org.eclipse.reqcycle.traceability.storage.blueprints.graph.ISpecificGraphProvider;
import org.openrdf.sail.nativerdf.NativeStore;

import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.impls.sail.SailGraph;

public class SesameBinaryGraphProvider implements ISpecificGraphProvider {

	@Override
	public Graph getGraph(String path) {
		NativeStore store = new NativeStore(new File(path + "/binary"));
		return new SailGraph(store);
	}

	@Override
	public IBusinessOperations getBusinessOperation() {
		SailBusinessOperations op = new SailBusinessOperations();
		AgesysInject.inject(op);
		return op;
	}

}

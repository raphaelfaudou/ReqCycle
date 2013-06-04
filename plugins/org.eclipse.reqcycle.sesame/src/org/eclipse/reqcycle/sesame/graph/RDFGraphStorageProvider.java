package org.eclipse.reqcycle.sesame.graph;

import org.agesys.inject.AgesysInject;
import org.eclipse.reqcycle.traceability.storage.blueprints.graph.IGraphProvider;
import org.eclipse.reqcycle.traceability.storage.blueprints.storage.GraphStorageProvider;

public class RDFGraphStorageProvider extends GraphStorageProvider {

	@Override
	protected IGraphProvider getProvider() {
		SailGraphProvider provider = new SailGraphProvider();
		AgesysInject.inject(provider);
		return provider;
	}

}

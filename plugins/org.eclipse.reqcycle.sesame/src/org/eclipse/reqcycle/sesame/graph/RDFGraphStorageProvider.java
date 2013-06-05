package org.eclipse.reqcycle.sesame.graph;

import org.eclipse.reqcycle.traceability.storage.blueprints.graph.IGraphProvider;
import org.eclipse.reqcycle.traceability.storage.blueprints.storage.GraphStorageProvider;
import org.eclipse.ziggurat.inject.ZigguratInject;

public class RDFGraphStorageProvider extends GraphStorageProvider {

	@Override
	protected IGraphProvider getProvider() {
		SailGraphProvider provider = new SailGraphProvider();
		ZigguratInject.inject(provider);
		return provider;
	}

}

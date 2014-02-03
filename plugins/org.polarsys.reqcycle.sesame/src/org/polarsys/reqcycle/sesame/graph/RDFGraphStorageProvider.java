package org.polarsys.reqcycle.sesame.graph;

import org.polarsys.reqcycle.traceability.storage.blueprints.graph.IGraphProvider;
import org.polarsys.reqcycle.traceability.storage.blueprints.storage.GraphStorageProvider;
import org.eclipse.ziggurat.inject.ZigguratInject;

public class RDFGraphStorageProvider extends GraphStorageProvider {

	@Override
	protected IGraphProvider getProvider() {
		SailGraphProvider provider = new SailGraphProvider();
		ZigguratInject.inject(provider);
		return provider;
	}

}

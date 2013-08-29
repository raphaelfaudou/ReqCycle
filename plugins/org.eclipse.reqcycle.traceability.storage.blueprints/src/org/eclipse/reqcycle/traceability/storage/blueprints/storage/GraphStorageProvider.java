package org.eclipse.reqcycle.traceability.storage.blueprints.storage;

import javax.inject.Inject;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.reqcycle.traceability.storage.IStorageProvider;
import org.eclipse.reqcycle.traceability.storage.ITraceabilityStorage;
import org.eclipse.reqcycle.traceability.storage.blueprints.graph.IGraphProvider;
import org.eclipse.reqcycle.traceability.storage.blueprints.graph.ISpecificGraphProvider;
import org.eclipse.ziggurat.inject.ZigguratInject;

public class GraphStorageProvider implements IStorageProvider {

	@Inject
	IGraphProvider graphProvider;
	
	@Inject
	IEventBroker broker;

	@Override
	public ITraceabilityStorage getStorage(String path) {
		GraphStorage graphStorage = null;
		if (getProvider() instanceof ISpecificGraphProvider) {
			ISpecificGraphProvider specProvider = (ISpecificGraphProvider) getProvider();
			graphStorage = new GraphStorage(specProvider.getGraph(path), path,
					specProvider.getBusinessOperation());
		} else {
			graphStorage = new GraphStorage(getProvider().getGraph(path), path);
		}
		ZigguratInject.inject(graphStorage);
		return graphStorage;
	}

	protected IGraphProvider getProvider() {
		return graphProvider;
	}

	@Override
	public void notifyChanged(String event,
			Object data) {
		//Asynchronousky sending a message.
		broker.post(event, data);
	}
}

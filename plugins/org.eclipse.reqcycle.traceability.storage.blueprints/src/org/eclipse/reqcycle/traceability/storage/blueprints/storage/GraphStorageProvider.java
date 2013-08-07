package org.eclipse.reqcycle.traceability.storage.blueprints.storage;

import java.util.Collection;

import javax.inject.Inject;

import org.eclipse.reqcycle.traceability.storage.IStorageProvider;
import org.eclipse.reqcycle.traceability.storage.ITraceabilityStorage;
import org.eclipse.reqcycle.traceability.storage.ITraceabilityStorageListener;
import org.eclipse.reqcycle.traceability.storage.ITraceabilityStorageListener.Event;
import org.eclipse.reqcycle.traceability.storage.blueprints.graph.IGraphProvider;
import org.eclipse.reqcycle.traceability.storage.blueprints.graph.ISpecificGraphProvider;
import org.eclipse.ziggurat.inject.ZigguratInject;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class GraphStorageProvider implements IStorageProvider {

	@Inject
	IGraphProvider graphProvider;

	private Multimap<String, ITraceabilityStorageListener> listeners = HashMultimap
			.create();

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
	public void registerListener(String path,
			ITraceabilityStorageListener listener) {
		listeners.put(path, listener);
	}

	@Override
	public void unregisterListener(String path,
			ITraceabilityStorageListener listener) {
		listeners.remove(path, listener);
	}

	@Override
	public void notifyChanged(ITraceabilityStorage storage, Event event,
			Object data) {
		String path = storage.getPath();
		Collection<ITraceabilityStorageListener> listenersFromMap = listeners
				.get(path);
		for (ITraceabilityStorageListener l : listenersFromMap) {
			l.notify(storage, event, data);
		}
	}
}

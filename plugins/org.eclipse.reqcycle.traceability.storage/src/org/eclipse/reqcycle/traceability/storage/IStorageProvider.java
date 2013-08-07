package org.eclipse.reqcycle.traceability.storage;

import org.eclipse.reqcycle.traceability.storage.ITraceabilityStorageListener.Event;

public interface IStorageProvider {
	/**
	 * Returns a storage able to store traceability information
	 * 
	 * @param path
	 * @return
	 */
	ITraceabilityStorage getStorage(String path);

	void registerListener(String path, ITraceabilityStorageListener listener);

	void unregisterListener(String path, ITraceabilityStorageListener listener);

	void notifyChanged(ITraceabilityStorage storage, Event event, Object data);
}

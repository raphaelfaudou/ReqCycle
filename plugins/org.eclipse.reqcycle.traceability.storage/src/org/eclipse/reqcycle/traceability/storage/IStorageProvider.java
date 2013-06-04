package org.eclipse.reqcycle.traceability.storage;

public interface IStorageProvider {
	ITraceabilityStorage getStorage(String path);
}

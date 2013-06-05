package org.eclipse.reqcycle.traceability.storage.jena;

import javax.inject.Singleton;

import org.eclipse.reqcycle.traceability.storage.IStorageProvider;
import org.eclipse.reqcycle.traceability.storage.ITraceabilityStorage;
import org.eclipse.ziggurat.inject.ZigguratInject;

@Singleton
public class JenaTraceabilityStorageEngine implements
		IStorageProvider {

	public ITraceabilityStorage getStorage(String path) {
		JenaStorage storage = new JenaStorage(path);
		ZigguratInject.inject(storage);
		return storage;
	}

}

package org.eclipse.reqcycle.traceability.storage.jena;

import javax.inject.Singleton;

import org.agesys.inject.AgesysInject;
import org.eclipse.reqcycle.traceability.storage.ITraceabilityStorage;
import org.eclipse.reqcycle.traceability.storage.IStorageProvider;

@Singleton
public class JenaTraceabilityStorageEngine implements
		IStorageProvider {

	public ITraceabilityStorage getStorage(String path) {
		JenaStorage storage = new JenaStorage(path);
		AgesysInject.inject(storage);
		return storage;
	}

}

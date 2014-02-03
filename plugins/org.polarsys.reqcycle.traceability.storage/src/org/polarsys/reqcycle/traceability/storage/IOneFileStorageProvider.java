package org.polarsys.reqcycle.traceability.storage;

import java.io.InputStream;


public interface IOneFileStorageProvider extends IStorageProvider {

	ITraceabilityStorage getStorageReader(InputStream inputStream);
	
}

package org.eclipse.reqcycle.uri.services;

import java.net.URI;
import java.util.Map;

public interface IReachableExtender {
	Map<String, String> getExtendedProperties(URI uri, Object originalObject);

	boolean handles(URI uri, Object object);
}

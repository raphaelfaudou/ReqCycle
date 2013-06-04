package org.eclipse.reqcycle.uri;

import java.net.URI;

import org.eclipse.reqcycle.uri.model.Reachable;

public interface IReachableCreator {
	/**
	 * Create a {@link Reachable} from an {@link URI} and an {@link Object}
	 * 
	 * @param uri
	 * @param originalObject
	 * @return
	 */
	Reachable getReachable(URI uri, Object originalObject);

	/**
	 * Create a reachable from an URI
	 * 
	 * @param uri
	 * @return
	 */
	Reachable getReachable(URI uri);
}

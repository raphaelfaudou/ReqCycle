package org.eclipse.reqcycle.uri;

import org.eclipse.reqcycle.uri.model.Reachable;

public interface IReachableListener {
	/**
	 * This method is called with the reachable modified
	 * 
	 * @param reachable
	 */
	void hasChanged(Reachable reachable);

}
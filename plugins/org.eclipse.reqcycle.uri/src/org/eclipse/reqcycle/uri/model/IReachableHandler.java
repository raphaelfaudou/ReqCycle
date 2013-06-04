package org.eclipse.reqcycle.uri.model;

public interface IReachableHandler extends IHandler {
	/**
	 * @param t
	 * @return
	 */
	ReachableObject getFromReachable(Reachable t);

	/**
	 * @param t
	 * @return
	 */
	boolean handlesReachable(Reachable t);
}

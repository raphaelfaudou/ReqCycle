package org.eclipse.reqcycle.uri;

import org.eclipse.reqcycle.uri.model.Reachable;

public interface IReachableListenerManager {
	/**
	 * Add a {@link IReachableListener} listener
	 * 
	 * @param reachable
	 *            the reachable watched
	 * @param listener
	 *            the given listener
	 * @param notifyOnResourceChanged
	 *            true if the listener shall be notified if the resource changed
	 */
	void addReachableListener(Reachable reachable, IReachableListener listener);

	void removeReachableListener(IReachableListener listener,
			Reachable reachable);

	/**
	 * Remove a pre registered {@link IReachableListener}
	 * 
	 * @param listener
	 */
	void removeReachableListener(IReachableListener listener);

	/**
	 * Notify all the {@link IReachableListener} registered to this
	 * {@link Reachable}
	 * 
	 * @param r
	 */
	void notifyChanged(Reachable r);
}

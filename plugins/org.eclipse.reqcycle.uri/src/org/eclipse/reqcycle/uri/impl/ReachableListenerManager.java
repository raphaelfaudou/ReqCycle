package org.eclipse.reqcycle.uri.impl;

import java.util.Collection;

import javax.inject.Singleton;

import org.eclipse.reqcycle.uri.IReachableListener;
import org.eclipse.reqcycle.uri.IReachableListenerManager;
import org.eclipse.reqcycle.uri.model.Reachable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

@Singleton
public class ReachableListenerManager implements IReachableListenerManager {

	Multimap<Reachable, IReachableListener> mapReachableToListener = HashMultimap
			.create();
	Multimap<IReachableListener, Reachable> mapListenerToReachable = HashMultimap
			.create();

	boolean preventReentrant = false;

	@Override
	public void addReachableListener(Reachable reachable,
			IReachableListener listener) {
		put(mapReachableToListener, mapListenerToReachable, reachable, listener);
	}

	public <X, Y> void put(Multimap<X, Y> map, Multimap<Y, X> map2,
			X reachable, Y listener) {
		map.put(reachable, listener);
		map2.put(listener, reachable);
	}

	public <X, Y> void remove(Multimap<X, Y> map, Multimap<Y, X> map2,
			X reachable, Y listener) {
		map.remove(reachable, listener);
		map2.remove(listener, reachable);
	}

	@Override
	public synchronized void removeReachableListener(IReachableListener listener) {
		Collection<Reachable> reachables = mapListenerToReachable
				.removeAll(listener);
		for (Reachable r : reachables) {
			mapReachableToListener.remove(r, listener);
		}
	}

	@Override
	public synchronized void notifyChanged(Reachable r) {
		if (!preventReentrant) {
			try {
				preventReentrant = true;
				for (IReachableListener l : mapReachableToListener.get(r)) {
					l.hasChanged(r);
				}
			} catch (RuntimeException e) {
				throw e;
			} finally {
				preventReentrant = false;
			}
		}
	}

	@Override
	public synchronized void removeReachableListener(
			IReachableListener listener, Reachable reachable) {
		remove(mapReachableToListener, mapListenerToReachable, reachable,
				listener);
	}
}

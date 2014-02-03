/*******************************************************************************
 *  * Copyright (c) 2013-2014 AtoS.
 *  * All rights reserved. This program and the accompanying materials
 *  * are made available under the terms of the Eclipse Public License v1.0
 *  * which accompanies this distribution, and is available at
 *  * http://www.eclipse.org/legal/epl-v10.html
 *  *
 *  * Contributors:
 *  *    {INITIAL AUTHOR} - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.reqcycle.types.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.reqcycle.types.ICachedTypedChecker;
import org.eclipse.reqcycle.types.ITypeChecker;
import org.eclipse.reqcycle.types.impl.cache.ReachableStruct;
import org.eclipse.reqcycle.types.impl.cache.TypeCouple;
import org.eclipse.reqcycle.uri.IReachableListener;
import org.eclipse.reqcycle.uri.IReachableListenerManager;
import org.eclipse.reqcycle.uri.model.Reachable;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;



/**
 * This class keeps a cache of type of elements the cache is reinitialized each
 * time a resource is modified
 * 
 * @author tfaure
 * 
 */
@Singleton
public class CachedTypeChecker implements ICachedTypedChecker,
		IReachableListener {

	@Inject
	IReachableListenerManager listener;
	Map<Reachable, ReachableStruct> allResources = new HashMap<Reachable, ReachableStruct>();
	// 30 sec seems a reasonnable time to keep data of the types
	Cache<Reachable, ReachableStruct> cache = CacheBuilder.newBuilder()
			.expireAfterAccess(30000, TimeUnit.MILLISECONDS)
			.removalListener(new RemovalListener<Reachable, ReachableStruct>() {

				@Override
				public void onRemoval(
						RemovalNotification<Reachable, ReachableStruct> arg0) {
					arg0.getValue().clear();
					listener.removeReachableListener(CachedTypeChecker.this,
							arg0.getKey());
				}
			}).build();

	// Set<TypeCouple> allCouples = new HashSet<TypeCouple>();

	@Override
	public boolean is(Reachable reachable, ITypeChecker typeChecker) {
		final Reachable trimFragment = reachable.trimFragment();
		ReachableStruct res;
		try {
			res = cache.get(trimFragment, new Callable<ReachableStruct>() {
				@Override
				public ReachableStruct call() throws Exception {
					ReachableStruct res = new ReachableStruct(trimFragment);
					listener.addReachableListener(trimFragment,
							CachedTypeChecker.this);
					return res;
				}
			});
			if (res == null) {
				allResources.put(trimFragment, res);
			}
			TypeCouple couple = new TypeCouple(reachable, typeChecker);
			if (res.contains(couple)) {
				couple = res.get(couple);
				return couple.getResult();
			} else {
				boolean result = typeChecker.apply(reachable);
				couple.setResult(result);
				res.add(couple);
				return result;
			}
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return typeChecker.apply(reachable);
	}

	@Override
	public void hasChanged(Reachable reachable) {
		ReachableStruct get = cache.getIfPresent(reachable);
		if (get != null) {
			get.clear();
		}
		cache.invalidate(reachable);
		listener.removeReachableListener(this, reachable);
	}
}

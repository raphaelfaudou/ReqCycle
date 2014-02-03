/*******************************************************************************
 * Copyright (c) 2012 Original authors and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Original authors and others - initial API and implementation
 ******************************************************************************/
package org.eclipse.reqcycle.traceability.table.providers;

import java.util.Iterator;
import java.util.concurrent.ExecutionException;

import org.eclipse.jface.viewers.ILazyContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;


public class TraceabilityLazyContentProvider<T> implements ILazyContentProvider {

	private TableViewer viewer;

	private Class<? extends T> clazz;

	private LoadingCache<Integer, T> cache;

	public <E extends T> TraceabilityLazyContentProvider(Class<E> clazz, TableViewer viewer) {
		this.clazz = clazz;
		this.viewer = viewer;
	}

	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, final Object newInput) {
		if(newInput instanceof Iterable<?>) {
			CacheLoader<Integer, T> loader = new CacheLoader<Integer, T>() {

				private int lastArg = -1;
				private Iterator<?> iterator; 
				
				@Override
				public T load(Integer arg0) throws Exception {
					T value = null; 
					/*
					 * Keeps the same iterator between 2 calls if possible, as calling "iterator" on the iterable 
					 * or invoking Iterables.get(Iterable, Int) are rather costly operations.
					 */
					if (iterator == null || lastArg >= arg0){
						iterator = ((Iterable<?>) newInput).iterator();
						lastArg = -1;
					} 
					for (int i = lastArg; i < arg0; i++){
						if (iterator.hasNext()){
							value = clazz.cast(iterator.next());
						}
					}
					lastArg = arg0;
					return value ;
				}

			};
			cache = CacheBuilder.newBuilder().maximumSize(200).build(loader);
		}
	}

	@Override
	public void updateElement(int index) {
		try {
			viewer.replace(cache.get(index), index);
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	public static <T> TraceabilityLazyContentProvider<T> create(Class<T> clazz, TableViewer viewer) {
		return new TraceabilityLazyContentProvider<T>(clazz, viewer);
	}
	
	public void clearCache(){
		cache.invalidateAll();
	}
}

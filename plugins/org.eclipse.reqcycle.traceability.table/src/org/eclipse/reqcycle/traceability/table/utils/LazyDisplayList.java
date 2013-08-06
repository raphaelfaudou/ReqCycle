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
package org.eclipse.reqcycle.traceability.table.utils;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * half-modifiable lazy list that implements a remove(int) and an add() method.
 * @param <T>
 */
public class LazyDisplayList<T> extends AbstractList<T> {

	private Iterable<T> iterable;

	private List<T> cache;

	private Iterator<T> cacheIterator;

	private int bufferSize;

	public LazyDisplayList(Iterable<T> iterable, int bufferSize) {
		this.iterable = iterable;
		this.bufferSize = bufferSize;
	}

	@Override
	public Iterator<T> iterator() {
		return cache == null ? iterable.iterator() : super.iterator();
	}

	@Override
	public T get(int index) {
		useCache();
		while((cache.size() <= index + bufferSize) && cacheIterator.hasNext()) {
			cache.add(cacheIterator.next());
		}
		return cache.get(index);
	}
	
	public boolean hasNext(){
		useCache();
		return cacheIterator.hasNext();
	}

	public int getCacheSize(){
		useCache();
		return cache.size();
	}
	
	@Override
	public int size() {
		useCache();
		while(cacheIterator.hasNext()) {
			cache.add(cacheIterator.next());
		}
		return cache.size();
	}

	private void useCache() {
		if(cache == null) {
			cache = new ArrayList<T>();
			cacheIterator = iterable.iterator();
			while((cache.size() < bufferSize) && cacheIterator.hasNext()) {
				cache.add(cacheIterator.next());
			}
		}
	}
	

	@Override
	public T remove(int index) {
		useCache();
		if (cache.size() - 1 > index){
			T removed = cache.remove(index);
			return removed;
		}
		return null;
	}
	
	@Override
	public void clear() {
		this.iterable = Collections.emptyList();
		this.cache = null;
		super.clear();
	}
	
	// the following methods in AbstractList use #iterator(). Overwrite them to make sure they use the
	// cached version
	@Override
	public boolean contains(Object o) {
		useCache();
		return super.contains(o);
	}

	@Override
	public Object[] toArray() {
		useCache();
		return super.toArray();
	}

	public <S> S[] toArray(S[] a) {
		useCache();
		return super.toArray(a);
	}

	@Override
	public int hashCode() {
		useCache();
		return super.hashCode();
	}
}

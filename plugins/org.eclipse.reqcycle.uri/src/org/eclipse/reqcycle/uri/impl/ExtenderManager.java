package org.eclipse.reqcycle.uri.impl;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.agesys.inject.AgesysInject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.reqcycle.uri.Activator;
import org.eclipse.reqcycle.uri.services.IReachableExtender;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class ExtenderManager {

	private static String EXT_EXTENDER_NAME = "reachableExtender";
	private static List<IReachableExtender> allRegistered = getAllRegistered();
	Cache<Pair, Iterable<IReachableExtender>> cache = CacheBuilder.newBuilder()
			.expireAfterAccess(10, TimeUnit.MINUTES)
			.build(new CacheLoader<Pair, Iterable<IReachableExtender>>() {

				@Override
				public Iterable<IReachableExtender> load(final Pair pair)
						throws Exception {
					return Iterables.filter(allRegistered,
							new Predicate<IReachableExtender>() {
								public boolean apply(IReachableExtender ext) {
									return ext.handles(pair.uri,
											pair.originalObject);
								}
							});

				}
			});

	/**
	 * Returns a list of extenders, can be empty not null
	 * 
	 * @param uri
	 * @param originalObject
	 * @return
	 */
	public Iterable<IReachableExtender> getExtenders(URI uri,
			Object originalObject) {
		Pair pair = new Pair();
		pair.uri = uri;
		pair.originalObject = originalObject;
		try {
			return cache.get(pair);
		} catch (ExecutionException e) {
			return Lists.newArrayList();
		}
	}

	private class Pair {
		URI uri;
		Object originalObject;

		@Override
		public boolean equals(Object arg0) {
			if (arg0 instanceof Pair) {
				Pair pair = (Pair) arg0;
				return Objects.equal(pair.uri, this.uri)
						&& Objects.equal(pair.originalObject,
								this.originalObject);
			}
			return Objects.equal(arg0, this);
		}

		@Override
		public int hashCode() {
			return Objects.hashCode(this.originalObject, this.uri);
		}

	}

	private static List<IReachableExtender> getAllRegistered() {
		return Lists.newArrayList(Iterables.filter(Iterables.transform(Arrays
				.asList(Platform.getExtensionRegistry()
						.getConfigurationElementsFor(Activator.PLUGIN_ID,
								EXT_EXTENDER_NAME)),
				new Function<IConfigurationElement, IReachableExtender>() {
					public IReachableExtender apply(IConfigurationElement conf) {
						IReachableExtender ext = null;
						try {
							ext = (IReachableExtender) conf
									.createExecutableExtension("instance");
							AgesysInject.inject(ext);
						} catch (CoreException e) {
						}
						return ext;
					}
				}), Predicates.notNull()));
	}
}

/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.types;

import java.util.Arrays;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.polarsys.reqcycle.traceability.model.TType;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.RegisteredAttribute;

import com.google.common.base.Function;
import com.google.common.collect.Maps;

import static com.google.common.collect.Iterables.concat;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.transform;

/**
 * Reads all the ttypes extensions
 * 
 * @author tfaure
 * 
 */
public class ExtensionPointReader {

	public static String EXT_ID_TTYPES = "ttypes";
	public static String EXT_ID_ATTRIBUTES = "tattributes";
	public static String ATT_CLASS_NAME = "provider";

	private final class Conf2Provider implements
			Function<IConfigurationElement, TTypeProvider> {
		@Override
		public TTypeProvider apply(IConfigurationElement arg0) {
			TTypeProvider t = null;
			try {
				t = (TTypeProvider) arg0
						.createExecutableExtension(ATT_CLASS_NAME);
			} catch (CoreException e) {
			}
			return t;
		}
	}

	private final class Conf2AttProvider implements
			Function<IConfigurationElement, TAttributeProvider> {
		@Override
		public TAttributeProvider apply(IConfigurationElement arg0) {
			TAttributeProvider t = null;
			try {
				t = (TAttributeProvider) arg0
						.createExecutableExtension(ATT_CLASS_NAME);
			} catch (CoreException e) {
			}
			return t;
		}
	}

	private final class Provider2TAType implements
			Function<TAttributeProvider, Iterable<RegisteredAttribute>> {
		@Override
		public Iterable<RegisteredAttribute> apply(TAttributeProvider arg0) {
			return arg0.getAttributes();
		}
	}

	private final class Provider2TType implements
			Function<TTypeProvider, Iterable<TType>> {
		@Override
		public Iterable<TType> apply(TTypeProvider arg0) {
			return arg0.getTTypes();
		}
	}

	public Map<String, TType> readTTypes() {
		// get all the configuration elements, filter the IConfigurationElement,
		// transform them in TType and create the associated map
		Iterable<IConfigurationElement> allConf = filter(Arrays.asList(Platform
				.getExtensionRegistry().getConfigurationElementsFor(
						Activator.PLUGIN_ID, EXT_ID_TTYPES)),
				IConfigurationElement.class);
		Iterable<TTypeProvider> allProviders = transform(allConf,
				new Conf2Provider());
		Iterable<Iterable<TType>> allTTypes = transform(allProviders,
				new Provider2TType());
		Iterable<TType> allTTypesFlattened = concat(allTTypes);
		return Maps.uniqueIndex(allTTypesFlattened,
				new Function<TType, String>() {

					@Override
					public String apply(TType arg0) {
						return arg0.getId();
					}
				});
	}

	public Map<String, RegisteredAttribute> readAttributes() {
		Iterable<IConfigurationElement> allConf = filter(Arrays.asList(Platform
				.getExtensionRegistry().getConfigurationElementsFor(
						Activator.PLUGIN_ID, EXT_ID_ATTRIBUTES)),
				IConfigurationElement.class);
		Iterable<TAttributeProvider> allProviders = transform(allConf,
				new Conf2AttProvider());
		Iterable<Iterable<RegisteredAttribute>> allTTypes = transform(
				allProviders, new Provider2TAType());
		Iterable<RegisteredAttribute> allTTypesFlattened = concat(allTTypes);
		return Maps.uniqueIndex(allTTypesFlattened,
				new Function<RegisteredAttribute, String>() {

					@Override
					public String apply(RegisteredAttribute arg0) {
						return arg0.getId();
					}
				});
	}
}

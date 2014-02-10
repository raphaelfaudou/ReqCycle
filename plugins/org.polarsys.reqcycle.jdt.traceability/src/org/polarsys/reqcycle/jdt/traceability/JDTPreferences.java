/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.jdt.traceability;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.polarsys.reqcycle.jdt.traceability.types.JDTType;
import org.polarsys.reqcycle.traceability.model.TType;
import org.polarsys.reqcycle.utils.configuration.IConfigurationManager;
import org.polarsys.reqcycle.utils.inject.ZigguratInject;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.Maps;

public class JDTPreferences {
	private static final String JDT_TYPES_CONSTANT = Activator.PLUGIN_ID
			+ ".jdtType";

	public static Map<String, TType> getPreferences() {
		IConfigurationManager manager = ZigguratInject
				.make(IConfigurationManager.class);
		Map<String, Object> map = manager.getSimpleConfiguration(null, null,
				JDT_TYPES_CONSTANT, false);
		if (map == null) {
			return new HashMap<String, TType>();
		}
		return new HashMap<String, TType>(Maps.transformValues(map,
				new MapFunction()));
	}

	public static void savePreferences(Map<String, TType> map) {
		IConfigurationManager manager = ZigguratInject
				.make(IConfigurationManager.class);
		Map<String, Object> newMap = new HashMap<String, Object>(
				Maps.transformValues(map, Functions.toStringFunction()));
		try {
			manager.saveSimpleConfiguration(newMap, null, null,
					JDT_TYPES_CONSTANT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static class MapFunction implements Function<Object, TType> {
		public TType apply(Object o) {
			if (o instanceof String) {
				String string = (String) o;
				return new JDTType(string);
			} else {
				return null;
			}
		}
	}
}

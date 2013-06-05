package org.eclipse.reqcycle.jdt.traceability;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.reqcycle.traceability.model.TraceabilityLink;
import org.eclipse.ziggurat.configuration.IConfigurationManager;
import org.eclipse.ziggurat.inject.ZigguratInject;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.Maps;

public class JDTPreferences {
	private static final String JDT_TYPES_CONSTANT = Activator.PLUGIN_ID
			+ ".jdtType";

	public static Map<String, TraceabilityLink> getPreferences() {
		IConfigurationManager manager = ZigguratInject
				.make(IConfigurationManager.class);
		Map<String, Object> map = manager.getSimpleConfiguration(null, null,
				JDT_TYPES_CONSTANT);
		if (map == null) {
			return new HashMap<String, TraceabilityLink>();
		}
		return new HashMap<String, TraceabilityLink>(Maps.transformValues(map,
				new MapFunction()));
	}

	public static void savePreferences(Map<String, TraceabilityLink> map) {
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

	private static class MapFunction implements
			Function<Object, TraceabilityLink> {
		public TraceabilityLink apply(Object o) {
			if (o instanceof String) {
				String string = (String) o;
				return TraceabilityLink.valueOf(string);
			} else {
				return null;
			}
		}
	}
}

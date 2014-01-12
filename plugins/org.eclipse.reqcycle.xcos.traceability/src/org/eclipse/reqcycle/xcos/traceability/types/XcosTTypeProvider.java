package org.eclipse.reqcycle.xcos.traceability.types;

import java.util.Arrays;
import java.util.Map;

import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.traceability.types.TTypeProvider;


import com.google.common.base.Function;
import com.google.common.collect.Maps;

public class XcosTTypeProvider implements TTypeProvider{

	
	private static TType[] TYPES = new XcosTType[] {
		new XcosTType("COVERS"),
		new XcosTType("IMPLEMENTS"),
		new XcosTType("TRACE")
		 };

	private static Map<String, TType> MAP = Maps.uniqueIndex(
			Arrays.asList(TYPES), new Function<TType, String>() {
				@Override
				public String apply(TType arg0) {
					return (((XcosTType) arg0).getLabel());
				}
			});

	public static TType get(String label) {
		TType result = MAP.get(label);
		if (result == null) {
			result = MAP.get("TRACE");
		}
		return result;
	}

	@Override
	public Iterable<TType> getTTypes() {
		return Arrays.asList(TYPES);
	}

}

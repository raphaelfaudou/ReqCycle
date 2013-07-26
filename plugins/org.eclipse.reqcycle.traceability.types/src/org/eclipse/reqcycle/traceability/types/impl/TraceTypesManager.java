package org.eclipse.reqcycle.traceability.types.impl;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.traceability.types.ExtensionPointReader;
import org.eclipse.reqcycle.traceability.types.ITraceTypesManager;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.RegisteredAttribute;
import org.eclipse.reqcycle.types.ITypesManager;

import com.google.common.collect.Ordering;

@Singleton
public class TraceTypesManager implements ITraceTypesManager {

	@Inject
	ITypesManager manager;

	Map<String, TType> map = new ExtensionPointReader().readTTypes();
	Map<String, RegisteredAttribute> mapAtt = new ExtensionPointReader()
			.readAttributes();

	@Override
	public Iterable<TType> getAllTTypes() {
		return Ordering.usingToString().immutableSortedCopy(map.values());
	}

	@Override
	public TType getTType(String id) {
		return map.get(id);
	}

	@Override
	public RegisteredAttribute getAttribute(String id) {
		return mapAtt.get(id);
	}

	@Override
	public Iterable<RegisteredAttribute> getAllAttributes() {
		return Ordering.usingToString().immutableSortedCopy(mapAtt.values());
	}

}

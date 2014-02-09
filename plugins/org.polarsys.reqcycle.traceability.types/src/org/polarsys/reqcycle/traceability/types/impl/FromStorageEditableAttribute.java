/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.types.impl;

import org.polarsys.reqcycle.traceability.storage.IStorageProvider;
import org.polarsys.reqcycle.traceability.storage.ITraceabilityStorage;
import org.polarsys.reqcycle.traceability.types.ITraceabilityAttributesManager.EditableAttribute;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Attribute;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.AttributeType;
import org.polarsys.reqcycle.uri.model.Reachable;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import static com.google.common.collect.Iterables.transform;

/**
 * A very basic attribute modifier loading the whole rdf each time. Could be
 * optimized ...
 * 
 * @author tfaure
 * 
 */
public class FromStorageEditableAttribute implements EditableAttribute {

	private Attribute attribute;
	private IStorageProvider provider;
	private String path;
	private Reachable reachable;
	private Object value = null;

	public FromStorageEditableAttribute(Attribute attribute,
			Reachable reachable, IStorageProvider provider, String path) {
		this.attribute = attribute;
		this.reachable = reachable;
		this.provider = provider;
		this.path = path;
	}

	@Override
	public String getName() {
		return attribute.getName();
	}

	@Override
	public Object getValue() {
		if (value == null) {
			ITraceabilityStorage storage = getStorage();
			String propValue = storage.getProperty(reachable,
					attribute.getName());
			value = getObjectValue(propValue, attribute.getType());
			storage.dispose();
		}
		return value;
	}

	private ITraceabilityStorage getStorage() {
		return provider.getStorage(path);
	}

	@Override
	public void setValue(Object value) {
		ITraceabilityStorage storage = getStorage();
		storage.addUpdateProperty(reachable, attribute.getName(),
				getStringValue(value, attribute.getType()));
		storage.save();
		storage.dispose();
		this.value = value;

	}

	public String getStringValue(Object val, AttributeType type) {
		switch (type) {
		case INT:
			return String.valueOf((Integer) val);
		case BOOLEAN:
			return String.valueOf((Boolean) val);
		case STRING:
			return val.toString();
		}
		return val.toString();
	}

	public Object getObjectValue(String val, AttributeType type) {
		switch (type) {
		case INT:
			if (val == null || "null".equals(val)) {
				return 0;
			}
			return Integer.valueOf(val);
		case BOOLEAN:
			if (val == null || "null".equals(val)) {
				return false;
			}
			return Boolean.valueOf(val);
		case STRING:
			if (val == null || "null".equals(val)) {
				return "";
			}
			return val;
		}
		return val;
	}

	@Override
	public Object[] getPossibleValues() {
		return Lists.newArrayList(
				transform(attribute.getPossibleValues(),
						new Function<String, Object>() {

							@Override
							public Object apply(String arg0) {
								return getObjectValue(arg0, attribute.getType());
							}
						})).toArray();
	}

	@Override
	public AttributeType getType() {
		return attribute.getType();
	}

}

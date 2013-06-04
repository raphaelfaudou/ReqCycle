package org.eclipse.reqcycle.traceability.types.engine.impl;

import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.CustomType;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Entry;
import org.eclipse.reqcycle.types.IInjectedTypeChecker.IValueInjecter;
import org.eclipse.reqcycle.types.StringWrapper;

public class ConfigurationValueInjecter implements IValueInjecter {

	private CustomType type;

	public ConfigurationValueInjecter(CustomType type) {
		this.type = type;
	}

	@Override
	public <T> T getValue(String typeId, String name,
			Class<T> typeOfTheAttribute) {
		for (Entry e : ((CustomType) type).getEntries()) {
			if (e.getName().equalsIgnoreCase(name)) {
				if (e.getValue() instanceof String) {
					String valueString = (String) e.getValue();
					return StringWrapper.wrap(valueString, typeOfTheAttribute);
				} else if (typeOfTheAttribute.isInstance(e.getValue())) {
					return (T) e.getValue();
				}
			}
		}
		return null;
	}
}

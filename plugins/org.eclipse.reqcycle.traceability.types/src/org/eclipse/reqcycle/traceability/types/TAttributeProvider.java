package org.eclipse.reqcycle.traceability.types;

import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.RegisteredAttribute;

public interface TAttributeProvider {
	Iterable<RegisteredAttribute> getAttributes();
}

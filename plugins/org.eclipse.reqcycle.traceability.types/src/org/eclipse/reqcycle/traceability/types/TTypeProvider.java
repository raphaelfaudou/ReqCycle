package org.eclipse.reqcycle.traceability.types;

import org.eclipse.reqcycle.traceability.model.TType;

public interface TTypeProvider {

	Iterable<TType> getTTypes();

}

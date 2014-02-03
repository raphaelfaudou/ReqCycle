package org.eclipse.reqcycle.jdt.traceability.types;

import org.eclipse.reqcycle.jdt.traceability.JDTPreferences;
import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.traceability.types.TTypeProvider;

public class JDTTTypeProvider implements TTypeProvider {

	@Override
	public Iterable<TType> getTTypes() {
		return JDTPreferences.getPreferences().values();
	}

}

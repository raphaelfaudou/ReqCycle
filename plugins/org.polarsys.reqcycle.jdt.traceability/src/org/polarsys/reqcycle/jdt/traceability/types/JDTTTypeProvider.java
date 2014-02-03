package org.polarsys.reqcycle.jdt.traceability.types;

import org.polarsys.reqcycle.jdt.traceability.JDTPreferences;
import org.polarsys.reqcycle.traceability.model.TType;
import org.polarsys.reqcycle.traceability.types.TTypeProvider;

public class JDTTTypeProvider implements TTypeProvider {

	@Override
	public Iterable<TType> getTTypes() {
		return JDTPreferences.getPreferences().values();
	}

}

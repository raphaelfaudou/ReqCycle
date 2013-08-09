package org.eclipse.reqcycle.emf.traceability.topcasedreq.types;

import java.util.Arrays;

import org.eclipse.reqcycle.emf.traceability.topcasedreq.Activator;
import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.traceability.types.TTypeProvider;

public class TReqTypeProvider implements TTypeProvider {

	public static final TType SATISFY = new TType(Activator.PLUGIN_ID
			+ ".satisfy", "Satisfy (TOPCASED Req)");
	public static final TType REFINE = new TType(Activator.PLUGIN_ID
			+ ".refine", "Refine (TOPCASED Req)");

	public static TType[] ttypes = new TType[] { SATISFY, REFINE };

	@Override
	public Iterable<TType> getTTypes() {
		return Arrays.asList(ttypes);
	}

}

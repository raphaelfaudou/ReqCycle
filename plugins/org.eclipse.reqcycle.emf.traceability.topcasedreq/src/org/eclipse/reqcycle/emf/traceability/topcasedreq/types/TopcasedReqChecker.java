package org.eclipse.reqcycle.emf.traceability.topcasedreq.types;

import org.eclipse.reqcycle.emf.types.EMFTypeChecker;
import org.eclipse.reqcycle.types.ITypeChecker;
import org.eclipse.reqcycle.uri.model.Reachable;

public class TopcasedReqChecker implements ITypeChecker {

	@Override
	public boolean apply(Reachable reachable) {
		return new EMFTypeChecker().apply(reachable)
				&& reachable.getPath().endsWith(".requirement");
	}

}

package org.polarsys.reqcycle.emf.traceability;

import org.eclipse.emf.common.util.URI;
import org.polarsys.reqcycle.emf.utils.EMFUtils;
import org.polarsys.reqcycle.uri.model.IReachableHandler;
import org.polarsys.reqcycle.uri.model.Reachable;

public abstract class AbstractEMFHandler implements IReachableHandler {
	public boolean handles(Reachable t) {
		URI uri = EMFUtils.getEMFURI(t);
		return uri.isPlatform() || uri.isFile();
	}
}

package org.eclipse.reqcycle.emf.traceability;

import org.eclipse.emf.common.util.URI;
import org.eclipse.reqcycle.emf.utils.EMFUtils;
import org.eclipse.reqcycle.uri.model.IReachableHandler;
import org.eclipse.reqcycle.uri.model.Reachable;

public abstract class AbstractEMFHandler implements IReachableHandler {
	public boolean handles(Reachable t) {
		URI uri = EMFUtils.getEMFURI(t);
		return uri.isPlatform() || uri.isFile();
	}
}

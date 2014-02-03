package org.polarsys.reqcycle.emf.functions;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.reqcycle.emf.utils.EMFUtils;
import org.polarsys.reqcycle.uri.model.Reachable;

import com.google.common.base.Function;

public class EObject2Traceable implements Function<EObject, Reachable> {

	@Override
	public Reachable apply(EObject arg0) {
		return EMFUtils.getReachable(EcoreUtil.getURI(arg0));
	}

}

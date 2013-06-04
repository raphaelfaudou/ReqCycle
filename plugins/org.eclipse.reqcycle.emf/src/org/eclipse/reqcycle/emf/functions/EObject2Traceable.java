package org.eclipse.reqcycle.emf.functions;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.reqcycle.emf.utils.EMFUtils;
import org.eclipse.reqcycle.uri.model.Reachable;

import com.google.common.base.Function;

public class EObject2Traceable implements Function<EObject, Reachable> {

	@Override
	public Reachable apply(EObject arg0) {
		return EMFUtils.getReachable(EcoreUtil.getURI(arg0));
	}

}

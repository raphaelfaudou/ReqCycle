package org.eclipse.reqcycle.jdt.types;

import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.reqcycle.jdt.handlers.JDTReachableHandler;
import org.eclipse.reqcycle.types.ITypeChecker;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.reqcycle.uri.model.ReachableObject;

public class MethodChecker implements ITypeChecker {

	@Override
	public boolean apply(Reachable arg0) {
		JDTReachableHandler jdtReachableHandler = new JDTReachableHandler();
		if (!jdtReachableHandler.apply(arg0)) {
			return false;
		}
		ReachableObject handler = jdtReachableHandler.getFromReachable(arg0);
		if (handler == null) {
			return false;
		}
		IJavaElement adapter = (IJavaElement) handler
				.getAdapter(IJavaElement.class);
		return adapter != null && adapter instanceof IMethod;
	}
}

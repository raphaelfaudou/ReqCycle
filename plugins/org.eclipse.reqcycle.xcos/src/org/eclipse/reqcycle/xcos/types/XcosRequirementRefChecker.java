package org.eclipse.reqcycle.xcos.types;


import org.eclipse.reqcycle.types.ITypeChecker;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.reqcycle.uri.model.ReachableObject;
import org.eclipse.reqcycle.xcos.handlers.XcosReachableHandler;
import org.eclipse.reqcycle.xcos.model.XcosElement;
import org.eclipse.reqcycle.xcos.model.XcosRequirementRef;

public class XcosRequirementRefChecker implements ITypeChecker {

	@Override
	public boolean apply(Reachable reachable) {
//		XcosReachableHandler xcosReachableHandler = new XcosReachableHandler();
//		if (!xcosReachableHandler.apply(arg0)) {
//			return false;
//		}
//		ReachableObject handler = jdtReachableHandler.getFromReachable(arg0);
//		if (handler == null) {
//			return false;
//		}
//		XcosElement adapter = (XcosElement) handler
//				.getAdapter(XcosElement.class);
//		return adapter != null && adapter instanceof XcosRequirementRef;
		
		//TODO do real impl
		return true;
	}
}

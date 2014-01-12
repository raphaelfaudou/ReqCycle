package org.eclipse.reqcycle.xcos.handlers;

import static org.eclipse.reqcycle.xcos.utils.XcosUtils.XcosExtension;

import org.eclipse.reqcycle.uri.model.IReachableHandler;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.reqcycle.uri.model.ReachableObject;
import org.eclipse.reqcycle.xcos.model.XcosReachableObject;
import org.eclipse.ziggurat.inject.ZigguratInject;

public class XcosReachableHandler implements IReachableHandler {
	

	public XcosReachableHandler() {
	}

	@Override
	public ReachableObject getFromReachable(Reachable t) {
		XcosReachableObject xcosReachableObject = new XcosReachableObject(t);
		ZigguratInject.inject(xcosReachableObject);
		return xcosReachableObject;
	}

	@Override
	public boolean handlesReachable(Reachable t) {
		
		return t != null && t.getPath() !=null && t.getPath().endsWith(XcosExtension);
	}

	

}

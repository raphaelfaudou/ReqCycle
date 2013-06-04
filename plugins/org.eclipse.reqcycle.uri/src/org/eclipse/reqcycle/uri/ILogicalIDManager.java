package org.eclipse.reqcycle.uri;

import org.eclipse.reqcycle.uri.model.Reachable;

public interface ILogicalIDManager {
	Reachable getReachable(String logicalId);
}

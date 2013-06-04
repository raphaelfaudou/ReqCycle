package org.eclipse.reqcycle.uri;

import org.eclipse.reqcycle.uri.model.Reachable;

public interface IIDContributor {
	Reachable getReachable(String logicalID);
}

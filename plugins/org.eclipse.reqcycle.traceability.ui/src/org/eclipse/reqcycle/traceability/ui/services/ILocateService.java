package org.eclipse.reqcycle.traceability.ui.services;

import org.eclipse.reqcycle.uri.model.Reachable;

public interface ILocateService {

	boolean isOpenable(Reachable reachable);

	void open(Reachable reachable) throws Exception;

}

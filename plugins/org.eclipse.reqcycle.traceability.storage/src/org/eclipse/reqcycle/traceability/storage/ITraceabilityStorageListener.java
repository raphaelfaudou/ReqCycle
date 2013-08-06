package org.eclipse.reqcycle.traceability.storage;

import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine.DIRECTION;
import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.uri.model.Reachable;

/**
 * A listener for {@link ITraceabilityStorage} events
 * 
 * @author tfaure
 * 
 */
public interface ITraceabilityStorageListener {

	void notifyDispose(ITraceabilityStorage storage);

	void notifyCommit(ITraceabilityStorage storage);

	void notifySave(ITraceabilityStorage storage);

	void notifyNewUpwardRelationShip(ITraceabilityStorage storage, TType kind,
			Reachable tracea, Reachable container, Reachable source,
			Reachable... targets);

	void notifyTraceabilityLinksRemoved(ITraceabilityStorage graphStorage,
			Reachable container, Reachable source, Reachable target, TType kind);

	void notifyUpdateRelationShip(ITraceabilityStorage storage, Link oldLink,
			Link newLink, DIRECTION direction);
}

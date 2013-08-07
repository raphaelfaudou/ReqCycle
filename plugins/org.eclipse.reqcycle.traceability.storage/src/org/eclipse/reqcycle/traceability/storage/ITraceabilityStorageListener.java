package org.eclipse.reqcycle.traceability.storage;

/**
 * A listener for {@link ITraceabilityStorage} events
 * 
 * @author tfaure
 * 
 */
public interface ITraceabilityStorageListener {

	public enum Event {
		DISPOSE, COMMIT, SAVE, NEW_UPWARD, REMOVE_TRACEABILITY, UPDATE_TRACEABILITY
	}

	void notify(ITraceabilityStorage storage, Event event, Object data);
}

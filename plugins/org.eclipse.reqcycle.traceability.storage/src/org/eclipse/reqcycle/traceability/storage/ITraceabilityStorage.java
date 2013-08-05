package org.eclipse.reqcycle.traceability.storage;

import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine.DIRECTION;
import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.traceability.model.Pair;
import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.uri.model.Reachable;

public interface ITraceabilityStorage {

	/**
	 * Dispose the storage
	 */
	void dispose();

	/**
	 * start a transaction for the storage
	 */
	void startTransaction();

	/**
	 * Commit the modification during the curent transaction
	 */
	void commit();

	/**
	 * Cancel the modification made during the current transaction
	 */
	void rollback();

	/**
	 * Save the instance of {@link ITraceabilityStorage}
	 */
	void save();

	/**
	 * Create an upward relation ship
	 * 
	 * @param kind
	 *            the kind of relation ship
	 * @param container
	 *            the container of the relation ship
	 * @param source
	 *            the source of the upward relationship
	 * @param source2
	 * @param targets
	 *            the targets of the upward relationship
	 */
	void newUpwardRelationShip(TType kind, Reachable traceaReachable,
			Reachable container, Reachable source, Reachable... targets);

	/**
	 * Get a reachable from the storage
	 * 
	 * @param uri
	 * @return
	 */
	Reachable getReachable(String uri);

	Iterable<Pair<Link, Reachable>> getTraceability(Reachable r,
			DIRECTION direction);

	/**
	 * Returns all the traceability link of the {@link ITraceabilityStorage}
	 * 
	 * @param direction
	 * @return
	 */
	Iterable<Pair<Link, Reachable>> getAllTraceability(DIRECTION direction);

	/**
	 * Removes all the traceability links contained in the given reachable
	 * 
	 * @param reachable
	 */
	void removeTraceabilityLinksContainedIn(Reachable reachable);

	/**
	 * Remove an upward relation ship
	 * 
	 * @param kind
	 * @param container
	 * @param source
	 * @param targets
	 */
	void removeUpwardRelationShip(TType kind, Reachable container,
			Reachable source, Reachable... targets);

	/**
	 * Update in the storage the given relation ship
	 * 
	 * @param oldLink
	 * @param newLin
	 */
	void updateRelationShip(Link oldLink, Link newLink, DIRECTION direction);

	/**
	 * @param reachable
	 *            the element which have a property (can be a traceability link)
	 * @param propertyName
	 *            the name of the property
	 * @param propertyValue
	 *            set to null to remove the property
	 */
	void addUpdateRemoveProperty(Reachable reachable, String propertyName,
			String propertyValue);

	/**
	 * Get property for a given reachable
	 * 
	 * @param reachable
	 * @param propertyName
	 * @return
	 */
	String getProperty(Reachable reachable, String propertyName);

	/**
	 * Add a traceability listener to the storage. The listener is enabled until
	 * it is removed or the storage is disposed
	 * 
	 * @param listener
	 */
	void addTraceabilityStorageListener(ITraceabilityStorageListener listener);

	/**
	 * Remove the listner registered
	 * 
	 * @param listener
	 */
	void removeTraceabilityStorageListener(ITraceabilityStorageListener listener);
}

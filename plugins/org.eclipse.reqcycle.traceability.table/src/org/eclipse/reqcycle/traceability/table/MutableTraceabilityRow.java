/*******************************************************************************
 * Copyright (c) 2012 Original authors and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Original authors and others - initial API and implementation
 ******************************************************************************/
package org.eclipse.reqcycle.traceability.table;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.resources.IProject;
import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.traceability.storage.IStorageProvider;
import org.eclipse.reqcycle.traceability.storage.ITraceabilityStorage;
import org.eclipse.reqcycle.traceability.table.utils.TableUtils;
import org.eclipse.reqcycle.uri.model.Reachable;

import ca.odell.glazedlists.EventList;

import com.google.common.collect.Iterables;

public class MutableTraceabilityRow extends TraceabilityRow {

	@Inject
	@Named("RDF")
	protected IStorageProvider provider;


	private EventList<TraceabilityRow> eventList;

	protected IProject project;

	public MutableTraceabilityRow(Link traceabilityLink, IProject project, EventList<TraceabilityRow> eventList) {
		super(traceabilityLink);
		this.eventList = eventList;
		this.project = project;
	}


	/**
	 * Deletes the data link and the row from the table.
	 */
	public void deleteTraceabilityLink() {
		if(eventList != null) {
			eventList.getReadWriteLock().writeLock().lock();
			ITraceabilityStorage storage = TableUtils.getStorage(project, provider);
			storage.startTransaction();
			try {
				Reachable source = Iterables.get(link.getSources(), 0);
				Reachable target = Iterables.get(link.getTargets(), 0);
				storage.removeUpwardRelationShip(link.getKind(), null, source, target);
				storage.commit();
				eventList.remove(this);
			} catch (Exception e) {
				e.printStackTrace();
				storage.rollback();
			} finally {
				storage.save();
				eventList.getReadWriteLock().writeLock().unlock();
			}
		}
	}

}

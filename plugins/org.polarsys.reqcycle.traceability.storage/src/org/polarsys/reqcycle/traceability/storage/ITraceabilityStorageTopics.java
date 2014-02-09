/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.storage;

/**
 * Topics used in events related to the traceability storage
 */
public interface ITraceabilityStorageTopics {

	public static final String ALLTOPICS = "TOPIC_REQCYCLE_TRACEABILITY/*"; //$NON-NLS-1$

	public static final String DISPOSE = "TOPIC_REQCYCLE_TRACEABILITY/DISPOSE"; //$NON-NLS-1$

	public static final String COMMIT =  "TOPIC_REQCYCLE_TRACEABILITY/COMMIT"; //$NON-NLS-1$

	public static final String SAVE =  "TOPIC_REQCYCLE_TRACEABILITY/SAVE"; //$NON-NLS-1$

	public static final String NEW =  "TOPIC_REQCYCLE_TRACEABILITY/NEW"; //$NON-NLS-1$

	public static final String REMOVE =  "TOPIC_REQCYCLE_TRACEABILITY/REMOVE"; //$NON-NLS-1$

	public static final String UPDATE =  "TOPIC_REQCYCLE_TRACEABILITY/UPDATE"; //$NON-NLS-1$
	
}

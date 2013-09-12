/*****************************************************************************
 * Copyright (c) 2013 AtoS.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Anass RADOUANI (AtoS) anass.radouani@atos.net - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.reqcycle.repository.data;


/**
 * The Interface IDataTopics.
 */
public interface IDataTopics {

	/** The Constant ALLTOPICS. */
	public static final String ALLTOPICS = "TOPIC_REQCYCLE_DATA/*"; //$NON-NLS-1$

	/** The Constant NEW Data Element. */
	public static final String NEW = "TOPIC_REQCYCLE_DATA/NEW"; //$NON-NLS-1$

	/** The Constant REMOVE Data Element. */
	public static final String REMOVE = "TOPIC_REQCYCLE_DATA/REMOVE"; //$NON-NLS-1$

	/** The Constant UPDATE Data Element. */
	public static final String UPDATE = "TOPIC_REQCYCLE_DATA/UPDATE"; //$NON-NLS-1$

	/** The Constant SAVE Data. */
	public static final String SAVE = "TOPIC_REQCYCLE_DATA/SAVE"; //$NON-NLS-1$

}

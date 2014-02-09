/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Anass Radouani (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.repository.ui;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.polarsys.reqcycle.repository.ui.messages"; //$NON-NLS-1$

	public static String ADD_RESOURCE_ICON;

	public static String ADD_RESOURCE_TEXT;

	public static String EDIT_RESOURCE_TEXT;

	public static String DELETE_RESOURCE_ICON;

	public static String GREEN_STATUS_ICON;

	public static String GREY_STATUS_ICON;

	public static String RED_STATUS_ICON;

	public static String REMOVE_RESOURCE_TEXT;

	public static String REQ_RESOURCE_VIEW_ID;

	public static String SYNC_ERROR_MSG;

	public static String SYNC_REQ_RESOURCE_TASK_TITLE;

	public static String SYNC_RESOURCE_ICON;

	public static String SYNC_RESOURCE_TEXT;

	public static String YELLOW_STATUS_ICON;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}

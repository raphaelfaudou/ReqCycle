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

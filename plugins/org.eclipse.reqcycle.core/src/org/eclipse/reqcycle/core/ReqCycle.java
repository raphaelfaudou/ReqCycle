package org.eclipse.reqcycle.core;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class ReqCycle extends AbstractUIPlugin {
	public static final String PLUGIN_ID = "org.eclipse.reqcycle.core"; //$NON-NLS-1$
	// The shared instance
	private static ReqCycle plugin;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static ReqCycle getDefault() {
		return plugin;
	}

	// The plug-in ID

	/**
	 * The constructor
	 */
	public ReqCycle() {
	}

}

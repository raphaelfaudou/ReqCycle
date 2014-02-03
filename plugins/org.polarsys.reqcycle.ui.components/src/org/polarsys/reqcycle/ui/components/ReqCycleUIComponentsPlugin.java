/*******************************************************************************
 *  * Copyright (c) 2013-2014 AtoS.
 *  * All rights reserved. This program and the accompanying materials
 *  * are made available under the terms of the Eclipse Public License v1.0
 *  * which accompanies this distribution, and is available at
 *  * http://www.eclipse.org/legal/epl-v10.html
 *  *
 *  * Contributors:
 *  *    {INITIAL AUTHOR} - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.ui.components;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class ReqCycleUIComponentsPlugin implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		ReqCycleUIComponentsPlugin.context = bundleContext;
	}

	public void stop(BundleContext bundleContext) throws Exception {
		ReqCycleUIComponentsPlugin.context = null;
	}

}

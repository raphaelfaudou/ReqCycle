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
 *  Papa Issa DIAKHATE (AtoS) papa-issa.diakhate@atos.net - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.reqcycle.ui.datepropseditor;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class DatePropsEditorPlugin implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		DatePropsEditorPlugin.context = bundleContext;
	}

	public void stop(BundleContext bundleContext) throws Exception {
		DatePropsEditorPlugin.context = null;
	}

}

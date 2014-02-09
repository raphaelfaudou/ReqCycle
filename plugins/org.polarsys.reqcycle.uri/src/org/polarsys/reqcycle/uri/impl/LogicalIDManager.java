/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.uri.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.polarsys.reqcycle.uri.Activator;
import org.polarsys.reqcycle.uri.IIDContributor;
import org.polarsys.reqcycle.uri.ILogicalIDManager;
import org.polarsys.reqcycle.uri.model.Reachable;
import org.eclipse.ziggurat.inject.ZigguratInject;

public class LogicalIDManager implements ILogicalIDManager {
	private static String EXT_NAME = "idContributors";

	private static List<IIDContributor> contributors = getContributors();

	@Override
	public Reachable getReachable(String logicalId) {
		for (IIDContributor cont : contributors) {
			Reachable reachable = cont.getReachable(logicalId);
			if (reachable != null) {
				return reachable;
			}
		}
		return null;
	}

	private static List<IIDContributor> getContributors() {
		IConfigurationElement[] contributors = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(Activator.PLUGIN_ID, EXT_NAME);
		List<IIDContributor> result = new ArrayList<IIDContributor>(
				contributors.length);
		for (IConfigurationElement c : contributors) {
			try {
				IIDContributor createExecutableExtension = (IIDContributor) c
						.createExecutableExtension("instance");
				ZigguratInject.inject(createExecutableExtension);
				result.add(createExecutableExtension);
			} catch (CoreException e) {
				e.printStackTrace();

			}
		}
		return result;
	}
}

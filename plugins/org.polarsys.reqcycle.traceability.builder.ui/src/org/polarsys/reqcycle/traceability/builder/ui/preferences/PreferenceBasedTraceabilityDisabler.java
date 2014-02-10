/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.builder.ui.preferences;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.polarsys.reqcycle.traceability.builder.ITraceabilityAnalyserDisabler;
import org.polarsys.reqcycle.uri.visitors.IVisitor;
import org.polarsys.reqcycle.utils.configuration.IConfigurationManager;
import org.polarsys.reqcycle.utils.configuration.IConfigurationManager.Scope;

@Singleton
public class PreferenceBasedTraceabilityDisabler implements
		ITraceabilityAnalyserDisabler {

	@Inject
	IConfigurationManager manager;

	@Override
	public boolean isDisabled(Class<? extends IVisitor> visitorClass) {
		Map<String, Object> pref = manager.getSimpleConfiguration(null,
				Scope.WORKSPACE, AnalysersPreferencePage.PREF_ID, true);
		return pref != null
				&& Boolean.FALSE.equals(pref.get(visitorClass.getName()));
	}

}

/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.types.engine.functions;

import org.polarsys.reqcycle.traceability.engine.ITraceabilityEngine.DIRECTION;
import org.polarsys.reqcycle.traceability.model.Filter;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Configuration;
import org.polarsys.reqcycle.traceability.types.engine.impl.ConfigurationBasedFilter;
import org.polarsys.reqcycle.utils.inject.ZigguratInject;

import com.google.common.base.Function;

public class Configuration2Filter implements Function<Configuration, Filter> {

	private DIRECTION direction;

	public Configuration2Filter(DIRECTION direction) {
		this.direction = direction;
	}

	@Override
	public Filter apply(Configuration arg0) {
		ConfigurationBasedFilter configurationBasedFilter = new ConfigurationBasedFilter(
				direction, arg0);
		ZigguratInject.inject(configurationBasedFilter);
		return configurationBasedFilter;
	}

}

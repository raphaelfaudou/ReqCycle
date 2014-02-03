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
package org.eclipse.reqcycle.traceability.types.engine.functions;

import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine.DIRECTION;
import org.eclipse.reqcycle.traceability.model.Filter;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Configuration;
import org.eclipse.reqcycle.traceability.types.engine.impl.ConfigurationBasedFilter;
import org.eclipse.ziggurat.inject.ZigguratInject;

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

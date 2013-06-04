package org.eclipse.reqcycle.traceability.types.engine.functions;

import org.agesys.inject.AgesysInject;
import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine.DIRECTION;
import org.eclipse.reqcycle.traceability.model.Filter;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Configuration;
import org.eclipse.reqcycle.traceability.types.engine.impl.ConfigurationBasedFilter;

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
		AgesysInject.inject(configurationBasedFilter);
		return configurationBasedFilter;
	}

}

package org.eclipse.reqcycle.repository.connector.local;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import org.eclipse.reqcycle.repository.connector.IConnector;
import org.eclipse.reqcycle.repository.data.IDataManager;

import RequirementSourceConf.RequirementSource;

public class LocalConnector implements IConnector {

	public final static String LOCAL_CONNECTOR_ID = "org.eclipse.reqcycle.repository.connector.local.connectorCore";

	@Inject
	IDataManager manager;

	public LocalConnector() {
	}

	@Override
	public void initializeWithRequirementSource(RequirementSource requirementSource) {
	}

	@Override
	public Callable<RequirementSource> createRequirementSource() {
		return new Callable<RequirementSource>() {

			@Override
			public RequirementSource call() throws Exception {
				RequirementSource source = manager.createRequirementSource();
				return source;
			}
		};
	}
}

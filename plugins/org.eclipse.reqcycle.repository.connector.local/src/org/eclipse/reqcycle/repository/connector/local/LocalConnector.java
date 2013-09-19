package org.eclipse.reqcycle.repository.connector.local;

import java.util.concurrent.Callable;

import org.eclipse.reqcycle.repository.connector.IConnector;

import RequirementSourceData.RequirementSource;
import RequirementSourceData.RequirementSourceDataFactory;

public class LocalConnector implements IConnector {

	public final static String LOCAL_CONNECTOR_ID = "org.eclipse.reqcycle.repository.connector.local.connectorCore";

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
				return RequirementSourceDataFactory.eINSTANCE.createRequirementSource();
			}
		};
	}
}

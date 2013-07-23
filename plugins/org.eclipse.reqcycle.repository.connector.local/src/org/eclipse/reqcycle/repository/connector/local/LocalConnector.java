package org.eclipse.reqcycle.repository.connector.local;

import java.util.concurrent.Callable;

import org.eclipse.reqcycle.repository.connector.IConnector;

import DataModel.DataModelFactory;
import DataModel.RequirementSource;

public class LocalConnector implements IConnector {

	public LocalConnector() {
	}

	@Override
	public void initializeWithRequirementSource(RequirementSource requirementSource) {
	}

	@Override
	public Callable<RequirementSource> createRequirementSource() {
		return new  Callable<RequirementSource>() {
			@Override
			public RequirementSource call() throws Exception {
				return DataModelFactory.eINSTANCE.createRequirementSource();
			}
		};
	}
}

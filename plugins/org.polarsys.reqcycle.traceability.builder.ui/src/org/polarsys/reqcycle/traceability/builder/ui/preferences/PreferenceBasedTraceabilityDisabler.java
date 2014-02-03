package org.polarsys.reqcycle.traceability.builder.ui.preferences;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.polarsys.reqcycle.traceability.builder.ITraceabilityAnalyserDisabler;
import org.polarsys.reqcycle.uri.visitors.IVisitor;
import org.eclipse.ziggurat.configuration.IConfigurationManager;
import org.eclipse.ziggurat.configuration.IConfigurationManager.Scope;

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

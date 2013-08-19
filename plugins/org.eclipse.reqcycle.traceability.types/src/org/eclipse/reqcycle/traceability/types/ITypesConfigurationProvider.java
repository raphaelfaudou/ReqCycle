package org.eclipse.reqcycle.traceability.types;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Configuration;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer;

public interface ITypesConfigurationProvider {
	public static String CONF_PREF_ID = "org.eclipse.reqcycle.traceability.types.conf";

	/**
	 * Returns the root model
	 * 
	 * @return
	 */
	TypeConfigContainer getContainer();

	/**
	 * Get the configuration with the given id
	 * 
	 * @param id
	 * @return
	 */
	Configuration getConfiguration(String id);

	/**
	 * Returns the default configuration
	 * 
	 * @return
	 */
	Configuration getDefaultConfiguration();

	void setDefaultConfiguration(Configuration conf);

	/**
	 * All the objects returned by the {@link ITypesConfigurationProvider} are
	 * copys so it is needed to save modifications Save a given object in
	 * parameter Supports : {@link TypeConfigContainer}, {@link Configuration}
	 * 
	 * @param eobject
	 */
	void save(EObject eobject);
}

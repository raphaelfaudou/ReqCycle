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
package org.polarsys.reqcycle.traceability.types;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Configuration;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer;

public interface ITypesConfigurationProvider {
	public static String CONF_PREF_ID = "org.polarsys.reqcycle.traceability.types.conf";

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

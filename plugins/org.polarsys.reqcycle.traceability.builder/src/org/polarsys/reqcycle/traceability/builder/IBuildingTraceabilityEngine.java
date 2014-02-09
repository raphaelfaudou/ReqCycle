/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.builder;

import org.polarsys.reqcycle.traceability.builder.ITraceabilityBuilder.IBuilderCallBack;
import org.polarsys.reqcycle.traceability.engine.ITraceabilityEngine;

/**
 * A Traceability Engine registering traceability information
 * 
 * @author tfaure
 * 
 */
public interface IBuildingTraceabilityEngine extends ITraceabilityEngine,
		IBuilderCallBack {
	/**
	 * This string can be used as an option to prevent the check of the cache if
	 * set to FALSE the cache is not checked
	 */
	public static String OPTION_CHECK_CACHE = "optionCheckCache";

}

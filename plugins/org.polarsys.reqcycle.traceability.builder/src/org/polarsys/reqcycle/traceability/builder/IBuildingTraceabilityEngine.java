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

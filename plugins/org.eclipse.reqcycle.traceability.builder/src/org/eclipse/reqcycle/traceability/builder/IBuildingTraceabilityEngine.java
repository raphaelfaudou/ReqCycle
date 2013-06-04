package org.eclipse.reqcycle.traceability.builder;

import org.eclipse.reqcycle.traceability.builder.ITraceabilityBuilder.IBuilderCallBack;
import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine;

/**
 * A Traceability Engine registering traceability information
 * 
 * @author tfaure
 * 
 */
public interface IBuildingTraceabilityEngine extends ITraceabilityEngine,
		IBuilderCallBack {
	public static String OPTION_CHECK_CACHE = "optionCheckCache";
}

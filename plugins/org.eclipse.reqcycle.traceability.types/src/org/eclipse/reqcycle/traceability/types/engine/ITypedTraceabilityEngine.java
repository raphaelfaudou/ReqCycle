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
package org.eclipse.reqcycle.traceability.types.engine;

import java.util.Iterator;

import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine;
import org.eclipse.reqcycle.traceability.engine.Request;
import org.eclipse.reqcycle.traceability.exceptions.EngineException;
import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.traceability.model.Pair;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Configuration;
import org.eclipse.reqcycle.uri.model.Reachable;

/**
 * A Typed traceability engine will compute traceability links according to
 * typed defined by user. The getTraceability (Request...) from
 * {@link ITraceabilityEngine} uses a default configuration otherwise the
 * getTraceability (Configuration, Request...) will use the given config
 * 
 * @author tfaure
 * 
 */
public interface ITypedTraceabilityEngine extends ITraceabilityEngine {
	/**
	 * @return a traceability {@link Iterator} from the {@link Request} and the
	 *         {@link Configuration}
	 * 
	 */
	Iterator<Pair<Link, Reachable>> getTraceability(Configuration typeConfig,
			Request... requests) throws EngineException;
}

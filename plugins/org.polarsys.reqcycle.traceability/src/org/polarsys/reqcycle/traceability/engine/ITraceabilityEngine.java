/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.engine;

import java.util.Iterator;

import org.polarsys.reqcycle.traceability.exceptions.EngineException;
import org.polarsys.reqcycle.traceability.model.Link;
import org.polarsys.reqcycle.traceability.model.Pair;
import org.polarsys.reqcycle.uri.model.Reachable;

/**
 * Interface for traceability management
 */
public interface ITraceabilityEngine {
	public enum DIRECTION {
		UPWARD, DOWNWARD
	}

	/**
	 * @return a traceability {@link Iterator} from the {@link Request}
	 * 
	 */
	Iterator<Pair<Link, Reachable>> getTraceability(Request... requests)
			throws EngineException;

}

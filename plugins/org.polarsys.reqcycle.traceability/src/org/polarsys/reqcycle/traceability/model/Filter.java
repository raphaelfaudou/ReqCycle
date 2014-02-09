/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.model;

import org.polarsys.reqcycle.uri.model.Reachable;

/**
 * A filter determines if the given pair is selected for the traceability
 * 
 * @author tfaure
 * 
 */
public interface Filter {
	boolean apply(Pair<Link, Reachable> pair);
}

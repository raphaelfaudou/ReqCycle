/*****************************************************************************
 * Copyright (c) 2013 AtoS.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Anass RADOUANI (AtoS) anass.radouani@atos.net - Initial API and implementation
 *
  *****************************************************************************/
package org.eclipse.reqcycle.repository.requirement.data;

import java.util.Collection;

import DataModel.Contained;
import DataModel.Scope;


public interface IScopeManager {

	public Collection<Scope> getAllScopes();

	public void addToScope(Scope scope, Contained element);
	
	public void addToScope(Scope scope, Collection<Contained> elements);

	public void removeFromScope(Contained contained);
	
}

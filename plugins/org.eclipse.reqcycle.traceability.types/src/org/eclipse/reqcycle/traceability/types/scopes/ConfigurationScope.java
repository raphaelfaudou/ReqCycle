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
package org.eclipse.reqcycle.traceability.types.scopes;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.reqcycle.traceability.model.scopes.IScope;
import org.eclipse.reqcycle.uri.IReachableCreator;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.ziggurat.configuration.Activator;
import org.eclipse.ziggurat.inject.ZigguratInject;

/**
 * A scope containing files in the configuration area
 * 
 * @author tfaure
 * 
 */
public class ConfigurationScope implements IScope {

	IReachableCreator creator = ZigguratInject.make(IReachableCreator.class);

	@Override
	public Iterator<Reachable> getReachables() {
		Set<Reachable> result = new HashSet<Reachable>();
		File location = Activator.getDefault().getStateLocation().toFile();
		if (location.isDirectory()) {
			for (File f : location.listFiles()) {
				result.add(creator.getReachable(f.toURI(), f));
			}
		}
		return result.iterator();
	}
}

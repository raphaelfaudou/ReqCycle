/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.sesame.graph;

import org.polarsys.reqcycle.traceability.storage.blueprints.graph.IGraphProvider;
import org.polarsys.reqcycle.traceability.storage.blueprints.storage.GraphStorageProvider;
import org.polarsys.reqcycle.utils.inject.ZigguratInject;

public class RDFGraphStorageProvider extends GraphStorageProvider {

	@Override
	protected IGraphProvider getProvider() {
		SailGraphProvider provider = new SailGraphProvider();
		ZigguratInject.inject(provider);
		return provider;
	}

}

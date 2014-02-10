/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.sesame.handlers;

import java.io.File;

import javax.inject.Inject;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.polarsys.reqcycle.sesame.graph.SailBusinessOperations;
import org.polarsys.reqcycle.uri.IReachableCreator;
import org.polarsys.reqcycle.uri.exceptions.VisitableException;
import org.polarsys.reqcycle.uri.model.Reachable;
import org.polarsys.reqcycle.uri.model.ReachableObject;
import org.polarsys.reqcycle.uri.visitors.IVisitable;
import org.polarsys.reqcycle.utils.inject.ZigguratInject;

import com.tinkerpop.blueprints.Vertex;

public class SesameReachableObject implements ReachableObject {

	private Reachable reachable;
	@Inject
	IReachableCreator creator;
	private SailBusinessOperations op;

	public SesameReachableObject(Reachable t, SailBusinessOperations op) {
		this.reachable = t;
		this.op = op;
	}

	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}

	@Override
	public IVisitable getVisitable() throws VisitableException {
		SesameVisitable sesameVisitable = new SesameVisitable(reachable);
		ZigguratInject.inject(sesameVisitable);
		return sesameVisitable;
	}

	@Override
	public String getRevisionIdentification() {
		String path = reachable.getPath();
		if ("file".equals(reachable.getScheme())) {
			File f = new File(path);
			if (f.exists()) {
				return String.valueOf(f.lastModified());
			}
		} else if ("platform".equals(reachable.getScheme())) {
			IFile f = ResourcesPlugin.getWorkspace().getRoot()
					.getFile(new Path(path));
			if (f.exists()) {
				return String.valueOf(f.getModificationStamp());
			}
		}
		return null;
	}

	@Override
	public Reachable getReachable(Object o) {
		if (o instanceof Vertex) {
			Vertex vertex = (Vertex) o;
			return op.getReachable(vertex);
		} else {
			return this.reachable;
		}
	}
}

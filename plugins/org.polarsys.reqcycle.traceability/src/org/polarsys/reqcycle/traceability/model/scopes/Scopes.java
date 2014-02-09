/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.model.scopes;

import javax.inject.Inject;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.polarsys.reqcycle.uri.IReachableManager;
import org.polarsys.reqcycle.uri.model.Reachable;
import org.polarsys.reqcycle.uri.utils.ReachableUtils;
import org.eclipse.ziggurat.inject.ZigguratInject;

public class Scopes {
	public static IScope getProjectScope(IResource r) {
		ResourceVisitor visitor = new ResourceVisitor();
		ZigguratInject.inject(visitor);
		try {
			r.getProject().accept(visitor);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return visitor.getResult();
	}

	public static IScope getSubTree(IResource r) {
		ResourceVisitor visitor = new ResourceVisitor();
		ZigguratInject.inject(visitor);
		try {
			r.accept(visitor);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return visitor.getResult();
	}

	public static IScope getWorkspaceScope() {
		ResourceVisitor visitor = new ResourceVisitor();
		ZigguratInject.inject(visitor);
		try {
			ResourcesPlugin.getWorkspace().getRoot().accept(visitor);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return visitor.getResult();
	}

	private static class ResourceVisitor implements IResourceVisitor {
		@Inject
		IReachableManager manager;
		CompositeScope scope = new CompositeScope();

		@Override
		public boolean visit(IResource resource) throws CoreException {
			Reachable r = ReachableUtils.getReachable(resource);
			if (r != null) {
				scope.add(new ResourceScope(r));
			}
			return true;
		}

		public IScope getResult() {
			return scope;
		}
	}
}

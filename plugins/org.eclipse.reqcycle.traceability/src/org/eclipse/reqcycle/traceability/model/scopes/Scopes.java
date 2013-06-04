package org.eclipse.reqcycle.traceability.model.scopes;

import javax.inject.Inject;

import org.agesys.inject.AgesysInject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.reqcycle.uri.IReachableManager;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.reqcycle.uri.utils.ReachableUtils;

public class Scopes {
	public static IScope getProjectScope(IResource r) {
		ResourceVisitor visitor = new ResourceVisitor();
		AgesysInject.inject(visitor);
		try {
			r.getProject().accept(visitor);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return visitor.getResult();
	}

	public static IScope getSubTree(IResource r) {
		ResourceVisitor visitor = new ResourceVisitor();
		AgesysInject.inject(visitor);
		try {
			r.accept(visitor);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return visitor.getResult();
	}

	public static IScope getWorkspaceScope() {
		ResourceVisitor visitor = new ResourceVisitor();
		AgesysInject.inject(visitor);
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

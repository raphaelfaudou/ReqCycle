package org.eclipse.reqcycle.traceability.builder.impl;

import java.util.ArrayDeque;

import javax.inject.Inject;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.reqcycle.traceability.builder.IBuildingTraceabilityEngine;
import org.eclipse.reqcycle.traceability.builder.ITraceabilityBuilder;
import org.eclipse.reqcycle.traceability.builder.exceptions.BuilderException;
import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine;
import org.eclipse.reqcycle.uri.IReachableManager;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.reqcycle.uri.utils.ReachableUtils;

public class ResourceDeltaBuilderVisitor implements IResourceDeltaVisitor,
		IResourceVisitor {
	@Inject
	ITraceabilityBuilder builder;
	@Inject
	IReachableManager manager;
	@Inject
	ITraceabilityEngine engine;
	private IProgressMonitor monitor;
	private boolean forceBuild;
	private ArrayDeque<Reachable> toBuild = new ArrayDeque<Reachable>();

	public ResourceDeltaBuilderVisitor(IProgressMonitor monitor,
			boolean forceBuild) {
		this.monitor = monitor;
		this.forceBuild = forceBuild;
	}

	@Override
	public boolean visit(IResourceDelta delta) throws CoreException {
		IResource res = delta.getResource();
		return doVisit(res);
	}

	private boolean doVisit(IResource res) {
		if (!(res instanceof IContainer)) {
			if (engine instanceof IBuildingTraceabilityEngine) {
				Reachable reachable = ReachableUtils.getReachable(res);
				if (reachable != null) {
					toBuild.add(reachable);
				}
			} else {
				// the engine does not need build : returns false
				return false;
			}
		}
		return !monitor.isCanceled();
	}

	public void build() throws BuilderException {
		IBuildingTraceabilityEngine callBack = null;
		if (engine instanceof IBuildingTraceabilityEngine) {
			monitor.beginTask("Traceability Build", toBuild.size());
			callBack = (IBuildingTraceabilityEngine) engine;
			for (Reachable r : toBuild) {
				if (monitor.isCanceled()) {
					break;
				}
				monitor.subTask(r.toString());
				builder.build(r, callBack, forceBuild);
				monitor.worked(1);
			}
		}
	}

	@Override
	public boolean visit(IResource resource) throws CoreException {
		return doVisit(resource);
	}
}

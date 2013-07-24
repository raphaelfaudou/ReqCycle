package org.eclipse.reqcycle.traceability.builder.impl;

import java.util.List;

import org.eclipse.reqcycle.traceability.builder.ITraceabilityBuilder.IBuilderCallBack;
import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.uri.model.Reachable;

import com.google.common.base.Preconditions;

public class DelegatedAndDecoratedBuilderCallBack implements IBuilderCallBack {

	IBuilderCallBack callBack = null;

	public DelegatedAndDecoratedBuilderCallBack(IBuilderCallBack callBack) {
		this.callBack = callBack;
	}

	@Override
	public boolean needsBuild(Reachable reachable) {
		return callBack.needsBuild(reachable);
	}

	@Override
	public void startBuild(Reachable reachable) {
		callBack.startBuild(reachable);
	}

	@Override
	public void endBuild(Reachable reachable) {
		callBack.endBuild(reachable);
	}

	@Override
	public void newUpwardRelation(Object resource, Object source,
			List<? extends Object> targets, TType kind) {
		String check = "arguments must be different to null";
		Preconditions.checkNotNull(resource, check);
		Preconditions.checkNotNull(source, check);
		Preconditions.checkNotNull(targets, check);
		Preconditions.checkNotNull(kind, check);
		callBack.newUpwardRelation(resource, source, targets, kind);
	}

	@Override
	public void errorOccurs(Reachable reachable, Throwable t) {
		callBack.errorOccurs(reachable, t);
	}

}

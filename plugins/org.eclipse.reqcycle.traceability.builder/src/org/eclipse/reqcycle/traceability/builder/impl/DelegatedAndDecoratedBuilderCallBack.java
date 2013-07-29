package org.eclipse.reqcycle.traceability.builder.impl;

import java.util.List;
import java.util.Set;

import org.eclipse.reqcycle.traceability.builder.IBuildingDecoration;
import org.eclipse.reqcycle.traceability.builder.IBuildingDecoration.Composite;
import org.eclipse.reqcycle.traceability.builder.ITraceabilityBuilder.IBuilderCallBack;
import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.uri.model.Reachable;

import com.google.common.base.Preconditions;

public class DelegatedAndDecoratedBuilderCallBack implements IBuilderCallBack {

	IBuilderCallBack callBack = null;
	Set<IBuildingDecoration> decorations = new ExtensionPointReader().read();

	public DelegatedAndDecoratedBuilderCallBack(IBuilderCallBack callBack) {
		this.callBack = callBack;
	}

	@Override
	public boolean needsBuild(Reachable reachable) {
		return callBack.needsBuild(reachable);
	}

	@Override
	public void startBuild(Reachable reachable) {
		for (IBuildingDecoration d : decorations) {
			d.startBuild(this, reachable);
		}
		callBack.startBuild(reachable);
	}

	@Override
	public void endBuild(Reachable reachable) {
		for (IBuildingDecoration d : decorations) {
			d.endBuild(this, reachable);
		}
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
		boolean keepOriginalLink = true;
		for (IBuildingDecoration d : decorations) {
			keepOriginalLink &= d.newUpwardRelation(this, resource, source,
					targets, kind);
		}
		if (keepOriginalLink) {
			Composite c = getComposite(resource, source, targets, kind);
			callBack.newUpwardRelation(c.resource, c.source, c.targets, c.kind);
		}
	}

	private Composite getComposite(Object resource, Object source,
			List<? extends Object> targets, TType kind) {
		Composite compo = new Composite();
		compo.resource = resource;
		compo.source = source;
		compo.targets = targets;
		compo.kind = kind;
		for (IBuildingDecoration d : decorations) {
			Composite old;
			try {
				old = (Composite) compo.clone();
				d.transform(compo);
				if (compo == null || compo.kind == null
						|| compo.resource == null || compo.source == null
						|| compo.targets == null) {
					compo = old;
				}
			} catch (CloneNotSupportedException e) {
			}
		}
		return compo;
	}

	@Override
	public void errorOccurs(Reachable reachable, Throwable t) {
		callBack.errorOccurs(reachable, t);
	}

}

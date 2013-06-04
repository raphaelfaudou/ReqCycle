package org.eclipse.reqcycle.traceability.builder.impl;

import java.util.ArrayDeque;
import java.util.Collection;

import org.agesys.inject.AgesysInject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.reqcycle.traceability.builder.Activator;
import org.eclipse.reqcycle.traceability.builder.ITraceabilityBuilder.IBuilderCallBack;
import org.eclipse.reqcycle.uri.visitors.CompositeVisitor;
import org.eclipse.reqcycle.uri.visitors.IVisitor;

public class TraceabilityVisitor extends CompositeVisitor implements IAdaptable {

	private IBuilderCallBack callBack;
	private static String EXT_POINT = "traceabilityAnalyser";
	static IExtensionRegistry registry = Platform.getExtensionRegistry();

	public TraceabilityVisitor(IBuilderCallBack callBack) {
		super(getTraceabilityVisitors());
		this.callBack = callBack;
	}

	private static Collection<IVisitor> getTraceabilityVisitors() {
		Collection<IVisitor> tmp = getRegisteredVisitors();
		return tmp;
	}

	private static Collection<IVisitor> getRegisteredVisitors() {
		Collection<IVisitor> result = new ArrayDeque<IVisitor>();
		for (IConfigurationElement e : registry.getConfigurationElementsFor(
				Activator.PLUGIN_ID, EXT_POINT)) {
			try {
				IVisitor v = (IVisitor) e.createExecutableExtension("visitor");
				AgesysInject.inject(v);
				result.add(v);
			} catch (CoreException e1) {
				e1.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public boolean visit(Object o, IAdaptable adapable) {
		// redirect the adapt to this to manage IBuilderCallback
		return super.visit(o, this);
	}

	@Override
	public Object getAdapter(Class adapter) {
		if (IBuilderCallBack.class == adapter) {
			return callBack;
		}
		return null;
	}

}

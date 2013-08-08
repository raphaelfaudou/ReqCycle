package org.eclipse.reqcycle.traceability.builder;

import org.eclipse.reqcycle.uri.visitors.IVisitor;

public interface ITraceabilityAnalyserDisabler {
	boolean isDisabled(Class<? extends IVisitor> visitorClass);
}

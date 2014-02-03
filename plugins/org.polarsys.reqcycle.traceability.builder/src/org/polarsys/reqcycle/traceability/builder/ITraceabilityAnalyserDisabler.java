package org.polarsys.reqcycle.traceability.builder;

import org.polarsys.reqcycle.uri.visitors.IVisitor;

public interface ITraceabilityAnalyserDisabler {
	boolean isDisabled(Class<? extends IVisitor> visitorClass);
}

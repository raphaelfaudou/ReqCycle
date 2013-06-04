package org.eclipse.reqcycle.traceability.test.traca;

import org.agesys.inject.AgesysInject;
import org.eclipse.reqcycle.traceability.cache.jena.JenaTraceabilityEngine;
import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine;
import org.junit.Before;

public class JenaTraceabilityEngineTest extends TraceabilityEngineTest {
	@SuppressWarnings("restriction")
	@Before
	public void registerBinding() {
		AgesysInject.addBinding(ITraceabilityEngine.class).implementedBy(
				JenaTraceabilityEngine.class);
	}

}

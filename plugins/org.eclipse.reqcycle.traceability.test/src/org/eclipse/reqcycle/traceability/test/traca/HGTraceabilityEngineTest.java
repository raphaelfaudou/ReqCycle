package org.eclipse.reqcycle.traceability.test.traca;

import org.agesys.inject.AgesysInject;
import org.eclipse.reqcycle.traceability.cache.hypergraph.HGTraceabilityEngine;
import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine;
import org.junit.Before;

public class HGTraceabilityEngineTest extends TraceabilityEngineTest {
	@SuppressWarnings("restriction")
	@Before
	public void registerBinding() {
		AgesysInject.addBinding(ITraceabilityEngine.class).implementedBy(
				HGTraceabilityEngine.class);
	}
}

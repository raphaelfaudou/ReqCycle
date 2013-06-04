package org.eclipse.reqcycle.traceability.test.traca;

import org.agesys.inject.AgesysInject;
import org.eclipse.reqcycle.traceability.cache.emfbased.CacheTraceabilityEngine;
import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine;
import org.junit.Before;

public class CacheTraceabilityEngineTest extends TraceabilityEngineTest {
	@SuppressWarnings("restriction")
	@Before
	public void registerBinding() {
		AgesysInject.addBinding(ITraceabilityEngine.class).implementedBy(
				CacheTraceabilityEngine.class);
	}
}

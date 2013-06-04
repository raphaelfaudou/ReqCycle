package org.eclipse.reqcycle.traceability.test.builder;

import org.agesys.inject.AgesysInject;
import org.eclipse.reqcycle.traceability.cache.jena.JenaTraceabilityEngine;
import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine;
import org.junit.Before;

public class JenaBuilderTest extends BuilderTest {
	@SuppressWarnings("restriction")
	@Before
	public void registerBinding() {
		AgesysInject.addBinding(ITraceabilityEngine.class).implementedBy(
				JenaTraceabilityEngine.class);
	}
}

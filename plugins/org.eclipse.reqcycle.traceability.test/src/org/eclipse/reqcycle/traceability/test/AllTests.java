package org.eclipse.reqcycle.traceability.test;

import org.eclipse.reqcycle.traceability.test.builder.JenaBuilderTest;
import org.eclipse.reqcycle.traceability.test.traca.CacheTraceabilityEngineTest;
import org.eclipse.reqcycle.traceability.test.traca.HGTraceabilityEngineTest;
import org.eclipse.reqcycle.traceability.test.traca.JenaTraceabilityEngineTest;
import org.eclipse.reqcycle.traceability.test.traca.types.TypedTraceabilityEngineTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ JenaEngineTest.class, CacheTraceabilityEngineTest.class,
		HGTraceabilityEngineTest.class, JenaTraceabilityEngineTest.class,
		JenaBuilderTest.class, TypedTraceabilityEngineTest.class })
public class AllTests {
}

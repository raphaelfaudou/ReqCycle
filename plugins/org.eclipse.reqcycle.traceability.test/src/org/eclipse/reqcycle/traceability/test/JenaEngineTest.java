package org.eclipse.reqcycle.traceability.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.agesys.inject.AgesysInject;
import org.eclipse.reqcycle.traceability.cache.jena.JenaTraceabilityEngine;
import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine;
import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine.DIRECTION;
import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.traceability.model.Pair;
import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.traceability.model.TraceabilityLink;
import org.eclipse.reqcycle.traceability.storage.IStorageProvider;
import org.eclipse.reqcycle.traceability.storage.ITraceabilityStorage;
import org.eclipse.reqcycle.uri.IReachableCreator;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.junit.Before;
import org.junit.Test;

public class JenaEngineTest {
	@SuppressWarnings("restriction")
	@Before
	public void registerBinding() {
		AgesysInject.addBinding(ITraceabilityEngine.class).implementedBy(
				JenaTraceabilityEngine.class);
	}

	@Test
	public void testLoadCreateAndGet() {
		IReachableCreator creator = AgesysInject.make(IReachableCreator.class);
		IStorageProvider engine = AgesysInject.make(IStorageProvider.class);
		ITraceabilityStorage storage = getStorage(engine);
		Reachable source;
		try {
			source = creator.getReachable(new URI("file://test/test"));
			source.put("test", "value");
			Reachable target = creator.getReachable(new URI(
					"platform:/test2/test2"));
			target.put("test2", "value2");
			storage.newUpwardRelationShip(
					TType.custom(TraceabilityLink.TRACE, "test"), source,
					source, target);
			Reachable r1 = storage.getReachable(source.toString());
			assertNotNull(r1);
			assertEquals(r1.get("test"), "value");
			assertNull(storage.getReachable("http://doNotExist"));
		} catch (URISyntaxException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	public ITraceabilityStorage getStorage(IStorageProvider engine) {
		try {
			return engine.getStorage(File.createTempFile("rdfTmp", ".rdf")
					.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Test
	public void testGetTraceabilityOneLevel() {
		IReachableCreator creator = AgesysInject.make(IReachableCreator.class);
		IStorageProvider engine = AgesysInject.make(IStorageProvider.class);
		ITraceabilityStorage storage = getStorage(engine);
		Reachable source;
		try {
			source = creator.getReachable(new URI("file://test/test"));
			source.put("test", "value");
			Reachable target = creator.getReachable(new URI(
					"platform:/test2/test2"));
			target.put("test2", "value2");
			storage.newUpwardRelationShip(
					TType.custom(TraceabilityLink.TRACE, "test"), source,
					source, target);
			Iterable<Pair<Link, Reachable>> traceaUp = storage.getTraceability(
					source, DIRECTION.UPWARD);
			Iterable<Pair<Link, Reachable>> traceaDown = storage
					.getTraceability(target, DIRECTION.DOWNWARD);
			assertNotNull(traceaUp);
			assertNotNull(traceaDown);
			// check UP (and not shake up)
			Pair<Link, Reachable> pairUP = traceaUp.iterator().next();
			assertEquals(pairUP.getFirst().getLabel(), "test");
			assertEquals(pairUP.getSecond(), target);
			assertTrue(pairUP.getFirst().getSources().contains(source));
			assertTrue(pairUP.getFirst().getTargets().contains(target));

			// check down (and not shake down)
			Pair<Link, Reachable> pairDown = traceaDown.iterator().next();
			assertEquals(pairDown.getFirst().getLabel(), "test");
			assertEquals(pairDown.getSecond(), source);
			assertTrue(pairDown.getFirst().getSources().contains(target));
			assertTrue(pairDown.getFirst().getTargets().contains(source));
		} catch (URISyntaxException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
}

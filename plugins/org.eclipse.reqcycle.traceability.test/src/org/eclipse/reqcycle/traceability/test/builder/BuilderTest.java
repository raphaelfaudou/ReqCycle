package org.eclipse.reqcycle.traceability.test.builder;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.agesys.inject.AgesysInject;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.papyrus.sysml.requirements.RequirementsFactory;
import org.eclipse.papyrus.sysml.requirements.Satisfy;
import org.eclipse.reqcycle.traceability.builder.IBuildingTraceabilityEngine;
import org.eclipse.reqcycle.traceability.cache.AbstractCachedTraceabilityEngine;
import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine;
import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine.DIRECTION;
import org.eclipse.reqcycle.traceability.engine.Request;
import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.traceability.model.Pair;
import org.eclipse.reqcycle.traceability.model.scopes.CompositeScope;
import org.eclipse.reqcycle.traceability.test.Activator;
import org.eclipse.reqcycle.traceability.test.CONSTANTS;
import org.eclipse.reqcycle.traceability.test.Utils;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.uml2.uml.Abstraction;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.UMLFactory;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.io.Resources;

public abstract class BuilderTest {
	private static final String TEMPORARY_FOLDER = Activator.getDefault()
			.getStateLocation().toFile().getAbsolutePath()
			+ "\\tmp";
	private static final String TEST2_UML = "test2.uml";
	private static final String TEST2_UML_CLASS1_FRAGMENT = "_zrXQAH9MEeK-erqHFDUaww";
	private static final String TEST2_UML_REQUIREMENT2_FRAGMENT = "_tQx0MIGnEeKBL502FBPduw";
	private static final String TEST2_UML_CLASS1 = TEST2_UML + "#"
			+ TEST2_UML_CLASS1_FRAGMENT;
	private static final String TEST2_UML_REQUIREMENT2 = TEST2_UML + "#"
			+ TEST2_UML_REQUIREMENT2_FRAGMENT;
	private static final String TEST1_UML = "test1.uml";
	private static final String TEST1_UML_REQUIREMENT1_FRAGMENT = "_uj-fcH9MEeK-erqHFDUaww";
	private static final String TEST1_UML_CLASS1_FRAGMENT = TEST2_UML_CLASS1_FRAGMENT;
	private static final String TEST1_UML_CLASS1 = TEST1_UML + "#"
			+ TEST1_UML_CLASS1_FRAGMENT;
	private static final String TEST1_UML_REQUIREMENT1 = TEST1_UML + "#"
			+ TEST1_UML_REQUIREMENT1_FRAGMENT;

	String[] toCopy = new String[] { TEST1_UML, TEST2_UML };

	public void deleteCacheOfTraceability() {
		// check org.eclipse.reqcycle.traceability.cache plugin to see where is
		// located the cache
		File f = new File(org.eclipse.reqcycle.traceability.cache.Activator
				.getDefault().getStateLocation().toString());
		File f2 = new File(f.getAbsolutePath() + "/"
				+ AbstractCachedTraceabilityEngine.HIDDEN_PROJET_NAME + "/");
		f2.mkdirs();
		// try {
		// for (File f3 : f2.listFiles()) {
		// // Files.deleteRecursively(f3);
		// }
		// } catch (IOException e) {
		// }
	}

	public void createTempFiles() {
		String tempDirectory = TEMPORARY_FOLDER;
		File f = new File(tempDirectory);
		f.mkdirs();
		// ensure the directory is empty
		for (File x : f.listFiles()) {
			// try {
			// // Files.deleteRecursively(x);
			// } catch (IOException e) {
			// }
		}
		for (String s : toCopy) {
			createTmpFile(Activator.PLUGIN_ID, CONSTANTS.RESOURCES + s,
					tempDirectory + "\\" + s);
		}
	}

	private void createTmpFile(String pluginId, String path, String outputFile) {
		URL resource = Platform.getBundle(pluginId).getEntry(path);
		BufferedOutputStream bos = null;
		try {
			try {
				bos = new BufferedOutputStream(new FileOutputStream(new File(
						outputFile)));
				Resources.copy(resource, bos);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Test
	public void testNominalCase() {
		try {
			deleteCacheOfTraceability();
			createTempFiles();
			ITraceabilityEngine engine = AgesysInject
					.make(ITraceabilityEngine.class);
			assertTrue(
					"this test is only available for IBuildingTraceabilityEngine",
					engine instanceof IBuildingTraceabilityEngine);
			Reachable sourceReq = Utils.getReachable(getURI(TEST1_UML,
					TEST1_UML_REQUIREMENT1_FRAGMENT));
			Request r = new Request().setDirection(DIRECTION.DOWNWARD)
					.addSource(sourceReq);
			Iterator<Pair<Link, Reachable>> result = engine.getTraceability(r);
			List<Pair<Link, Reachable>> list = Lists.newArrayList(result);
			assertTrue(list.size() == 1);
			assertTrue(list.get(0).getFirst().getSources().contains(sourceReq));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testAfterDeletion() {
		try {
			// cache initialization and same test as nominal
			deleteCacheOfTraceability();
			createTempFiles();
			ITraceabilityEngine engine = AgesysInject
					.make(ITraceabilityEngine.class);
			assertTrue(
					"this test is only available for IBuildingTraceabilityEngine",
					engine instanceof IBuildingTraceabilityEngine);
			Reachable sourceReq = Utils.getReachable(getURI(TEST1_UML,
					TEST1_UML_REQUIREMENT1_FRAGMENT));
			CompositeScope s = new CompositeScope();
			Request r = new Request().setDirection(DIRECTION.DOWNWARD)
					.addSource(sourceReq).setScope(s);
			Iterator<Pair<Link, Reachable>> result = engine.getTraceability(r);
			List<Pair<Link, Reachable>> list = Lists.newArrayList(result);
			assertTrue(list.size() == 1);
			assertTrue(list.get(0).getFirst().getSources().contains(sourceReq));

			// deletion of file traceability does not exist anymore
			new File(TEMPORARY_FOLDER + "\\" + TEST1_UML).delete();

			Iterator<Pair<Link, Reachable>> result2 = engine.getTraceability(r);
			List<Pair<Link, Reachable>> list2 = Lists.newArrayList(result2);
			assertTrue(list2.size() == 0);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testAfterCreationOfElement() {
		try {
			// cache initialization and same test as nominal
			deleteCacheOfTraceability();
			createTempFiles();
			ITraceabilityEngine engine = AgesysInject
					.make(ITraceabilityEngine.class);
			assertTrue(
					"this test is only available for IBuildingTraceabilityEngine",
					engine instanceof IBuildingTraceabilityEngine);
			Reachable sourceReq = Utils.getReachable(getURI(TEST1_UML,
					TEST1_UML_REQUIREMENT1_FRAGMENT));
			Request r = new Request().setDirection(DIRECTION.DOWNWARD)
					.addSource(sourceReq);
			Iterator<Pair<Link, Reachable>> result = engine.getTraceability(r);
			List<Pair<Link, Reachable>> list = Lists.newArrayList(result);
			assertTrue(list.size() == 1);
			assertTrue(list.get(0).getFirst().getSources().contains(sourceReq));

			// modification of test2.uml => new satisfy relation to requirement
			// 1
			ResourceSetImpl resourceSetImpl = new ResourceSetImpl();
			Resource resourceToSav = resourceSetImpl.getResource(
					getURI(TEST1_UML), true);
			org.eclipse.uml2.uml.Class satisfied = (Class) resourceToSav
					.getEObject(TEST1_UML_REQUIREMENT1_FRAGMENT);
			Model m = (Model) resourceToSav.getContents().get(0);
			Class newClass = m.createOwnedClass("newReq", false);
			Abstraction a = UMLFactory.eINSTANCE.createAbstraction();
			m.getPackagedElements().add(a);
			a.getClients().add(newClass);
			a.getSuppliers().add(satisfied);
			Satisfy s = RequirementsFactory.eINSTANCE.createSatisfy();
			s.setBase_Abstraction(a);
			resourceToSav.getContents().add(s);
			resourceToSav.save(Collections.emptyMap());

			// check if new relation has been found
			Request r2 = new Request().setDirection(DIRECTION.DOWNWARD)
					.addSource(sourceReq);
			Iterator<Pair<Link, Reachable>> result2 = engine
					.getTraceability(r2);
			List<Pair<Link, Reachable>> list2 = Lists.newArrayList(result2);
			assertTrue(list2.size() == 2);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	URI getURI(String modelName, String fragment) {
		return getURI(modelName).appendFragment(fragment);
	}

	URI getURI(String modelName) {
		return URI.createFileURI(TEMPORARY_FOLDER + "\\" + modelName);
	}
}

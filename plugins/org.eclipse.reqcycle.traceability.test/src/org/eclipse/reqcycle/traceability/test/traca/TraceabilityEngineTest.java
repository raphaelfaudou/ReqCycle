package org.eclipse.reqcycle.traceability.test.traca;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.List;

import org.agesys.inject.AgesysInject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.reqcycle.emf.utils.EMFUtils;
import org.eclipse.reqcycle.traceability.builder.ITraceabilityBuilder;
import org.eclipse.reqcycle.traceability.builder.ITraceabilityBuilder.IBuilderCallBack;
import org.eclipse.reqcycle.traceability.builder.exceptions.BuilderException;
import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine;
import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine.DIRECTION;
import org.eclipse.reqcycle.traceability.engine.Request;
import org.eclipse.reqcycle.traceability.exceptions.EngineException;
import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.traceability.model.Pair;
import org.eclipse.reqcycle.traceability.model.scopes.ResourceScope;
import org.eclipse.reqcycle.traceability.test.CONSTANTS;
import org.eclipse.reqcycle.traceability.test.Utils;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.uml2.uml.Class;
import org.junit.Test;

import com.google.common.collect.Lists;

public abstract class TraceabilityEngineTest {

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

	/**
	 * This test will check if the satisfy link is found between the requirement
	 * and the class in DOWNARD analysis
	 */
	@Test
	public void testNominalCaseSatisfyBetweenClassAndRequirement() {
		try {
			ITraceabilityEngine engine = AgesysInject
					.make(ITraceabilityEngine.class);

			Reachable sourceReq = Utils.getReachable(URI
					.createPlatformPluginURI(CONSTANTS.RESOURCE_FULL_PATH
							+ TEST1_UML_REQUIREMENT1, false));
			Reachable targetClass = Utils.getReachable(URI
					.createPlatformPluginURI(CONSTANTS.RESOURCE_FULL_PATH
							+ TEST1_UML_CLASS1, false));

			Request r = new Request().setDirection(DIRECTION.DOWNWARD)
					.addSourceAndTarget(sourceReq, targetClass);
			Iterator<Pair<Link, Reachable>> result = engine.getTraceability(r);
			List<Pair<Link, Reachable>> list = Lists.newArrayList(result);
			assertTrue(list.size() == 1);
			assertTrue(list.get(0).getSecond().equals(targetClass));
			assertTrue(list.get(0).getFirst().getSources().contains(sourceReq));
			assertTrue(list.get(0).getFirst().getTargets()
					.contains(targetClass));
		} catch (RuntimeException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (EngineException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * This test will check if the satisfy link is found between the requirement
	 * and the class in UPWARD analysis
	 */
	@Test
	public void testNominalCaseSatisfyBetweenRequirementAndClass() {
		try {
			ITraceabilityEngine engine = AgesysInject
					.make(ITraceabilityEngine.class);
			Reachable sourceReq = Utils.getReachable(URI
					.createPlatformPluginURI(CONSTANTS.RESOURCE_FULL_PATH
							+ TEST1_UML_REQUIREMENT1, false));
			Reachable targetClass = Utils.getReachable(URI
					.createPlatformPluginURI(CONSTANTS.RESOURCE_FULL_PATH
							+ TEST1_UML_CLASS1, false));

			Request r = new Request().setDirection(DIRECTION.UPWARD)
					.addSourceAndTarget(targetClass, sourceReq);
			Iterator<Pair<Link, Reachable>> result = engine.getTraceability(r);
			List<Pair<Link, Reachable>> list = Lists.newArrayList(result);
			assertTrue(list.size() == 1);
			assertTrue(list.get(0).getSecond().equals(sourceReq));
			assertTrue(list.get(0).getFirst().getSources()
					.contains(targetClass));
			assertTrue(list.get(0).getFirst().getTargets().contains(sourceReq));
		} catch (RuntimeException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (EngineException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * This test will check if the satisfy link is found between the requirement
	 * and the class this code is made starting from emf elements in DOWNARD
	 * analysis
	 */
	@Test
	public void testNominalCaseSatisfyBetweenClassAndRequirementUsingEMF() {
		try {
			ITraceabilityEngine engine = AgesysInject
					.make(ITraceabilityEngine.class);
			URI createPlatformPluginURI = URI.createPlatformPluginURI(
					CONSTANTS.RESOURCE_FULL_PATH + TEST1_UML, false);
			ResourceSet set = new ResourceSetImpl();
			Resource r = set.getResource(createPlatformPluginURI, true);
			Class req = (Class) r.getEObject(TEST1_UML_REQUIREMENT1_FRAGMENT);
			Class theClass = (Class) r.getEObject(TEST1_UML_CLASS1_FRAGMENT);
			Request r2 = new Request().setDirection(DIRECTION.DOWNWARD)
					.addSourceAndTarget(EMFUtils.getReachable(req),
							EMFUtils.getReachable(theClass));
			Iterator<Pair<Link, Reachable>> result = engine.getTraceability(r2);
			List<Pair<Link, Reachable>> list = Lists.newArrayList(result);
			assertTrue(list.size() == 1);

			Reachable second = list.get(0).getSecond();
			Reachable first = list.get(0).getFirst().getSources().iterator()
					.next();

			assertEquals(theClass, EMFUtils.getEObject(second, set));
			assertEquals(req, EMFUtils.getEObject(first, set));
		} catch (RuntimeException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (EngineException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * This test will check if the satisfy link is found between the requirement
	 * and the class in DOWNARD analysis
	 */
	@Test
	public void test2StepsCaseSatisfyBetweenClassAndRequirement() {
		try {
			ITraceabilityEngine engine = AgesysInject
					.make(ITraceabilityEngine.class);
			Reachable sourceReq = Utils.getReachable(URI
					.createPlatformPluginURI(CONSTANTS.RESOURCE_FULL_PATH
							+ TEST2_UML_REQUIREMENT2, false));
			Reachable targetClass = Utils.getReachable(URI
					.createPlatformPluginURI(CONSTANTS.RESOURCE_FULL_PATH
							+ TEST2_UML_CLASS1, false));

			Request r2 = new Request().setDirection(DIRECTION.DOWNWARD)
					.addSourceAndTarget(sourceReq, targetClass);
			Iterator<Pair<Link, Reachable>> result = engine.getTraceability(r2);
			// the result path is composed by one link from requirement to
			// requirement
			// and a second one class to requirement
			List<Pair<Link, Reachable>> list = Lists.newArrayList(result);
			assertTrue(list.size() == 2);
			assertTrue(list.get(1).getSecond().equals(targetClass));
		} catch (RuntimeException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (EngineException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * This test will check if no traceability is found from one uml element if
	 * the scope is constrained to the uml file
	 */
	@Test
	public void testForExtendedScopeBetweenUMLAndTopcasedReq() {
		try {
			ITraceabilityEngine engine = AgesysInject
					.make(ITraceabilityEngine.class);
			Reachable sourceReq = Utils
					.getReachable(URI
							.createPlatformPluginURI(
									CONSTANTS.RESOURCE_FULL_PATH
											+ "EquipmentMonitoringPapyrus.uml#_w8IxIM37EdqwVrslYOdUDA",
									false));
			Request r = new Request().setDirection(DIRECTION.UPWARD).addSource(
					sourceReq);
			Iterator<Pair<Link, Reachable>> result = engine.getTraceability(r);
			assertFalse(result.hasNext());
		} catch (RuntimeException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (EngineException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * This test will check if a traceability is found from one uml element if
	 * the scope is constrained to the uml file and requirement file
	 */
	@Test
	public void testForExtendedScopeBetweenUMLAndTopcasedReq2() {
		try {
			ITraceabilityEngine engine = AgesysInject
					.make(ITraceabilityEngine.class);
			Reachable sourceReq = Utils
					.getReachable(URI
							.createPlatformPluginURI(
									CONSTANTS.RESOURCE_FULL_PATH
											+ "EquipmentMonitoringPapyrus.uml#_w8IxIM37EdqwVrslYOdUDA",
									false));
			Reachable requirementFile = Utils.getReachable(URI
					.createPlatformPluginURI(CONSTANTS.RESOURCE_FULL_PATH
							+ "EquipmentMonitoringPapyrus.requirement", false));
			Request r = new Request().setDirection(DIRECTION.UPWARD)
					.addSource(sourceReq)
					.setScope(new ResourceScope(requirementFile));
			Iterator<Pair<Link, Reachable>> result = engine.getTraceability(r);
			assertTrue(result.hasNext());
			// TODO
		} catch (RuntimeException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (EngineException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * This test will check if a traceability is found from one uml element if
	 * the scope is constrained to the uml file and requirement file
	 */
	@Test
	public void testLabelProviderCapabilities() {
		try {

			// force build to make entry storage in db
			ITraceabilityEngine engine = AgesysInject
					.make(ITraceabilityEngine.class);
			ITraceabilityBuilder builder = AgesysInject
					.make(ITraceabilityBuilder.class);
			builder.build(Utils.getReachable(URI.createPlatformPluginURI(
					CONSTANTS.RESOURCE_FULL_PATH
							+ "EquipmentMonitoringPapyrus.uml", false)),
					(IBuilderCallBack) engine, false);

			// for each element in traceability the label and the image
			Reachable sourceReq = Utils.getReachable(URI
					.createPlatformPluginURI(CONSTANTS.RESOURCE_FULL_PATH
							+ TEST2_UML_REQUIREMENT2, false));
			Reachable targetClass = Utils.getReachable(URI
					.createPlatformPluginURI(CONSTANTS.RESOURCE_FULL_PATH
							+ TEST2_UML_CLASS1, false));

			Request r2 = new Request().setDirection(DIRECTION.DOWNWARD)
					.addSourceAndTarget(sourceReq, targetClass);
			Iterator<Pair<Link, Reachable>> result = engine.getTraceability(r2);
			// the result path is composed by one link from requirement to
			// requirement
			// and a second one class to requirement
			List<Pair<Link, Reachable>> list = Lists.newArrayList(result);
			for (Pair<Link, Reachable> p : list) {
				String label1 = getLabel(p.getFirst().getSources().iterator()
						.next());
				assertTrue(label1.length() > 0);
				String label2 = getLabel(p.getSecond());
				assertTrue(label2.length() > 0);
				System.out.println(label1 + " " + label2);
			}

		} catch (BuilderException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (EngineException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	public String getLabel(Reachable r) {
		ILabelProvider p = Utils.getLabelProvider(r);
		if (p != null) {
			return p.getText(r);
		}
		return "";

	}
}

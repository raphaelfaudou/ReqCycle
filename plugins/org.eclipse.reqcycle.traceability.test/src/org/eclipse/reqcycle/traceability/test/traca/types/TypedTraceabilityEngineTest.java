package org.eclipse.reqcycle.traceability.test.traca.types;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.List;

import org.agesys.inject.AgesysInject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine;
import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine.DIRECTION;
import org.eclipse.reqcycle.traceability.engine.Request;
import org.eclipse.reqcycle.traceability.exceptions.EngineException;
import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.traceability.model.Pair;
import org.eclipse.reqcycle.traceability.test.CONSTANTS;
import org.eclipse.reqcycle.traceability.test.Utils;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Configuration;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Relation;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Type;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationFactory;
import org.eclipse.reqcycle.traceability.types.engine.ITypedTraceabilityEngine;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.junit.Test;

import com.google.common.collect.Lists;

public class TypedTraceabilityEngineTest {

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

	// **********************
	// Types
	// **********************
	public static final String ANY_ELEMENT_TYPE = "org.eclipse.reqcycle.types.any";
	public static final String EMF_ELEMENT_TYPE = "org.eclipse.reqcycle.emf.type";
	public static final String JAVA_METHOD_ELEMENT_TYPE = "org.eclipse.reqcycle.jdt.type.method";

	/**
	 * This test will check if the satisfy link is found between the requirement
	 * and the class in DOWNARD analysis
	 */
	@Test
	public void testNominalCaseSatisfyBetweenClassAndRequirement() {
		try {
			ITypedTraceabilityEngine engine = AgesysInject
					.make(ITypedTraceabilityEngine.class);

			Reachable sourceReq = Utils.getReachable(URI
					.createPlatformPluginURI(CONSTANTS.RESOURCE_FULL_PATH
							+ TEST1_UML_REQUIREMENT1, false));
			Reachable targetClass = Utils.getReachable(URI
					.createPlatformPluginURI(CONSTANTS.RESOURCE_FULL_PATH
							+ TEST1_UML_CLASS1, false));
			// the configuration is looking for a relation
			// ANY => Satisfy => ANY
			Configuration confg = getConfig(ANY_ELEMENT_TYPE, ANY_ELEMENT_TYPE,
					"Satisfy");

			Request r = new Request().setDirection(DIRECTION.DOWNWARD)
					.addSourceAndTarget(sourceReq, targetClass);
			Iterator<Pair<Link, Reachable>> result = engine.getTraceability(
					confg, r);
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
	 * the test is the same as
	 * testNominalCaseSatisfyBetweenClassAndRequirementWithMoreAccurateTyping
	 * but in UPWARD so source and targets are inverted. The config is not
	 * changed so the traceability shall be the same
	 */
	@Test
	public void testNominalCaseSatisfyBetweenClassAndRequirementUpward() {
		try {
			ITypedTraceabilityEngine engine = AgesysInject
					.make(ITypedTraceabilityEngine.class);

			Reachable sourceReq = Utils.getReachable(URI
					.createPlatformPluginURI(CONSTANTS.RESOURCE_FULL_PATH
							+ TEST1_UML_REQUIREMENT1, false));
			Reachable targetClass = Utils.getReachable(URI
					.createPlatformPluginURI(CONSTANTS.RESOURCE_FULL_PATH
							+ TEST1_UML_CLASS1, false));
			// the configuration is looking for a relation
			// ANY => Satisfy => ANY
			Configuration confg = getConfig(EMF_ELEMENT_TYPE, EMF_ELEMENT_TYPE,
					"Satisfy");

			Request r = new Request().setDirection(DIRECTION.UPWARD)
					.addSourceAndTarget(targetClass, sourceReq);
			Iterator<Pair<Link, Reachable>> result = engine.getTraceability(
					confg, r);
			List<Pair<Link, Reachable>> list = Lists.newArrayList(result);
			assertTrue(list.size() == 1);
		} catch (RuntimeException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (EngineException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * the test shall verify that there is no "trace" relationship
	 */
	@Test
	public void testWithConfigurationRelationTypeInconrrect() {
		try {
			ITypedTraceabilityEngine engine = AgesysInject
					.make(ITypedTraceabilityEngine.class);

			Reachable sourceReq = Utils.getReachable(URI
					.createPlatformPluginURI(CONSTANTS.RESOURCE_FULL_PATH
							+ TEST1_UML_REQUIREMENT1, false));
			Reachable targetClass = Utils.getReachable(URI
					.createPlatformPluginURI(CONSTANTS.RESOURCE_FULL_PATH
							+ TEST1_UML_CLASS1, false));
			// the configuration is looking for a relation
			// ANY => Satisfy => ANY
			Configuration confg = getConfig(EMF_ELEMENT_TYPE, EMF_ELEMENT_TYPE,
					"Trace");

			Request r = new Request().setDirection(DIRECTION.DOWNWARD)
					.addSourceAndTarget(sourceReq, targetClass);
			Iterator<Pair<Link, Reachable>> result = engine.getTraceability(
					confg, r);
			List<Pair<Link, Reachable>> list = Lists.newArrayList(result);
			assertTrue(list.size() == 0);
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
	public void testNominalCaseSatisfyBetweenClassAndRequirementWithMoreAccurateTyping() {
		try {
			ITypedTraceabilityEngine engine = AgesysInject
					.make(ITypedTraceabilityEngine.class);

			Reachable sourceReq = Utils.getReachable(URI
					.createPlatformPluginURI(CONSTANTS.RESOURCE_FULL_PATH
							+ TEST1_UML_REQUIREMENT1, false));
			Reachable targetClass = Utils.getReachable(URI
					.createPlatformPluginURI(CONSTANTS.RESOURCE_FULL_PATH
							+ TEST1_UML_CLASS1, false));
			// the configuration is looking for a relation
			// EMF => Satisfy => EMF
			Configuration confg = getConfig(EMF_ELEMENT_TYPE, EMF_ELEMENT_TYPE,
					"Satisfy");

			Request r = new Request().setDirection(DIRECTION.DOWNWARD)
					.addSourceAndTarget(sourceReq, targetClass);
			Iterator<Pair<Link, Reachable>> result = engine.getTraceability(
					confg, r);
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
	 * This test will ensure that if the type is not correct nothing is found
	 */
	@Test
	public void testNothingFoundBetweenClassAndRequirement() {
		try {
			ITypedTraceabilityEngine engine = AgesysInject
					.make(ITypedTraceabilityEngine.class);

			Reachable sourceReq = Utils.getReachable(URI
					.createPlatformPluginURI(CONSTANTS.RESOURCE_FULL_PATH
							+ TEST1_UML_REQUIREMENT1, false));
			Reachable targetClass = Utils.getReachable(URI
					.createPlatformPluginURI(CONSTANTS.RESOURCE_FULL_PATH
							+ TEST1_UML_CLASS1, false));

			// the configuration is looking for a relation
			// ANY => Satisfy => JAVA
			Configuration confg = getConfig(ANY_ELEMENT_TYPE,
					JAVA_METHOD_ELEMENT_TYPE, "Satisfy");

			Request r = new Request().setDirection(DIRECTION.DOWNWARD)
					.addSourceAndTarget(sourceReq, targetClass);
			Iterator<Pair<Link, Reachable>> result = engine.getTraceability(
					confg, r);
			List<Pair<Link, Reachable>> list = Lists.newArrayList(result);
			assertTrue(list.size() == 0);
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

	private Configuration getConfig(String typeSource, String typeTarget,
			String string) {
		TypeconfigurationFactory einstance = TypeconfigurationFactory.eINSTANCE;
		Configuration config = einstance.createConfiguration();
		TypeConfigContainer container = einstance.createTypeConfigContainer();
		Type source = einstance.createType();
		source.setTypeId(typeSource);

		Type target = null;
		if (typeTarget.equals(typeSource)) {
			target = source;
		} else {
			target = einstance.createType();
			target.setTypeId(typeTarget);
		}
		Relation rel = einstance.createRelation();
		rel.setKind(string);
		rel.setUpstreamType(source);
		rel.setDownstreamType(target);
		config.getRelations().add(rel);
		container.getConfigurations().add(config);
		container.getTypes().add(source);
		container.getTypes().add(target);
		return config;
	}

	public String getLabel(Reachable r) {
		ILabelProvider p = Utils.getLabelProvider(r);
		if (p != null) {
			return p.getText(r);
		}
		return "";

	}
}

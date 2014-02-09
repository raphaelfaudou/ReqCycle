/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.types.configuration.predicates;

import java.util.Collection;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.polarsys.reqcycle.traceability.model.Link;
import org.polarsys.reqcycle.traceability.types.ITraceabilityAttributesManager;
import org.polarsys.reqcycle.traceability.types.ITraceabilityAttributesManager.EditableAttribute;
import org.polarsys.reqcycle.traceability.types.ITypesConfigurationProvider;
import org.polarsys.reqcycle.traceability.types.RelationUtils;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Attribute;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Configuration;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation;
import org.eclipse.ziggurat.inject.ZigguratInject;

public class ReqCycleDynamicPackage {
	private static final String PREFIX = "rAttributes";

	public static final String URI = "http://www.reqcycle.org/relations/attributes";
	public static final EDataType INTEGER = EcorePackage.Literals.EINT;
	public static final EDataType STRING = EcorePackage.Literals.ESTRING;
	public static final EDataType BOOLEAN = EcorePackage.Literals.EBOOLEAN;

	static ITypesConfigurationProvider provider = ZigguratInject
			.make(ITypesConfigurationProvider.class);
	private static ITraceabilityAttributesManager attributesManager = ZigguratInject
			.make(ITraceabilityAttributesManager.class);

	public static void reinitURIPackage() {
		try {
			EPackage.Registry.INSTANCE.put(URI, getEPackage());
		} catch (EPackageCreationException e) {
			e.printStackTrace();
		}
	}

	public static EPackage getEPackage() throws EPackageCreationException {
		EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
		ePackage.setNsURI(URI);
		ePackage.setNsPrefix(PREFIX);
		ePackage.setName("Relations_Attributes");
		Configuration c = provider.getDefaultConfiguration();
		if (c == null) {
			throw new EPackageCreationException("no default configuration");
		}
		for (Relation r : c.getRelations()) {
			ePackage.getEClassifiers().add(getEClass(ePackage, r));
		}
		if (ePackage.eResource() == null) {
			Resource r = new EcoreResourceFactoryImpl()
					.createResource(org.eclipse.emf.common.util.URI
							.createURI(URI));
			r.getContents().add(ePackage);
		}
		return ePackage;
	}

	private static EClassifier getEClass(EPackage ePackage, Relation r) {
		EClass eclass = EcoreFactory.eINSTANCE.createEClass();
		eclass.setName(r.getKind());
		for (Attribute a : r.getAttributes()) {
			eclass.getEStructuralFeatures().add(getEAttribute(ePackage, a));
		}
		return eclass;
	}

	private static EAttribute getEAttribute(EPackage ePackage, Attribute a) {
		EAttribute att = EcoreFactory.eINSTANCE.createEAttribute();
		att.setName(a.getName());
		att.setEType(getType(ePackage, a));
		return att;
	}

	private static EClassifier getType(EPackage ePackage, Attribute a) {
		if (a.getPossibleValues().isEmpty()) {
			switch (a.getType()) {
			case BOOLEAN:
				return BOOLEAN;
			case INT:
				return INTEGER;
			case STRING:
				return STRING;
			default:
				return STRING;
			}
		} else {
			return getEnum(ePackage, a);
		}
	}

	private static EClassifier getEnum(EPackage ePackage, Attribute a) {
		EEnum e = EcoreFactory.eINSTANCE.createEEnum();
		e.setName(a.getName());
		ePackage.getEClassifiers().add(e);
		int i = 0;
		for (String s : a.getPossibleValues()) {
			EEnumLiteral l = EcoreFactory.eINSTANCE.createEEnumLiteral();
			l.setLiteral(s);
			l.setValue(i);
			e.getELiterals().add(l);
			i++;
		}
		return e;
	}

	public static EObject getEObject(Link link) {
		Configuration defaultConfiguration = provider.getDefaultConfiguration();
		if (defaultConfiguration == null) {
			return null;
		}
		Relation r = RelationUtils.getRelation(link.getKind().getLabel(),
				defaultConfiguration);
		if (r == null) {
			return null;
		}
		try {
			return getEObject(link, r, getEPackage());
		} catch (EPackageCreationException e) {
			return null;
		}
	}

	private static EObject getEObject(Link link, Relation r, EPackage ePackage) {
		for (EClassifier c : ePackage.getEClassifiers()) {
			if (c.getName().equals(r.getKind())) {
				return getEObject(link, r, (EClass) c);
			}
		}
		return null;
	}

	private static EObject getEObject(Link link, Relation r, EClass c) {
		Collection<EditableAttribute> attributes = attributesManager
				.getAttributes(link.getId());
		EObject e = c.getEPackage().getEFactoryInstance().create(c);
		for (EAttribute a : c.getEAllAttributes()) {
			for (EditableAttribute att : attributes) {
				if (a.getName().equals(att.getName())) {
					// WORK
					Object val = getVal(link, a, att);
					e.eSet(a, val);
					break;
				}
			}
		}
		return e;
	}

	private static Object getVal(Link link, EAttribute a, EditableAttribute att) {
		Object val = att.getValue();
		if (att.getPossibleValues() == null
				|| att.getPossibleValues().length == 0) {
			return val;
		} else {
			return getEnumValue(val, a.getEType());
		}
	}

	private static Object getEnumValue(Object val, EClassifier eType) {
		if (val instanceof String) {
			String stringVal = (String) val;
			if (eType instanceof EEnum) {
				EEnum enumeration = (EEnum) eType;
				for (EEnumLiteral l : enumeration.getELiterals()) {
					if (l.getLiteral().equals(stringVal)) {
						return l;
					}
				}
			}
		}
		return null;
	}
}

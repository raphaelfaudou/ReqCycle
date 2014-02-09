/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.types;

import org.polarsys.reqcycle.traceability.model.TType;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation;

public class RelationBasedType extends TType {

	private static final long serialVersionUID = 1L;

	// private static String ATTRIBUTE_PREFIX = "RELATION_BASED_TYPE#";

	public static String RELATION_BASED_NS = Activator.PLUGIN_ID
			+ ".relations.";

	public RelationBasedType(Relation rel) {
		this(rel, null);
	}

	public RelationBasedType(Relation rel, TType superType) {
		super(RELATION_BASED_NS + rel.getKind(), rel.getKind(), superType);
		// loadAttributes(rel);
	}

	// protected void loadAttributes(Relation rel) {
	// for (Attribute a : rel.getAttributes()) {
	// getMetadata().put(getAttributeName(a), null);
	// }
	// }
	//
	// public String getAttributeName(Attribute a) {
	// return ATTRIBUTE_PREFIX + a.getName();
	// }

}

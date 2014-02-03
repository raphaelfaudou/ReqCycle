/*******************************************************************************
 *  * Copyright (c) 2013-2014 AtoS.
 *  * All rights reserved. This program and the accompanying materials
 *  * are made available under the terms of the Eclipse Public License v1.0
 *  * which accompanies this distribution, and is available at
 *  * http://www.eclipse.org/legal/epl-v10.html
 *  *
 *  * Contributors:
 *  *    {INITIAL AUTHOR} - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.reqcycle.traceability.types.lifecycle.providers;

import java.util.Arrays;

import org.eclipse.reqcycle.traceability.types.TAttributeProvider;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.AttributeType;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.RegisteredAttribute;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationFactory;
import org.eclipse.reqcycle.traceability.types.lifecycle.Activator;

public class LifeCycleProvider implements TAttributeProvider {

	private static final String LIFECYCLE_ID = Activator.PLUGIN_ID
			+ ".lifecycle";
	private static RegisteredAttribute[] ATTRIBUTES = new RegisteredAttribute[] { createRA(
			LIFECYCLE_ID, "lifecycle", AttributeType.STRING, new String[] {
					"DEFINED", "VALIDATED", "TO-CHECK" }) };

	@Override
	public Iterable<RegisteredAttribute> getAttributes() {
		return Arrays.asList(ATTRIBUTES);
	}

	private static RegisteredAttribute createRA(String id, String name,
			AttributeType type, String[] values) {
		RegisteredAttribute result = TypeconfigurationFactory.eINSTANCE
				.createRegisteredAttribute();
		result.setId(id);
		result.setName(name);
		result.setType(type);
		result.getPossibleValues().addAll(Arrays.asList(values));
		return result;
	}
}

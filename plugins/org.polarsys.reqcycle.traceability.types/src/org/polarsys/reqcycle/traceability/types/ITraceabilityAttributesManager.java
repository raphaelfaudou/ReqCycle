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

import java.util.Collection;

import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.AttributeType;
import org.polarsys.reqcycle.uri.model.Reachable;

public interface ITraceabilityAttributesManager {

	/**
	 * Returns a {@link Collection} of {@link EditableAttribute} of a
	 * {@link Reachable}. The attributes returned are those defined in the
	 * enabled configuration
	 * 
	 * @param reachable
	 * @return
	 */
	Collection<EditableAttribute> getAttributes(Reachable reachable);

	public interface EditableAttribute {
		String getName();

		Object getValue();

		void setValue(Object value);

		AttributeType getType();

		public Object[] getPossibleValues();
	}
}

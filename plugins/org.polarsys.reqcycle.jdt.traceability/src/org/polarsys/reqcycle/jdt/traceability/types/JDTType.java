/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.jdt.traceability.types;

import org.polarsys.reqcycle.jdt.traceability.Activator;
import org.polarsys.reqcycle.traceability.model.TType;

public class JDTType extends TType {
	String label;

	public JDTType(String label) {
		super(Activator.PLUGIN_ID + "." + label, label + " (Java)");
		this.label = label;
	}
}

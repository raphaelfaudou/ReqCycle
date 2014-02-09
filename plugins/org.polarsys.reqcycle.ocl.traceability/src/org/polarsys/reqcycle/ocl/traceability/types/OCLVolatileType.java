/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Olivier Melois (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.ocl.traceability.types;

import org.polarsys.reqcycle.traceability.model.TType;

/**
 * Traceability type which instances are not necessarily object. The link is found out the execution
 * of an operation from a object "from".
 */
public class OCLVolatileType extends TType {


	private static final long serialVersionUID = 4863675945945242661L;

	private static final String ID_PREFIX = "OCL_VOLATILE"; //$NON-NLS-1$

	private static final String underscore = "_"; //$NON-NLS-1$

	public OCLVolatileType(String id) {
		super(makeId(id), id);
	}

	private static String makeId(String rawId) {
		return ID_PREFIX + underscore + rawId.replaceAll("\\s+", underscore); //$NON-NLS-1$ 
	}

	public String getOperationName(){
		String[] split = this.getLabel().trim().split("\\s+"); //$NON-NLS-1$
		StringBuilder builder = new StringBuilder("trace"); //$NON-NLS-1$
		for (int i = 0; i < split.length ; i++){
			builder.append(Character.toUpperCase(split[i].charAt(0))).append(split[i].substring(1));
		}
		return builder.toString();
	}
	
}

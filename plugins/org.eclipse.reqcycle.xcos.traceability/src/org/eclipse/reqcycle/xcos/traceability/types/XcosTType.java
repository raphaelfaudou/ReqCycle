/*******************************************************************************
 * Copyright (c) 2014 Samares Engineering
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Samares Engineering - initial API and implementation
 ******************************************************************************/
package org.eclipse.reqcycle.xcos.traceability.types;

import org.eclipse.reqcycle.traceability.model.TType;

/**
 * Traceability type which instances are not necessarily object. 
 */
public class XcosTType extends TType {


	private static final long serialVersionUID = 1L;

	private static final String ID_PREFIX = "Xcos"; //$NON-NLS-1$

	private static final String underscore = "_"; //$NON-NLS-1$

	public XcosTType(String label) {
		super(makeId(label), label);
	}

	private static String makeId(String rawId) {
		return ID_PREFIX + underscore + rawId.replaceAll("\\s+", underscore); //$NON-NLS-1$ 
	}


	
}

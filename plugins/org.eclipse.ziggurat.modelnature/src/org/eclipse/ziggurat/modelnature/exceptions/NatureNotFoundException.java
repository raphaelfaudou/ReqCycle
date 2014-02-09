/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Olivier Melois (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.ziggurat.modelnature.exceptions;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ziggurat.modelnature.ModelNaturePlugin;


public class NatureNotFoundException extends CoreException {

	private static final long serialVersionUID = 7701522530140459106L;

	public NatureNotFoundException(String natureID) {
		super(new Status(IStatus.ERROR, ModelNaturePlugin.PLUGIN_ID, "The model nature " + natureID + " does not exist"));
	}

}

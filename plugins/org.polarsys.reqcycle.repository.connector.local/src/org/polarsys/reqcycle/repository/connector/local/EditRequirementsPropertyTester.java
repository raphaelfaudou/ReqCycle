/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Anass Radouani (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.repository.connector.local;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.jface.viewers.IStructuredSelection;

import RequirementSourceConf.RequirementSource;


public class EditRequirementsPropertyTester extends PropertyTester {

	public static final String IS_LOCAL = "isLocal";

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		if(IS_LOCAL.equals(property) && receiver instanceof IStructuredSelection) {
			Object element = ((IStructuredSelection)receiver).getFirstElement();
			if(element instanceof RequirementSource) {
				return LocalConnector.LOCAL_CONNECTOR_ID.equals(((RequirementSource)element).getConnectorId());
			}
		}
		return false;
	}

}

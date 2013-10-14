/*****************************************************************************
 * Copyright (c) 2013 AtoS.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Anass RADOUANI (AtoS) anass.radouani@atos.net - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.reqcycle.repository.connector.local.ui.editor.provider;

import org.eclipse.emf.common.notify.AdapterFactory;

import RequirementSourceData.SimpleRequirement;
import RequirementSourceData.provider.SimpleRequirementItemProvider;


/**
 * The Class CustomRequirementItemProvider.
 */
public class CustomSimpleRequirementItemProvider extends SimpleRequirementItemProvider {

	/**
	 * Instantiates a new custom requirement item provider.
	 * 
	 * @param adapterFactory
	 *        the adapter factory
	 */
	public CustomSimpleRequirementItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public String getText(Object object) {
		String text = "";

		String id = ((SimpleRequirement)object).getId();
		String name = ((SimpleRequirement)object).getName();

		if(id != null && !id.isEmpty()) {
			text += "[ id : " + id;
		}

		if(name != null && !name.isEmpty()) {
			text += text.isEmpty() ? "[ " : " | ";
			text += "name : " + name;
		}

		if(!text.isEmpty()) {
			text += " ]";
		}

		return "Simple Requirement " + text;
	}
}

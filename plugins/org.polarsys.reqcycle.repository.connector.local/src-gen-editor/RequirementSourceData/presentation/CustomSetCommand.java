/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Anass Radouani (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package RequirementSourceData.presentation;

import java.util.Collection;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import RequirementSourceData.Requirement;


public class CustomSetCommand extends SetCommand {

	private Collection<?> result;

	public CustomSetCommand(EditingDomain domain, Collection<?> result, EStructuralFeature feature, Object value) {
		super(domain, null, feature, value);
		this.result = result;
	}

	@Override
	public void doExecute() {
		if(result.size() > 0) {
			Object element = result.iterator().next();
			if(element instanceof Requirement) {
				this.owner = ((Requirement)element);
				super.doExecute();
			}
		}
	}

}

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
package org.eclipse.reqcycle.repository.connector.local.editor;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.reqcycle.repository.connector.local.editor.util.CustomDataModelEditorUtil;

import RequirementSourceData.RequirementSource;
import RequirementSourceData.presentation.RequirementSourceDataActionBarContributor;
import RequirementSourceConf.RequirementSources;


/**
 * The Class RequirementsActionBarContributor.
 */
public class CustomDataModelActionBarContributor extends RequirementSourceDataActionBarContributor {

	@Override
	protected Collection<IAction> generateCreateChildActions(Collection<?> descriptors, ISelection selection) {
		if(CustomDataModelEditorUtil.isInstance(selection, RequirementSources.class)) {
			return Collections.emptyList();
		}
		return super.generateCreateChildActions(descriptors, selection);
	}

	@Override
	protected Collection<IAction> generateCreateSiblingActions(Collection<?> descriptors, ISelection selection) {
		if(CustomDataModelEditorUtil.isInstance(selection, RequirementSources.class) || CustomDataModelEditorUtil.isInstance(selection, RequirementSource.class)) {
			return Collections.emptyList();
		}
		return super.generateCreateSiblingActions(descriptors, selection);
	}

}

/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Anass Radouani (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.repository.data;

import org.polarsys.reqcycle.repository.data.util.EntryUtil;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Entry;
import org.polarsys.reqcycle.traceability.types.ui.IEntryCompositeProvider;
import org.polarsys.reqcycle.types.IType.FieldDescriptor;
import org.polarsys.reqcycle.utils.inject.ZigguratInject;
import org.eclipse.swt.widgets.Composite;


public class RequirementTypeEntryProvider implements IEntryCompositeProvider {

	IDataModelManager dataModelManager = ZigguratInject.make(IDataModelManager.class);

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Entry createEntryComposite(Composite parent, int style, FieldDescriptor fd) {

		return EntryUtil.createComboViewer(parent, fd, dataModelManager.getAllRequirementTypes());
	}

}

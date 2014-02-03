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
 *  Papa Issa DIAKHATE (AtoS) papa-issa.diakhate@atos.net - Initial API and implementation
 *
 *****************************************************************************/
package org.polarsys.reqcycle.predicates.ui.listeners;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.dnd.EditingDomainViewerDropAdapter;
import org.eclipse.jface.viewers.StructuredViewer;
import org.polarsys.reqcycle.predicates.core.api.IPredicate;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;

public class PredicatesTreeViewerDropAdapter extends EditingDomainViewerDropAdapter {

	public PredicatesTreeViewerDropAdapter(EditingDomain editingDomain, StructuredViewer viewer) {
		super(editingDomain, viewer);
	}

	@Override
	public void dragOver(DropTargetEvent event) {
		Object target = determineTarget(event);
		if(target == null || validateDrop(target)) {
			super.dragOver(event);
		} else {
			event.detail = DND.DROP_NONE;
		}
	}

	/**
	 * Returns the target item of the given drop event.
	 * 
	 * @param event
	 *        the event
	 * @return The target of the drop, may be <code>null</code>.
	 */
	protected Object determineTarget(DropTargetEvent event) {
		return event.item == null ? null : event.item.getData();
	}

	protected boolean validateDrop(Object target) {
		return target instanceof IPredicate;
	}

}

/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Papa Issa Diakhate (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.predicates.ui.listeners;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.ui.dnd.ViewerDragAdapter;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.polarsys.reqcycle.predicates.core.api.IPredicate;
import org.eclipse.swt.dnd.DragSourceEvent;


public class CustomPredicatesTreeViewerDragAdapter extends ViewerDragAdapter {

	public CustomPredicatesTreeViewerDragAdapter(Viewer viewer) {
		super(viewer);
	}

	@Override
	public void dragStart(DragSourceEvent event) {
		ISelection s = viewer.getSelection();
		if(s instanceof IStructuredSelection) {
			Object element = ((IStructuredSelection)s).getFirstElement();
			if(element instanceof IPredicate) {
				selection = new StructuredSelection(EcoreUtil.copy((IPredicate)element));

			}
		}

		LocalSelectionTransfer.getTransfer().setSelection(selection);
		LocalSelectionTransfer.getTransfer().setSelectionSetTime(System.currentTimeMillis());
	}

}

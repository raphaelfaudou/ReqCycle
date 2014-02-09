/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Olivier Melois (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.table.handlers;


import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.polarsys.reqcycle.traceability.table.model.TableController;
import org.polarsys.reqcycle.traceability.table.model.TransverseLink;
import org.polarsys.reqcycle.traceability.table.view.TraceabilityTableView;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;


public class DeleteLinkHandler extends AbstractHandler {
	
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
		IWorkbenchPart activePart = HandlerUtil.getActivePart(event);
		if (activePart instanceof TraceabilityTableView){
			TableController controller = ((TraceabilityTableView)activePart).getController();
			Iterator<?> iterator = ((IStructuredSelection)currentSelection).iterator();
			UnmodifiableIterator<TransverseLink> links = Iterators.filter(iterator, TransverseLink.class);
			controller.deleteTraceabilityLinks(links);
		}
		return null;
	}

	@Override
	public boolean isEnabled() {
		ISelection selection = getSelection();
		if (selection instanceof IStructuredSelection){
			Iterator<?> iterator = ((IStructuredSelection)selection).iterator();
			return Iterators.all(iterator, Predicates.instanceOf(TransverseLink.class));
		}
		return false;
	}
	
	@Override
	public boolean isHandled() {
		return true;
	}
	
	public ISelection getSelection(){
		 IWorkbench workbench = PlatformUI.getWorkbench();
		 IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
		 ISelectionService selectionService = workbenchWindow.getSelectionService();
		 return selectionService.getSelection();
	}
}

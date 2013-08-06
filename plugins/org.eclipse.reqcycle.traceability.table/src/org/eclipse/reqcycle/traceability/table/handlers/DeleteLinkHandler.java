/*******************************************************************************
 * Copyright (c) 2012 Original authors and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Original authors and others - initial API and implementation
 ******************************************************************************/
package org.eclipse.reqcycle.traceability.table.handlers;


import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.reqcycle.traceability.storage.IStorageProvider;
import org.eclipse.reqcycle.traceability.table.MutableTraceabilityRow;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ziggurat.inject.ZigguratInject;


public class DeleteLinkHandler extends AbstractHandler {
	
	@Inject
	@Named("RDF")
	protected IStorageProvider provider;
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		if (provider == null)
			ZigguratInject.inject(this);
		if (provider == null)
			return null;

		ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
		if (currentSelection instanceof IStructuredSelection){
			Object firstElement = ((IStructuredSelection)currentSelection).getFirstElement();
			if (firstElement instanceof MutableTraceabilityRow){
				((MutableTraceabilityRow)firstElement).deleteTraceabilityLink();
			}
		}
		return null;
	}

	@Override
	public boolean isEnabled() {
		ISelection selection = getSelection();
		if (selection instanceof IStructuredSelection){
			return ((IStructuredSelection)selection).getFirstElement() instanceof MutableTraceabilityRow;
		}
		return true;
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

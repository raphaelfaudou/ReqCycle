/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.builder.ui.handlers;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class AddBuilderAction implements IObjectActionDelegate {

	private ISelection selection;

	@Override
	public void run(IAction action) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structured = (IStructuredSelection) selection;
			if (structured.getFirstElement() instanceof IProject) {
				IProject p = (IProject) structured.getFirstElement();
				addBuild(p);
			}
		}
	}

	private void addBuild(IProject project) {
		try {
			final String BUILDER_ID = "org.polarsys.reqcycle.traceability.builder";
			IProjectDescription desc = project.getDescription();
			ICommand[] commands = desc.getBuildSpec();
			boolean found = false;

			for (int i = 0; i < commands.length; ++i) {
				if (commands[i].getBuilderName().equals(BUILDER_ID)) {
					found = true;
					break;
				}
			}
			if (!found) {
				// add builder to project
				ICommand command = desc.newCommand();
				command.setBuilderName(BUILDER_ID);
				ICommand[] newCommands = new ICommand[commands.length + 1];

				// Add it after other builders.
				System.arraycopy(commands, 0, newCommands, 0, commands.length);
				newCommands[newCommands.length - 1] = command;
				desc.setBuildSpec(newCommands);
				project.setDescription(desc, null);
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {

	}

}

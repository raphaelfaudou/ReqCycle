package org.eclipse.reqcycle.traceability.builder.ui.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

public class AddBuilderHandler extends AbstractHandler {
	final static String BUILDER_ID = "org.eclipse.reqcycle.traceability.builder";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getActiveMenuSelection(event);
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structured = (IStructuredSelection) selection;
			if (structured.getFirstElement() instanceof IProject) {
				IProject p = (IProject) structured.getFirstElement();
				addBuild(p);
			}
		}
		return null;
	}

	private void addBuild(IProject project) {
		if (!isBuilderInstalled(project)) {
			// add builder to project
			installBuilder(project);
		}
	}

	public static boolean isBuilderInstalled(IProject project) {
		boolean found = false;
		IProjectDescription desc;
		try {
			desc = project.getDescription();
			ICommand[] commands = desc.getBuildSpec();
			for (int i = 0; i < commands.length; ++i) {
				if (commands[i].getBuilderName().equals(BUILDER_ID)) {
					found = true;
					break;
				}
			}
		} catch (CoreException e) {
		}
		return found;
	}

	public static void installBuilder(IProject project) {
		IProjectDescription desc;
		try {
			desc = project.getDescription();
			ICommand[] commands = desc.getBuildSpec();
			ICommand command = desc.newCommand();
			command.setBuilderName(BUILDER_ID);
			ICommand[] newCommands = new ICommand[commands.length + 1];

			// Add it before other builders.
			System.arraycopy(commands, 0, newCommands, 1, commands.length);
			newCommands[0] = command;
			desc.setBuildSpec(newCommands);
			project.setDescription(desc, null);
		} catch (CoreException e) {
		}
	}

	public static void removeBuilder(IProject project) {
		IProjectDescription desc;
		try {
			desc = project.getDescription();
			ICommand[] commands = desc.getBuildSpec();
			ICommand[] newCommands = new ICommand[commands.length - 1];
			for (int i = 0, j = 0; i < commands.length; i++) {
				if (!commands[i].getBuilderName().equals(BUILDER_ID)) {
					newCommands[j] = commands[i];
					j++;
				}
			}
			// Add it before other builders.
			desc.setBuildSpec(newCommands);
			project.setDescription(desc, null);
		} catch (CoreException e) {
		}
	}
}

/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Anass Radouani (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.repository.ui.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.wizard.WizardDialog;
import org.polarsys.reqcycle.repository.ui.wizard.NewRequirementSourceWizard;
import org.polarsys.reqcycle.utils.inject.ZigguratInject;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Action to create a new requirement source
 */
public class AddRequirementSourceAction extends Action {

	/**
	 * Constructor
	 * 
	 */
	public AddRequirementSourceAction() {
	}

	@Override
	public void run() {
		NewRequirementSourceWizard wizard = new NewRequirementSourceWizard();
		ZigguratInject.inject(wizard);
		Shell shell = Display.getDefault().getActiveShell();
		//FIXME : Create a custom wizard dialog
		WizardDialog wd = new WizardDialog(shell, wizard);
		wd.setHelpAvailable(false);
		wd.open();
	}
}

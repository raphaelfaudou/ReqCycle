package org.eclipse.reqcycle.core.ui.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class ReqCyclePreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	public ReqCyclePreferencePage() {
	}

	public ReqCyclePreferencePage(String title) {
		super(title);
	}

	public ReqCyclePreferencePage(String title, ImageDescriptor image) {
		super(title, image);
	}

	@Override
	public void init(IWorkbench workbench) {

	}

	@Override
	protected Control createContents(Composite parent) {
		return new Composite(parent, SWT.NONE);
	}

}

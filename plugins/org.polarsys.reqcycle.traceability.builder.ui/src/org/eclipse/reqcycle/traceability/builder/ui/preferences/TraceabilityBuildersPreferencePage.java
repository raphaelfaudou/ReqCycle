package org.eclipse.reqcycle.traceability.builder.ui.preferences;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ICheckStateProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.reqcycle.traceability.builder.ui.handlers.AddBuilderHandler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

public class TraceabilityBuildersPreferencePage extends PreferencePage
		implements IWorkbenchPreferencePage {

	public TraceabilityBuildersPreferencePage() {
		super();
	}

	@Override
	public void init(IWorkbench workbench) {

	}

	@Override
	protected Control createContents(Composite parent) {
		Composite c = new Composite(parent, SWT.None);
		c.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		c.setLayout(new GridLayout(1, false));

		Label lblTheCheckedProjects = new Label(c, SWT.NONE);
		lblTheCheckedProjects
				.setText("The checked projects will be analyzed by ReqCycle traceability engine");

		CheckboxTableViewer checkboxTableViewer = CheckboxTableViewer
				.newCheckList(c, SWT.BORDER);
		Table table = checkboxTableViewer.getTable();
		checkboxTableViewer.setLabelProvider(new WorkbenchLabelProvider());
		checkboxTableViewer
				.setContentProvider(new BaseWorkbenchContentProvider());
		checkboxTableViewer.setInput(ResourcesPlugin.getWorkspace().getRoot());
		checkboxTableViewer.setCheckStateProvider(new ICheckStateProvider() {

			@Override
			public boolean isGrayed(Object element) {
				return false;
			}

			@Override
			public boolean isChecked(Object element) {
				if (element instanceof IProject) {
					IProject project = (IProject) element;
					return AddBuilderHandler.isBuilderInstalled(project);
				}
				return false;
			}
		});
		checkboxTableViewer.addFilter(new ViewerFilter() {

			@Override
			public boolean select(Viewer viewer, Object parentElement,
					Object element) {
				return element instanceof IProject
						&& ((IProject) element).isAccessible();
			}
		});
		checkboxTableViewer.addCheckStateListener(new ICheckStateListener() {

			@Override
			public void checkStateChanged(CheckStateChangedEvent event) {
				Object element = event.getElement();
				if (element instanceof IProject) {
					IProject project = (IProject) element;
					if (event.getChecked()) {
						AddBuilderHandler.installBuilder(project);
					} else {
						AddBuilderHandler.removeBuilder(project);
					}
				}
			}
		});
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		return parent;
	}

}

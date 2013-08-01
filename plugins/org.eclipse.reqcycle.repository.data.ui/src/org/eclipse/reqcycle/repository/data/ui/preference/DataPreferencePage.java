package org.eclipse.reqcycle.repository.data.ui.preference;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.reqcycle.repository.data.IDataTypeManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ziggurat.inject.ZigguratInject;


public abstract class DataPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

	private IDataTypeManager dataTypeManager = ZigguratInject.make(IDataTypeManager.class);
	
	public DataPreferencePage() {
	}
	
	public DataPreferencePage(String title) {
		super(title);
	}

	public DataPreferencePage(String title, ImageDescriptor image) {
		super(title, image);
	}
	
	@Override
	public void init(IWorkbench workbench) {
		doInit();
	}

	abstract void doInit();

	@Override
	protected void performDefaults() {
		super.performDefaults();
		doInit();
	}
	
	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		Button defaultButton = getDefaultsButton();
		if(defaultButton != null) {
			//Rename Default Button to Load Backup
			defaultButton.setText("Load Backup");
		}
		Button applyButton = getApplyButton();
		if(applyButton != null) {
			//Rename Apply Button to Save 
			applyButton.setText("Save");
		}
	}
	
	@Override
	public boolean performOk() {
		dataTypeManager.saveTypes();
		return super.performOk();
	}
	
	@Override
	public void applyData(Object data) {
		dataTypeManager.saveTypes();
	}

	@Override
	public boolean performCancel() {
		dataTypeManager.discardUnsavedChanges();
		return super.performCancel();
	}
	
}

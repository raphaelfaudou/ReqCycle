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
 *  Anass RADOUANI (AtoS) anass.radouani@atos.net - Initial API and implementation
 *
  *****************************************************************************/
package org.eclipse.reqcycle.repository.data.ui.preference.pages;

import java.util.Collection;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.reqcycle.repository.data.IDataModelManager;
import org.eclipse.reqcycle.repository.data.types.DataModel;
import org.eclipse.reqcycle.repository.data.ui.Activator;
import org.eclipse.reqcycle.repository.data.ui.IDataModelUiManager;
import org.eclipse.reqcycle.repository.data.ui.dialog.NameDialog;
import org.eclipse.reqcycle.repository.data.ui.preference.PreferenceUiUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ziggurat.inject.ZigguratInject;


public class DataModelsPreferencePage extends PreferencePage implements IWorkbenchPreferencePage, Listener {

	//TODO : Use e4 Injection instead of make method
	IDataModelManager dataModelManager = ZigguratInject.make(IDataModelManager.class);
	
	/** Models table viewer */
	protected TableViewer tvModels;
	
	/** Models table */
	protected Table tModels;
	
//	/** Models table viewer column */
//	protected TableViewerColumn tvcModelsNames;
	
//	/** Models table viewer input */
//	protected static Collection<DataTypePackage> inputModels = new ArrayList<DataTypePackage>();
	
	/** Add Model Button */
	protected Button btnAddModel;
	
	/** Edit Model Button */
	protected Button btnEditModel;
	
	protected IDataModelUiManager viewerManager = ZigguratInject.make(IDataModelUiManager.class);

	private Button btnDeleteModel; 
	
	/**
	 * @wbp.parser.constructor
	 */
	public DataModelsPreferencePage() {
	}
	
	public DataModelsPreferencePage(String title) {
		super(title);
	}

	public DataModelsPreferencePage(String title, ImageDescriptor image) {
		super(title, image);
	}
	
	@Override
	protected void performDefaults() {
		super.performDefaults();
		dataModelManager.discardUnsavedChanges();

		viewerManager.clear();
		Collection<DataModel> models = dataModelManager.getAllDataModels();
		viewerManager.addDataModels((DataModel[])models.toArray(new DataModel[models.size()]));
		
		handleEvent(new Event());
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
		dataModelManager.save();
		return super.performOk();
	}
	
	@Override
	public void applyData(Object data) {
		dataModelManager.save();
		viewerManager.notifyListeners(new Event());
	}

	@Override
	public boolean performCancel() {
		dataModelManager.discardUnsavedChanges();
		return super.performCancel();
	}
	

	@Override
	protected Control createContents(Composite parent) {
		SashForm control = new SashForm(parent, SWT.VERTICAL);
		control.setLayout(new GridLayout(1, false));
		control.setLayoutData(new GridData());
		
		//Data type Packages group
		Group packagesGrp = PreferenceUiUtil.createGroup(control, "Data Models");
		createModelsUi(packagesGrp);
		
		doCreateContents(control);
		
		hookListeners();
		return control;
	}

	/**
	 * Override this method to add element UI
	 * @param control
	 */
	public void doCreateContents(Composite control) {
	}

	
	/**
	 * Create Model Viewer UI
	 * @param parent Composite parent
	 */
	protected void createModelsUi(Composite parent) {
		//Table Viewer
		Composite viewerComposite = new Composite(parent, SWT.None);
		viewerComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		TableColumnLayout packagesTVLayout = new TableColumnLayout();
		viewerComposite.setLayout(packagesTVLayout);

//		tvModels = new TableViewer(viewerComposite);
//		tvModels.setContentProvider(ArrayContentProvider.getInstance());
//		tModels = tvModels.getTable();
//		tModels.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
//		tModels.setLinesVisible(true);
//
//		//Columns
//		tvcModelsNames = PreferenceUiUtil.createTableViewerColumn(tvModels, "Name", SWT.None);
//		tvcModelsNames.setLabelProvider(new ColumnLabelProvider() {
//
//			@Override
//			public String getText(Object element) {
//				if(element instanceof DataTypePackage) {
//					return ((DataTypePackage)element).getName();
//				}
//				return super.getText(element);
//			}
//		});
//		packagesTVLayout.setColumnData(tvcModelsNames.getColumn(), new ColumnWeightData(20, 100, true));
//
//		tvModels.setInput(inputModels);
		
		viewerManager.addListener(this);
		tvModels = viewerManager.createDataModelTableViewer(viewerComposite, packagesTVLayout);
		tModels = tvModels.getTable();

		Composite btnComposite = new Composite(parent, SWT.None);
		btnComposite.setLayout(new GridLayout());
		btnComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true));
		
		btnAddModel = PreferenceUiUtil.createButton(btnComposite, "Add Data Model", Activator.getImageDescriptor("/icons/add.gif").createImage());
		
		btnEditModel = PreferenceUiUtil.createButton(btnComposite, "Edit Data Model", Activator.getImageDescriptor("/icons/edit.png").createImage());
		btnEditModel.setEnabled(false);
		
		btnDeleteModel = PreferenceUiUtil.createButton(btnComposite, "Delete Data Model", Activator.getImageDescriptor("/icons/delete.gif").createImage());
		btnDeleteModel.setEnabled(false);
	}
	
	
	/**
	 * Add Listeners 
	 */
	public void hookListeners() {

		btnAddModel.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				NameDialog dialog = new NameDialog(e.display.getActiveShell(), "Add Data Model");
				if(dialog.open() == Window.OK) {
					String name = dialog.getName();
//					inputModels.add(dataModelManager.createDataTypePackage(name));
					viewerManager.addDataModels(dataModelManager.createDataModel(name));
					tvModels.refresh();
				}
			}
		});
	}

	@Override
	public void handleEvent(Event event) {
		if(tvModels != null) {
			tvModels.refresh();
		}
	}

	@Override
	public void dispose() {
		viewerManager.removeListener(this);
		super.dispose();
	}

	@Override
	public void init(IWorkbench workbench) {
		
	}
	
}

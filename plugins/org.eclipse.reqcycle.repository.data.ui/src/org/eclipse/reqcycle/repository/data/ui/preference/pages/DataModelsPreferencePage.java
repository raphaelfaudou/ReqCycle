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

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.reqcycle.repository.data.IDataModelManager;
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

	/** Add Model Button */
	protected Button btnAddModel;

	/** Edit Model Button */
	protected Button btnEditModel;

	protected IDataModelUiManager viewerManager = ZigguratInject.make(IDataModelUiManager.class);

	private Button btnDeleteModel;

	//	private Button btnImportModel;

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
	}


	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		Button defaultButton = getDefaultsButton();
		//Disable default button
		if(defaultButton != null) {
			defaultButton.setVisible(false);
			defaultButton.setEnabled(false);
		}
	}

	@Override
	public boolean performOk() {
		dataModelManager.save();
		return super.performOk();
	}

	//	@Override
	//	public void applyData(Object data) {
	//		dataModelManager.save();
	//		viewerManager.notifyListeners(new Event());
	//	}

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
	 * 
	 * @param control
	 */
	public void doCreateContents(Composite control) {
	}


	/**
	 * Create Model Viewer UI
	 * 
	 * @param parent
	 *        Composite parent
	 */
	protected void createModelsUi(Composite parent) {
		//Table Viewer
		Composite viewerComposite = new Composite(parent, SWT.None);
		viewerComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		TableColumnLayout packagesTVLayout = new TableColumnLayout();
		viewerComposite.setLayout(packagesTVLayout);

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

		//		btnImportModel = PreferenceUiUtil.createButton(btnComposite, "Import Data Model", Activator.getImageDescriptor("/icons/edit.png").createImage());
		//		btnImportModel.setEnabled(true);
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
					viewerManager.addDataModels(dataModelManager.createDataModel(name));
					tvModels.refresh();
				}
			}
		});


		//		btnImportModel.addSelectionListener(new SelectionAdapter() {
		//
		//			@Override
		//			public void widgetSelected(SelectionEvent e) {
		//				ResourceDialog dialog = new ResourceDialog(getShell(), "Select Data Model file", SWT.NONE);
		//				int res = dialog.open();
		//				if(res == ResourceDialog.OK) {
		//					List<URI> uris = dialog.getURIs();
		//					if(!uris.isEmpty() && uris.size() > 0) {
		//						URI uri = uris.get(0);
		//
		//						Collection<IDataModel> models = dataModelManager.getDataModel(uri);
		//
		//						Collection<IDataModel> conflictingDataModels = Collections2.filter(models, new Predicate<IDataModel>() {
		//
		//							@Override
		//							public boolean apply(IDataModel arg0) {
		//								if(dataModelManager.getDataModel(arg0.getName()) != null) {
		//									return true;
		//								}
		//								return false;
		//							}
		//						});
		//
		//						Collection<IDataModel> importableDataModels = Collections2.filter(models, new Predicate<IDataModel>() {
		//
		//							@Override
		//							public boolean apply(IDataModel arg0) {
		//								if(dataModelManager.getDataModel(arg0.getName()) == null) {
		//									return true;
		//								}
		//								return false;
		//							}
		//						});
		//
		//						String errorMessage = "";
		//
		//						if(!conflictingDataModels.isEmpty()) {
		//							errorMessage = "The following Data Models already exists :\n";
		//							for(IDataModel iDataModel : conflictingDataModels) {
		//								errorMessage += "- " + iDataModel.getName() + "\n";
		//							}
		//							errorMessage += "\n";
		//						}
		//
		//						String importMessage = "Import :\n";
		//
		//						if(!importableDataModels.isEmpty()) {
		//							for(IDataModel iDataModel : importableDataModels) {
		//								importMessage += "- " + iDataModel.getName() + "\n";
		//							}
		//						} else {
		//							importMessage += "None\n";
		//						}
		//
		//
		//						String message = errorMessage + importMessage + "\n" + "Would you like to continue ?";
		//
		//						if(MessageDialog.openQuestion(Display.getDefault().getActiveShell(), "Import Data Models", message)) {
		//							for(IDataModel iDataModel : importableDataModels) {
		//								dataModelManager.addDataModel(iDataModel);
		//								viewerManager.addDataModels(iDataModel);
		//							}
		//							tvModels.refresh();
		//						}
		//
		//					}
		//				}
		//			}
		//
		//		});

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

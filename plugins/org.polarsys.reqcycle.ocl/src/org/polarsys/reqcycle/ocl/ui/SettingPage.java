/*******************************************************************************
 * * Copyright (c) 2013,2014 AtoS and others
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Olivier Melois (AtoS) - initial API and implementation and/or initial documentation
 * * Raphael Faudou (Samares Engineering) - Fixed some bugs in OCL connector to manage types and align
 * * connector on other connectors with a destination file
 *******************************************************************************/
package org.polarsys.reqcycle.ocl.ui;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.ui.dialogs.WorkspaceResourceDialog;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.polarsys.reqcycle.emf.utils.EMFUtils;
import org.polarsys.reqcycle.ocl.ReqcycleOCLPlugin;
import org.polarsys.reqcycle.ocl.ui.OCLConnector.SettingBean;
import org.polarsys.reqcycle.repository.data.IDataModelManager;
import org.polarsys.reqcycle.repository.data.types.IDataModel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ziggurat.inject.ZigguratInject;

import ScopeConf.Scope;

import com.google.common.collect.Lists;

public class SettingPage extends WizardPage {

	private IDataModelManager dataManager = ZigguratInject.make(IDataModelManager.class);

	private Button browseButton;

	private Text tFile;

	private ComboViewer cvDataModel;

	private ComboViewer cvScope;

	private Combo cScope;

	private Combo cDataModel;

	private List<Scope> inputScope = Lists.newArrayList();
	
	private Text txtDestination;

	private Button btnBrowseDestination;

	private SettingBean bean;

	protected SettingPage(SettingBean bean) {
		super("OCL Connector settings");
		this.bean = bean;
		setDescription("Connector settings");
	}

	@Override
	public void createControl(Composite parent) {
		Composite containerComposite = new Composite(parent, SWT.None);
		containerComposite.setLayout(new GridLayout(3, false));
		Label fileLabel = new Label(containerComposite, SWT.NONE);
		fileLabel.setText("EMF Model :");

		tFile = new Text(containerComposite, SWT.BORDER | SWT.READ_ONLY);
		tFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		tFile.setEnabled(false);
		browseButton = new Button(containerComposite, SWT.NONE);
		browseButton.setText("Browse");

		Label lblSeparator = new Label(containerComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
		lblSeparator.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1));

		SashForm sashForm = new SashForm(containerComposite, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1));

		Label lblDataModel = new Label(containerComposite, SWT.NONE);
		lblDataModel.setText("Data Model :");

		cvDataModel = new ComboViewer(containerComposite);
		cDataModel = cvDataModel.getCombo();
		cDataModel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		cvDataModel.setContentProvider(ArrayContentProvider.getInstance());
		cvDataModel.setLabelProvider(new LabelProvider() {

			@Override
			public String getText(Object element) {
				if(element instanceof IDataModel) {
					return ((IDataModel)element).getName();
				}
				return super.getText(element);
			}
		});
		cvDataModel.setInput(dataManager.getAllDataModels());

		Label lblScope = new Label(containerComposite, SWT.NONE);
		lblScope.setText("Scope :");

		cvScope = new ComboViewer(containerComposite);
		cScope = cvScope.getCombo();
		cScope.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		cScope.setEnabled(false);
		cvScope.setContentProvider(ArrayContentProvider.getInstance());
		cvScope.setLabelProvider(new LabelProvider() {

			@Override
			public String getText(Object element) {
				if(element instanceof Scope) {
					return ((Scope)element).getName();
				}
				return super.getText(element);
			}
		});
		cvScope.setInput(inputScope);
		
		// RFa - add detination file to store the source
		Label lblDestination = new Label(containerComposite, SWT.NONE);
		lblDestination.setText("Destination File :");

		txtDestination = new Text(containerComposite, SWT.BORDER);
		txtDestination.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		btnBrowseDestination = new Button(containerComposite, SWT.PUSH);
		btnBrowseDestination.setText("Browse destination file");
		btnBrowseDestination.setLayoutData(new GridData());

		hookListeners();
		initDataBindings();
		setControl(containerComposite);
	}

	@Override
	public boolean isPageComplete() {
		StringBuffer error = new StringBuffer();
		boolean result = true;
		
		if (bean.getUri().isEmpty()) {
			error.append("Choose an EMF model \n");
			result = false;
		}
		else {
			String uriString = bean.getUri();
		
			if(uriString != null && !uriString.isEmpty()) {
				URI uri = URI.createPlatformResourceURI(uriString, true);
				if(!EMFUtils.isEMF(uri, false)) {
					error.append("Selected file is not an EMF resource");
					result = false;
				}
			}
		} 


		if(bean.getDataPackage() == null) {
			error.append("Choose a Data Model\n");
			result = false;
		}

		if(bean.getScope() == null) {
			error.append("Choose a Scope\n");
			result = false;
		}

		if(bean.getDestination() == null || bean.getDestination().isEmpty()) {
			error.append("Choose a destination file for a Copy Import Mode");
			result = false;
		}
		

		if(!result) {
			setErrorMessage(error.toString());
		} else {
			setErrorMessage(null);
		}

		return result;
		
	}


	/**
	 * Label provider for the workspace resource dialog.
	 */
	protected static ILabelProvider labelProvider = new WorkbenchLabelProvider() {

		@Override
		public Color getForeground(Object element) {
			return Display.getDefault().getSystemColor(SWT.COLOR_BLACK);
		}

		@Override
		public Color getBackground(Object element) {
			return Display.getDefault().getSystemColor(SWT.COLOR_WHITE);
		}
	};

	protected static ViewerFilter filter = new ViewerFilter() {

		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			return true;
		}
	};

	protected static ISelectionStatusValidator validator = new ISelectionStatusValidator() {

		@Override
		public IStatus validate(Object[] selection) {
			if(selection.length == 1) {
				Object o = selection[0];
				
				// RFa we do not want to restrict to UML files
				//if(o instanceof IFile && "uml".equals(((IFile)o).getFileExtension())) {
				if (o instanceof IFile) {
					URI uri = URI.createPlatformResourceURI(((IFile) o).getFullPath().toOSString(), true);
					System.out.println("uri: "+ uri);
					if(EMFUtils.isEMF(uri,false)) {
						return Status.OK_STATUS;
					}
				}
			}
			return new Status(IStatus.ERROR, ReqcycleOCLPlugin.PLUGIN_ID, "Select a single EMF file");
		}
	};

	protected void hookListeners() {

		cvDataModel.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				inputScope.clear();
				cScope.setEnabled(false);
				ISelection selection = event.getSelection();
				if(selection instanceof IStructuredSelection) {
					Object obj = ((IStructuredSelection)selection).getFirstElement();
					if(obj instanceof IDataModel) {
						cScope.setEnabled(true);
						Collection<Scope> scopes = dataManager.getScopes((IDataModel)obj);
						inputScope.addAll(scopes);
					}

				}
				cvScope.refresh();
			}
		});

		browseButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				BaseWorkbenchContentProvider contentProvider = new BaseWorkbenchContentProvider();
				WorkspaceResourceDialog dialog = new WorkspaceResourceDialog(Display.getCurrent().getActiveShell(), labelProvider, contentProvider);
				dialog.addFilter(filter);
				dialog.setAllowMultiple(false);
				dialog.setInput(ResourcesPlugin.getWorkspace().getRoot());
				dialog.setValidator(validator);
				int open = dialog.open();
				if(open == 0) {
					IFile iFile = dialog.getSelectedFiles()[0];
					String location = iFile.getFullPath().toOSString();
					tFile.setText(location);
				}
			}
		});
		
		// destination file
		btnBrowseDestination.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				SaveAsDialog dialog = new SaveAsDialog(getShell());
				if(Window.OK == dialog.open()) {
					IPath result = dialog.getResult();
					if(!"reqcycle".equals(result.getFileExtension())) {
						result = result.addFileExtension("reqcycle");
					}
					txtDestination.setText(result.toString());
				}
			}
		});
	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextFileURITextObserveWidget = WidgetProperties.text(SWT.Modify).observe(tFile);
		IObservableValue uriBeanObserveValue = PojoProperties.value("uri").observe(bean);
		bindingContext.bindValue(observeTextFileURITextObserveWidget, uriBeanObserveValue, null, null);
		//
		IObservableValue observeSingleSelectionCvDataModel = ViewerProperties.singleSelection().observe(cvDataModel);
		IObservableValue dataPackageBeanObserveValue = PojoProperties.value("dataPackage").observe(bean);
		bindingContext.bindValue(observeSingleSelectionCvDataModel, dataPackageBeanObserveValue, null, null);
		//
		IObservableValue observeSingleSelectionCvScope = ViewerProperties.singleSelection().observe(cvScope);
		IObservableValue scopeBeanObserveValue = PojoProperties.value("scope").observe(bean);
		bindingContext.bindValue(observeSingleSelectionCvScope, scopeBeanObserveValue, null, null);
	
		// destination file
		IObservableValue observeTextTxtDestinationObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtDestination);
		IObservableValue destinationBeanObserveValue = PojoProperties.value("destination").observe(bean);
		bindingContext.bindValue(observeTextTxtDestinationObserveWidget, destinationBeanObserveValue, null, null);

		return bindingContext;
	}


}

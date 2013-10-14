package org.eclipse.reqcycle.repository.ui.dialogs;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.team.svn.core.resource.IRepositoryLocation;
import org.eclipse.team.svn.core.svnstorage.SVNRemoteStorage;


public class SynchronizeSourceDialog extends Dialog {

	@SuppressWarnings("unused")
	private DataBindingContext m_bindingContext;

	private Button btnSpecificTrace;

	private Group groupTraceability;

	private Composite compoTraceability;

	private Combo comboTraceability;

	private Combo comboRepository;

	private ComboViewer comboViewerTraceability;

	private ComboViewer comboViewerRepository;

	public Bean bean = new Bean();

	static class RepositoriesLabelProvider extends LabelProvider {

		@Override
		public String getText(Object element) {
			if(element instanceof IRepositoryLocation) {
				return ((IRepositoryLocation)element).getLabel();
			}
			return super.getText(element);
		}

	}

	public static class Bean {

		private IRepositoryLocation requirementLocation;

		private IRepositoryLocation traceabilityLocation;

		public IRepositoryLocation getRequirementLocation() {
			return requirementLocation;
		}

		public void setRequirementLocation(IRepositoryLocation repository) {
			this.requirementLocation = repository;
		}

		public IRepositoryLocation getTraceabilityLocation() {
			return traceabilityLocation;
		}

		public void setTraceabilityLocation(IRepositoryLocation traceabilityRepository) {
			this.traceabilityLocation = traceabilityRepository;
		}

	}

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public SynchronizeSourceDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.DIALOG_TRIM);
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {

		IRepositoryLocation[] locations = SVNRemoteStorage.instance().getRepositoryLocations();

		//Create Control
		Composite control = new Composite(parent, SWT.NONE);
		control.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		control.setLayout(new GridLayout(2, false));

		//Create Repository Combo
		Label lblRepository = new Label(control, SWT.NONE);
		lblRepository.setText("Repository : ");

		comboViewerRepository = new ComboViewer(control);
		comboRepository = comboViewerRepository.getCombo();
		comboRepository.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		comboViewerRepository.setLabelProvider(new RepositoriesLabelProvider());
		comboViewerRepository.setContentProvider(ArrayContentProvider.getInstance());
		comboViewerRepository.setInput(locations);


		groupTraceability = new Group(control, SWT.NONE);
		groupTraceability.setTouchEnabled(true);
		groupTraceability.setLayout(new GridLayout(1, false));
		groupTraceability.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
		groupTraceability.setText("Advanced");


		btnSpecificTrace = new Button(groupTraceability, SWT.CHECK);
		btnSpecificTrace.setText("Use differente repository for Traceability");

		compoTraceability = new Composite(groupTraceability, SWT.NONE);
		compoTraceability.setLayout(new GridLayout(2, false));
		compoTraceability.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		groupTraceability.redraw();

		Label lblTraceability = new Label(compoTraceability, SWT.NONE);
		lblTraceability.setText("Traceability : ");

		comboViewerTraceability = new ComboViewer(compoTraceability);
		comboTraceability = comboViewerTraceability.getCombo();
		comboTraceability.setLayout(new GridLayout());
		comboTraceability.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		comboViewerTraceability.setContentProvider(ArrayContentProvider.getInstance());
		comboViewerTraceability.setLabelProvider(new RepositoriesLabelProvider());
		comboViewerTraceability.setInput(locations);
		comboTraceability.setEnabled(false);

		hookListeners();

		return control;
	}

	protected void hookListeners() {
		btnSpecificTrace.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean selection = btnSpecificTrace.getSelection();
				comboTraceability.setEnabled(selection);
				comboViewerTraceability.setSelection(selection ? StructuredSelection.EMPTY : comboViewerRepository.getSelection());
			}
		});

		comboRepository.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				ISelection selection = comboViewerRepository.getSelection();
				if(!btnSpecificTrace.getSelection()) {
					comboViewerTraceability.setSelection(selection);
				}
			}
		});

	}

	@Override
	protected void okPressed() {
		if(comboRepository != null && comboRepository.getSelectionIndex() == -1) {
			MessageDialog.openError(getShell(), "Synchronize Error", "Please Select a Repository to synchronize with.");
			return;
		}

		if(btnSpecificTrace != null && btnSpecificTrace.getSelection() && comboTraceability.getSelectionIndex() == -1) {
			MessageDialog.openError(getShell(), "Synchronize Error", "Please Select a Repository for Traceability or uncheck traceability specific option button");
			return;
		}
		super.okPressed();
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
		m_bindingContext = initDataBindings();
	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeSingleSelectionComboViewerRepository = ViewerProperties.singleSelection().observe(comboViewerRepository);
		IObservableValue repositoryBeanObserveValue = PojoProperties.value("requirementLocation").observe(bean);
		bindingContext.bindValue(observeSingleSelectionComboViewerRepository, repositoryBeanObserveValue, null, null);
		//
		IObservableValue observeSingleSelectionComboViewerTraceability = ViewerProperties.singleSelection().observe(comboViewerTraceability);
		IObservableValue traceabilityRepositoryBeanObserveValue = PojoProperties.value("traceabilityLocation").observe(bean);
		bindingContext.bindValue(observeSingleSelectionComboViewerTraceability, traceabilityRepositoryBeanObserveValue, null, null);
		//
		return bindingContext;
	}

	public Bean getBean() {
		return bean;
	}
}

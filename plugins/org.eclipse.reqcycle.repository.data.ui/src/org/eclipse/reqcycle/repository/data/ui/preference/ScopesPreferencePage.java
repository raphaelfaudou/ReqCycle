package org.eclipse.reqcycle.repository.data.ui.preference;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.window.Window;
import org.eclipse.reqcycle.repository.data.IDataTypeManager;
import org.eclipse.reqcycle.repository.data.types.DataTypePackage;
import org.eclipse.reqcycle.repository.data.ui.Activator;
import org.eclipse.reqcycle.repository.data.ui.dialog.NameDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ziggurat.inject.ZigguratInject;

import DataModel.Scope;


public class ScopesPreferencePage extends DataPreferencePage {

	private IDataTypeManager dataTypeManager = ZigguratInject.make(IDataTypeManager.class);
	
	private TableViewer packagesTV;
	private TableViewer scopesTV;
	
	private Table packagesTable;
	private Table scopesTable;

	private TableViewerColumn packagesNameCol;
	private TableViewerColumn scopesNamesCol;

	private Button addPackageBtn;
	private Button addScopeBtn;
	
	private DataTypePackage selectedPackage;

	private Collection<Scope> scopesInput = new ArrayList<Scope>();
	private Collection<DataTypePackage> packagesInput = new ArrayList<DataTypePackage>();
	
	@Override
	void doInit() {
		dataTypeManager.discardUnsavedChanges();
		
		if(packagesInput != null) {
			packagesInput.clear();
			packagesInput.addAll(dataTypeManager.getAllDataTypePackages());
		}
		
		if (packagesTV != null) {
			packagesTV.refresh();
		}
		
		if(scopesInput != null) {
			scopesInput.clear();
		}
		
		if(scopesTV != null) {
			scopesTV.refresh();
		}
	}
	@Override
	protected Control createContents(Composite parent) {
		Composite control = new Composite(parent, SWT.None);
		control.setLayout(new GridLayout(1, false));

		Group packagesGrp = PreferenceUtil.createGroup(control, "Data Type Packages");
		createPackagesUi(packagesGrp);

		Group scopesGrp = PreferenceUtil.createGroup(control, "Scopes");
		createScopesUi(scopesGrp);
		
		hookListeners();
		doInit();

		return control;
	}

	private void hookListeners() {
		packagesTV.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				addScopeBtn.setEnabled(false);
				scopesInput.clear();
				
				ISelection selection = event.getSelection();
				if(selection instanceof IStructuredSelection) {
					Object obj = ((IStructuredSelection)selection).getFirstElement();
					if(obj instanceof DataTypePackage) {
						selectedPackage = (DataTypePackage)obj;
						addScopeBtn.setEnabled(true);
						scopesInput.addAll(selectedPackage.getScopes());
					}
					
				}
				
				scopesTV.refresh();
			}
		});
		
		
		addPackageBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				NameDialog dialog = new NameDialog(e.display.getActiveShell(), null);
				if(dialog.open() == Window.OK) {
					String name = dialog.getName();
					packagesInput.add(dataTypeManager.createDataTypePackage(name));
					packagesTV.setInput(packagesInput);
					packagesTV.refresh();
				}
			}
		});
		
		addScopeBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				NameDialog dialog = new NameDialog(e.display.getActiveShell(), null);
				if(dialog.open() == Window.OK) {
					String name = dialog.getName();
					Scope scope = dataTypeManager.createScope(name);
					dataTypeManager.addScope(selectedPackage, scope);
					scopesInput.add(scope);
					scopesTV.setInput(scopesInput);
					scopesTV.refresh();
				}
			}
		});
		
	}

	private void createPackagesUi(Composite parent) {
		//Table Viewer
		Composite viewerComposite = new Composite(parent, SWT.None);
		viewerComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		TableColumnLayout dataTypeTVLayout = new TableColumnLayout();
		viewerComposite.setLayout(dataTypeTVLayout);

		packagesTV = new TableViewer(viewerComposite);
		packagesTV.setContentProvider(ArrayContentProvider.getInstance());
		packagesTable = packagesTV.getTable();
		packagesTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		packagesTable.setLinesVisible(true);

		//Columns
		packagesNameCol = PreferenceUtil.createTableViewerColumn(packagesTV, "Name", 100, 0, SWT.None);
		packagesNameCol.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				if(element instanceof DataTypePackage) {
					return ((DataTypePackage)element).getName();
				}
				return super.getText(element);
			}
		});
		dataTypeTVLayout.setColumnData(packagesNameCol.getColumn(), new ColumnWeightData(20, 100, true));

		packagesTV.setInput(packagesInput);
		
		addPackageBtn = new Button(parent, SWT.PUSH);
		addPackageBtn.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
		addPackageBtn.setToolTipText("Add Data Type Package");
		addPackageBtn.setImage(Activator.getImageDescriptor("/icons/add.gif").createImage());
	}
	
	private void createScopesUi(Group parent) {
		//Table Viewer
		Composite viewerComposite = new Composite(parent, SWT.None);
		viewerComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		TableColumnLayout dataTypeTVLayout = new TableColumnLayout();
		viewerComposite.setLayout(dataTypeTVLayout);

		scopesTV = new TableViewer(viewerComposite);
		scopesTV.setContentProvider(ArrayContentProvider.getInstance());
		scopesTable = scopesTV.getTable();
		scopesTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		scopesTable.setLinesVisible(true);

		//Columns
		scopesNamesCol = PreferenceUtil.createTableViewerColumn(scopesTV, "Name", 100, 0, SWT.None);
		scopesNamesCol.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				if(element instanceof Scope) {
					return ((Scope)element).getName();
				}
				return super.getText(element);
			}
		});
		dataTypeTVLayout.setColumnData(scopesNamesCol.getColumn(), new ColumnWeightData(20, 100, true));
		
		scopesTV.setInput(scopesInput);

		addScopeBtn = new Button(parent, SWT.PUSH);
		addScopeBtn.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
		addScopeBtn.setToolTipText("Add Scope");
		addScopeBtn.setImage(Activator.getImageDescriptor("/icons/add.gif").createImage());
		addScopeBtn.setEnabled(false);
	}

}

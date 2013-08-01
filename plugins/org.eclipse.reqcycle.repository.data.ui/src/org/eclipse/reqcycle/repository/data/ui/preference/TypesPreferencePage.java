package org.eclipse.reqcycle.repository.data.ui.preference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EcorePackage;
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
import org.eclipse.reqcycle.repository.data.types.DataType;
import org.eclipse.reqcycle.repository.data.types.DataTypeAttribute;
import org.eclipse.reqcycle.repository.data.types.DataTypePackage;
import org.eclipse.reqcycle.repository.data.types.EnumerationType;
import org.eclipse.reqcycle.repository.data.types.EnumeratorType;
import org.eclipse.reqcycle.repository.data.types.RequirementType;
import org.eclipse.reqcycle.repository.data.types.RequirementTypeAttribute;
import org.eclipse.reqcycle.repository.data.ui.dialog.AddAttributeDialog;
import org.eclipse.reqcycle.repository.data.ui.dialog.AddElementDialog;
import org.eclipse.reqcycle.repository.data.ui.dialog.NameDialog;
import org.eclipse.reqcycle.repository.data.util.DataUtil;
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

import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;


public class TypesPreferencePage extends DataPreferencePage {

	private IDataTypeManager dataTypeManager = ZigguratInject.make(IDataTypeManager.class);

	private TableViewer packagesTV;

	private TableViewer typesTV;

	private TableViewer attributesTV;

	private Table packagesTable;

	private Table typesTable;

	private Table attributesTable;

	private TableViewerColumn packagesNamesCol;

	private TableViewerColumn typesNamesCol;

	private TableViewerColumn typesTypesCol;

	private TableViewerColumn attributesNamesCol;

	private TableViewerColumn attributesTypesCol;

	private Button addPackageBtn;

	private Button addTypeBtn;

	private Button addAttributeBtn;

	private Collection<DataTypePackage> packagesInput = new ArrayList<DataTypePackage>();

	private Collection<DataType> typesInput = new ArrayList<DataType>();

	private Collection<DataTypeAttribute> attributesInput = new ArrayList<DataTypeAttribute>();


	protected DataTypePackage selectedPackage;

	protected DataType selectedType;

	@Override
	void doInit() {
		dataTypeManager.discardUnsavedChanges();

		packagesInput.clear();
		packagesInput.addAll(dataTypeManager.getAllDataTypePackages());
		if(packagesInput != null && packagesTV != null) {
			packagesTV.refresh();
		}

		typesInput.clear();
		if(typesTV != null) {
			typesTV.refresh();
		}

		attributesInput.clear();
		if(attributesTV != null) {
			attributesTV.refresh();
		}
	}

	@Override
	protected Control createContents(Composite parent) {

		Composite control = new Composite(parent, SWT.None);
		control.setLayout(new GridLayout(1, false));

		//Data type Packages group
		Group packagesGrp = PreferenceUtil.createGroup(control, "Data Type Packages");
		createPackagesUi(packagesGrp);

		//Data Type group
		Group typeGrp = PreferenceUtil.createGroup(control, "Data Types");
		createTypesUi(typeGrp);

		//Attribute group
		Group attributesGrp = PreferenceUtil.createGroup(control, "Attributes");
		createAttribuesUi(attributesGrp);

		hookListeners();
		doInit();
		return control;
	}

	private void createPackagesUi(Composite parent) {
		//Table Viewer
		Composite viewerComposite = new Composite(parent, SWT.None);
		viewerComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		TableColumnLayout packagesTVLayout = new TableColumnLayout();
		viewerComposite.setLayout(packagesTVLayout);

		packagesTV = new TableViewer(viewerComposite);
		packagesTV.setContentProvider(ArrayContentProvider.getInstance());
		packagesTable = packagesTV.getTable();
		packagesTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		packagesTable.setLinesVisible(true);

		//Columns
		packagesNamesCol = PreferenceUtil.createTableViewerColumn(packagesTV, "Name", 100, 0, SWT.None);
		packagesNamesCol.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				if(element instanceof DataTypePackage) {
					return ((DataTypePackage)element).getName();
				}
				return super.getText(element);
			}
		});
		packagesTVLayout.setColumnData(packagesNamesCol.getColumn(), new ColumnWeightData(20, 100, true));

		packagesTV.setInput(packagesInput);

		addPackageBtn = PreferenceUtil.createAddButton(parent, "Add Data Type Package");
	}

	private void createAttribuesUi(Composite parent) {
		//Table Viewer
		Composite viewerComposite = new Composite(parent, SWT.NONE);
		viewerComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		TableColumnLayout attributeTVLayout = new TableColumnLayout();
		viewerComposite.setLayout(attributeTVLayout);

		attributesTV = new TableViewer(viewerComposite);
		attributesTV.setContentProvider(ArrayContentProvider.getInstance());
		attributesTable = attributesTV.getTable();
		attributesTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		attributesTable.setHeaderVisible(true);
		attributesTable.setLinesVisible(true);

		//Columns
		attributesNamesCol = PreferenceUtil.createTableViewerColumn(attributesTV, "Name", 100, 0, SWT.None);
		attributesNamesCol.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				if(element instanceof RequirementTypeAttribute) {
					return ((RequirementTypeAttribute)element).getName();
				} else if(element instanceof EnumeratorType) {
					return ((EnumeratorType)element).getName();
				}
				return super.getText(element);
			}
		});
		attributeTVLayout.setColumnData(attributesNamesCol.getColumn(), new ColumnWeightData(20, 100, true));

		attributesTypesCol = PreferenceUtil.createTableViewerColumn(attributesTV, "Value Type", 100, 0, SWT.None);
		attributesTypesCol.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				if(element instanceof RequirementTypeAttribute) {
					EDataType type = ((RequirementTypeAttribute)element).getType();
					String name = type.getName();
					String nsURI = type.getEPackage().getNsURI();
					if(EcorePackage.eNS_URI.equals(nsURI) && name.startsWith("E")) {
						name = name.replaceFirst("E", "");
					}
					return name;
				}
				if(element instanceof EnumeratorType) {
					return "-";
				}
				return super.getText(element);
			}
		});
		attributeTVLayout.setColumnData(attributesTypesCol.getColumn(), new ColumnWeightData(20, 100, true));

		attributesTV.setInput(attributesInput);

		addAttributeBtn = PreferenceUtil.createAddButton(parent, "Add Attribute");
		addAttributeBtn.setEnabled(false);
	}

	private void createTypesUi(Composite parent) {
		//Table viewer
		Composite viewerComposite = new Composite(parent, SWT.NONE);
		viewerComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		TableColumnLayout typesTVLayout = new TableColumnLayout();
		viewerComposite.setLayout(typesTVLayout);

		typesTV = new TableViewer(viewerComposite, SWT.BORDER);
		typesTable = typesTV.getTable();
		typesTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		typesTable.setHeaderVisible(true);
		typesTable.setLinesVisible(true);
		typesTV.setContentProvider(ArrayContentProvider.getInstance());

		//Columns
		typesNamesCol = PreferenceUtil.createTableViewerColumn(typesTV, "Name", 100, 0, SWT.None);
		typesNamesCol.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				if(element instanceof DataType) {
					return ((DataType)element).getName();
				}
				return super.getText(element);
			}
		});
		typesTVLayout.setColumnData(typesNamesCol.getColumn(), new ColumnWeightData(20, 100, true));

		typesTypesCol = PreferenceUtil.createTableViewerColumn(typesTV, "Type", 200, 0, SWT.None);
		typesTypesCol.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				if(element instanceof EnumerationType) {
					return "Enumeration";
				} else {
					return "Requirement";
				}
			}
		});
		typesTVLayout.setColumnData(typesTypesCol.getColumn(), new ColumnWeightData(20, 200, true));

		typesTV.setInput(typesInput);

		addTypeBtn = PreferenceUtil.createAddButton(parent, "Add Data Type");
		addTypeBtn.setEnabled(false);
	}

	private void hookListeners() {

		packagesTV.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				addTypeBtn.setEnabled(false);
				addAttributeBtn.setEnabled(false);
				typesInput.clear();
				attributesInput.clear();

				ISelection selection = event.getSelection();
				if(selection instanceof IStructuredSelection) {
					Object obj = ((IStructuredSelection)selection).getFirstElement();
					if(obj instanceof DataTypePackage) {
						selectedPackage = (DataTypePackage)obj;
						addTypeBtn.setEnabled(true);
						typesInput.addAll(dataTypeManager.getDataTypes(selectedPackage));
						typesInput.addAll(dataTypeManager.getEnumerationTypes(selectedPackage));
					}
				}
				typesTV.refresh();
				attributesTV.refresh();
			}
		});

		typesTV.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				addAttributeBtn.setEnabled(false);
				attributesInput.clear();

				ISelection selection = event.getSelection();
				if(selection instanceof IStructuredSelection) {
					Object obj = ((IStructuredSelection)selection).getFirstElement();
					if(obj instanceof DataType) {
						selectedType = (DataType)obj;
						attributesInput.addAll(selectedType.getAttributes());
						addAttributeBtn.setEnabled(true);
					}
				}
				attributesTV.refresh();
			}
		});

		addPackageBtn.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				NameDialog dialog = new NameDialog(e.display.getActiveShell());
				if(dialog.open() == Window.OK) {
					String name = dialog.getName();
					packagesInput.add(dataTypeManager.createDataTypePackage(name));
					packagesTV.refresh();
				}
			}
		});

		addTypeBtn.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				AddElementDialog dialog = new AddElementDialog(e.display.getActiveShell());
				if(dialog.open() == Window.OK) {
					String name = dialog.getName();
					boolean isReq = dialog.isRequirement();
					DataType element;

					if(isReq) {
						element = dataTypeManager.createRequirementType(name);
					} else {
						element = dataTypeManager.createEnumerationType(name);
					}

					dataTypeManager.addDataType(selectedPackage, element);

					typesInput.add(element);
					typesTV.refresh();

				}
			}
		});

		addAttributeBtn.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				DataTypeAttribute attribute = null;

				if(selectedType instanceof EnumerationType) {

					NameDialog dialog = new NameDialog(e.display.getActiveShell());
					if(dialog.open() == Window.OK) {
						attribute = dataTypeManager.createEnumeratorType(dialog.getName());
						((EnumerationType)selectedType).addEnumeratorType((EnumeratorType)attribute);
					}

				} else if(selectedType instanceof RequirementType) {

					Set<Object> values = new HashSet<Object>();
					values.addAll(DataUtil.eDataTypes);
					values.addAll(dataTypeManager.getEnumerationTypes(selectedPackage));
					Collection<Object> types = Collections2.filter(values, Predicates.notNull());

					AddAttributeDialog dialog = new AddAttributeDialog(e.display.getActiveShell(), types);

					if(Window.OK == dialog.open()) {

						String name = dialog.getName();
						EDataType type = dialog.getType();

						attribute = dataTypeManager.createAttributeType(name, type);
						((RequirementType)selectedType).addAttributeType((RequirementTypeAttribute)attribute);
					}
				}
				if(attribute != null) {
					attributesInput.add(attribute);
					attributesTV.refresh();
				}

			}

		});
	}

}

package org.eclipse.reqcycle.repository.data.ui.preference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
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
import org.eclipse.reqcycle.repository.data.AttributeType;
import org.eclipse.reqcycle.repository.data.RequirementType;
import org.eclipse.reqcycle.repository.data.DataTypePackage;
import org.eclipse.reqcycle.repository.data.EnumerationType;
import org.eclipse.reqcycle.repository.data.EnumeratorType;
import org.eclipse.reqcycle.repository.data.IDataTypeManager;
import org.eclipse.reqcycle.repository.data.DataType;
import org.eclipse.reqcycle.repository.data.ui.Activator;
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
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ziggurat.inject.ZigguratInject;

import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;


public class TypePreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

	private TableViewer elementTableViewer;

	private Table elementTable;

	private TableViewerColumn elementNameCol;

	private TableViewerColumn elementTypeCol;

	private IDataTypeManager dataTypeManager = ZigguratInject.make(IDataTypeManager.class);

	private TableViewer attrTableViewer;

	private Table attrTable;

	private TableViewerColumn attrNameCol;

	private TableViewerColumn attrTypeCol;

	private Button addElementBtn;

	private Button addAttrBtn;

	private ArrayList<DataType> input = new ArrayList<DataType>();

	private Collection<DataTypePackage> dataTypeInput = new ArrayList<DataTypePackage>();

	protected Object selectedElmt;

	private TableViewer dataTypeTV;

	private Table dataTypeTable;

	private TableViewerColumn dataTypeNameCol;

	private Button addDataTypeBtn;

	protected DataTypePackage selectedDataType;

	/**
	 * @wbp.parser.constructor
	 */
	public TypePreferencePage() {
	}

	public TypePreferencePage(String title) {
		super(title);
	}

	public TypePreferencePage(String title, ImageDescriptor image) {
		super(title, image);
	}

	@Override
	public void init(IWorkbench workbench) {
		performDefaults();
	}

	@Override
	protected void performDefaults() {
		super.performDefaults();
		dataTypeManager.undoUnsavedChanges();
		init();
	}

	private void init() {
		if(input != null) {
			input.clear();
		}
		if(dataTypeInput != null && dataTypeTV != null) {
			dataTypeInput.clear();
			dataTypeInput.addAll(dataTypeManager.getAllDataTypePackages());
			dataTypeTV.setInput(dataTypeInput);
			dataTypeTV.refresh();
		}
		if(elementTableViewer != null) {
			elementTableViewer.setInput(Collections.emptyList());
			elementTableViewer.refresh();
		}
		if(attrTableViewer != null) {
			attrTableViewer.setInput(Collections.emptyList());
			attrTableViewer.refresh();
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
		dataTypeManager.undoUnsavedChanges();
		return super.performCancel();
	}

	@Override
	protected Control createContents(Composite parent) {

		Composite control = new Composite(parent, SWT.None);
		control.setLayout(new GridLayout(1, false));

		//Data type group
		Group dataTypeGrp = createGroup(control, "DataType");
		createDataTypPackageUi(dataTypeGrp);

		//Elements' group
		Group elementGrp = createGroup(control, "Element");
		createElementUi(elementGrp);

		//Attributes' group
		Group attrGrp = createGroup(control, "Attributes");
		createAttrUi(attrGrp);

		hookListeners();
		init();
		return control;
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


	private void createDataTypPackageUi(Composite parent) {
		//Table Viewer
		Composite viewerComposite = new Composite(parent, SWT.None);
		viewerComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		TableColumnLayout dataTypeTVLayout = new TableColumnLayout();
		viewerComposite.setLayout(dataTypeTVLayout);

		dataTypeTV = new TableViewer(viewerComposite);
		dataTypeTable = dataTypeTV.getTable();
		dataTypeTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		dataTypeTable.setHeaderVisible(true);
		dataTypeTable.setLinesVisible(true);
		dataTypeTV.setContentProvider(ArrayContentProvider.getInstance());

		//Columns
		dataTypeNameCol = createTableViewerColumn(dataTypeTV, "Name", 100, 0, SWT.None);
		dataTypeNameCol.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				if(element instanceof DataTypePackage) {
					return ((DataTypePackage)element).getName();
				}
				return super.getText(element);
			}
		});
		dataTypeTVLayout.setColumnData(dataTypeNameCol.getColumn(), new ColumnWeightData(20, 100, true));

		addDataTypeBtn = new Button(parent, SWT.PUSH);
		addDataTypeBtn.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
		addDataTypeBtn.setToolTipText("Add DataType");
		addDataTypeBtn.setImage(Activator.getImageDescriptor("/icons/add.gif").createImage());
	}

	private void createAttrUi(Composite parent) {
		//Table Viewer
		Composite viewerComposite = new Composite(parent, SWT.NONE);
		viewerComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		TableColumnLayout attrTVLayout = new TableColumnLayout();
		viewerComposite.setLayout(attrTVLayout);

		attrTableViewer = new TableViewer(viewerComposite);
		attrTable = attrTableViewer.getTable();
		attrTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		attrTable.setHeaderVisible(true);
		attrTable.setLinesVisible(true);
		attrTableViewer.setContentProvider(ArrayContentProvider.getInstance());

		//Columns
		attrNameCol = createTableViewerColumn(attrTableViewer, "Name", 100, 0, SWT.None);
		attrNameCol.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				if(element instanceof AttributeType) {
					return ((AttributeType)element).getName();
				} else if(element instanceof EnumeratorType) {
					return ((EnumeratorType)element).getName();
				}
				return super.getText(element);
			}
		});
		attrTVLayout.setColumnData(attrNameCol.getColumn(), new ColumnWeightData(20, 100, true));

		attrTypeCol = createTableViewerColumn(attrTableViewer, "Value Type", 100, 0, SWT.None);
		attrTypeCol.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				if(element instanceof AttributeType) {
					return ((AttributeType)element).getType().getName();
				}
				if(element instanceof EnumeratorType) {
					return "-";// ((EEnumLiteral)element).getLiteral();
				}
				return super.getText(element);
			}
		});
		attrTVLayout.setColumnData(attrTypeCol.getColumn(), new ColumnWeightData(20, 100, true));


		addAttrBtn = new Button(parent, SWT.PUSH);
		addAttrBtn.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
		addAttrBtn.setToolTipText("Add Type");
		addAttrBtn.setImage(Activator.getImageDescriptor("/icons/add.gif").createImage());
		addAttrBtn.setEnabled(false);
	}

	private void createElementUi(Composite parent) {
		//Table viewer
		Composite viewerComposite = new Composite(parent, SWT.NONE);
		TableColumnLayout elementTVLayout = new TableColumnLayout();
		viewerComposite.setLayout(new TableColumnLayout());
		viewerComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		elementTableViewer = new TableViewer(viewerComposite, SWT.BORDER);
		elementTable = elementTableViewer.getTable();
		elementTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		elementTable.setHeaderVisible(true);
		elementTable.setLinesVisible(true);
		elementTableViewer.setContentProvider(ArrayContentProvider.getInstance());

		//Columns
		elementNameCol = createTableViewerColumn(elementTableViewer, "Name", 100, 0, SWT.None);
		elementNameCol.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				if(element instanceof DataType) {
					return ((DataType)element).getName();
				}
				return super.getText(element);
			}
		});
		elementTVLayout.setColumnData(elementNameCol.getColumn(), new ColumnWeightData(20, 100, true));

		elementTypeCol = createTableViewerColumn(elementTableViewer, "Type", 200, 0, SWT.None);
		elementTypeCol.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				if(element instanceof EnumerationType) {
					return "Enumeration";
				} else {
					return "Requirement";
				}
			}
		});
		elementTVLayout.setColumnData(elementTypeCol.getColumn(), new ColumnWeightData(20, 200, true));

		addElementBtn = new Button(parent, SWT.PUSH);
		addElementBtn.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
		addElementBtn.setToolTipText("Add Type");
		addElementBtn.setImage(Activator.getImageDescriptor("/icons/add.gif").createImage());
		addElementBtn.setEnabled(false);
	}

	private Group createGroup(Composite parent, String name) {
		Group grpElements = new Group(parent, SWT.NONE);
		grpElements.setLayout(new GridLayout(2, false));
		grpElements.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpElements.setText(name);
		return grpElements;
	}

	private TableViewerColumn createTableViewerColumn(TableViewer viewer, String title, int bound, int colNumber, int style) {
		TableViewerColumn viewerColumn = new TableViewerColumn(viewer, style);
		TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;
	}

	private void hookListeners() {

		dataTypeTV.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				ISelection selection = event.getSelection();
				addElementBtn.setEnabled(false);
				elementTableViewer.setInput(Collections.emptyList());
				attrTableViewer.setInput(Collections.emptyList());
				if(selection instanceof IStructuredSelection) {
					Object obj = ((IStructuredSelection)selection).getFirstElement();
					if(obj instanceof DataTypePackage) {
						selectedDataType = (DataTypePackage)obj;
						input.clear();
						addElementBtn.setEnabled(true);
						input.addAll(dataTypeManager.getDataTypes(selectedDataType));
						input.addAll(dataTypeManager.getEnumerationTypes(selectedDataType));
						elementTableViewer.setInput(input);
					}
					elementTableViewer.refresh();
				}
			}
		});

		elementTableViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				ISelection selection = event.getSelection();
				addAttrBtn.setEnabled(!selection.isEmpty());
				if(selection instanceof IStructuredSelection) {
					selectedElmt = ((IStructuredSelection)selection).getFirstElement();
					if(selectedElmt instanceof RequirementType) {
						attrTableViewer.setInput(((RequirementType)selectedElmt).getAttributeTypes());
					}
					if(selectedElmt instanceof EnumerationType) {
						attrTableViewer.setInput(((EnumerationType)selectedElmt).getEnumerators());
					}
					attrTableViewer.refresh();
				}
			}
		});

		addDataTypeBtn.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				NameDialog dialog = new NameDialog(e.display.getActiveShell(), null);
				if(dialog.open() == Window.OK) {
					String name = dialog.getName();
					dataTypeInput.add(dataTypeManager.createDataTypePackage(name));
					dataTypeTV.setInput(dataTypeInput);
					dataTypeTV.refresh();
				}
			}
		});

		addElementBtn.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				AddElementDialog dialog = new AddElementDialog(e.display.getActiveShell());
				if(dialog.open() == Window.OK) {
					String name = dialog.getName();
					boolean isReq = dialog.isRequirement();
					DataType element;

					if(isReq) {
						element = dataTypeManager.createDataType(name);
						dataTypeManager.addDataType(selectedDataType, (RequirementType)element);
					} else {
						element = dataTypeManager.createEnumerationType(name);
						dataTypeManager.addEnumerationType(selectedDataType, (EnumerationType)element);
					}

					input.add(element);
					elementTableViewer.refresh();

				}
			}
		});

		addAttrBtn.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				NameDialog dialog = null;
				if(selectedElmt instanceof EnumerationType) {
					dialog = new NameDialog(e.display.getActiveShell(), null);
					if(dialog.open() == Window.OK) {
						EnumeratorType enumerator = dataTypeManager.createEnumeratorType(dialog.getName());
						((EnumerationType)selectedElmt).addEnumeratorType(enumerator);
						attrTableViewer.refresh();
					}
				} else if(selectedElmt instanceof RequirementType) {

					Set<Object> values = new HashSet<Object>();
					values.addAll(DataUtil.eDataTypes);
					values.addAll(dataTypeManager.getEnumerationTypes(selectedDataType));
					Collection<Object> types = Collections2.filter(values, Predicates.notNull());

					dialog = new AddAttributeDialog(e.display.getActiveShell(), types);

					if(Window.OK == dialog.open()) {

						String name = dialog.getName();
						EDataType type = ((AddAttributeDialog)dialog).getType();

						AttributeType attr = dataTypeManager.createAttributeType(name, type);

						((RequirementType)selectedElmt).addAttributeType(attr);

						attrTableViewer.setInput(((RequirementType)selectedElmt).getAttributeTypes());
						attrTableViewer.refresh();
					}
				}
			}

		});
	}

}

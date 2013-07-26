package org.eclipse.reqcycle.repository.data.ui.preference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EcoreFactory;
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
import org.eclipse.reqcycle.repository.data.IDataTypeManager;
import org.eclipse.reqcycle.repository.data.ui.Activator;
import org.eclipse.reqcycle.repository.data.ui.dialog.AddAttributeDialog;
import org.eclipse.reqcycle.repository.data.ui.dialog.AddElementDialog;
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

import DataModel.DataModelPackage;

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
	private ArrayList<EClassifier> input = new ArrayList<EClassifier>();
	protected Object selectedElmt;

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
	protected void performDefaults() {
		dataTypeManager.loadTypes();
		if(elementTableViewer != null) {
			input.clear();
			input.addAll(dataTypeManager.getTypes());
			input.addAll(dataTypeManager.getEEnums());
			elementTableViewer.refresh();
			attrTableViewer.setInput(Collections.emptyList());
			attrTableViewer.refresh();
		}
		super.performDefaults();
	}
	
	@Override
	public void init(IWorkbench workbench) {
		performDefaults();
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
		dataTypeManager.loadTypes();
		return super.performCancel();
	}
	
	@Override
	protected Control createContents(Composite parent) {
		
		Composite control = new Composite(parent, SWT.None);
		control.setLayout(new GridLayout(2, false));
		
		//Elements' group
		Group elementGrp = createGroup(control, "Element");
		
		createElementUi(elementGrp);
		
		//Attributes' group
		Group attrGrp = createGroup(control, "Attributes");
		
		createAttrUi(attrGrp);
		
		hookListeners();
		
		return control;
	}

	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		Button defaultButton = getDefaultsButton();
		if(defaultButton != null) {
			defaultButton.setText("Load Backup");
		}
		Button applyButton = getApplyButton();
		if(applyButton != null) {
			applyButton.setText("Save");
		}
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
		attrTVLayout.setColumnData(attrNameCol.getColumn(), new ColumnWeightData(20, 100, true));
		attrNameCol.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				if(element instanceof EAttribute) {
					return ((EAttribute)element).getName();
				}
				if(element instanceof EEnumLiteral) {
					return ((EEnumLiteral)element).getName();
				}
				return super.getText(element);
			}
		});

		attrTypeCol = createTableViewerColumn(attrTableViewer, "Value Type", 100, 0, SWT.None);
		attrTypeCol.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				if(element instanceof EAttribute) {
					String name = ((EAttribute)element).getEAttributeType().getName();
					String nsURI = ((EAttribute)element).getEAttributeType().getEPackage().getNsURI();
					if(EcoreFactory.eINSTANCE.getEPackage().getNsURI().equals(nsURI) && name.startsWith("E")) {
						name = name.replaceFirst("E", "");
					}
					return name;
				}
				if(element instanceof EEnumLiteral) {
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
				return ((EClassifier)element).getName();
			}
		});
		elementTVLayout.setColumnData(elementNameCol.getColumn(), new ColumnWeightData(20, 100, true));

		elementTypeCol = createTableViewerColumn(elementTableViewer, "Type", 200, 0, SWT.None);
		elementTypeCol.setLabelProvider(new ColumnLabelProvider() {
			
			@Override
			public String getText(Object element) {

				if(element instanceof EEnum) {
					return "Enumeration";
				} else {
					return "Requirement";
				}
			}
		});
		elementTVLayout.setColumnData(elementTypeCol.getColumn(), new ColumnWeightData(20, 200, true));

		input.addAll(dataTypeManager.getTypes());
		input.addAll(dataTypeManager.getEEnums());
		elementTableViewer.setInput(input);
		
		addElementBtn = new Button(parent, SWT.PUSH);
		addElementBtn.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
		addElementBtn.setToolTipText("Add Type");
		addElementBtn.setImage(Activator.getImageDescriptor("/icons/add.gif").createImage());
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
		
		elementTableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				ISelection selection = event.getSelection();
				addAttrBtn.setEnabled(!selection.isEmpty());
				if(selection instanceof IStructuredSelection) {
					selectedElmt = ((IStructuredSelection)selection).getFirstElement();
					if(selectedElmt instanceof EClass) {
						attrTableViewer.setInput(((EClass)selectedElmt).getEAllAttributes());
					}
					if(selectedElmt instanceof EEnum) {
						attrTableViewer.setInput(((EEnum)selectedElmt).getELiterals());
					}
					attrTableViewer.refresh();
				}
			}
		});
		
		addElementBtn.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddElementDialog dialog = new AddElementDialog(e.display.getActiveShell());
				if(dialog.open() == Window.OK) {
					String name = dialog.getName();
					boolean isReq= dialog.isRequirement();
					EClassifier element;
					
					if(isReq) {
						element = EcoreFactory.eINSTANCE.createEClass();
						((EClass)element).getESuperTypes().add(DataModelPackage.Literals.REQUIREMENT_SECTION);
					}
					else {
						element = EcoreFactory.eINSTANCE.createEEnum();
					}
					
					element.setName(name);
					dataTypeManager.addType(element);
					input.add(element);
					elementTableViewer.refresh();
					
				}
			}
		});
		
		addAttrBtn.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddAttributeDialog dialog = null;
				if(selectedElmt instanceof EEnum) {
					dialog = new AddAttributeDialog(e.display.getActiveShell(), true, null);
					if (dialog.open() == Window.OK){
						String name = dialog.getName();
						EEnumLiteral enumLiteral = EcoreFactory.eINSTANCE.createEEnumLiteral();
						enumLiteral.setName(name);
						((EEnum)selectedElmt).getELiterals().add(enumLiteral);
						attrTableViewer.refresh();
					}
				} else if (selectedElmt instanceof EClass) {
					Set<Object> values = new HashSet<Object>();
					values.addAll(DataUtil.eDataTypes);
					values.addAll(dataTypeManager.getEEnums());
					Collection<Object> types = Collections2.filter(values, Predicates.notNull());
					
					dialog = new AddAttributeDialog(e.display.getActiveShell(), false, types);
					
					if(Window.OK == dialog.open()) {
						
						String name = dialog.getName();
						EDataType type = dialog.getType();
						
						EAttribute eAttr = EcoreFactory.eINSTANCE.createEAttribute();
						eAttr.setName(name);
						eAttr.setEType(type);
						
						((EClass)selectedElmt).getEStructuralFeatures().add(eAttr);
						attrTableViewer.setInput(((EClass)selectedElmt).getEAllAttributes());
						attrTableViewer.refresh();
					} 
				}
			}

		});
	}
	
}

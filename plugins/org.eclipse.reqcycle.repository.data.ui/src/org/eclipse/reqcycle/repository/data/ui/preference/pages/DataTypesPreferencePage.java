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
import org.eclipse.reqcycle.repository.data.types.DataType;
import org.eclipse.reqcycle.repository.data.types.DataTypeAttribute;
import org.eclipse.reqcycle.repository.data.types.DataModel;
import org.eclipse.reqcycle.repository.data.types.EnumerationType;
import org.eclipse.reqcycle.repository.data.types.EnumeratorType;
import org.eclipse.reqcycle.repository.data.types.RequirementType;
import org.eclipse.reqcycle.repository.data.types.RequirementTypeAttribute;
import org.eclipse.reqcycle.repository.data.ui.dialog.AddAttributeDialog;
import org.eclipse.reqcycle.repository.data.ui.dialog.AddTypeDialog;
import org.eclipse.reqcycle.repository.data.ui.dialog.NameDialog;
import org.eclipse.reqcycle.repository.data.ui.preference.PreferenceUiUtil;
import org.eclipse.reqcycle.repository.data.util.DataUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;

public class DataTypesPreferencePage extends DataModelsPreferencePage {

	/** Types Viewer Elements */ 
	protected TableViewer tvTypes;
	protected Table tTypes;
	protected TableViewerColumn tvcTypesNames;
	protected TableViewerColumn tvcTypesTypes;

	/** Attributes Viewer Elements */
	protected TableViewer tvAttributes;
	protected Table tAttributes;
	protected TableViewerColumn tvcAttributesNames;
	protected TableViewerColumn tvcAttributesTypes;

	/** Add Buttons */
	protected Button btnAddType;
	protected Button btnAddAttribute;

	/** Edit Buttons */
	protected Button btnEditAttribute;
	protected Button btnEditType;

	/** Viewers Inputs */
	protected Collection<DataType> inputTypes = new ArrayList<DataType>();
	protected Collection<DataTypeAttribute> inputAttributes = new ArrayList<DataTypeAttribute>();

	/** Viewers Selected Items */
	protected DataModel selectedModel;
	protected DataType selectedType;
	
	
	public DataTypesPreferencePage() {
	}

	@Override
	public void handleEvent(Event event) {
		super.handleEvent(event);
		
		inputTypes.clear();
		if(tvTypes != null) {
			tvTypes.refresh();
		}

		inputAttributes.clear();
		if(tvAttributes != null) {
			tvAttributes.refresh();
		}
	}
	
	@Override
	public void doCreateContents(Composite control) {
		//Data Type group
		Group typeGrp = PreferenceUiUtil.createGroup(control, "Data Types");
		createTypesUi(typeGrp);

		//Attribute group
		Group attributesGrp = PreferenceUiUtil.createGroup(control, "Attributes");
		createAttribuesUi(attributesGrp);
	}

	/**
	 * Create Attributes Ui Elements
	 * @param parent Composite parent
	 */
	protected void createAttribuesUi(Composite parent) {
		//Table Viewer
		Composite viewerComposite = new Composite(parent, SWT.NONE);
		viewerComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		TableColumnLayout attributeTVLayout = new TableColumnLayout();
		viewerComposite.setLayout(attributeTVLayout);

		tvAttributes = new TableViewer(viewerComposite);
		tvAttributes.setContentProvider(ArrayContentProvider.getInstance());
		tAttributes = tvAttributes.getTable();
		tAttributes.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tAttributes.setHeaderVisible(true);
		tAttributes.setLinesVisible(true);

		//Columns
		tvcAttributesNames = PreferenceUiUtil.createTableViewerColumn(tvAttributes, "Name", SWT.None);
		tvcAttributesNames.setLabelProvider(new ColumnLabelProvider() {

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
		attributeTVLayout.setColumnData(tvcAttributesNames.getColumn(), new ColumnWeightData(20, 100, true));

		tvcAttributesTypes = PreferenceUiUtil.createTableViewerColumn(tvAttributes, "Type", SWT.None);
		tvcAttributesTypes.setLabelProvider(new ColumnLabelProvider() {

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
		attributeTVLayout.setColumnData(tvcAttributesTypes.getColumn(), new ColumnWeightData(20, 100, true));

		tvAttributes.setInput(inputAttributes);

		Composite btnComposite = new Composite(parent, SWT.None);
		btnComposite.setLayout(new GridLayout());
		btnComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true));
		
		btnAddAttribute = PreferenceUiUtil.createAddButton(btnComposite, "Add Attribute");
		btnAddAttribute.setEnabled(false);
		
		btnEditAttribute = PreferenceUiUtil.createEditButton(btnComposite, "Edit Attribute");
		btnEditAttribute.setEnabled(false);
	}

	
	/** 
	 * Create Types Attributes
	 * @param parent Composite parent
	 */
	protected void createTypesUi(Composite parent) {
		//Table viewer
		Composite viewerComposite = new Composite(parent, SWT.NONE);
		viewerComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		TableColumnLayout typesTVLayout = new TableColumnLayout();
		viewerComposite.setLayout(typesTVLayout);

		tvTypes = new TableViewer(viewerComposite, SWT.BORDER);
		tvTypes.setContentProvider(ArrayContentProvider.getInstance());

		tTypes = tvTypes.getTable();
		tTypes.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tTypes.setHeaderVisible(true);
		tTypes.setLinesVisible(true);

		//Columns
		tvcTypesNames = PreferenceUiUtil.createTableViewerColumn(tvTypes, "Name", SWT.None);
		tvcTypesNames.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				if(element instanceof DataType) {
					return ((DataType)element).getName();
				}
				return super.getText(element);
			}
		});
		typesTVLayout.setColumnData(tvcTypesNames.getColumn(), new ColumnWeightData(20, 100, true));

		tvcTypesTypes = PreferenceUiUtil.createTableViewerColumn(tvTypes, "Type", SWT.None);
		tvcTypesTypes.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				if(element instanceof EnumerationType) {
					return "Enumeration";
				} else {
					return "Requirement";
				}
			}
		});
		typesTVLayout.setColumnData(tvcTypesTypes.getColumn(), new ColumnWeightData(20, 200, true));

		tvTypes.setInput(inputTypes);

		Composite btnComposite = new Composite(parent, SWT.None);
		btnComposite.setLayout(new GridLayout());
		btnComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true));
		
		btnAddType = PreferenceUiUtil.createAddButton(btnComposite, "Add Data Type");
		btnAddType.setEnabled(false);
		
		btnEditType = PreferenceUiUtil.createEditButton(btnComposite, "Edit Data Type");
		btnEditType.setEnabled(false);
	}

	@Override
	public void hookListeners() {

		super.hookListeners();
		
		tvModels.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				btnAddType.setEnabled(false);
				btnAddAttribute.setEnabled(false);
				inputTypes.clear();
				inputAttributes.clear();

				ISelection selection = event.getSelection();
				if(selection instanceof IStructuredSelection) {
					Object obj = ((IStructuredSelection)selection).getFirstElement();
					if(obj instanceof DataModel) {
						selectedModel = (DataModel)obj;
						btnAddType.setEnabled(true);
						inputTypes.addAll(dataModelManager.getDataTypes(selectedModel));
						inputTypes.addAll(dataModelManager.getEnumerationTypes(selectedModel));
					}
				}
				tvTypes.refresh();
				tvAttributes.refresh();
			}
		});

		tvTypes.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				btnAddAttribute.setEnabled(false);
				inputAttributes.clear();

				ISelection selection = event.getSelection();
				if(selection instanceof IStructuredSelection) {
					Object obj = ((IStructuredSelection)selection).getFirstElement();
					if(obj instanceof DataType) {
						selectedType = (DataType)obj;
						inputAttributes.addAll(selectedType.getAttributes());
						btnAddAttribute.setEnabled(true);
					}
				}
				tvAttributes.refresh();
			}
		});


		btnAddType.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				AddTypeDialog dialog = new AddTypeDialog(e.display.getActiveShell(), "Add Data Type");
				if(dialog.open() == Window.OK) {
					String name = dialog.getName();
					boolean isReq = dialog.isRequirement();
					DataType element;

					if(isReq) {
						element = dataModelManager.createRequirementType(name);
					} else {
						element = dataModelManager.createEnumerationType(name);
					}

					dataModelManager.addDataTypes(selectedModel, element);

					inputTypes.add(element);
					tvTypes.refresh();

				}
			}
		});

		btnAddAttribute.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				DataTypeAttribute attribute = null;

				if(selectedType instanceof EnumerationType) {

					NameDialog dialog = new NameDialog(e.display.getActiveShell(), "Add Enumeration Value");
					if(dialog.open() == Window.OK) {
						attribute = dataModelManager.createEnumeratorType(dialog.getName());
						((EnumerationType)selectedType).addEnumeratorType((EnumeratorType)attribute);
					}

				} else if(selectedType instanceof RequirementType) {

					Set<EDataType> values = new HashSet<EDataType>();
					values.addAll(DataUtil.eDataTypes);
					Collection<EnumerationType> enumerations = dataModelManager.getEnumerationTypes(selectedModel);
					values.addAll(Collections2.transform(enumerations, new Function<EnumerationType, EDataType>() {

						@Override
						public EDataType apply(EnumerationType arg0) {
							return arg0.getEDataType();
						}
					}));
					Collection<EDataType> types = Collections2.filter(values, Predicates.notNull());

					AddAttributeDialog dialog = new AddAttributeDialog(e.display.getActiveShell(), "Add Attribute", types);

					if(Window.OK == dialog.open()) {

						String name = dialog.getName();
						EDataType type = dialog.getType();
						attribute = dataModelManager.createAttributeType(name, type);
						((RequirementType)selectedType).addAttributeType((RequirementTypeAttribute)attribute);
					}
				}
				if(attribute != null) {
					inputAttributes.add(attribute);
					tvAttributes.refresh();
				}

			}

		});
	}

}

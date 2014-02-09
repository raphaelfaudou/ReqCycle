/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.types.configuration.preferences;

import java.util.Collections;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.polarsys.reqcycle.traceability.types.ITypesConfigurationProvider;
import org.polarsys.reqcycle.traceability.types.configuration.preferences.dialogs.DecorationDialog;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Configuration;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.DecorationPredicate;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.RelationsPredicatesMapping;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.ziggurat.inject.ZigguratInject;

public class TTypeDecorationPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {
	private ITypesConfigurationProvider typeConfProvider = ZigguratInject
			.make(ITypesConfigurationProvider.class);
	private Configuration defaultConfiguration;
	private Table tableOfMappings;
	private Table listOfRelations;
	private TableViewer listOfRelationsViewer;
	private TableViewer tableOfMappingViewer;
	private Button btnAdd;
	private Button btnRemove;
	private Button btnUp;
	private Button btnDown;

	/**
	 * Create the preference page.
	 */
	public TTypeDecorationPreferencePage() {
	}

	/**
	 * Create contents of the preference page.
	 * 
	 * @param parent
	 */
	@Override
	public Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		container.setLayout(new GridLayout(1, false));

		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1));
		lblNewLabel
				.setText("To add predicates use the menu Predicate Editor for Traceability Relation");

		Label lblIfARelation = new Label(container, SWT.NONE);
		lblIfARelation
				.setText("The predicates will be checked in the order they are displayed");

		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new GridLayout(4, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true,
				1, 1));

		Label lblPredicates = new Label(composite, SWT.NONE);
		lblPredicates.setBounds(0, 0, 55, 15);
		lblPredicates.setText("Traceability Relations");

		Label lblMappings = new Label(composite, SWT.NONE);
		lblMappings.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 3, 1));
		lblMappings.setText("Decorations");

		listOfRelationsViewer = new TableViewer(composite, SWT.BORDER
				| SWT.V_SCROLL);
		listOfRelations = listOfRelationsViewer.getTable();
		listOfRelations.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false,
				true, 2, 1));

		Composite composite_2 = new Composite(composite, SWT.NONE);
		GridLayout gl_composite_2 = new GridLayout(2, false);
		gl_composite_2.marginHeight = 0;
		gl_composite_2.marginWidth = 0;
		gl_composite_2.verticalSpacing = 0;
		composite_2.setLayout(gl_composite_2);
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				2, 1));

		tableOfMappingViewer = new TableViewer(composite_2, SWT.BORDER
				| SWT.FULL_SELECTION);
		tableOfMappings = tableOfMappingViewer.getTable();
		tableOfMappings.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1));
		tableOfMappings.setLinesVisible(true);

		Composite composite_1 = new Composite(composite_2, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true,
				1, 1));
		composite_1.setBounds(0, 0, 64, 64);
		GridLayout gl_composite_1 = new GridLayout(1, false);
		gl_composite_1.marginHeight = 0;
		composite_1.setLayout(gl_composite_1);

		btnAdd = new Button(composite_1, SWT.NONE);
		btnAdd.setImage(ResourceManager.getPluginImage(
				"org.polarsys.reqcycle.traceability.types.ui",
				"icons/add_obj.gif"));
		btnAdd.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false,
				1, 1));
		btnAdd.setBounds(0, 0, 75, 25);

		btnRemove = new Button(composite_1, SWT.NONE);
		btnRemove.setImage(ResourceManager.getPluginImage(
				"org.polarsys.reqcycle.traceability.types.ui",
				"icons/delete_obj.gif"));
		btnRemove.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (tableOfMappingViewer.getSelection() instanceof IStructuredSelection) {
					IStructuredSelection structured = (IStructuredSelection) tableOfMappingViewer
							.getSelection();
					if (structured.getFirstElement() instanceof EObject) {
						EObject eobject = (EObject) structured
								.getFirstElement();
						if (!(eobject instanceof Relation)) {
							EcoreUtil.delete(eobject);
						}
					}
				}
				tableOfMappingViewer.refresh(true);
			}
		});

		btnUp = new Button(composite_1, SWT.NONE);
		btnUp.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1,
				1));
		btnUp.setImage(ResourceManager.getPluginImage(
				"org.polarsys.reqcycle.traceability.types.ui",
				"icons/prev_nav-1.gif"));

		btnDown = new Button(composite_1, SWT.NONE);
		btnDown.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false,
				1, 1));
		btnDown.setImage(ResourceManager.getPluginImage(
				"org.polarsys.reqcycle.traceability.types.ui",
				"icons/next_nav-1.gif"));
		initProviders();

		defaultConfiguration = typeConfProvider.getDefaultConfiguration();
		if (defaultConfiguration != null) {
			TypeConfigContainer configContainer = typeConfProvider
					.getContainer();
			listOfRelationsViewer.setInput(defaultConfiguration.getRelations());
		} else {
			setErrorMessage("A default configuration has to be set");
		}
		initListeners();
		return container;
	}

	private void initListeners() {
		listOfRelationsViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
					@Override
					public void selectionChanged(SelectionChangedEvent event) {
						tableOfMappingViewer.setInput(Collections.emptyList());
						final Relation rel = getFromSelection(
								listOfRelationsViewer.getSelection(),
								Relation.class);
						if (rel != null) {
							RelationsPredicatesMapping m = getMapping(rel);
							if (m != null) {
								tableOfMappingViewer.setInput(m
										.getDecorations());
							}
						}
					}
				});

		ISelectionChangedListener listener = new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				boolean upEnabled = false;
				boolean downEnabled = false;
				boolean addEnabled = true;
				boolean deleteEnabled = false;
				if (listOfRelationsViewer.getSelection().isEmpty()) {
					addEnabled = false;
				}
				if (!tableOfMappingViewer.getSelection().isEmpty()) {
					deleteEnabled = true;
					DecorationPredicate dec = getFromSelection(
							tableOfMappingViewer.getSelection(),
							DecorationPredicate.class);
					if (dec != null) {
						@SuppressWarnings("unchecked")
						java.util.List<DecorationPredicate> decorations = (java.util.List) tableOfMappingViewer
								.getInput();
						int index = decorations.indexOf(dec);
						if (index > 0) {
							upEnabled = true;
						}
						if (index < decorations.size() - 1) {
							downEnabled = true;
						}
					}
				}
				btnAdd.setEnabled(addEnabled);
				btnRemove.setEnabled(deleteEnabled);
				btnUp.setEnabled(upEnabled);
				btnDown.setEnabled(downEnabled);
			}

		};
		tableOfMappingViewer.addSelectionChangedListener(listener);
		listOfRelationsViewer.addSelectionChangedListener(listener);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DecorationDialog d = new DecorationDialog(getShell());
				ZigguratInject.inject(d);
				if (d.open() == DecorationDialog.OK) {
					RelationsPredicatesMapping m = getMapping(getFromSelection(
							listOfRelationsViewer.getSelection(),
							Relation.class));
					DecorationPredicate p = TypeconfigurationFactory.eINSTANCE
							.createDecorationPredicate();
					p.setStyle(d.getStyle());
					p.setColor(d.getColor());
					p.setPredicate(d.getPredicate());
					m.getDecorations().add(p);
					tableOfMappingViewer.refresh();
				}

			}

		});
		btnRemove.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				DecorationPredicate dec = getFromSelection(
						tableOfMappingViewer.getSelection(),
						DecorationPredicate.class);
				EcoreUtil.delete(dec);
				tableOfMappingViewer.refresh();
			}

		});
		btnUp.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void widgetSelected(SelectionEvent e) {
				EList<DecorationPredicate> mappings = (EList<DecorationPredicate>) tableOfMappingViewer
						.getInput();
				DecorationPredicate d = getFromSelection(
						tableOfMappingViewer.getSelection(),
						DecorationPredicate.class);
				int index = mappings.indexOf(d);
				mappings.move(index - 1, d);
				tableOfMappingViewer.refresh();
			}
		});
		btnDown.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void widgetSelected(SelectionEvent e) {
				EList<DecorationPredicate> mappings = (EList<DecorationPredicate>) tableOfMappingViewer
						.getInput();
				DecorationPredicate d = getFromSelection(
						tableOfMappingViewer.getSelection(),
						DecorationPredicate.class);
				int index = mappings.indexOf(d);
				mappings.move(index + 1, d);
				tableOfMappingViewer.refresh();
			}
		});

	}

	protected RelationsPredicatesMapping getMapping(Relation rel) {
		for (RelationsPredicatesMapping m : defaultConfiguration.getParent()
				.getMappings()) {
			if (rel.equals(m.getRelation())) {
				return m;
			}
		}
		RelationsPredicatesMapping m = TypeconfigurationFactory.eINSTANCE
				.createRelationsPredicatesMapping();
		m.setRelation(rel);
		defaultConfiguration.getParent().getMappings().add(m);
		return m;
	}

	public <T> T getFromSelection(ISelection selec, Class<T> clazz) {
		if (selec instanceof IStructuredSelection) {
			IStructuredSelection structured = (IStructuredSelection) selec;
			if (clazz.isInstance(structured.getFirstElement())) {
				return (T) structured.getFirstElement();
			}
		}
		return null;
	}

	private void initProviders() {
		AdapterFactory factory = new ComposedAdapterFactory(
				ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		listOfRelationsViewer.setLabelProvider(new AdapterFactoryLabelProvider(
				factory));
		listOfRelationsViewer.setContentProvider(ArrayContentProvider
				.getInstance());
		tableOfMappingViewer.setContentProvider(ArrayContentProvider
				.getInstance());
		tableOfMappingViewer
				.setLabelProvider(new AdapterFactoryLabelProvider.FontAndColorProvider(
						factory, tableOfMappingViewer) {

					@Override
					public Font getFont(Object object, int columnIndex) {
						if (object instanceof DecorationPredicate) {
							DecorationPredicate d = (DecorationPredicate) object;
							Font font = JFaceResources.getFontRegistry().get(
									d.getStyle());
							if (font == null) {
								JFaceResources.getFontRegistry().put(
										d.getStyle(),
										StringConverter.asFontDataArray(d
												.getStyle()));
								font = JFaceResources.getFontRegistry().get(
										d.getStyle());
							}
							return font;
						}
						return super.getFont(object, columnIndex);
					}

					@Override
					public Color getForeground(Object object, int columnIndex) {
						if (object instanceof DecorationPredicate) {
							DecorationPredicate d = (DecorationPredicate) object;
							Color color = JFaceResources.getColorRegistry()
									.get(d.getColor());
							if (color == null) {
								JFaceResources.getColorRegistry().put(
										d.getColor(),
										StringConverter.asRGB(d.getColor()));
								color = JFaceResources.getColorRegistry().get(
										d.getColor());
							}
							return color;
						}
						return super.getForeground(object, columnIndex);
					}

				});
	}

	@Override
	public boolean performOk() {
		if (defaultConfiguration != null) {
			typeConfProvider.save(defaultConfiguration.getParent());
		}
		return super.performOk();
	}

	/**
	 * Initialize the preference page.
	 */
	public void init(IWorkbench workbench) {
		// Initialize the preference page
	}

}

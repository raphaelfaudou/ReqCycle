/*******************************************************************************
 *  * Copyright (c) 2013-2014 AtoS.
 *  * All rights reserved. This program and the accompanying materials
 *  * are made available under the terms of the Eclipse Public License v1.0
 *  * which accompanies this distribution, and is available at
 *  * http://www.eclipse.org/legal/epl-v10.html
 *  *
 *  * Contributors:
 *  *    {INITIAL AUTHOR} - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.reqcycle.traceability.types.configuration.preferences;

import java.util.Collection;
import java.util.Comparator;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.reqcycle.traceability.types.ITypesConfigurationProvider;
import org.eclipse.reqcycle.traceability.types.configuration.preferences.dialogs.NewConfigurationDialog;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Configuration;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer;
import org.eclipse.reqcycle.types.ITypesManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ziggurat.inject.ZigguratInject;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Ordering;

public abstract class AbstractPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {
	protected ITypesManager typesManager = ZigguratInject
			.make(ITypesManager.class);
	protected ITypesConfigurationProvider provider = ZigguratInject
			.make(ITypesConfigurationProvider.class);
	protected TypeConfigContainer container;
	protected TreeViewer treeViewer;
	protected Label labelProperties;
	protected ComposedAdapterFactory factory;
	protected Button btnAdd;
	protected Button btnRemove;
	private Tree tree;

	public AbstractPreferencePage() {
	}

	public AbstractPreferencePage(String title) {
		super(title);
	}

	/**
	 * @wbp.parser.constructor
	 */
	public AbstractPreferencePage(String title, ImageDescriptor image) {
		super(title, image);
	}

	@Override
	public void init(IWorkbench workbench) {
	}

	@Override
	protected Control createContents(Composite parent) {
		Composite top = new Composite(parent, SWT.None);
		top.setLayout(new GridLayout(2, false));

		Composite composite = new Composite(top, SWT.NONE);
		composite.setLayout(new FormLayout());
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,
				1));

		treeViewer = new TreeViewer(composite, SWT.BORDER);
		tree = treeViewer.getTree();
		FormData fd_tree = new FormData();
		fd_tree.bottom = new FormAttachment(75);
		fd_tree.right = new FormAttachment(100);
		fd_tree.top = new FormAttachment(0, 5);
		fd_tree.left = new FormAttachment(0, 5);
		tree.setLayoutData(fd_tree);

		addProperties(composite);

		Composite composite_1 = new Composite(top, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false,
				1, 1));
		composite_1.setLayout(new GridLayout(1, false));

		btnAdd = new Button(composite_1, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addAction();
			}

		});
		btnAdd.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		btnAdd.setText(getAddLabel());

		btnRemove = new Button(composite_1, SWT.NONE);
		btnRemove.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				removeAction();
			}

		});
		btnRemove.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		btnRemove.setText("Remove");

		addDefaultButton(composite_1);

		// providers
		factory = new ComposedAdapterFactory(
				ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		treeViewer.addFilter(getFilter());
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				AbstractPreferencePage.this.selectionChanged(event);

			}
		});

		doLoad();
		final ILabelProvider labelProvider = getLabelProvider();
		treeViewer.setLabelProvider(labelProvider);
		treeViewer.setComparator(new ViewerComparator(new Comparator<Object>() {

			@Override
			public int compare(Object o1, Object o2) {
				String s1 = o1.toString();
				String s2 = o2.toString();
				return Ordering.natural().compare(s1, s2);
			}
		}));
		treeViewer.setContentProvider(getContentProvider());
		setInput();
		return top;
	}

	protected IContentProvider getContentProvider() {
		return new AdapterFactoryContentProvider(factory);
	}

	protected ILabelProvider getLabelProvider() {
		return new AdapterFactoryLabelProvider(factory);
	}

	protected abstract void removeAction();

	protected void addProperties(Composite composite) {
		Group grpProperties = new Group(composite, SWT.NONE);
		FormData fd_grpProperties = new FormData();
		fd_grpProperties.top = new FormAttachment(tree, 5);
		fd_grpProperties.bottom = new FormAttachment(100, 5);
		fd_grpProperties.right = new FormAttachment(100);
		fd_grpProperties.left = new FormAttachment(0, 5);
		grpProperties.setLayoutData(fd_grpProperties);
		grpProperties.setLayout(new GridLayout(1, false));
		grpProperties.setText("Properties");

		labelProperties = new Label(grpProperties, SWT.NONE | SWT.WRAP);
		labelProperties.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1));
	}

	protected String getAddLabel() {
		return "Add";
	}

	protected abstract void addAction();

	protected abstract boolean removeCondition(EObject firstElement);

	protected void addDefaultButton(Composite composite_1) {
		Button defaultButton = new Button(composite_1, SWT.NONE);
		defaultButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false, 1, 1));
		defaultButton.setText("set current");
		defaultButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (treeViewer.getSelection() instanceof IStructuredSelection) {
					IStructuredSelection new_name = (IStructuredSelection) treeViewer
							.getSelection();
					if (new_name.getFirstElement() instanceof Configuration) {
						Configuration conf = (Configuration) new_name
								.getFirstElement();
						if (conf.getParent().getDefaultConfiguration() == conf) {
							conf.getParent().setDefaultConfiguration(null);
						} else {
							conf.getParent().setDefaultConfiguration(conf);
						}
						treeViewer.refresh();
					}
				}
			}
		});
	}

	protected abstract ViewerFilter getFilter();

	protected void displayProperties(EObject eobject) {
		if (eobject == null) {
			labelProperties.setText("");
		} else {
			StringBuffer buffer = new StringBuffer();
			ILabelProvider labelProvider = getLabelProvider();
			for (EStructuralFeature f : eobject.eClass()
					.getEAllStructuralFeatures()) {
				if (!(f instanceof EReference && ((EReference) f)
						.isContainment())) {
					buffer.append(f.getName()).append(" : ");
					buffer.append(getText(eobject, labelProvider, f));
					buffer.append("\n");
				}
			}
			labelProperties.setText(buffer.toString());
		}
	}

	private String getText(EObject eobject, final ILabelProvider labelProvider,
			EStructuralFeature f) {
		Object eGet = eobject.eGet(f);
		if (eGet instanceof Collection) {
			Collection<Object> collec = (Collection<Object>) eGet;
			return Joiner.on(", ").join(
					Iterables.transform(collec, new Function<Object, String>() {
						public String apply(Object o) {
							return labelProvider.getText(o);
						}
					}));
		} else {
			return labelProvider.getText(eGet);
		}
	}

	protected void createConfiguration() {
		NewConfigurationDialog dialog = new NewConfigurationDialog(getShell());
		if (dialog.open() == NewConfigurationDialog.OK) {
			container.getConfigurations().add(dialog.getConfiguration());
			treeViewer.refresh();
		}
	}

	@Override
	public boolean performCancel() {
		return super.performCancel();
	}

	@Override
	protected void performDefaults() {
		doLoad();
		super.performDefaults();
	}

	protected void doLoad() {
		container = provider.getContainer();
		if (container.eResource() == null) {
			Resource r = new ResourceSetImpl().createResource(URI
					.createURI("dummy://dummy"));
			r.getContents().add(container);
		}
	}

	protected void setInput() {
		treeViewer.setInput(container.eResource());
	}

	@Override
	protected void performApply() {
		super.performApply();
		performOk();
		doLoad();
		setInput();
	}

	@Override
	public boolean performOk() {
		if (container != null) {
			provider.save(container);
		}
		return super.performOk();
	}

	protected void selectionChanged(SelectionChangedEvent event) {
		if (event.getSelection() instanceof IStructuredSelection) {
			IStructuredSelection structured = (IStructuredSelection) event
					.getSelection();
			Object firstElement = structured.getFirstElement();
			if (firstElement instanceof EObject) {
				EObject eobject = (EObject) firstElement;
				displayProperties(eobject);
				btnRemove.setEnabled(removeCondition(eobject));
			} else {
				displayProperties(null);
			}
		} else {
			displayProperties(null);
		}
	}
}

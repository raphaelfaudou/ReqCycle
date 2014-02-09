/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.types.configuration.preferences.dialogs;

import javax.inject.Inject;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.polarsys.reqcycle.traceability.model.TType;
import org.polarsys.reqcycle.traceability.types.ITraceTypesManager;
import org.polarsys.reqcycle.traceability.types.configuration.preferences.providers.PreferenceDialogTypeLabelProvider;
import org.polarsys.reqcycle.traceability.types.configuration.preferences.providers.PreferenceDialogTypesContentProvider;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.CustomType;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Type;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationFactory;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationPackage;
import org.polarsys.reqcycle.types.ITypesManager;
import org.polarsys.reqcycle.types.ui.providers.TypeLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ziggurat.inject.ZigguratInject;

import com.google.common.collect.Lists;

public class NewRelationDialog extends TitleAreaDialog {
	private Text textSource;
	private Text textTarget;
	private Relation rel = TypeconfigurationFactory.eINSTANCE.createRelation();
	private TreeViewer treeViewer;
	@Inject
	ITypesManager manager;
	@Inject
	ITraceTypesManager ttManager;
	private TypeConfigContainer container;
	private AdapterFactory adapterFactory = new ComposedAdapterFactory(
			ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
	private Text textKind;
	private CheckboxTableViewer listOfTTViewer;
	private Text textIcon;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 * @param container
	 */
	public NewRelationDialog(Shell parentShell, TypeConfigContainer container) {
		super(parentShell);
		setShellStyle(SWT.RESIZE | SWT.TITLE);
		this.container = container;
	}

	@Override
	protected void okPressed() {
		if (rel.getDownstreamType() == null || rel.getUpstreamType() == null
				|| rel.getKind() == null) {
			setErrorMessage("Please fill all the parameters");
		} else {
			super.okPressed();
		}
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle("Create a new Relation Type");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,
				1));
		composite.setLayout(new GridLayout(2, false));

		treeViewer = new TreeViewer(composite, SWT.BORDER);
		Tree tree = treeViewer.getTree();
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false,
				1, 1));
		composite_1.setLayout(new GridLayout(1, false));

		Button btnSource = new Button(composite_1, SWT.NONE);
		btnSource.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Object firstElement = ((IStructuredSelection) treeViewer
						.getSelection()).getFirstElement();
				if (firstElement instanceof Type) {
					Type type = (Type) firstElement;
					set(type,
							textSource,
							TypeconfigurationPackage.Literals.RELATION__UPSTREAM_TYPE);
				}
			}
		});
		btnSource.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false,
				1, 1));
		btnSource.setText("Upstream");

		Button btnTarget = new Button(composite_1, SWT.NONE);
		btnTarget.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				set((Type) ((IStructuredSelection) treeViewer.getSelection())
						.getFirstElement(),
						textTarget,
						TypeconfigurationPackage.Literals.RELATION__DOWNSTREAM_TYPE);
			}
		});
		btnTarget.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false, 1, 1));
		btnTarget.setText("Downstream");

		Group grpProperties = new Group(container, SWT.NONE);
		grpProperties.setLayout(new GridLayout(3, false));
		grpProperties.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		grpProperties.setText("Properties");

		Label lblNewLabel_1 = new Label(grpProperties, SWT.NONE);
		lblNewLabel_1.setText("Name : ");

		textKind = new Text(grpProperties, SWT.BORDER);
		textKind.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				2, 1));
		textKind.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				rel.setKind(textKind.getText());
			}
		});

		Label lblIcon = new Label(grpProperties, SWT.NONE);
		lblIcon.setText("Icon : ");

		textIcon = new Text(grpProperties, SWT.BORDER);
		textIcon.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Button btnNewButton = new Button(grpProperties, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// image serialization
			}
		});
		btnNewButton.setText("...");

		Label lblSource = new Label(grpProperties, SWT.NONE);
		lblSource.setText("Upstream :");

		textSource = new Text(grpProperties, SWT.BORDER);
		textSource.setEditable(false);
		textSource.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 2, 1));

		Label lblNewLabel = new Label(grpProperties, SWT.NONE);
		lblNewLabel.setText("Downstream :");

		textTarget = new Text(grpProperties, SWT.BORDER);
		textTarget.setEditable(false);
		textTarget.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 2, 1));

		Group grpIncludedTraceability = new Group(container, SWT.NONE);
		grpIncludedTraceability.setText("Included Traceability");
		grpIncludedTraceability.setLayout(new FillLayout(SWT.HORIZONTAL));
		grpIncludedTraceability.setLayoutData(new GridData(SWT.FILL,
				SWT.CENTER, false, false, 1, 1));

		listOfTTViewer = CheckboxTableViewer.newCheckList(
				grpIncludedTraceability, SWT.BORDER | SWT.V_SCROLL | SWT.CHECK);
		listOfTTViewer.addCheckStateListener(new ICheckStateListener() {

			@Override
			public void checkStateChanged(CheckStateChangedEvent event) {
				String id = ((TType) event.getElement()).getId();
				if (event.getChecked()) {
					rel.getAgregatedTypes().add(id);
				} else {
					rel.getAgregatedTypes().remove(id);
				}
			}
		});
		listOfTTViewer.setContentProvider(new ArrayContentProvider());
		listOfTTViewer.setLabelProvider(new LabelProvider() {

			@Override
			public String getText(Object element) {
				if (element instanceof TType) {
					TType ttype = (TType) element;
					return ttype.getLabel();
				}
				return super.getText(element);
			}

		});
		listOfTTViewer.setInput(Lists.newArrayList(ttManager.getAllTTypes()));
		IContentProvider provider = new PreferenceDialogTypesContentProvider(
				adapterFactory, this.container);
		ILabelProvider labelProvider = new PreferenceDialogTypeLabelProvider(
				adapterFactory, new AdapterFactoryLabelProvider(adapterFactory));
		ZigguratInject.inject(labelProvider, provider);
		treeViewer.setContentProvider(provider);
		treeViewer.setLabelProvider(labelProvider);
		treeViewer.addFilter(new ViewerFilter() {

			@Override
			public boolean select(Viewer viewer, Object parentElement,
					Object element) {
				return element instanceof TypeConfigContainer
						|| element instanceof Type;
			}
		});
		treeViewer.setInput(this.container.eResource());
		return area;
	}

	protected void set(Type type, Text aText, EReference relationDownstreamType) {
		if (type instanceof CustomType) {
			CustomType cust = (CustomType) type;
			aText.setText(cust.getTypeId());
		} else {
			aText.setText(new TypeLabelProvider().getText(type.getIType()));
		}
		rel.eSet(relationDownstreamType, type);
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		Rectangle bounds = Display.getDefault().getPrimaryMonitor().getBounds();
		int width = bounds.width * 3 / 5;
		int height = bounds.width * 4 / 5;
		return new Point(width, height);
	}

	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle bounds = Display.getDefault().getPrimaryMonitor().getBounds();
		int x = bounds.x + (bounds.width * 1 / 5);
		int y = bounds.y + (bounds.height * 1 / 5);
		return new Point(x, y);
	}

	public Relation getRelation() {
		return rel;
	}
}

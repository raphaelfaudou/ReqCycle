package org.eclipse.reqcycle.core.ui.preferences;

import java.util.Collections;

import javax.inject.Inject;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.preference.IPreferencePageContainer;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.reqcycle.core.ILogger;
import org.eclipse.reqcycle.core.ui.Activator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ziggurat.inject.ZigguratInject;

public class ReqCyclePreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

	protected Table tConfRepos;

	protected TableViewer tvConfRepos;

	protected Label labelProperties;

	protected Button btnAdd;

	protected Button btnEdit;

	protected Button btnRemove;

	protected Button btnSync;

	protected Button btnImport;

	protected Button btnExport;
	
	@Inject
	IEventBroker broker;

	@Inject
	ILogger logger;

	/**
	 * @wbp.parser.constructor
	 */
	public ReqCyclePreferencePage() {
		ZigguratInject.inject(this);
	}

	public ReqCyclePreferencePage(String title) {
		super(title);
	}

	public ReqCyclePreferencePage(String title, ImageDescriptor image) {
		super(title, image);
	}

	@Override
	public void init(IWorkbench workbench) {

	}

	@Override
	protected Control createContents(Composite parent) {

		Group control = new Group(parent, SWT.None);
		control.setLayout(new GridLayout(2, false));
		control.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		control.setText("Configuration Repositories");

		SashForm sashForm = new SashForm(control, SWT.VERTICAL);
		sashForm.setLayout(new GridLayout());
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		Composite tvComposite = new Composite(sashForm, SWT.NONE);
		TableColumnLayout tvLayout = new TableColumnLayout();
		tvComposite.setLayout(tvLayout);

		tvConfRepos = new TableViewer(tvComposite);
		tvConfRepos.setContentProvider(ArrayContentProvider.getInstance());
		tConfRepos = tvConfRepos.getTable();
		tConfRepos.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tConfRepos.setLinesVisible(true);

		TableViewerColumn viewerColumn = new TableViewerColumn(tvConfRepos, SWT.None);
		TableColumn column = viewerColumn.getColumn();
		column.setText("Label");
		column.setResizable(true);
		column.setMoveable(true);
		//FIXME : override ColumnLabelProvider.getText
		viewerColumn.setLabelProvider(new ColumnLabelProvider());
		tvLayout.setColumnData(column, new ColumnWeightData(20, 100, true));
		//FIXME : manage table viewer Input
		tvConfRepos.setInput(Collections.emptyList());

		Group grpProperties = new Group(sashForm, SWT.NONE);
		grpProperties.setLayout(new GridLayout(1, false));
		grpProperties.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		grpProperties.setText("Properties");

		labelProperties = new Label(grpProperties, SWT.NONE | SWT.WRAP);
		labelProperties.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Composite btnComposite = new Composite(control, SWT.None);
		btnComposite.setLayout(new GridLayout());
		btnComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true));

		createButtons(btnComposite);
		hookListeners();

		return control;
	}

	protected void hookListeners() {
	}

	
	
	protected void close() {
		IPreferencePageContainer container = getContainer();
		if(container instanceof PreferenceDialog) {
			((PreferenceDialog)container).close();
		}
	}

	protected void createButtons(Composite btnComposite) {
		btnAdd = new Button(btnComposite, SWT.PUSH);
		btnAdd.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
		btnAdd.setToolTipText("Add");
		btnAdd.setImage(Activator.getImageDescriptor("/icons/add.gif").createImage());
		btnAdd.setEnabled(false);

		btnRemove = new Button(btnComposite, SWT.PUSH);
		btnRemove.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
		btnRemove.setToolTipText("Remove");
		btnRemove.setImage(Activator.getImageDescriptor("/icons/delete.gif").createImage());
		btnRemove.setEnabled(false);

		btnEdit = new Button(btnComposite, SWT.PUSH);
		btnEdit.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
		btnEdit.setToolTipText("Edit");
		btnEdit.setImage(Activator.getImageDescriptor("/icons/edit.png").createImage());
		btnEdit.setEnabled(false);

		btnSync = new Button(btnComposite, SWT.PUSH);
		btnSync.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
		btnSync.setToolTipText("Synchronize");
		btnSync.setImage(Activator.getImageDescriptor("/icons/sync.gif").createImage());
		btnSync.setEnabled(false);

		btnImport = new Button(btnComposite, SWT.PUSH);
		btnImport.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
		btnImport.setToolTipText("Import");
		btnImport.setImage(Activator.getImageDescriptor("/icons/import16.gif").createImage());
		btnImport.setEnabled(false);

		btnExport = new Button(btnComposite, SWT.PUSH);
		btnExport.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
		btnExport.setToolTipText("Export");
		btnExport.setImage(Activator.getImageDescriptor("/icons/export16.gif").createImage());
		btnExport.setEnabled(false);
	}

	
	
}

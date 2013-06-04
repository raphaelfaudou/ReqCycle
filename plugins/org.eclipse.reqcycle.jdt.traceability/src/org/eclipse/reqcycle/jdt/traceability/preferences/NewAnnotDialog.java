package org.eclipse.reqcycle.jdt.traceability.preferences;

import java.util.Arrays;

import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.internal.core.SourceType;
import org.eclipse.jdt.internal.ui.dialogs.OpenTypeSelectionDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.reqcycle.traceability.model.TraceabilityLink;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wb.swt.ResourceManager;

public class NewAnnotDialog extends TitleAreaDialog {
	private Text text;
	private String annotName = null;
	private TraceabilityLink link = null;
	private ComboViewer comboViewer;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public NewAnnotDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitleImage(ResourceManager.getPluginImage(
				"org.eclipse.reqcycle.jdt.traceability", "icons/annot.gif"));
		setTitle("Register new Annotation");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(3, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label lblAnnotationName = new Label(container, SWT.NONE);
		lblAnnotationName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
				false, false, 1, 1));
		lblAnnotationName.setText("Annotation Name : ");

		text = new Text(container, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				annotName = text.getText();
			}
		});

		Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				OpenTypeSelectionDialog d = new OpenTypeSelectionDialog(
						getShell(), false, PlatformUI.getWorkbench()
								.getProgressService(), SearchEngine
								.createWorkspaceScope(),
						IJavaSearchConstants.ANNOTATION_TYPE);
				if (d.open() == OpenTypeSelectionDialog.OK) {
					SourceType type = (SourceType) d.getFirstResult();
					text.setText(type.getElementName());
				}
			}
		});
		button.setText("...");

		Label lblKind = new Label(container, SWT.NONE);
		lblKind.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		lblKind.setText("Kind : ");

		comboViewer = new ComboViewer(container, SWT.READ_ONLY);
		Combo combo = comboViewer.getCombo();
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2,
				1));
		comboViewer.setContentProvider(new ArrayContentProvider());
		comboViewer.setLabelProvider(new LabelProvider());
		comboViewer.setInput(Arrays.asList(TraceabilityLink.values()));
		comboViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {

					@Override
					public void selectionChanged(SelectionChangedEvent event) {
						link = (TraceabilityLink) ((IStructuredSelection) comboViewer
								.getSelection()).getFirstElement();
					}
				});
		return area;
	}

	@Override
	protected void okPressed() {
		if (annotName == null || annotName.length() == 0 || link == null) {
			setErrorMessage("please fill all the fields");
		} else {
			super.okPressed();
		}
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
		return new Point(450, 300);
	}

	public String getAnnotName() {
		return annotName;
	}

	public TraceabilityLink getLink() {
		return link;
	}

}

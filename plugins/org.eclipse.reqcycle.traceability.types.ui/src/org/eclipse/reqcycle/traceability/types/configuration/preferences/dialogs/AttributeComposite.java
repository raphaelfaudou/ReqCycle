package org.eclipse.reqcycle.traceability.types.configuration.preferences.dialogs;

import java.util.Collection;
import java.util.LinkedList;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Attribute;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.AttributeType;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.StdAttribute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;

public class AttributeComposite extends Composite {
	private boolean editable;
	private Text textName;
	private Button buttonAdd;
	private Combo comboType;
	private List listValuess;
	private ComboViewer comboViewer;
	private Collection<String> allPossibleValues = new LinkedList<String>();
	private Button btnRemove;
	private ListViewer listViewer;

	public AttributeComposite(Composite parent) {
		super(parent, SWT.NONE);
		setLayout(new GridLayout(3, false));

		Label lblName = new Label(this, SWT.NONE);
		lblName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		lblName.setText("name : ");
		new Label(this, SWT.NONE);

		textName = new Text(this, SWT.BORDER);
		textName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Label lblType = new Label(this, SWT.NONE);
		lblType.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		lblType.setText("type : ");
		new Label(this, SWT.NONE);

		comboViewer = new ComboViewer(this, SWT.READ_ONLY);
		comboType = comboViewer.getCombo();
		comboType.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		comboViewer.setLabelProvider(new LabelProvider() {

			@Override
			public String getText(Object element) {
				if (element instanceof AttributeType) {
					AttributeType type = (AttributeType) element;
					return type.getName();
				}
				return super.getText(element);
			}

		});
		comboViewer.setContentProvider(new ArrayContentProvider());
		comboViewer.setInput(AttributeType.values());

		Label lblPossibleValues = new Label(this, SWT.NONE);
		lblPossibleValues.setText("Possible Values : ");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);

		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3,
				1));

		listViewer = new ListViewer(composite, SWT.BORDER | SWT.V_SCROLL);
		listValuess = listViewer.getList();
		listValuess.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				1, 1));
		listViewer.setLabelProvider(new LabelProvider());
		listViewer.setContentProvider(new ArrayContentProvider());
		listViewer.setInput(allPossibleValues);

		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false,
				false, 1, 1));
		composite_1.setLayout(new FillLayout(SWT.VERTICAL));
		composite_1.setBounds(0, 0, 64, 64);

		buttonAdd = new Button(composite_1, SWT.NONE);
		buttonAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				InputDialog dialog = new InputDialog(getShell(), "Value",
						"Please enter a possible value", "", null);
				if (dialog.open() == InputDialog.OK) {
					allPossibleValues.add(dialog.getValue());
					listViewer.refresh();
				}
			}
		});
		buttonAdd.setText("Add");

		btnRemove = new Button(composite_1, SWT.NONE);
		btnRemove.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ISelection selection = listViewer.getSelection();
				if (selection != null) {
					String element = (String) ((IStructuredSelection) selection)
							.getFirstElement();
					allPossibleValues.remove(element);
					listViewer.refresh();
				}
			}
		});
		btnRemove.setText("Remove");

	}

	public void setEditable(boolean editable) {
		this.editable = editable;
		textName.setEnabled(editable);
		btnRemove.setEnabled(editable);
		buttonAdd.setEnabled(editable);
		comboType.setEnabled(editable);
		textName.setText("");
		comboType.clearSelection();
		allPossibleValues.clear();
		listViewer.refresh();
		comboViewer.setSelection(null);

	}

	public boolean isValid() {
		if (editable) {
			return textName != null && !comboViewer.getSelection().isEmpty();
		}
		return true;
	}

	public void displayAttribute(Attribute att) {
		textName.setText(att.getName());
		comboViewer.setSelection(new StructuredSelection(att.getType()));
		allPossibleValues.addAll(att.getPossibleValues());
		comboViewer.refresh(true);
		listViewer.refresh();
	}

	public void saveInAttribute(StdAttribute att) {
		att.setName(textName.getText());
		att.setType((AttributeType) ((IStructuredSelection) comboViewer
				.getSelection()).getFirstElement());
		att.getPossibleValues().addAll(allPossibleValues);
	}
}

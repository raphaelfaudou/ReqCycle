package org.eclipse.reqcycle.traceability.ui.views;

import java.util.regex.Pattern;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.reqcycle.traceability.types.ITraceabilityAttributesManager.EditableAttribute;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.AttributeType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;

public class LinkPropertyDescriptor extends PropertyDescriptor {

	private EditableAttribute att;
	private static Pattern INTPATTERN = Pattern.compile("\\d*");

	public LinkPropertyDescriptor(EditableAttribute att) {
		super(att.getName(), att.getName());
		this.att = att;
	}

	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		if (att.getType() == AttributeType.BOOLEAN) {
			return new CheckboxCellEditor(parent);
		}
		Object[] possibleValues = att.getPossibleValues();
		if (possibleValues != null && possibleValues.length > 0) {
			ComboBoxViewerCellEditor cellEditor = new ComboBoxViewerCellEditor(
					parent, SWT.READ_ONLY);
			cellEditor.setContentProvider(ArrayContentProvider.getInstance());
			cellEditor.setInput(possibleValues);
			return cellEditor;
		} else {
			TextCellEditor result = new TextCellEditor(parent);
			if (att.getType() == AttributeType.INT) {
				result.setValidator(new ICellEditorValidator() {

					@Override
					public String isValid(Object value) {
						if (value instanceof String
								&& INTPATTERN.matcher((CharSequence) value)
										.matches()) {
							return null;
						}
						return "integer value expected";
					}
				});
			}
			return result;
		}
	}

}

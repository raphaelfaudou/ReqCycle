package org.eclipse.reqcycle.ui.eenumpropseditor.internal.components;

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditorComponent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class EEnumPropsEditorComponent extends AbstractPropsEditorComponent<Enumerator> {

    private ComboViewer comboViewer;

    public EEnumPropsEditorComponent(EAttribute attribute, Composite parent, int style) {
        super(attribute, parent, style);
        setLayout(new GridLayout(2, false));

        Label lblName = new Label(this, SWT.NONE);
        lblName.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
        lblName.setText(attribute.getName());

        comboViewer = new ComboViewer(this, SWT.READ_ONLY);
        Combo combo = comboViewer.getCombo();
        combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        comboViewer.setContentProvider(ArrayContentProvider.getInstance());
        comboViewer.setLabelProvider(new LabelProvider() {
            @Override
            public String getText(Object element) {
                if (element instanceof EEnumLiteral) {
                    return ((EEnumLiteral) element).getLiteral();
                }
                return super.getText(element);
            }
        });
        final EEnum eEnum = (EEnum) attribute.getEType();
        comboViewer.setInput(eEnum.getELiterals().toArray());
        comboViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                if (!comboViewer.getSelection().isEmpty()
                        && (comboViewer.getSelection() instanceof IStructuredSelection)) {
                    IStructuredSelection selection = (IStructuredSelection) comboViewer.getSelection();
                    final Enumerator selectedLiteral = ((EEnumLiteral) selection.getFirstElement()).getInstance();
                    setValue(selectedLiteral);
                }
            }
        });
    }

    @Override
    public boolean isValid() {
        return true;
    }
}

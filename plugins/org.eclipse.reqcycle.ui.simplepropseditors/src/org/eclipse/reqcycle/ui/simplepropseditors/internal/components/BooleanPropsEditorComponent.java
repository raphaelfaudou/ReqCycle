package org.eclipse.reqcycle.ui.simplepropseditors.internal.components;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditorComponent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridData;

public class BooleanPropsEditorComponent extends AbstractPropsEditorComponent<Boolean> {

    public BooleanPropsEditorComponent(EAttribute attribute, Composite parent, int style) {
        super(attribute, parent, style);
        setLayout(new GridLayout(2, false));

        Label lblName = new Label(this, SWT.NONE);
        lblName.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
        lblName.setText(attribute.getName());

        Composite buttonsComposite = new Composite(this, SWT.NONE);
        buttonsComposite.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, false, 1, 1));
        buttonsComposite.setLayout(new GridLayout(2, false));

        Button btnTrue = new Button(buttonsComposite, SWT.RADIO);
        btnTrue.setText("true");
        btnTrue.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                setValue(Boolean.TRUE);
            }
        });

        Button btnFalse = new Button(buttonsComposite, SWT.RADIO);
        btnFalse.setText("false");
        btnFalse.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                setValue(Boolean.FALSE);
            }
        });
    }

    @Override
    public boolean isValid() {
        return getValue() != null && getValue() instanceof Boolean;
    }
}

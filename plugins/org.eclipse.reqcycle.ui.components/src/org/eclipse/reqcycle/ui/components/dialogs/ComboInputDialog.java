package org.eclipse.reqcycle.ui.components.dialogs;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;

/**
 * A simple dialog box composed of combo and the 2 OK and cancel buttons.
 * 
 * @author Papa Issa DIAKHATE
 */
public class ComboInputDialog extends AbstractCustomDialog {

    /** Sets whether or not the ComboBox is editable or not */
    private final boolean    readOnly;

    /** Combo viewer. */
    private ComboViewer      comboViewer;

    /**
     * The selected item when the OK button is pressed, <code>null</code> otherwise (no selection, or when cancel button
     * is pressed instead.
     */
    private Object           selectedItem;

    private IContentProvider comboContentProvider;

    private ILabelProvider   comboILabelProvider;

    /**
     * Create the dialog with a read-only ComboViewer.
     * 
     * @param parentShell
     * @param dialogTitle
     * @param dialogMessage
     * @param initialInput
     * @param validator
     * @wbp.parser.constructor
     */
    public ComboInputDialog(Shell parentShell, String dialogTitle, String dialogMessage, Object initialInput,
            IInputValidator validator) {
        this(parentShell, dialogTitle, dialogMessage, initialInput, validator, true);
    }

    /**
     * Create the dialog.
     * 
     * @param parentShell
     * @param dialogTitle
     * @param dialogMessage
     * @param initialInput
     * @param validator
     * @param readOnly
     */
    public ComboInputDialog(Shell parentShell, String dialogTitle, String dialogMessage, Object initialInput,
            IInputValidator validator, boolean readOnly) {

        super(parentShell, dialogTitle, dialogMessage, initialInput, validator);
        this.readOnly = readOnly;
    }

    @Override
    protected void createCustomDialogArea(Composite parent) {

        this.comboViewer = this.readOnly ? new ComboViewer(parent, SWT.READ_ONLY) : new ComboViewer(parent);
        Combo combo = comboViewer.getCombo();
        combo.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
        if (!readOnly) {
            combo.addModifyListener(new ModifyListener() {
                public void modifyText(ModifyEvent e) {
                    validateInput();
                }
            });
        }
        if (this.comboContentProvider == null) this.comboContentProvider = ArrayContentProvider.getInstance();
        if (this.comboILabelProvider == null) this.comboILabelProvider = new LabelProvider();
        this.comboViewer.setContentProvider(this.comboContentProvider);
        this.comboViewer.setLabelProvider(this.comboILabelProvider);
        this.comboViewer.setInput(getInput());
        combo.addListener(SWT.Selection, this);
    }

    protected void validateInput() {
        String errorMessage = null;
        if (getValidator() != null) {
            errorMessage = getValidator().isValid(this.comboViewer.getCombo().getText());
        }
        setErrorMessage(errorMessage);
    }

    @Override
    protected void buttonPressed(int buttonId) {
        if (buttonId == IDialogConstants.OK_ID) {
            this.selectedItem = ((IStructuredSelection) this.comboViewer.getSelection()).getFirstElement();
        } else {
            this.selectedItem = null;
        }
        super.buttonPressed(buttonId);
    }

    /**
     * 
     * @return The selected item when the OK button is pressed, <code>null</code> otherwise (no selection, or when
     *         cancel button is pressed instead.
     */
    public Object getSelectedItem() {
        Iterator<Object> iter = this.getSelectedItems().iterator();
        if (iter.hasNext()) {
            return iter.next();
        }
        return null;
    }

    @Override
    public Collection<Object> getSelectedItems() {
        return Arrays.asList(new Object[] { this.selectedItem });
    }

    /**
     * When this method is never called, the default content provider which is used is {@link ArrayContentProvider}.
     * 
     * @param contentProvider
     */
    public void setComboContentProvider(IContentProvider contentProvider) {
        this.comboContentProvider = contentProvider;
    }

    /**
     * When this method is never called, the default content provider which is used is {@link LabelProvider}.
     * 
     * @param labelProvider
     */
    public void setComboLabelProvider(ILabelProvider labelProvider) {
        this.comboILabelProvider = labelProvider;
    }

    protected ComboViewer getComboViewer() {
        return this.comboViewer;
    }

    @Override
    protected Button createButton(Composite parent, int id, String label, boolean defaultButton) {
        Button btn = super.createButton(parent, id, label, defaultButton);
        if (OK == id && btn != null) {
            btn.setEnabled(false);
        }
        return btn;
    }

    @Override
    public void handleEvent(Event event) {
        if (comboViewer == null) {
            return;
        }
        enableOkButton(comboViewer.getSelection() != null);
    }

}

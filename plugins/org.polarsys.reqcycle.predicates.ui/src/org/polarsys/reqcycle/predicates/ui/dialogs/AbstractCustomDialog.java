/*****************************************************************************
 * Copyright (c) 2013 AtoS.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Papa Issa DIAKHATE (AtoS) papa-issa.diakhate@atos.net - Initial API and implementation
 *
 *****************************************************************************/
package org.polarsys.reqcycle.predicates.ui.dialogs;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public abstract class AbstractCustomDialog extends Dialog implements Listener {

	/** The title of the dialog. */
	private String title;

	/** The message to display, or <code>null</code> if none. */
	private String message;

	/** The input of the combo. Default value is an empty array of Object. */
	private final Object input; //$NON-NLS-1$

	/** The input validator, or <code>null</code> if none. */
	private IInputValidator validator;

	/** Ok button widget. */
	private Button okButton;

	/** Error message label widget. */
	private Text errorMessageText;

	/** Error message string. */
	private String errorMessage;

	public AbstractCustomDialog(Shell parentShell, String dialogTitle, String dialogMessage, Object initialInput, IInputValidator validator) {

		super(parentShell);

		this.title = dialogTitle;
		this.message = dialogMessage;
		this.input = (initialInput == null) ? Collections.EMPTY_LIST.toArray() : initialInput;
		this.validator = validator;
	}

	@Override
	protected Control createDialogArea(Composite parent) {

		Composite container = (Composite)super.createDialogArea(parent);

		if(this.message != null) {
			Label label = new Label(container, SWT.WRAP);
			label.setText(this.message);
			GridData data = new GridData(GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL | GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_CENTER);
			data.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH);
			label.setLayoutData(data);
			label.setFont(parent.getFont());
		}

		createCustomDialogArea(container);

		errorMessageText = new Text(container, SWT.READ_ONLY | SWT.WRAP);
		errorMessageText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		errorMessageText.setBackground(errorMessageText.getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		setErrorMessage(errorMessage);

		applyDialogFont(container);

		return container;
	}

	protected abstract void createCustomDialogArea(Composite parent);

	public abstract Collection<Object> getSelectedItems();

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		this.okButton = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		if(title != null) {
			shell.setText(title);
		}
	}

	protected Text getErrorMessageText() {
		return this.errorMessageText;
	}

	/**
	 * Sets or clears the error message. If not <code>null</code>, the OK button is disabled.
	 * 
	 * @param errorMessage
	 *        the error message, or <code>null</code> to clear
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		if(errorMessageText != null && !errorMessageText.isDisposed()) {
			errorMessageText.setText(errorMessage == null ? " \n " : errorMessage); //$NON-NLS-1$
			// Disable the error message text control if there is no error, or
			// no error text (empty or whitespace only). Hide it also to avoid color change.
			boolean hasError = errorMessage != null && (StringConverter.removeWhiteSpaces(errorMessage)).length() > 0;
			errorMessageText.setEnabled(hasError);
			errorMessageText.setVisible(hasError);
			errorMessageText.getParent().update();
			// Access the ok button by id, in case clients have overridden button creation.
			Control button = getButton(IDialogConstants.OK_ID);
			if(button != null) {
				button.setEnabled(errorMessage == null);
			}
		}
	}

	@Override
	protected Button getOKButton() {
		return okButton;
	}

	protected Object getInput() {
		return this.input;
	}

	protected IInputValidator getValidator() {
		return this.validator;
	}

	protected void enableOkButton(boolean enable) {
		Button okBtn = getButton(OK);
		if(okBtn != null) {
			okBtn.setEnabled(enable);
		}
	}

	@Override
	public void handleEvent(Event event) {
	}
}

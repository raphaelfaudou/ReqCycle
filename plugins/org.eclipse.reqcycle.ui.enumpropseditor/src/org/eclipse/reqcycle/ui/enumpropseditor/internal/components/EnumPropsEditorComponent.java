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
package org.eclipse.reqcycle.ui.enumpropseditor.internal.components;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditorComponent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class EnumPropsEditorComponent extends AbstractPropsEditorComponent<Enum<?>> {

	private ComboViewer comboViewer;

	public EnumPropsEditorComponent(EAttribute attribute, Composite parent, int style) {

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
				if(element instanceof EEnumLiteral) {
					return ((EEnumLiteral)element).getLiteral();
				}
				return super.getText(element);
			}
		});
		final EEnum eEnum = (EEnum)attribute.getEType();
		comboViewer.setInput(eEnum.getELiterals().toArray());
	}

	@Override
	public boolean isValid() {
		final IStructuredSelection selectedLiteral = (IStructuredSelection)((IStructuredSelection)comboViewer.getSelection()).getFirstElement();
		return selectedLiteral != null;
	}
}

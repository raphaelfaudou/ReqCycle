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
 *  Anass RADOUANI (AtoS) anass.radouani@atos.net - Initial API and implementation
 *
  *****************************************************************************/
package org.eclipse.reqcycle.repository.data.ui.dialog;

import java.util.Collection;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class AddAttributeDialog extends NameDialog {
	
	/**
	 * The popup bean
	 */
	public class Bean extends NameBean {
		
		private EDataType type;

		public Bean(Listener listener) {
			super(listener);
		}
		
		public EDataType getType() {
			return type;
		}

		public void setType(EDataType type) {
			this.type = type;
			listener.handleEvent(new Event());
		}
	}
	
	/** Attribute types */
	private Collection<EDataType> dataTypes;
	
	/** Attribute Types Combo Viewer */
	private ComboViewer cvAttribute;

	public AddAttributeDialog(Shell parentShell, String title, Collection<EDataType> dataTypes) {
		super(parentShell, title);
		setBean(new Bean(this));
		this.dataTypes = dataTypes;
	}

	@Override
	protected void doCreateDialogArea(Composite parent) {
			Label typelbl = new Label(parent, SWT.None);
			typelbl.setText("Type :");
			typelbl.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
			
			cvAttribute = new ComboViewer(parent);
			cvAttribute.getCombo().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
			cvAttribute.setLabelProvider(new LabelProvider(){
				@Override
				public String getText(Object element) {
					if(element instanceof EDataType) {
						String name = ((EDataType)element).getName();
						String nsURI = ((EDataType)element).getEPackage().getNsURI();
						if(EcoreFactory.eINSTANCE.getEPackage().getNsURI().equals(nsURI) && name.startsWith("E")) {
							name = name.replaceFirst("E", "");
						}
						return name + "  [" + nsURI + "]";
					}
					return super.getText(element);
				}
			});
			cvAttribute.setContentProvider(ArrayContentProvider.getInstance());
			cvAttribute.setInput(dataTypes);
	};
	
	@Override
	protected Button createButton(Composite parent, int id, String label, boolean defaultButton) {
		Button button = super.createButton(parent, id, label, defaultButton);
		if(OK == id) {
			button.setEnabled(false);
		}
		return button;
	}
	
	@Override
	public String getName() {
		return bean.getName();
	}

	@Override
	public boolean doHandleEvent(Event event) {
		if(((Bean)bean).getType() == null) {
			return false;
		}
		return true;
	}
	
	@Override
	protected void doInitDataBindings(DataBindingContext bindingContext) {
		IObservableValue observeSingleSelectionAttrCV = ViewerProperties.singleSelection().observe(cvAttribute);
		IObservableValue typeBeanObserveValue = PojoProperties.value("type").observe(bean);
		bindingContext.bindValue(observeSingleSelectionAttrCV, typeBeanObserveValue, null, null);
	}

	/**
	 * @return chosen attribue type
	 */
	public EDataType getType() {
		return ((Bean)bean).getType();
	}
}

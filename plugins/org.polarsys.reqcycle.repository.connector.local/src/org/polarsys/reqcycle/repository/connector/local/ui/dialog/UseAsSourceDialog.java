/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Anass Radouani (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.repository.connector.local.ui.dialog;

import javax.inject.Inject;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.dialogs.Dialog;
import org.polarsys.reqcycle.repository.data.IDataManager;
import org.polarsys.reqcycle.repository.data.IDataModelManager;
import org.polarsys.reqcycle.utils.inject.ZigguratInject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class UseAsSourceDialog extends Dialog {

	private DataBindingContext m_bindingContext;

	public Bean bean = new Bean();

	@Inject
	IDataModelManager dataModelManager;

	@Inject
	IDataManager dataManager;

	protected CreateNewSourceComposite compositeNewSource;



	public UseAsSourceDialog(Shell parentShell) {
		super(parentShell);
		ZigguratInject.inject(this);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite control = (Composite)super.createDialogArea(parent);

		compositeNewSource = new CreateNewSourceComposite(control, SWT.NONE);
		compositeNewSource.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		compositeNewSource.init(bean.getSourceName());

		m_bindingContext = initDataBindings();

		return control;
	}




	public void init(String name) {
		bean.setSourceName(name);
	}


	public static class Bean extends CreateNewSourceComposite.Bean {

	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTxtNameObserveWidget = WidgetProperties.text(SWT.Modify).observe(compositeNewSource.txtName);
		IObservableValue sourceNameBeanObserveValue = PojoProperties.value("sourceName").observe(bean);
		bindingContext.bindValue(observeTextTxtNameObserveWidget, sourceNameBeanObserveValue, null, null);
		//
		IObservableValue observeSingleSelectionCvDataModel = ViewerProperties.singleSelection().observe(compositeNewSource.cvDataModel);
		IObservableValue dataModelBeanObserveValue = PojoProperties.value("dataModel").observe(bean);
		bindingContext.bindValue(observeSingleSelectionCvDataModel, dataModelBeanObserveValue, null, null);
		//
		IObservableValue observeSingleSelectionCvScope = ViewerProperties.singleSelection().observe(compositeNewSource.cvScope);
		IObservableValue scopeBeanObserveValue = PojoProperties.value("scope").observe(bean);
		bindingContext.bindValue(observeSingleSelectionCvScope, scopeBeanObserveValue, null, null);
		//
		return bindingContext;
	}
}

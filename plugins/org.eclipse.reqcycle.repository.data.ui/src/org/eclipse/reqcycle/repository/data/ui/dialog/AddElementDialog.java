package org.eclipse.reqcycle.repository.data.ui.dialog;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;


public class AddElementDialog extends NameDialog {
	
	static enum TYPE {
		REQUIREMENT,
		ENUMERATION
	}
	
	public class Bean extends NameBean {
		
		private TYPE type;

		public Bean(Listener listener) {
			super(listener);
		}

		public void setType(TYPE type) {
			this.type = type;
			listener.handleEvent(new Event());
		}
		
		public TYPE getType() {
			return type;
		}
	}
	
	
	private Button reqRadio;
	private Button enumRadio;

	public AddElementDialog(Shell parentShell) {
		super(parentShell);
		setBean(new Bean(this));
	}

	@Override
	protected void doCreateDialogArea(Composite parent) {
		Label typelbl = new Label(parent, SWT.None);
		typelbl.setText("Type :");
		typelbl.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		
		Composite radioComposite = new Composite(parent, SWT.None);
		radioComposite.setLayout(new GridLayout(1, false));
		radioComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		
		reqRadio = new Button(radioComposite, SWT.RADIO);
		reqRadio.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		reqRadio.setText("Requirement");

		enumRadio = new Button(radioComposite, SWT.RADIO);
		enumRadio.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		enumRadio.setText("Enumeration");

		reqRadio.setSelection(true);
		((Bean)bean).setType(TYPE.REQUIREMENT);
	};
	
	
	public boolean isRequirement() {
		return TYPE.REQUIREMENT.equals(((Bean)bean).getType());
	}
	
	public boolean isEnumeration() {
		return TYPE.ENUMERATION.equals(((Bean)bean).getType());
	}
	
	protected void doInitDataBindings(DataBindingContext bindingContext) {
		//Create the Select Observable for our enum type  
		SelectObservableValue typeRadioObservable = new SelectObservableValue(TYPE.class);
		
		//bind the requirement radio button selection to the right enum value  
		IObservableValue btnRequirementObserverSelection = SWTObservables.observeSelection(reqRadio);
		typeRadioObservable.addOption(TYPE.REQUIREMENT, btnRequirementObserverSelection);
		
		//bind the enumeration radio button selection to the right enum value 
		IObservableValue btnEnumerationObserverSelection = SWTObservables.observeSelection(enumRadio);
		typeRadioObservable.addOption(TYPE.ENUMERATION, btnEnumerationObserverSelection);
		
		bindingContext.bindValue(typeRadioObservable, PojoObservables.observeValue(bean ,"type"));
	}

	@Override
	public boolean doHandleEvent(Event event) {
		if(((Bean)bean).getType() == null) {
			return false;
		}
		return true;
	}
	
}

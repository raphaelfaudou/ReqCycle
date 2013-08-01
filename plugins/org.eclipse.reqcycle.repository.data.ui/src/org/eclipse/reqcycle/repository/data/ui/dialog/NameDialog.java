package org.eclipse.reqcycle.repository.data.ui.dialog;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


public class NameDialog extends Dialog implements Listener {

	public class NameBean {
		
		protected Listener listener;
		private String name;

		public NameBean(Listener listener) {
			this.listener = listener;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
			listener.handleEvent(new Event());
		}
		
		protected Listener getListener() {
			return listener;
		}
	}
	
	protected NameBean bean;
	protected Text txtName;

	public NameDialog(Shell parentShell) {
		super(parentShell);
	}
	
	public NameDialog(Shell parentShell, NameBean bean){
		this(parentShell);
		this.bean = bean;
	}
	
	
	@Override
	protected Control createDialogArea(Composite parent) {
		
		Composite control = (Composite)super.createDialogArea(parent);
		
		Composite composite = new Composite(control, SWT.None);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		composite.setLayout(new GridLayout(2, false));
		
		Label lblName = new Label(composite, SWT.None);
		lblName.setText("Name :");
		
		txtName = new Text(composite, SWT.BORDER);
		txtName.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		doCreateDialogArea(composite);

		initDataBindings();
		
		return control;
	};
	
	
	/**
	 * Override this method to add custom Ui
	 * @param control
	 */
	protected void doCreateDialogArea(Composite control) {
	}

	@Override
	protected Button createButton(Composite parent, int id, String label, boolean defaultButton) {
		Button button = super.createButton(parent, id, label, defaultButton);
		if(OK == id) {
			button.setEnabled(false);
		}
		return button;
	}
	
	public String getName() {
		return bean.getName();
	}
	
	
	protected DataBindingContext initDataBindings() {
		if(bean == null) {
			bean = new NameBean(this);
		}
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTxtNameObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtName);
		IObservableValue nameBeanObserveValue = PojoProperties.value("name").observe(bean);
		bindingContext.bindValue(observeTextTxtNameObserveWidget, nameBeanObserveValue, null, null);
		//
		doInitDataBindings(bindingContext);
		//
		return bindingContext;
	}
	
	/**
	 * Override this method to add data binding
	 * @param bindingContext the binding context
	 */
	protected void doInitDataBindings(DataBindingContext bindingContext) {
	}

	
	private void enableOKbtn(boolean enable){
		Button okBtn = getButton(OK);
		if(okBtn != null) {
			okBtn.setEnabled(enable);
		}
	}

	@Override
	public void handleEvent(Event event) {
		String name = bean.getName();
		boolean enableOK = true;
		if(name == null || name.isEmpty() ) {
			enableOK = false;
		}
		
		if(!doHandleEvent(event)) {
			enableOK = false;
		}
		
		enableOKbtn(enableOK);
	}

	/**
	 * Override this method to handle change modification events
	 * 
	 * @param event the event which occurred
	 * @return return false if OK button have to be disabled
	 */
	public boolean doHandleEvent(Event event) {
		return true;
	}
	
	
	public void setBean(NameBean bean) {
		this.bean = bean;
	}

}

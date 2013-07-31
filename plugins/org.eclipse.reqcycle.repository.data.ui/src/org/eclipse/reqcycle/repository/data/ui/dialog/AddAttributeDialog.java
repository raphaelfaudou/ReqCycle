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
	
	private Collection<Object> types;
	private ComboViewer attrCV;

	public AddAttributeDialog(Shell parentShell, Collection<Object> types) {
		super(parentShell);
		setBean(new Bean(this));
		this.types = types;
	}

	@Override
	protected void doCreateDialogArea(Composite parent) {
			Label typelbl = new Label(parent, SWT.None);
			typelbl.setText("Type :");
			typelbl.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
			
			attrCV = new ComboViewer(parent);
			attrCV.getCombo().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
			attrCV.setLabelProvider(new LabelProvider(){
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
			attrCV.setContentProvider(ArrayContentProvider.getInstance());
			attrCV.setInput(types);
	};
	
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

	@Override
	public boolean doHandleEvent(Event event) {
		if(((Bean)bean).getType() == null) {
			return false;
		}
		return true;
	}
	
	
	protected void doInitDataBindings(DataBindingContext bindingContext) {
		IObservableValue observeSingleSelectionAttrCV = ViewerProperties.singleSelection().observe(attrCV);
		IObservableValue typeBeanObserveValue = PojoProperties.value("type").observe(bean);
		bindingContext.bindValue(observeSingleSelectionAttrCV, typeBeanObserveValue, null, null);
	}

	public EDataType getType() {
		return ((Bean)bean).getType();
	}
}

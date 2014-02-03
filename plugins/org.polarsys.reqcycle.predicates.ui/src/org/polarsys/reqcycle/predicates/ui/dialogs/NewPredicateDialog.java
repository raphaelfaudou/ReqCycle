package org.polarsys.reqcycle.predicates.ui.dialogs;

import java.util.regex.Pattern;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.window.Window;
import org.polarsys.reqcycle.predicates.core.api.IPredicate;
import org.polarsys.reqcycle.predicates.core.util.PredicatesUtil;
import org.polarsys.reqcycle.predicates.persistance.util.IPredicatesConfManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ziggurat.inject.ZigguratInject;


public class NewPredicateDialog extends TitleAreaDialog {

	private DataBindingContext m_bindingContext;

	private Text text;

	private ComboViewer comboViewer;

	private IContentProvider comboContentProvider;

	private ILabelProvider comboILabelProvider;

	private IInputValidator validator;

	private PredicateBean bean = new PredicateBean();

	IPredicatesConfManager predicatesConfManager = ZigguratInject.make(IPredicatesConfManager.class);

	private Combo combo;

	static class PredicateBean {

		private String name;

		private IPredicate rootPredicate;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public IPredicate getRootPredicate() {
			return rootPredicate;
		}

		public void setRootPredicate(IPredicate rootPredicate) {
			this.rootPredicate = rootPredicate;
		}
	}


	public NewPredicateDialog(Shell parentShell) {
		super(parentShell);
		this.validator = new IInputValidator() {

			@Override
			public String isValid(String newText) {
				final String regex = "\\w+[-\\w]*";
				if(newText == null || newText.isEmpty()) {
					return "Enter the name of the new predicate";
				} else if(!Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(newText).matches()) {
					return "The name of the predicate is not valid.";
				} else if(predicatesConfManager.isPredicateNameAlreadyUsed(newText)) {
					return "This predicate's name is already used.";
				}
				return null;
			}
		};
	}


	@Override
	protected Control createDialogArea(Composite parent) {
		// create composite
		Composite control = (Composite)super.createDialogArea(parent);

		Composite composite = new Composite(control, SWT.WRAP);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite.setLayout(new GridLayout(2, false));

		// create message
		Label label = new Label(composite, SWT.None);
		label.setText("Predicate name");
		label.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		label.setFont(parent.getFont());

		text = new Text(composite, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		text.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				validateInput();
			}
		});

		label = new Label(composite, SWT.WRAP);
		label.setText("Root Predicate");
		label.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		label.setFont(parent.getFont());

		comboViewer = new ComboViewer(composite, SWT.READ_ONLY);
		combo = comboViewer.getCombo();
		combo.setLayout(new GridLayout());
		combo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		if(this.comboContentProvider == null)
			this.comboContentProvider = ArrayContentProvider.getInstance();
		if(this.comboILabelProvider == null)
			this.comboILabelProvider = new LabelProvider();
		comboViewer.setContentProvider(comboContentProvider);
		comboViewer.setLabelProvider(comboILabelProvider);
		comboViewer.setInput(PredicatesUtil.getDefaultPredicates());
		comboViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				validateInput();
			}
		});


		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		final AdapterFactoryLabelProvider adapterLabelProvider = new AdapterFactoryLabelProvider(adapterFactory);

		comboViewer.setLabelProvider(new LabelProvider() {

			@Override
			public String getText(Object element) {
				return adapterLabelProvider.getText(element);
			}
		});

		// Set the error message text
		// See https://bugs.eclipse.org/bugs/show_bug.cgi?id=66292
		//        setErrorMessage(errorMessage);

		applyDialogFont(composite);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		m_bindingContext = initDataBindings();
		return composite;
	}


	protected void validateInput() {

		String errorMessage = null;
		if(validator != null && text != null) {
			errorMessage = validator.isValid(text.getText());
		}

		if(comboViewer != null && comboViewer.getSelection().isEmpty()) {
			String msg = "Select a root predicate";
			errorMessage = errorMessage != null && !errorMessage.isEmpty() ? errorMessage + "\n" + msg : msg;
		}

		setErrorMessage(errorMessage);
		Button okButton = getButton(OK);
		if(okButton != null) {
			okButton.setEnabled(errorMessage == null);
		}
	}

	@Override
	protected Button createButton(Composite parent, int id, String label, boolean defaultButton) {
		Button btn = super.createButton(parent, id, label, defaultButton);

		if(Window.OK == id && btn != null) {
			btn.setEnabled(false);
		}

		return btn;
	}

	public String getName() {
		return bean != null ? bean.getName() : null;
	}

	public IPredicate getRootPredicate() {
		return bean != null ? bean.getRootPredicate() : null;
	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(text);
		IObservableValue nameBeanObserveValue = PojoProperties.value("name").observe(bean);
		bindingContext.bindValue(observeTextTextObserveWidget, nameBeanObserveValue, null, null);
		//
		IObservableValue observeSingleSelectionComboViewer = ViewerProperties.singleSelection().observe(comboViewer);
		IObservableValue rootPredicateBeanObserveValue = PojoProperties.value("rootPredicate").observe(bean);
		bindingContext.bindValue(observeSingleSelectionComboViewer, rootPredicateBeanObserveValue, null, null);
		//
		return bindingContext;
	}
}

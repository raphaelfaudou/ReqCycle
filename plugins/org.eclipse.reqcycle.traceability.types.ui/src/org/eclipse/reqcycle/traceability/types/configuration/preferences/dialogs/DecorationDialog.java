package org.eclipse.reqcycle.traceability.types.configuration.preferences.dialogs;

import javax.inject.Inject;

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.reqcycle.predicates.core.api.IPredicate;
import org.eclipse.reqcycle.predicates.persistance.util.IPredicatesConfManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class DecorationDialog extends TitleAreaDialog {
	private Text textStyle;
	private String currentStyle = null;
	private String currentColor = null;
	private Combo predicateCombo;
	private IPredicate currentPredicate;
	private ComboViewer comboViewer;
	@Inject
	private IPredicatesConfManager predicateManager;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public DecorationDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected void okPressed() {
		if (currentColor == null || currentStyle == null
				|| currentPredicate == null) {
			setErrorMessage("Please fill all the fields");
			return;
		}
		super.okPressed();
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setMessage("Please choose the style applied to relations matching the chosen predicate");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(4, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label lblStyle = new Label(container, SWT.NONE);
		lblStyle.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblStyle.setText("style :");

		textStyle = new Text(container, SWT.BORDER);
		textStyle.setText("a quick brown fox jumps over the lazy dog");
		textStyle.setEditable(false);
		textStyle.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				2, 1));

		Button button_1 = new Button(container, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FontDialog font = new FontDialog(getShell());
				FontData result = font.open();
				if (result != null) {
					FontData[] list = font.getFontList();
					currentStyle = StringConverter.asString(list);
					JFaceResources.getFontRegistry().put(currentStyle, list);
					textStyle.setFont(JFaceResources.getFontRegistry().get(
							currentStyle));
					RGB rgb = font.getRGB();
					if (rgb != null) {
						currentColor = StringConverter.asString(rgb);
						JFaceResources.getColorRegistry()
								.put(currentColor, rgb);
						textStyle.setForeground(JFaceResources
								.getColorRegistry().get(currentColor));
					}
				}
			}
		});
		button_1.setText("...");

		Label lblPredicate = new Label(container, SWT.NONE);
		lblPredicate.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblPredicate.setText("predicate : ");

		comboViewer = new ComboViewer(container, SWT.READ_ONLY);
		predicateCombo = comboViewer.getCombo();
		predicateCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 3, 1));
		comboViewer.setContentProvider(ArrayContentProvider.getInstance());
		comboViewer.setLabelProvider(new AdapterFactoryLabelProvider(
				new ComposedAdapterFactory(
						ComposedAdapterFactory.Descriptor.Registry.INSTANCE)));
		comboViewer.setInput(predicateManager.getPredicates(true));
		comboViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {

					@Override
					public void selectionChanged(SelectionChangedEvent event) {
						if (comboViewer.getSelection() instanceof IStructuredSelection) {
							IStructuredSelection structured = (IStructuredSelection) comboViewer
									.getSelection();
							if (structured.getFirstElement() instanceof IPredicate) {
								IPredicate p = (IPredicate) structured
										.getFirstElement();
								currentPredicate = p;
								return;
							}
						}
						currentPredicate = null;
					}
				});
		return area;
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}

	public String getStyle() {
		return currentStyle;
	}

	public String getColor() {
		return currentColor;
	}

	public IPredicate getPredicate() {
		return currentPredicate;
	}

}

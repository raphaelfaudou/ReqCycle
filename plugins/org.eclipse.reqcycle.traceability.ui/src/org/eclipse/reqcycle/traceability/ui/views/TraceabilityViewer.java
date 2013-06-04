package org.eclipse.reqcycle.traceability.ui.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.agesys.inject.AgesysInject;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.reqcycle.dnd.DNDReqCycle;
import org.eclipse.reqcycle.traceability.builder.IBuildingTraceabilityEngine;
import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine.DIRECTION;
import org.eclipse.reqcycle.traceability.engine.Request;
import org.eclipse.reqcycle.traceability.engine.Request.DEPTH;
import org.eclipse.reqcycle.traceability.model.scopes.CompositeScope;
import org.eclipse.reqcycle.traceability.model.scopes.Scopes;
import org.eclipse.reqcycle.traceability.types.ITypesConfigurationProvider;
import org.eclipse.reqcycle.traceability.types.conditions.TypeConditions;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Configuration;
import org.eclipse.reqcycle.traceability.types.scopes.ConfigurationScope;
import org.eclipse.reqcycle.traceability.ui.Activator;
import org.eclipse.reqcycle.traceability.ui.TraceabilityUtils;
import org.eclipse.reqcycle.traceability.ui.providers.RequestContentProvider;
import org.eclipse.reqcycle.traceability.ui.providers.RequestLabelProvider;
import org.eclipse.reqcycle.types.IType;
import org.eclipse.reqcycle.types.ITypesManager;
import org.eclipse.reqcycle.types.ui.providers.IterableOfTypesContentProvider;
import org.eclipse.reqcycle.types.ui.providers.TypeLabelProvider;
import org.eclipse.reqcycle.uri.IReachableManager;
import org.eclipse.reqcycle.uri.exceptions.IReachableHandlerException;
import org.eclipse.reqcycle.uri.model.IObjectHandler;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.reqcycle.uri.model.ReachableObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.part.PluginTransfer;
import org.eclipse.ui.part.PluginTransferData;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.ResourceManager;

public class TraceabilityViewer extends ViewPart implements ISelectionListener {

	private static final String PLUGIN_ID = Activator.PLUGIN_ID;
	public static final String ID = "org.eclipse.reqcycle.traceability.ui.views.TraceabilityViewer"; //$NON-NLS-1$
	private final FormToolkit formToolkit = new FormToolkit(
			Display.getDefault());
	private Text sourceText;
	private Text targetText;
	private List<Reachable> sources = new LinkedList<Reachable>();
	private Object target;
	private ISelection selection;
	private TreeViewer treeViewer;
	private ComboViewer comboViewer;
	private DIRECTION direction;
	private RequestContentProvider contentProvider;
	private TreeViewer listOfTypesViewer;
	private ITypesManager manager = AgesysInject.make(ITypesManager.class);
	private ITypesConfigurationProvider typeProvider = AgesysInject
			.make(ITypesConfigurationProvider.class);
	private IReachableManager reachManager = AgesysInject
			.make(IReachableManager.class);
	private ComboViewer comboConfViewer;

	public TraceabilityViewer() {
	}

	@Override
	public void dispose() {
		super.dispose();
		PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getSelectionService().removeSelectionListener(this);
		contentProvider.dispose();
	}

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getSelectionService().addSelectionListener(this);
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));

		Form frmNewForm = formToolkit.createForm(container);
		formToolkit.paintBordersFor(frmNewForm);
		frmNewForm.setText("Traceability");
		frmNewForm.getBody().setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite_3 = formToolkit.createComposite(
				frmNewForm.getBody(), SWT.NONE);
		formToolkit.paintBordersFor(composite_3);
		composite_3.setLayout(new GridLayout(1, false));

		Composite compoTrac = formToolkit
				.createComposite(composite_3, SWT.NONE);
		compoTrac.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,
				1));
		formToolkit.paintBordersFor(compoTrac);
		compoTrac.setLayout(new FillLayout(SWT.HORIZONTAL));

		Section sctTraceability = formToolkit.createSection(compoTrac,
				Section.EXPANDED);
		formToolkit.paintBordersFor(sctTraceability);
		sctTraceability.setText("Path");

		Composite composite_1 = formToolkit.createComposite(sctTraceability,
				SWT.NONE);
		formToolkit.paintBordersFor(composite_1);
		sctTraceability.setClient(composite_1);
		composite_1.setLayout(new GridLayout(1, false));

		treeViewer = new TreeViewer(composite_1, SWT.BORDER);
		treeViewer.setUseHashlookup(true);
		contentProvider = new RequestContentProvider();
		RequestLabelProvider labelProvider = new RequestLabelProvider();
		AgesysInject.inject(labelProvider, contentProvider);
		treeViewer.setContentProvider(contentProvider);
		treeViewer.setLabelProvider(labelProvider);
		Tree tree = treeViewer.getTree();
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		formToolkit.paintBordersFor(tree);

		Composite composite_2 = formToolkit.createComposite(composite_1,
				SWT.NONE);
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false, 1, 1));
		formToolkit.paintBordersFor(composite_2);
		composite_2.setLayout(new GridLayout(1, false));

		Label lblNewLabel = new Label(composite_2, SWT.NONE);
		formToolkit.adapt(lblNewLabel, true, true);
		lblNewLabel.setText("Type(s) of selections");

		listOfTypesViewer = new TreeViewer(composite_2, SWT.BORDER
				| SWT.V_SCROLL);
		listOfTypesViewer
				.setContentProvider(new IterableOfTypesContentProvider());
		listOfTypesViewer.setLabelProvider(new TypeLabelProvider());
		org.eclipse.swt.widgets.Tree listOfTypes = listOfTypesViewer.getTree();
		listOfTypes.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false,
				1, 1));

		Button btnRefresh = formToolkit.createButton(sctTraceability, "",
				SWT.NONE);
		btnRefresh.setImage(ResourceManager.getPluginImage(PLUGIN_ID,
				"icons/update.gif"));
		btnRefresh.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection sel = (IStructuredSelection) comboViewer
						.getSelection();
				if (sel != null && !sel.isEmpty() && !sources.isEmpty()) {
					setInput();
				}
			}

		});
		sctTraceability.setTextClient(btnRefresh);

		Composite compoParam = formToolkit.createComposite(composite_3,
				SWT.NONE);
		compoParam.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false,
				1, 1));
		formToolkit.paintBordersFor(compoParam);
		compoParam.setLayout(new FillLayout(SWT.HORIZONTAL));

		Section sctnParameters = formToolkit.createSection(compoParam,
				Section.EXPANDED | Section.TWISTIE);
		formToolkit.paintBordersFor(sctnParameters);
		sctnParameters.setText("Parameters");

		Composite composite = formToolkit.createComposite(sctnParameters,
				SWT.NONE);
		formToolkit.paintBordersFor(composite);
		sctnParameters.setClient(composite);
		composite.setLayout(new GridLayout(5, false));

		Label lblSource = formToolkit.createLabel(composite, "source :",
				SWT.NONE);
		lblSource.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));

		sourceText = formToolkit.createText(composite, "", SWT.NONE);
		sourceText.setEditable(false);
		sourceText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 2, 1));
		createDropTarget(sourceText);

		Button btnNewButton = formToolkit.createButton(composite, "", SWT.NONE);
		btnNewButton.setImage(ResourceManager.getPluginImage(PLUGIN_ID,
				"icons/add_obj.gif"));
		btnNewButton.setToolTipText("Set selection as source");
		btnNewButton.addSelectionListener(new AddSelectionListener(
				new SourceSetter()));

		Button btnNewButton_1 = formToolkit.createButton(composite, "",
				SWT.NONE);
		btnNewButton_1.setImage(ResourceManager.getPluginImage(PLUGIN_ID,
				"icons/delete_obj.gif"));
		btnNewButton_1.setToolTipText("delete selection");
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sources.clear();
				sourceText.setText("");
			}
		});

		Label lblTarget = formToolkit.createLabel(composite, "target :",
				SWT.NONE);
		lblTarget.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));

		targetText = formToolkit.createText(composite, "New Text", SWT.NONE);
		targetText.setEditable(false);
		targetText.setText("");
		targetText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		createDropTarget(targetText);
		Button btnChooseType = formToolkit.createButton(composite, "...",
				SWT.NONE);
		btnChooseType.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				ElementTreeSelectionDialog d = new ElementTreeSelectionDialog(
						Display.getDefault().getActiveShell(),
						new TypeLabelProvider(),
						new IterableOfTypesContentProvider());
				d.setInput(manager.getAllTypes());
				if (d.open() == ElementTreeSelectionDialog.OK) {
					IType firstResult = (IType) d.getFirstResult();
					target = firstResult;
					targetText.setText(firstResult.getLabel());
				}
			}

		});
		Button btnNewButton_2 = formToolkit.createButton(composite, "",
				SWT.NONE);
		btnNewButton_2.setImage(ResourceManager.getPluginImage(PLUGIN_ID,
				"icons/add_obj.gif"));
		btnNewButton_2.addSelectionListener(new AddSelectionListener(
				new TargetSetter()));

		Button btnNewButton_3 = formToolkit.createButton(composite, "",
				SWT.NONE);
		btnNewButton_3.setImage(ResourceManager.getPluginImage(PLUGIN_ID,
				"icons/delete_obj.gif"));
		btnNewButton_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				target = null;
				targetText.setText("");
			}
		});

		Label lblDirection = formToolkit.createLabel(composite, "direction :",
				SWT.NONE);
		lblDirection.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));

		comboViewer = new ComboViewer(composite, SWT.READ_ONLY);
		Combo combo = comboViewer.getCombo();
		comboViewer.setContentProvider(new ArrayContentProvider());
		comboViewer.setLabelProvider(new LabelProvider() {

			@Override
			public String getText(Object element) {
				return super.getText(element.toString());
			}

		});
		comboViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {

					@Override
					public void selectionChanged(SelectionChangedEvent event) {
						direction = (DIRECTION) ((IStructuredSelection) event
								.getSelection()).getFirstElement();
					}
				});
		comboViewer.setInput(Arrays
				.asList(DIRECTION.UPWARD, DIRECTION.DOWNWARD));
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 4,
				1));
		formToolkit.paintBordersFor(combo);

		Label lblConfiguration = new Label(composite, SWT.NONE);
		lblConfiguration.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
				false, false, 1, 1));
		formToolkit.adapt(lblConfiguration, true, true);
		lblConfiguration.setText("configuration :");

		comboConfViewer = new ComboViewer(composite, SWT.READ_ONLY);
		Combo comboConf = comboConfViewer.getCombo();
		comboConf.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				4, 1));
		comboConfViewer.setLabelProvider(new LabelProvider() {

			@Override
			public String getText(Object element) {
				if (element == "") {
					return "";
				}
				if (element instanceof Configuration) {
					Configuration conf = (Configuration) element;
					return conf.getName();
				}
				return super.getText(element);
			}

		});
		comboConfViewer.setContentProvider(new ArrayContentProvider());
		List<Object> input = new LinkedList<Object>();
		input.add("");
		input.addAll(typeProvider.getContainer().getConfigurations());
		comboConfViewer.setInput(input);
		formToolkit.paintBordersFor(comboConf);
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				Object firstElement = ((IStructuredSelection) event
						.getSelection()).getFirstElement();
				if (firstElement instanceof Reachable) {
					Reachable reachable = (Reachable) firstElement;
					listOfTypesViewer.setInput(manager
							.getAllApplicableTypes(reachable));
				} else {
					listOfTypesViewer.setInput(null);
				}
			}
		});
		createActions();
		initializeToolBar();
		initializeMenu();
	}

	private void createDropTarget(Text text) {
		DropTarget target = new DropTarget(text, DND.DROP_DEFAULT
				| DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_LINK);
		target.setTransfer(new Transfer[] {
				LocalSelectionTransfer.getTransfer(),
				PluginTransfer.getInstance() });
		target.addDropListener(new DropTargetAdapter() {

			@Override
			public void dragEnter(DropTargetEvent event) {
				super.dragEnter(event);
				for (int i = 0; i < event.dataTypes.length; i++) {
					if (LocalSelectionTransfer.getTransfer().isSupportedType(
							event.dataTypes[i])) {
						event.currentDataType = event.dataTypes[i];
						break;
					}
				}
			}

			@Override
			public void drop(DropTargetEvent event) {
				super.drop(event);
				List<Reachable> reachables = new ArrayList<Reachable>();
				if (PluginTransfer.getInstance().isSupportedType(
						event.currentDataType)) {
					PluginTransferData ptd = (PluginTransferData) event.data;
					reachables = DNDReqCycle.getReachables(ptd.getData());
					if (!reachables.isEmpty()) {
						for (Reachable r : reachables) {
							ISetter setter = null;
							if (event.item == sourceText) {
								setter = new SourceSetter();
							} else {
								setter = new TargetSetter();
							}
							setter.set(r);
						}
					}
				}
				if (LocalSelectionTransfer.getTransfer().isSupportedType(
						event.currentDataType)) {
					if (event.data instanceof IStructuredSelection) {
						IStructuredSelection structured = (IStructuredSelection) event.data;
						for (Iterator i = structured.iterator(); i.hasNext();) {
							Object o = i.next();
							Reachable r = getReachable(o);
							if (r != null) {
								reachables.add(r);
							}
						}
					}
				}
				Widget widget = event.widget;
				if (widget instanceof DropTarget) {
					DropTarget drop = (DropTarget) widget;
					ISetter setter = null;
					if (drop.getControl() == sourceText) {
						setter = new SourceSetter();
					} else {
						setter = new TargetSetter();
					}
					if (reachables != null && !reachables.isEmpty()) {
						for (Reachable r : reachables) {
							setter.set(r);
						}
					} else {
						setter.set(null);
					}
				}

			}

			@Override
			public void dragOver(DropTargetEvent event) {
				// TODO Auto-generated method stub
				super.dragOver(event);
			}

			@Override
			public void dropAccept(DropTargetEvent event) {
				super.dropAccept(event);
			}

		});
	}

	public void setInput() {
		CompositeScope scope = new CompositeScope();
		scope.add(Scopes.getWorkspaceScope());
		scope.add(new ConfigurationScope());
		Request request = new Request()
				.setDirection(direction)
				.setScope(scope)
				.setDepth(DEPTH.ONE)
				.addProperty(IBuildingTraceabilityEngine.OPTION_CHECK_CACHE,
						false)
				.addProperty(
						RequestContentProvider.CONF_KEY,
						((IStructuredSelection) comboConfViewer.getSelection())
								.getFirstElement());
		if (target == null) {
			for (Reachable r : sources) {
				request.addSource(r);
			}
		} else {
			if (target instanceof Reachable) {
				request.addSourceAndTarget(sources.iterator().next(),
						(Reachable) target);
			} else if (target instanceof IType) {
				request.addSourceAndCondition(sources.iterator().next(),
						TypeConditions.is((IType) target));
			}
		}
		treeViewer.setInput(request);
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	private class AddSelectionListener implements SelectionListener {

		private ISetter setter;

		public AddSelectionListener(ISetter s) {
			this.setter = s;
		}

		@Override
		public void widgetSelected(SelectionEvent e) {
			if (selection != null && !selection.isEmpty()) {
				if (selection instanceof IStructuredSelection) {
					IStructuredSelection structured = (IStructuredSelection) selection;
					for (Iterator<Object> i = structured.iterator(); i
							.hasNext();) {
						Object o = i.next();
						if (o == null) {
							setter.set(null);
						} else {
							setter.set(getReachable(o));
						}

					}
				}
			}

		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			widgetSelected(e);
		}

	}

	private interface ISetter {
		void set(Reachable r);
	}

	private class SourceSetter implements ISetter {

		@Override
		public void set(Reachable r) {
			if (r == null) {
				sourceText
						.setText(sourceText.getText().length() > 0 ? sourceText
								.getText() + ", " : "" + "invalid selection");
			} else {
				if (target != null) {
					new TargetSetter().set(null);
				}
				if (!sources.contains(r)) {
					sources.add(r);
					sourceText
							.setText((sourceText.getText().length() > 0 ? sourceText
									.getText() + ", "
									: "")
									+ TraceabilityUtils.getText(r));
				}

			}

		}
	}

	private class TargetSetter implements ISetter {

		@Override
		public void set(Reachable r) {
			if (sources.size() > 1) {
				targetText.setText("impossible with several sources");
				target = null;
			} else if (r == null) {
				targetText.setText("invalid selection");
				target = null;
			} else {
				target = r;
				targetText.setText(TraceabilityUtils.getText(r));
			}

		}
	}

	public Reachable getReachable(Object o) {
		try {
			IObjectHandler handler = reachManager.getHandlerFromObject(o);
			ReachableObject reachableObject = handler.getFromObject(o);
			if (reachableObject != null) {
				return reachableObject.getReachable(o);
			}
		} catch (IReachableHandlerException e) {
		}
		return null;
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		this.selection = selection;
	}
}

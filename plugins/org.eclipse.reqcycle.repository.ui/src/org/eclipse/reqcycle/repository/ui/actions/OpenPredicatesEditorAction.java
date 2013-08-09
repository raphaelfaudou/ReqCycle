package org.eclipse.reqcycle.repository.ui.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.inject.Inject;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.reqcycle.core.ILogger;
import org.eclipse.reqcycle.predicates.core.api.IPredicate;
import org.eclipse.reqcycle.predicates.core.util.PredicatesUtil;
import org.eclipse.reqcycle.predicates.ui.presentation.PredicatesEditor;
import org.eclipse.reqcycle.ui.components.dialogs.ComboInputDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ziggurat.inject.ZigguratInject;

import DataModel.RequirementSource;

public class OpenPredicatesEditorAction extends Action {

	private final ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

	private final AdapterFactoryLabelProvider adapterLabelProvider = new AdapterFactoryLabelProvider(adapterFactory);

	/** Requirement repositories TreeViewer. */
	private final TreeViewer viewer;

	@Inject
	private ILogger logger = ZigguratInject.make(ILogger.class);

	public OpenPredicatesEditorAction(final TreeViewer treeViewer) {
		this.viewer = treeViewer;
	}

	@Override
	public void run() {
		ISelection selection = viewer.getSelection();
		if(selection instanceof IStructuredSelection) {

			final Collection<RequirementSource> selectedReqSources = new ArrayList<RequirementSource>();
			for(Iterator<?> iterator = ((IStructuredSelection)selection).iterator(); iterator.hasNext();) {
				Object obj = iterator.next();
				if(obj instanceof RequirementSource) {
					selectedReqSources.add((RequirementSource)obj);
				}
			}
			// The set of EClass to pass to the Predicates Editor.
			final Set<EClass> eClasses = new HashSet<EClass>();
			for(Iterator<RequirementSource> iterator = selectedReqSources.iterator(); iterator.hasNext();) {
				RequirementSource reqSource = (RequirementSource)iterator.next();
				eClasses.addAll(((RequirementSource)reqSource).getTargetEPackage());
			}

			try {
				IPredicate selectedPredicate = selectRootPredicate();
				if(selectedPredicate != null) {
					PredicatesEditor.openEditor(eClasses, selectedPredicate);
				}

			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Unable to open the Predicates Editor : " + e.toString());
				logger.error(e.toString());
			}
		}
	}

	private IPredicate selectRootPredicate() {
		Display display = Display.getCurrent();
		if(display == null)
			display = Display.getDefault();
		ComboInputDialog dialog = new ComboInputDialog(display.getActiveShell(), "Root Predicate", "Select a root predicate", PredicatesUtil.getDefaultPredicates(), null);
		dialog.setComboLabelProvider(new LabelProvider() {

			@Override
			public String getText(Object element) {
				return adapterLabelProvider.getText(element);
			}
		});
		if(Window.OK == dialog.open()) {
			return (IPredicate)dialog.getSelectedItem();
		}
		return null;
	}

}

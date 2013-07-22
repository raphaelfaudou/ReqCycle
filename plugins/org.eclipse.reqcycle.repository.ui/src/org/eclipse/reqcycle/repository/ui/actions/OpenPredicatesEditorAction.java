package org.eclipse.reqcycle.repository.ui.actions;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

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
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ziggurat.inject.ZigguratInject;

import DataModel.RequirementSource;

public class OpenPredicatesEditorAction extends Action {

    private final ComposedAdapterFactory      adapterFactory       = new ComposedAdapterFactory(
                                                                           ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

    private final AdapterFactoryLabelProvider adapterLabelProvider = new AdapterFactoryLabelProvider(adapterFactory);

    /** Requirement repositories TreeViewer. */
    private final TreeViewer                  viewer;

    @Inject
    private ILogger                           logger               = ZigguratInject.make(ILogger.class);

    public OpenPredicatesEditorAction(final TreeViewer treeViewer) {
        this.viewer = treeViewer;
    }

    @Override
    public void run() {
        ISelection selection = viewer.getSelection();
        if (selection instanceof IStructuredSelection) {

            final Object obj = ((IStructuredSelection) selection).getFirstElement();
            if (obj instanceof RequirementSource) {
                final Collection<EClass> eClasses = ((RequirementSource) obj).getTargetEPackage();
                try {
                    IPredicate selectedPredicate = selectRootPredicate();
                    if (selectedPredicate != null) {
                        openEditor(eClasses, selectedPredicate);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("Unable to open the Predicates Editor : " + e.toString());
                    logger.error(e.toString());
                }
            }
        }
    }

    private IPredicate selectRootPredicate() {
        Display display = Display.getCurrent();
        if (display == null) display = Display.getDefault();
        ComboInputDialog dialog = new ComboInputDialog(display.getActiveShell(), "Root Predicate",
                "Select a root predicate", PredicatesUtil.getDefaultPredicates(), null);
        dialog.setComboLabelProvider(new LabelProvider() {
            @Override
            public String getText(Object element) {
                return adapterLabelProvider.getText(element);
            }
        });
        if (Window.OK == dialog.open()) {
            return (IPredicate) dialog.getSelectedItem();
        }
        return null;
    }

    public void openEditor(Object input, IPredicate rootPredicate) {
        try {
            final File f = File.createTempFile("predicate", ".predicates");
            Runtime.getRuntime().addShutdownHook(new ShutDownHook(f));

            IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
            PredicatesEditor editor = (PredicatesEditor) IDE.openEditor(page, f.toURI(), PredicatesEditor.ID, true);
            editor.setRootPredicate(rootPredicate);
            editor.setInput(input);
            editor.hideButtonLoadModel();

        } catch (PartInitException e) {
            e.printStackTrace();
            logger.error("Unable to open the predicates Editor : " + e.getMessage());
            logger.error(e.toString());

        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Unable to create the predicates temporary file : " + e.getMessage());
            logger.error(e.toString());
        }
    }

    private static class ShutDownHook extends Thread {

        private final File file;

        public ShutDownHook(File file) {
            super();
            this.file = file;
        }

        @Override
        public void run() {
            try {
                if (this.file.exists()) this.file.delete();
            } catch (Exception e) {
            }
        }
    }

}

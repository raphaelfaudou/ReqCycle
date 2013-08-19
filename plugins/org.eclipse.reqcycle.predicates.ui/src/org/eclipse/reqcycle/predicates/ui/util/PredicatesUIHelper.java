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
 *  Papa Issa DIAKHATE (AtoS) papa-issa.diakhate@atos.net - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.reqcycle.predicates.ui.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.reqcycle.core.ILogger;
import org.eclipse.reqcycle.predicates.core.api.IPredicate;
import org.eclipse.reqcycle.predicates.core.util.PredicatesUtil;
import org.eclipse.reqcycle.predicates.persistance.util.IPredicatesConfManager;
import org.eclipse.reqcycle.predicates.ui.dialogs.AbstractCustomDialog;
import org.eclipse.reqcycle.predicates.ui.dialogs.CheckBoxInputDialog;
import org.eclipse.reqcycle.predicates.ui.dialogs.ComboInputDialog;
import org.eclipse.reqcycle.predicates.ui.presentation.PredicatesEditor;
import org.eclipse.reqcycle.predicates.ui.providers.PredicatesTableLabelProvider;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ziggurat.inject.ZigguratInject;

/**
 * This class contains common UI utilities related to predicates such as for example : opening a dialog chooser in order
 * to select desired predicates.
 * 
 * @author Papa Issa DIAKHATE
 */
public class PredicatesUIHelper {

	static IPredicatesConfManager predicatesConfManager = ZigguratInject.make(IPredicatesConfManager.class);
	
	static ILogger logger = ZigguratInject.make(ILogger.class);

	private PredicatesUIHelper() {
	}

	/**
	 * Opens a dialog which proposes to select a predicate to apply for filtering.
	 * 
	 * @param preSelection predicates to preselect
	 * 
	 * @return The selected predicates or an empty list if nothing is selected and null if cancelled.
	 */
	public static Collection<IPredicate> openPredicatesChooser(Collection<IPredicate> preSelection, String title, String msg, Boolean multiSelection) {
		Display display = Display.getCurrent();
		if(display == null)
			display = Display.getDefault();


		Collection<IPredicate> storedPredicates = predicatesConfManager.getPredicates(false);
		AbstractCustomDialog dialog;
		if(multiSelection) {
			dialog = new CheckBoxInputDialog(display.getActiveShell(), title, //$NON-NLS-1$
			msg, storedPredicates, null, (Collection)preSelection); //$NON-NLS-1$
			((CheckBoxInputDialog)dialog).setLabelProvider(new PredicatesTableLabelProvider());
		} else {
			dialog = new ComboInputDialog(display.getActiveShell(), title, msg, storedPredicates, null, true);
			((ComboInputDialog)dialog).setLabelProvider(new LabelProvider() {
				@Override
				public String getText(Object element) {
					if(element instanceof IPredicate) {
						return ((IPredicate)element).getDisplayName();
					}
					return super.getText(element);
				}
			});
		}
		if(Window.OK == dialog.open()) {
			Collection<IPredicate> predicates = new ArrayList<IPredicate>();
			for(Object obj : dialog.getSelectedItems()) {
				if(obj instanceof IPredicate) {
					predicates.add((IPredicate)obj);
				}
			}
			return predicates;
		}
		return null;
	}
	
	/**
	 * Gets root predicate
	 * 
	 * Open a Dialog to ask the user the root predicate
	 * 
	 * @return the root predicate or null
	 */
	public static IPredicate selectRootPredicate() {
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		final AdapterFactoryLabelProvider adapterLabelProvider = new AdapterFactoryLabelProvider(adapterFactory);

		Display display = Display.getCurrent();
		if(display == null)
			display = Display.getDefault();
		ComboInputDialog dialog = new ComboInputDialog(display.getActiveShell(), "Root Predicate", "Select a root predicate", PredicatesUtil.getDefaultPredicates(), null);
		dialog.setLabelProvider(new LabelProvider() {

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

	public static void openNewPredicateEditor() {
		IPredicate selectedPredicate = selectRootPredicate();
		if(selectedPredicate != null) {
			openEditor(Collections.emptyList(), selectedPredicate);
		}
	}
	
	public static void editPredicate() {
		Collection<IPredicate> predicates = openPredicatesChooser(null,"","", false);
		if(predicates != null && !predicates.isEmpty()) {
			openEditor(null, predicates.iterator().next());
		}
	}
	
	/**
	 * Opens a new predicates editor.
	 * 
	 * @param input
	 *        - The input of the editor. Typically a Collection of EClass objects. (The EClass of the model to
	 *        edit/to which the predicates are to applied).
	 * @param rootPredicate
	 *        - The root predicate of the tree.
	 */
	public static void openEditor(final Object input, final IPredicate rootPredicate) {
		try {
			final File f = File.createTempFile("predicate", ".predicates");
			Runtime.getRuntime().addShutdownHook(new ShutDownHook(f));

			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			PredicatesEditor editor = (PredicatesEditor)IDE.openEditor(page, f.toURI(), PredicatesEditor.ID, true);
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
				if(this.file.exists())
					this.file.delete();
			} catch (Exception e) {
			}
		}
	}
	
}

/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Papa Issa Diakhate (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.predicates.ui.listeners;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.polarsys.reqcycle.predicates.core.api.CompositePredicate;
import org.polarsys.reqcycle.predicates.core.api.IEAttrPredicate;
import org.polarsys.reqcycle.predicates.core.api.ITypedPredicate;
import org.polarsys.reqcycle.predicates.core.util.PredicatesUtil;
import org.polarsys.reqcycle.predicates.persistance.util.IPredicatesConfManager;
import org.polarsys.reqcycle.predicates.ui.dialogs.IEAttrPredicatesNodeEditorDialog;
import org.polarsys.reqcycle.predicates.ui.presentation.PredicatesEditor;
import org.polarsys.reqcycle.ui.eattrpropseditor.api.IEditionResult;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ziggurat.inject.ZigguratInject;

public class PredicatesTreeDoubleClickListener implements IDoubleClickListener {

	private final PredicatesEditor editor;

	private Collection<EClass> eClasses;

	private boolean useExtendedFeature;

	IPredicatesConfManager manager = ZigguratInject.make(IPredicatesConfManager.class);

	/**
	 * @param editor
	 *        - The PredicatesEditor. Must not be <tt>null</tt>.
	 * @param eClassesOfModelToEdit
	 *        - The collection of EClass.
	 * @param useExtendedFeature
	 */
	public PredicatesTreeDoubleClickListener(final PredicatesEditor editor, final Collection<EClass> eClassesOfModelToEdit, final boolean useExtendedFeature) {
		this.editor = editor;
		this.eClasses = eClassesOfModelToEdit;
		this.useExtendedFeature = useExtendedFeature;
	}

	@Override
	public void doubleClick(DoubleClickEvent event) {
		final Shell parent = event.getViewer().getControl().getShell();
		final IStructuredSelection selection = (IStructuredSelection)event.getSelection();
		if(selection.getFirstElement() instanceof CompositePredicate) {
			MessageDialog.openInformation(parent, "Info", "Unable to edit a Composite Predicate.");
			return; // quit
		}
		if(this.eClasses == null || this.eClasses.isEmpty()) {
			MessageDialog.openError(parent, "Error", "You must load a model to edit.");
			return; // quit
		}
		if(selection.getFirstElement() instanceof ITypedPredicate) {
			ITypedPredicate<?> predicate = (ITypedPredicate<?>)selection.getFirstElement();
			if(predicate instanceof IEAttrPredicate) {
				final IEAttrPredicatesNodeEditorDialog dialog = new IEAttrPredicatesNodeEditorDialog(parent, (IEAttrPredicate)predicate, this.eClasses, this.useExtendedFeature);
				if(dialog.open() == Window.OK) {

					if(selection.getFirstElement() instanceof IEAttrPredicate) {

						final IEditionResult editionResult = dialog.getEditionResult();
						final IEAttrPredicate predicatToEdit = (IEAttrPredicate)editionResult.getEObjectToEdit();
						Iterator<Entry<EStructuralFeature, Object>> iter = editionResult.getEditedEntries().entrySet().iterator();

						while(iter.hasNext()) {
							final Entry<EStructuralFeature, Object> entry = iter.next();
							updatePredicate(predicatToEdit, entry.getKey(), entry.getValue());
						}
					}
				}
			} else {
				MessageDialog.openError(parent, "Error", "Only IEAttrPredicate is currently supported.");
			}
		}
	}

	private void updatePredicate(ITypedPredicate<?> predicate, EStructuralFeature feature, Object value) {

		final String featureName = feature.getName();

		if(feature instanceof EReference) {
			throw new UnsupportedOperationException("Only attribute are currently supported ... ");

		} else if(feature instanceof EAttribute) {
			if(predicate.eClass().getEAttributes().contains(feature)) {
				predicate.eSet(predicate.eClass().getEStructuralFeature(featureName), value);
			} else {
				// This is an attribute from the model for which we are going to apply the predicate.
				predicate.eSet(getPredicateEStructuralFeature(predicate, "typedElement"), feature);
				predicate.eSet(PredicatesUtil.getUserSpecificInputAttribute(predicate), value);
			}
			// The editor must be dirty now !
			this.editor.setDirty(true);
		}
	}

	/**
	 * @param typedPredicate
	 * @return The {@link EStructuralFeature} of the specified typed predicate.
	 */
	private static EStructuralFeature getPredicateEStructuralFeature(final ITypedPredicate<?> typedPredicate, String attributeName) {
		return typedPredicate.eClass().getEStructuralFeature(attributeName);
	}

	public void setEClasses(Collection<EClass> eClasses) {
		this.eClasses = eClasses;
	}

	public void setUseExtendedFeature(boolean useExtendedFeature) {
		this.useExtendedFeature = useExtendedFeature;
	}

}

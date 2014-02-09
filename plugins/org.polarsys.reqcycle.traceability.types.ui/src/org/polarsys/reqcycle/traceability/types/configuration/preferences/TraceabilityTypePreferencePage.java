/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.types.configuration.preferences;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.polarsys.reqcycle.traceability.types.configuration.predicates.ReqCycleDynamicPackage;
import org.polarsys.reqcycle.traceability.types.configuration.preferences.dialogs.NewAttributeDialog;
import org.polarsys.reqcycle.traceability.types.configuration.preferences.dialogs.NewRelationDialog;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Configuration;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.RelationsPredicatesMapping;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Type;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer;
import org.eclipse.ziggurat.inject.ZigguratInject;

public class TraceabilityTypePreferencePage extends AbstractPreferencePage {
	public TraceabilityTypePreferencePage() {
	}

	@Override
	protected ViewerFilter getFilter() {
		return new ViewerFilter() {

			@Override
			public boolean select(Viewer viewer, Object parentElement,
					Object element) {
				return !(element instanceof Type)
						&& !(element instanceof RelationsPredicatesMapping);
			}
		};
	}

	@Override
	protected boolean removeCondition(EObject firstElement) {
		return !(firstElement instanceof TypeConfigContainer)
				&& firstElement != null;
	}

	@Override
	protected void addAction() {
		if (treeViewer.getSelection() instanceof IStructuredSelection) {
			IStructuredSelection selec = (IStructuredSelection) treeViewer
					.getSelection();
			Object firstElement = selec.getFirstElement();
			if (firstElement == null) {
				createConfiguration();
			} else if (firstElement instanceof Relation) {
				createAttribute((Relation) firstElement);
			} else if (firstElement instanceof TypeConfigContainer) {
				createConfiguration();
			} else if (firstElement instanceof Configuration) {
				createRelation((Configuration) firstElement);
			}
		} else {
			createConfiguration();
		}

	}

	protected void createRelation(Configuration conf) {
		NewRelationDialog dialog = new NewRelationDialog(getShell(), container);
		ZigguratInject.inject(dialog);
		if (dialog.open() == NewRelationDialog.OK) {
			conf.getRelations().add(dialog.getRelation());
		}
	}

	private void createAttribute(Relation relation) {
		NewAttributeDialog dialog = new NewAttributeDialog(getShell(),
				container);
		ZigguratInject.inject(dialog);
		if (dialog.open() == NewAttributeDialog.OK) {
			relation.getAttributes().add(EcoreUtil.copy(dialog.getAttribute()));
		}
	}

	@Override
	public boolean performOk() {
		boolean result = super.performOk();
		if (result) {
			ReqCycleDynamicPackage.reinitURIPackage();
		}
		return result;
	}

	@Override
	protected void removeAction() {
		if (treeViewer.getSelection() instanceof IStructuredSelection) {
			IStructuredSelection new_name = (IStructuredSelection) treeViewer
					.getSelection();
			EObject firstElement = (EObject) new_name.getFirstElement();
			if (removeCondition(firstElement)) {
				EcoreUtil.delete(firstElement, true);
			}
		}
	}
	
	@Override
	public void setVisible(boolean visible) {
		if(visible) {
			doLoad();
			setInput();
		}
		super.setVisible(visible);
	}

	
	
}

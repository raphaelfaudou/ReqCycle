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
 *  Anass RADOUANI (AtoS) anass.radouani@atos.net - Initial API and implementation
 *
  *****************************************************************************/
package org.eclipse.reqcycle.repository.data.editor;

import java.net.URI;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import DataModel.RequirementSource;
import DataModel.Section;
import DataModel.presentation.DataModelEditor;
import RequirementSourcesConf.RequirementSources;


/**
 * The Class RequirementSourceEditor.
 */
public class RequirementsEditor extends DataModelEditor {
	
	/** The Constant EDITOR ID. */
	public static final String EDITOR_ID = "DataModel.presentation.DataModelEditorID";
	protected static RequirementSource selectedRequirementSource;

	/**
	 * Open Requirement editor.
	 *
	 * @param selectedRequirementSource the selected requirement source
	 * @param inputURI the input uri
	 */
	public static void openEditor(RequirementSource selectedRequirementSource, URI inputURI) {
		RequirementsEditor.selectedRequirementSource = selectedRequirementSource;
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		try {
			IDE.openEditor(page, inputURI, EDITOR_ID, true);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

	protected RequirementSource editedRequirementSource;
	
	@Override
	public void createPages() {
		super.createPages();
		selectionViewer.setContentProvider(new ITreeContentProvider() {
			
			@Override
			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void dispose() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean hasChildren(Object element) {
				if(element instanceof RequirementSource) {
					return !((RequirementSource)element).getRequirements().isEmpty();
				}
				if(element instanceof Section) {
					return !((Section)element).getChildren().isEmpty();
				}
				return false;
			}
			
			@Override
			public Object getParent(Object element) {
				if(element instanceof EObject) {
					return ((EObject)element).eContainer();
				}
				return null;
			}
			
			@Override
			public Object[] getElements(Object inputElement) {
				if(inputElement instanceof ResourceSet) {
					EList<Resource> resources = ((ResourceSet)inputElement).getResources();
					for(Resource resource : resources) {
						EList<EObject> contents = resource.getContents();
						for(EObject eObject : contents) {
							if(eObject instanceof RequirementSources) {
								EList<RequirementSource> requirementSources = ((RequirementSources)eObject).getRequirementSources();
								for(RequirementSource requirementSource : requirementSources) {
									if(editedRequirementSource != null) {
										return new Object[]{editedRequirementSource};
									}
									if(requirementSource instanceof RequirementSource && EcoreUtil.equals(requirementSource, selectedRequirementSource)) {
										editedRequirementSource = requirementSource;
										return new Object[]{requirementSource};
									}
								}
							}
						}
					}
				}
				return new Object[]{};
			}
			
			@Override
			public Object[] getChildren(Object parentElement) {
				if(parentElement instanceof RequirementSource) {
					return ((RequirementSource)parentElement).getRequirements().toArray();
				}
				if(parentElement instanceof Section) {
					return ((Section)parentElement).getChildren().toArray();
				}
				return new Object[]{};
			}
		});
	}
	
	@Override
	protected void setPartName(String partName) {
		super.setPartName("Requirements Editor");
	}
}

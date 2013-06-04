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

package org.eclipse.reqcycle.repository.ui.providers;

import javax.inject.Inject;

import org.agesys.inject.AgesysInject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.reqcycle.repository.connector.ui.IConnectorManagerUi;
import org.eclipse.swt.graphics.Image;

import DataModel.Contained;
import DataModel.RequirementSource;

public class RequirementLabelProvider extends LabelProvider {
	
	private @Inject IConnectorManagerUi manager = AgesysInject.make(IConnectorManagerUi.class);
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		
		if(element instanceof RequirementSource) {
			return ((RequirementSource)element).getRepositoryLabel() + " (" +  ((RequirementSource)element).getRepositoryUri() + " )";
		}
		
		if(element instanceof Contained) {
			String name = ((Contained)element).getName();
			EList<EStructuralFeature> structuralFeatures = ((Contained)element).eClass().getEStructuralFeatures();
			String attr = " [ id : " + ((Contained)element).getId() + " ]" + "[ name : " + ((Contained)element).getName() + " ]";
			
			for(EStructuralFeature eStructuralFeature : structuralFeatures) {
				attr += "[ " + eStructuralFeature.getName() + " : " + ((Contained)element).eGet(eStructuralFeature) + "]";
			}
			return name + attr;
		}
		
		return super.getText(element);
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
	 */
	@Override
	public Image getImage(Object element) {
		
		if(element instanceof RequirementSource) {
			String connectorId = ((RequirementSource)element).getConnectorID();
			return manager.getImage(connectorId, 20, 20);
		}
		
		return super.getImage(element);
	}
	
}

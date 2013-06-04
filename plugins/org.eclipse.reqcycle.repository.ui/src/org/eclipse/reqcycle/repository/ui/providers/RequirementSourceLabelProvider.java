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

/**
 * 
 */
package org.eclipse.reqcycle.repository.ui.providers;

import javax.inject.Inject;

import org.agesys.inject.AgesysInject;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.reqcycle.repository.connector.IConnectorManager;
import org.eclipse.reqcycle.repository.connector.ui.IConnectorManagerUi;
import org.eclipse.reqcycle.repository.requirement.data.util.DataUtil;
import org.eclipse.reqcycle.repository.ui.Activator;
import org.eclipse.reqcycle.repository.ui.Messages;
import org.eclipse.reqcycle.repository.ui.views.ScopeView;
import org.eclipse.swt.graphics.Image;

import DataModel.RequirementSource;

public class RequirementSourceLabelProvider extends LabelProvider {

	/** Repository icon */
	private static final String ICONS_STATUS_OFFLINE = Messages.GREY_STATUS_ICON;

	/** Repository icon */
	private static final String ICONS_STATUS_ONLINE = Messages.GREEN_STATUS_ICON;

	/** Repository icon */
	private static final String ICONS_STATUS_UNSYNCHRONIZED = Messages.YELLOW_STATUS_ICON;

	private static final String ICONS_STATUS_BUSY = Messages.RED_STATUS_ICON;
	
	private @Inject IConnectorManager repositoryConnectorManager = AgesysInject.make(IConnectorManager.class);
	
	private @Inject IConnectorManagerUi repositoryConnectorManagerUi = AgesysInject.make(IConnectorManagerUi.class);


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
	 */
	public String getText(Object obj) {
		if(obj instanceof String && repositoryConnectorManager.getConnector((String)obj) != null) {
			return repositoryConnectorManager.getConnector((String)obj).getLabel();
		}
		if(obj instanceof RequirementSource) {
			return DataUtil.getLabel(obj);
		}
		return obj.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
	 */
	public Image getImage(Object obj) {
		
		if(obj instanceof String) {
			return repositoryConnectorManagerUi.getImage((String)obj, 16, 16);
		}
		
		return Activator.getImageDescriptor(ICONS_STATUS_OFFLINE).createImage();
		
//		Image image = null;
//		if(obj instanceof TreeParent) {
//			image = ((TreeParent)obj).getImage();
//		} else if(obj instanceof TreeObject) {
//			if(((TreeObject)obj).getCanSynchronize()) {
//				if(((TreeObject)obj).getIsSynchronized()) {
//					image = Activator.getImageDescriptor(ICONS_STATUS_ONLINE).createImage();
//				} else {
//					if(((TreeObject)obj).getIsBusy()) {
//						image = Activator.getImageDescriptor(ICONS_STATUS_BUSY).createImage();
//					} else {
//						image = Activator.getImageDescriptor(ICONS_STATUS_UNSYNCHRONIZED).createImage();
//					}
//				}
//			} else {
//				image = Activator.getImageDescriptor(ICONS_STATUS_OFFLINE).createImage();
//			}
//
//		}
//		return image;
	}
}

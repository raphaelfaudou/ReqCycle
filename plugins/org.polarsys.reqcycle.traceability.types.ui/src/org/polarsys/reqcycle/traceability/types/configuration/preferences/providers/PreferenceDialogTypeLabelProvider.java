/*******************************************************************************
 *  * Copyright (c) 2013-2014 AtoS.
 *  * All rights reserved. This program and the accompanying materials
 *  * are made available under the terms of the Eclipse Public License v1.0
 *  * which accompanies this distribution, and is available at
 *  * http://www.eclipse.org/legal/epl-v10.html
 *  *
 *  * Contributors:
 *  *    {INITIAL AUTHOR} - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.types.configuration.preferences.providers;

import javax.inject.Inject;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.polarsys.reqcycle.traceability.types.configuration.preferences.ElementTypeConfigurationPage;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.CustomType;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Type;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer;
import org.polarsys.reqcycle.types.ITypesManager;
import org.polarsys.reqcycle.types.ui.providers.TypeLabelProvider;
import org.eclipse.swt.graphics.Image;

public class PreferenceDialogTypeLabelProvider extends
		AdapterFactoryLabelProvider {
	@Inject
	private ITypesManager typesManager;
	private ILabelProvider original;
	private TypeLabelProvider forTypes;

	public PreferenceDialogTypeLabelProvider(AdapterFactory adapterFactory,
			ILabelProvider delegeated) {
		super(adapterFactory);
		original = delegeated;
		forTypes = new TypeLabelProvider();
	}

	@Override
	public String getText(Object object) {
		String text = super.getText(object);
		if (object instanceof Type) {
			Type type = (Type) object;
			if (ElementTypeConfigurationPage.isExtensible(type, typesManager)) {
				text += " (can be specialized)";
			}

		} else if (object instanceof TypeConfigContainer) {
			text = "Types";
		}
		return text;
	}
	
	@Override
	public Image getImage(Object element) {
		String typeId = null;
		if (element instanceof CustomType) {
			CustomType custom = (CustomType) element;
			typeId = custom.getSuperType().getTypeId();
		} else if (element instanceof Type) {
			Type type = (Type) element;
			typeId = type.getTypeId();
		}
		if (typeId != null) {
			return forTypes.getImage(typesManager.getType(typeId));
		} else {
			return original.getImage(element);
		}
	}
}

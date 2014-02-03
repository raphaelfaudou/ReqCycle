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
package org.eclipse.reqcycle.ui.eattrpropseditor.api;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Represents the result of the edition done into an {@link IEAttrPropsEditor}.
 * 
 * @author Papa Issa DIAKHATE
 */
public interface IEditionResult {

	/**
	 * @return The {@link EObject} that is to be edited.
	 */
	EObject getEObjectToEdit();

	/**
	 * @return The Map containing the edited {@link EStructuralFeature} of the <tt>EObject</tt>.
	 */
	Map<EStructuralFeature, Object> getEditedEntries();

}

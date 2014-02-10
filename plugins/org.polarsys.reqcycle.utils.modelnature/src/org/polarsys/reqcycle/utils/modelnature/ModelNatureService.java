/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Olivier Melois (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.utils.modelnature;

import java.util.Collection;

import org.eclipse.emf.ecore.EModelElement;
import org.polarsys.reqcycle.utils.modelnature.exceptions.NatureNotFoundException;


public interface ModelNatureService {



	/**
	 * Adds a nature to an eObject.
	 * 
	 * @param eObject
	 * @param natureID
	 *        the id of the nature to be added.
	 */
	public void addNature(EModelElement eObject, String natureID) throws NatureNotFoundException;

	/**
	 * Removes a nature from an eObject.
	 * 
	 * @param eObject
	 * @param natureID
	 *        the id of the nature to be removed.
	 */
	public void removeNature(EModelElement eObject, String natureID) throws NatureNotFoundException;


	/**
	 * Checks whether an eObject has a given nature.
	 * 
	 * @param eObject
	 * @param natureID
	 *        the id of the nature to be checked.
	 */
	public boolean hasNature(EModelElement eObject, String natureID);


	/**
	 * Return all registered model nature
	 * 
	 * @return
	 */
	public Collection<ModelNature> getModelNatures();

	/**
	 * Return all registered model nature ids
	 * 
	 * @return
	 */
	public Collection<String> getModelNaturesIds();

}
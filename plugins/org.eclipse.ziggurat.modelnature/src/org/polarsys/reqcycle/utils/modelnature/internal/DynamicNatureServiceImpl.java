/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Olivier Melois (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.utils.modelnature.internal;

import java.util.Collection;

import javax.inject.Singleton;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.reqcycle.utils.modelnature.ModelNature;
import org.polarsys.reqcycle.utils.modelnature.ModelNatureService;
import org.polarsys.reqcycle.utils.modelnature.exceptions.NatureNotFoundException;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

@Singleton
public class DynamicNatureServiceImpl implements ModelNatureService {

	protected Multimap<String, String> natureMap = HashMultimap.create();


	@Override
	public void addNature(EModelElement eObject, String natureID) throws NatureNotFoundException {
		String fragment = EcoreUtil.getURI(eObject).fragment();
		if(!getModelNaturesIds().contains(natureID)) {
			throw new NatureNotFoundException(natureID);
		}
		natureMap.put(fragment, natureID);
	}

	@Override
	public void removeNature(EModelElement eObject, String natureID) throws NatureNotFoundException {
		String fragment = EcoreUtil.getURI(eObject).fragment();
		natureMap.remove(fragment, natureID);
	}

	@Override
	public boolean hasNature(EModelElement eObject, String natureID) {
		String fragment = EcoreUtil.getURI(eObject).fragment();
		if(!getModelNaturesIds().contains(natureID)) {
			return false;
		}
		Collection<String> naturesOfEObject = natureMap.get(fragment);
		return naturesOfEObject.contains(natureID);
	}

	@Override
	public Collection<ModelNature> getModelNatures() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> getModelNaturesIds() {
		// TODO Auto-generated method stub
		return null;
	}

}

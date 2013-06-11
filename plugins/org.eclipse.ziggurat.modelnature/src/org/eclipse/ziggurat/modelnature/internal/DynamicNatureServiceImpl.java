/*******************************************************************************
 * Copyright (c) 2013 Atos.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Atos - initial API and implementation
 ******************************************************************************/
package org.eclipse.ziggurat.modelnature.internal;

import java.util.Collection;

import javax.inject.Singleton;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ziggurat.modelnature.ModelNature;
import org.eclipse.ziggurat.modelnature.ModelNatureService;
import org.eclipse.ziggurat.modelnature.exceptions.NatureNotFoundException;

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

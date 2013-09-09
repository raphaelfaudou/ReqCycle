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
package org.eclipse.reqcycle.repository.data.impl;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.reqcycle.repository.data.IDataManager;
import org.eclipse.reqcycle.repository.data.IDataModelManager;
import org.eclipse.reqcycle.repository.data.IListener;
import org.eclipse.ziggurat.configuration.IConfigurationManager;

import DataModel.RequirementSource;
import RequirementSourcesConf.RequirementSources;
import RequirementSourcesConf.RequirementSourcesConfFactory;

@Singleton
public class DataManagerImpl implements IDataManager {

	/** Connector id to repositories */
	private Map<String, Set<RequirementSource>> repositoryMap = new HashMap<String, Set<RequirementSource>>();

	private RequirementSources sources;

	@Inject
	IConfigurationManager confManager;

	public static final String ID = "org.eclipse.reqcycle.repositories";

	@Inject
	@Named("confResourceSet")
	private ResourceSet rs;

	@Inject
	IDataModelManager dataManager;

	public Set<IListener> listeners = new HashSet<IListener>();


	/**
	 * Constructor
	 */
	@Inject
	DataManagerImpl(@Named("confResourceSet") ResourceSet rs, IConfigurationManager confManager, IDataModelManager dataManager) {
		this.rs = rs;
		this.confManager = confManager;
		this.dataManager = dataManager;

		EObject conf = confManager.getConfiguration(null, IConfigurationManager.Scope.WORKSPACE, ID, rs, true);

		if(conf instanceof RequirementSources) {
			sources = (RequirementSources)conf;
			Collection<RequirementSource> RequirementSources = sources.getRequirementSources();
			for(RequirementSource requirementSourceRepository : RequirementSources) {
				String connectorId = requirementSourceRepository.getConnectorId();
				if(repositoryMap.containsKey(connectorId)) {
					repositoryMap.get(connectorId).add(requirementSourceRepository);
				} else {
					Set<RequirementSource> set = new HashSet<RequirementSource>();
					set.add(requirementSourceRepository);
					repositoryMap.put(connectorId, set);
				}
			}
		} else {
			sources = RequirementSourcesConfFactory.eINSTANCE.createRequirementSources();
		}
	}

	@Override
	public void addRequirementSource(final RequirementSource repository) {
		Set<RequirementSource> repositories;
		repositories = repositoryMap.get(repository.getConnectorId());
		if(repositories == null) {
			repositories = new HashSet<RequirementSource>();
			repositoryMap.put(repository.getConnectorId(), repositories);
		}

		if(!repositories.contains(repository)) {
			repositories.add(repository);
		}

		if(!sources.getRequirementSources().contains(repository)) {
			sources.getRequirementSources().add(repository);
		}

		try {
			save();
			notifyListeners();
		} catch (IOException e) {
			//FIXME : use Logger
			e.printStackTrace();
		}
	}

	@Override
	public void removeRequirementSource(final RequirementSource repository) {
		Set<RequirementSource> repositories = repositoryMap.get(repository.getConnectorId());
		if(repositories != null) {
			repositories.remove(repository);
			sources.removeRequirementSource(repository);
			repository.getRequirements().clear();
			EcoreUtil.delete(repository, true);
			try {
				save();
				notifyListeners();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void save() throws IOException {
		confManager.saveConfiguration(sources, null, null, ID, rs);
	}

	@Override
	public void removeRequirementSources(String connectorId) {
		Set<RequirementSource> repositories = repositoryMap.get(connectorId);
		for(RequirementSource reqSource : repositories) {
			sources.removeRequirementSource(reqSource);
			try {
				save();
				notifyListeners();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		repositoryMap.remove(connectorId);
	}

	@Override
	public RequirementSource getRequirementSource(String connectorId, String repositoryUri) {
		Assert.isNotNull(connectorId);
		Assert.isNotNull(repositoryUri);
		if(repositoryMap.containsKey(connectorId)) {
			for(RequirementSource repository : repositoryMap.get(connectorId)) {
				if(repository.getRepositoryUri().equals(repositoryUri)) {
					return repository;
				}
			}
		}
		return null;
	}

	@Override
	public Set<RequirementSource> getRequirementSources(String connectorId) {
		Assert.isNotNull(connectorId);
		Set<RequirementSource> result;
		result = repositoryMap.get(connectorId);
		if(result == null) {
			return Collections.emptySet();
		}
		return new HashSet<RequirementSource>(result);
	}

	@Override
	public Map<String, Set<RequirementSource>> getRepositoryMap() {
		return repositoryMap;
	}

	@Override
	public Set<RequirementSource> getRequirementSource() {
		Collection<Set<RequirementSource>> values = repositoryMap.values();
		Set<RequirementSource> res = new HashSet<RequirementSource>();
		for(Set<RequirementSource> sources : values) {
			res.addAll(sources);
		}
		return res;
	}



	@Override
	public void addListener(IListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeListener(IListener listener) {
		listeners.remove(listener);
	}

	protected void notifyListeners() {
		for(IListener listener : listeners) {
			listener.handleChange(this.getClass());
		}
	}

}

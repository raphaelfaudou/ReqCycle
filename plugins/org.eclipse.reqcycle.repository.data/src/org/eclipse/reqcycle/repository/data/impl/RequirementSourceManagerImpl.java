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
import javax.inject.Singleton;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.reqcycle.repository.data.IRequirementSourceManager;
import org.eclipse.reqcycle.repository.data.IScopeManager;
import org.eclipse.ziggurat.configuration.IConfigurationManager;
import org.eclipse.ziggurat.inject.ZigguratInject;

import DataModel.Contained;
import DataModel.RequirementSource;
import DataModel.Section;
import RequirementSourcesConf.RequirementSources;
import RequirementSourcesConf.RequirementSourcesConfFactory;

@Singleton
public class RequirementSourceManagerImpl implements IRequirementSourceManager {

	/** Connector id to repositories */
	private Map<String, Set<RequirementSource>> repositoryMap = new HashMap<String, Set<RequirementSource>>();

	private RequirementSources sources;

	@Inject
	private static IConfigurationManager confManager;

	@Inject
	private IScopeManager scopeManager;

	public static final String ID = "org.eclipse.reqcycle.repositories";

	private ResourceSet rs = new ResourceSetImpl();

	/**
	 * Constructor
	 */
	RequirementSourceManagerImpl() {

		confManager = ZigguratInject.make(IConfigurationManager.class);
		EObject conf = confManager.getConfiguration(null, IConfigurationManager.Scope.WORKSPACE, ID, rs, false);
		if(conf instanceof RequirementSources) {
			sources = (RequirementSources)conf;
			EList<RequirementSource> RequirementSources = ((RequirementSources)conf).getRequirementSources();
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

	public void addRepository(final RequirementSource repository, ResourceSet rs) {
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
			if(rs != null) {
				confManager.saveConfiguration(sources, null, null, ID, rs);
			} else {
				confManager.saveConfiguration(sources, null, null, ID);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void removeRequirementSource(final RequirementSource repository) {
		Set<RequirementSource> repositories = repositoryMap.get(repository.getConnectorId());
		if(repositories != null) {
			//TODO : remove the repository by command
			repositories.remove(repository);
			sources.removeRequirementSource(repository);
			removeScopes(repository.getRequirements());
			repository.getRequirements().clear();
			EcoreUtil.delete(repository, true);
			try {
				confManager.saveConfiguration(sources, null, null, ID);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Deprecated
	//TODO remove method
	public void removeRequirements(RequirementSource repository) {
		removeScopes(repository.getRequirements());
		repository.getRequirements().clear();
	}

	/**
	 * @param reqs
	 */
	private void removeScopes(EList<Contained> reqs) {
		for(Contained contained : reqs) {
			scopeManager.removeFromScopes(contained);

			contained.getScopes().clear();
			if(contained instanceof Section) {
				removeScopes(((Section)contained).getChildren());
			}
		}
	}

	public void removeConnectorRepositories(String connectorId) {
		Set<RequirementSource> repositories = repositoryMap.get(connectorId);
		for(RequirementSource iRequirementSourceRepository : repositories) {
			sources.removeRequirementSource(iRequirementSourceRepository);
			try {
				confManager.saveConfiguration(sources, null, null, ID);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		repositoryMap.remove(connectorId);
	}

	public RequirementSource getRepository(String kind, String urlString) {
		Assert.isNotNull(kind);
		Assert.isNotNull(urlString);
		if(repositoryMap.containsKey(kind)) {
			for(RequirementSource repository : repositoryMap.get(kind)) {
				if(repository.getRepositoryUri().equals(urlString)) {
					return repository;
				}
			}
		}
		return null;
	}

	public Set<RequirementSource> getRepositories(String connectorId) {
		Assert.isNotNull(connectorId);
		Set<RequirementSource> result;
		result = repositoryMap.get(connectorId);
		if(result == null) {
			return Collections.emptySet();
		}
		return new HashSet<RequirementSource>(result);
	}

	public Map<String, Set<RequirementSource>> getRepositoryMap() {
		return repositoryMap;
	}

	@Override
	@Deprecated
	//TODO remove method
	public void remove(Object toRemove) {
		if(toRemove instanceof String && repositoryMap.containsKey((String)toRemove)) {
			removeConnectorRepositories((String)toRemove);
		}
		if(toRemove instanceof RequirementSource) {
			removeRequirementSource((RequirementSource)toRemove);
		}
	}

	@Override
	public Set<RequirementSource> getRepositories() {
		Collection<Set<RequirementSource>> values = repositoryMap.values();
		Set<RequirementSource> res = new HashSet<RequirementSource>();
		for(Set<RequirementSource> sources : values) {
			res.addAll(sources);
		}
		return res;
	}

}

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
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.reqcycle.core.ILogger;
import org.eclipse.reqcycle.repository.data.Activator;
import org.eclipse.reqcycle.repository.data.IDataManager;
import org.eclipse.reqcycle.repository.data.IDataModelManager;
import org.eclipse.reqcycle.repository.data.IDataTopics;
import org.eclipse.ziggurat.configuration.IConfigurationManager;

import RequirementSourceData.AbstractElement;
import RequirementSourceData.RequirementSource;
import RequirementSourceData.RequirementSourceDataFactory;
import RequirementSourceData.Section;
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

	@Inject
	IEventBroker broker;

	@Inject
	ILogger logger;


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
			notifyChange(IDataTopics.NEW_SOURCE, repository);
		} catch (IOException e) {
			e.printStackTrace();
			logger.log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage()));
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
				notifyChange(IDataTopics.REMOVE_SOURCE, repository);
			} catch (IOException e) {
				e.printStackTrace();
				logger.log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage()));
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
			notifyChange(IDataTopics.REMOVE_REQUIREMENT, reqSource);
		}
		try {
			save();
			repositoryMap.remove(connectorId);
		} catch (IOException e) {
			e.printStackTrace();
			logger.log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage()));
		}
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
	public void notifyChange(String event, Object data) {
		//asynchronous publishing
		broker.post(event, data);
	}

	@Override
	public RequirementSource createRequirementSource(String name, String connectorId) {
		RequirementSource source = RequirementSourceDataFactory.eINSTANCE.createRequirementSource();
		source.setName(name);
		source.setConnectorId(connectorId);
		return source;
	}

	@Override
	public Section createSection(String id, String name, String uri) {
		Section section = RequirementSourceDataFactory.eINSTANCE.createSection();
		section.setId(id);
		section.setName(name);
		section.setUri(uri);
		return section;
	}

	@Override
	public void addAttribute(AbstractElement element, org.eclipse.reqcycle.repository.data.types.IAttribute attribute, Object value) {
		EAttribute eAttribute = null;
		if(attribute instanceof IAdaptable) {
			eAttribute = (EAttribute)((IAdaptable)attribute).getAdapter(EAttribute.class);
		}
		//FIXME : raise exception when eAttribute is null (must not happen)
		if(eAttribute != null) {
			element.eSet(eAttribute, value);
		}
	};

}

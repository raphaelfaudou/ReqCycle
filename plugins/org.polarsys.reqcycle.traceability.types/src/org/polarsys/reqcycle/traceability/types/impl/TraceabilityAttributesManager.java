/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.types.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Path;
import org.polarsys.reqcycle.traceability.storage.IStorageProvider;
import org.polarsys.reqcycle.traceability.storage.ITraceabilityStorage;
import org.polarsys.reqcycle.traceability.types.ITraceabilityAttributesManager;
import org.polarsys.reqcycle.traceability.types.ITypesConfigurationProvider;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Attribute;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Configuration;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation;
import org.polarsys.reqcycle.uri.IReachableManager;
import org.polarsys.reqcycle.uri.exceptions.IReachableHandlerException;
import org.polarsys.reqcycle.uri.model.IReachableHandler;
import org.polarsys.reqcycle.uri.model.Reachable;
import org.polarsys.reqcycle.uri.model.ReachableObject;

import com.google.common.base.Predicate;

import static com.google.common.collect.Iterables.find;

public class TraceabilityAttributesManager implements
		ITraceabilityAttributesManager {

	public static final String STORAGE_PATH = "./.t-attributes";
	public static final String RELATION_NAME = "relationKind";

	@Inject
	IReachableManager reachableManager;
	@Inject
	ITypesConfigurationProvider typesConfigurationProvider;
	@Inject
	@Named("RDF")
	IStorageProvider storageProvider;

	@Override
	public Collection<EditableAttribute> getAttributes(Reachable reachable) {
		// check if default configuration is set
		Configuration defaultConfiguration = typesConfigurationProvider
				.getDefaultConfiguration();
		if (defaultConfiguration == null) {
			return Collections.emptyList();
		}
		// retrieve the reachable object to get correct project
		try {
			IReachableHandler handler = reachableManager
					.getHandlerFromReachable(reachable);
			ReachableObject object = handler.getFromReachable(reachable);
			IFile file = (IFile) object.getAdapter(IFile.class);
			if (file != null) {
				return getAttributes(reachable, defaultConfiguration, file);
			}
		} catch (IReachableHandlerException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	private Collection<EditableAttribute> getAttributes(Reachable reachable,
			Configuration configuration, IFile file) {
		ITraceabilityStorage storage = storageProvider.getStorage(file
				.getProject().getFile(new Path(STORAGE_PATH)).getLocationURI()
				.getPath());
		if (storage == null) {
			return Collections.emptyList();
		}
		return getAttributes(reachable, configuration, storage);
	}

	private Collection<EditableAttribute> getAttributes(Reachable reachable,
			Configuration configuration, ITraceabilityStorage storage) {
		final String relationName = storage.getProperty(reachable,
				RELATION_NAME);
		if (relationName != null) {
			Relation rel = find(configuration.getRelations(),
					new Predicate<Relation>() {

						@Override
						public boolean apply(Relation arg0) {
							return relationName.equals(arg0.getKind());
						}

					}, null);
			if (rel != null) {
				return getAttributes(reachable, storage, rel);
			}
		}
		return Collections.emptyList();
	}

	private Collection<EditableAttribute> getAttributes(Reachable reachable,
			ITraceabilityStorage storage, Relation rel) {
		Collection<EditableAttribute> attributes = new ArrayList<ITraceabilityAttributesManager.EditableAttribute>();
		for (Attribute a : rel.getAttributes()) {
			attributes.add(new FromStorageEditableAttribute(a, reachable,
					storageProvider, storage.getPath()));
		}
		return attributes;
	}
}

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

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.polarsys.reqcycle.utils.modelnature.ModelNature;
import org.polarsys.reqcycle.utils.modelnature.ModelNaturePlugin;
import org.polarsys.reqcycle.utils.modelnature.ModelNatureService;
import org.polarsys.reqcycle.utils.modelnature.exceptions.NatureNotFoundException;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

@Singleton
public class SerializedNatureServiceImpl implements ModelNatureService {

	private static final String MODEL_NATURE_EXTENSION_POINT_ID = ModelNaturePlugin.PLUGIN_ID + ".modelnature"; //$NON-NLS-1$


	protected static final String SEPARATOR = ";";


	protected static final String NATURE_ANNOTATION_KEY = "MODEL_NATURES";

	protected static final String NATURE_SOURCE_URI = "http://www.agesys.org/nature";

	@Override
	public void addNature(final EModelElement eObject, String natureID) throws NatureNotFoundException {
		//No need to add the nature if the eObject already has it.
		if(hasNature(eObject, natureID)) {
			return;
		}

		if(!getModelNaturesIds().contains(natureID)) {
			throw new NatureNotFoundException(natureID);
		}
		String[] natures = getNatures(eObject);
		List<String> newNatures = Lists.newArrayList();
		newNatures.addAll(Arrays.asList(natures));
		newNatures.add(natureID);
		final String newNaturesAsString = naturesAsString(newNatures);
		safeExecute(new AbstractCommand() {

			public void execute() {
				EcoreUtil.setAnnotation(eObject, NATURE_SOURCE_URI, NATURE_ANNOTATION_KEY, newNaturesAsString);
			}

			@Override
			public void redo() {
			};

		}, TransactionUtil.getEditingDomain(eObject));
	}

	@Override
	public void removeNature(final EModelElement eObject, String natureID) throws NatureNotFoundException {
		if(!getModelNaturesIds().contains(natureID)) {
			throw new NatureNotFoundException(natureID);
		}
		String[] natures = getNatures(eObject);
		List<String> newNatures = Lists.newArrayList();
		newNatures.addAll(Arrays.asList(natures));
		newNatures.remove(natureID);
		final String newNaturesAsString = naturesAsString(newNatures);
		safeExecute(new AbstractCommand() {

			public void execute() {
				if(newNaturesAsString.isEmpty()) {
					EcoreUtil.setAnnotation(eObject, NATURE_SOURCE_URI, NATURE_ANNOTATION_KEY, null);
				} else {
					EcoreUtil.setAnnotation(eObject, NATURE_SOURCE_URI, NATURE_ANNOTATION_KEY, newNaturesAsString);
				}
			}

			@Override
			public void redo() {
			};

		}, TransactionUtil.getEditingDomain(eObject));
	}

	@Override
	public boolean hasNature(EModelElement eObject, String natureID) {
		if(!getModelNaturesIds().contains(natureID)) {
			return false;
		}
		String[] natures = getNatures(eObject);
		List<String> naturesAsList = Arrays.asList(natures);
		return naturesAsList.contains(natureID);
	}

	/**
	 * Gets the natures of an eObject.
	 * 
	 * @param eObject
	 * @return
	 */
	protected String[] getNatures(EModelElement eObject) {
		String annotation = EcoreUtil.getAnnotation(eObject, NATURE_SOURCE_URI, NATURE_ANNOTATION_KEY);
		if(annotation != null) {
			String[] naturesAsArray = annotation.split(SEPARATOR); //$NON-NLS-1$
			return naturesAsArray;
		}
		return new String[0];
	}

	/**
	 * Transforms a list of nature ids into a string usable in an annotation.
	 * 
	 * @param natures
	 * @return
	 */
	protected String naturesAsString(List<String> natures) {
		StringBuilder builder = new StringBuilder();
		if(natures.size() > 0) {
			for(int i = 0; i < natures.size(); i++) {
				String nature = natures.get(i);
				if(getModelNaturesIds().contains(nature)) {
					if(i > 0) {
						builder.append(SEPARATOR);
					}
					builder.append(nature);
				}
			}
		}
		return builder.toString();
	}

	/**
	 * Runs a command using a transaction if needed.
	 * 
	 * @param command
	 * @param domain
	 */
	protected void safeExecute(final Command command, TransactionalEditingDomain domain) {
		if(domain == null) {
			command.execute();
		} else {
			RecordingCommand recordingCommand = new RecordingCommand(domain) {

				@Override
				protected void doExecute() {
					command.execute();
				}
			};
			domain.getCommandStack().execute(recordingCommand);
		}
	}

	private Map<String, ModelNature> natures = null;

	@Override
	public Collection<ModelNature> getModelNatures() {
		if(natures == null) {
			natures = new HashMap<String, ModelNature>();
			IExtensionRegistry reg = Platform.getExtensionRegistry();
			IExtensionPoint extensionPoint = reg.getExtensionPoint(MODEL_NATURE_EXTENSION_POINT_ID);

			for(IExtension extension : extensionPoint.getExtensions()) {
				for(IConfigurationElement e : extension.getConfigurationElements()) {
					String id = e.getAttribute("id"); //$NON-NLS-1$
					String description = e.getAttribute("description");
					String name = e.getAttribute("name");
					natures.put(id, new ModelNature(id, description, name));
				}
			}
		}
		return natures.values();
	}

	@Override
	public Collection<String> getModelNaturesIds() {

		return Collections2.transform(getModelNatures(), new Function<ModelNature, String>() {

			@Override
			public String apply(ModelNature arg0) {
				return arg0.getId();
			}
		});
	}


}

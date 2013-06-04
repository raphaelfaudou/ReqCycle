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
package org.eclipse.reqcycle.repository.requirement.data.impl;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.agesys.configuration.IConfigurationManager;
import org.agesys.inject.AgesysInject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.reqcycle.core.ILogger;
import org.eclipse.reqcycle.repository.requirement.data.IScopeManager;

import CustomDataModel.CustomDataModelFactory;
import DataModel.Contained;
import DataModel.DataModelFactory;
import DataModel.Scope;
import ScopesConf.Scopes;
import ScopesConf.ScopesConfFactory;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

@Singleton
public class ScopeManagerImpl implements IScopeManager {

	private @Inject
	ILogger logger = AgesysInject.make(ILogger.class);

	private static final String SCOPE_MODEL_ECORE = "org.eclipse.reqcycle.repository.data/model/DataModel.ecore";

	private static final String CUSTOM_DATA_MODEL = "org.eclipse.reqcycle.repository.data/model/CustomDataModel.ecore";
	
	private Scopes scopes;

	private String id = "org.eclipse.reqcycle.repository.scopes";

	private ResourceSet rs = new ResourceSetImpl();

	private @Inject
	static IConfigurationManager confManager;



	ScopeManagerImpl() {
		Scopes confScopes = null;
		

		confManager = AgesysInject.make(IConfigurationManager.class);
		EObject conf = confManager.getConfiguration(null, IConfigurationManager.Scope.WORKSPACE, id, rs);
		if(conf instanceof Scopes) {
			confScopes = (Scopes)conf;
		} else {
			confScopes = ScopesConfFactory.eINSTANCE.createScopes();
		}


		ResourceSet resourceSet = new ResourceSetImpl();

		scopes = confScopes;

		for(EClassifier eClassifier :  getScopes(URI.createPlatformPluginURI(SCOPE_MODEL_ECORE, true), resourceSet)) {
			if(!contains(scopes.getScopes(), eClassifier)) {
				scopes.getScopes().add((Scope)DataModelFactory.eINSTANCE.create((EClass)eClassifier));
			}
		}

		for(EClassifier eClassifier :  getScopes(URI.createPlatformPluginURI(CUSTOM_DATA_MODEL, true), resourceSet)) {
			if(!contains(scopes.getScopes(), eClassifier)) {
				scopes.getScopes().add((Scope)CustomDataModelFactory.eINSTANCE.create((EClass)eClassifier));
			}
		}
		
	}


	/**
	 * @param eClassifiers
	 * @param uri
	 * @param resourceSet
	 * @return
	 */
	private Collection<EClassifier> getScopes(URI uri, ResourceSet resourceSet) {
		Collection<EClassifier> eClassifiers = null;
		Resource attributeModelResource = resourceSet.getResource(uri, true);

		EList<EObject> modelContent = attributeModelResource.getContents();

		if(modelContent.size() > 0) {
			EObject rootElement = modelContent.get(0);
			if(rootElement instanceof EPackage) {
				EList<EClassifier> classifiers = ((EPackage)rootElement).getEClassifiers();
				eClassifiers = Collections2.filter(classifiers, new Predicate<EClassifier>() {
					@Override
					public boolean apply(EClassifier arg0) {
						//TODO : trouver un moyen de verifier qu'ils sont des sous types de Scope
						if(arg0 instanceof EClass) {
							EList<EClass> superTypes = ((EClass)arg0).getEAllSuperTypes();
							for(EClass superType : superTypes) {
								if("Scope".equals(superType.getName())) {
									return true;
								}
							}
						}
						return false;
					}
				});
			}
		}
		return eClassifiers;
	}


	/**
	 * @param scopes2
	 * @param eClassifier
	 * @return
	 */
	private boolean contains(EList<Scope> scopes, EClassifier eClassifier) {
		//TODO use class instance instead of name
		for(Scope scope : scopes) {
			if(scope.eClass().getName().equals(eClassifier.getName())) {
				return true;
			}
		}
		return false;
	}


	@Override
	public Collection<Scope> getAllScopes() {
		return scopes.getScopes();
	}

	public void addToScope(Scope scope, Contained element) {
		//		Resource resource = element.eResource();
		//		scope.getRequirements().add(element);
	}

	public void addToScope(Scope scope, Collection<Contained> elements) {
		for(Contained contained : elements) {
			contained.getScopes().add(scope);

			if(contained.eResource() == null || contained.eResource().getResourceSet() == null) {
				logger.error(contained + " Resource or ResourceSet is null");
				return;
			}

			try {
				confManager.saveConfiguration(scopes, null, null, id, contained.eResource().getResourceSet());
			} catch (IOException e) {
				logger.error("Can't save scope.");
			}
		}
	}

	@Override
	public void removeFromScope(Contained contained) {
		//TODO : correct reference bug when saving conf (error when saving conf)
		for(Scope scope : scopes.getScopes()) {
			EList<Contained> reqs = scope.getRequirements();
			Iterator<Contained> iter = reqs.iterator();
			while(iter.hasNext()) {
				Contained req = (Contained)iter.next();
				if(contained.getId().equals(req.getId())) {
					iter.remove();
//					scope.getRequirements().remove(req);
					try {
						if(req.eResource() != null && req.eResource().getResourceSet() != null) {
							confManager.saveConfiguration(scopes, null, null, id, req.eResource().getResourceSet());
						} else if(contained.eResource() != null && contained.eResource().getResourceSet() != null) {
							confManager.saveConfiguration(scopes, null, null, id, contained.eResource().getResourceSet());
						} else {
							confManager.saveConfiguration(scopes, null, null, id);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
//		try {
//			if(contained.eResource() != null && contained.eResource().getResourceSet() != null) {
//				confManager.saveConfiguration(scopes, null, null, id, contained.eResource().getResourceSet());
//			} else {
//				confManager.saveConfiguration(scopes, null, null, id);
//			}
//		} catch (IOException e) {
//			logger.error("Can't save scope.");
//		}
		//		for(Scope scope : contained.getScopes()) {
		//			scope.getRequirements().remove(contained);
		//			try {
		//				if(contained.eResource() != null && contained.eResource().getResourceSet() != null) {
		//					confManager.saveConfiguration(scopes, null, null, id, contained.eResource().getResourceSet());
		//				} else {
		//					confManager.saveConfiguration(scopes, null, null, id);
		//				}
		//			} catch (IOException e) {
		//				logger.error("Can't save scope.");
		//			}
		//		}
	}

}
